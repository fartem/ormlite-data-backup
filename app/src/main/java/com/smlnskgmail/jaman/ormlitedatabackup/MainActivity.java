package com.smlnskgmail.jaman.ormlitedatabackup;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.jakewharton.processphoenix.ProcessPhoenix;
import com.smlnskgmail.jaman.adaptiverecyclerview.AdaptiveRecyclerView;
import com.smlnskgmail.jaman.ormlitedatabackup.components.BaseActivity;
import com.smlnskgmail.jaman.ormlitedatabackup.components.LongSnackbar;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.event.actions.create.CreateEventBottomSheet;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.event.actions.create.EventCreateTarget;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.event.list.EventsAdapter;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.OrmLiteDatabaseParameters;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.OrmLiteHelperFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup.OrmLiteLocalBackupPath;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup.tasks.create.CreateOrmLiteLocalBackupTask;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup.tasks.restore.RestoreOrmLiteLocalBackupTask;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.entities.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jahirfiquitiva.libs.fabsmenu.FABsMenu;

public class MainActivity extends BaseActivity implements EventCreateTarget {

    private static final int REQUEST_CODE_STORAGE = 101;

    private final List<Event> events = new ArrayList<>();

    private AdaptiveRecyclerView eventsList;
    private FABsMenu menuFAB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        loadEvents();
    }

    private void initViews() {
        eventsList = findViewById(R.id.events_list);
        eventsList.setMessageView(findViewById(R.id.events_list_message_view));

        menuFAB = findViewById(R.id.main_fab_menu);

        setClickToFABTitle(R.id.create_event, view -> {
            hideFab();
            CreateEventBottomSheet createEventBottomSheet = new CreateEventBottomSheet();
            createEventBottomSheet.show(
                    getSupportFragmentManager(),
                    createEventBottomSheet.getClass().getName()
            );
        });
        setClickToFABTitle(R.id.create_local_backup, view -> {
            hideFab();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !isStoragePermissionGranted()) {
                requestStoragePermission();
            } else {
                new CreateOrmLiteLocalBackupTask(
                        MainActivity.this,
                        success -> {
                            int messageResId = success
                                    ? R.string.callback_backup_create_success
                                    : R.string.callback_backup_create_error;
                            showLongSnackbar(messageResId);
                        }
                ).execute();
            }
        });
        setClickToFABTitle(R.id.restore_local_backup, view -> {
            hideFab();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !isStoragePermissionGranted()) {
                requestStoragePermission();
            } else {
                new RestoreOrmLiteLocalBackupTask(
                        MainActivity.this,
                        success -> ProcessPhoenix.triggerRebirth(
                                this,
                                new Intent(this, MainActivity.class)
                        ),
                        localDatabasePath()
                ).execute();
            }
        });
    }

    private String localDatabasePath() {
        OrmLiteDatabaseParameters parameters = OrmLiteHelperFactory.databaseParameters();
        return new OrmLiteLocalBackupPath(parameters).pathAsString();
    }

    private void setClickToFABTitle(
            int resId,
            View.OnClickListener clickListener
    ) {
        findViewById(resId).setOnClickListener(clickListener);
    }

    private void hideFab() {
        menuFAB.collapse();
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean isStoragePermissionGranted() {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestStoragePermission() {
        requestPermissions(
                new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                REQUEST_CODE_STORAGE
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults
    ) {
        super.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults
        );
        if (requestCode == REQUEST_CODE_STORAGE) {
            int snackbarMessageResId;
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                snackbarMessageResId = R.string.request_storage_permission_status_granted;
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    snackbarMessageResId = R.string.request_storage_permission_status_denied;
                } else {
                    snackbarMessageResId = R.string.request_storage_permission_status_blocked;
                }
            }
            showLongSnackbar(snackbarMessageResId);
        }
    }

    private void showLongSnackbar(int messageResId) {
        new LongSnackbar(
                findViewById(android.R.id.content),
                getString(messageResId)
        ).show();
    }

    @Override
    public void eventAdded(@NonNull Event event) {
        OrmLiteHelperFactory.databaseHelper().saveEvent(event);
        events.add(event);

        Objects.requireNonNull(eventsList.getAdapter())
                .notifyItemInserted(events.size() - 1);
    }

    private void loadEvents() {
        events.addAll(OrmLiteHelperFactory.databaseHelper().allEvents());
        eventsList.setAdapter(new EventsAdapter(events));
    }

    @Override
    public int layoutResId() {
        return R.layout.activity_main;
    }

}

package com.smlnskgmail.jaman.ormlitedatabackup.logic.main;

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
import com.smlnskgmail.jaman.ormlitedatabackup.R;
import com.smlnskgmail.jaman.ormlitedatabackup.components.BaseActivity;
import com.smlnskgmail.jaman.ormlitedatabackup.components.LongSnackbar;
import com.smlnskgmail.jaman.ormlitedatabackup.components.views.AdaptiveRecyclerView;
import com.smlnskgmail.jaman.ormlitedatabackup.db.HelperFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.Backup;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.tasks.CreateLocalBackupTask;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.tasks.RestoreLocalBackupTask;
import com.smlnskgmail.jaman.ormlitedatabackup.db.entities.Event;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.createevent.CreateEventBottomSheet;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.main.eventslist.EventHolder;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.main.eventslist.EventsAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jahirfiquitiva.libs.fabsmenu.FABsMenu;

public class MainActivity extends BaseActivity
        implements CreateEventBottomSheet.EventCreateTarget, EventHolder.EventDeleteTarget {

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
        eventsList.setEmptyMessageView(findViewById(R.id.view_empty_message));

        menuFAB = findViewById(R.id.main_fab_menu);

        setClickToFABTitle(R.id.create_event, view -> {
            hideFab();
            CreateEventBottomSheet createEventBottomSheet = new CreateEventBottomSheet();
            createEventBottomSheet.setupCreateEventTarget(MainActivity.this);
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
                new Backup(MainActivity.this, (CreateLocalBackupTask.CreateLocalBackupTarget) success -> {
                    int messageResId = success
                            ? R.string.callback_backup_create_success
                            : R.string.callback_backup_create_error;
                    showLongSnackbar(messageResId);
                }).createLocalBackup();
            }
        });
        setClickToFABTitle(R.id.restore_local_backup, view -> {
            hideFab();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !isStoragePermissionGranted()) {
                requestStoragePermission();
            } else {
                new Backup(MainActivity.this, (RestoreLocalBackupTask.RestoreLocalBackupTarget) success ->
                        ProcessPhoenix.triggerRebirth(
                                this,
                                new Intent(this, MainActivity.class)
                        )
                ).restoreLocalBackup();
            }
        });
    }

    private void setClickToFABTitle(int resId, View.OnClickListener clickListener) {
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
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
        HelperFactory.instance().databaseHelper().saveEvent(event);
        events.add(event);

        Objects.requireNonNull(eventsList.getAdapter()).notifyItemInserted(events.size() - 1);
    }

    private void loadEvents() {
        events.addAll(HelperFactory.instance().databaseHelper().allEvents());
        eventsList.setAdapter(new EventsAdapter(events, this));
    }

    @Override
    public void eventDeleted(@NonNull Event event) {
        HelperFactory.instance().databaseHelper().deleteEvent(event);

        int indexOfEvent = events.indexOf(event);
        events.remove(event);

        Objects.requireNonNull(eventsList.getAdapter()).notifyItemRemoved(indexOfEvent);
    }

    @Override
    public int layoutResId() {
        return R.layout.activity_main;
    }

}

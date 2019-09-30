package com.smlnskgmail.jaman.ormlitedatabackup.navigation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.ormlitedatabackup.R;
import com.smlnskgmail.jaman.ormlitedatabackup.components.BaseActivity;
import com.smlnskgmail.jaman.ormlitedatabackup.components.LongSnackbar;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.Backup;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.create.CreateLocalBackupTarget;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.restore.RestoreLocalBackupTarget;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.Event;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.EventFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.components.newevent.NewEventBottomSheet;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.components.newevent.NewEventTarget;
import com.smlnskgmail.jaman.ormlitedatabackup.logs.ErrorLog;
import com.smlnskgmail.jaman.ormlitedatabackup.logs.Log;
import com.smlnskgmail.jaman.ormlitedatabackup.navigation.eventslist.EventsAdapter;

import java.sql.SQLException;
import java.util.List;

import jahirfiquitiva.libs.fabsmenu.FABsMenu;

public class MainActivity extends BaseActivity implements NewEventTarget {

    private static final int REQUEST_CODE_STORAGE = 101;

    private final Log errorLog = new ErrorLog();

    private RecyclerView eventsList;
    private FABsMenu menuFAB;

    @Override
    public int layoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        initViews();
        loadEvents();
    }

    private void initViews() {
        eventsList = findViewById(R.id.events_list);
        menuFAB = findViewById(R.id.main_fab_menu);

        setClickToFABTitle(R.id.create_event, view -> {
            hideFab();
            NewEventBottomSheet newEventBottomSheet = new NewEventBottomSheet();
            newEventBottomSheet.setupNewEventTarget(MainActivity.this);
            newEventBottomSheet.setupLog(new ErrorLog());
            newEventBottomSheet.show(getSupportFragmentManager(), newEventBottomSheet.getClass().getName());
        });
        setClickToFABTitle(R.id.create_local_backup, view -> {
            hideFab();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !isStoragePermissionGranted()) {
                requestStoragePermission();
            } else {
                new Backup(MainActivity.this, (CreateLocalBackupTarget) result -> {

                }).createLocalBackup();
            }
        });
        setClickToFABTitle(R.id.restore_local_backup, view -> {
            hideFab();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !isStoragePermissionGranted()) {
                requestStoragePermission();
            } else {
                new Backup(MainActivity.this, (RestoreLocalBackupTarget) result -> {

                }).restoreLocalBackup();
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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
            new LongSnackbar(findViewById(android.R.id.content), getString(snackbarMessageResId)).show();
        }
    }

    @Override
    public void newEventAdded() {
        loadEvents();
    }

    private void loadEvents() {
        try {
            List<Event> events = EventFactory.allEvents();
            eventsList.setAdapter(new EventsAdapter(events));
        } catch (SQLException e) {
            errorLog.log(e);
        }
    }

}

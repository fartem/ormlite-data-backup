package com.smlnskgmail.jaman.ormlitedatabackup.db.backup.tools;

import android.content.Context;
import android.net.Uri;

import com.smlnskgmail.jaman.ormlitedatabackup.logs.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopy {

    private final Context context;

    private String from;
    private InputStream fromAsStream;
    private final String to;

    private final Log log;

    public FileCopy(Context context, String from, String to, Log log) {
        this.context = context;
        this.from = from;
        this.to = to;
        this.log = log;
    }

    public FileCopy(Context context, InputStream fromAsStream, String to, Log log) {
        this.context = context;
        this.fromAsStream = fromAsStream;
        this.to = to;
        this.log = log;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public boolean copy() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = fromAsStream != null ? fromAsStream
                    :context.getContentResolver().openInputStream(Uri.fromFile(new File(from)));
            File toFile = new File(to);
            if (toFile.exists()) {
                toFile.delete();
            }
            File toFolder = toFile.getParentFile();
            if (toFolder != null) {
                toFolder.mkdirs();
            }

            outputStream = new FileOutputStream(toFile);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.flush();
            return true;
        } catch (Exception e) {
            log.log(e);
            return false;
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                log.log(e);
            }
        }
    }

}

package com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.ormlitedatabackup.tools.L;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public class FileCopy {

    private final Context context;

    private String from;
    private final String to;

    private InputStream fromAsStream;

    public FileCopy(
            @NonNull Context context,
            @NonNull String from,
            @NonNull String to
    ) {
        this.context = context;
        this.from = from;
        this.to = to;
    }

    public FileCopy(
            @NonNull Context context,
            @NonNull InputStream stream,
            @NonNull String to
    ) {
        this.context = context;
        this.fromAsStream = stream;
        this.to = to;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public boolean copy() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = fromAsStream != null
                    ? fromAsStream
                    : context.getContentResolver().openInputStream(
                            Uri.fromFile(new File(from))
            );
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
            while ((length = Objects.requireNonNull(inputStream).read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.flush();
            return true;
        } catch (Exception e) {
            L.e(e);
            return false;
        } finally {
            try {
                Objects.requireNonNull(inputStream).close();
                Objects.requireNonNull(outputStream).close();
            } catch (IOException e) {
                L.e(e);
            }
        }
    }

}

package com.smlnskgmail.jaman.ormlitedatabackup.components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.smlnskgmail.jaman.ormlitedatabackup.R;

public abstract class BaseBottomSheet extends BottomSheetDialogFragment {

    @Override
    public final void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    protected abstract void init(@NonNull View view);

    @Nullable
    @Override
    public final View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(
                layoutResId(),
                container,
                false
        );
    }

    protected abstract int layoutResId();

    @Override
    public int getTheme() {
        return R.style.AppBottomSheetStyle;
    }

}

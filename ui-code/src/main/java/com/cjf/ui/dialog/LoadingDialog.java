package com.cjf.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.cjf.ui.R;
import com.cjf.ui.base.AnimAction;
import com.cjf.ui.dialog.builder.LoadingDialogBuilder;
import com.cjf.ui.progress.ProgressLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatDialog;

/**
 * 等待加载对话框
 */
public final class LoadingDialog extends BaseDialogFragment {

    private ProgressLayout mProgressLayout;
    private LoadingDialogBuilder mBuilder;

    public LoadingDialog(LoadingDialogBuilder builder) {
        mBuilder = builder;
    }

    public static LoadingDialog create(CharSequence message) {
        LoadingDialogBuilder builder = new LoadingDialogBuilder();
        builder.setMessage(message);
        return builder.create();
    }

    public static LoadingDialog create() {
        LoadingDialogBuilder builder = new LoadingDialogBuilder();
        builder.setMessage(R.string.common_loading);
        return builder.create();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AppCompatDialog dialog = new AppCompatDialog(getContext(), R.style.MaterialDialogBackgroundTransparent);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setWindowAnimations(AnimAction.DEFAULT);
        }
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return mProgressLayout = new ProgressLayout(inflater.getContext());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setCancelable(mBuilder.mCancelable);
        mProgressLayout.setMessage(mBuilder.mMessage);
        mProgressLayout.setMessageVisibility(mBuilder.mMessage == null ? View.GONE : View.VISIBLE);
        super.onViewCreated(view, savedInstanceState);
    }

    public LoadingDialog setMessage(@StringRes int id) {
        return setMessage(getString(id));
    }

    public LoadingDialog setMessage(CharSequence text) {
        if (mProgressLayout != null) {
            mProgressLayout.setMessage(text);
            mProgressLayout.setMessageVisibility(text == null ? View.GONE : View.VISIBLE);
        }
        return this;
    }

}
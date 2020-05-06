package com.cjf.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;

import com.cjf.ui.base.AnimAction;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

/**
 * <p>Title: BaseDialogFragment </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/2/22 18:05
 */
public abstract class BaseDialogFragment extends AppCompatDialogFragment implements IDialogShow {

    protected String TAG = this.getClass().getSimpleName();
    private static final String SAVED_DIALOG_STATE_TAG = "android:savedDialogState";

    private boolean mDismissed;
    private boolean mShownByMe;
    private int mAnimations;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void show(@NonNull FragmentManager manager) {
        show(manager, TAG);
    }

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        if (isShown()) {
            return;
        }
        FragmentActivity activity = getActivity();
        postOnMain(activity, () -> {
            mDismissed = false;
            mShownByMe = true;
            BaseDialogFragment.super.show(manager, tag);
        });
    }

    public void showNow(@NonNull FragmentManager manager) {
        showNow(manager, TAG);
    }

    @Override
    public void showNow(@NonNull FragmentManager manager, @Nullable String tag) {
        FragmentActivity activity = getActivity();
        postOnMain(activity, () -> {
            mDismissed = false;
            mShownByMe = true;
            BaseDialogFragment.super.showNow(manager, tag);
        });
    }

    @Override
    public void dismiss() {
        if (mDismissed) {
            return;
        }
        mDismissed = true;
        mShownByMe = false;
        final Dialog dialog = getDialog();
        if (dialog != null) {
            // 解决Dialog内D存泄漏
            dialog.setOnDismissListener(null);
            dialog.setOnCancelListener(null);
        }
        super.dismiss();
    }

    public boolean isDismissed() {
        return mDismissed;
    }

    public boolean isShown() {
        return mShownByMe;
    }

    /**
     * 设置动画，已经封装好几种样式，具体可见{@link AnimAction}类
     */
    public void setAnimStyle(@StyleRes int id) {
        mAnimations = id;
    }
}

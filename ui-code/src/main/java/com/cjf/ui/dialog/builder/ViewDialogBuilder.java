package com.cjf.ui.dialog.builder;

import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;

import com.cjf.ui.base.ContextAction;
import com.cjf.ui.dialog.AlertDialogFragment;

import androidx.annotation.StringRes;

/**
 * <p>Title: LoadingDialogBuilder </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/10 23:05
 */
public class ViewDialogBuilder implements ContextAction, DialogBuilder<AlertDialogFragment> {

    private CharSequence mTitle;
    private View mView;
    private boolean mFullScreen = false;
    private int mGravity;

    private CharSequence mCancelButtonText;
    private DialogInterface.OnClickListener mCancelButtonListener;
    private CharSequence mOkButtonText;
    private DialogInterface.OnClickListener mOkButtonListener;

    public ViewDialogBuilder setTitle(@StringRes int id) {
        return setTitle(getResources().getString(id));
    }

    public ViewDialogBuilder setTitle(CharSequence text) {
        mTitle = text;
        return this;
    }

    public ViewDialogBuilder setOkButton(CharSequence text, final DialogInterface.OnClickListener listener) {
        mOkButtonText = text;
        mOkButtonListener = listener;
        return this;
    }

    public ViewDialogBuilder setCancelButton(CharSequence text, final DialogInterface.OnClickListener listener) {
        mCancelButtonText = text;
        mCancelButtonListener = listener;
        return this;
    }

    public ViewDialogBuilder setGravity(int gravity) {
        mGravity = gravity;
        return this;
    }

    public ViewDialogBuilder fromBottom() {
        mGravity = Gravity.BOTTOM;
        return this;
    }

    public ViewDialogBuilder fromTop() {
        mGravity = Gravity.TOP;
        return this;
    }

    public ViewDialogBuilder fullScreen() {
        mFullScreen = true;
        return this;
    }

    public ViewDialogBuilder setView(View view) {
        mView = view;
        return this;
    }

    public AlertDialogFragment create(View view) {
        return new AlertDialogFragment.Builder()
                .setTitle(mTitle)
                .setView(view)
                .setOkButton(mOkButtonText, mOkButtonListener)
                .setCancelButton(mCancelButtonText, mCancelButtonListener)
                .setGravity(mGravity)
                .fullScreen(mFullScreen)
                .create();
    }


    @Override
    public AlertDialogFragment create() {
        return create(mView);
    }
}

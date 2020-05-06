package com.cjf.ui.dialog.builder;

import android.content.DialogInterface;
import android.view.Gravity;

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
public class MessageDialogBuilder implements ContextAction, DialogBuilder<AlertDialogFragment> {

    private CharSequence mTitle;
    private CharSequence mMessage;
    private boolean mFullScreen = false;
    private int mGravity;

    private CharSequence mCancelButtonText;
    private DialogInterface.OnClickListener mCancelButtonListener;
    private CharSequence mOkButtonText;
    private DialogInterface.OnClickListener mOkButtonListener;

    public MessageDialogBuilder setTitle(@StringRes int id) {
        return setTitle(getResources().getString(id));
    }

    public MessageDialogBuilder setTitle(CharSequence text) {
        mTitle = text;
        return this;
    }

    public MessageDialogBuilder setMessage(@StringRes int id) {
        return setMessage(getResources().getString(id));
    }

    public MessageDialogBuilder setMessage(CharSequence text) {
        mMessage = text;
        return this;
    }

    public MessageDialogBuilder setCancelButton(CharSequence text, final DialogInterface.OnClickListener listener) {
        mCancelButtonText = text;
        mCancelButtonListener = listener;
        return this;
    }

    public MessageDialogBuilder setOkButton(CharSequence text, final DialogInterface.OnClickListener listener) {
        mOkButtonText = text;
        mOkButtonListener = listener;
        return this;
    }

    public MessageDialogBuilder setGravity(int gravity) {
        mGravity = gravity;
        return this;
    }

    public MessageDialogBuilder fromBottom() {
        mGravity = Gravity.BOTTOM;
        return this;
    }

    public MessageDialogBuilder fromTop() {
        mGravity = Gravity.TOP;
        return this;
    }

    public MessageDialogBuilder fullScreen() {
        mFullScreen = true;
        return this;
    }

    public AlertDialogFragment create() {
        return new AlertDialogFragment.Builder()
                .setTitle(mTitle)
                .setMessage(mMessage)
                .setOkButton(mOkButtonText, mOkButtonListener)
                .setCancelButton(mCancelButtonText, mCancelButtonListener)
                .setGravity(mGravity)
                .fullScreen(mFullScreen)
                .create();
    }
}

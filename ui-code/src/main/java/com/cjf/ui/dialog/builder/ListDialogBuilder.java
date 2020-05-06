package com.cjf.ui.dialog.builder;

import android.content.DialogInterface;
import android.view.Gravity;

import com.cjf.ui.base.ContextAction;
import com.cjf.ui.dialog.AlertDialogFragment;
import com.cjf.ui.list.adapter.CommonAdapter;

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
public class ListDialogBuilder implements ContextAction, DialogBuilder<AlertDialogFragment> {

    private CharSequence mTitle;
    private boolean mFullScreen = false;
    private int mGravity;

    private CommonAdapter mAdapter;
    private CharSequence mCancelButtonText;
    private DialogInterface.OnClickListener mCancelButtonListener;
    private CharSequence mOkButtonText;
    private DialogInterface.OnClickListener mOkButtonListener;

    public ListDialogBuilder setTitle(@StringRes int id) {
        return setTitle(getResources().getString(id));
    }

    public ListDialogBuilder setTitle(CharSequence text) {
        mTitle = text;
        return this;
    }

    public <T> ListDialogBuilder setAdapter(CommonAdapter<T> adapter) {
        mAdapter = adapter;
        return this;
    }

    public ListDialogBuilder setOkButton(CharSequence text, final DialogInterface.OnClickListener listener) {
        mOkButtonText = text;
        mOkButtonListener = listener;
        return this;
    }

    public ListDialogBuilder setCancelButton(CharSequence text, final DialogInterface.OnClickListener listener) {
        mCancelButtonText = text;
        mCancelButtonListener = listener;
        return this;
    }

    public ListDialogBuilder setGravity(int gravity) {
        mGravity = gravity;
        return this;
    }

    public ListDialogBuilder fromBottom() {
        mGravity = Gravity.BOTTOM;
        return this;
    }

    public ListDialogBuilder fromTop() {
        mGravity = Gravity.TOP;
        return this;
    }

    public ListDialogBuilder fullScreen() {
        mFullScreen = true;
        return this;
    }

    public AlertDialogFragment create() {
        return new AlertDialogFragment.Builder()
                .setTitle(mTitle)
                .setAdapter(mAdapter, null)
                .setOkButton(mOkButtonText, mOkButtonListener)
                .setCancelButton(mCancelButtonText, mCancelButtonListener)
                .setGravity(mGravity)
                .fullScreen(mFullScreen)
                .create();
    }


}

package com.cjf.ui.dialog.builder;

import com.cjf.ui.base.ContextAction;
import com.cjf.ui.dialog.LoadingDialog;

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
public class LoadingDialogBuilder implements ContextAction, DialogBuilder<LoadingDialog> {

    public CharSequence mMessage;
    public boolean mCancelable = false;

    public LoadingDialogBuilder setMessage(@StringRes int id) {
        return setMessage(getResources().getString(id));
    }

    public LoadingDialogBuilder setMessage(CharSequence text) {
        mMessage = text;
        return this;
    }

    public LoadingDialogBuilder setCancelable(boolean cancelable) {
        mCancelable = cancelable;
        return this;
    }

    public LoadingDialogBuilder setCancelable() {
        return setCancelable(true);
    }

    public LoadingDialog create() {
        return new LoadingDialog(this);
    }

}

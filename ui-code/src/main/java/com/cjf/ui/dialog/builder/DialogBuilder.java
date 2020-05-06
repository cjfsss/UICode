package com.cjf.ui.dialog.builder;

import com.cjf.ui.dialog.IDialogShow;
import com.cjf.ui.dialog.LoadingDialog;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/**
 * <p>Title: DialogBuilder </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/11 18:46
 */
public interface DialogBuilder<T extends IDialogShow> {

    T create();

    default T show(FragmentActivity activity) {
        T dialogFragment = create();
        dialogFragment.show(activity);
        return dialogFragment;
    }

    default T show(Fragment fragment) {
        T dialogFragment = create();
        dialogFragment.show(fragment);
        return dialogFragment;
    }
}

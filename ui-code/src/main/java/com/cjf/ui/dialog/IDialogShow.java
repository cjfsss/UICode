package com.cjf.ui.dialog;

import android.app.Activity;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

/**
 * <p>Title: IDialogShow </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/1/18 9:00
 */
public interface IDialogShow {

    default void postOnMain(Activity activity, Runnable runnable) {
        if (activity == null || isMainThread()) {
            runnable.run();
            return;
        }
        activity.runOnUiThread(runnable);
    }

    default boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    default void show(@NonNull Fragment fragment) {
        if (fragment.getFragmentManager() != null) {
            show(fragment.getFragmentManager());
        } else {
            if (fragment.getActivity() != null) {
                show(fragment.getActivity());
            }
        }
    }

    default void show(@NonNull FragmentActivity activity) {
        show(activity.getSupportFragmentManager());
    }

    void show(@NonNull FragmentManager manager);

    void show(@NonNull FragmentManager manager, @Nullable String tag);

    default void showNow(@NonNull Fragment fragment) {
        if (fragment.getFragmentManager() != null) {
            show(fragment.getFragmentManager());
        } else {
            if (fragment.getActivity() != null) {
                show(fragment.getActivity());
            }
        }
    }

    default void showNow(@NonNull FragmentActivity activity) {
        show(activity.getSupportFragmentManager());
    }

    void showNow(@NonNull FragmentManager manager);

    void showNow(@NonNull FragmentManager manager, @Nullable String tag);
}

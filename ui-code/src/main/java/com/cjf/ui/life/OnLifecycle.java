package com.cjf.ui.life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * <p>Title: onDestroyLife </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/5/16 13:29
 */
public interface OnLifecycle extends LifecycleObserver {

    default void bind(AppCompatActivity activity) {
        activity.getLifecycle().addObserver(this);
    }

    default void bind(Fragment fragment) {
        fragment.getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    default void onCreate(LifecycleOwner owner){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner owner);


}

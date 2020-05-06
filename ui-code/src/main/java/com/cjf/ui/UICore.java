package com.cjf.ui;

import android.content.Context;

/**
 * <p>Title: UICore </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/10 22:14
 */
public class UICore {

    private Context mContext;

    private static final class UICoreHolder {

        private volatile static UICore mUICore = null;

        static UICore getUICore() {
            if (mUICore == null) {
                synchronized (UICoreHolder.class) {
                    if (mUICore == null) {
                        return mUICore = new UICore();
                    }
                }
            }
            return mUICore;
        }
    }

    private UICore() {
    }

    public static UICore get() {
        return UICoreHolder.getUICore();
    }

    public void init(Context context){
        mContext = context.getApplicationContext();
    }

    public Context getContext() {
        return mContext;
    }
}

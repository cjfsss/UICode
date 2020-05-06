package com.cjf.ui.base;

import android.view.View;

/**
 * <p>Title: OnLifeWindow </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/12 19:43
 */
public interface OnLifeWindow<VIEW extends View> {

    void onAttachedToWindow(VIEW view);

    void onDetachedFromWindow(VIEW view);
}

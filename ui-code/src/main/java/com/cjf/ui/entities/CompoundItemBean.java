package com.cjf.ui.entities;

import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;

/**
 * <p>Title: CompoundItemBean </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/11 17:04
 */
public interface CompoundItemBean {

    CharSequence getLeft();

    Drawable getIcon();

    @DrawableRes
    int getIconRes();

    boolean isChecked();

    void setCheck(boolean check);
}

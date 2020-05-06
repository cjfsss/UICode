package com.cjf.ui.entities;

import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;

/**
 * <p>Title: DefaultCompoundItemBean </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/11 18:14
 */
public class DefaultCompoundItemBean implements CompoundItemBean {

    private String title;
    private Drawable icon;

    @DrawableRes
    private int iconRes;

    private boolean isCheck;

    public DefaultCompoundItemBean(String title) {
        this.title = title;
    }

    public DefaultCompoundItemBean(String title, int iconRes) {
        this.title = title;
        this.iconRes = iconRes;
    }

    public DefaultCompoundItemBean(String title, Drawable icon) {
        this.title = title;
        this.icon = icon;
    }

    public DefaultCompoundItemBean(String title, Drawable icon, boolean isCheck) {
        this.title = title;
        this.icon = icon;
        this.isCheck = isCheck;
    }

    public DefaultCompoundItemBean(String title, int iconRes, boolean isCheck) {
        this.title = title;
        this.iconRes = iconRes;
        this.isCheck = isCheck;
    }

    public DefaultCompoundItemBean(String title, boolean isCheck) {
        this.title = title;
        this.isCheck = isCheck;
    }

    @Override
    public CharSequence getLeft() {
        return title;
    }

    @Override
    public Drawable getIcon() {
        return icon;
    }

    @Override
    public int getIconRes() {
        return iconRes;
    }

    @Override
    public boolean isChecked() {
        return isCheck;
    }

    @Override
    public void setCheck(boolean check) {
        isCheck = check;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }
}

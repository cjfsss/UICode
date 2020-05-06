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
public class DefaultCompoundSubtitleItemBean implements CompoundSubtitleItemBean {

    private String title;
    private String subtitle;
    private Drawable icon;

    @DrawableRes
    private int iconRes;

    private boolean isCheck;

    public DefaultCompoundSubtitleItemBean(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public DefaultCompoundSubtitleItemBean(String title, String subtitle, Drawable icon) {
        this.title = title;
        this.subtitle = subtitle;
        this.icon = icon;
    }

    public DefaultCompoundSubtitleItemBean(String title, String subtitle, int iconRes) {
        this.title = title;
        this.subtitle = subtitle;
        this.iconRes = iconRes;
    }

    public DefaultCompoundSubtitleItemBean(String title, String subtitle, boolean isCheck) {
        this.title = title;
        this.subtitle = subtitle;
        this.isCheck = isCheck;
    }

    public DefaultCompoundSubtitleItemBean(String title, String subtitle, Drawable icon, boolean isCheck) {
        this.title = title;
        this.subtitle = subtitle;
        this.icon = icon;
        this.isCheck = isCheck;
    }

    public DefaultCompoundSubtitleItemBean(String title, String subtitle, int iconRes, boolean isCheck) {
        this.title = title;
        this.subtitle = subtitle;
        this.iconRes = iconRes;
        this.isCheck = isCheck;
    }

    @Override
    public CharSequence getLeft() {
        return title;
    }

    @Override
    public CharSequence getSubtitle() {
        return subtitle;
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

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }
}

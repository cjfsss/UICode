package com.cjf.ui.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;

import com.google.android.material.switchmaterial.SwitchMaterial;

/**
 * <p>Title: ItemSubtitleView </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/1 21:12
 */
public class SwitchSubtitleItem extends CompoundSubtitleItem<SwitchSubtitleItem> {

    private SwitchMaterial mSwitchMaterial;


    public SwitchSubtitleItem(Context context) {
        this(context, null);
        generateViewId();
    }

    public SwitchSubtitleItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchSubtitleItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void createView(Context context, AttributeSet attrs, int defStyleAttr) {
        mSwitchMaterial = new SwitchMaterial(getContext());
        super.createView(context, attrs, defStyleAttr);
        addRightLayout();
    }

    public SwitchMaterial getSwitchMaterial() {
        return mSwitchMaterial;
    }

    @Override
    public CompoundButton getCompoundButton() {
        return mSwitchMaterial;
    }

}

package com.cjf.ui.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;

import com.google.android.material.checkbox.MaterialCheckBox;

/**
 * <p>Title: ItemSubtitleView </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/1 21:12
 */
public class CheckBoxSubtitleItem extends CompoundSubtitleItem<CheckBoxSubtitleItem> {

    private MaterialCheckBox mMaterialCheckBox;

    public CheckBoxSubtitleItem(Context context) {
        this(context, null);
    }

    public CheckBoxSubtitleItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckBoxSubtitleItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void createView(Context context, AttributeSet attrs, int defStyleAttr) {
        mMaterialCheckBox = new MaterialCheckBox(getContext());
        super.createView(context, attrs, defStyleAttr);
        addRightLayout();
    }

    public MaterialCheckBox getMaterialCheckBox() {
        return mMaterialCheckBox;
    }

    @Override
    public CompoundButton getCompoundButton() {
        return mMaterialCheckBox;
    }
}

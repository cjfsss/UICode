package com.cjf.ui.item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;

import com.cjf.ui.base.ChipAction;
import com.cjf.ui.base.CompoundAction;
import com.cjf.ui.chip.ChipGroupLayout;
import com.google.android.material.switchmaterial.SwitchMaterial;

import androidx.appcompat.widget.LinearLayoutCompat;

/**
 * <p>Title: ItemSubtitleView </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/1 21:12
 */
public class ChipBottomItem extends ItemGroupView<ChipBottomItem> implements ChipAction<ChipBottomItem>, CompoundAction<ChipBottomItem> {

    private ChipGroupLayout mChipGroup;

    private SwitchMaterial mSwitchMaterial;
    private View mSpaceView;


    private OnCheckedChangeListener mOnCheckedChangeListener;

    public ChipBottomItem(Context context) {
        this(context, null);
    }

    public ChipBottomItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChipBottomItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void createView(Context context, AttributeSet attrs, int defStyleAttr) {
        mSpaceView = new View(context);
        mChipGroup = new ChipGroupLayout(getContext());
        mSwitchMaterial = new SwitchMaterial(getContext());

        loadFromChipAttributes(context, attrs, defStyleAttr);
        loadFromCompoundAttributes(context, attrs, defStyleAttr);
        addBottomLayout(mChipGroup);
    }

    @Override
    protected void addRightView() {
        LinearLayoutCompat.LayoutParams spaceParams = new LinearLayoutCompat.LayoutParams(0, dp2px(1));
        spaceParams.gravity = Gravity.CENTER_VERTICAL;
        spaceParams.weight = 1;
        mMainLayout.addView(mSpaceView, spaceParams);

        setCommonPadding(mSwitchMaterial);
        FrameLayout.LayoutParams checkBoxParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        checkBoxParams.gravity = Gravity.CENTER_VERTICAL;
        mMainLayout.addView(mSwitchMaterial, checkBoxParams);
    }

    @Override
    public ChipGroupLayout getChipGroup() {
        return mChipGroup;
    }

    public SwitchMaterial getSwitchMaterial() {
        return mSwitchMaterial;
    }

    @Override
    public View getClickView() {
        return mMainLayout;
    }

    @Override
    public CompoundButton getCompoundButton() {
        return mSwitchMaterial;
    }

    public <Listener> void setOnCheckedChangeListener(OnCheckedChangeListener<Listener> onCheckedChangeListener) {
        mOnCheckedChangeListener = onCheckedChangeListener;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        checkAll(isChecked);
        if (mOnCheckedChangeListener != null) {
            mOnCheckedChangeListener.onCheckedChanged(this, buttonView, isChecked);
        }
    }

    @Override
    public void setChecked(boolean checked) {
        if (getCompoundButton() != null) {
            getCompoundButton().setChecked(checked);
        }
        checkAll(checked);
    }
}

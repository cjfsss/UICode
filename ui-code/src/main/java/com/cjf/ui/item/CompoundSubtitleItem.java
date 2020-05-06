package com.cjf.ui.item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;

import com.cjf.ui.base.CompoundAction;

import androidx.appcompat.widget.LinearLayoutCompat;

/**
 * <p>Title: ItemTextSubtitleView </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/1 18:44
 */
public abstract class CompoundSubtitleItem<T extends SubtitleGroupItem<T>> extends SubtitleGroupItem<T> implements CompoundAction<T> {

    private View mSpaceView;
    private OnCheckedChangeListener mOnCheckedChangeListener;

    public CompoundSubtitleItem(Context context) {
        this(context, null);
    }

    public CompoundSubtitleItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CompoundSubtitleItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void createView(Context context, AttributeSet attrs, int defStyleAttr) {
        mSpaceView = new View(context);
        super.createView(context, attrs, defStyleAttr);
        loadFromCompoundAttributes(context, attrs, defStyleAttr);
    }


    @Override
    protected void addRightView() {
        LinearLayoutCompat.LayoutParams spaceParams = new LinearLayoutCompat.LayoutParams(0, dp2px(getContext(), 1));
        spaceParams.gravity = Gravity.CENTER_VERTICAL;
        spaceParams.weight = 1;
        mMainLayout.addView(mSpaceView, spaceParams);

        setCommonPadding(getCompoundButton());
        FrameLayout.LayoutParams checkBoxParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        checkBoxParams.gravity = Gravity.CENTER_VERTICAL;
        mMainLayout.addView(getCompoundButton(), checkBoxParams);
    }

    @Override
    public View getClickView() {
        return this;
    }

    @Override
    public <Listener> void setOnCheckedChangeListener(OnCheckedChangeListener<Listener> onCheckedChangeListener) {
        mOnCheckedChangeListener = onCheckedChangeListener;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (mOnCheckedChangeListener != null) {
            mOnCheckedChangeListener.onCheckedChanged(this, buttonView, isChecked);
        }
    }

}

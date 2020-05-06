package com.cjf.ui.item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;


import com.cjf.ui.R;
import com.cjf.ui.base.RightTextAction;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
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
public class TitleImageItem extends ItemGroupView<TitleImageItem> implements RightTextAction<TitleImageItem> {

    private View mSpaceView;
    private AppCompatTextView mRightView;

    public TitleImageItem(Context context) {
        this(context, null);
    }

    public TitleImageItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleImageItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void createView(Context context, AttributeSet attrs, int defStyleAttr) {
        mSpaceView = new View(context);
        mRightView = new AppCompatTextView(getContext());
        mRightView.setGravity(Gravity.END | Gravity.CENTER);
        loadFromRightTextAttributes(context, attrs, defStyleAttr);

        addRightLayout();
    }

    @Override
    protected void addRightView() {
        LinearLayoutCompat.LayoutParams spaceParams = new LinearLayoutCompat.LayoutParams(0, dp2px(getContext(), 1));
        spaceParams.gravity = Gravity.CENTER_VERTICAL;
        spaceParams.weight = 1;
        mMainLayout.addView(mSpaceView, spaceParams);

        setCommonPadding(mRightView);
        mRightView.setLineSpacing(dp2px(getContext(), 6), mTitleView.getLineSpacingMultiplier());
        mRightView.setCompoundDrawablePadding(dp2px(getContext(), 8));

        LinearLayoutCompat.LayoutParams rightParams = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        rightParams.gravity = Gravity.CENTER_VERTICAL;
        mMainLayout.addView(mRightView, rightParams);
    }

    @Override
    public void setOnClickListener(@Nullable View.OnClickListener l) {
        if (l != null) {
            setBackgroundResource(R.drawable.ripple_water);
        } else {
            setBackground(null);
        }
        if (mRightView != null) {
            mRightView.setOnClickListener(null);
            mRightView.setBackgroundDrawable(null);
        }
        super.setOnClickListener(l);
    }

    @Override
    public void setOnRightClickListener(@Nullable View.OnClickListener l) {
        setOnClickListener(null);
        getRightView().setBackgroundResource(R.drawable.ripple_water);
        getRightView().setOnClickListener(l);
    }

    @Override
    public AppCompatTextView getRightView() {
        return mRightView;
    }
}

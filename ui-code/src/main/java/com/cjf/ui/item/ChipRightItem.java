package com.cjf.ui.item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

import com.cjf.ui.base.ChipAction;
import com.cjf.ui.chip.ChipGroupLayout;

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
public class ChipRightItem extends ItemGroupView<ChipRightItem> implements ChipAction<ChipRightItem> {

    private ChipGroupLayout mChipGroup;
    private View mSpaceView;

    public ChipRightItem(Context context) {
        this(context, null);
    }

    public ChipRightItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChipRightItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void createView(Context context, AttributeSet attrs, int defStyleAttr) {
        mSpaceView = new View(context);
        mChipGroup = new ChipGroupLayout(getContext());
        setCommonPaddingLeftRight(mChipGroup);

        loadFromChipAttributes(context, attrs, defStyleAttr);
        addRightLayout();
    }

    @Override
    protected void addRightView() {
        LinearLayoutCompat.LayoutParams spaceParams = new LinearLayoutCompat.LayoutParams(0, dp2px(getContext(), 1));
        spaceParams.gravity = Gravity.CENTER_VERTICAL;
        spaceParams.weight = 1;
        mMainLayout.addView(mSpaceView, spaceParams);

        LinearLayoutCompat.LayoutParams chipGroupParams = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        chipGroupParams.gravity = Gravity.CENTER_VERTICAL;
        mMainLayout.addView(mChipGroup, chipGroupParams);
    }

    @Override
    public ChipGroupLayout getChipGroup() {
        return mChipGroup;
    }
}

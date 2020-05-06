package com.cjf.ui.item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;


import com.cjf.ui.R;

import androidx.annotation.Nullable;

/**
 * <p>Title: ItemSubtitleView </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/1 21:12
 */
public class TitleItem extends ItemGroupView<TitleItem> {

    public TitleItem(Context context) {
        this(context, null);
    }

    public TitleItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void createView(Context context, AttributeSet attrs, int defStyleAttr) {
        addRightLayout();
    }

    @Override
    protected void addRightView() {

    }

    @Override
    public void setOnClickListener(@Nullable View.OnClickListener l) {
        super.setOnClickListener(l);
        if (l != null) {
            setBackgroundResource(R.drawable.ripple_water);
        } else {
            setBackground(null);
        }
    }

}

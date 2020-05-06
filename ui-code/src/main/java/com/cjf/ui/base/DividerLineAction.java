package com.cjf.ui.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;


import com.cjf.ui.R;

import androidx.annotation.ColorInt;

/**
 * <p>Title: DividerLineAction </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/3 13:44
 */
@SuppressWarnings("unchecked")
public interface DividerLineAction<SELF> extends ContextAction {

    View getLineView();

    /**
     * 设置分割线是否显示
     */
    default SELF setLineVisible(boolean visible) {
        getLineView().setVisibility(visible ? View.VISIBLE : View.GONE);
        return (SELF) this;
    }

    /**
     * 设置分割线的颜色
     */
    default SELF setLineColor(@ColorInt int color) {
        return setLineDrawable(new ColorDrawable(color));
    }

    default SELF setLineDrawable(Drawable drawable) {
        getLineView().setBackground(drawable);
        return (SELF) this;
    }

    /**
     * 设置分割线的大小
     */
    default SELF setLineSize(int size) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLineView().getLayoutParams();
        layoutParams.height = size;
        getLineView().setLayoutParams(layoutParams);
        return (SELF) this;
    }

    /**
     * 设置分割线边界
     */
    default SELF setLineMargin(int margin) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) getLineView().getLayoutParams();
        params.leftMargin = margin;
        params.rightMargin = margin;
        getLineView().setLayoutParams(params);
        return (SELF) this;
    }

    default void loadFromDividerLineAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DividerLineAction, defStyleAttr, 0);
        try {
            // 分割线设置
            Drawable lineColor;
            if (array.hasValue(R.styleable.DividerLineAction_item_lineColor)) {
                lineColor = array.getDrawable(R.styleable.DividerLineAction_item_lineColor);
            } else {
                lineColor = new ColorDrawable(0xFFECECEC);
            }
            boolean lineVisible = array.getBoolean(R.styleable.DividerLineAction_item_lineVisible, true);
            int lineSize = array.getDimensionPixelSize(R.styleable.DividerLineAction_item_lineSize, 1);
            int lineMargin = array.getDimensionPixelSize(R.styleable.DividerLineAction_item_lineMargin, 0);
            // 分割线设置
            setLineDrawable(lineColor);
            setLineVisible(lineVisible);
            setLineSize(lineSize);
            setLineMargin(lineMargin);
        } finally {
            array.recycle();
        }
    }

}

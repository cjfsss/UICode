package com.cjf.ui.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;


import com.cjf.ui.R;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

/**
 * <p>Title: CenterTitleAction </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/3 13:08
 */
@SuppressWarnings("unchecked")
public interface CenterTitleAction<SELF> extends ContextAction {

    AppCompatTextView getCenterView();

    /**
     * 设置左边的标题
     */
    default SELF setCenterText(@StringRes int id) {
        return (SELF) setCenterText(getResources().getString(id));
    }

    default SELF setCenterText(CharSequence text) {
        getCenterView().setText(text);
        return (SELF) this;
    }

    default CharSequence getCenterText() {
        return getCenterView().getText();
    }

    /**
     * 设置左标题颜色
     */
    default SELF setCenterColorRes(@ColorRes int color) {
        return setCenterColor(ContextCompat.getColor(getContext(), color));
    }

    default SELF setCenterColor(@ColorInt int color) {
        getCenterView().setTextColor(color);
        return (SELF) this;
    }

    /**
     * 设置左标题的文本大小
     */
    default SELF setCenterSize(int unit, float size) {
        getCenterView().setTextSize(unit, size);
        return (SELF) this;
    }

    default SELF setCenterSize(float size) {
        getCenterView().setTextSize(size);
        return (SELF) this;
    }

    /**
     * 设置左边的图标
     */
    default SELF setCenterIcon(@DrawableRes int id) {
        return setCenterIcon(ContextCompat.getDrawable(getContext(), id));
    }

    default SELF setCenterIcon(Drawable drawable) {
        getCenterView().setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        return (SELF) this;
    }

    default Drawable getCenterIcon() {
        return getCenterView().getCompoundDrawables()[0];
    }

    default void setCenterIconColor(int drawableColor) {
        Drawable rightIcon = getCenterIcon();
        if (rightIcon != null) {
            setCenterIcon(tintDrawable(rightIcon, drawableColor));
        }
    }

    default void setOnCenterClickListener(View.OnClickListener listener) {
        if (listener == null) {
            getCenterView().setBackgroundDrawable(null);
        } else {
            getCenterView().setBackgroundResource(R.drawable.ripple_water);
        }
        getCenterView().setOnClickListener(listener);
    }

    default void loadFromCenterTitleAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CenterTitleAction, defStyleAttr, 0);
        try {
            String CenterText = null;
            // 文本设置
            if (array.hasValue(R.styleable.CenterTitleAction_item_centerText)) {
                CenterText = array.getString(R.styleable.CenterTitleAction_item_centerText);
            }
            // 文字颜色设置
            int CenterColor = array.getColor(R.styleable.CenterTitleAction_item_centerColor, Color.BLACK);
            // 文字大小设置
            int CenterSize = array.getDimensionPixelSize(R.styleable.CenterTitleAction_item_centerSize, 16);
            // 添加子选择
            if (!TextUtils.isEmpty(CenterText)) {
                setCenterText(CenterText);
            }
            setCenterColor(CenterColor);
            setCenterSize(TypedValue.COMPLEX_UNIT_SP, CenterSize);
        } finally {
            array.recycle();
        }
    }
}

package com.cjf.ui.base;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.cjf.ui.R;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

/**
 * <p>Title: RightTextAction </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/3 13:42
 */
@SuppressWarnings("unchecked")
public interface RightTextAction<SELF> extends ContextAction {

    AppCompatTextView getRightView();

    /**
     * 设置左边的标题
     */
    default SELF setRightText(@StringRes int id) {
        return (SELF) setRightText(getResources().getString(id));
    }

    default SELF setRightText(CharSequence text) {
        getRightView().setText(text);
        return (SELF) this;
    }

    default CharSequence getRightText() {
        return getRightView().getText();
    }

    /**
     * 设置左边的提示
     */
    default SELF setRightHint(@StringRes int id) {
        return setRightHint(getResources().getString(id));
    }

    default SELF setRightHint(CharSequence hint) {
        getRightView().setHint(hint);
        return (SELF) this;
    }

    /**
     * 设置左标题颜色
     */
    default SELF setRightColorRes(@ColorRes int color) {
        return setRightColor(ContextCompat.getColor(getContext(), color));
    }

    default SELF setRightColor(@ColorInt int color) {
        getRightView().setTextColor(color);
        return (SELF) this;
    }

    /**
     * 设置左标题的文本大小
     */
    default SELF setRightSize(int unit, float size) {
        getRightView().setTextSize(unit, size);
        return (SELF) this;
    }

    default SELF setRightSize(float size) {
        getRightView().setTextSize(size);
        return (SELF) this;
    }

    /**
     * 设置左边的图标
     */
    default SELF setRightIcon(@DrawableRes int id) {
        return setRightIcon(ContextCompat.getDrawable(getContext(), id));
    }

    default SELF setRightIcon(Drawable drawable) {
        getRightView().setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        return (SELF) this;
    }

    default Drawable getRightIcon() {
        return getRightView().getCompoundDrawables()[2];
    }

    default void setRightIconColor(int drawableColor) {
        Drawable rightIcon = getRightIcon();
        if (rightIcon != null) {
            setRightIcon(tintDrawable(rightIcon, drawableColor));
        }
    }

    default void setOnRightClickListener(@Nullable View.OnClickListener l) {
        if (l == null) {
            getRightView().setBackgroundDrawable(null);
        } else {
            getRightView().setBackgroundResource(R.drawable.ripple_water);
        }
        getRightView().setOnClickListener(l);
    }

    default void loadFromRightTextAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RightTextAction, defStyleAttr, 0);
        try {
            // 文本设置
            if (array.hasValue(R.styleable.RightTextAction_item_rightText)) {
                setRightText(array.getString(R.styleable.RightTextAction_item_rightText));
            }
            // 提示设置
            if (array.hasValue(R.styleable.RightTextAction_item_rightHint)) {
                setRightHint(array.getString(R.styleable.RightTextAction_item_rightHint));
            }
            // 图标设置
            Drawable rightIcon = null;
            if (array.hasValue(R.styleable.RightTextAction_item_rightIcon)) {
                rightIcon = array.getDrawable(R.styleable.RightTextAction_item_rightIcon);
            }
            // 图标设置颜色
            if (array.hasValue(R.styleable.RightTextAction_item_rightIconColor)) {
                ColorStateList rightDrawableTintList = array.getColorStateList(R.styleable.RightTextAction_item_rightIconColor);
                if (rightIcon != null) {
                    rightIcon = tintDrawable(rightIcon, rightDrawableTintList);
                }
            }
            // 图标设置
            setRightIcon(rightIcon);
            // 文字颜色设置
            setRightColor(array.getColor(R.styleable.RightTextAction_item_rightColor,getDefaultTextColor()));
            // 文字大小设置
            setRightSize(TypedValue.COMPLEX_UNIT_SP, array.getDimensionPixelSize(R.styleable.RightTextAction_item_rightSize, 14));
        } finally {
            array.recycle();
        }
    }
}

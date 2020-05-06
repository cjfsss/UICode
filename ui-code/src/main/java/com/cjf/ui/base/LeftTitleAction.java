package com.cjf.ui.base;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
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
 * <p>Title: LeftTitleAction </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/3 13:08
 */
@SuppressWarnings("unchecked")
public interface LeftTitleAction<SELF> extends ContextAction {

    AppCompatTextView getLeftView();

    /**
     * 设置左边的标题
     */
    default SELF setLeftText(@StringRes int id) {
        return (SELF) setLeftText(getResources().getString(id));
    }

    default SELF setLeftText(CharSequence text) {
        getLeftView().setText(text);
        return (SELF) this;
    }

    default CharSequence getLeftText() {
        return getLeftView().getText();
    }

    /**
     * 设置左边的提示
     */
    default SELF setLeftHint(@StringRes int id) {
        return setLeftHint(getResources().getString(id));
    }

    default SELF setLeftHint(CharSequence hint) {
        getLeftView().setHint(hint);
        return (SELF) this;
    }

    /**
     * 设置左标题颜色
     */
    default SELF setLeftColorRes(@ColorRes int color) {
        return setLeftColor(ContextCompat.getColor(getContext(), color));
    }

    default SELF setLeftColor(@ColorInt int color) {
        getLeftView().setTextColor(color);
        return (SELF) this;
    }

    /**
     * 设置左标题的文本大小
     */
    default SELF setLeftSize(int unit, float size) {
        getLeftView().setTextSize(unit, size);
        return (SELF) this;
    }

    default SELF setLeftSize(float size) {
        getLeftView().setTextSize(size);
        return (SELF) this;
    }

    /**
     * 设置左边的图标
     */
    default SELF setLeftIcon(@DrawableRes int id) {
        if (id == 0) {
            setLeftIcon(null);
            return (SELF) this;
        }
        return setLeftIcon(ContextCompat.getDrawable(getContext(), id));
    }

    SELF setLeftIcon(Drawable drawable);

    Drawable getLeftIcon();

    default void setLeftIconColor(int drawableColor) {
        Drawable rightIcon = getLeftIcon();
        if (rightIcon != null) {
            setLeftIcon(tintDrawable(rightIcon, drawableColor));
        }
    }

    default void setOnLeftClickListener(View.OnClickListener listener) {
        if (listener == null) {
            getLeftView().setBackgroundDrawable(null);
        } else {
            getLeftView().setBackgroundResource(R.drawable.ripple_water);
        }
        getLeftView().setOnClickListener(listener);
    }

    default void loadFromLeftTitleAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LeftTitleAction, defStyleAttr, 0);
        try {
            String leftText = null;
            // 文本设置
            if (array.hasValue(R.styleable.LeftTitleAction_item_leftText)) {
                leftText = array.getString(R.styleable.LeftTitleAction_item_leftText);
            }
            String leftHint = null;
            // 提示设置
            if (array.hasValue(R.styleable.LeftTitleAction_item_leftHint)) {
                leftHint = array.getString(R.styleable.LeftTitleAction_item_leftHint);
            }
            // 文字颜色设置
            int leftColor = array.getColor(R.styleable.LeftTitleAction_item_leftColor, getDefaultTextColor());
            // 文字大小设置
            int leftSize = array.getDimensionPixelSize(R.styleable.LeftTitleAction_item_leftSize, 16);
            // 图标设置
            Drawable leftIcon = null;
            if (array.hasValue(R.styleable.LeftTitleAction_item_leftIcon)) {
                leftIcon = array.getDrawable(R.styleable.LeftTitleAction_item_leftIcon);
            }
            // 图标设置颜色
            if (array.hasValue(R.styleable.LeftTitleAction_item_leftIconColor)) {
                ColorStateList rightDrawableTintList = array.getColorStateList(R.styleable.LeftTitleAction_item_leftIconColor);
                if (leftIcon != null) {
                    leftIcon = tintDrawable(leftIcon, rightDrawableTintList);
                }
            }
            if (leftIcon != null) {
                setLeftIcon(leftIcon);
            }
            // 添加子选择
            if (!TextUtils.isEmpty(leftText)) {
                setLeftText(leftText);
            }
            if (!TextUtils.isEmpty(leftHint)) {
                setLeftHint(leftHint);
            }
            setLeftColor(leftColor);
            setLeftSize(TypedValue.COMPLEX_UNIT_SP, leftSize);
        } finally {
            array.recycle();
        }
    }
}

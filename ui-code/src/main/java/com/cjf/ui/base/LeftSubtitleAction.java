package com.cjf.ui.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.cjf.ui.R;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
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
public interface LeftSubtitleAction<SELF> extends ContextAction {

    AppCompatTextView getSubtitleView();

    /**
     * 设置左边的标题
     */
    default SELF setSubtitleText(@StringRes int id) {
        return (SELF) setSubtitleText(getResources().getString(id));
    }

    default SELF setSubtitleText(CharSequence text) {
        getSubtitleView().setText(text);
        return (SELF) this;
    }

    default CharSequence getSubtitleText() {
        return getSubtitleView().getText();
    }

    /**
     * 设置左边的提示
     */
    default SELF setSubtitleHint(@StringRes int id) {
        return setSubtitleHint(getResources().getString(id));
    }

    default SELF setSubtitleHint(CharSequence hint) {
        getSubtitleView().setHint(hint);
        return (SELF) this;
    }

    /**
     * 设置左标题颜色
     */
    default SELF setSubtitleColorRes(@ColorRes int color) {
        return setSubtitleColor(ContextCompat.getColor(getContext(), color));
    }

    default SELF setSubtitleColor(@ColorInt int color) {
        getSubtitleView().setTextColor(color);
        return (SELF) this;
    }

    /**
     * 设置左标题的文本大小
     */
    default SELF setSubtitleSize(int unit, float size) {
        getSubtitleView().setTextSize(unit, size);
        return (SELF) this;
    }

    default SELF setSubtitleSize(float size) {
        getSubtitleView().setTextSize(size);
        return (SELF) this;
    }

    default void loadFromSubtitleAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LeftSubtitleAction, defStyleAttr, 0);
        try {
            String leftSubTitleText = null;
            // 文本设置
            if (array.hasValue(R.styleable.LeftSubtitleAction_item_leftSubtitleText)) {
                leftSubTitleText = array.getString(R.styleable.LeftSubtitleAction_item_leftSubtitleText);
            }
            // 文字颜色设置
            int leftSubTitleColor = array.getColor(R.styleable.LeftSubtitleAction_item_leftSubtitleColor, getDefaultTextColor());
            // 文字大小设置
            int leftSubTitleSize = array.getDimensionPixelSize(R.styleable.LeftSubtitleAction_item_leftSubtitleSize, 12);
            // 添加子标题
            if (!TextUtils.isEmpty(leftSubTitleText)) {
                setSubtitleText(leftSubTitleText);
            }
            setSubtitleColor(leftSubTitleColor);
            setSubtitleSize(TypedValue.COMPLEX_UNIT_SP, leftSubTitleSize);
        } finally {
            array.recycle();
        }
    }
}

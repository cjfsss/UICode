package com.cjf.ui.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.inputmethod.EditorInfo;

import com.cjf.ui.R;
import com.cjf.ui.text.XEditText;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

/**
 * <p>Title: InputAction </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/3 17:22
 */
@SuppressWarnings("unchecked")
public interface InputAction<SELF> extends ContextAction {

    AppCompatEditText getInputContext();

    /**
     * 设置左边的标题
     */
    default SELF setContent(@StringRes int id) {
        return setContent(getResources().getString(id));
    }

    default SELF setContent(CharSequence text) {
        getInputContext().setText(text);
        return (SELF) this;
    }

    default CharSequence getContent() {
        if (getInputContext() instanceof XEditText) {
            return ((XEditText) getInputContext()).getTrimmedString();
        }
        return getInputContext().getText();
    }

    /**
     * 设置左边的提示
     */
    default SELF setContentHint(@StringRes int id) {
        return setContentHint(getResources().getString(id));
    }

    default SELF setContentHint(CharSequence hint) {
        getInputContext().setHint(hint);
        return (SELF) this;
    }

    /**
     * 设置左标题颜色
     */
    default SELF setContentColor(@ColorInt int color) {
        getInputContext().setTextColor(color);
        return (SELF) this;
    }

    /**
     * 设置左标题颜色
     */
    default SELF setContentColorRes(@ColorRes int color) {
        return setContentColor(ContextCompat.getColor(getContext(), color));
    }

    /**
     * 设置左标题的文本大小
     */
    default SELF setContentSize(int unit, float size) {
        getInputContext().setTextSize(unit, size);
        return (SELF) this;
    }

    /**
     * 设置左标题的文本大小
     */
    default SELF setContentSize(float size) {
        getInputContext().setTextSize(size);
        return (SELF) this;
    }

    /**
     * 设置输入框的背景
     */
    default SELF setContentBackground(@DrawableRes int background) {
        getInputContext().setBackgroundResource(background);
        return (SELF) this;
    }

    /**
     * 设置输入框的背景
     */
    default SELF setContentBackground(Drawable background) {
        getInputContext().setBackground(background);
        return (SELF) this;
    }

    /**
     * 设置输入框的最小行数
     */
    default SELF setContentMinLines(int lines) {
        getInputContext().setMinLines(lines);
        return (SELF) this;
    }

    /**
     * 设置输入框是否可以点击
     */
    default SELF setContentClickable(boolean clickable) {
        getInputContext().setClickable(clickable);
        return (SELF) this;
    }

    /**
     * 设置输入框是否可以点击
     */
    default SELF setContentWidth(int min, int max) {
        getInputContext().setMinWidth(min);
        getInputContext().setMaxWidth(max);
        return (SELF) this;

    }

    /**
     * 设置输入框是否是一行
     */
    default SELF setContentSingleLine() {
        getInputContext().setSingleLine(true);
        return (SELF) this;
    }

    /**
     * 设置输入框是否是一行
     */
    default SELF setContentSingleLine(boolean singleLine) {
        getInputContext().setSingleLine(singleLine);
        return (SELF) this;
    }

    default void loadFromInputAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.InputAction, defStyleAttr, 0);
        try {
            int inputMinLines = array.getInt(R.styleable.InputAction_item_inputMinLines, 0);
            int inputType = array.getInt(R.styleable.InputAction_android_inputType, EditorInfo.TYPE_CLASS_TEXT);
            int inputMinWidth = array.getDimensionPixelSize(R.styleable.InputAction_item_inputMinWidth, 100);
            int inputMaxWidth = array.getDimensionPixelSize(R.styleable.InputAction_item_inputMaxWidth, 0);
            boolean inputSingleLine = array.getBoolean(R.styleable.InputAction_item_inputSingleLine, false);
            String inputText = null;
            if (array.hasValue(R.styleable.InputAction_item_inputText)) {
                inputText = array.getString(R.styleable.InputAction_item_inputText);
            }
            String inputHint = null;
            if (array.hasValue(R.styleable.InputAction_item_inputHint)) {
                inputHint = array.getString(R.styleable.InputAction_item_inputHint);
            }
            // 文字颜色设置
            int inputColor = array.getColor(R.styleable.InputAction_item_inputColor, getDefaultTextColor());
            // 文字大小设置
            int inputSize = array.getDimensionPixelSize(R.styleable.InputAction_item_inputSize, 16);
            boolean inputClickable = array.getBoolean(R.styleable.InputAction_item_inputClickable, true);
            // 图标设置
            Drawable inputBackground = null;
            if (array.hasValue(R.styleable.InputAction_item_inputBackground)) {
                inputBackground = array.getDrawable(R.styleable.InputAction_item_inputBackground);
            } else {
                inputBackground = ContextCompat.getDrawable(getContext(), R.drawable.et_bg);
            }
            // 输入框
            setContentBackground(inputBackground);
            if (!TextUtils.isEmpty(inputHint)) {
                setContentHint(inputHint);
            }
            if (!TextUtils.isEmpty(inputText)) {
                setContent(inputText);
            }
            if (inputMinWidth != 0) {
                getInputContext().setMinWidth(dp2px(inputMinWidth));
            }
            if (inputMaxWidth != 0) {
                getInputContext().setMaxWidth(dp2px(inputMaxWidth));
            }
            if (inputMinLines != 0) {
                getInputContext().setMinLines(inputMinLines);
            }
            getInputContext().setInputType(inputType);
            getInputContext().setTextColor(inputColor);
            getInputContext().setClickable(inputClickable);
            getInputContext().setSingleLine(inputSingleLine);
            getInputContext().setTextSize(TypedValue.COMPLEX_UNIT_SP, inputSize);
        } finally {
            array.recycle();
        }
    }
}

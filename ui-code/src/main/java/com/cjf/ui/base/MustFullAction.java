package com.cjf.ui.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.cjf.ui.R;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * <p>Title: MustFullAction </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/3 14:03
 */
@SuppressWarnings("unchecked")
public interface MustFullAction<SELF> extends ContextAction {

    AppCompatTextView getMustFullView();

    default SELF setMustFullText(CharSequence text) {
        getMustFullView().setText(text);
        return (SELF) this;
    }

    default SELF setMustFullSize(float size) {
        getMustFullView().setTextSize(size);
        return (SELF) this;
    }

    default SELF setMustFullSize(int unit, float size) {
        getMustFullView().setTextSize(unit, size);
        return (SELF) this;
    }

    default SELF setMustFullColor(int color) {
        getMustFullView().setTextColor(color);
        return (SELF) this;
    }

    /**
     * 设置当前Item为必填项
     *
     * @return
     */
    default SELF setMustFull() {
        return setMustFull(true);
    }

    /**
     * 设置是否为必填
     *
     * @param visible true 必填项
     * @return
     */
    default SELF setMustFull(boolean visible) {
        getMustFullView().setVisibility(visible ? View.VISIBLE : View.GONE);
        return (SELF) this;
    }

    /**
     * 是否为必填
     *
     * @return true 必填
     */
    default boolean isMustFull() {
        return getMustFullView().getVisibility() == View.VISIBLE;
    }

    default void loadFromMustFullAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MustFullAction, defStyleAttr, 0);
        try {
            String mustText = "*";
            if (array.hasValue(R.styleable.MustFullAction_item_mustText)) {
                mustText = array.getString(R.styleable.MustFullAction_item_mustText);
            }
            int mustColor = array.getColor(R.styleable.MustFullAction_item_mustColor, getDefaultMustFullColor());
            int mustSize = array.getDimensionPixelSize(R.styleable.MustFullAction_item_mustSize, 16);
            boolean mustVisibility = array.getBoolean(R.styleable.MustFullAction_item_mustVisibility, true);
            // 必填
            setMustFullText(mustText);
            // 必填颜色设置
            setMustFullColor(mustColor);
            // 必填大小设置
            setMustFullSize(TypedValue.COMPLEX_UNIT_SP, mustSize);
            setMustFull(mustVisibility);
        } finally {
            array.recycle();
        }
    }

    default int getDefaultMustFullColor() {
        return Color.RED;
    }
}

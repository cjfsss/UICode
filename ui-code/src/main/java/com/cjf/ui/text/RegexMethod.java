package com.cjf.ui.text;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.cjf.ui.R;
import com.cjf.ui.base.ContextAction;

import androidx.annotation.NonNull;

/**
 * <p>Title: RegexMethod </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/9 23:06
 */
public interface RegexMethod extends ContextAction, RegexContant {

    @NonNull
    RegexDelegate getRegexDelegate();

    /**
     * 是否有这个输入标记
     */
    default boolean hasInputType(int type) {
        return getRegexDelegate().hasInputType(type);
    }

    /**
     * 添加一个输入标记
     */
    default void addInputType(int type) {
        getRegexDelegate().addInputType(type);
    }

    /**
     * 移除一个输入标记
     */
    default void removeInputType(int type) {
        getRegexDelegate().removeInputType(type);
    }

    /**
     * 设置输入正则
     */
    default void setInputRegex(String regex) {
        getRegexDelegate().setInputRegex(regex);
    }

    /**
     * 获取输入正则
     */
    default String getInputRegex() {
        return getRegexDelegate().getInputRegex();
    }

    default void loadFromRegexAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RegexMethod, defStyleAttr, 0);
        try {
            if (array.hasValue(R.styleable.RegexMethod_inputRegex)) {
                setInputRegex(array.getString(R.styleable.RegexMethod_inputRegex));
            } else {
                if (array.hasValue(R.styleable.RegexMethod_regexType)) {
                    int regexType = array.getInt(R.styleable.RegexMethod_regexType, 0);
                    switch (regexType) {
                        case 0x01:
                            setInputRegex(REGEX_MOBILE);
                            break;
                        case 0x02:
                            setInputRegex(REGEX_CHINESE);
                            break;
                        case 0x03:
                            setInputRegex(REGEX_ENGLISH);
                            break;
                        case 0x04:
                            setInputRegex(REGEX_COUNT);
                            break;
                        case 0x05:
                            setInputRegex(REGEX_NAME);
                            break;
                        case 0x06:
                            setInputRegex(REGEX_NONNULL);
                            break;
                        default:
                            break;
                    }
                }
            }
        } finally {
            array.recycle();
        }
    }
}

package com.cjf.ui.text;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

/**
 * <p>Title: RegexTextInputLayout </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/9 23:17
 */
public class TextInputLayoutGo extends TextInputLayout implements RegexMethod {

    private RegexMethod mRegexMethod;

    public TextInputLayoutGo(@NonNull Context context) {
        this(context, null);
    }

    public TextInputLayoutGo(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, com.google.android.material.R.attr.textInputStyle);
    }

    public TextInputLayoutGo(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    public void addView(
            @NonNull View child, int index, @NonNull final ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        if (child instanceof RegexMethod) {
            mRegexMethod = (RegexMethod) child;
        }
    }

    @Nullable
    @Override
    public AppCompatEditText getEditText() {
        EditText editText = super.getEditText();
        if (editText != null) {
            return (AppCompatEditText) super.getEditText();
        }
        return (AppCompatEditText) mRegexMethod;
    }

    @Override
    @NonNull
    public RegexDelegate getRegexDelegate() {
        return mRegexMethod.getRegexDelegate();
    }
}

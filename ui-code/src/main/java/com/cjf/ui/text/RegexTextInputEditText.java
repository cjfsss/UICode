package com.cjf.ui.text;

import android.content.Context;
import android.util.AttributeSet;

import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2019/06/29
 * desc   : 正则输入限制编辑框
 */
class RegexTextInputEditText extends TextInputEditText implements RegexMethod {

    private RegexDelegate mRegexDelegate;

    public RegexTextInputEditText(Context context) {
        this(context, null);
    }

    public RegexTextInputEditText(Context context, AttributeSet attrs) {
        this(context, attrs, com.google.android.material.R.attr.editTextStyle);
    }

    public RegexTextInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRegexDelegate = new RegexDelegate(this);
        loadFromRegexAttributes(context, attrs, defStyleAttr);
    }

    @Override
    @NonNull
    public RegexDelegate getRegexDelegate() {
        return mRegexDelegate;
    }


}
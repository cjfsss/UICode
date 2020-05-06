package com.cjf.ui.text;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2019/06/29
 * desc   : 正则输入限制编辑框
 */
public class RegexEditText extends AppCompatEditText implements RegexMethod {

    private RegexDelegate mRegexDelegate;

    public RegexEditText(Context context) {
        this(context, null);
    }

    public RegexEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public RegexEditText(Context context, AttributeSet attrs, int defStyleAttr) {
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
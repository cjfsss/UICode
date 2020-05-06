package com.cjf.ui.text;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2019/06/29
 * desc   : 正则输入限制编辑框
 */
class RegexMultiAutoCompleteTextView extends AppCompatAutoCompleteTextView implements RegexMethod {

    private RegexDelegate mRegexDelegate;

    public RegexMultiAutoCompleteTextView(Context context) {
        this(context, null);
    }

    public RegexMultiAutoCompleteTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public RegexMultiAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
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
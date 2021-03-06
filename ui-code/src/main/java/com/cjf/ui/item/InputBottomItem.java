package com.cjf.ui.item;

import android.content.Context;
import android.util.AttributeSet;


import com.cjf.ui.base.InputAction;
import com.cjf.ui.text.XEditText;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * <p>Title: ItemSubtitleView </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/1 21:12
 */
public class InputBottomItem extends ItemGroupView<InputBottomItem> implements InputAction<InputBottomItem> {

    private XEditText mInputContent;

    public InputBottomItem(Context context) {
        this(context, null);
    }

    public InputBottomItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InputBottomItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void createView(Context context, AttributeSet attrs, int defStyleAttr) {
        mInputContent = new XEditText(getContext());

        loadFromInputAttributes(context, attrs, defStyleAttr);
        addBottomLayout(mInputContent);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    protected void addRightView() {

    }

    @Override
    public AppCompatEditText getInputContext() {
        return mInputContent;
    }
}

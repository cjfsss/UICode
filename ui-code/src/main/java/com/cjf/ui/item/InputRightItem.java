package com.cjf.ui.item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;


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
public class InputRightItem extends ItemGroupView<InputRightItem> implements InputAction<InputRightItem> {

    private View mSpaceView;
    private XEditText mInputContent;

    public InputRightItem(Context context) {
        this(context, null);
    }

    public InputRightItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InputRightItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void createView(Context context, AttributeSet attrs, int defStyleAttr) {
        mSpaceView = new View(context);
        mInputContent = new XEditText(getContext());

        loadFromInputAttributes(context, attrs, defStyleAttr);
        addRightLayout();
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    protected void addRightView() {
        LayoutParams spaceParams = new LayoutParams(0, dp2px(getContext(), 1));
        spaceParams.gravity = Gravity.CENTER_VERTICAL;
        spaceParams.weight = 1;
        mMainLayout.addView(mSpaceView, spaceParams);

        LayoutParams inputParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        inputParams.gravity = Gravity.CENTER_VERTICAL;
        inputParams.leftMargin = dp2px(getContext(), 16);
        inputParams.rightMargin = dp2px(getContext(), 16);
        mMainLayout.addView(mInputContent, inputParams);
    }

    @Override
    public AppCompatEditText getInputContext() {
        return mInputContent;
    }
}

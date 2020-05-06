package com.cjf.ui.item;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;

import com.cjf.ui.base.DividerLineAction;
import com.cjf.ui.base.LeftTitleAction;
import com.cjf.ui.base.MustFullAction;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

/**
 * <p>Title: ItemGroupView </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/1 18:24
 */
@SuppressWarnings("unchecked")
public abstract class ItemGroupView<T extends ItemGroupView<T>> extends LinearLayoutCompat implements
        LeftTitleAction<T>, MustFullAction<T>, DividerLineAction<T> {

    protected final LinearLayoutCompat mMainLayout;
    protected final AppCompatTextView mTitleView;
    protected final AppCompatTextView mMustFullView;
    protected final View mLineView;

    private int mPosition;

    public ItemGroupView(Context context) {
        this(context, null);
    }

    public ItemGroupView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOrientation(LinearLayoutCompat.VERTICAL);
        setMinimumHeight(dp2px(48));

        mMainLayout = new LinearLayoutCompat(getContext());
        mTitleView = new AppCompatTextView(getContext());
        mMustFullView = new AppCompatTextView(getContext());
        mLineView = new View(getContext());

        mMainLayout.setOrientation(LinearLayoutCompat.HORIZONTAL);
        mMainLayout.setGravity(Gravity.CENTER_VERTICAL);
        mMainLayout.setMinimumHeight(dp2px(48));

        createView(context, attrs, defStyleAttr);
        initAttrView(context, attrs, defStyleAttr);
    }

    protected abstract void createView(Context context, AttributeSet attrs, int defStyleAttr);

    protected void initAttrView(Context context, AttributeSet attrs, int defStyleAttr) {
        loadFromMustFullAttributes(context, attrs, defStyleAttr);
        loadFromLeftTitleAttributes(context, attrs, defStyleAttr);
        loadFromDividerLineAttributes(context, attrs, defStyleAttr);
    }

//    public abstract void splicingView(LinearLayoutCompat mainLayout, AppCompatTextView titleView, AppCompatTextView mustFullView, View lineView);

    public void addRightLayout() {
        addTitleView();
        addRightView();
        LayoutParams mainLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER_VERTICAL);
        addView(mMainLayout, 0, mainLayoutParams);
        addView(mLineView, 1, new LayoutParams(LayoutParams.MATCH_PARENT, 1, Gravity.BOTTOM));
    }

    public void addBottomLayout(View view) {
        addTitleView();
        addRightView();

        LayoutParams mainLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER_VERTICAL);
        addView(mMainLayout, 0, mainLayoutParams);

        LayoutParams inputParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        inputParams.gravity = Gravity.CENTER_VERTICAL;
        inputParams.leftMargin = dp2px(16);
        inputParams.rightMargin = dp2px(16);
        inputParams.bottomMargin = dp2px(8);
        addView(view, 1, inputParams);

        addView(mLineView, 2, new LayoutParams(LayoutParams.MATCH_PARENT, 1, Gravity.BOTTOM));
    }

    protected abstract void addRightView();

    protected void addTitleView() {
        removeMainViews();
        removeAllViews();
        setCommonPadding(mTitleView);

        mTitleView.setLineSpacing(dp2px(6), mTitleView.getLineSpacingMultiplier());
        mTitleView.setCompoundDrawablePadding(dp2px(8));

        LayoutParams leftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftParams.gravity = Gravity.CENTER_VERTICAL;
        mMainLayout.addView(mTitleView, leftParams);

        LayoutParams mustFullParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mustFullParams.gravity = Gravity.CENTER_VERTICAL;
        mMainLayout.addView(mMustFullView, mustFullParams);
    }


    /**
     * 获取主布局
     */
    public LinearLayoutCompat getMainLayout() {
        return mMainLayout;
    }

    /**
     * 获取左标题
     */
    @Override
    public AppCompatTextView getLeftView() {
        return mTitleView;
    }

    @Override
    public T setLeftIcon(Drawable drawable) {
        mTitleView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        return (T) this;
    }

    @Override
    public Drawable getLeftIcon() {
        return mTitleView.getCompoundDrawables()[0];
    }

    /**
     * 获取分割线
     */
    @Override
    public View getLineView() {
        return mLineView;
    }

    @Override
    public AppCompatTextView getMustFullView() {
        return mMustFullView;
    }

    public void removeMainViews() {
        if (mMainLayout.getChildCount() != 0) {
            mMainLayout.removeAllViews();
        }
    }

    @Override
    public void removeAllViews() {
        if (getChildCount() != 0) {
            super.removeAllViews();
        }
    }

    public static int dp2px(Context context, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }


    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        mPosition = position;
    }
}

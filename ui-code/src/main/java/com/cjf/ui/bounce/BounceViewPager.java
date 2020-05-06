package com.cjf.ui.bounce;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * <p>Title: ElasticNestedScrollView </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/9 17:45
 */
public class BounceViewPager extends ViewPager implements BounceAction {

    private BounceDelegate mBounceDelegate;

    public BounceViewPager(@NonNull Context context) {
        this(context, null);
    }

    public BounceViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BounceViewPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        mBounceDelegate = new BounceDelegate(this);
        loadFromBounceAttributes(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mBounceDelegate.onInterceptTouchEvent(ev)) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mBounceDelegate.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mBounceDelegate.onDetachedFromWindow(this);
    }

    @Override
    public BounceDelegate getBounceDelegate() {
        return mBounceDelegate;
    }

}
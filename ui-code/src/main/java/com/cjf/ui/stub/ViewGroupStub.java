package com.cjf.ui.stub;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.cjf.ui.R;

import androidx.annotation.LayoutRes;


/**
 *    自定义 ViewStub（原生 ViewStub 的缺点：继承至 View，不支持 findViewById、动态添加和移除 View、监听显示隐藏）
 */
public final class ViewGroupStub extends FrameLayout {

    private OnViewStubListener mListener;

    private int mLayoutResource;

    private View mInflateView;

    public ViewGroupStub(Context context,@LayoutRes int layoutResource) {
        this(context, null);
        mLayoutResource = layoutResource;
    }

    public ViewGroupStub(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewGroupStub(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ViewGroupStub);
        mLayoutResource = array.getResourceId(R.styleable.ViewGroupStub_layout, 0);
        array.recycle();

        // 隐藏自己
        setVisibility(GONE);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (mInflateView == null && visibility != GONE) {
            inflate();
        }
        if (mListener != null) {
            mListener.onVisibility(this, visibility);
        }
    }

    /**
     * Inflates the layout resource identified by {@link #getLayoutResource()}
     * and replaces this StubbedView in its parent by the inflated layout resource.
     *
     * @return The inflated layout resource.
     *
     */
    private View inflate() {
        if (mLayoutResource != 0) {
            mInflateView = LayoutInflater.from(getContext()).inflate(mLayoutResource, this, false);
            LayoutParams layoutParams = (LayoutParams) mInflateView.getLayoutParams();
            layoutParams.width = getLayoutParams().width;
            layoutParams.height = getLayoutParams().height;
            if (layoutParams.gravity == LayoutParams.UNSPECIFIED_GRAVITY) {
                layoutParams.gravity = Gravity.CENTER;
            }
            mInflateView.setLayoutParams(layoutParams);
            removeAllViews();
            addView(mInflateView);
            if (mListener != null) {
                mListener.onInflate(this, mInflateView);
            }
            return mInflateView;
        } else {
            throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
        }
    }

    /**
     * 设置显示状态（避免 setVisibility 导致的无限递归）
     */
    public void setCustomVisibility(int visibility) {
        super.setVisibility(visibility);
    }

    /**
     * 获取填充的 View
     */
    public View getInflateView() {
        return mInflateView;
    }

    /**
     * 设置监听器
     */
    public void setOnViewStubListener(OnViewStubListener listener) {
        mListener = listener;
    }

    public interface OnViewStubListener {

        /**
         * 布局填充回调（可在此中做 View 初始化）
         *
         * @param stub              当前 ViewStub 对象
         * @param inflatedView      填充布局对象
         */
        void onInflate(ViewGroupStub stub, View inflatedView);

        /**
         * 可见状态改变（可在此中做 View 更新）
         *
         * @param stub              当前 ViewStub 对象
         * @param visibility        可见状态参数改变
         */
        void onVisibility(ViewGroupStub stub, int visibility);
    }
}
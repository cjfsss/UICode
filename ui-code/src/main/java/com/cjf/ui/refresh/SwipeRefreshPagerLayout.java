package com.cjf.ui.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cjf.ui.bounce.Bounce;
import com.cjf.ui.bounce.BounceAction;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * <p>Title: SwipeRefreshPagerLayout </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/12 2:20
 */
public class SwipeRefreshPagerLayout extends SwipeRefreshLayout implements RefreshPagerLayout,
        RefreshModule, SwipeRefreshLayout.OnRefreshListener, LoadView, Bounce {

    private boolean mEnableRefresh = true;
    private final RefreshPagerDelegate mRefreshPagerDelegate;

    private boolean mIsRefreshLoading;

    private BaseQuickAdapter mAdapter;

    private LoadView mLoadView;

    private View mLoadFailView;
    private View mLoadNetworkView;
    private View mEmptyView;
    private View mLoadingView;
    private OnClickListener mOnClickListener;

    public SwipeRefreshPagerLayout(@NonNull Context context) {
        this(context, null);
    }

    public SwipeRefreshPagerLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mRefreshPagerDelegate = new RefreshPagerDelegate(this);
        setOnRefreshListener(this);
    }

    public void setAdapter(BaseQuickAdapter adapter) {
        mAdapter = adapter;
        mRefreshPagerDelegate.setLoadMoreModule(adapter.getLoadMoreModule());
    }

    public BaseLoadMoreModule getLoadMoreModule() {
        return mRefreshPagerDelegate.getLoadMoreModule();
    }

    @Override
    public void setOnRefreshPagerListener(OnRefreshPagerListener pagerListener) {
        mRefreshPagerDelegate.setOnRefreshPagerListener(pagerListener);
    }

    @Override
    public void onRefresh() {
        mRefreshPagerDelegate.onRefresh();
        mIsRefreshLoading = true;
    }

    @Override
    public void openRefresh() {
        mRefreshPagerDelegate.openRefresh();
    }

    @Override
    public void disableRefresh() {
        mRefreshPagerDelegate.disableRefresh();
    }

    @Override
    public void openLoadMore() {
        mRefreshPagerDelegate.openLoadMore();
    }

    @Override
    public void disableLoadMore() {
        mRefreshPagerDelegate.disableLoadMore();
    }

    @Override
    public void startRefresh() {
        mRefreshPagerDelegate.startRefresh();
    }

    @Override
    public void startLoadMore() {
        mRefreshPagerDelegate.startLoadMore();
    }

    @Override
    public void finishEnd(int newSize) {
        mRefreshPagerDelegate.finishEnd(newSize);
    }

    @Override
    public void setStartPageIndex(int startPageIndex) {
        mRefreshPagerDelegate.setStartPageIndex(startPageIndex);
    }

    @Override
    public void setPageSize(int pageSize) {
        mRefreshPagerDelegate.setPageSize(pageSize);
    }

    @Override
    public int getPageSize() {
        return mRefreshPagerDelegate.getPageSize();
    }

    @Override
    public boolean isDropDownRefreshing() {
        return mRefreshPagerDelegate.isDropDownRefreshing();
    }

    @Override
    public boolean isMoreRefreshing() {
        return mRefreshPagerDelegate.isMoreRefreshing();
    }

    @Override
    public void loadMoreFail() {
        mRefreshPagerDelegate.loadMoreFail();
    }

    @Override
    public void setEnableRefresh(boolean enableRefresh) {
        mEnableRefresh = enableRefresh;
        setEnabled(enableRefresh);
        setOnRefreshListener(mEnableRefresh ? this : null);
    }

    @Override
    public boolean isEnableRefreshing() {
        return mEnableRefresh;
    }

    @Override
    public boolean isRefreshLoading() {
        return mIsRefreshLoading;
    }

    @Override
    public void refreshToLoading() {
        if (!isEnableRefreshing() || isRefreshLoading()) {
            // 没有开启或者正在刷新
            return;
        }
        // 可以进行下拉刷新
        mIsRefreshLoading = true;
        setRefreshing(true);
    }

    @Override
    public void refreshEnd() {
        if (isRefreshLoading() || isRefreshing()) {
            // 刷新完成，关闭动画
            mIsRefreshLoading = false;
            setRefreshing(false);
        }
    }

    @Override
    public void refreshFail() {
        // 刷新失败，请稍后再试
        refreshEnd();
        if (mAdapter != null) {
            mAdapter.setNewInstance(new ArrayList());
            mAdapter.setEmptyView(getLoadView(getContext(), mLoadFailView, LoadStaus.Fail));
        }
    }

    @Override
    public void refreshEmpty() {
        refreshEnd();
        if (mAdapter != null) {
            mAdapter.setNewInstance(new ArrayList());
            mAdapter.setEmptyView(getLoadView(getContext(), mEmptyView, LoadStaus.Empty));
        }
    }

    @Override
    public void refreshNetwork() {
        refreshEnd();
        if (mAdapter != null) {
            mAdapter.setNewInstance(new ArrayList());
            mAdapter.setEmptyView(getLoadView(getContext(), mLoadNetworkView, LoadStaus.Network));
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setLoadView(new SimpleLoadView());
        mRefreshPagerDelegate.onAttachedToWindow(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mRefreshPagerDelegate.onDetachedFromWindow(this);
    }

    public void setLoadView(LoadView loadView) {
        mLoadView = loadView;
    }

    @Override
    public View getEmptyView(Context context) {
        if (mEmptyView == null) {
            mEmptyView = mLoadView.getEmptyView(context);
        }
        return mEmptyView;
    }

    @Override
    public View getLoadingView(Context context) {
        if (mLoadingView == null) {
            mLoadingView = mLoadView.getLoadingView(context);
        }
        return mLoadingView;
    }

    @Override
    public View getLoadFailView(Context context) {
        if (mLoadFailView == null) {
            mLoadFailView = mLoadView.getLoadFailView(context);
        }
        mLoadFailView.setOnClickListener(mOnClickListener);
        return mLoadFailView;
    }

    @Override
    public View getLoadNetworkView(Context context) {
        if (mLoadNetworkView == null) {
            mLoadNetworkView = mLoadView.getLoadNetworkView(context);
        }
        return mLoadNetworkView;
    }

    @Override
    public View getLoadView(Context context, @Nullable View targetView, LoadStaus loadStaus) {
        return mLoadView.getLoadView(context, targetView, loadStaus);
    }

    @Override
    public void disableBounce() {
        if (getChildCount() == 0) {
            return;
        }
        View childAt = getChildAt(0);
        if (childAt instanceof BounceAction) {
            ((BounceAction) childAt).disableBounce();
        }
    }

    @Override
    public void openBothHorizontal() {
    }

    @Override
    public void openBothVertical() {
        if (getChildCount() == 0) {
            return;
        }
        View childAt = getChildAt(0);
        if (childAt instanceof BounceAction) {
            ((BounceAction) childAt).openBothVertical();
        }
    }

    @Override
    public void openTop() {
        if (getChildCount() == 0) {
            return;
        }
        View childAt = getChildAt(0);
        if (childAt instanceof BounceAction) {
            ((BounceAction) childAt).openTop();
        }
    }

    @Override
    public void openBottom() {
        if (getChildCount() == 0) {
            return;
        }
        View childAt = getChildAt(0);
        if (childAt instanceof BounceAction) {
            ((BounceAction) childAt).openBottom();
        }
    }

    @Override
    public void openLeft() {
    }

    @Override
    public void openRight() {

    }

    public void setOnResetClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }
}

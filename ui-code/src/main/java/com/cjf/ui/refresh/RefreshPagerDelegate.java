package com.cjf.ui.refresh;

import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cjf.ui.base.OnLifeWindow;

/**
 * <p>Title: RefreshPagerDelegate </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/12 13:56
 */
public class RefreshPagerDelegate implements RefreshPagerLayout, OnLifeWindow<SwipeRefreshPagerLayout> {

    private OnRefreshPagerListener mOnRefreshPagerListener;

    private BaseLoadMoreModule mLoadMoreModule;

    private RefreshModule mRefreshModule;

    private long mPageIndex;
    private int mStartPageIndex;
    private int mPageSize;

    public RefreshPagerDelegate(RefreshModule refreshModule) {
        mRefreshModule = refreshModule;
    }

    public void onRefresh() {
        if (isMoreRefreshing() || (mRefreshModule != null && !mRefreshModule.isEnableRefreshing())) {
            // 正在下拉刷新，或者下拉刷新已经禁止
            if (mRefreshModule != null) {
                mRefreshModule.refreshEnd();
            }
            return;
        }
        //加载刷新数据
        mPageIndex = mStartPageIndex;
        if (mOnRefreshPagerListener != null) {
            mOnRefreshPagerListener.onPagerRefresh(this, true, mPageIndex, getPageSize());
        }
    }

    public void setLoadMoreModule(BaseLoadMoreModule loadMoreModule) {
        mLoadMoreModule = loadMoreModule;
        if (mLoadMoreModule == null) {
            return;
        }
        mLoadMoreModule.setPreLoadNumber(getPageSize());
        mLoadMoreModule.setOnLoadMoreListener(this::onLoadMore);
    }

    public void onLoadMore() {
        if (isDropDownRefreshing()) {
            // 正在下拉刷新，或者下拉刷新已经禁止
            if (mLoadMoreModule != null) {
                mLoadMoreModule.loadMoreEnd();
            }
            return;
        }
        //加载数据
        mPageIndex++;
        if (mOnRefreshPagerListener != null) {
            mOnRefreshPagerListener.onPagerRefresh(this, false, mPageIndex, getPageSize());
        }
    }

    @Override
    public void setOnRefreshPagerListener(OnRefreshPagerListener pagerListener) {
        mOnRefreshPagerListener = pagerListener;
    }

    @Override
    public void openRefresh() {
        if (mRefreshModule != null) {
            mRefreshModule.setEnableRefresh(true);
        }
    }

    @Override
    public void disableRefresh() {
        if (mRefreshModule != null) {
            mRefreshModule.setEnableRefresh(false);
        }
    }

    @Override
    public void openLoadMore() {
        if (mLoadMoreModule == null) {
            return;
        }
        mLoadMoreModule.setEnableLoadMore(true);
        mLoadMoreModule.setOnLoadMoreListener(this::onLoadMore);
    }

    @Override
    public void disableLoadMore() {
        if (mLoadMoreModule == null) {
            return;
        }
        mLoadMoreModule.setEnableLoadMore(false);
        mLoadMoreModule.setOnLoadMoreListener(null);
    }

    @Override
    public void startRefresh() {
        if (mRefreshModule == null) {
            return;
        }
        if (!mRefreshModule.isEnableRefreshing()) {
            // 下拉刷新已经关闭
            return;
        }
        if (isDropDownRefreshing() || isMoreRefreshing()) {
            // 正在下拉刷新，或者下拉刷新已经禁止
            return;
        }
        mRefreshModule.refreshToLoading();
        onRefresh();
    }

    @Override
    public void startLoadMore() {
        if (mLoadMoreModule == null) {
            return;
        }
        if (!mLoadMoreModule.isEnableLoadMore()) {
            // 上拉加载已经关闭
            return;
        }
        if (isDropDownRefreshing() || isMoreRefreshing()) {
            // 正在下拉刷新，或者下拉刷新已经禁止
            return;
        }
        mLoadMoreModule.loadMoreToLoading();
        onLoadMore();
    }

    @Override
    public void finishEnd(int newSize) {
        if (isDropDownRefreshing()) {
            mRefreshModule.refreshEnd();
            if ((newSize < getPageSize())) {
                // 所有数据加载完成，调用此方法
                // 需要重置"加载完成"状态时，请调用 setNewData()
                if (mLoadMoreModule != null) {
                    mLoadMoreModule.loadMoreEnd();
                }
            }
        }
        if (isMoreRefreshing()) {
            if ((newSize < getPageSize())) {
                // 所有数据加载完成，调用此方法
                // 需要重置"加载完成"状态时，请调用 setNewData()
                mLoadMoreModule.loadMoreEnd();
            } else {
                // 加载更多
                mLoadMoreModule.loadMoreComplete();
            }
        }
    }

    @Override
    public void setStartPageIndex(int startPageIndex) {
        mStartPageIndex = startPageIndex;
    }

    @Override
    public void setPageSize(int pageSize) {
        mPageSize = pageSize;
    }

    @Override
    public int getPageSize() {
        if (mPageSize == 0) {
            mPageSize = 30;
        }
        return mPageSize;
    }

    @Override
    public boolean isDropDownRefreshing() {
        return mRefreshModule != null && mRefreshModule.isRefreshLoading();
    }

    @Override
    public boolean isMoreRefreshing() {
        return mLoadMoreModule != null && mLoadMoreModule.isLoading();
    }

    @Override
    public void loadMoreFail() {
        if (mLoadMoreModule != null) {
            mLoadMoreModule.loadMoreFail();
        }
    }

    public BaseLoadMoreModule getLoadMoreModule() {
        return mLoadMoreModule;
    }

    @Override
    public void onAttachedToWindow(SwipeRefreshPagerLayout view) {

    }

    @Override
    public void onDetachedFromWindow(SwipeRefreshPagerLayout view) {
        mOnRefreshPagerListener = null;
        mLoadMoreModule = null;
        mRefreshModule = null;
    }
}

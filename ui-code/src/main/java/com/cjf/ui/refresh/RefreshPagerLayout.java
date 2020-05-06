package com.cjf.ui.refresh;

import com.cjf.ui.bounce.BounceDelegate;

import androidx.appcompat.widget.LinearLayoutCompat;

/**
 * <p>Title: RefreshPagerLayout </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/12 1:45
 */
public interface RefreshPagerLayout {

    /**
     * 设置刷新监听
     *
     * @param pagerListener 监听事件
     */
    void setOnRefreshPagerListener(OnRefreshPagerListener pagerListener);

    /**
     * 打开下拉刷新
     */
    void openRefresh();

    /**
     * 禁止下拉刷新
     */
    void disableRefresh();

    /**
     * 打开加载更多
     */
    void openLoadMore();

    /**
     * 禁止加载更多
     */
    void disableLoadMore();

    /**
     * 禁止下拉和上拉
     */
    default void disableBoth() {
        disableLoadMore();
        disableRefresh();
    }

    /**
     * 打开下拉和上拉
     */
    default void openBoth() {
        openRefresh();
        openLoadMore();
    }

    /**
     * 开启自动刷新
     *
     * @return this
     */
    void startRefresh();

    /**
     * 开启自动加载更多
     *
     * @return this
     */
    void startLoadMore();

    /**
     * 数据加载结束
     */
    default void finishEnd() {
        finishEnd(Integer.MAX_VALUE);
    }

    /**
     * 数据加载结束
     */
    void finishEnd(int newSize);

    /**
     * 从第几条开始
     *
     * @param startPageIndex 从第几条开始
     */
    void setStartPageIndex(int startPageIndex);

    /**
     * 设置每一页条数
     *
     * @param pageSize 每一页条数
     */
    void setPageSize(int pageSize);

    /**
     * 获取一页多少条
     *
     * @return
     */
    int getPageSize();

    /**
     * 是否正是下拉刷新
     *
     * @return
     */
    boolean isDropDownRefreshing();

    /**
     * 是否正在上拉加载
     *
     * @return
     */
    boolean isMoreRefreshing();

    /**
     * 上拉加载失败
     */
    void loadMoreFail();
}

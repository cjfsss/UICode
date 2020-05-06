package com.cjf.ui.refresh;

/**
 * <p>Title: RefreshModule </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/12 14:11
 */
public interface RefreshModule {
    /**
     * 设置是否启用 下拉刷新
     *
     * @param enableRefresh true  启用
     */
    void setEnableRefresh(boolean enableRefresh);

    /**
     * 下拉刷新是否可以使用
     *
     * @return
     */
    boolean isEnableRefreshing();

    /**
     * 是否正在刷新
     *
     * @return
     */
    boolean isRefreshLoading();

    /**
     * 开始下拉刷新
     */
    void refreshToLoading();

    /**
     * 下拉刷新结束
     */
    void refreshEnd();

    /**
     * 下拉刷新失败
     */
    void refreshFail();

    /**
     * 空布局
     */
    void refreshEmpty();

    /**
     * 网络错误
     */
    void refreshNetwork();
}

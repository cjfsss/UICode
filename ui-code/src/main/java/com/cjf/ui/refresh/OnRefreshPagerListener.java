package com.cjf.ui.refresh;

/**
 * <p>Title: OnRefreshPagerListener </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/12 1:37
 */
public interface OnRefreshPagerListener {

    /**
     * 获取数据
     *
     * @param pageIndex 第几页
     * @param pageSize  每页的数量
     */
    void onPagerRefresh(RefreshPagerLayout refreshPagerLayout, boolean isRefresh, long pageIndex, long pageSize);
}

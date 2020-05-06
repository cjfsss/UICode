package com.cjf.ui.recycler.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.cjf.ui.entities.DefaultCompoundItemBean;
import com.cjf.ui.item.SwitchItem;

import java.util.List;

/**
 * <p>Title: SingleCompoundAdapter </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/11 17:08
 */
public class DefaultSingleSwitchAdapter extends SingleCompoundAdapter<DefaultCompoundItemBean, SwitchItem> {
    public DefaultSingleSwitchAdapter(List<DefaultCompoundItemBean> data) {
        super(data);
    }

    public DefaultSingleSwitchAdapter() {
        super();
    }

    @Override
    protected View getView(ViewGroup parent, int viewType) {
        return new SwitchItem(getContext());
    }
}

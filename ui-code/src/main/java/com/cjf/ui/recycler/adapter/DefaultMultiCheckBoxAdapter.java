package com.cjf.ui.recycler.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.cjf.ui.entities.DefaultCompoundItemBean;
import com.cjf.ui.item.CheckBoxItem;

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
public class DefaultMultiCheckBoxAdapter extends MultiCompoundAdapter<DefaultCompoundItemBean, CheckBoxItem> {

    public DefaultMultiCheckBoxAdapter(List<DefaultCompoundItemBean> data) {
        super(data);
    }

    public DefaultMultiCheckBoxAdapter() {
        super();
    }

    @Override
    protected View getView(ViewGroup parent, int viewType) {
        return new CheckBoxItem(getContext());
    }
}

package com.cjf.ui.recycler.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.cjf.ui.entities.DefaultCompoundSubtitleItemBean;
import com.cjf.ui.item.CheckBoxSubtitleItem;

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
public class DefaultSingleCheckBoxSubtitleAdapter extends SingleCompoundAdapter<DefaultCompoundSubtitleItemBean, CheckBoxSubtitleItem> {
    public DefaultSingleCheckBoxSubtitleAdapter(List<DefaultCompoundSubtitleItemBean> data) {
        super(data);
    }

    public DefaultSingleCheckBoxSubtitleAdapter() {
    }

    @Override
    protected View getView(ViewGroup parent, int viewType) {
        return new CheckBoxSubtitleItem(getContext());
    }
}

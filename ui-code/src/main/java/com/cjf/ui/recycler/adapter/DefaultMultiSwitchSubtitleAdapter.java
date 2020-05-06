package com.cjf.ui.recycler.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.cjf.ui.entities.DefaultCompoundSubtitleItemBean;
import com.cjf.ui.item.SwitchSubtitleItem;

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
public class DefaultMultiSwitchSubtitleAdapter extends MultiCompoundAdapter<DefaultCompoundSubtitleItemBean, SwitchSubtitleItem> {

    public DefaultMultiSwitchSubtitleAdapter(List<DefaultCompoundSubtitleItemBean> data) {
        super(data);
    }

    public DefaultMultiSwitchSubtitleAdapter() {
    }

    @Override
    protected View getView(ViewGroup parent, int viewType) {
        return new SwitchSubtitleItem(getContext());
    }
}

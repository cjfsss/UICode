package com.cjf.ui.dialog.adapter;

import android.content.Context;
import android.view.View;

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

    public DefaultSingleCheckBoxSubtitleAdapter(Context context, List<DefaultCompoundSubtitleItemBean> data) {
        super(context, data);
    }

    @Override
    protected View getView() {
        return new CheckBoxSubtitleItem(getContext());
    }
}

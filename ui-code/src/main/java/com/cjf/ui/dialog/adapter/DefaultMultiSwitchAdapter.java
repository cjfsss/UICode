package com.cjf.ui.dialog.adapter;

import android.content.Context;
import android.view.View;

import com.cjf.ui.entities.DefaultCompoundItemBean;
import com.cjf.ui.item.CheckBoxItem;
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
public class DefaultMultiSwitchAdapter extends MultiCompoundAdapter<DefaultCompoundItemBean, CheckBoxItem> {

    public DefaultMultiSwitchAdapter(Context context, List<DefaultCompoundItemBean> data) {
        super(context, data);
    }

    @Override
    protected View getView() {
        return new SwitchItem(getContext());
    }
}

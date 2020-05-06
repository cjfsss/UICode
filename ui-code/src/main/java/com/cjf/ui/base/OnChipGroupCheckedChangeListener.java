package com.cjf.ui.base;

import com.cjf.ui.chip.ChipGroupLayout;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import androidx.annotation.IdRes;

/**
 * <p>Title: OnChipGroupCheckedChangeListener </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/3/27 13:52
 */
public interface OnChipGroupCheckedChangeListener {

    void onCheckedChanged(ChipGroupLayout chipGroup, @IdRes int checkedId, String clickText, boolean isChecked);
}

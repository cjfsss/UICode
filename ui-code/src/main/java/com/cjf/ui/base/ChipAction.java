package com.cjf.ui.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.cjf.ui.R;
import com.cjf.ui.chip.ChipGroupLayout;

import java.util.List;

/**
 * <p>Title: ChipAction </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/3 17:29
 */
@SuppressWarnings("unchecked")
public interface ChipAction<SELF> extends ContextAction {

    ChipGroupLayout getChipGroup();

    /**
     * 添加需要选择的chip
     *
     * @param chipArrays 选项
     * @return
     */
    default SELF addChip(List<String> chipArrays) {
        return addChip(true, null, chipArrays);
    }

    /**
     * 添加需要选择的chip
     *
     * @param checkChipText 选中的文字
     * @param chipArrays    选项
     * @return
     */
    default SELF addChip(String checkChipText, List<String> chipArrays) {
        return addChip(true, checkChipText, chipArrays);
    }

    /**
     * 添加需要选择的chip
     *
     * @param clickable  是否可以点击(选择)
     * @param chipArrays 选项
     * @return
     */
    default SELF addChip(boolean clickable, List<String> chipArrays) {
        return addChip(clickable, null, chipArrays);
    }

    /**
     * 添加需要选择的chip
     *
     * @param clickable     是否可以选择
     * @param checkChipText 选中的文字
     * @param chipArrays    选项
     * @return
     */
    default SELF addChip(boolean clickable, String checkChipText, List<String> chipArrays) {
        getChipGroup().addChip(clickable, checkChipText, chipArrays);
        return (SELF) this;
    }

    default void setChipClickable(boolean clickable) {
        getChipGroup().setClickable(clickable);
    }

    default SELF check(String checkText) {
        getChipGroup().check(checkText);
        return (SELF) this;
    }

    default SELF check(List<String> checkTextList) {
        getChipGroup().check(checkTextList);
        return (SELF) this;
    }

    default SELF checkAll(boolean isChecked) {
        getChipGroup().checkAll(isChecked);
        return (SELF) this;
    }

    default SELF setChipAction() {
        getChipGroup().setChipAction();
        return (SELF) this;
    }

    default SELF setChipFilter() {
        getChipGroup().setChipFilter();
        return (SELF) this;
    }

    default SELF setChipChoice() {
        getChipGroup().setChipChoice();
        return (SELF) this;
    }

    default SELF setChipEntry() {
        getChipGroup().setChipEntry();
        return (SELF) this;
    }

    default void addOnChipGroupCheckedChangeListener(OnChipGroupCheckedChangeListener listener) {
        getChipGroup().addOnChipGroupCheckedChangeListener(listener);
    }

    default List<OnChipGroupCheckedChangeListener> getOnChipGroupCheckedChangeListenerList() {
        return getChipGroup().getOnChipGroupCheckedChangeListenerList();
    }

    default List<String> getCheckList() {
        return getChipGroup().getCheckList();
    }

    default List<String> getChipAllList() {
        return getChipGroup().getChipAllList();
    }

    default int getChipCheckPosition() {
        return getChipGroup().getChipCheckPosition();
    }

    default String getChipCheckText() {
        return getChipGroup().getChipCheckText();
    }

    default SELF setSingleSelection(boolean singleSelection) {
        getChipGroup().setSingleSelection(singleSelection);
        return (SELF) this;
    }

    default void loadFromChipAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ChipAction, defStyleAttr, 0);
        try {
            String checkText = null;
            int chipType = array.getInt(R.styleable.ChipAction_item_chipType, 1);
            if (array.hasValue(R.styleable.ChipAction_item_chipAllText)) {
                checkText = array.getString(R.styleable.ChipAction_item_chipCheckText);
            }
            List<String> chipAllList = null;
            if (array.hasValue(R.styleable.ChipAction_item_chipAllText)) {
                chipAllList = toListString(array.getTextArray(R.styleable.ChipAction_item_chipAllText));
            }
            boolean chipClickable = array.getBoolean(R.styleable.ChipAction_item_chipClickable, true);
            // true 单选 false 多选
            setSingleSelection(chipType == 1);
            addChip(chipClickable, checkText, chipAllList);
        } finally {
            array.recycle();
        }
    }
}

package com.cjf.ui.item;

import android.animation.AnimatorInflater;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;

import com.cjf.ui.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.internal.ViewUtils;

import androidx.core.content.ContextCompat;

/**
 * <p>Title: ChipUtils </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/3/30 13:04
 */
public abstract class ChipUtils {


    @SuppressLint({"PrivateResource", "RestrictedApi"})
    public static void setChip(Chip chip) {
        Context context = chip.getContext();
        chip.setFocusable(true);
        chip.setClickable(true);
        chip.setCheckable(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            chip.setStateListAnimator(AnimatorInflater.loadStateListAnimator(chip.getContext(),
                    R.animator.mtrl_chip_state_list_anim));
        }
        chip.setChipIconVisible(true);
        chip.setCheckedIconVisible(true);
        chip.setCloseIconVisible(true);
        chip.setChipIcon(null);
        chip.setCheckedIcon(ContextCompat.getDrawable(context, com.google.android.material.R.drawable.ic_mtrl_chip_checked_circle));
        chip.setCloseIcon(ContextCompat.getDrawable(context, com.google.android.material.R.drawable.ic_mtrl_chip_close_circle));
        chip.setText(null);
        chip.setTextAppearance(R.style.TextAppearance_AppCompat_Body2);
        chip.setTextColor(ContextCompat.getColor(context, com.google.android.material.R.color.mtrl_chip_text_color));
        chip.setCloseIconTintResource(com.google.android.material.R.color.mtrl_chip_close_icon_tint);
        chip.setChipBackgroundColorResource(com.google.android.material.R.color.mtrl_chip_background_color);
        chip.setChipStrokeWidth(0);
        chip.setRippleColorResource(R.color.mtrl_chip_ripple_color);
        chip.setChipMinHeight( ViewUtils.dpToPx(context,32));
        chip.setChipCornerRadius( ViewUtils.dpToPx(context,16));
        chip.setChipIconSize( ViewUtils.dpToPx(context,24));
        chip.setCloseIconSize( ViewUtils.dpToPx(context,18));
        chip.setChipStartPadding( ViewUtils.dpToPx(context,4));
        chip.setIconStartPadding( ViewUtils.dpToPx(context,0));
        chip.setIconEndPadding( ViewUtils.dpToPx(context,0));
        chip.setTextStartPadding( ViewUtils.dpToPx(context,8));
        chip.setTextEndPadding( ViewUtils.dpToPx(context,6));
        chip.setCloseIconStartPadding( ViewUtils.dpToPx(context,2));
        chip.setCloseIconEndPadding( ViewUtils.dpToPx(context,2));
        chip.setChipEndPadding( ViewUtils.dpToPx(context,6));
    }

    public static void setChipFilter(Chip chip) {
        setChip(chip);
        chip.setClickable(true);
        chip.setChipIconVisible(false);
        chip.setCloseIconVisible(false);
        chip.setChipIcon(ContextCompat.getDrawable(chip.getContext(), R.drawable.ic_mtrl_chip_checked_black));
    }

    public static void setChipChoice(Chip chip) {
        setChip(chip);
        chip.setClickable(true);
        chip.setChipIconVisible(false);
        chip.setCheckedIconVisible(false);
        chip.setCloseIconVisible(false);
        chip.setChipIcon(ContextCompat.getDrawable(chip.getContext(), R.drawable.ic_mtrl_chip_checked_black));
    }

    public static void setChipEntry(Chip chip) {
        setChip(chip);
        chip.setClickable(true);
    }

    public static void setChipAction(Chip chip) {
        setChip(chip);
        chip.setCloseIconVisible(false);
    }

    @SuppressLint("RestrictedApi")
    public static void setChipGroup(ChipGroup chipGroup) {
        chipGroup.setChipSpacing((int) ViewUtils.dpToPx(chipGroup.getContext(), 4));
        chipGroup.setSingleLine(false);
        chipGroup.setSingleSelection(false);
    }

    public static Chip createTagTextView(Context context, CharSequence tagString) {
        Chip chipText = new Chip(context);
        ChipUtils.setChipFilter(chipText);
        chipText.setTextColor(ContextCompat.getColor(context,androidx.appcompat.R.color.primary_text_default_material_dark));
        chipText.setTextSize(14);
        chipText.setText(tagString);
        return chipText;
    }


}

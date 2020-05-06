package com.cjf.ui.base;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BlendMode;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.cjf.ui.R;
import com.cjf.ui.UICore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

/**
 * <p>Title: ContextAction </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/3 13:21
 */
public interface ContextAction {

    default Context getContext() {
        return UICore.get().getContext();
    }

    default Resources getResources() {
        return getContext().getResources();
    }

    default void checkAppCompatTheme(Context context) {
        int[] APPCOMPAT_CHECK_ATTRS = {
                androidx.appcompat.R.attr.colorPrimary
        };
        TypedArray a = context.obtainStyledAttributes(APPCOMPAT_CHECK_ATTRS);
        final boolean failed = !a.hasValue(0);
        a.recycle();
        if (failed) {
            throw new IllegalArgumentException("You need to use a Theme.AppCompat theme "
                    + "(or descendant) with the design library.");
        }
    }

    default int dp2px(int i) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) i, getResources().getDisplayMetrics());
    }

    default int sp2px(int i) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, (float) i, getResources().getDisplayMetrics());
    }

    default void setCommonPaddingTopBottom(View view) {
        int leftRight = dp2px(0);
        int topBottom = dp2px(8);
        setPadding(view, leftRight, topBottom);
    }

    default void setCommonPaddingLeftRight(View view) {
        int leftRight = dp2px(16);
        int topBottom = dp2px(0);
        setPadding(view, leftRight, topBottom);
    }

    default void setCommonPadding(View view) {
        int leftRight = dp2px(16);
        int topBottom = dp2px(8);
        setPadding(view, leftRight, topBottom);
    }

    default void setPadding(View view, int leftRight, int topBottom) {
        if (Build.VERSION.SDK_INT >= 17) {
            view.setPaddingRelative(leftRight, topBottom, leftRight, topBottom);
        } else {
            view.setPadding(leftRight, topBottom, leftRight, topBottom);
        }
    }

    default Drawable tintDrawable(Drawable drawable, @ColorInt int color) {
        if (drawable == null) {
            return null;
        }
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable.mutate());
        DrawableCompat.setTint(wrappedDrawable, color);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            wrappedDrawable.setTintBlendMode(BlendMode.SRC_ATOP);
        } else {
            DrawableCompat.setTintMode(wrappedDrawable, PorterDuff.Mode.SRC_ATOP);
        }
        return wrappedDrawable;
    }

    default Drawable tintDrawable(Drawable drawable, ColorStateList colorStateList) {
        if (drawable == null) {
            return null;
        }
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable.mutate());
        DrawableCompat.setTintList(wrappedDrawable, colorStateList);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            wrappedDrawable.setTintBlendMode(BlendMode.SRC_ATOP);
        } else {
            DrawableCompat.setTintMode(wrappedDrawable, PorterDuff.Mode.SRC_ATOP);
        }
        return wrappedDrawable;
    }

    default int getColor(@ColorRes int id) {
        return ContextCompat.getColor(getContext(), id);
    }


    default int getDefaultTextColor() {
        return ContextCompat.getColor(getContext(), R.color.design_txt);
    }

    /**
     * Hide the soft input.
     *
     * @param view The view.
     */
    default void hideSoftInput(@NonNull final View view) {
        InputMethodManager imm =
                (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Toggle the soft input display or not.
     */
    default void toggleSoftInput() {
        InputMethodManager imm =
                (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.toggleSoftInput(0, 0);
    }

    default double castToDouble(Object value) {
        Double doubleEmpty = castToDoubleEmpty(value);
        return doubleEmpty == null ? 0.0D : doubleEmpty;
    }

    default Double castToDoubleEmpty(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Number) {
            return ((Number) value).doubleValue();
        } else {
            if (value instanceof SpannableStringBuilder) {
                value = castToStringEmpty(value.toString());
            }

            if (value instanceof CharSequence) {
                String strVal = value.toString();
                return isStrNull(strVal) ? null : Double.parseDouble(strVal);
            } else {
                return null;
            }
        }
    }

    default long castToLong(Object value) {
        Long longEmpty = castToLongEmpty(value);
        return longEmpty == null ? 0L : longEmpty;
    }

    default Long castToLongEmpty(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof BigDecimal) {
            BigDecimal decimal = (BigDecimal) value;
            int scale = decimal.scale();
            return scale >= -100 && scale <= 100 ? decimal.longValue() : decimal.longValueExact();
        } else if (value instanceof Number) {
            return ((Number) value).longValue();
        } else {
            if (value instanceof SpannableStringBuilder) {
                value = castToStringEmpty(value.toString());
            }

            if (value instanceof CharSequence) {
                String strVal = castToString(value);
                if (strVal == null || isStrNull(strVal)) {
                    return null;
                }

                try {
                    return Long.parseLong(strVal);
                } catch (NumberFormatException var3) {
                }
            }

            return null;
        }
    }

    default int castToInt(Object value) {
        Integer intEmpty = castToIntEmpty(value);
        return intEmpty == null ? 0 : intEmpty;
    }

    default Integer castToIntEmpty(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof BigDecimal) {
            BigDecimal decimal = (BigDecimal) value;
            int scale = decimal.scale();
            return scale >= -100 && scale <= 100 ? decimal.intValue() : decimal.intValueExact();
        } else if (value instanceof Number) {
            return ((Number) value).intValue();
        } else {
            if (value instanceof SpannableStringBuilder) {
                value = castToStringEmpty(value.toString());
            }

            if (value instanceof CharSequence) {
                String strVal = castToString(value);
                return strVal != null && !isStrNull(strVal) ? Integer.parseInt(strVal) : null;
            } else {
                return null;
            }
        }
    }

    default boolean castToBoolean(Object value) {
        Boolean booleanEmpty = castToBooleanEmpty(value);
        return booleanEmpty == null ? false : booleanEmpty;
    }

    default Boolean castToBooleanEmpty(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Boolean) {
            return (Boolean) value;
        } else if (value instanceof BigDecimal) {
            return ((BigDecimal) value).intValueExact() == 1;
        } else if (value instanceof Number) {
            return ((Number) value).intValue() == 1;
        } else {
            if (value instanceof SpannableStringBuilder) {
                value = castToStringEmpty(value.toString());
            }

            if (value instanceof CharSequence) {
                String strVal = castToString(value);
                if (strVal == null || isStrNull(strVal)) {
                    return null;
                }

                if ("true".equalsIgnoreCase(strVal) || "TRUE".equalsIgnoreCase(strVal) || "1".equals(strVal)) {
                    return Boolean.TRUE;
                }

                if ("false".equalsIgnoreCase(strVal) || "FALSE".equalsIgnoreCase(strVal) || "0".equals(strVal)) {
                    return Boolean.FALSE;
                }
            }

            return null;
        }
    }

    default String castToString(Object value) {
        if (value == null) {
            return null;
        } else {
            String stringEmpty;
            if (value instanceof SpannableStringBuilder) {
                stringEmpty = castToStringEmpty(value.toString());
                return stringEmpty == null ? "" : stringEmpty;
            } else {
                stringEmpty = castToStringEmpty(value);
                return stringEmpty == null ? "" : stringEmpty;
            }
        }
    }

    default String castToStringEmpty(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof CharSequence) {
            String strVal = (String) value;
            return isStrNull(strVal.trim()) ? null : strVal;
        } else if (value instanceof Integer) {
            return String.valueOf((Integer) value);
        } else if (value instanceof Float) {
            return String.valueOf((Float) value);
        } else if (value instanceof Long) {
            return String.valueOf((Long) value);
        } else if (value instanceof Short) {
            return String.valueOf((Short) value);
        } else if (value instanceof Double) {
            return String.valueOf((Double) value);
        } else {
            return value instanceof Boolean ? String.valueOf((Boolean) value) : null;
        }
    }

    default boolean isStrNull(String strVal) {
        return strVal.length() == 0 || TextUtils.equals(strVal, "null") || TextUtils.equals(strVal, "NULL");
    }

    default List<String> toListString(Object[] targetArrays) {
        return toList(String.class, targetArrays);
    }

    default <T> List<T> toList(Class<T> clazz, Object[] targetArrays) {
        return toLists(clazz, targetArrays);
    }

    default <T> List<T> toLists(Class<T> clazz, Object... targetArrays) {
        int length = targetArrays.length;
        List<T> targetList = new ArrayList();

        for (int i = 0; i < length; ++i) {
            if (clazz.equals(String.class)) {
                targetList.add(clazz.cast(castToString(targetArrays[i])));
            } else if (clazz.equals(Integer.class)) {
                targetList.add(clazz.cast(castToInt(targetArrays[i])));
            } else if (clazz.equals(Long.class)) {
                targetList.add(clazz.cast(castToLong(targetArrays[i])));
            } else if (clazz.equals(Double.class)) {
                targetList.add(clazz.cast(castToDouble(targetArrays[i])));
            } else if (clazz.equals(Boolean.class)) {
                targetList.add(clazz.cast(castToBoolean(targetArrays[i])));
            } else {
                targetList.add(clazz.cast(targetArrays[i]));
            }
        }

        return targetList;
    }
}

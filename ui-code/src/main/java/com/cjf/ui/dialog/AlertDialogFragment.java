package com.cjf.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import com.cjf.ui.R;
import com.cjf.ui.base.AnimAction;
import com.cjf.ui.base.ContextAction;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Arrays;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;

/**
 * 一个自定义的DialogFragment
 * 接口和功能基本等同于系统的AlertDialog

 */
public class AlertDialogFragment extends BaseDialogFragment implements IDialogShow {
    private static final String TAG = AlertDialogFragment.class.getSimpleName();
    private static final boolean DEBUG = false;

    public static AlertDialogFragment create(CharSequence title, CharSequence message) {
        Builder builder = new Builder();
        builder.setTitle(title).setMessage(message);
        return builder.create();
    }

    private Params mParams;

    private void setParams(@NonNull Params params) {
        mParams = params;
    }

    public AlertDialogFragment setTitle(CharSequence title) {
        mParams.mTitle = title;
        return this;
    }

    public AlertDialogFragment setMessage(CharSequence message) {
        mParams.mMessage = message;
        return this;
    }

    public AlertDialogFragment setIcon(int iconId) {
        mParams.mIconId = iconId;
        return this;
    }

    public AlertDialogFragment setIcon(Drawable icon) {
        mParams.mIcon = icon;
        return this;
    }

    public AlertDialogFragment setCancelButton(CharSequence text, final DialogInterface.OnClickListener listener) {
        mParams.mPositiveButtonText = text;
        mParams.mPositiveButtonListener = listener;
        return this;
    }

    public AlertDialogFragment setOkButton(CharSequence text, final DialogInterface.OnClickListener listener) {
        mParams.mNegativeButtonText = text;
        mParams.mNegativeButtonListener = listener;
        return this;
    }

    public AlertDialogFragment setNeutralButton(CharSequence text, final DialogInterface.OnClickListener listener) {
        mParams.mNeutralButtonText = text;
        mParams.mNeutralButtonListener = listener;
        return this;
    }

    public void setCancelable(boolean cancelable) {
        mParams.mCancelable = cancelable;
    }

    public AlertDialogFragment setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        mParams.mCanceledOnTouchOutside = canceledOnTouchOutside;
        return this;
    }

    public AlertDialogFragment setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        mParams.mOnCancelListener = onCancelListener;
        return this;
    }

    public AlertDialogFragment setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        mParams.mOnDismissListener = onDismissListener;
        return this;
    }

    public AlertDialogFragment setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
        mParams.mOnKeyListener = onKeyListener;
        return this;
    }

    public AlertDialogFragment setItems(CharSequence[] items, final DialogInterface.OnClickListener listener) {
        mParams.mItems = items;
        mParams.mOnClickListener = listener;
        return this;
    }

    public AlertDialogFragment setAdapter(final ListAdapter adapter, final DialogInterface.OnClickListener listener) {
        mParams.mAdapter = adapter;
        mParams.mOnClickListener = listener;
        return this;
    }

    public AlertDialogFragment setView(View view) {
        mParams.mView = view;
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.Theme_MaterialComponents_Light_Dialog_Alert);
        if (DEBUG) {
            Log.v(TAG, "onCreate()");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle bundle) {
        if (DEBUG) {
            Log.v(TAG, "onCreateDialog() mParams=" + mParams);
        }
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext(), getTheme());
        if (mParams.mIconId > 0) {
            builder.setIcon(mParams.mIconId);
        } else if (mParams.mIcon != null) {
            builder.setIcon(mParams.mIcon);
        }
        if (mParams.mTitle != null) {
            builder.setTitle(mParams.mTitle);
        }
        if (mParams.mMessage != null) {
            builder.setMessage(mParams.mMessage);
        }

        if (mParams.mPositiveButtonText != null) {
            builder.setPositiveButton(mParams.mPositiveButtonText, mParams.mPositiveButtonListener);
        }
        if (mParams.mNegativeButtonText != null) {
            builder.setNegativeButton(mParams.mNegativeButtonText, mParams.mNegativeButtonListener);
        }
        if (mParams.mNeutralButtonText != null) {
            builder.setNeutralButton(mParams.mNeutralButtonText, mParams.mNeutralButtonListener);
        }

        if (mParams.mCustomTitleView != null) {
            builder.setCustomTitle(mParams.mCustomTitleView);
        }

        if (mParams.mView != null) {
            builder.setView(mParams.mView);
        }

        if (mParams.mIsSingleChoice) {
            if (mParams.mItems != null) {
                builder.setSingleChoiceItems(mParams.mItems, mParams.mCheckedItem, mParams.mOnClickListener);
            } else if (mParams.mAdapter != null) {
                builder.setSingleChoiceItems(mParams.mAdapter, mParams.mCheckedItem, mParams.mOnClickListener);
            }
        } else if (mParams.mIsMultiChoice) {
            if (mParams.mItems != null) {
                builder.setMultiChoiceItems(mParams.mItems, mParams.mCheckedItems, mParams.mOnCheckboxClickListener);
            }
        } else {
            if (mParams.mItems != null) {
                builder.setItems(mParams.mItems, mParams.mOnClickListener);
            } else if (mParams.mAdapter != null) {
                builder.setAdapter(mParams.mAdapter, mParams.mOnClickListener);
            }
        }
        setCancelable(mParams.mCancelable);
        builder.setCancelable(mParams.mCancelable);
        if (mParams.mOnKeyListener != null) {
            builder.setOnKeyListener(mParams.mOnKeyListener);
        }

        // 如果当前没有设置动画效果，就设置一个默认的动画效果
        if (mParams.mAnimations == AnimAction.NO_ANIM) {
            switch (mParams.mGravity) {
                case Gravity.TOP:
                    mParams.mAnimations = AnimAction.TOP;
                    break;
                case Gravity.BOTTOM:
                    mParams.mAnimations = AnimAction.BOTTOM;
                    break;
                case Gravity.LEFT:
                    mParams.mAnimations = AnimAction.LEFT;
                    break;
                case Gravity.RIGHT:
                    mParams.mAnimations = AnimAction.RIGHT;
                    break;
                default:
                    mParams.mAnimations = AnimAction.DEFAULT;
                    break;
            }
        }

        AlertDialog dialog = builder.create();
        if (mParams.mWindowNoTitle) {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        dialog.setCanceledOnTouchOutside(mParams.mCanceledOnTouchOutside);
        Window windowDialog = dialog.getWindow();
        if (windowDialog != null) {
            windowDialog.setWindowAnimations(mParams.mAnimations);
        }
        if (mParams.mFullScreen) {
            // 设置宽度为屏宽、位置靠近屏幕底部
            Window window = dialog.getWindow();
            WindowManager.LayoutParams wlp;
            if (window != null) {
                window.setBackgroundDrawableResource(android.R.color.white);
                window.getDecorView().setPadding(0, 0, 0, 0);
                wlp = window.getAttributes();
            } else {
                wlp = new WindowManager.LayoutParams();
            }
            wlp.gravity = mParams.mGravity;
            wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
            wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            if (window != null) {
                window.setAttributes(wlp);
            }
        }
        return dialog;
    }

    public <T extends View> T findViewById(@IdRes int id) {
        Dialog dialog = getDialog();
        if (dialog != null) {
            return dialog.findViewById(id);
        }
        View view = getView();
        if (view != null) {
            return view.findViewById(id);
        }
        FragmentActivity activity = getActivity();
        if (activity != null) {
            return activity.findViewById(id);
        }
        return null;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (DEBUG) {
            Log.v(TAG, "onDismiss()");
        }
        if (mParams.mOnDismissListener != null) {
            mParams.mOnDismissListener.onDismiss(dialog);
        }
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        if (DEBUG) {
            Log.v(TAG, "onCancel()");
        }
        if (mParams.mOnCancelListener != null) {
            mParams.mOnCancelListener.onCancel(dialog);
        }
    }

    static class Params {
        // 主题
        int mTheme;
        // 是否隐藏标题栏
        boolean mWindowNoTitle;
        // 图标
        int mIconId;
        // 图标
        Drawable mIcon;
        // 标题
        CharSequence mTitle;
        // 自定义标题栏
        View mCustomTitleView;
        // 内容
        CharSequence mMessage;
        // 按钮
        CharSequence mPositiveButtonText;
        DialogInterface.OnClickListener mPositiveButtonListener;
        // 按钮
        CharSequence mNegativeButtonText;
        DialogInterface.OnClickListener mNegativeButtonListener;

        // 按钮
        CharSequence mNeutralButtonText;
        DialogInterface.OnClickListener mNeutralButtonListener;

        // 是否可以Cancel，默认为true
        boolean mCancelable = true;

        // 是否触摸对话框意外区域Cancel
        boolean mCanceledOnTouchOutside = true;

        // 几个Listener
        DialogInterface.OnCancelListener mOnCancelListener;
        DialogInterface.OnDismissListener mOnDismissListener;
        DialogInterface.OnKeyListener mOnKeyListener;

        // 列表数据源
        CharSequence[] mItems;
        ListAdapter mAdapter;
        boolean[] mCheckedItems;

        // 列表属性
        boolean mIsMultiChoice;
        boolean mIsSingleChoice;
        int mCheckedItem = -1;

        // 列表Listener
        DialogInterface.OnClickListener mOnClickListener;
        DialogInterface.OnMultiChoiceClickListener mOnCheckboxClickListener;
        AdapterView.OnItemSelectedListener mOnItemSelectedListener;

        // 自定义View
        View mView;

        int mGravity;
        boolean mFullScreen = false;

        int mAnimations;
        @NonNull
        @Override
        public String toString() {
            return "Params{" +
                    "mTheme=" + mTheme +
                    ", mWindowNoTitle=" + mWindowNoTitle +
                    ", mIconId=" + mIconId +
                    ", mIcon=" + mIcon +
                    ", mLeftText=" + mTitle +
                    ", mCustomTitleView=" + mCustomTitleView +
                    ", mMessage=" + mMessage +
                    ", mPositiveButtonText=" + mPositiveButtonText +
                    ", mPositiveButtonListener=" + mPositiveButtonListener +
                    ", mNegativeButtonText=" + mNegativeButtonText +
                    ", mNegativeButtonListener=" + mNegativeButtonListener +
                    ", mNeutralButtonText=" + mNeutralButtonText +
                    ", mNeutralButtonListener=" + mNeutralButtonListener +
                    ", mCancelable=" + mCancelable +
                    ", mCanceledOnTouchOutside=" + mCanceledOnTouchOutside +
                    ", mOnCancelListener=" + mOnCancelListener +
                    ", mOnDismissListener=" + mOnDismissListener +
                    ", mOnKeyListener=" + mOnKeyListener +
                    ", mItems=" + Arrays.toString(mItems) +
                    ", mAdapter=" + mAdapter +
                    ", mOnClickListener=" + mOnClickListener +
                    ", mView=" + mView +
                    '}';
        }
    }

    public static class Builder implements ContextAction {
        private Params mParams = new Params();

        public Builder(Builder builder) {
            mParams = builder.mParams;
        }

        public Builder() {
            this(0);
        }

        public Builder(int theme) {
            mParams.mTheme = theme;
        }

        public Builder setTheme(int mTheme) {
            this.mParams.mTheme = mTheme;
            return this;
        }

        public int getTheme() {
            return mParams.mTheme;
        }

        public Builder setWindowNoTitle(boolean noTitle) {
            this.mParams.mWindowNoTitle = noTitle;
            return this;
        }

        public boolean isWindowNoTitle() {
            return mParams.mWindowNoTitle;
        }

        public Builder setTitle(int titleId) {
            mParams.mTitle = getResources().getString(titleId);
            return this;
        }

        public Builder setTitle(CharSequence title) {
            mParams.mTitle = title;
            return this;
        }

        public Builder setCustomTitle(View customTitleView) {
            mParams.mCustomTitleView = customTitleView;
            return this;
        }

        public Builder setMessage(int messageId) {
            mParams.mMessage = getResources().getString(messageId);
            return this;
        }

        public Builder setMessage(CharSequence message) {
            mParams.mMessage = message;
            return this;
        }

        public Builder setIcon(int iconId) {
            mParams.mIconId = iconId;
            return this;
        }

        public Builder setIcon(Drawable icon) {
            mParams.mIcon = icon;
            return this;
        }

        public Builder setOkButton(int textId, final DialogInterface.OnClickListener listener) {
            mParams.mPositiveButtonText = getResources().getString(textId);
            mParams.mPositiveButtonListener = listener;
            return this;
        }

        public Builder setOkButton(CharSequence text, final DialogInterface.OnClickListener listener) {
            mParams.mPositiveButtonText = text;
            mParams.mPositiveButtonListener = listener;
            return this;
        }

        public Builder setCancelButton(int textId, final DialogInterface.OnClickListener listener) {
            mParams.mNegativeButtonText = getResources().getString(textId);
            mParams.mNegativeButtonListener = listener;
            return this;
        }

        public Builder setCancelButton(CharSequence text, final DialogInterface.OnClickListener listener) {
            mParams.mNegativeButtonText = text;
            mParams.mNegativeButtonListener = listener;
            return this;
        }

        public Builder setNeutralButton(int textId, final DialogInterface.OnClickListener listener) {
            mParams.mNeutralButtonText = getResources().getString(textId);
            mParams.mNeutralButtonListener = listener;
            return this;
        }

        public Builder setNeutralButton(CharSequence text, final DialogInterface.OnClickListener listener) {
            mParams.mNeutralButtonText = text;
            mParams.mNeutralButtonListener = listener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            mParams.mCancelable = cancelable;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            mParams.mCanceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public Builder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            mParams.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            mParams.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
            mParams.mOnKeyListener = onKeyListener;
            return this;
        }

        public Builder setItems(int itemsId, final DialogInterface.OnClickListener listener) {
            mParams.mItems = getResources().getTextArray(itemsId);
            mParams.mOnClickListener = listener;
            return this;
        }

        public Builder setItems(CharSequence[] items, final DialogInterface.OnClickListener listener) {
            mParams.mItems = items;
            mParams.mOnClickListener = listener;
            return this;
        }

        public Builder setAdapter(final ListAdapter adapter, final DialogInterface.OnClickListener listener) {
            mParams.mAdapter = adapter;
            mParams.mOnClickListener = listener;
            return this;
        }

        public Builder setMultiChoiceItems(int itemsId, boolean[] checkedItems,
                                           final DialogInterface.OnMultiChoiceClickListener listener) {
            mParams.mItems = getResources().getTextArray(itemsId);
            mParams.mOnCheckboxClickListener = listener;
            mParams.mCheckedItems = checkedItems;
            mParams.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems,
                                           final DialogInterface.OnMultiChoiceClickListener listener) {
            mParams.mItems = items;
            mParams.mOnCheckboxClickListener = listener;
            mParams.mCheckedItems = checkedItems;
            mParams.mIsMultiChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(int itemsId, int checkedItem,
                                            final DialogInterface.OnClickListener listener) {
            mParams.mItems = getResources().getTextArray(itemsId);
            mParams.mOnClickListener = listener;
            mParams.mCheckedItem = checkedItem;
            mParams.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(CharSequence[] items, int checkedItem, final DialogInterface.OnClickListener listener) {
            mParams.mItems = items;
            mParams.mOnClickListener = listener;
            mParams.mCheckedItem = checkedItem;
            mParams.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(ListAdapter adapter, int checkedItem, final DialogInterface.OnClickListener listener) {
            mParams.mAdapter = adapter;
            mParams.mOnClickListener = listener;
            mParams.mCheckedItem = checkedItem;
            mParams.mIsSingleChoice = true;
            return this;
        }

        public Builder setOnItemSelectedListener(final AdapterView.OnItemSelectedListener listener) {
            mParams.mOnItemSelectedListener = listener;
            return this;
        }

        public Builder setView(View view) {
            mParams.mView = view;
            return this;
        }

        public Builder setGravity(int gravity) {
            mParams.mGravity = gravity;
            return this;
        }

        public Builder fromBottom() {
            mParams.mGravity = Gravity.BOTTOM;
            return this;
        }

        public Builder fromTop() {
            mParams.mGravity = Gravity.TOP;
            return this;
        }

        public Builder fullScreen(boolean fullScreen) {
            mParams.mFullScreen = fullScreen;
            return this;
        }

        public Builder fullScreen() {
            mParams.mFullScreen = true;
            return this;
        }

        /**
         * 设置动画，已经封装好几种样式，具体可见{@link AnimAction}类
         */
        public Builder animStyle(@StyleRes int id) {
            mParams.mAnimations = id;
            return this;
        }

        public AlertDialogFragment create() {
            AlertDialogFragment fragment = new AlertDialogFragment();
            fragment.setParams(mParams);
            return fragment;
        }

    }

}

package com.cjf.ui.bounce;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import com.cjf.ui.R;
import com.cjf.ui.base.ContextAction;

import androidx.appcompat.widget.LinearLayoutCompat;

/**
 * <p>Title: BounceAction </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/9 17:44
 */
public interface BounceAction extends ContextAction,Bounce {

    BounceDelegate getBounceDelegate();

    default void setTargetView(View targetView) {
        getBounceDelegate().setTargetView(targetView);
    }

    default void setResistance(float resistance) {
        getBounceDelegate().setResistance(resistance);
    }

    default void setOrientation(int orientation) {
        getBounceDelegate().setOrientation(orientation);
    }

    default void setDuration(long duration) {
        getBounceDelegate().setDuration(duration);
    }

    default void setInterpolator(Interpolator interpolator) {
        getBounceDelegate().setInterpolator(interpolator);
    }

    default void setNeedReset(boolean needReset) {
        getBounceDelegate().setNeedReset(needReset);
    }

    default void setResetDistance(int resetDistance) {
        getBounceDelegate().setResetDistance(resetDistance);
    }

    default void setOnBounceDistanceChangeListener(OnBounceDistanceChangeListener onBounceDistanceChangeListener) {
        getBounceDelegate().setOnBounceDistanceChangeListener(onBounceDistanceChangeListener);
    }

    default void setBounceType(int bounceType){
        getBounceDelegate().setBounceType(bounceType);
    }

    default void disableBounce() {
        setBounceType(BounceDelegate.BOUNCE_TYPE_DISABLE);
    }

    default void openBothHorizontal() {
        setBounceType(BounceDelegate.BOUNCE_TYPE_BOTH);
        setOrientation(LinearLayoutCompat.HORIZONTAL);
    }

    default void openBothVertical() {
        setBounceType(BounceDelegate.BOUNCE_TYPE_BOTH);
        setOrientation(LinearLayoutCompat.VERTICAL);
    }

    default void openTop() {
        setBounceType(BounceDelegate.BOUNCE_TYPE_TOP);
        setOrientation(LinearLayoutCompat.VERTICAL);
    }

    default void openBottom() {
        setBounceType(BounceDelegate.BOUNCE_TYPE_BOTTOM);
        setOrientation(LinearLayoutCompat.VERTICAL);
    }

    default void openLeft() {
        setBounceType(BounceDelegate.BOUNCE_TYPE_LEFT);
        setOrientation(LinearLayoutCompat.HORIZONTAL);
    }

    default void openRight() {
        setBounceType(BounceDelegate.BOUNCE_TYPE_RIGHT);
        setOrientation(LinearLayoutCompat.HORIZONTAL);
    }

    default void loadFromBounceAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BounceAction, defStyleAttr, 0);
        try {
            int orientation = array.getInt(R.styleable.BounceAction_BounceOrientation, LinearLayout.HORIZONTAL);
            float resistance = array.getFloat(R.styleable.BounceAction_BounceResistance, 3f);
            int duration = array.getInteger(R.styleable.BounceAction_BounceDuration, 300);
            if (resistance < 1) {
                resistance = 1f;
            }
            setOrientation(orientation);
            setDuration(duration);
            setResistance(resistance);
        } finally {
            array.recycle();
        }
    }

}

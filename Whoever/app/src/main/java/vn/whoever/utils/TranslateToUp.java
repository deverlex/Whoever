package vn.whoever.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by spider man on 1/17/2016.
 */
public class TranslateToUp extends TranslateAnimation implements Animation.AnimationListener {

    private LinearLayout panelLayout;
    private int panelHeight;

    public TranslateToUp(LinearLayout layout, int panelHeight, int fromXType, float fromXValue, int toXType, float toXValue,
                         int fromYType, float fromYValue, int toYType, float toYValue) {
        super(fromXType, fromXValue, toXType, toXValue, fromYType, fromYValue, toYType, toYValue);
        this.panelLayout = layout;
        this.panelHeight = panelHeight;

        setDuration(1000);
        setFillAfter(true);
        setInterpolator(new AccelerateDecelerateInterpolator());
        setAnimationListener(this);
        this.panelLayout.startAnimation(this);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) panelLayout.getLayoutParams();
        params.bottomMargin = -panelHeight;
        //params.gravity = Gravity.BOTTOM;
        panelLayout.clearAnimation();
        panelLayout.setLayoutParams(params);
        panelLayout.requestLayout();
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) panelLayout.getLayoutParams();
        params.bottomMargin = 0;//panelHeight;
        //params.gravity = Gravity.BOTTOM;
        panelLayout.clearAnimation();
        panelLayout.setLayoutParams(params);
        panelLayout.requestLayout();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}



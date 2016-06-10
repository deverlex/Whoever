package vn.whoever.utils;

import android.view.Gravity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by Nguyen Van Do on 12/27/2015.
 * TODO: translate layout from LEFT to RIGHT
 */
public class TranslateToRight extends TranslateAnimation
        implements Animation.AnimationListener {

    private LinearLayout slidingLayout;
    private int panelWidth;

    public TranslateToRight(LinearLayout layout, int width,
                            int fromXType, float fromXValue,
                            int toXType, float toXValue,
                            int fromYType, float fromYValue,
                            int toYType, float toYValue) {
        super(fromXType, fromXValue, toXType, toXValue, fromYType, fromYValue, toYType, toYValue);

        this.slidingLayout = layout;
        this.panelWidth = width;

        // Set time handling translate is 300 ms
        setDuration(300);
        setFillAfter(false);
        setInterpolator(new AccelerateDecelerateInterpolator());
        setAnimationListener(this);
        this.slidingLayout.startAnimation(this);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        /**
         * TODO: when end animation process, set params for layout
         */
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) slidingLayout.getLayoutParams();
        params.leftMargin = panelWidth;
        params.gravity = Gravity.LEFT;
        slidingLayout.clearAnimation();
        slidingLayout.setLayoutParams(params);
        slidingLayout.requestLayout();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {}

    @Override
    public void onAnimationStart(Animation animation) {}
}

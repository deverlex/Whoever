package vn.whoever.utils;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by spider man on 12/27/2015.
 */
public class CloseSliding extends TranslateAnimation
        implements Animation.AnimationListener {

    private LinearLayout slidingLayout;
    private int panelWidth;

    public CloseSliding(LinearLayout layout, int width,
                        int fromXType, float fromXValue,
                        int toXType, float toXValue,
                        int fromYType, float fromYValue,
                        int toYType, float toYValue) {
        super(fromXType, fromXValue, toXType, toXValue, fromYType, fromYValue, toYType, toYValue);

        slidingLayout = layout;
        panelWidth = width;

        setDuration(300);
        setFillAfter(false);
        setInterpolator(new AccelerateDecelerateInterpolator());
        setAnimationListener(this);

        /**
         * TODO: clean layout
         */
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) slidingLayout.getLayoutParams();
        params.rightMargin = 0;
        params.leftMargin = 0;
        slidingLayout.setLayoutParams(params);
        slidingLayout.requestLayout();
        slidingLayout.startAnimation(this);
    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }
}

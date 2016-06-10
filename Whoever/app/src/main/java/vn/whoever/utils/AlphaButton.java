package vn.whoever.utils;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;

/**
 * Created by Nguyen Van Do on 1/16/2016.
 * Animation for button
 */
public class AlphaButton extends AlphaAnimation
        implements Animation.AnimationListener {

    private FloatingActionButton imageButton;

    public AlphaButton(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public AlphaButton(FloatingActionButton imageButton, float fromAlpha, float toAlpha) {
        super(fromAlpha, toAlpha);
        this.imageButton = imageButton;
        setDuration(300);
        setFillAfter(false);
        setInterpolator(new AccelerateDecelerateInterpolator());
        setAnimationListener(this);
        this.imageButton.startAnimation(this);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (imageButton.getVisibility() == View.VISIBLE) {
            imageButton.setVisibility(View.GONE);
        } else if (imageButton.getVisibility() != View.VISIBLE) {
            imageButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {}

    @Override
    public void onAnimationStart(Animation animation) {}
}

package vn.whoever.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by spider man on 1/17/2016.
 */
public class RotatePanel extends RotateAnimation implements Animation.AnimationListener {

    private RelativeLayout layout;

    public RotatePanel(RelativeLayout layout, float fromDegrees, float toDegrees, float pivotX, float pivotY) {

        super(fromDegrees, toDegrees, pivotX, pivotY);
        this.layout = layout;
    }

    public RotatePanel(RelativeLayout layout, float fromDegrees, float toDegrees, int pivotXType,
                       float pivotXValue, int pivotYType, float pivotYValue) {

        super(fromDegrees, toDegrees, pivotXType, pivotXValue, pivotYType, pivotYValue);
        this.layout = layout;

        setDuration(500);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}



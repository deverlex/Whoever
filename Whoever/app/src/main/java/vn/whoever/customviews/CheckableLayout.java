package vn.whoever.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spider man on 2/29/2016.
 */
public class CheckableLayout extends RelativeLayout implements Checkable {

    private boolean isChecked;
    private List<Checkable> checkableViews;

    public CheckableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise(attrs);
    }

    public CheckableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialise(attrs);
    }

    public CheckableLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialise(attrs);
    }

    public CheckableLayout(Context context, int checkableId) {
        super(context);
        initialise(null);
    }

    @Override
    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
        for(Checkable ck : checkableViews) {
            ck.setChecked(isChecked);
        }
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        this.isChecked = !this.isChecked;
        for(Checkable ck : checkableViews) {
            ck.toggle();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        final int childCount = this.getChildCount();
        for(int i = 0; i < childCount; ++i) {
            findCheckableChildren(this.getChildAt(i));
        }
    }

    private void initialise(AttributeSet attr) {
        this.isChecked = false;
        this.checkableViews = new ArrayList<Checkable>(5);
    }

    private void findCheckableChildren(View view) {
        if(view instanceof Checkable) {
            this.checkableViews.add((Checkable) view);
        }

        if(view instanceof ViewGroup) {
            final ViewGroup viewGroup = (ViewGroup) view;
            final int childCount = viewGroup.getChildCount();

            for (int i = 0; i < childCount; ++i) {
                findCheckableChildren(viewGroup.getChildAt(i));
            }
        }
    }
}

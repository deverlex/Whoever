package vn.whoever.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import vn.whoever.R;
import vn.whoever.adapters.StatusAdapter;
import vn.whoever.utils.ConvertSizeDisplay;
import vn.whoever.utils.Initgc;
import vn.whoever.utils.TranslateToDown;
import vn.whoever.utils.TranslateToUp;

/**
 * Created by spider man on 12/28/2015.
 */
public class NewsFeedFragment extends Fragment implements Initgc {

    private LinearLayout toolbar;
    private ListView listStatus;
    private FloatingActionButton btnFilter;
    private StatusAdapter statusAdapter;

    private boolean isHideToolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newsfeed_layout, null);
        init(view);
        initListener(view);
        return view;
    }

    @Override
    public void init(View view) {
        btnFilter = (FloatingActionButton) view.findViewById(R.id.btnSettingFilterNewsFeed);
        toolbar = (LinearLayout) view.findViewById(R.id.layoutHeaderNewsFeed);
        listStatus = (ListView) view.findViewById(R.id.listViewNewsFeed);
        statusAdapter = new StatusAdapter(getActivity());
        listStatus.setAdapter(statusAdapter);
    }

    @Override
    public void initListener(View view) {
        final GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onDown(MotionEvent event) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                final int SWIPE_MIN_DISTANCE = 40;
                final int SWIPE_MAX_OFF_PATH = 150;
                final int SWIPE_THRESHOLD_VELOCITY = 150;

                try {
                    if(Math.abs(e1.getX() - e2.getX()) > SWIPE_MAX_OFF_PATH) {
                        return false;
                    }
                    if((e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY)) {
                        // up && hide toolbar && show filter
                        if(!isHideToolbar) {
                            isHideToolbar = true;
                            toolbar.setVisibility(View.GONE);
                        }

                    } else if((e2.getY() - e1.getY()) > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY){
                        // down && show toolbar && hide filter
                        if(isHideToolbar) {
                            isHideToolbar = false;
                            toolbar.setVisibility(View.VISIBLE);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });

        listStatus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return false;
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        System.gc();
    }

    @Override
    public void initGc() {

    }
}

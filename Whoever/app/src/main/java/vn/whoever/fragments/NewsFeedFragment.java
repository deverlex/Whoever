package vn.whoever.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import vn.whoever.R;
import vn.whoever.activities.PostStatusActivity;
import vn.whoever.activities.ProifileActivity;
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
    private Intent intentNav;

    /**
     * TODO: for toolbar
     */

    private RelativeLayout btnWriteStatus;
    private LinearLayout btnChoiceWriteStatus;
    private LinearLayout btnChoiceUpPhoto;
    private ImageButton avatarInToolbar;

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
        intentNav = new Intent();

        btnFilter = (FloatingActionButton) view.findViewById(R.id.btnSettingFilterNewsFeed);
        toolbar = (LinearLayout) view.findViewById(R.id.layoutToolBarWriteNewsFeed);
        listStatus = (ListView) view.findViewById(R.id.listViewNewsFeed);
        statusAdapter = new StatusAdapter(getActivity());
        listStatus.setAdapter(statusAdapter);

        /**
         * TODO: for toobar layout
         */
        avatarInToolbar = (ImageButton) toolbar.findViewById(R.id.btnAvatarInWriteStatus);
        btnChoiceWriteStatus = (LinearLayout) toolbar.findViewById(R.id.btnChoiceWriteStatus);
        btnChoiceUpPhoto = (LinearLayout) toolbar.findViewById(R.id.btnChoiceUploadPhoto);
        btnWriteStatus = (RelativeLayout) toolbar.findViewById(R.id.btnWriteStatusInWriteStatus);

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

        listStatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        listStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /**
         * TODO: initListener() for toolbar
         *
         */

        avatarInToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // navigate to profile layout
                intentNav.setClass(getContext(), ProifileActivity.class);
                startActivity(intentNav);
            }
        });

        btnChoiceWriteStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentNav.setClass(getContext(), PostStatusActivity.class);
                startActivity(intentNav);
            }
        });

        btnChoiceUpPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentNav.setClass(getContext(), PostStatusActivity.class);
                startActivity(intentNav);
            }
        });

        btnWriteStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentNav.setClass(getContext(), PostStatusActivity.class);
                startActivity(intentNav);
            }
        });

        /**
         * TODO: filter
         */

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show a dialog
            }
        });

        Log.d("Init()", "reInit()");
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume()", "Resume() hihihih");
        intentNav = new Intent();
    }

    @Override
    public void initGc() {

    }
}

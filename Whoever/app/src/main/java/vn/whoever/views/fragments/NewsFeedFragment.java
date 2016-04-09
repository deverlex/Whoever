package vn.whoever.views.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import vn.whoever.R;
import vn.whoever.views.activities.MainActivity;
import vn.whoever.adapters.StatusAdapter;
import vn.whoever.utils.Initgc;

/**
 * Created by spider man on 12/28/2015.
 */
public class NewsFeedFragment extends Fragment implements Initgc, AbsListView.OnScrollListener {

    private LinearLayout toolbar;
    private ListView listStatus;
    private FloatingActionButton btnFilter;
    private StatusAdapter statusAdapter;

    private ProgressBar progressBarLoadMore;
    private Handler mHandler;

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
        mHandler = new Handler();

        btnFilter = (FloatingActionButton) view.findViewById(R.id.btnSettingFilterNewsFeed);
        toolbar = (LinearLayout) view.findViewById(R.id.layoutToolBarWriteNewsFeed);

        View footer = getActivity().getLayoutInflater().inflate(R.layout.progress_bar_footer, null);
        progressBarLoadMore = (ProgressBar) footer.findViewById(R.id.progressBarLoad);
        progressBarLoadMore.getIndeterminateDrawable().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);

        listStatus = (ListView) view.findViewById(R.id.listViewNewsFeed);
        listStatus.addFooterView(footer);


        statusAdapter = new StatusAdapter(getActivity(), 10, 7);
        listStatus.setAdapter(statusAdapter);
        listStatus.setOnScrollListener(this);
        progressBarLoadMore.setVisibility((7 < statusAdapter.getSize()) ? View.VISIBLE : View.GONE);

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
                MainActivity.frgTransactionMain = MainActivity.frgtManagerMain.beginTransaction();
                MainActivity.frgTransactionMain.replace(R.id.mainFrame, new ProfileFragment()).addToBackStack("profileFrame").commit();
            }
        });

        btnChoiceWriteStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.frgTransactionMain = MainActivity.frgtManagerMain.beginTransaction();
                MainActivity.frgTransactionMain.replace(R.id.mainFrame, new PostStatusFragment()).addToBackStack("postStatus").commit();
            }
        });

        btnChoiceUpPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.frgTransactionMain = MainActivity.frgtManagerMain.beginTransaction();
                MainActivity.frgTransactionMain.replace(R.id.mainFrame, new PostStatusFragment()).addToBackStack("postStatus").commit();
            }
        });

        btnWriteStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.frgTransactionMain = MainActivity.frgtManagerMain.beginTransaction();
                MainActivity.frgTransactionMain.replace(R.id.mainFrame, new PostStatusFragment()).addToBackStack("postStatus").commit();
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
    }

    @Override
    public void onResume() {
        super.onResume();
        intentNav = new Intent();
    }

    @Override
    public void initGc() {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(firstVisibleItem + visibleItemCount == totalItemCount && !statusAdapter.endReached() && !hasCallback) {
            mHandler.postDelayed(showMore, 1200);
            hasCallback = true;
        }
    }

    private boolean hasCallback;

    private Runnable showMore = new Runnable() {
        @Override
        public void run() {
            boolean noMoreToShow = statusAdapter.showMore();
            progressBarLoadMore.setVisibility(noMoreToShow? View.GONE : View.VISIBLE);
            hasCallback = false;
        }
    };
}

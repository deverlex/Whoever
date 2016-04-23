package vn.whoever.views.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import vn.whoever.R;
import vn.whoever.adapters.DataAdapter;
import vn.whoever.adapters.OnLoadMoreListener;
import vn.whoever.models.Status;
import vn.whoever.models.dao.ConnDB;
import vn.whoever.adapters.StatusAdapter;
import vn.whoever.utils.Initgc;

/**
 * Created by spider man on 12/28/2015.
 */

public class NewsFeedFragment extends Fragment implements Initgc, SwipeRefreshLayout.OnRefreshListener {

    private LinearLayout toolbar;
    private RecyclerView recyclerViewStatus;
    private FloatingActionButton btnFilter;
    private StatusAdapter statusAdapter;

    //private ProgressBar progressBarLoadMore;
    protected Handler mHandler;

    private boolean isHideToolbar = false;

    private RelativeLayout btnWriteStatus;
    private LinearLayout btnChoiceWriteStatus;
    private LinearLayout btnChoiceUpPhoto;
    private ImageButton avatarInToolbar;

    private LinearLayoutManager linearLayoutManager;

    private SwipeRefreshLayout newsRefreshLayout;

    private List<Status> statusList;
    private DataAdapter dataAdapter;

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
        //mHandler = new Handler();

        btnFilter = (FloatingActionButton) view.findViewById(R.id.btnSettingFilterNewsFeed);
        toolbar = (LinearLayout) view.findViewById(R.id.layoutToolBarWriteNewsFeed);

        //View footer = getActivity().getLayoutInflater().inflate(R.layout.progress_bar_footer, null);
        //progressBarLoadMore = (ProgressBar) footer.findViewById(R.id.progressBarLoad);
        //progressBarLoadMore.getIndeterminateDrawable().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);

        recyclerViewStatus = (RecyclerView) view.findViewById(R.id.listViewNewsFeed);
        //recyclerViewStatus.addFooterView(footer);


        //statusAdapter = new StatusAdapter(this, 10, 7, "News");
        //recyclerViewStatus.setAdapter(statusAdapter);
        //recyclerViewStatus.setOnScrollListener(this);
        //progressBarLoadMore.setVisibility((7 < statusAdapter.getSize()) ? View.VISIBLE : View.GONE);

        newsRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshListNewsLayout);
        newsRefreshLayout.setOnRefreshListener(this);
        newsRefreshLayout.setColorSchemeColors(R.color.colorAccent);

        /**
         * TODO: for toobar layout
         */
        avatarInToolbar = (ImageButton) toolbar.findViewById(R.id.btnAvatarInWriteStatus);
        btnChoiceWriteStatus = (LinearLayout) toolbar.findViewById(R.id.btnChoiceWriteStatus);
        btnChoiceUpPhoto = (LinearLayout) toolbar.findViewById(R.id.btnChoiceUploadPhoto);
        btnWriteStatus = (RelativeLayout) toolbar.findViewById(R.id.btnWriteStatusInWriteStatus);


        statusList = new ArrayList<>();
        mHandler = new Handler();
        loadData();

        recyclerViewStatus.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerViewStatus.setLayoutManager(linearLayoutManager);
        dataAdapter = new DataAdapter(this, statusList, recyclerViewStatus);

        recyclerViewStatus.setAdapter(dataAdapter);

        if(statusList.isEmpty()) {
            recyclerViewStatus.setVisibility(View.GONE);
        } else {
            recyclerViewStatus.setVisibility(View.VISIBLE);
        }

        dataAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                statusList.add(null);
                dataAdapter.notifyItemInserted(statusList.size() - 1);

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        statusList.remove(statusList.size() - 1);
                        dataAdapter.notifyItemRemoved(statusList.size());

                        int start = statusList.size();
                        int end = start + 10;

                        //load in here


                        dataAdapter.setLoaded();



                    }
                }, 2000);

            }
        });
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
                } catch (Exception e) {}
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });

        recyclerViewStatus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return false;
            }
        });

        avatarInToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateFragment(new ProfileFragment(), "newsFrameToprofile");
            }
        });

        btnChoiceWriteStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateFragment(new PostStatusFragment(), "newsFrameToPostStatus");
            }
        });

        btnChoiceUpPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateFragment(new PostStatusFragment(), "newsFrameToPostPhoto");
            }
        });

        btnWriteStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateFragment(new PostStatusFragment(), "newsFrameToPostStatus");
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show a dialog
            }
        });

        newsRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                newsRefreshLayout.setRefreshing(true);

                fetchStatus();
            }
        });
    }

    private void fetchStatus() {
        newsRefreshLayout.setRefreshing(true);
        Log.d("GetMore", "Item Status more");
        newsRefreshLayout.setRefreshing(false);
    }

    public void navigateFragment(Fragment fragment, String strStack) {
        getParentFragment().getChildFragmentManager().beginTransaction()
                .replace(R.id.majorFrame, fragment).addToBackStack(strStack).commit();
        getParentFragment().getChildFragmentManager().executePendingTransactions();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void initGc() {

    }

    private void loadData() {
        statusList = new ArrayList<>();
        SQLiteDatabase db = ConnDB.getConn().getReadableDatabase();
        String arg[] = {String.valueOf(0)};
        Cursor cursor = db.rawQuery("select id, idStatus, ssoIdPoster, avatarPoster, namePoster," +
                " timePost, contentText, contentImage, totalLike, totalDislike, totalComment," +
                " interact from News where id >=?", arg);
        while (cursor.moveToNext()) {
            Status status = new Status();
            status.setId(cursor.getInt(0));
            status.setIdStatus(cursor.getString(1));
            status.setSsoIdPoster(cursor.getString(2));
            status.setAvatarPoster(cursor.getString(3));
            status.setNamePoster(cursor.getString(4));
            status.setTimePost(cursor.getString(5));
            status.setContentText(cursor.getString(6));
            status.setContentImage(cursor.getString(7));
            status.setTotalLike(cursor.getInt(8));
            status.setTotalDislike(cursor.getInt(9));
            status.setTotalComment(cursor.getInt(10));
            status.setInteract(cursor.getString(11));
            statusList.add(status);
        }
        cursor.close();
        db.close();
    }

//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//    }
//
//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        if(firstVisibleItem + visibleItemCount == totalItemCount && !statusAdapter.endReached() && !hasCallback) {
//            mHandler.postDelayed(showMore, 1200);
//            hasCallback = true;
//        }
//    }

//    private boolean hasCallback;
//
//    private Runnable showMore = new Runnable() {
//        @Override
//        public void run() {
//            boolean noMoreToShow = statusAdapter.showMore();
//            progressBarLoadMore.setVisibility(noMoreToShow? View.GONE : View.VISIBLE);
//            hasCallback = false;
//        }
//    };

    @Override
    public void onRefresh() {
        fetchStatus();
    }
}

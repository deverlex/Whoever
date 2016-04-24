package vn.whoever.views.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
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
import vn.whoever.adapters.StatusAdapter;
import vn.whoever.adapters.OnLoadMoreListener;
import vn.whoever.models.Status;
import vn.whoever.models.dao.ConnDB;
import vn.whoever.utils.Initgc;

/**
 * Created by spider man on 12/28/2015.
 */

public class NewsFeedFragment extends Fragment implements Initgc, SwipeRefreshLayout.OnRefreshListener {

    private LinearLayout toolbar;
    private RecyclerView recyclerViewStatus;
    private FloatingActionButton btnFilter;
    protected Handler mHandler;

    private boolean isHideToolbar = false;

    private RelativeLayout btnWriteStatus;
    private LinearLayout btnChoiceWriteStatus;
    private LinearLayout btnChoiceUpPhoto;
    private ImageButton avatarInToolbar;

    private LinearLayoutManager linearLayoutManager;

    private SwipeRefreshLayout newsRefreshLayout;

    private List<Status> statusList;
    private StatusAdapter statusAdapter;

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
        toolbar = (LinearLayout) view.findViewById(R.id.layoutToolBarWriteNewsFeed);
        recyclerViewStatus = (RecyclerView) view.findViewById(R.id.listViewNewsFeed);

        newsRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshListNewsLayout);
        newsRefreshLayout.setOnRefreshListener(this);
        newsRefreshLayout.setColorSchemeResources(R.color.colorMain);

        avatarInToolbar = (ImageButton) toolbar.findViewById(R.id.btnAvatarInWriteStatus);
        btnChoiceWriteStatus = (LinearLayout) toolbar.findViewById(R.id.btnChoiceWriteStatus);
        btnChoiceUpPhoto = (LinearLayout) toolbar.findViewById(R.id.btnChoiceUploadPhoto);
        btnWriteStatus = (RelativeLayout) toolbar.findViewById(R.id.btnWriteStatusInWriteStatus);

        mHandler = new Handler();
        loadData();

        recyclerViewStatus.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerViewStatus.setLayoutManager(linearLayoutManager);
        statusAdapter = new StatusAdapter(this, statusList, recyclerViewStatus);

        recyclerViewStatus.setAdapter(statusAdapter);

        if(statusList.isEmpty()) {
            recyclerViewStatus.setVisibility(View.GONE);
        } else {
            recyclerViewStatus.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initListener(View view) {

        statusAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                statusList.add(null);
                statusAdapter.notifyItemInserted(statusList.size() - 1);

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        statusList.remove(statusList.size() - 1);
                        statusAdapter.notifyItemRemoved(statusList.size());
                        fetchStatus();
                        statusAdapter.setLoaded();
                    }
                }, 2000);

            }
        });

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
                            btnFilter.show();
                        }
                    } else if((e2.getY() - e1.getY()) > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY){
                        // down && show toolbar && hide filter
                        if(isHideToolbar) {
                            isHideToolbar = false;
                            toolbar.setVisibility(View.VISIBLE);
                            btnFilter.hide();
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
       // db.close();
    }

    @Override
    public void onRefresh() {
        fetchStatus();
    }
}

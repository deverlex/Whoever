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
import vn.whoever.TransConnection.HttpStatus;
import vn.whoever.TransConnection.StatusTransaction;
import vn.whoever.adapters.StatusAdapter;
import vn.whoever.adapters.OnLoadMoreListener;
import vn.whoever.models.Status;
import vn.whoever.models.dao.ConnDB;
import vn.whoever.utils.AlphaButton;
import vn.whoever.utils.Initgc;
import vn.whoever.views.dialogs.DialogViewNews;

/**
 * Created by Nguyen Van Do on 12/28/2015.
 * This class implement news feed layout.
 */
public class NewsFeedFragment extends Fragment implements Initgc, SwipeRefreshLayout.OnRefreshListener {

    private LinearLayout toolbar;
    private RecyclerView recyclerViewStatus;
    private FloatingActionButton btnFilter;
    protected Handler mHandler;
    protected Handler pHandler;
    private boolean isHideToolbar = false;

    private RelativeLayout btnWriteStatus;
    private LinearLayout btnChoiceWriteStatus;
    private LinearLayout btnChoiceUpPhoto;
    private ImageButton avatarInToolbar;
    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout newsRefreshLayout;

    private List<Status> statusList;
    private StatusAdapter statusAdapter;
    private StatusTransaction statusTransaction;
    private boolean onCreate = false;
    private DialogViewNews dialogViewNews;
    private String order = "nearby";
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
        dialogViewNews = new DialogViewNews(getThis());
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
        pHandler = new Handler();
        loadData();
        statusTransaction = new StatusTransaction(getActivity());

        recyclerViewStatus.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerViewStatus.setLayoutManager(linearLayoutManager);
        statusAdapter = new StatusAdapter(this, statusList, recyclerViewStatus);
        recyclerViewStatus.setAdapter(statusAdapter);
        onCreate = true;
    }

    @Override
    public void initListener(View view) {
        statusAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                statusAdapter.addItem(null);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadMoreStatus();
                    }
                }, 2000);
            }
        });
        // Show/Hide tab bar -> write new status
        final GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onDown(MotionEvent event) {
                return true;
            }
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                final int SWIPE_MIN_DISTANCE = 30;
                final int SWIPE_MAX_OFF_PATH = 150;
                final int SWIPE_THRESHOLD_VELOCITY = 150;
                try {
                    if(Math.abs(e1.getX() - e2.getX()) > SWIPE_MAX_OFF_PATH) {
                        return false;
                    }
                    if((e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY)) {
                        if(!isHideToolbar) {
                            isHideToolbar = true;
                            toolbar.setVisibility(View.GONE);
                            btnFilter.show();
                        }
                    } else if((e2.getY() - e1.getY()) > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY){
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
                dialogViewNews.show(getActivity().getFragmentManager(), "Choice View News");
            }
        });

        newsRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                fetchStatus();
            }
        });
    }

    public NewsFeedFragment getThis() {
        return this;
    }

    int loop = 0;

    public void fetchStatus() {
        loop = 0;
        order = dialogViewNews.getSetView();
        newsRefreshLayout.setRefreshing(true);
        SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
        db.execSQL("delete from Status");
        statusTransaction.getNewsFeed(order, 0);
        (new Thread(new Runnable() {
            @Override
            public void run() {
                while (loop < 15) {
                    pHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Integer httpCode = statusTransaction.getHttpStatusCode();
                            if(httpCode != null && httpCode == HttpStatus.SC_CREATED) {
                                loadData();
                                if(statusList.size() > 0) {
                                    statusAdapter.swapData(statusList);
                                    newsRefreshLayout.setRefreshing(false);
                                    loop = 15;
                                }
                            }
                            ++loop;
                            if(loop >= 15) newsRefreshLayout.setRefreshing(false);
                        }
                    });
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {}
                }
            }
        })).start();
    }

    public void loadMoreStatus() {
        loop = 0;
        order = dialogViewNews.getSetView();
        statusTransaction.getNewsFeed(order, statusAdapter.getItemCount() - 1);
        (new Thread(new Runnable() {
            @Override
            public void run() {
                while (loop < 15) {
                    pHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Integer httpCode = statusTransaction.getHttpStatusCode();
                            if(httpCode != null && httpCode == HttpStatus.SC_CREATED) {
                                loadData();
                                if(statusList.size() > 0) {
                                    statusAdapter.removeItem(statusAdapter.getItemCount() - 1);
                                    statusAdapter.setLoaded();
                                    statusAdapter.swapData(statusList);
                                    loop = 15;
                                }
                            }
                            ++loop;
                        }
                    });
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {}
                }
            }
        })).start();
    }

    public void navigateFragment(Fragment fragment, String strStack) {
        getParentFragment().getChildFragmentManager().beginTransaction()
                .replace(R.id.majorFrame, fragment).addToBackStack(strStack).commit();
        getParentFragment().getChildFragmentManager().executePendingTransactions();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if(onCreate) {
                fetchStatus();
            }
        }
        else {}
    }

    @Override
    public void initGc() {}

    private void loadData() {
        statusList = new ArrayList<Status>();
        SQLiteDatabase db = ConnDB.getConn().getReadableDatabase();
        Cursor cursor = db.rawQuery("select idStatus, ssoIdPoster, avatarPoster, namePoster," +
                " timePost, contentText, contentImage, totalLike, totalDislike, totalComment," +
                " interact from Status", null);
        while (cursor.moveToNext()) {
            Status status = new Status();
            status.setIdStatus(cursor.getString(0));
            status.setSsoIdPoster(cursor.getString(1));
            status.setAvatarPoster(cursor.getString(2));
            status.setNamePoster(cursor.getString(3));
            status.setTimePost(cursor.getString(4));
            status.setContentText(cursor.getString(5));
            status.setContentImage(cursor.getString(6));
            status.setTotalLike(cursor.getInt(7));
            status.setTotalDislike(cursor.getInt(8));
            status.setTotalComment(cursor.getInt(9));
            status.setInteract(cursor.getString(10));
            statusList.add(status);
        }
        cursor.close();
    }

    @Override
    public void onRefresh() {
        fetchStatus();
    }
}

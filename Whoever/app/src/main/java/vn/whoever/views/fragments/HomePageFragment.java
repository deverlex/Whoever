package vn.whoever.views.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.whoever.R;
import vn.whoever.TransConnection.HttpStatus;
import vn.whoever.TransConnection.StatusTransaction;
import vn.whoever.adapters.OnLoadMoreListener;
import vn.whoever.adapters.StatusAdapter;
import vn.whoever.models.Status;
import vn.whoever.models.dao.ConnDB;
import vn.whoever.utils.Initgc;

/**
 * Created by spider man on 12/28/2015.
 */
public class HomePageFragment extends Fragment implements Initgc, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout homeRefreshLayout;
    private RecyclerView recyclerViewHome;

    private StatusAdapter statusAdapter;
    private List<Status> statusList;
    private Handler mHandler;
    private Handler pHandler;

    private LinearLayoutManager linearLayoutManager;
    private StatusTransaction statusTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newshome_layout, null);

        init(view);
        initListener(view);
        return view;
    }

    @Override
    public void init(View view) {
        homeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshListHomeLayout);
        homeRefreshLayout.setOnRefreshListener(this);
        homeRefreshLayout.setColorSchemeResources(R.color.colorRedMain);

        recyclerViewHome = (RecyclerView) view.findViewById(R.id.listViewHomeLayout);
        mHandler = new Handler();
        pHandler = new Handler();
        statusTransaction = new StatusTransaction(getActivity());
        loadData();

        recyclerViewHome.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerViewHome.setLayoutManager(linearLayoutManager);
        statusAdapter = new StatusAdapter(this, statusList, recyclerViewHome);

        recyclerViewHome.setAdapter(statusAdapter);

        if(statusList.isEmpty()) {
            recyclerViewHome.setVisibility(View.GONE);
        } else {
            recyclerViewHome.setVisibility(View.VISIBLE);
        }
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

        homeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                homeRefreshLayout.setRefreshing(true);
                fetchStatus();
            }
        });
    }

    @Override
    public void onRefresh() {
        fetchStatus();
    }

    int loop = 0;

    private void fetchStatus() {
        loop = 0;
        homeRefreshLayout.setRefreshing(true);
        SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
        db.execSQL("delete from Status");
        statusTransaction.getNewsFeed("nearby", 0);
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
                                    Log.d("update size", String.valueOf(statusList.size()));
                                    statusAdapter.swapData(statusList);
                                    homeRefreshLayout.setRefreshing(false);
                                    loop = 15;
                                    Log.d("loadData", "update news feeds");
                                }
                            }
                            ++loop;
                            if(loop >= 15) homeRefreshLayout.setRefreshing(false);
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
        statusTransaction.getNewsFeed("nearby", statusAdapter.getItemCount() - 1);

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
                                    Log.d("sizeList", "status list: " + statusList.size());
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

    protected void loadData() {
        Log.d("loadDbHome", "loading....");
        statusList = new ArrayList<>();
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
        //db.close();
    }

    @Override
    public void onPause() {
        System.gc();
        super.onPause();
    }

    @Override
    public void initGc() {

    }
}

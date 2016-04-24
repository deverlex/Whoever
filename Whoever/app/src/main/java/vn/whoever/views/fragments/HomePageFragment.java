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

    private LinearLayoutManager linearLayoutManager;

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

    protected void fetchStatus() {
        homeRefreshLayout.setRefreshing(true);

        Log.d("GetMore", "Item Status more");
        homeRefreshLayout.setRefreshing(false);
    }

    protected void loadData() {
        Log.d("loadDbHome", "loading....");
        statusList = new ArrayList<>();
        SQLiteDatabase db = ConnDB.getConn().getReadableDatabase();
        String arg[] = {String.valueOf(0)};
        Cursor cursor = db.rawQuery("select id, idStatus, ssoIdPoster, avatarPoster, namePoster," +
                " timePost, contentText, contentImage, totalLike, totalDislike, totalComment," +
                " interact from Status where id >=?", arg);
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

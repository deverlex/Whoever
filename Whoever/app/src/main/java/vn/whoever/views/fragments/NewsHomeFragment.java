package vn.whoever.views.fragments;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;

import vn.whoever.R;
import vn.whoever.adapters.StatusAdapter;
import vn.whoever.utils.Initgc;

/**
 * Created by spider man on 12/28/2015.
 */
public class NewsHomeFragment extends Fragment implements Initgc, AbsListView.OnScrollListener {

    private ListView listStatus;
    private StatusAdapter statusAdapter;
    private ProgressBar progressBarLoadMore;
    private Handler mHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newshome_layout, null);

        init(view);
        initListener(view);
        return view;
    }

    @Override
    public void init(View view) {
        mHandler = new Handler();

        View footer = getActivity().getLayoutInflater().inflate(R.layout.progress_bar_footer, null);
        progressBarLoadMore = (ProgressBar) footer.findViewById(R.id.progressBarLoad);
        progressBarLoadMore.getIndeterminateDrawable().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);

        listStatus = (ListView) view.findViewById(R.id.listViewNewsHome);
        listStatus.addFooterView(footer);

        statusAdapter = new StatusAdapter(getActivity(), 9, 7);

        listStatus.setAdapter(statusAdapter);
        listStatus.setOnScrollListener(this);
        progressBarLoadMore.setVisibility((7 < statusAdapter.getSize()) ? View.VISIBLE : View.GONE);

    }

    @Override
    public void initListener(View view) {

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void initGc() {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    private boolean hasCallback;

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(firstVisibleItem + visibleItemCount == totalItemCount && !statusAdapter.endReached() && !hasCallback) {
            mHandler.postDelayed(showMore, 1600);
            hasCallback = true;
        }
    }

    private Runnable showMore = new Runnable() {
        @Override
        public void run() {
            boolean noMoreToShow = statusAdapter.showMore();
            progressBarLoadMore.setVisibility(noMoreToShow ? View.GONE : View.VISIBLE);
            hasCallback = false;
        }
    };
}

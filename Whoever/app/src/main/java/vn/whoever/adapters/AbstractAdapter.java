package vn.whoever.adapters;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import vn.whoever.R;

/**
 * Created by spider man on 4/24/2016.
 */
public abstract class AbstractAdapter<T> extends RecyclerView.Adapter {

    protected List<T> dataList;
    protected Fragment fragment;

    protected final int VIEW_ITEM = 1;
    protected final int VIEW_PROG = 0;

    protected int visibleThreshold = 5;
    protected int lastVisibleItem, totalItemCount;
    protected boolean loading;
    protected OnLoadMoreListener onLoadMoreListener;

    public AbstractAdapter(Fragment fragment, List<T> dataList,RecyclerView recyclerView) {
        this.fragment = fragment;
        this.dataList = dataList;

        if(recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if(!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        if(onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        loading = true;
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, int position);

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setLoaded() {
        loading = false;
    }

    public void addItem(T item) {
        this.dataList.add(item);
        notifyItemInserted(dataList.size() - 1);
    }

    public void removeItem(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
    }

    public void swapData(List<T> newData) {
        if(newData.size() > 14) {
            while (!dataList.isEmpty()){
                dataList.remove(0);
                notifyItemRemoved(0);
            }
            while (!newData.isEmpty()) {
                dataList.add(0,newData.remove(newData.size() - 1));
                notifyItemInserted(0);
            }
            Log.d("update", "listStatus");
        } else if(newData.size() > 0) {
            while (!newData.isEmpty()) {
                dataList.add(0, newData.remove(newData.size() - 1));
                notifyItemInserted(0);
            }
            Log.d("update", "listStatus");
        }
        Log.d("update", "not success!!!");
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBarLoad);
            progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#FF4801"),
                    android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }

    public void navigateToFragment(Fragment frag, String strStack) {
        fragment.getParentFragment().getChildFragmentManager().beginTransaction().replace(R.id.majorFrame, frag)
                .addToBackStack(strStack).commit();
        fragment.getParentFragment().getChildFragmentManager().executePendingTransactions();
    }
}

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
import java.util.Iterator;
import java.util.List;

import vn.whoever.R;

/**
 * Created by Nguyen Van Do on 4/24/2016.
 * This is adapter for update data in db to UI
 */
public abstract class AbstractAdapter<T> extends RecyclerView.Adapter {

    // List contain data show on UI
    // Need changeable list to Iterator - but not now
    protected List<T> dataList;
    protected Fragment fragment;

    protected final int VIEW_ITEM = 1;
    // View_prog is item (icon) bottom when load more item
    protected final int VIEW_PROG = 0;

    protected int visibleThreshold = 5;
    protected int lastVisibleItem, totalItemCount;
    protected boolean loading;
    protected OnLoadMoreListener onLoadMoreListener;

    public AbstractAdapter(Fragment fragment, List<T> dataList, RecyclerView recyclerView) {
        this.fragment = fragment;
        this.dataList = dataList;

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

            // Listener scroll event
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    // Check condition for load more item on recycler view
                    if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        if (onLoadMoreListener != null) {
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

    // refresh data on UI
    public void refreshData(List<T> newData) {
        if (newData.size() > 0) {
            while (!dataList.isEmpty()) {
                dataList.remove(0);
                notifyItemRemoved(0);
            }
            while (!newData.isEmpty()) {
                dataList.add(0, newData.remove(newData.size() - 1));
                notifyItemInserted(0);
            }
        }
    }

    public void swapData(List<T> newData) {
        /**
         * If new data response from server > 14 item
         * refresh all item on UI
         */
        if (newData.size() > 14) {
            while (!dataList.isEmpty()) {
                dataList.remove(0);
                notifyItemRemoved(0);
            }
            while (!newData.isEmpty()) {
                dataList.add(0, newData.remove(newData.size() - 1));
                notifyItemInserted(0);
            }
        } else if (newData.size() > 0) {
            // else: add more item into old list
            while (!newData.isEmpty()) {
                dataList.add(0, newData.remove(newData.size() - 1));
                notifyItemInserted(0);
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // Class set progress bar when load more item
    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        public ProgressViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBarLoad);
            progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#FF4801"),
                    android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }

    // Navigate to different fragment from old fragment to new fragment
    public void navigateToFragment(Fragment frag, String strStack) {
        fragment.getParentFragment().getChildFragmentManager().beginTransaction().replace(R.id.majorFrame, frag)
                .addToBackStack(strStack).commit();
        fragment.getParentFragment().getChildFragmentManager().executePendingTransactions();
    }
}

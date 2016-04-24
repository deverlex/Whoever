package vn.whoever.adapters;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vn.whoever.models.Comment;

/**
 * Created by spider man on 4/24/2016.
 */
public class DataAdapter extends AbstractAdapter<Comment> {

    public DataAdapter(Fragment fragment, List<Comment> commentList, RecyclerView recyclerView) {
        super(fragment, commentList, recyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {



        public CommentViewHolder(View view) {
            super(view);


        }

    }

}

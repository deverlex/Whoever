package vn.whoever.views.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import vn.whoever.R;
import vn.whoever.adapters.ReplyAdapter;
import vn.whoever.models.dao.ConnDB;
import vn.whoever.utils.Initgc;
import vn.whoever.views.customviews.JTextView;
import vn.whoever.views.customviews.RoundedImageView;

/**
 * Created by spider man on 4/9/2016.
 */
public class RepliesFragment extends Fragment implements Initgc, AbsListView.OnScrollListener {

    private ListView listReply;
    private ImageButton btnBackActivity;
    private ImageButton btnSendComment;
    private ProgressBar progressBarLoadMore;
    private ReplyAdapter replyAdapter;
    private Handler mHandler;
    private EditText inputCommentSend;

    private ImageButton btnQuickLike;
    private TextView nickNamePostStatus;
    private JTextView contentTextStatus;
    private RoundedImageView avatarPosterStatus;
    private TextView timePostStatus;
    private TextView viewTotalComment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.replies_layout, container, false);

        init(view);
        initListener(view);
        return view;
    }

    @Override
    public void init(View view) {

        mHandler = new Handler();
        btnBackActivity = (ImageButton) view.findViewById(R.id.btnBackHomeFromComment);

        View footer = getActivity().getLayoutInflater().inflate(R.layout.progress_bar_footer, null);
        progressBarLoadMore = (ProgressBar) footer.findViewById(R.id.progressBarLoad);
        progressBarLoadMore.getIndeterminateDrawable().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);

        listReply = (ListView) view.findViewById(R.id.listCommentOfStatusDetail);
        listReply.addFooterView(footer);

        replyAdapter = new ReplyAdapter(getActivity(), 10, 7);
        listReply.setAdapter(replyAdapter);
        listReply.setOnScrollListener(this);
        progressBarLoadMore.setVisibility((7 < replyAdapter.getSize()) ? View.VISIBLE : View.GONE);

        btnSendComment = (ImageButton) view.findViewById(R.id.btnSendComment);
        inputCommentSend = (EditText)  view.findViewById(R.id.inputCommentSend);

        avatarPosterStatus = (RoundedImageView) view.findViewById(R.id.imageAvatarOnStatusDetail);
        nickNamePostStatus = (TextView) view.findViewById(R.id.nickNameAndExtendOnStatusDetail);
        btnQuickLike = (ImageButton) view.findViewById(R.id.btnQuickLikeStatus);
        timePostStatus = (TextView) view.findViewById(R.id.timeUploadStatusDetail);
        contentTextStatus = (JTextView) view.findViewById(R.id.contentStatusDetail);
        viewTotalComment = (TextView) view.findViewById(R.id.viewTotalCommentOnReply);

        Bundle bundle = getArguments();
        String idStatus = bundle.getString("idStatus");
        if(idStatus != null) {
            String database = bundle.getString("database");
            SQLiteDatabase db = ConnDB.getConn().getReadableDatabase();
            String arg[] = {idStatus};
            Cursor cursor = db.rawQuery("select idStatus, ssoIdPoster, avatarPoster, namePoster," +
                    " timePost, contentText, contentImage, totalComment," +
                    " interact from " + database + " where idStatus=?", arg);
            String interact = "normal";
            while (cursor.moveToNext()) {
                nickNamePostStatus.setText(cursor.getString(3));
                timePostStatus.setText(cursor.getString(4));
                contentTextStatus.setText(cursor.getString(5));
                int totalComment = cursor.getInt(7);
                interact = cursor.getString(8);
                if(totalComment == 0) {
                    viewTotalComment.setText("No one comment on this status");
                } else if(totalComment == 1) {
                    viewTotalComment.setText("Have a person comments");
                } else {
                    viewTotalComment.setText("Have " + totalComment + " comments on this status");
                }
                if(interact.equals("like")) {
                    btnQuickLike.setImageResource(R.drawable.icon_dislike_red);
                } else if(interact.equals("dislike")) {
                    btnQuickLike.setImageResource(R.drawable.icon_dislike_red);
                }
            }
        }
    }

    @Override
    public void initListener(View view) {
        btnBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        btnSendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send comment to server
            }
        });

        btnQuickLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(firstVisibleItem + visibleItemCount == totalItemCount && !replyAdapter.endReached() && !hasCallback){ //check if we've reached the bottom
            mHandler.postDelayed(showMore, 1200);
            hasCallback = true;
        }
    }

    private boolean hasCallback;
    private Runnable showMore = new Runnable(){
        public void run(){
            boolean noMoreToShow = replyAdapter.showMore(); //show more views and find out if
            progressBarLoadMore.setVisibility(noMoreToShow? View.GONE : View.VISIBLE);
            hasCallback = false;
        }
    };

    @Override
    public void initGc() {

    }
}

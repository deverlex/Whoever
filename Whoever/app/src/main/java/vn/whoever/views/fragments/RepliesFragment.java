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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import vn.whoever.R;
import vn.whoever.adapters.ReplyAdapter;
import vn.whoever.utils.Initgc;
import vn.whoever.views.customviews.JTextView;
import vn.whoever.views.customviews.RoundedImageView;

/**
 * Created by spider man on 4/9/2016.
 */
public class RepliesFragment extends Fragment implements Initgc, AbsListView.OnScrollListener {

    private ListView listReply;
    private TextView nickNamePostStatus;
    private ImageButton btnBackActivity;
    private JTextView contentStatus;
    private RoundedImageView avatarPostStatus;
    private TextView timeUpStatus;
    private ImageButton btnSendComment;
    private EditText inputCommentSend;
    private ProgressBar progressBarLoadMore;
    private ReplyAdapter replyAdapter;
    private Handler mHandler;

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
        nickNamePostStatus = (TextView) view.findViewById(R.id.nickNameAndExtendOnStatusDetail);

        View footer = getActivity().getLayoutInflater().inflate(R.layout.progress_bar_footer, null);
        progressBarLoadMore = (ProgressBar) footer.findViewById(R.id.progressBarLoad);
        progressBarLoadMore.getIndeterminateDrawable().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);

        listReply = (ListView) view.findViewById(R.id.listCommentOfStatusDetail);
        listReply.addFooterView(footer);

        replyAdapter = new ReplyAdapter(getActivity(), 10, 7);
        listReply.setAdapter(replyAdapter);

        listReply.setOnScrollListener(this);
        progressBarLoadMore.setVisibility((7 < replyAdapter.getSize()) ? View.VISIBLE : View.GONE);

        contentStatus = (JTextView) view.findViewById(R.id.contentStatusDetail);
        avatarPostStatus = (RoundedImageView) view.findViewById(R.id.imageAvatarOnStatusDetail);
        timeUpStatus = (TextView) view.findViewById(R.id.timeUploadStatusDetail);
        btnSendComment = (ImageButton) view.findViewById(R.id.btnSendComment);
        inputCommentSend = (EditText)  view.findViewById(R.id.inputCommentSend);
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

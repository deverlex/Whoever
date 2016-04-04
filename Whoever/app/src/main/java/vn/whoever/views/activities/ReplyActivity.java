package vn.whoever.views.activities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import android.os.Handler;

import vn.whoever.R;
import vn.whoever.adapters.ReplyAdapter;
import vn.whoever.views.customviews.JTextView;
import vn.whoever.views.customviews.RoundedImageView;
import vn.whoever.utils.AppGc;

/**
 * Created by spider man on 1/30/2016.
 */
public class ReplyActivity extends AppCompatActivity implements AppGc, AbsListView.OnScrollListener {

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
    private Handler mHandler;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reply_layout);

        init();
        initListener();

    }

    public void init() {
        mHandler = new Handler();
        btnBackActivity = (ImageButton) findViewById(R.id.btnBackHomeFromComment);
        nickNamePostStatus = (TextView) findViewById(R.id.nickNameAndExtendOnStatusDetail);

        View footer = getLayoutInflater().inflate(R.layout.progress_bar_footer, null);
        progressBarLoadMore = (ProgressBar) footer.findViewById(R.id.progressBarLoad);
        progressBarLoadMore.getIndeterminateDrawable().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);

        listReply = (ListView) findViewById(R.id.listCommentOfStatusDetail);
        listReply.addFooterView(footer);

        replyAdapter = new ReplyAdapter(this, 10, 7);
        listReply.setAdapter(replyAdapter);
        listReply.setOnScrollListener(this);
        progressBarLoadMore.setVisibility((7 < replyAdapter.getSize()) ? View.VISIBLE : View.GONE);

        contentStatus = (JTextView) findViewById(R.id.contentStatusDetail);
        avatarPostStatus = (RoundedImageView) findViewById(R.id.imageAvatarOnStatusDetail);
        timeUpStatus = (TextView) findViewById(R.id.timeUploadStatusDetail);
        btnSendComment = (ImageButton) findViewById(R.id.btnSendComment);
        inputCommentSend = (EditText)  findViewById(R.id.inputCommentSend);
    }

    public void initListener() {
        btnBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                hiddenSoftInput();
                finish();
            }
        });

        btnSendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send comment to server
            }
        });
    }

    public void hiddenSoftInput() {
        View view = this.getCurrentFocus();
        if(view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void initGc() {

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
}

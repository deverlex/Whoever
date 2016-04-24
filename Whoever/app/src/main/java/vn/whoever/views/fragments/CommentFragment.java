package vn.whoever.views.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.whoever.R;
import vn.whoever.TransConnection.CommentTrans;
import vn.whoever.adapters.CommentAdapter;
import vn.whoever.adapters.OnLoadMoreListener;
import vn.whoever.models.Comment;
import vn.whoever.models.dao.ConnDB;
import vn.whoever.utils.Initgc;
import vn.whoever.views.customviews.JTextView;
import vn.whoever.views.customviews.RoundedImageView;

/**
 * Created by spider man on 4/9/2016.
 */
public class CommentFragment extends Fragment implements Initgc {

    private RecyclerView recyclerViewComment;
    private List<Comment> commentList;
    private CommentAdapter commentAdapter;
    private LinearLayoutManager linearLayoutManager;

    private ImageButton btnBackActivity;
    private ImageButton btnSendComment;
    private Handler mHandler;
    private EditText inputCommentSend;

    private ImageButton btnQuickLike;
    private TextView nickNamePostStatus;
    private JTextView contentTextStatus;
    private RoundedImageView avatarPosterStatus;
    private TextView timePostStatus;
    private TextView viewTotalComment;

    private String idStatus;
    private boolean isHideToolbar;
    private RelativeLayout toolBarInputComment;
    private ProgressBar progressLoadComment;
    private CommentTrans commentTrans;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comments_layout, container, false);

        init(view);
        initListener(view);
        return view;
    }

    @Override
    public void init(View view) {
        commentList = new ArrayList<Comment>();
        mHandler = new Handler();
        commentTrans = new CommentTrans(getActivity());

        avatarPosterStatus = (RoundedImageView) view.findViewById(R.id.imageAvatarOnStatusDetail);
        nickNamePostStatus = (TextView) view.findViewById(R.id.nickNameAndExtendOnStatusDetail);
        btnQuickLike = (ImageButton) view.findViewById(R.id.btnQuickLikeStatus);
        timePostStatus = (TextView) view.findViewById(R.id.timeUploadStatusDetail);
        contentTextStatus = (JTextView) view.findViewById(R.id.contentStatusDetail);
        viewTotalComment = (TextView) view.findViewById(R.id.viewTotalCommentOnReply);
        btnBackActivity = (ImageButton) view.findViewById(R.id.btnBackHomeFromComment);
        idStatus = loadSetStatusOnListComment();

        progressLoadComment = (ProgressBar) view.findViewById(R.id.progressLoadComment);
        progressLoadComment.getIndeterminateDrawable().setColorFilter(Color.parseColor("#FF4801"),
                android.graphics.PorterDuff.Mode.MULTIPLY);

        toolBarInputComment = (RelativeLayout) view.findViewById(R.id.toolBarInputSendTextOfComment);

        btnSendComment = (ImageButton) view.findViewById(R.id.btnSendComment);
        inputCommentSend = (EditText)  view.findViewById(R.id.inputCommentSend);

        recyclerViewComment = (RecyclerView) view.findViewById(R.id.listCommentOfStatusDetail);

        recyclerViewComment.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerViewComment.setLayoutManager(linearLayoutManager);

        commentAdapter = new CommentAdapter(this, commentList, recyclerViewComment);

        recyclerViewComment.setAdapter(commentAdapter);

        getCommentFromService(idStatus);

//        if(commentList.isEmpty()) {
//            recyclerViewComment.setVisibility(View.GONE);
//        } else {
//            recyclerViewComment.setVisibility(View.VISIBLE);
//        }
    }

    private String loadSetStatusOnListComment() {
        Bundle bundle = getArguments();
        String idStatus = bundle.getString("idStatus");
        Log.d("idStatusGetCmt", idStatus);
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
                    btnQuickLike.setImageResource(R.drawable.icon_like_red);
                } else if(interact.equals("dislike")) {
                    btnQuickLike.setImageResource(R.drawable.icon_dislike_red);
                }
            }
        }
        return idStatus;
    }

    @Override
    public void initListener(View view) {

        commentAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                commentList.add(null);
                commentAdapter.notifyItemInserted(commentList.size() - 1);

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commentList.remove(commentList.size() - 1);
                        commentAdapter.notifyItemRemoved(commentList.size());
                        fetchComments();
                        commentAdapter.setLoaded();
                    }
                }, 2000);
            }
        });

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

        isHideToolbar = false;
        final GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onDown(MotionEvent event) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                final int SWIPE_MIN_DISTANCE = 40;
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
                            toolBarInputComment.setVisibility(View.GONE);
                        }
                    } else if((e2.getY() - e1.getY()) > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY){
                        if(isHideToolbar) {
                            isHideToolbar = false;
                            toolBarInputComment.setVisibility(View.VISIBLE);
                        }
                    }
                } catch (Exception e) {}
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });

        recyclerViewComment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return false;
            }
        });
    }

    public void fetchComments() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void getCommentFromService(final String idStatus) {
        progressLoadComment.setVisibility(View.VISIBLE);
        progressLoadComment.setIndeterminate(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                commentTrans.getCommentOfStatus(idStatus);
                for(int i = 0; i < 20; ++i) {
                    if (commentTrans.getCommentList().size() > 0) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                commentList = commentTrans.getCommentList();
                                for(int i = 0 ; i < commentList.size(); ++i) {
                                    commentAdapter.addItem(commentList.get(i));
                                }
                                /**
                                 * can sua cho nay chut nua
                                 */
                                //commentAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {}
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressLoadComment.setIndeterminate(false);
                        progressLoadComment.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }

    @Override
    public void onPause() {
        progressLoadComment.setIndeterminate(false);
        progressLoadComment.setVisibility(View.GONE);
        super.onPause();
    }

    @Override
    public void initGc() {

    }
}

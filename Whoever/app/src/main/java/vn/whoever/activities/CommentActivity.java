package vn.whoever.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import vn.whoever.R;
import vn.whoever.customviews.JTextView;
import vn.whoever.customviews.RoundedImageView;
import vn.whoever.utils.AppGc;

/**
 * Created by spider man on 1/30/2016.
 */
public class CommentActivity extends AppCompatActivity implements AppGc {

    private ListView listComment;
    private TextView nickNamePostStatus;
    private ImageButton btnBackActivity;
    private JTextView contentStatus;
    private RoundedImageView avatarPostStatus;
    private TextView timeUpStatus;
    private ImageButton btnSendComment;
    private EditText inputCommentSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_layout);

        init();
        initListener();
    }

    public void init() {
        btnBackActivity = (ImageButton) findViewById(R.id.btnBackHomeFromComment);
        nickNamePostStatus = (TextView) findViewById(R.id.nickNameAndExtendOnStatusDetail);
        listComment = (ListView) findViewById(R.id.listCommentOfStatusDetail);
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

    @Override
    public void initGc() {

    }
}

package vn.whoever.views.fragments;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import vn.whoever.R;
import vn.whoever.TransConnection.HttpStatus;
import vn.whoever.TransConnection.StatusTransaction;
import vn.whoever.models.Status;
import vn.whoever.models.dao.ConnDB;
import vn.whoever.utils.Initgc;
import vn.whoever.views.dialogs.DialogPrivacyPostStatus;

/**
 * Created by spider man on 4/8/2016.
 */
public class PostStatusFragment extends Fragment implements Initgc {

    private RelativeLayout toolbarPostStatus;
    private RelativeLayout toobarPrivacy;

    private ImageButton btnBackHome;
    private ImageButton btnPost;

    private ImageView symbolUse;
    private TextView textShowUse;
    private ImageView symbolPrivacy;
    private TextView textShowPrivacy;

    private EditText editContentStatus;
    private DialogPrivacyPostStatus privacyPost;
    private StatusTransaction statusTransaction;

    private String strStatus;
    private Handler handler;
    private ProgressDialog progressPost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.post_status_layout, container, false);

        init(view);
        initListener(view);
        return view;
    }

    @Override
    public void init(View view) {
        toolbarPostStatus = (RelativeLayout) view.findViewById(R.id.toolBarFromPostStatus);
        toobarPrivacy = (RelativeLayout) view.findViewById(R.id.toolBarChoicePrivacy);

        editContentStatus = (EditText) view.findViewById(R.id.contentTextInputStatus);
        btnBackHome = (ImageButton) toolbarPostStatus.findViewById(R.id.btnBackFromPostStatus);
        btnPost = (ImageButton) toolbarPostStatus.findViewById(R.id.btnSendStatus);

        symbolUse = (ImageView) toobarPrivacy.findViewById(R.id.symbolUseAccountPostStatus);
        symbolPrivacy = (ImageView) toobarPrivacy.findViewById(R.id.symbolPrivacyPostStatus);
        textShowUse = (TextView) toobarPrivacy.findViewById(R.id.textShowUseAccountPostStatus);
        textShowPrivacy = (TextView) toobarPrivacy.findViewById(R.id.textShowUseAccountPostStatus);

        SQLiteDatabase db = ConnDB.getConn().getReadableDatabase();
        Cursor cursor = db.rawQuery("select use, privacy from SetPostStatus where id=1", null);
        while (cursor.moveToNext()){
            if(cursor.getString(0).equals("anonymous")) {
                symbolUse.setImageResource(R.drawable.icon_anonymous);
                textShowUse.setText("Anonymous");
            } else {
                symbolUse.setImageResource(R.drawable.icon_account);
                textShowUse.setText("Account");
            }
            if(cursor.getString(1).equals("public")) {
                symbolPrivacy.setImageResource(R.drawable.icon_notify_red);
                textShowPrivacy.setText("Public");
            } else if(cursor.getString(1).equals("friends")) {
                symbolPrivacy.setImageResource(R.drawable.icon_contacts_red);
                textShowPrivacy.setText("Friends");
            } else {
                symbolPrivacy.setImageResource(R.drawable.icon_lock_red);
                textShowPrivacy.setText("Primary");
            }
        }
        cursor.close();
        db.close();
        handler = new Handler();
        statusTransaction = new StatusTransaction(getActivity());
    }

    @Override
    public void initListener(View view) {
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(progressPost.isShowing()) {
                    progressPost.dismiss();
                }
                getActivity().onBackPressed();
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strStatus = editContentStatus.getText().toString();
                /**
                 * TODO: update for new feed & database SQLite
                 * => redirect to News
                 */
                SQLiteDatabase db = ConnDB.getConn().getReadableDatabase();
                Cursor cursor = db.rawQuery("select use, privacy from SetPostStatus where id=1", null);
                String privacy = "open";
                boolean isUseAccount = false;
                while (cursor.moveToNext()) {
                    if (cursor.getString(0).equals("account")) {
                        isUseAccount = true;
                    }
                    if (cursor.getString(1).equals("friends")) {
                        privacy = "normal";
                    } else if (cursor.getString(1).equals("primary")) {
                        privacy = "close";
                    }
                }
                if (strStatus.length() > 0) {
                    statusTransaction.postStatus(strStatus, "", privacy, String.valueOf(isUseAccount));
                    progressPost = ProgressDialog.show(getActivity(), "", "wait posting...", true);
                            (new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    for (int i = 0; i < 30; ++i) {
                                        final Integer httpStatus = statusTransaction.getHttpStatusCode();
                                        handler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (httpStatus != null && HttpStatus.getStatus(getActivity()).signalCode(httpStatus)) {
                                                    strStatus = "";
                                                    progressPost.dismiss();
                                                    getActivity().onBackPressed();
                                                } 
                                            }
                                        });
                                        try {
                                            Thread.sleep(200);
                                        } catch (InterruptedException e) {}
                                        if (i == 29) {
                                            handler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    progressPost.dismiss();
                                                    Toast.makeText(getActivity(), "Have error in system, try again!", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    }
                                }
                            })).start();
                }
                cursor.close();
                db.close();
            }
        });

        toobarPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                privacyPost = new DialogPrivacyPostStatus();
                privacyPost.setLayout(toobarPrivacy);
                privacyPost.show(getActivity().getFragmentManager(), "Privacy Post Status");
            }
        });
    }

    @Override
    public void initGc() {

    }
}

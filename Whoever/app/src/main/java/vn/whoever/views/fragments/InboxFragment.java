package vn.whoever.views.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import vn.whoever.R;
import vn.whoever.adapters.InboxAdapter;
import vn.whoever.utils.AlphaButton;
import vn.whoever.utils.Initgc;

/**
 * Created by spider man on 12/29/2015.
 */
public class InboxFragment extends Fragment implements Initgc {

    private ListView listInbox;
    private FloatingActionButton btnCreateNewChat;
    private InboxAdapter inboxAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inbox_layout, null);

        init(view);
        initListener(view);
        return view;
    }

    @Override
    public void init(View view) {
        listInbox = (ListView) view.findViewById(R.id.listViewPreviewMessage);
        btnCreateNewChat = (FloatingActionButton) view.findViewById(R.id.btnCreateNewMessage);
        inboxAdapter = new InboxAdapter(getActivity());
        listInbox.setAdapter(inboxAdapter);
    }

    @Override
    public void initListener(View view) {
        final GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onDown(MotionEvent event) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                final int SWIPE_MIN_DISTANCE = 30;
                final int SWIPE_MAX_OFF_PATH = 150;
                final int SWIPE_THRESHOLD_VELOCITY = 150;

                try {
                    if(Math.abs(e1.getX() - e2.getX()) > SWIPE_MAX_OFF_PATH) {
                        return false;
                    }
                    if((e1.getY() - e2.getY()) > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) { //up
                        if(btnCreateNewChat.getVisibility() == View.VISIBLE) {
                            new AlphaButton(btnCreateNewChat, 1.0f, 0.0f);
                        }
                    } else if((e2.getY() - e1.getY()) > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                        if(btnCreateNewChat.getVisibility() != View.VISIBLE) {
                            new AlphaButton(btnCreateNewChat, 0.0f, 1.0f);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return super.onFling(e1,e2, velocityX, velocityY );
            }
        });

        listInbox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return false;
            }
        });

        listInbox.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                navigateToMessage(new MessageFragment(), "inboxFrameToMessage");
            }
        });

        btnCreateNewChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void navigateToMessage(Fragment fragment, String strStack) {
        getParentFragment().getChildFragmentManager().beginTransaction().replace(R.id.majorFrame, fragment)
                .addToBackStack(strStack).commit();
        getParentFragment().getChildFragmentManager().executePendingTransactions();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void initGc() {

    }
}

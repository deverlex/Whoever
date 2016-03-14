package vn.whoever.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;

import vn.whoever.R;
import vn.whoever.adapters.ContactsAdapter;
import vn.whoever.utils.AlphaButton;
import vn.whoever.utils.Initgc;

/**
 * Created by spider man on 12/29/2015.
 */
public class ContactsFragment extends Fragment implements Initgc {

    private LinearLayout layoutToolBar;
    private FloatingActionButton buttonAddAccount;
    private ListView listContact;

    private ContactsAdapter contactsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_layout, null);

        init(view);
        initListener(view);
        return view;
    }

    @Override
    public void init(View view) {
        layoutToolBar = (LinearLayout) view.findViewById(R.id.layoutHeaderContactList);
        buttonAddAccount = (FloatingActionButton) view.findViewById(R.id.btnAddNewContact);
        listContact = (ListView) view.findViewById(R.id.listViewContactList);

        contactsAdapter = new ContactsAdapter(getActivity());
        listContact.setAdapter(contactsAdapter);
    }

    @Override
    public void initListener(View view) {
        final GestureDetector gestureDetector = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener(){

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
                    if(Math.abs(e1.getX() - e2.getX()) >  SWIPE_MAX_OFF_PATH) {
                        return false;
                    }
                    if((e1.getY() - e2.getY()) > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                        // up
                        if(buttonAddAccount.getVisibility() == View.VISIBLE) {
                            new AlphaButton(buttonAddAccount, 1.0f, 0.0f);
                        }
                    } else if(e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                        // down
                        if(buttonAddAccount.getVisibility() != View.VISIBLE) {
                            new AlphaButton(buttonAddAccount, 0.0f, 1.0f);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return super.onFling(e1, e2, velocityX, velocityY);
            }

        });

        listContact.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return false;
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void initGc() {

    }
}

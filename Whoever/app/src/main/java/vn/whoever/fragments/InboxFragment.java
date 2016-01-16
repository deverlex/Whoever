package vn.whoever.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import vn.whoever.R;
import vn.whoever.utils.AlphaButton;

/**
 * Created by spider man on 12/29/2015.
 */
public class InboxFragment extends Fragment {

    private ListView listView;
    private FloatingActionButton button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inbox_layout, null);

    //    init(view);
    //    initListener(view);
        return view;
    }

    public void init(View view) {
      //  listView = (ListView) view.findViewById(R.id.listViewPreviewMessage);
       // button = (FloatingActionButton) view.findViewById(R.id.btnCreateNewMessage);
    }

    public void initListener(View view) {
        final GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onDown(MotionEvent event) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                final int SWIPE_MIN_DISTANCE = 100;
                final int SWIPE_MAX_OFF_PATH = 150;
                final int SWIPE_THRESHOLD_VELOCITY = 150;

                try {
                    if(Math.abs(e1.getX() - e2.getX()) > SWIPE_MAX_OFF_PATH) {
                        return false;
                    }
                    if((e1.getY() - e2.getY()) > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                        if(button.getVisibility() == View.VISIBLE) {
                            new AlphaButton(button, 1.0f, 0.0f);
                        }
                        Log.d("SWIPE TOUCH:", "e1 - e2"); // up
                    } else if((e2.getY() - e1.getY()) > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                        if(button.getVisibility() != View.VISIBLE) {
                            new AlphaButton(button, 0.0f, 1.0f);
                        }
                        Log.d("SWIPE TOUCH:", "e2 - e1"); //down
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return super.onFling(e1,e2, velocityX, velocityY );
            }
        });

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Pause", "clear memory");
        System.gc();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Stop", "clear memory");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Destroy", "clear memory");
    }
}

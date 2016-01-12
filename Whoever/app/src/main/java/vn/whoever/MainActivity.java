package vn.whoever;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import vn.whoever.activities.NotifyActivity;
import vn.whoever.activities.SearchActivity;
import vn.whoever.fragments.TabHomeFragment;
import vn.whoever.utils.*;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager frgtManagerMain;
    public static FragmentTransaction frgTransactionMain;

    private DisplayMetrics metrics;
    private boolean isExpandedLeft;
    private boolean isExpandedRight;
    private int panelWidth;

    private LinearLayout layoutOverview;
    private LinearLayout layoutOnline;
    private LinearLayout layoutHome;

    private FrameLayout.LayoutParams layoutParamsOverview;
    private FrameLayout.LayoutParams layoutParamsOnline;
    private FrameLayout.LayoutParams layoutParamsMain;

    private LinearLayout listenerClick;
    private FrameLayout.LayoutParams listenerPrams;

    private RelativeLayout btnOpenOverview;
    private RelativeLayout btnOpenOnline;
    private RelativeLayout btnOpenNotify;
    private RelativeLayout btnOpenSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        frgtManagerMain = getSupportFragmentManager();
        frgTransactionMain = frgtManagerMain.beginTransaction();
        frgTransactionMain.replace(R.id.containerViewMain, new TabHomeFragment()).commit();

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        panelWidth = (int) (metrics.widthPixels * 0.85);

        init();
        initListener();
    }

    public void init() {
        layoutOnline = (LinearLayout) findViewById(R.id.onlineLayout);
        layoutParamsOnline = (FrameLayout.LayoutParams) layoutOnline.getLayoutParams();
        layoutParamsOnline.width = metrics.widthPixels;
        layoutOnline.setLayoutParams(layoutParamsOnline);

        layoutOverview = (LinearLayout) findViewById(R.id.overviewLayout);
        layoutParamsOverview = (FrameLayout.LayoutParams) layoutOverview.getLayoutParams();
        layoutParamsOverview.width = metrics.widthPixels;
        layoutOverview.setLayoutParams(layoutParamsOverview);

        layoutHome = (LinearLayout) findViewById(R.id.homeLayout);
        layoutParamsMain = (FrameLayout.LayoutParams) layoutHome.getLayoutParams();
        layoutParamsMain.width = metrics.widthPixels;
        layoutHome.setLayoutParams(layoutParamsMain);

        btnOpenOverview = (RelativeLayout) findViewById(R.id.btnOpenOverview);
        btnOpenOnline = (RelativeLayout) findViewById(R.id.btnOpenOnlineView);
        btnOpenNotify = (RelativeLayout) findViewById(R.id.btnOpenNotify);
        btnOpenSearch = (RelativeLayout) findViewById(R.id.btnOpenSearch);

        listenerClick = (LinearLayout) findViewById(R.id.listenerClick);
    }

    public void initListener() {
        btnOpenOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isExpandedLeft) {
                    isExpandedLeft = true;
                    openRightListenerClick();;
                    new TranslateToRight(layoutHome, panelWidth,
                            TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                            TranslateAnimation.RELATIVE_TO_SELF, 0.85f,
                            0, 0.0f,
                            0, 0.0f);
                    layoutOverview.setVisibility(View.VISIBLE);
                    layoutOnline.setVisibility(View.INVISIBLE);
                } else {
                    isExpandedLeft = false;
                    closeListenerClick();
                    new CloseSliding(layoutHome, panelWidth,
                            TranslateAnimation.RELATIVE_TO_SELF, 0.85f,
                            TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                            0, 0.0f,
                            0, 0.0f);
                }
            }
        });

        btnOpenOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isExpandedRight) {
                    isExpandedRight = true;
                    openLeftListenerClick();
                    new TranslateToLeft(layoutHome, panelWidth,
                            TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                            TranslateAnimation.RELATIVE_TO_SELF, -0.85f,
                            0, 0.0f,
                            0, 0.0f);
                    layoutOverview.setVisibility(View.INVISIBLE);
                    layoutOnline.setVisibility(View.VISIBLE);
                } else {
                    isExpandedRight = false;
                    closeListenerClick();
                    new CloseSliding(layoutHome, panelWidth,
                            TranslateAnimation.RELATIVE_TO_SELF, -0.85f,
                            TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                            0, 0.0f,
                            0, 0.0f);
                }
            }
        });

        btnOpenNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToNotify();
            }
        });

        btnOpenSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSearch();
            }
        });

        listenerClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isExpandedLeft) {
                    isExpandedLeft = false;
                    closeListenerClick();
                    new CloseSliding(layoutHome, panelWidth,
                            TranslateAnimation.RELATIVE_TO_SELF, 0.85f,
                            TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                            0, 0.0f,
                            0, 0.0f);
                }
                if(isExpandedRight) {
                    isExpandedRight = false;
                    closeListenerClick();
                    new CloseSliding(layoutHome, panelWidth,
                            TranslateAnimation.RELATIVE_TO_SELF, -0.85f,
                            TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                            0, 0.0f,
                            0, 0.0f);
                }
            }
        });
    }

    public void openRightListenerClick() {
        /**
         * TODO: layout listener on right main layout
         * | L|
         */
        listenerPrams = (FrameLayout.LayoutParams) listenerClick.getLayoutParams();
        listenerPrams.width = (int) (metrics.widthPixels * 0.15);
        listenerPrams.leftMargin = (int) (metrics.widthPixels * 0.85);
        listenerClick.setLayoutParams(listenerPrams);
    }

    public void openLeftListenerClick() {
        /**
         * TODO: layout listener on left main layout
         * |L |
         */
        listenerPrams = (FrameLayout.LayoutParams) listenerClick.getLayoutParams();
        listenerPrams.width = (int) (metrics.widthPixels * 0.15);
        listenerPrams.rightMargin = (int) (metrics.widthPixels * 0.85);
        listenerClick.setLayoutParams(listenerPrams);
    }

    public void closeListenerClick() {
        listenerPrams = (FrameLayout.LayoutParams) listenerClick.getLayoutParams();
        listenerPrams.width = 0;
        listenerPrams.leftMargin = 0;
        listenerPrams.rightMargin = 0;
        listenerClick.setLayoutParams(listenerPrams);
    }

    public void navigateToNotify() {
        Intent intentNotify = new Intent(this, NotifyActivity.class);
        startActivity(intentNotify);
    }

    public void navigateToSearch() {
        Intent intentSearch = new Intent(this, SearchActivity.class);
        startActivity(intentSearch);
    }
}

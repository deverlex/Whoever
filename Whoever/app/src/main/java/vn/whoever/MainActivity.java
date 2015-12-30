package vn.whoever;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import vn.whoever.activities.SearchActivity;
import vn.whoever.fragments.TabHomeFragment;
import vn.whoever.utils.*;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager frgtManagerMain;
    public static FragmentTransaction frgTransactionMain;

    private DisplayMetrics metrics;
    private boolean isExpanded;
    private int panelWidth;

    private RelativeLayout layoutOverview;
    private RelativeLayout layoutOnline;
    private LinearLayout layoutMain;

    private FrameLayout.LayoutParams layoutParamsOverview;
    private FrameLayout.LayoutParams layoutParamsOnline;
    private FrameLayout.LayoutParams layoutParamsMain;

    private RelativeLayout btnOpenOverview;
    private RelativeLayout btnOpenOnline;
    private RelativeLayout btnOpenNotify;
    private RelativeLayout btnOpenSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        frgtManagerMain = getSupportFragmentManager();
        frgTransactionMain = frgtManagerMain.beginTransaction();
        frgTransactionMain.replace(R.id.containerViewMain, new TabHomeFragment()).commit();

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        panelWidth = (int) (metrics.widthPixels * 0.85);

        setDefaultFixLayout();

        btnOpenOverview = (RelativeLayout) findViewById(R.id.btnOpenOverview);
        btnOpenOnline = (RelativeLayout) findViewById(R.id.btnOpenOnlineView);
        btnOpenNotify = (RelativeLayout) findViewById(R.id.btnOpenNotify);
        btnOpenSearch = (RelativeLayout) findViewById(R.id.btnOpenSearch);

        setEventOnLayout();
    }

    public void setDefaultFixLayout() {
        layoutOnline = (RelativeLayout) findViewById(R.id.onlineLayout);
        layoutParamsOnline = (FrameLayout.LayoutParams) layoutOnline.getLayoutParams();
        layoutParamsOnline.width = metrics.widthPixels;
        layoutOnline.setLayoutParams(layoutParamsOnline);

        layoutOverview = (RelativeLayout) findViewById(R.id.overviewLayout);
        layoutParamsOverview = (FrameLayout.LayoutParams) layoutOverview.getLayoutParams();
        layoutParamsOverview.width = metrics.widthPixels;
        layoutOverview.setLayoutParams(layoutParamsOverview);

        layoutMain = (LinearLayout) findViewById(R.id.homeLayout);
        layoutParamsMain = (FrameLayout.LayoutParams) layoutMain.getLayoutParams();
        layoutParamsMain.width = metrics.widthPixels;
        layoutMain.setLayoutParams(layoutParamsMain);
    }

    public void setEventOnLayout() {
        btnOpenOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isExpanded) {
                    isExpanded = true;

                    new TranslateToRight(layoutMain, panelWidth,
                            TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                            TranslateAnimation.RELATIVE_TO_SELF, 0.85f,
                            0, 0.0f,
                            0, 0.0f);
                    layoutOverview.setVisibility(View.VISIBLE);
                    layoutOnline.setVisibility(View.INVISIBLE);
                } else {
                    isExpanded = false;

                    new CloseSliding(layoutMain, panelWidth,
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

                if(!isExpanded) {
                    isExpanded = true;

                    new TranslateToLeft(layoutMain, panelWidth,
                            TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                            TranslateAnimation.RELATIVE_TO_SELF, -0.85f,
                            0, 0.0f,
                            0, 0.0f);
                    layoutOverview.setVisibility(View.INVISIBLE);
                    layoutOnline.setVisibility(View.VISIBLE);
                } else {
                    isExpanded = false;

                    new CloseSliding(layoutMain, panelWidth,
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

            }
        });

        btnOpenSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSearch();
            }
        });
    }

    public void navigateToSearch() {
        Intent intentSearch = new Intent(this, SearchActivity.class);
        startActivity(intentSearch);
    }

}

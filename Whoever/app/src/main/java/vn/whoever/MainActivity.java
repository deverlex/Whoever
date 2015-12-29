package vn.whoever;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import vn.whoever.fragments.TabHomeFragment;

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

    private ImageView btnOpenOverview;
    private ImageView btnOpenOnline;

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
    }

    public void setDefaultFixLayout() {
        layoutOnline = (RelativeLayout) findViewById(R.id.onlineLayout);
        layoutParamsOnline = (FrameLayout.LayoutParams) layoutOnline.getLayoutParams();
        

        layoutOverview = (RelativeLayout) findViewById(R.id.overviewLayout);

        layoutMain = (LinearLayout) findViewById(R.id.homeLayout);
    }

}

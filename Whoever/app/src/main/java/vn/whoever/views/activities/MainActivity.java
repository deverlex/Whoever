package vn.whoever.views.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import vn.whoever.R;
import vn.whoever.views.activities.AccountSettingActivity;
import vn.whoever.views.activities.AppSettingActivity;
import vn.whoever.views.activities.HelpCenterActivity;
import vn.whoever.views.activities.LogActivity;
import vn.whoever.views.activities.NotifyActivity;
import vn.whoever.views.activities.PrivacyActivity;
import vn.whoever.views.activities.ReportActivity;
import vn.whoever.views.activities.SearchActivity;
import vn.whoever.adapters.ListOnlineAdapter;
import vn.whoever.views.fragments.SearchFragment;
import vn.whoever.views.fragments.TabHomeFragment;
import vn.whoever.utils.*;

public class MainActivity extends AppCompatActivity implements AppGc {

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

    private final float ratioLayout = 0.85f;

    /**
     * TODO: for layout on online list layout
     */
    private EditText inputSearchOnline;
    private ImageView btnDestroySeachOnline;
    private ListView listOnline;
    private ListOnlineAdapter listOnlineAdapter;

    /**
     * TODO: for layout overview
     */

    private RelativeLayout directAppSetting;
    private RelativeLayout directAccountSetting;
    private RelativeLayout directActivityLog;
    private RelativeLayout directPrivacy;
    private RelativeLayout directTermPolicities;
    private RelativeLayout directHelpCenter;
    private RelativeLayout directReport;
    private RelativeLayout directAbout;
    private RelativeLayout directLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hiddenSoftInput();

        frgtManagerMain = getSupportFragmentManager();
        frgTransactionMain = frgtManagerMain.beginTransaction();
        frgTransactionMain.replace(R.id.containerViewMain, new TabHomeFragment()).commit();

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        panelWidth = (int) (metrics.widthPixels * ratioLayout);

        init();
        initListener();
    }

    public void init() {
        layoutOnline = (LinearLayout) findViewById(R.id.onlineLayout);
        layoutParamsOnline = (FrameLayout.LayoutParams) layoutOnline.getLayoutParams();
        layoutParamsOnline.width = (int) (metrics.widthPixels * ratioLayout);
        layoutOnline.setLayoutParams(layoutParamsOnline);

        layoutOverview = (LinearLayout) findViewById(R.id.overviewLayout);
        layoutParamsOverview = (FrameLayout.LayoutParams) layoutOverview.getLayoutParams();
        layoutParamsOverview.width = (int) (metrics.widthPixels * ratioLayout);
        layoutOverview.setLayoutParams(layoutParamsOverview);

        layoutHome = (LinearLayout) findViewById(R.id.homeLayout);
        layoutParamsMain = (FrameLayout.LayoutParams) layoutHome.getLayoutParams();
        layoutParamsMain.width = metrics.widthPixels;
        layoutHome.setLayoutParams(layoutParamsMain);

        btnOpenOverview = (RelativeLayout) findViewById(R.id.btnOpenOverview);
        btnOpenOnline = (RelativeLayout) findViewById(R.id.btnOpenOnlineView);
        btnOpenNotify = (RelativeLayout) findViewById(R.id.btnOpenNotify);
        btnOpenSearch = (RelativeLayout) findViewById(R.id.btnOpenSearch);

        /// listenClick for close/open sliding
        listenerClick = (LinearLayout) findViewById(R.id.listenerClick);

        inputSearchOnline = (EditText) layoutOnline.findViewById(R.id.textInputFromOnlineList);
        inputSearchOnline.setTextColor(Color.parseColor("#ffffff"));
        btnDestroySeachOnline = (ImageView) layoutOnline.findViewById(R.id.btnDestroyInputFromOnlineList);

        /**
         * TODO: init() for online layout
         */

        listOnline = (ListView) findViewById(R.id.listOnlineUser);


        listOnlineAdapter = new ListOnlineAdapter(this);
         listOnline.setAdapter(listOnlineAdapter);
        /**
         * TODO: init() for overview layout
         */

        directAppSetting = (RelativeLayout) findViewById(R.id.layoutAppSettingOverview);
        directAccountSetting = (RelativeLayout) findViewById(R.id.layoutAccountSettingOverview);
        directActivityLog = (RelativeLayout) findViewById(R.id.layoutLogActivityOverview);
        directPrivacy = (RelativeLayout) findViewById(R.id.layoutPrivacyOverview);
        directTermPolicities = (RelativeLayout) findViewById(R.id.layoutTermOverview);
        directHelpCenter = (RelativeLayout) findViewById(R.id.layoutHelpCenterOverview);
        directReport = (RelativeLayout) findViewById(R.id.layoutReportOverview);
        directAbout = (RelativeLayout) findViewById(R.id.layoutAboutOverview);
        directLogout = (RelativeLayout) findViewById(R.id.layoutLogOutOverview);

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
                    hiddenSoftInput();
                }
            }
        });

        /**
         * TODO: listener for online list layout
         */



        /**
         * TODO: Listener for overview layout
         */

        directAppSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToAppSetting();
            }
        });

        directAccountSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToAccountSetting();
            }
        });

        directActivityLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActivityLog();
            }
        });

        directPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToPrivacy();
            }
        });

        directHelpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToHelpCenter();
            }
        });

        directReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToReport();
            }
        });

        directTermPolicities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO: something else
                 */
            }
        });

        directAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        directLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    public void hiddenSoftInput() {
        View view = this.getCurrentFocus();
        if(view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void navigateToNotify() {
        Intent intentNotify = new Intent(this, NotifyActivity.class);
        startActivity(intentNotify);
    }

    public void navigateToSearch() {
        frgTransactionMain = frgtManagerMain.beginTransaction();
        frgTransactionMain.replace(R.id.mainFrame, new SearchFragment()).addToBackStack("searchInforFrame").commit();
    }

    public void navigateToAppSetting() {
        Intent intentAppSet = new Intent(this, AppSettingActivity.class);
        startActivity(intentAppSet);
    }

    public void navigateToAccountSetting() {
        Intent intentAccountSet = new Intent(this, AccountSettingActivity.class);
        startActivity(intentAccountSet);
    }

    public void navigateToActivityLog() {
        Intent intentLog = new Intent(this, LogActivity.class);
        startActivity(intentLog);
    }

    public void navigateToPrivacy() {
        Intent intentPrivacy = new Intent(this, PrivacyActivity.class);
        startActivity(intentPrivacy);
    }

    public void navigateToHelpCenter() {
        Intent intentHelp = new Intent(this, HelpCenterActivity.class);
        startActivity(intentHelp);
    }

    public void navigateToReport() {
        Intent intentReport = new Intent(this, ReportActivity.class);
        startActivity(intentReport);
    }

    @Override
    public void onPause() {
        super.onPause();
        System.gc();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void initGc() {

    }
}

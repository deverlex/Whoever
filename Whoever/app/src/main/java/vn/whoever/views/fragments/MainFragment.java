package vn.whoever.views.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import vn.whoever.R;
import vn.whoever.adapters.ListOnlineAdapter;
import vn.whoever.utils.CloseSliding;
import vn.whoever.utils.Initgc;
import vn.whoever.utils.TranslateToLeft;
import vn.whoever.utils.TranslateToRight;
import vn.whoever.views.activities.MainActivity;

/**
 * Created by spider man on 4/9/2016.
 */
public class MainFragment extends Fragment implements Initgc {

    private final float ratioLayout = 0.85f;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_layout, container, false);

        init(view);
        initListener(view);
        return view;
    }

    @Override
    public void init(View view) {
        metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        panelWidth = (int) (metrics.widthPixels * ratioLayout);

        layoutOnline = (LinearLayout) view.findViewById(R.id.onlineLayout);
        layoutParamsOnline = (FrameLayout.LayoutParams) layoutOnline.getLayoutParams();
        layoutParamsOnline.width = (int) (metrics.widthPixels * ratioLayout);
        layoutOnline.setLayoutParams(layoutParamsOnline);

        layoutOverview = (LinearLayout) view.findViewById(R.id.overviewLayout);
        layoutParamsOverview = (FrameLayout.LayoutParams) layoutOverview.getLayoutParams();
        layoutParamsOverview.width = (int) (metrics.widthPixels * ratioLayout);
        layoutOverview.setLayoutParams(layoutParamsOverview);

        layoutHome = (LinearLayout) view.findViewById(R.id.homeLayout);
        layoutParamsMain = (FrameLayout.LayoutParams) layoutHome.getLayoutParams();
        layoutParamsMain.width = metrics.widthPixels;
        layoutHome.setLayoutParams(layoutParamsMain);

        btnOpenOverview = (RelativeLayout) view.findViewById(R.id.btnOpenOverview);
        btnOpenOnline = (RelativeLayout) view.findViewById(R.id.btnOpenOnlineView);
        btnOpenNotify = (RelativeLayout) view.findViewById(R.id.btnOpenNotify);
        btnOpenSearch = (RelativeLayout) view.findViewById(R.id.btnOpenSearch);

        /// listenClick for close/open sliding
        listenerClick = (LinearLayout) view.findViewById(R.id.listenerClick);

        inputSearchOnline = (EditText) layoutOnline.findViewById(R.id.textInputFromOnlineList);
        inputSearchOnline.setTextColor(Color.parseColor("#ffffff"));
        btnDestroySeachOnline = (ImageView) layoutOnline.findViewById(R.id.btnDestroyInputFromOnlineList);

        /**
         * TODO: init() for online layout
         */

        listOnline = (ListView) view.findViewById(R.id.listOnlineUser);


        listOnlineAdapter = new ListOnlineAdapter(getActivity());
        listOnline.setAdapter(listOnlineAdapter);
        /**
         * TODO: init() for overview layout
         */

        directAppSetting = (RelativeLayout) view.findViewById(R.id.layoutAppSettingOverview);
        directAccountSetting = (RelativeLayout) view.findViewById(R.id.layoutAccountSettingOverview);
        directActivityLog = (RelativeLayout) view.findViewById(R.id.layoutLogActivityOverview);
        directPrivacy = (RelativeLayout) view.findViewById(R.id.layoutPrivacyOverview);
        directTermPolicities = (RelativeLayout) view.findViewById(R.id.layoutTermOverview);
        directHelpCenter = (RelativeLayout) view.findViewById(R.id.layoutHelpCenterOverview);
        directReport = (RelativeLayout) view.findViewById(R.id.layoutReportOverview);
        directAbout = (RelativeLayout) view.findViewById(R.id.layoutAboutOverview);
        directLogout = (RelativeLayout) view.findViewById(R.id.layoutLogOutOverview);

        getChildFragmentManager().beginTransaction().replace(R.id.containerViewMain, new TabHomeFragment()).commit();
        getChildFragmentManager().executePendingTransactions();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#000000"));
        }
    }

    @Override
    public void initListener(View view) {
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
                navigateFragment(new NotifiesFragment(), "majorFrameToNotifies");
            }
        });

        btnOpenSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateFragment(new SearchFragment(), "majorFrameToSearch");
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
                navigateFragment(new AppSettingFragment(), "majorFrameToAppSetting");
            }
        });

        directAccountSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateFragment(new AccountSettingFragment(), "majorFrameToAccountSetting");
            }
        });

        directActivityLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateFragment(new LogFragment(), "majorFrameToLog");
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

    public void hiddenSoftInput() {
        View view = getActivity().getCurrentFocus();
        if(view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void navigateFragment(Fragment fragment, String strStack) {
        getChildFragmentManager().beginTransaction().replace(R.id.majorFrame, fragment).addToBackStack(strStack).commit();
        getChildFragmentManager().executePendingTransactions();
    }

    public void navigateToPrivacy() {
        MainActivity.frgTransaction = MainActivity.frgtManager.beginTransaction();
        MainActivity.frgTransaction.replace(R.id.mainFrame, new PrivacyFragment()).addToBackStack("privacy").commit();
    }

    public void navigateToHelpCenter() {
        MainActivity.frgTransaction = MainActivity.frgtManager.beginTransaction();
        MainActivity.frgTransaction.replace(R.id.mainFrame, new HelpCenterFragment()).addToBackStack("helpCenter").commit();
    }

    public void navigateToReport() {
        MainActivity.frgTransaction = MainActivity.frgtManager.beginTransaction();
        MainActivity.frgTransaction.replace(R.id.mainFrame, new ReportFragment()).addToBackStack("report").commit();
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

    @Override
    public void initGc() {

    }
}

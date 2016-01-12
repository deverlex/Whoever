package vn.whoever.fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.design.widget.TabLayout;

import java.util.ArrayList;

import vn.whoever.R;

/**
 * Created by spider man on 12/29/2015.
 */
public class TabHomeFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final int int_items = 4;
    private int[] icons = {
            R.drawable.ic_action_newfeed,
            R.drawable.ic_action_newline,
            R.drawable.ic_action_inbox,
            R.drawable.ic_action_contacts
    };
    private int[] icons_red = {
            R.drawable.ic_action_newfeed_red,
            R.drawable.ic_action_newline_red,
            R.drawable.ic_action_inbox_red,
            R.drawable.ic_action_contacst_red
    };

    private ArrayList<String> titles;

   // private int selectedItems = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedStanceState) {

        View view = inflater.inflate(R.layout.tab_home_layout, null);
        init(view);

        setTiltesTab(getActivity());
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);

                tabLayout.getTabAt(0).setIcon(icons_red[0]);
                for(int i = 1 ; i < int_items; ++i) {
                    tabLayout.getTabAt(i).setIcon(icons[i]);
                }

                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

                updateColorTabSelected();
            }
        });

        return view;
    }

    public void init(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayoutHome);
        viewPager = (ViewPager) view.findViewById(R.id.viewPageHome);
    }

    public void setTiltesTab(Context context) {
        titles = new ArrayList<>();
        titles.add(0, context.getString(R.string.title_tab_home));
        titles.add(1, context.getString(R.string.title_tab_new));
        titles.add(2, context.getString(R.string.title_tab_inbox));
        titles.add(3, context.getString(R.string.title_tab_contact));
    }

    public void updateColorTabSelected() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tabLayout.getTabAt(tab.getPosition()).setIcon(icons_red[tab.getPosition()]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tabLayout.getTabAt(tab.getPosition()).setIcon(icons[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tabLayout.getTabAt(tab.getPosition()).setIcon(icons_red[tab.getPosition()]);
            }
        });
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new NewFeedFragment();
                case 1:
                    return new NewLineFragment();
                case 2:
                    return new InboxFragment();
                case 3:
                    return new ContactsFragment();
            }

            return  null;
        }

        @Override
        public int getCount() {
            return int_items;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}

package vn.whoever.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.design.widget.TabLayout;

import java.util.ArrayList;

import vn.whoever.R;
import vn.whoever.utils.Initgc;

/**
 * Created by spider man on 12/29/2015.
 */
public class TabHomeFragment extends Fragment implements Initgc {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final int int_items = 4;
    private int[] icons = {
            R.drawable.icon_home,
            R.drawable.icon_newsfeed,
            R.drawable.icon_inbox,
            R.drawable.icon_contacts
    };
    private int[] icons_red = {
            R.drawable.icon_home_red,
            R.drawable.icon_newsfeed_red,
            R.drawable.icon_inbox_red,
            R.drawable.icon_contacts_red
    };

    private ArrayList<String> titles;

   // private int selectedItems = 0;
    private Fragment homeFragment;
    private Fragment newsFragment;
    private Fragment inboxFragment;
    private Fragment contactFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedStanceState) {

        View view = inflater.inflate(R.layout.tab_home_layout, container, false);

        init(view);
        initListener(view);

        final MyAdapter myAdapter = new MyAdapter(getParentFragment().getChildFragmentManager());

        viewPager.setAdapter(myAdapter);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);

                tabLayout.getTabAt(0).setIcon(icons_red[0]);
                for (int i = 1; i < int_items; ++i) {
                    tabLayout.getTabAt(i).setIcon(icons[i]);
                }

                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

                updateColorTabSelected();
            }
        });

        return view;
    }

    @Override
    public void init(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayoutHome);
        viewPager = (ViewPager) view.findViewById(R.id.viewPageHome);

        titles = new ArrayList<>();
        titles.add(0, getContext().getString(R.string.title_tab_home));
        titles.add(1, getContext().getString(R.string.title_tab_new));
        titles.add(2, getContext().getString(R.string.title_tab_inbox));
        titles.add(3, getContext().getString(R.string.title_tab_contact));

        homeFragment = new NewsHomeFragment();
        newsFragment = new NewsFeedFragment();
        inboxFragment = new InboxFragment();
        contactFragment = new ContactsFragment();
    }

    @Override
    public void initListener(View view) {

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

    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    return homeFragment;
                case 1:
                    return  newsFragment;
                case 2:
                    return inboxFragment;
                case 3:
                    return contactFragment;
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

    @Override
    public void onPause() {
        System.gc();
        super.onPause();
    }

    @Override
    public void initGc() {

    }
}

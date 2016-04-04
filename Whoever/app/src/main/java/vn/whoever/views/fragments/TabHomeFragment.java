package vn.whoever.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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

    private NewsHomeFragment newsHomeFragment;
    private NewsFeedFragment newsFeedFragment;
    private InboxFragment inboxFragment;
    private ContactsFragment contactsFragment;

   // private int selectedItems = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedStanceState) {

        View view = inflater.inflate(R.layout.tab_home_layout, null);

        init(view);
        initListener(view);

        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

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
        newsHomeFragment  = new NewsHomeFragment();
        newsFeedFragment = new NewsFeedFragment();
        inboxFragment = new InboxFragment();
        contactsFragment = new ContactsFragment();

        titles = new ArrayList<>();
        titles.add(0, getContext().getString(R.string.title_tab_home));
        titles.add(1, getContext().getString(R.string.title_tab_new));
        titles.add(2, getContext().getString(R.string.title_tab_inbox));
        titles.add(3, getContext().getString(R.string.title_tab_contact));
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

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return newsHomeFragment;
                case 1:
                    return newsFeedFragment;
                case 2:
                    return inboxFragment;
                case 3:
                    return contactsFragment;
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

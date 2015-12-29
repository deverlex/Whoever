package vn.whoever.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import vn.whoever.R;

/**
 * Created by spider man on 12/29/2015.
 */
public class TabHomeFragment extends Fragment {

    private TableLayout tableLayout;
    private ViewPager viewPager;
    private static final int int_items = 4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedStanceState) {

        View view = inflater.inflate(R.layout.tab_home_layout, null);

        tableLayout = (TableLayout) view.findViewById(R.id.tabLayoutHome);
        viewPager = (ViewPager) view.findViewById(R.id.viewPageHome);



        return view;
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
            }

            return  null;
        }

        @Override
        public int getCount() {
            return int_items;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "NewFeed";
                case 1:
                    return "NewLine";
                case 2:
                    return "Inbox";
                case 3:
                    return "Contacts";
            }

            return null;
        }
    }
}

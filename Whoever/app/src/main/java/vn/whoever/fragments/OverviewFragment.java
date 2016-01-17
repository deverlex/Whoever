package vn.whoever.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import vn.whoever.R;
import vn.whoever.utils.InitFragment;

/**
 * Created by spider man on 1/12/2016.
 */
public class OverviewFragment extends Fragment implements InitFragment {

    private ListView listViewFavorites;
    private ListView listViewgroups;

    private TextView textNickName;
    private TextView rankOnTheWorld;
    private TextView rankonTheLanguage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.overview_layout, null);

        init(view);
        initListener(view);

        return view;
    }

    @Override
    public void init(View view) {
        textNickName = (TextView) view.findViewById(R.id.overviewNickName);
        rankOnTheWorld = (TextView) view.findViewById(R.id.overviewRankOnTheWorld);
        rankonTheLanguage = (TextView) view.findViewById(R.id.overviewRankOnlanguage);
    }

    @Override
    public void initListener(View view) {
        /**
         * TODO: set listener for ui overview
         *
         */

    }

    @Override
    public void onPause() {
        super.onPause();
        System.gc();
    }

    @Override
    public void initGc() {

    }
}

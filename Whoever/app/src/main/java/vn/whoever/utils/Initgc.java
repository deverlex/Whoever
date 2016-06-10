package vn.whoever.utils;

import android.view.View;

/**
 * Created by Nguyen Van Do on 1/17/2016.
 * TODO: management memory on app
 */
public interface Initgc {

    public void init(View view);

    public void initListener(View view);

    public void initGc();
}
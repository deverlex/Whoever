package vn.whoever.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by Nguyen Van Do on 12/25/2015.
 * This class for handling convert type of size screen
 */
public class ConvertSizeDisplay {

    // Convert dp to pixel
    public static float convertDptoPx(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    // Convert pixel to dp
    public static float convertPxtoDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }
}

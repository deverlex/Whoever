package vn.whoever.utils;

import android.content.SharedPreferences;

import java.security.Key;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by spider man on 3/13/2016.
 */
public class Storage {

    /**
     * TODO: There are define list storage setting of whoever
     */
    private static SharedPreferences.Editor editor;

    private static SharedPreferences sharedPreferences;

    public static SharedPreferences getInstance() {
        return sharedPreferences;
    }

    public static void setInstance(SharedPreferences instance) {
        sharedPreferences = instance;
        editor = sharedPreferences.edit();
    }

    public static void editValues(String key, Integer value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public static void editValues(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public static void editValues(String key, Float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    public static void editValues(String key, Boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void editValue(String key, Set<String> arrStr) {
        editor.putStringSet(key, arrStr);
        editor.commit();
    }
}

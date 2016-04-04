package vn.whoever.models.dao;

import android.content.Context;

/**
 * Created by spider man on 1/10/2016.
 */
public class ConnectionDB {

    private static SQLiteAccess sqLiteAccess;

    public static SQLiteAccess getInstance(Context context) {
        if(sqLiteAccess == null) {
            sqLiteAccess = new SQLiteAccess(context);
            sqLiteAccess.createDatabase();
            return sqLiteAccess;
        }
        return sqLiteAccess;
    }

}

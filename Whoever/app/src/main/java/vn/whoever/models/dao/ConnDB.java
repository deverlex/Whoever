package vn.whoever.models.dao;

import android.content.Context;

/**
 * Created by spider man on 1/10/2016.
 */
public class ConnDB {

    private static SQLiteAccess sqLiteAccess = null;

    public static void getConn(Context context) {
        sqLiteAccess = new SQLiteAccess(context);
        sqLiteAccess.createDatabase();
    }

    public static  SQLiteAccess getConn() {
        return sqLiteAccess;
    }

}

package vn.whoever.models.dao;

import android.content.Context;

/**
 * Created by Nguyen Van Do on 1/10/2016.
 * Class for manageable connect to database
 */
public class ConnDB {

    private static SQLiteAccess sqLiteAccess = null;

    // another method need access database from this method
    public static void getConn(Context context) {
        sqLiteAccess = new SQLiteAccess(context);
        sqLiteAccess.createDatabase();
    }

    public static SQLiteAccess getConn() {
        return sqLiteAccess;
    }
}

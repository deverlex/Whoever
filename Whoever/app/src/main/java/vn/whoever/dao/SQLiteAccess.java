package vn.whoever.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLDataException;

/**
 * Created by spider man on 1/10/2016.
 */
public class SQLiteAccess extends SQLiteOpenHelper {

    private static String DB_NAME = "whoeverdb.sqlite";
    private static  String DB_PATH;
    private SQLiteDatabase myDatabase;
    private Context myContext;

    @SuppressLint("NewApi")
    public SQLiteAccess(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        DB_PATH = this.myContext.getApplicationInfo().dataDir + "/databases/";
    }

    public void createDatabase() {
        boolean dbExist = checkDatabase();
        if(dbExist) {
            //do nothing, database already
        } else {
            // By calling this method and empty database will be created into the default system path
            // of your application, so we are gonna be able to overwrite that database with our database
            this.getReadableDatabase();
            try {
                copyDatabase();
            } catch (IOException e) {
                e.printStackTrace();
                throw new Error("Copy database to default file system error !");
            }
        }
    }

    public void copyDatabase() throws IOException {
        //Open local database as the input stream
        InputStream inputStream = myContext.getAssets().open(DB_NAME);

        //path to just create empty database
        String outputFileNamme = DB_PATH + DB_NAME;

        //open empty database as the output stream
        OutputStream outputStream = new FileOutputStream(outputFileNamme);

        //transfer bytes from inputfile to outputfile
        byte[] buffer = new byte[516];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public boolean checkDatabase() {
        SQLiteDatabase database = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            database = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            if(database != null) {
                database.close();
            }
            return database != null ? true :false;
        }
    }

    @Override
    public synchronized void close() {
        if(myDatabase != null) {
            myDatabase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int args1, int args2) {
        
    }
}

package com.example.apttitude_crack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static String DB_NAME = "aptitude_crack.db";
    private static String DB_PATH = "";
    private static String TAG = "DataBaseHelper";
    private final Context mContext;
    private static SQLiteDatabase mDataBase;


    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;
    }

    public void createDataBase() throws IOException {
        if (!checkDataBase()) {
            getReadableDatabase();
            close();
            try {
                copyDataBase();
                Log.e(TAG, "createDatabase database created");
            } catch (IOException e) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private boolean checkDataBase() {
        return new File(DB_PATH + DB_NAME).exists();
    }

    private void copyDataBase() throws IOException {
        InputStream mInput = this.mContext.getAssets().open(DB_NAME);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        while (true) {
            int mLength = mInput.read(mBuffer);
            if (mLength > 0) {
                mOutput.write(mBuffer, 0, mLength);
            } else {
                mOutput.flush();
                mOutput.close();
                mInput.close();
                return;
            }
        }
    }

    public boolean openDataBase() throws SQLException {
        this.mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, 268435456);
        return this.mDataBase != null;
    }

    public synchronized void close() {
        if (this.mDataBase != null) {
            this.mDataBase.close();
        }
        super.close();
    }

    public void onCreate(SQLiteDatabase arg0) {
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public void UpdateDataBase() throws IOException {
        getReadableDatabase();
        close();
        try {
            copyUpdatedDataBase();
            Log.e(TAG, "createDatabase database created");
        } catch (IOException e) {
            throw new Error("ErrorCopyingDataBase");
        }
    }


    private void copyUpdatedDataBase() throws IOException {
        InputStream mInput = this.mContext.getAssets().open(DB_NAME);
        File file = new File(DB_PATH + DB_NAME);
        file.delete();
        if(file.exists()){
            file.getCanonicalFile().delete();
            if(file.exists()){
                this.mContext.deleteFile(file.getName());
            }
        }
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        while (true) {
            int mLength = mInput.read(mBuffer);
            if (mLength > 0) {
                mOutput.write(mBuffer, 0, mLength);
            } else {
                mOutput.flush();
                mOutput.close();
                mInput.close();
                return;
            }


        }
    }
}

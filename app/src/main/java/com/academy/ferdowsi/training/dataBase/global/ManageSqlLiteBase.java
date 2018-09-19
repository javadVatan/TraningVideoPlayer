package com.academy.ferdowsi.training.dataBase.global;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.academy.ferdowsi.training.global.GlobalFunction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ManageSqlLiteBase {
    protected SQLiteDatabase myDataBase;

    protected String mainPathDB = null, DB_PATH = null;
    protected String DATABASE_NAME = "";
    protected String DATABASE_NAME_Assets = "";
    protected boolean isZipDataBase = false;
    protected int modeOpen;
    private Context mContext;

    public ManageSqlLiteBase(Context context) {
        mContext = context;
    }

    private boolean checkDataBase() {
        boolean flag = false;
        File mainPath = GlobalFunction.getInstance().getpathOfSaveApk(mContext, modeOpen);
        if (mainPath != null) {
            mainPathDB = mainPath.getPath();
            DB_PATH = mainPathDB + File.separator + DATABASE_NAME;
            // + Constants.publicClassVar.glfu.getAppVersion(mContext);
            File dbFile = new File(DB_PATH);
            flag = dbFile.exists();
        }
        return flag;
    }

    protected boolean copyDataBase() {
        boolean res = false;
        if (mainPathDB != null) {
            String destPath;
            if (isZipDataBase) {
                destPath = mainPathDB + File.separator + DATABASE_NAME_Assets;
            } else {
                destPath = DB_PATH;
            }
            File dbFile = new File(destPath);
            try {
                InputStream is = mContext.getAssets().open(DATABASE_NAME_Assets);
                OutputStream os = new FileOutputStream(dbFile);
                byte[] buffer = new byte[1024 * 100];
                int read;
                while ((read = is.read(buffer)) != -1) {
                    os.write(buffer, 0, read);
                }
                os.flush();
                os.close();
                is.close();
                res = true;

                if (isZipDataBase) {
                    UnPackZip unPackZip = new UnPackZip();
                    unPackZip.unpackZip(mainPathDB + File.separator, DATABASE_NAME_Assets);
                    GlobalFunction.getInstance().deleteFile(destPath);
                }

            } catch (Exception e) {
                //  Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show();
                Log.e("ManageSqlLiteBase", e.toString());
            }
        }
        return res;
    }

    protected boolean createDataBase() {
        if (!checkDataBase()) {
            return copyDataBase();
        } else {
            return true;
        }
    }

    public synchronized SQLiteDatabase openDB() {
        if (checkQuery())
            return myDataBase;
        createDataBase();
        try {
            myDataBase = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            myDataBase = null;
        }
        return myDataBase;
    }

    private boolean checkQuery() {
        boolean res = true;
        String strSQL = "SELECT name FROM sqlite_master WHERE type='table' " + ";";
        try {
            Cursor cursor = myDataBase.rawQuery(strSQL, null);
            cursor.close();
        } catch (Exception e) {
            res = false;
        }
        return res;
    }

    public synchronized boolean closeDB() {
        boolean result;
        if (myDataBase != null) {
            myDataBase.close();
            myDataBase = null;
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean isOPenDB() {
        if (myDataBase != null) {
            if (myDataBase.isOpen()) {
                return checkDataBase();
            }
        }
        return false;
    }
}

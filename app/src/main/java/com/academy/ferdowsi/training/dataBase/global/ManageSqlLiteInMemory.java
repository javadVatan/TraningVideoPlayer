package com.academy.ferdowsi.training.dataBase.global;


import android.database.sqlite.SQLiteDatabase;

import com.academy.ferdowsi.training.Applications;
import com.academy.ferdowsi.training.global.GlobalFunction;


public class ManageSqlLiteInMemory extends ManageSqlLiteBase {

    private static ManageSqlLiteInMemory myInstance;

    private ManageSqlLiteInMemory() {
        super(Applications.getContext());
        DATABASE_NAME = "external_db.ll";
        modeOpen = GlobalFunction.DB_In_MemoryCard;
        DATABASE_NAME_Assets = DATABASE_NAME;
        isZipDataBase = false;
    }

    public static synchronized ManageSqlLiteInMemory getInstance() {
        if (myInstance == null) {
            myInstance = new ManageSqlLiteInMemory();
        }
        return myInstance;
    }

    public SQLiteDatabase getDB() {
        return myDataBase;
    }

}

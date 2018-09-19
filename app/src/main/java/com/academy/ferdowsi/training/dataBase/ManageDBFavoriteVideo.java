package com.academy.ferdowsi.training.dataBase;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.academy.ferdowsi.training.dataBase.global.ManageSqlLiteInMemory;
import com.academy.ferdowsi.training.video.struct.StructListVideo;

public class ManageDBFavoriteVideo {
    public static final String TABALE_MyFavoriteVideo = "VideoFavorite";
    public static final String ID_MyFavoriteVideo = "id";
    public static final String VIDEO_ID = "videoId";
    public static final String UID_MyFavoriteVideo = "uid";
    public static final String NAME_MyFavoriteVideo = "videoName";
    public static final String SEEN_MyFavoriteVideo = "seen";
    public static final String DATE_MyFavoriteVideo = "date";
    public static final String URL_Video_MyFavoriteVideo = "urlVideo";
    public static final String URL_lPerViewImage_MyFavoriteVideo = "urlPerViewImage";
    public static final String DURATION_MyFavoriteVideo = "duration";


    public static ManageDBFavoriteVideo myInstance;

    private ManageDBFavoriteVideo() {
    }

    public static ManageDBFavoriteVideo getInstance() {

        if (myInstance == null) {
            myInstance = new ManageDBFavoriteVideo();
        }
        if (!myInstance.setDB())
            myInstance = null;

        return myInstance;
    }

    private boolean setDB() {
        boolean res;
        SQLiteDatabase myDataBase;
        myDataBase = ManageSqlLiteInMemory.getInstance().openDB();
        if (myDataBase == null)
            res = false;
        else {
            res = createFavoriteTABLE();
        }
        return res;
    }

    public StructListVideo[] getAllFavoriteInfo() {
        StructListVideo[] listVideo;
        String[] cols = {ID_MyFavoriteVideo, UID_MyFavoriteVideo, VIDEO_ID, NAME_MyFavoriteVideo,
                SEEN_MyFavoriteVideo, DATE_MyFavoriteVideo, URL_Video_MyFavoriteVideo,
                URL_lPerViewImage_MyFavoriteVideo, DURATION_MyFavoriteVideo};
        String selection = null;

        Cursor cursor = getDataBase().query(TABALE_MyFavoriteVideo, cols, selection, null, null, null,
                null);
        cursor.moveToFirst();
        listVideo = new StructListVideo[cursor.getCount()];
        for (int i = 0; i < listVideo.length; i++) {
            listVideo[i] = new StructListVideo();

            listVideo[i].id = cursor.getInt(cursor.getColumnIndex(ID_MyFavoriteVideo));
            listVideo[i].uid = cursor.getString(cursor.getColumnIndex(UID_MyFavoriteVideo));
            listVideo[i].videoId = cursor.getString(cursor.getColumnIndex(VIDEO_ID));
            listVideo[i].videoName = cursor.getString(cursor.getColumnIndex(NAME_MyFavoriteVideo));
            listVideo[i].seen = cursor.getString(cursor.getColumnIndex(SEEN_MyFavoriteVideo));
            listVideo[i].date = cursor.getString(cursor.getColumnIndex(DATE_MyFavoriteVideo));
            listVideo[i].urlVideo = cursor.getString(cursor.getColumnIndex(URL_Video_MyFavoriteVideo));
            listVideo[i].urlPerViewImage = cursor.getString(cursor.getColumnIndex(
                    URL_lPerViewImage_MyFavoriteVideo));
            listVideo[i].duration = cursor.getInt(cursor.getColumnIndex(DURATION_MyFavoriteVideo));

            cursor.moveToNext();
        }
        cursor.close();
        return listVideo;
    }

    public boolean checkIsDataAlreadyInDBorNot(String videoId, String uid) {
        boolean status;
        String[] cols = {VIDEO_ID, UID_MyFavoriteVideo};

        String Query = "Select * from " + TABALE_MyFavoriteVideo + " where " + VIDEO_ID + " like '" + videoId
                + "' and " + UID_MyFavoriteVideo + " like '" + uid + "'";

        Cursor cursor = getDataBase().rawQuery(Query, null);
        status = cursor.getCount() > 0;
        cursor.close();
        return status;
    }

    public StructListVideo getAllFavoriteInfo(int id) {
        StructListVideo listVideo;
        String[] cols = {ID_MyFavoriteVideo, UID_MyFavoriteVideo, VIDEO_ID, NAME_MyFavoriteVideo,
                SEEN_MyFavoriteVideo, DATE_MyFavoriteVideo, URL_Video_MyFavoriteVideo,
                URL_lPerViewImage_MyFavoriteVideo, DURATION_MyFavoriteVideo};
        String selection = ID_MyFavoriteVideo + "=" + id;

        Cursor cursor = getDataBase().query(TABALE_MyFavoriteVideo, cols, selection, null, null, null,
                null);
        cursor.moveToFirst();
        listVideo = new StructListVideo();

        listVideo.id = cursor.getInt(cursor.getColumnIndex(ID_MyFavoriteVideo));
        listVideo.uid = cursor.getString(cursor.getColumnIndex(UID_MyFavoriteVideo));
        listVideo.videoId = cursor.getString(cursor.getColumnIndex(VIDEO_ID));
        listVideo.videoName = cursor.getString(cursor.getColumnIndex(NAME_MyFavoriteVideo));
        listVideo.seen = cursor.getString(cursor.getColumnIndex(SEEN_MyFavoriteVideo));
        listVideo.date = cursor.getString(cursor.getColumnIndex(DATE_MyFavoriteVideo));
        listVideo.urlVideo = cursor.getString(cursor.getColumnIndex(URL_Video_MyFavoriteVideo));
        listVideo.urlPerViewImage = cursor.getString(cursor.getColumnIndex(
                URL_lPerViewImage_MyFavoriteVideo));
        listVideo.duration = cursor.getInt(cursor.getColumnIndex(DURATION_MyFavoriteVideo));


        cursor.close();

        return listVideo;

    }

    public int insertFavorite(StructListVideo structListVideo) {
        long res;
        ContentValues values = new ContentValues();
        values.put(VIDEO_ID, structListVideo.getVideoId());
        values.put(UID_MyFavoriteVideo, structListVideo.getUid());
        values.put(NAME_MyFavoriteVideo, structListVideo.getVideoName());
        values.put(SEEN_MyFavoriteVideo, structListVideo.getSeen());
        values.put(DATE_MyFavoriteVideo, structListVideo.getDate());
        values.put(URL_Video_MyFavoriteVideo, structListVideo.getUrlVideo());
        values.put(URL_lPerViewImage_MyFavoriteVideo, structListVideo.getUrlPerViewImage());
        values.put(DURATION_MyFavoriteVideo, structListVideo.getDuration());

        res = getDataBase().insert(TABALE_MyFavoriteVideo, null, values);

        return (int) res;
    }

    public boolean deleteFavorite(String videoId) {
        String where = VIDEO_ID + "=" + videoId;
        boolean res = getDataBase().delete(TABALE_MyFavoriteVideo, where, null) > 0;

        return res;
    }

    private SQLiteDatabase getDataBase() {
        return ManageSqlLiteInMemory.getInstance().getDB();
    }

    private boolean createFavoriteTABLE() {
        boolean res = true;
        try {
            String sqlDataStore = "create table if not exists " + TABALE_MyFavoriteVideo + " (" +
                    ID_MyFavoriteVideo + " integer PRIMARY KEY AUTOINCREMENT," +
                    VIDEO_ID + " text," +
                    NAME_MyFavoriteVideo + " text," +
                    SEEN_MyFavoriteVideo + " text," +
                    DATE_MyFavoriteVideo + " text," +
                    URL_Video_MyFavoriteVideo + " text," +
                    URL_lPerViewImage_MyFavoriteVideo + " text," +
                    DURATION_MyFavoriteVideo + " integer," +
                    UID_MyFavoriteVideo + "  text " + ");";


            getDataBase().execSQL(sqlDataStore);
        } catch (Exception e) {
            e.printStackTrace();
            res = false;
        }
        return res;
    }

}

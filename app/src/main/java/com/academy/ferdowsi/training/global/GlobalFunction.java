package com.academy.ferdowsi.training.global;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;

import com.academy.ferdowsi.training.R;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
import com.mobiliha.activity.DownloadQueue;
import com.mobiliha.activity.DownloadService;
import com.mobiliha.activity.ShowNewsActivity;
import com.mobiliha.activity.ShowOpinionList;
import com.mobiliha.activity.ShowTopicsLesson;
import com.mobiliha.database.ManageDBDownload;
import com.mobiliha.database.ManageDBLessons;
import com.mobiliha.database.ManageDBOpinion;
import com.mobiliha.database.ManageDBSections;
import com.mobiliha.news.CheckNews;
import com.mobiliha.news.com.example.win81.note.ManageGPRS2;
import com.mobiliha.news.SelectInternet;
import com.mobiliha.payment.ManageGPRSPayment;
import com.mobiliha.payment.PurchaseStruct;
import com.mobiliha.perference.GetPreference;
import com.mobiliha.struct.StructLesson;
import com.mobiliha.struct.StructSection;
import com.mobiliha.util.Purchase;
*/

public class GlobalFunction {
    public static final int DB_In_MemoryCard = 1;
    public static final int DB_In_PrivateDir = 2;

    // private Context mcontext;
    private static final String SeperatorPhoneModel = "##";
    private static final String nullStr = "null";
    private static GlobalFunction myInstance;
    private String imei = "";

    public GlobalFunction() {
    }

    public static synchronized GlobalFunction getInstance() {
        if (myInstance == null) {
            myInstance = new GlobalFunction();
        }
        return myInstance;
    }


    public File getpathOfSaveApk(Context mcontext, int saveMode) {
        File file;
        if (saveMode == DB_In_MemoryCard) {
            String mainPath = getPathOfMemoryForAppFile(mcontext);
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
                file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),
                        mainPath);
            else
                file = mcontext.getFilesDir();
        } else {
            file = mcontext.getFilesDir();
        }
        if (!file.exists())
            file.mkdirs();
        return file;
    }

    public void openFile(Context mcontext, String fileName) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(fileName)),
                "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mcontext.startActivity(intent);
    }

    public String getAppName(Context context) {
        PackageManager packagemanager;
        packagemanager = context.getPackageManager();
        try {
            ApplicationInfo applicationinfo = packagemanager.getApplicationInfo(
                    context.getPackageName(), 0);
            return packagemanager.getApplicationLabel(applicationinfo).toString();
        } catch (Exception e) {
            return Constants.app_dir;
        }
    }

    public String getPathOfMemoryForAppFile(Context context) {
        return Constants.app_dir + File.separator + getAppName(context);
    }

    public boolean isConnection(Context mContext) {
        ConnectivityManager conMgr = (ConnectivityManager) mContext.getSystemService(
                Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfoWiFi, networkInfoMobile;
        networkInfoMobile = conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        networkInfoWiFi = conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        return (networkInfoWiFi != null &&
                networkInfoWiFi.getState() == NetworkInfo.State.CONNECTED) ||
                (networkInfoMobile != null &&
                        networkInfoMobile.getState() == NetworkInfo.State.CONNECTED);
    }


    public void shareText(Context mcontext, String text) {
        Intent intent;
        intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (mcontext.getPackageManager().resolveActivity(intent,
                PackageManager.MATCH_DEFAULT_ONLY) != null)
            mcontext.startActivity(intent);
    }

    public void openBrowserChrome(Context mContext, String url) {
        // Use a CustomTabsIntent.Builder to configure CustomTabsIntent.
// Once ready, call CustomTabsIntent.Builder.build() to create a CustomTabsIntent
// and launch the desired Url with CustomTabsIntent.launchUrl()
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.enableUrlBarHiding();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(mContext, Uri.parse(url));
        builder.setToolbarColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
    }

    public void deleteFile(String fileName) {
        File file = new File(fileName);
        file.delete();
    }

    public String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            StringBuilder hashtext = new StringBuilder(number.toString(16));
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext.insert(0, "0");
            }
            return hashtext.toString();
        } catch (NoSuchAlgorithmException e) {
            return input;
        }
    }


}

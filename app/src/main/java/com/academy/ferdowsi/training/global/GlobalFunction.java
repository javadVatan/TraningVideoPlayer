package com.academy.ferdowsi.training.global;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

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

    public void setGlobalFont(View view, int color, int size) {
        TextView titleTextView = (TextView) view;
        titleTextView.setTypeface(Constants.typeface, Typeface.NORMAL);
        if (size != -1) {
            titleTextView.setTextSize(size);
        }
        if (color != -1) {
            titleTextView.setTextColor(color);
        }

    }

    public void setGlobalFont(View view) {
        setGlobalFont(view, Constants.colorfont, Constants.sizefont);
    }

 /*   public boolean installMarket(Context mContext) {
        String bazzarPakageName = "com.farsitel.bazaar";
        switch (VersionType.Ver_Type) {
            case com.example.win81.note.ManageGPRS2.Ver_Type_ONLine_UPdate:
            case com.example.win81.note.ManageGPRS2.Ver_Type_MTH:
            case com.example.win81.note.ManageGPRS2.Ver_Type_Bazzar:
                bazzarPakageName = "com.farsitel.bazaar";
                break;
            case com.example.win81.note.ManageGPRS2.Ver_Type_ParsHub:
                bazzarPakageName = "net.jhoobin.jhub";
                break;
            case com.example.win81.note.ManageGPRS2.Ver_Type_MyKet:
                bazzarPakageName = "ir.mservices.market";
                break;
            case com.example.win81.note.ManageGPRS2.Ver_Type_Candoo:
                bazzarPakageName = "com.ada.market";
                break;
            case com.example.win81.note.ManageGPRS2.Ver_Type_IranApps:
                bazzarPakageName = "ir.tgbs.android.iranapp";
                break;

            case com.example.win81.note.ManageGPRS2.Ver_Type_PlayStore:
                bazzarPakageName = "com.android.vending";
                break;
            default:
                break;
        }

        return (appInstalledOrNot(mContext, bazzarPakageName));
    }*/

    private boolean hasOneMarket(Context mContext) {
        boolean isInstall = false;
        String[] packageMarket =
                {"com.farsitel.bazaar", "net.jhoobin.jhub", "ir.mservices.market", "com.ada.market",
                        "ir.tgbs.android.iranapp", "com.android.vending"};
        for (int i = 0; i < packageMarket.length; i++) {
            if (appInstalledOrNot(mContext, packageMarket[i])) {
                isInstall = true;
                break;
            }
        }

        return isInstall;

    }

    public File getpathOfSaveApk(Context mcontext, int saveMode) {
        File file;
        if (saveMode == DB_In_MemoryCard) {
            String mainPath = getPathOfMemoryForMTHFile(mcontext);
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

    public boolean saveApktoRam(String source, String dest) {
        FileInputStream in;
        FileOutputStream out;
        try {

            in = new FileInputStream(source);
            out = new FileOutputStream(dest);
            byte data[] = new byte[1024 * 10];
            int read;
            while ((read = in.read(data)) != -1) {
                out.write(data, 0, read);
            }
            in.close();
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getAppPkgPath(Context context) {
        String pathOfInstall;
        String myPackagename;
        List<ApplicationInfo> list;
        int i;
        pathOfInstall = null;
        PackageManager packagemanager = context.getPackageManager();
        list = packagemanager.getInstalledApplications(0);
        myPackagename = context.getPackageName();
        for (i = 0; i < list.size(); i++) {
            ApplicationInfo info = list.get(i);
            if (info.packageName.equalsIgnoreCase(myPackagename)) {
                pathOfInstall = info.sourceDir;
                break;
            }
        }

        return pathOfInstall;
    }

   /* public void setScreenStatus(Window window, Context mContext) {
        boolean keepScreenOn = GetPreference.getInstance(mContext).getScreenOn();
        if (keepScreenOn) {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }*/

    public String getVersion(Context mcontext) {
        String ver = "4.0";
        try {
            ver = mcontext.getPackageManager().getPackageInfo(mcontext.getPackageName(),
                    0).versionName;
        } catch (Exception exp) {
            ver = "4.0";
        }
        return ver;
    }

    public void openFile(Context mcontext, String fileName) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(fileName)),
                "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mcontext.startActivity(intent);
    }

    /* public String getIMEI(Context mContext) {
         if (imei == null || imei.length() == 0) {
             TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(
                     Context.TELEPHONY_SERVICE);
             imei = telephonyManager.getDeviceId();

             if (imei == null || (imei != null && imei.length() == 0)) {
                 WifiManager wimanager = (WifiManager) mContext.getSystemService(
                         Context.WIFI_SERVICE);
                 imei = wimanager.getConnectionInfo().getMacAddress();
             }
         }

         return imei;
     }
 */
    public String getPhoneName() {
        String PhoneNAmeModel = "unknown";
        PhoneNAmeModel = (android.os.Build.BRAND + SeperatorPhoneModel + android.os.Build.MODEL);
        PhoneNAmeModel +=
                SeperatorPhoneModel + "Android Version " + android.os.Build.VERSION.RELEASE;
        return PhoneNAmeModel;
    }

    public String getPhoneMod_For_KEY() {
        String PhoneNAmeModel = "unknown";
        try {
            PhoneNAmeModel = (android.os.Build.BRAND + android.os.Build.MODEL);
        } catch (Exception exp) {
            exp.printStackTrace();
        }


        return PhoneNAmeModel;
    }

    public int getVersionCode(Context mcontext, String packageName) {
        int ver = 122;
        try {
            ver = mcontext.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (Exception exp) {
            ver = 122;
        }
        return ver;
    }

    public int getFontHeight(Paint paint) {
        int top, bot;
        FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        top = fontMetricsInt.top;
        bot = fontMetricsInt.bottom;
        return Math.abs(top - bot);
    }

    public float getscaledDensityDevise(WindowManager window) {
        DisplayMetrics metrics = new DisplayMetrics();
        window.getDefaultDisplay().getMetrics(metrics);
        return metrics.scaledDensity;
    }

    public int getDensityDevise(WindowManager window) {
        DisplayMetrics metrics = new DisplayMetrics();
        window.getDefaultDisplay().getMetrics(metrics);
        return metrics.densityDpi;
    }

    /*public void showAlertErrorCon(Context mContext) {
        SelectInternet selectInternet = new SelectInternet();
        selectInternet.prepare(mContext, SelectInternet.SendInfoMode);
        selectInternet.onCreateDialog();
    }*/

    public void showGift(Context mcontext) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.mojemobile.ir/sb24/m/"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (mcontext.getPackageManager().resolveActivity(intent,
                PackageManager.MATCH_DEFAULT_ONLY) != null)
            mcontext.startActivity(intent);
    }

    public void showSite(Context mcontext) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.mth-co.ir"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (mcontext.getPackageManager().resolveActivity(intent,
                PackageManager.MATCH_DEFAULT_ONLY) != null)
            mcontext.startActivity(intent);
    }

   /* public void showNews(Activity mcontext) {
        Intent intent = new Intent(mcontext, ShowNewsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mcontext.startActivity(intent);
        mcontext.overridePendingTransition(R.anim.slide_in_bottom, R.anim.stay);
    }*/

  /*  public void setHeaderText(View currView, String title) {
        TextView title_tv = (TextView) currView.findViewById(R.id.tvSubjectPage);

        title_tv.setTypeface(Constants.iranSansLight);
        title_tv.setText(title);
    }*/

  /*  public boolean splitSupport(String records, Context mContext) {
        final int typeRecord = 0;
        String[] feids = records.split(CheckNews.feildSplit);
        boolean insertDone = false;
        int _type = Integer.parseInt(feids[typeRecord].trim());
        if (_type == CheckNews.SupportType) {


            int max = (feids.length - 1) / 2;
            int i = 1;
            int code = 0;
            String Reply;
            ManageDBOpinion manageDBOpinion = ManageDBOpinion.getInstance(mContext);
            while (i < feids.length) {
                try {
                    code = Integer.parseInt(feids[i]);
                } catch (Exception exp) {
                    exp.printStackTrace();
                }

                i++;
                Reply = feids[i].trim();
                int status = ManageDBOpinion.SEND_STATUS;
                if (Reply.equalsIgnoreCase(CheckNews.defaultLink)) {
                    status = ManageDBOpinion.READ_STATUS;
                } else if (Reply.length() > 0) {
                    status = ManageDBOpinion.ANSWERD_STATUS;
                }
                if (!Reply.equalsIgnoreCase(CheckNews.defaultLink) && Reply.length() > 0) {
                    insertDone = true;
                }
                manageDBOpinion.updateOpinion(code, Reply, status);
                i++;
            }
        }
        return insertDone;
    }*/

    public String creatPatchSave() {

        String PatchSaveTMP =
                Environment.getExternalStorageDirectory().getAbsolutePath() + Constants.IMAGE_PATH;
        File filecon = new File(PatchSaveTMP);
        if (filecon == null || !filecon.exists()) {
            filecon.mkdir();
        }
        filecon = null;
        return PatchSaveTMP;
    }

    public String getAppName(Context context) {
        PackageManager packagemanager;
        packagemanager = context.getPackageManager();
        try {
            ApplicationInfo applicationinfo = packagemanager.getApplicationInfo(
                    context.getPackageName(), 0);
            return packagemanager.getApplicationLabel(applicationinfo).toString();
        } catch (Exception e) {
            return Constants.mth_dir;
        }
    }

    public String getPathOfMemoryForMTHFile(Context context) {
        return Constants.mth_dir + File.separator + getAppName(context);
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

    public int getVersionCode(Context mcontext) {
        int ver = 1;
        try {
            ver = mcontext.getPackageManager().getPackageInfo(mcontext.getPackageName(),
                    0).versionCode;
        } catch (Exception exp) {
            ver = 1;
        }
        return ver;
    }

    @SuppressLint("NewApi")
    public String getDeviceSerial(Context mContext) {
        String serial = "~#";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD)
            serial = android.os.Build.SERIAL;
        return serial;
    }

    public String getAndroidId(Context mContext) {
        String id = Settings.Secure.getString(mContext.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return id;
    }

    /*  public String getWiFi(Context mContext) {
          WifiManager wimanager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
          String wifi = wimanager.getConnectionInfo().getMacAddress();
          return wifi;
      }
  */
    public int[] splitAmalvand(String RegisterEquation) {

        String[] records = RegisterEquation.split("~~");
        int[] amalVand = new int[records.length];
        for (int i = 0; i < amalVand.length; i++) {
            amalVand[i] = Integer.parseInt(records[i]);
        }

        return amalVand;
    }

    @SuppressLint("NewApi")
    public void disableHardwareAccelerated(View view) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB)
            view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    public long getAvailableSpaceByte(String path) {
        try {
            File f = new File(path);
            StatFs stat = new StatFs(f.getPath());
            long bytesAvailable = (long) stat.getBlockSize() * (long) stat.getAvailableBlocks();
            return bytesAvailable;
        } catch (Exception e) {
            return Integer.MAX_VALUE;
        }
    }

    public boolean appInstalledOrNot(Context mcontext, String uri) {
        PackageManager pm = mcontext.getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    public void updateData(Context mContext) {
        //        UpdateContent updateContent = new UpdateContent(
        //                Constants.publicClassVar.contentInfo, mContext);
        //        updateContent.update();
    }

  /*  public void CallSuportInMarket(Context mContext) {

        switch (VersionType.Ver_Type) {

            case ManageGPRSPayment.Ver_Type_MTH:
            case ManageGPRSPayment.Ver_Type_Bazzar:
                Intent intent;
                if (installMarket(mContext)) {
                    intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("bazaar://details?id=" + mContext.getPackageName()));
                    intent.setAction(Intent.ACTION_EDIT);
                    if (mContext.getPackageManager().resolveActivity(intent,
                            PackageManager.MATCH_DEFAULT_ONLY) != null)
                        mContext.startActivity(intent);
                } else if (hasOneMarket(mContext)) {
                    intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + mContext.getPackageName()));
                    if (mContext.getPackageManager().resolveActivity(intent,
                            PackageManager.MATCH_DEFAULT_ONLY) != null)
                        mContext.startActivity(intent);
                }
                break;
            case ManageGPRSPayment.Ver_Type_ParsHub:
                if (installMarket(mContext)) {
                    intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("parshub://APP?uuidString=920456873"));
                    if (mContext.getPackageManager().resolveActivity(intent,
                            PackageManager.MATCH_DEFAULT_ONLY) != null)
                        mContext.startActivity(intent);
                } else if (hasOneMarket(mContext)) {
                    intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + mContext.getPackageName()));
                    if (mContext.getPackageManager().resolveActivity(intent,
                            PackageManager.MATCH_DEFAULT_ONLY) != null)
                        mContext.startActivity(intent);
                }
                break;
            case ManageGPRSPayment.Ver_Type_MyKet:
                if (hasOneMarket(mContext)) {
                    intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + mContext.getPackageName()));
                    if (mContext.getPackageManager().resolveActivity(intent,
                            PackageManager.MATCH_DEFAULT_ONLY) != null)
                        mContext.startActivity(intent);
                }
                break;
            case ManageGPRSPayment.Ver_Type_Candoo:
                if (hasOneMarket(mContext)) {
                    intent = new Intent(Intent.ACTION_VIEW,

                            Uri.parse("market://details?id=" + mContext.getPackageName()));
                    if (mContext.getPackageManager().resolveActivity(intent,
                            PackageManager.MATCH_DEFAULT_ONLY) != null)
                        mContext.startActivity(intent);
                }
                break;
            case ManageGPRSPayment.Ver_Type_Iranapps:
                if (hasOneMarket(mContext)) {
                    intent = new Intent(Intent.ACTION_VIEW,

                            Uri.parse("market://details?id=" + mContext.getPackageName()));
                    if (mContext.getPackageManager().resolveActivity(intent,
                            PackageManager.MATCH_DEFAULT_ONLY) != null)
                        mContext.startActivity(intent);
                }
                break;
            case ManageGPRSPayment.Ver_Type_PlayStore:
                if (installMarket(mContext)) {
                    intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + mContext.getPackageName()));
                    if (mContext.getPackageManager().resolveActivity(intent,
                            PackageManager.MATCH_DEFAULT_ONLY) != null)
                        mContext.startActivity(intent);
                } else if (hasOneMarket(mContext)) {
                    intent = new Intent(Intent.ACTION_VIEW,

                            Uri.parse("market://details?id=com.mobiliha.badesaba"));
                    if (mContext.getPackageManager().resolveActivity(intent,
                            PackageManager.MATCH_DEFAULT_ONLY) != null)
                        mContext.startActivity(intent);
                }
                break;

        }

    }*/

    public boolean runApk(Context mcontext, String packageName) {
        boolean res = true;
        PackageManager pm = mcontext.getPackageManager();
        try {
            Intent launchIntent = pm.getLaunchIntentForPackage(packageName);
            mcontext.startActivity(launchIntent);
        } catch (Exception e) {
            e.printStackTrace();
            res = false;
        }
        return res;
    }

    /*public void sendServerError(Context mContext, String urlStr, String responce) {
        if (isConnection(mContext)) {
            com.example.win81.note.ManageGPRS2 manageGPRS2 = new com.example.win81.note.ManageGPRS2(mContext);
            String imei = getIMEI(mContext);
            String vercode = getVersionCode(mContext) + "";
            String model = getPhoneName();
            if (responce == null) {
                responce = "";
            }
            String _serialDev = getDeviceSerial(mContext);
            //            manageGPRS2.SendError(imei, vercode, urlStr, responce, model,
            //                    _serialDev);
        }
    }*/

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

    public int getMTH_app(Context mContext) {
        int mth_app = 1;
        boolean installKimia = appInstalledOrNot(mContext, Constants.Kimiya_URI);
        boolean installBadesaba = appInstalledOrNot(mContext, Constants.BadeSaba_URI);
        boolean installBabonNaeim = appInstalledOrNot(mContext, Constants.BabonNaeim_URI);
        if (installKimia && installBabonNaeim && installBadesaba) {
            mth_app = 5;
        } else if (installKimia) {

            if (installBadesaba && !installBabonNaeim) {
                mth_app = 14;
            } else if (!installBadesaba && installBabonNaeim) {
                mth_app = 13;
            } else {
                mth_app = 11;
            }
        } else if (installBadesaba) {

            if (installBabonNaeim) {
                mth_app = 6;

            } else {
                mth_app = 8;
            }
        } else if (installBabonNaeim) {
            mth_app = 7;
        }

        return mth_app;
    }

    public void deleteFile(String fileName) {
        File file = new File(fileName);
        file.delete();
    }

    public final int unSignedShort(short info) {
        return info >= 0 ? info : info + Math.abs(Short.MIN_VALUE * 2);
    }

    public boolean isActiveCode(String shenase) {
        if (Constants.ActiveProducts == null || Constants.ActiveProducts.length == 0) {
            return false;
        }

        boolean isActive = false;
        for (int i = 0; i < Constants.ActiveProducts.length; i++)
            if (Constants.ActiveProducts[i].equalsIgnoreCase(shenase)) {
                isActive = true;
                break;
            }

        return isActive;
    }

   /* public boolean isMaghta(Context mContext, String shenase) {
        boolean isActive = false;
        ManageDBSections manageDBSections = ManageDBSections.getInstance(mContext);
        StructSection[] structSection = manageDBSections.getAllSectionsInfo();
        for (int i = 0; i < structSection.length; i++) {
            if (structSection[i].shenase.equalsIgnoreCase(shenase)) {
                isActive = true;
                break;
            }
        }
        return isActive;
    }*/

   /* public Toast showTextToast(Context mContext, CharSequence text) {
        return showToast(mContext, R.layout.toast, R.id.toast_text, text, Toast.LENGTH_LONG);
    }*/

    public Toast showToast(Context mContext, int layoutResId, int textViewId, CharSequence text,
                           int duration) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(layoutResId, null);
        Toast toast = new Toast(mContext);
        toast.setGravity(Gravity.TOP, 0, 0);
        if (textViewId != 0) {
            TextView tv = layout.findViewById(textViewId);
            tv.setText(text);
            tv.setTypeface(Constants.iranSansLight);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
        }


        toast.setDuration(duration);
        toast.setView(layout);
        toast.show();
        return toast;
    }

    /*public void manageSupport(Context mcContext) {
        Intent intent = new Intent(mcContext, ShowOpinionList.class);
        mcContext.startActivity(intent);

    }*/

   /* public int getSectionIDFromLessonID(int lessonID) {
        String lessinStr = "" + lessonID;
        String sectionStr = lessinStr.substring(0, ShowTopicsLesson.SectionLength);
        int sectionID = Integer.parseInt(sectionStr);
        return sectionID;
    }*/

 /*   public int getLessonTagFromLessonID(int lessonID) {
        String lessonStr = "" + lessonID;
        int begin = ShowTopicsLesson.SectionLength;

        String lessonTagStr = lessonStr.substring(begin, lessonStr.length());
        int lessonTag = Integer.parseInt(lessonTagStr);
        return lessonTag;
    }*/

   /* public int[] getIdLessonFromShenase(Context mContext, String[] activeProduct) {
        final String seprator = "#";
        int[] res;
        String tempIDStr = "", lessonIDstr;
        StructSection section;
        StructLesson lesson, lessons[];
        ManageDBSections dbSections = ManageDBSections.getInstance(mContext);
        ManageDBLessons dbLessons = ManageDBLessons.getInstance(mContext);

        for (int i = 0; i < activeProduct.length; i++) {
            section = dbSections.getSectionsInfo(activeProduct[i]);
            if (section == null) {
                lesson = dbLessons.getLessonInfo(activeProduct[i]);
                if (lesson != null) {
                    lessonIDstr = lesson.mainID + seprator;
                    if (!tempIDStr.contains(lessonIDstr)) {
                        tempIDStr += lessonIDstr;
                    }
                }
            } else {
                lessons = dbLessons.getAllLessonsInfo(section.mainID);
                for (int j = 0; j < lessons.length; j++) {
                    lessonIDstr = lessons[j].mainID + seprator;
                    if (!tempIDStr.contains(lessonIDstr)) {
                        tempIDStr += lessonIDstr;
                    }
                }
            }
        }

        String[] IDs = {};
        if (tempIDStr.length() > 0)
            IDs = tempIDStr.split(seprator);
        res = new int[IDs.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = Integer.parseInt(IDs[i]);
        }
        return res;
    }
*/
   /* public boolean openPDF(Context mContext, String shenase) {
        boolean isOpen = true;
        try {
            String Path = DownloadQueue.getSavePath(mContext, ManageDBDownload.PdfDownload);
            File file = new File(Path + shenase + ".pdf");

            Intent viewIntent = new Intent();
            viewIntent.setAction(Intent.ACTION_VIEW);
            viewIntent.setDataAndType(Uri.fromFile(file), "application/pdf");
            mContext.startActivity(viewIntent);
        } catch (Exception exp) {
            isOpen = false;
            exp.printStackTrace();
        }
        return isOpen;

    }*/

   /* public String getDownloadFileName(int lessonID, int type, boolean setPasvand) {
        String res = "";
        switch (type) {
            case ManageDBDownload.PdfDownload:
                res = "" + lessonID;
                break;
            case ManageDBDownload.ContentDownload:
                res = "" + lessonID;
                break;
            case ManageDBDownload.Topic1Download:
                res = "" + lessonID;
                break;
        }
        if (setPasvand) {
            switch (type) {
                case ManageDBDownload.PdfDownload:
                    res += DownloadService.PdfPasvand;
                    break;
                case ManageDBDownload.ContentDownload:
                    res += DownloadService.DataBasePasvand;
                    break;
                case ManageDBDownload.Topic1Download:
                    res += DownloadService.DataBasePasvand;
                    break;
            }
        }
        return res;
    }*/

   /* public boolean isFile(Context mContext, int lessonID, int type) {
        String pathSave = DownloadQueue.getSavePath(mContext, type);
        String fileName = pathSave + getDownloadFileName(lessonID, type, true);
        File file = new File(fileName);
        if (file.exists())
            return true;
        else
            return false;
    }*/

  /*  public boolean deleteFile(Context mContext, int lessonID, int type) {
        boolean isDelete = false;

        String pathSave = DownloadQueue.getSavePath(mContext, type);
        String fileName = pathSave + getDownloadFileName(lessonID, type, true);
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
            isDelete = true;
        }
        return isDelete;
    }*/

    /*  public PurchaseStruct getPurchaseStruct(Purchase _myPurchase) {
          PurchaseStruct purchaseStruct = new PurchaseStruct();
          if (_myPurchase == null) {
              return null;
          }
          purchaseStruct.orderid = _myPurchase.getOrderId();
          purchaseStruct.productId = _myPurchase.getSku();
          purchaseStruct.signature = _myPurchase.getSignature();
          purchaseStruct.time = Long.toString(_myPurchase.getPurchaseTime());
          purchaseStruct.token = _myPurchase.getToken();
          purchaseStruct.status = _myPurchase.getPurchaseState();
          return purchaseStruct;
      }
  */
    public void openBrowser(Context mContext, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        mContext.startActivity(intent);
    }

}

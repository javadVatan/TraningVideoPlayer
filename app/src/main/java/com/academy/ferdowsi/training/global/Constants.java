package com.academy.ferdowsi.training.global;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;


public class Constants {

    /**
     * Screen Const
     */
    public static final int NoData = -1;
    public static final int SMALL_SCREEN = 0;
    public static final int MEDIUM_SCREEN = 1;
    public static final int LARGE_SCREEN = 2;
    public static final int XLARGE_SCREEN = 3;
    public static final int[] SCREEN_SIZES = {240, 450, 750, 1000};
    /**
     * Path of files
     */
    public static final String FILES_PATH = "mth.da/";
    public static final String ABOUT_HELP_PATH = FILES_PATH + "2/";
    public static final String HelpQuestion_PATH = "mth.he";
    public static final String IMAGE_PATH = "/NomreBehtarImage/";
    /**
     * Other Consts
     */
    public final static String Tehran_GMT = "+3:30";
    public static final String FontTTFPath = "fonts";
    public static final String BadeSaba_URI = "com.mobiliha.badesaba";
    public static final String BabonNaeim_URI = "com.mobiliha.babonnaeim";
    public static final String Kimiya_URI = "com.mobiliha.kimia";
    // public static byte FolderIndex = 0;
    public final static String mth_dir = "mth_app";
    public static final int MaxRunNumbers = 7;
    public static final String HablolMatin_PackageName = "com.mobiliha.hablolmatin";
    public static final String BabonNaeim_PackageName = "com.mobiliha.babonnaeim";
    public static final String Kimiya_PackageName = "com.mobiliha.kimia";
    public static final String BadeSaba_PackageName = "com.mobiliha.badesaba";
    public static final String Bazaar_PackageName = "com.farsitel.bazaar";
    public static final String NomreBehtarDataFolderName = "NomreBehtar_Data";
    public static final String[] deleteDBCodeProduct =
            {"elm2", "r6", "r9", "d5", "r1", "r0", "d0", "a0", "v0"};
    public static int SCREEN_WIDTH = NoData;
    public static int SCREEN_HEIGHT = NoData;
    public static int SCREEN_SIZE = MEDIUM_SCREEN;
    public static String FolderPath = "";
    // public static MainActivity Parent;
    public static Typeface iranSans = null;
    public static Typeface iranSansLight = null;
    public static Typeface typeface = null;
    public static Typeface englishFont = null;
    public static int sizefont = NoData;
    public static int colorfont;
    public static int ID_Mess_UpdateSoft = 0;
    public static boolean isAllShowContent = true;
    public static boolean isTrial = true;
    public static int[] AmalVand;
    public static String HashCode;
    public static boolean isMasraf;
    public static String productID;
    public static String ActiveProducts[] = {};
    //  public static WebViewPayment webViewPayment = null;
    public static float myScaled = NoData;
    public static int myDensity = NoData;
    public static SQLiteDatabase myDataBasePrivate;
    public static String seed = "*#nomrebehtar-co$";

}

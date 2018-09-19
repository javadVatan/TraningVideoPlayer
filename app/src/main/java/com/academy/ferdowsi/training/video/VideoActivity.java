package com.academy.ferdowsi.training.video;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.academy.ferdowsi.training.R;
import com.academy.ferdowsi.training.global.Constants;
import com.academy.ferdowsi.training.video.fragment.ShowVideo_Frg;
import com.academy.ferdowsi.training.video.fragment.TabsVideo_Frg;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class VideoActivity extends FragmentActivity {

    public static FragmentTransaction fragmentTransaction;
    public static FragmentManager fragmentManager;
    private static Fragment currFragment;

    public static void switchFragment(Fragment fragment, boolean addToBackStack, String tag) {
        // update the main content by replacing fragments
        fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right,R.anim.slide_in_left, R.anim.slide_out_right);
        fragmentTransaction.add(R.id.container, fragment, tag);
        // TODO: 10/10/2016  fragmentTransaction.replace replaced with fragmentTransaction.add for bug.
        if (addToBackStack)
            fragmentTransaction.addToBackStack(tag);

        fragmentTransaction.commit();
        currFragment = fragment;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_actvity);

        Constants.iranSans = Typeface.createFromAsset(getAssets(), "IRANSansMobile(FaNum).ttf");
        Constants.iranSansLight = Typeface.createFromAsset(getAssets(), "IRANSansMobile(FaNum)_Light.ttf");

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("IRANSansMobile(FaNum).ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        fragmentManager = getSupportFragmentManager();

        Fragment fragment = TabsVideo_Frg.newInstance();

        switchFragment(fragment, false, "");
    }

    @Override
    public void onBackPressed() {
        if (currFragment instanceof ShowVideo_Frg)
            ((ShowVideo_Frg) currFragment).stopVideo();
        super.onBackPressed();
    }

}


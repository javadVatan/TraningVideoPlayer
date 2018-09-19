package com.academy.ferdowsi.training.video.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.academy.ferdowsi.training.R;

import java.util.HashMap;
import java.util.Map;

public class TabsVideo_Frg extends Fragment {
    public static final int SecondTab = 1;
    public static final int FirstTab = 0;
    private View currView;
    private ViewPager vViewPager;
    private String[] tabTitles;
    private int PAGE_COUNT = 2;
    private Context mContext;
    private Map<Integer, String> mFragmentTags = new HashMap<>();
    private FragmentManager fragmentManager;

    public static Fragment newInstance() {
        return new TabsVideo_Frg();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        currView = inflater.inflate(R.layout.frg_tabs_video, container, false);
        tabTitles = new String[]{getString(R.string.pinted), getString(R.string.home)};
        initTab();
        initPageListener();


        return currView;
    }

    private void initTab() {
        TabLayout vTab = currView.findViewById(R.id.support_tab);
        vViewPager = currView.findViewById(R.id.support_view_pager);

        MyFragmentPagerAdapter mTabAdapter = new MyFragmentPagerAdapter(getFragmentManager());

        vViewPager.setAdapter(mTabAdapter);
        vViewPager.setOffscreenPageLimit(3);
        vTab.setupWithViewPager(vViewPager);

        TextView txtTabSupport = LayoutInflater.from(getContext()).inflate(R.layout.item_tab, null).findViewById(R.id.item_tab_txt_title);
        TextView txtTabFaq = LayoutInflater.from(getContext()).inflate(R.layout.item_tab, null).findViewById(R.id.item_tab_txt_title);

        txtTabSupport.setText("خانه");
        txtTabFaq.setText("نشان شده ها ");

//        vTab.getTabAt(FirstTab).setCustomView(txtTabSupport);
//        vTab.getTabAt(SecondTab).setCustomView(txtTabFaq);
        vViewPager.setCurrentItem(SecondTab);
    }

    private void initPageListener() {

        vViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
             /*   Fragment fragment;
                switch (position) {
                    case FirstTab:
                        fragment = ((MyFragmentPagerAdapter) vViewPager.getAdapter()).getFragment(position);
                        if (fragment != null) {
                            fragment.onResume();
                        }

                        break;
                    case SecondTab:
                        fragment = ((MyFragmentPagerAdapter) vViewPager.getAdapter()).getFragment(position);
                        if (fragment != null) {
                            // fragment.onResume();do someThing...
                        }
                        break;
                }*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    /*    @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Object obj = super.instantiateItem(container, position);
            if (obj instanceof Fragment) {
                //record the fragment tag here
                Fragment fragment = (Fragment) obj;
                String tag = fragment.getTag();
                mFragmentTags.put(position, tag);
            }
            return obj;
        }

        public Fragment getFragment(int position) {
            String tag = mFragmentTags.get(position);
            if (tag == null) return null;
            return fragmentManager.findFragmentByTag(tag);

        }*/

        /**
         * Constructor of the class
         */
        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * This method will be invoked when a page is requested to create
         */
        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case FirstTab:
                    return FavoriteVideo_Frg.newInstance();

                case SecondTab:
                    return CategoryVideo_Frg.newInstance();

                default:
                    return null;
            }
        }

        /**
         * Returns the number of pages
         */
        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}

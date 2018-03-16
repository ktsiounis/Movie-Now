package com.example.ntinos.moviesnow.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konstantinos Tsiounis on 16-Mar-18.
 */

public class DetailsPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private List<Bundle> argsList = new ArrayList<>();

    public DetailsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title, Bundle args){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
        argsList.add(args);
    }

    @Override
    public Fragment getItem(int position) {
        mFragmentList.get(position).setArguments(argsList.get(position));

        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}

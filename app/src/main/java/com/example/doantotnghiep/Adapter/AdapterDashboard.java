package com.example.doantotnghiep.Adapter;


import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.doantotnghiep.Fragment.FragmentProfile;
import com.example.doantotnghiep.Fragment.Tab.TabOder;
import com.example.doantotnghiep.Fragment.Tab.TabProfile;

public class AdapterDashboard extends FragmentPagerAdapter {


    int totalTabs;


    public AdapterDashboard(FragmentManager childFragmentManager, int behaviorResumeOnlyCurrentFragment) {
        super(childFragmentManager, behaviorResumeOnlyCurrentFragment);
        this.totalTabs = behaviorResumeOnlyCurrentFragment;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TabOder oder = new TabOder();
                return oder;
            case 1:
                TabProfile profile = new TabProfile();
                return profile;

            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}


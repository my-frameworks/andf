package com.and.framework.common.widget.tab;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.and.framework.common.fragment.BaseFragment;

import java.util.List;

/**
 * Created by lwp940118 on 2016/11/25.
 */
public class TabFragmentAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<BaseFragment> fragments;
    private List<String> strings;

    public TabFragmentAdapter(List<BaseFragment> fragments, List<String> strings, FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        this.strings = strings;
        this.context = context;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }
}

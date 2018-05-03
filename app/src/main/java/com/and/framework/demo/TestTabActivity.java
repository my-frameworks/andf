package com.and.framework.demo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.and.framework.R;
import com.and.framework.common.activity.BaseActivity;
import com.and.framework.common.fragment.BaseFragment;
import com.and.framework.common.widget.tab.TabFragmentAdapter;
import com.and.framework.demo.tab.Fragement2;
import com.and.framework.demo.tab.Fragement3;
import com.and.framework.demo.tab.Fragment1;
import com.and.framework.demo.tab.Fragment4;
import com.and.framework.demo.tab.Fragment5;
import com.and.framework.demo.tab.Fragment6;
import com.and.framework.demo.tab.Fragment7;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyadong on 2018/5/3.
 */

public class TestTabActivity extends BaseActivity {

    private TabLayout tabLayout_shouye;
    private ViewPager viewPager_shouye;

    private List<BaseFragment> fragments = new ArrayList<>();
    private List<String> strings = new ArrayList<>();

    @Override
    protected int getLayoutRes() {
        return R.layout.demo_tab_layout;
    }

    @Override
    protected void onViewCreated() {
        initdate();
        initView();
    }

    @Override
    protected void initEventAndData(Bundle bundle) {

    }

    private void initView() {
        tabLayout_shouye = (TabLayout) findViewById(R.id.tablayout_shouye);
        viewPager_shouye = (ViewPager) findViewById(R.id.viewpager);
        viewPager_shouye.setAdapter(new TabFragmentAdapter(fragments, strings, getSupportFragmentManager(), this));
        tabLayout_shouye.setupWithViewPager(viewPager_shouye);
        tabLayout_shouye.setTabTextColors(getResources().getColor(R.color.colorPrimary)
                , getResources().getColor(R.color.colorAccent));
    }


    private void initdate() {
        Fragment1 fragment1 = new Fragment1();
        fragments.add(fragment1);
        strings.add("推234234荐");
        Fragement2 fragment2 = new Fragement2();
        fragments.add(fragment2);
        strings.add("热123123点");

        Fragement3 fragment3 = new Fragement3();
        fragments.add(fragment3);
        strings.add("视频");
        Fragment4 fragment4 = new Fragment4();
        fragments.add(fragment4);

        strings.add("西ew安");
        Fragment5 fragment5 = new Fragment5();
        fragments.add(fragment5);
        strings.add("社会");
        Fragment6 fragment6 = new Fragment6();
        fragments.add(fragment6);
        strings.add("娱乐");
        Fragment7 fragment7 = new Fragment7();
        fragments.add(fragment7);
        strings.add("图片");
    }

}

package com.liulord.videoshare.activity;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.liulord.videoshare.R;
import com.liulord.videoshare.fragment.VideoShareFragmentAdapter;
import com.viewpagerindicator.PageIndicator;

import java.util.Random;

/**
 * Created by aaronliu on 14-11-27.
 */
public abstract class BaseActivity extends FragmentActivity {
    private static final Random RANDOM = new Random();

    VideoShareFragmentAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}

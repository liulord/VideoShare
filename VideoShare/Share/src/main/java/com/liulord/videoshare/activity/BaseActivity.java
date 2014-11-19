package com.liulord.videoshare.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by zhangdi on 14-7-31.
 */
public class BaseActivity extends Activity {

    private static long sLastOnPauseTime = 0;
    private static final long SESSION_DELAY = 3 * 1000;

    public static final String EXTRA_STARTUP_FROM = "startup_from";
    private String mStartupFrom = "logo_start";

    protected AtomicBoolean isTop = new AtomicBoolean(false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() != null) {
            mStartupFrom = getIntent().getStringExtra(EXTRA_STARTUP_FROM);
            if ("push_start".equals(mStartupFrom)) {
                statPushOpen(getIntent());
            }
        } else {
            mStartupFrom = null;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        isTop.set(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        isTop.set(false);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (intent != null) {
            mStartupFrom = intent.getStringExtra(EXTRA_STARTUP_FROM);
            if ("push_start".equals(mStartupFrom)) {
                statPushOpen(intent);
            }
        } else {
            mStartupFrom = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);

        long now = System.currentTimeMillis();
        if (sLastOnPauseTime == 0) {
            foreground(true);
        } else if (now - sLastOnPauseTime >= SESSION_DELAY) {
            foreground(false);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);

        sLastOnPauseTime = System.currentTimeMillis();
    }

    protected void initTitleBar(boolean back, int iconRes, String title, int rightRes) {
//        View titleBar = findViewById(R.id.title_bar);
//        View backV = titleBar.findViewById(R.id.back);
//        ImageView iconIv = (ImageView) titleBar.findViewById(R.id.icon);
//        TextView titleTv = (TextView) titleBar.findViewById(R.id.title);
//        ImageView rightIv = (ImageView) titleBar.findViewById(R.id.right_btn);
//
//        backV.setVisibility(back ? View.VISIBLE : View.GONE);
//        if (iconRes > 0) {
//            iconIv.setImageResource(iconRes);
//            iconIv.setVisibility(View.VISIBLE);
//        } else {
//            iconIv.setVisibility(View.GONE);
//        }
//        titleTv.setVisibility(View.VISIBLE);
//        titleTv.setText(title);
//        if (rightRes > 0) {
//            rightIv.setImageResource(rightRes);
//            rightIv.setVisibility(View.VISIBLE);
//        } else {
//            rightIv.setVisibility(View.GONE);
//        }
//
//        if (backV.getVisibility() == View.VISIBLE) {
//            backV.setOnClickListener(mOnBackClickListener);
//        }
    }

    protected void initTitleBar(boolean back, String title) {
        initTitleBar(back, 0, title, 0);
    }

    View.OnClickListener mOnBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    /**
     * 切到前台来源
     *
     * @return
     */
    private String getStartupFrom() {
        return mStartupFrom != null ? mStartupFrom : "logo_start";
    }

    /**
     * 统计push点击
     */
    private void statPushOpen(Intent intent) {
//        String pushType = intent.getStringExtra(NotifyBRC.EXTRA_PUSH_TYPE);
//        String from = intent.getStringExtra(NotifyBRC.EXTRA_FROM);
//        String operate = intent.getStringExtra(NotifyBRC.EXTRA_OPERATE);
//        // 统计打开普通Push
//        if ("normal".equals(pushType) && !TextUtils.isEmpty(from)
//                && !TextUtils.isEmpty(operate)) {
//            String bssid = WifiUtils.getConnectedWifiBSSID(this);
//            float score = WifiCacheManager.getInstance().getDetectScore(bssid);
//            StatUtils.push_open(this, from, operate, score, bssid);
//        }
//        // 统计打开持久化Push
//        if ("persistent".equals(pushType) && !TextUtils.isEmpty(operate)) {
//            String attr = WifiUtils.isWifiConnected(this) ? "wifi_connect"
//                    : "wifi_disconnect";
//            StatUtils.fixed_push_open(this, attr, "normal", operate);
//        }
    }

    /**
     * 回到前台
     *
     * @param launcher 是否启动
     */
    private void foreground(boolean launcher) {

    }

}

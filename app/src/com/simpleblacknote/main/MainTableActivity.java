
package com.simpleblacknote.main;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.tlyerfoxx.sbcnote.R;

@SuppressWarnings("deprecation")
public class MainTableActivity extends TabActivity
{
    private TabHost mTableHost;
    private RadioGroup mTabGroup;
    private RadioButton mConsumeListBtn;
    private RadioButton mTodayConsumeBtn;
    private RadioButton mWeekConsumeBtn;
    private RadioButton mMonthConsumeBtn;

    private enum TableType {
        CONSUME_LIST, TODAY_LIST, WEEK_LIST, MONTH_LIST;

        public String getIndicator()
        {
            switch (this) {
                case CONSUME_LIST:
                    return "consumeList";
                case TODAY_LIST:
                    return "todayList";
                case WEEK_LIST:
                    return "weekList";
                case MONTH_LIST:
                    return "monthList";
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_table_activity);
        showAdmob();
        initView();
    }

    private void initView()
    {
        mTableHost = getTabHost();
        mTabGroup = (RadioGroup) findViewById(R.id.maintable_rg_tables);
        mConsumeListBtn = (RadioButton) findViewById(R.id.maintable_rb_consume_list);
        mTodayConsumeBtn = (RadioButton) findViewById(R.id.maintable_rb_today_consume);
        mWeekConsumeBtn = (RadioButton) findViewById(R.id.maintable_rb_week_consume);
        mMonthConsumeBtn = (RadioButton) findViewById(R.id.maintable_rb_month_consume);

        addTableInHost();
        addTableChangeListener();
    }

    private void addTableChangeListener()
    {
        OnTabChangeListener tableChangeListener = new OnTabChangeListener();
        tableChangeListener.onCheckedChanged(mTabGroup, R.id.maintable_rb_consume_list);
        mTabGroup.setOnCheckedChangeListener(tableChangeListener);
    }

    private void addTableInHost()
    {
        Intent consumeListIntent = new Intent(this, All_List.class);
        Intent monthConsumeListIntent = new Intent(this, Month_List.class);
        Intent todayConsumeListIntent = new Intent(this, Today_List.class);
        Intent weekConsumeListIntent = new Intent(this, Week_List.class);

        mTableHost.addTab(mTableHost.newTabSpec(TableType.CONSUME_LIST.name())
                .setIndicator(TableType.CONSUME_LIST.getIndicator()).setContent(consumeListIntent));
        mTableHost.addTab(mTableHost.newTabSpec(TableType.TODAY_LIST.name())
                .setIndicator(TableType.TODAY_LIST.getIndicator())
                .setContent(todayConsumeListIntent));
        mTableHost
                .addTab(mTableHost.newTabSpec(TableType.WEEK_LIST.name())
                        .setIndicator(TableType.WEEK_LIST.getIndicator())
                        .setContent(weekConsumeListIntent));
        mTableHost.addTab(mTableHost.newTabSpec(TableType.MONTH_LIST.name())
                .setIndicator(TableType.MONTH_LIST.getIndicator())
                .setContent(monthConsumeListIntent));
    }

    private Animation zoomInXYAnimation()
    {
        Animation scaleXY = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f);
        scaleXY.setDuration(500);
        return scaleXY;
    }

    private class OnTabChangeListener implements OnCheckedChangeListener
    {
        @Override
        public void onCheckedChanged(RadioGroup group, int id)
        {
            switch (id) {
                case R.id.maintable_rb_consume_list:
                    mTableHost.setCurrentTabByTag(TableType.CONSUME_LIST.name());
                    mConsumeListBtn.startAnimation(zoomInXYAnimation());
                    break;

                case R.id.maintable_rb_today_consume:
                    mTableHost.setCurrentTabByTag(TableType.TODAY_LIST.name());
                    mTodayConsumeBtn.startAnimation(zoomInXYAnimation());
                    break;

                case R.id.maintable_rb_week_consume:
                    mTableHost.setCurrentTabByTag(TableType.WEEK_LIST.name());
                    mWeekConsumeBtn.startAnimation(zoomInXYAnimation());
                    break;

                case R.id.maintable_rb_month_consume:
                    mTableHost.setCurrentTabByTag(TableType.MONTH_LIST.name());
                    mMonthConsumeBtn.startAnimation(zoomInXYAnimation());
                    break;

                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    private void showAdmob()
    {
        AdView adView = new AdView(this, AdSize.BANNER, "a150189a3abeb1a");
        LinearLayout layout = (LinearLayout) findViewById(R.id.maintable_lt_admob);
        layout.addView(adView);
        adView.loadAd(new AdRequest());
    }
}

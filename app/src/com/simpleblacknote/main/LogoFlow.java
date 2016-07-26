
package com.simpleblacknote.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.tlyerfoxx.sbcnote.R;

@SuppressLint("NewApi")
public class LogoFlow extends Activity implements ILogoFlow
{
    private ImageView mLogo;
    private LogoAnimationTask mLogoAnimationtask;

    public enum AnimationType {
        ZOOMIN_AND_ROTATION360, ZOOMOUT_AND_ROTATION360, ROTATION_Y_AXIS720, ROTATION_X_AXIS720
    }
    
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logoflow);
        mLogo = (ImageView) findViewById(R.id.imageView1);
        mLogoAnimationtask = new LogoAnimationTask(this);
        mLogoAnimationtask.execute();
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        finishLogo();
        return true;
    }

    @Override
    public View getLogo()
    {
        return mLogo;
    }

    @Override
    public void finishLogo()
    {
        LogoFlow.this.finish();
        Intent goAct = new Intent();
        goAct.setClass(LogoFlow.this, Table_Flow.class);
        startActivity(goAct);
        overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
        System.exit(0);
    }

}

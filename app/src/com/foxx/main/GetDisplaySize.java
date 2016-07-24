package com.foxx.main;

import FoXxLib.FP;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.tlyerfoxx.sbcnote.R;

public class GetDisplaySize extends Activity {

	final String tLog = "Trace Log";

	TextView tv1;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

//		setWindowFeature();
		
		setContentView(R.layout.getdisplaysize);

		tv1 = (TextView) findViewById(R.id.textView1);

		DisplayMetrics metr = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metr);

		Display dplay = getWindowManager().getDefaultDisplay();
		
		tv1.setText("Width: " + metr.widthPixels + " Height: " //get width , height
				+ metr.heightPixels + "\n" + "Rotation: " + dplay.getRotation() //get rotation 
				+ " RefreshRate: " + dplay.getRefreshRate()); // get refreshrate

	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		FP.p("Touch OK");
		
		Intent inte = this.getIntent();
		
		Bundle b = new Bundle();
		b.putString("time", "�o�O��ծɶ�");
		b.putString("money", "�o�O��ժ���");
		b.putString("remark", "�o�O��ճƵ�");
		
		inte.putExtras(b);
		
		GetDisplaySize.this.setResult(1, inte);
		
//		GetDisplaySize.this.setResult(0);
		
		GetDisplaySize.this.finish();
		
		return super.onTouchEvent(event);
	}
	
	void setWindowFeature() {

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
}
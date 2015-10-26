package com.example.helloworld;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.tlyerfoxx.sbcnote.R;


@SuppressLint("NewApi")
public class CopyRightFlow extends Activity{

	static final String tLog = "Trace Log";

	TextView tv; // init by xml use

	Button bYes; // enter button
	Button bNo; // exit button
	
	AdView adView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
//		setWindowFeature();
		
		initByXml();

	}
	
	void setWindowFeature(){
		//fullscreen
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//no title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//portrait
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
	void initByXml() {
		setContentView(R.layout.copyright);
		
		addAdmob();
		
		// TextView init
		tv = (TextView) findViewById(R.id.textView1);// why can't cast to
														// TestTextView
		tv.setTextColor(0xff888888); // set color
		tv.setTextSize(18.0f); // set size
		tv.setTypeface(Typeface.SERIF);// set typeface
		tv.setText(R.string.copy_right);// change text
		tv.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

		// ButtonYes init
		bYes = (Button) findViewById(R.id.buttonYes); // get ButtonYes
		OnClickListener bYesOc = new OnClickListener() { // build ButtonYes Listener

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

//				CopyRightFlow.this.finish(); // exit program
//				
//				Intent goAct = new Intent();// new a Intent
//				goAct.setClass(CopyRightFlow.this, GetDisplaySize.class); // setclass
//				startActivity(goAct); // start another Activity
//				
//				System.exit(0);
				
				
				ValueAnimator rotationY = ObjectAnimator.ofFloat(bYes, "rotationY", 0f, 360f);
				ValueAnimator rotationX = ObjectAnimator.ofFloat(bYes, "rotationX", 0f, 360f);
				rotationY.setDuration(1000);		        
		        rotationX.setDuration(500);
		       
		        AnimatorSet as = new AnimatorSet();
		        as.playTogether(rotationY,rotationX);
		        as.start();
			}
		};
		bYes.setOnClickListener(bYesOc);// set ButtonYes Listener

		// ButtonNo init
		bNo = (Button) findViewById(R.id.buttonNo); // get ButtonNo
		bNo.setOnClickListener(new OnClickListener() { // build ButtonNo Listener

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				showAlertDialog();
				
			}
		});
	}

	void showAlertDialog(){

		AlertDialog ad = new AlertDialog.Builder(this).create();
		
		ad.setTitle("警告");//設定警告標題
		ad.setMessage("確定離開??");//設定警告內容
		ad.setButton("確定", new DialogInterface.OnClickListener() {//設定按鈕1
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				//點選按鈕1後執行的動作
				//檢查網路狀態
				ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

				NetworkInfo ni = cm.getActiveNetworkInfo();
				if (ni == null) {//沒有網路

//					CopyRightFlow.this.finish();//結束程式
					System.exit(0);
				}
				else if (ni != null) {//若有網路先連結到外部網頁

					if( ni.isConnected()){
						
					
					Uri uri = Uri.parse("http://vulpesadn.blogspot.tw/");
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(intent);

//					CopyRightFlow.this.finish();//再結束程序
					System.exit(0);
					}
				}
			}
		});
		ad.setButton2("取消", new DialogInterface.OnClickListener() {//設定按鈕2
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				//點選按鈕2後執行的動作
				//無動作
			}
		});
		
		ad.setCanceledOnTouchOutside(false);//當警告提示出現後,點選提示以外範圍,是否會取消提示,預設是true
		
		ad.setCancelable(false);//當警告提示出現後,點選其他實體按鈕(backkey等等),是否會取消提示,預設是true
		
		ad.show();//顯示按鈕
	}
	
	void addAdmob() {

		 adView = new AdView(this, AdSize.BANNER, "a150189a3abeb1a");//xxxxxxxxxxxxxxx is your admob id	
		 LinearLayout layout = (LinearLayout) findViewById(R.id.AdLayout);
		 layout.addView(adView);
		 adView.loadAd(new AdRequest());

	}
}
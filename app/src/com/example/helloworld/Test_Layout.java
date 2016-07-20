package com.example.helloworld;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import com.tlyerfoxx.sbcnote.R;

public class Test_Layout extends Activity{

	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setWindowFeature();
		setLayout();
	}
	
	public boolean onCreateOptionsMenu(Menu m){
		m.add(0, 0, 0, R.string.LinearLayout);
		m.add(0, 0, 1, R.string.LinearLayout);
		return super.onCreateOptionsMenu(m);
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		super.onOptionsItemSelected(item);
		
		return true;
	}
	
	void setWindowFeature(){
		//fullscreen
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//no title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//portrait
//		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
	void setLayout(){
		
		setContentView(R.layout.test_relativelayout);
	}
}

package com.foxx.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import FoXxLib.FP;
import FoXxLib.F_UseString;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.tlyerfoxx.sbcnote.R;


@SuppressLint("NewApi")
public class TestFlow extends Activity{

	static final String tLog = "Trace Log";

	TextView tv; // init by xml use

	Button bYes; // enter button
	Button bNo; // exit button
	
	File file;
	FileOutputStream fos;
	FileInputStream fis;
	
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
		// TextView init
		tv = (TextView) findViewById(R.id.textView1);// why can't cast to
														// TestTextView
		tv.setTextColor(0xff888888); // set color
		tv.setTextSize(18.0f); // set size
		tv.setTypeface(Typeface.SERIF);// set typeface
		tv.setText(R.string.copy_right);// change text
		tv.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

//		tv.setText(""+testInt);
		
		
		
		// ButtonYes init
		bYes = (Button) findViewById(R.id.buttonYes); // get ButtonYes
		OnClickListener bYesOc = new OnClickListener() { // build ButtonYes Listener

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			
				System.exit(0);
			}
		};
		bYes.setOnClickListener(bYesOc);// set ButtonYes Listener

		// ButtonNo init
		bNo = (Button) findViewById(R.id.buttonNo); // get ButtonNo
		bNo.setOnClickListener(new OnClickListener() { // build ButtonNo Listener

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				System.exit(0);
			}
		});
	}

	
}
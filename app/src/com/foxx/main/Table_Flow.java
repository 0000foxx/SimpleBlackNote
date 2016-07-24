package com.foxx.main;

import FoXxLib.FP;
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
public class Table_Flow extends TabActivity{

	
	private RadioGroup tabGroup;
	
	private RadioButton rb1;
	private RadioButton rb2;
	private RadioButton rb3;
	private RadioButton rb4;
	
	private TabHost thost;
	
	String templist;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.table_host);
		
		showAdmob();
		
		initView();
	}
	
	
	void initView(){
		
		thost = getTabHost();// build tabhost
		
		tabGroup = (RadioGroup) findViewById(R.id.tab_group);
		
		rb1 = (RadioButton)findViewById(R.id.tab1);
		rb2 = (RadioButton)findViewById(R.id.tab2);
		rb3 = (RadioButton)findViewById(R.id.tab3);
		rb4 = (RadioButton)findViewById(R.id.tab4);
		
		Intent inte1 = new Intent(this, All_List.class);//�M��
		Intent inte2 = new Intent(this, Month_List.class);//����
		Intent inte3 = new Intent(this, Today_List.class);//����
		Intent inte4 = new Intent(this, Week_List.class);//���P
		
		thost.addTab(thost.newTabSpec("TAB1").setIndicator("tab1").setContent(inte1));
		thost.addTab(thost.newTabSpec("TAB4").setIndicator("tab4").setContent(inte2));
		thost.addTab(thost.newTabSpec("TAB2").setIndicator("tab2").setContent(inte3));
		thost.addTab(thost.newTabSpec("TAB3").setIndicator("tab3").setContent(inte4));
				
		
		OnTabChangeListener otl = new OnTabChangeListener();

		otl.onCheckedChanged(tabGroup, R.id.tab1);
		
		tabGroup.setOnCheckedChangeListener(otl);

		// load bundle data from All_List or Today_List or Week_List or Month_List
		Bundle b = this.getIntent().getExtras();
		if (b != null) {
			
			//button anim
			Animation scalybig = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f);
			scalybig.setDuration(500);
			
			templist = b.getString("nextlist");
			
			thost.setCurrentTabByTag(templist);
			
			if(templist.equals("TAB1"))
			{
				otl.onCheckedChanged(tabGroup, R.id.tab1);
				
				rb1.setChecked(true);
				rb2.setChecked(false);
				rb3.setChecked(false);
				rb4.setChecked(false);
			}
			else if(templist.equals("TAB2"))
			{
				otl.onCheckedChanged(tabGroup, R.id.tab2);
				rb2.startAnimation(scalybig);
				rb2.setChecked(true);
				rb1.setChecked(false);
				rb3.setChecked(false);
				rb4.setChecked(false);
			}
			else if(templist.equals("TAB3"))
			{
				otl.onCheckedChanged(tabGroup, R.id.tab3);
				rb3.startAnimation(scalybig);
				rb3.setChecked(true);
				rb1.setChecked(false);
				rb2.setChecked(false);
				rb4.setChecked(false);
			}
			else if(templist.equals("TAB4"))
			{
				otl.onCheckedChanged(tabGroup, R.id.tab4);
				rb4.startAnimation(scalybig);
				rb4.setChecked(true);
				rb1.setChecked(false);
				rb2.setChecked(false);
				rb3.setChecked(false);
			}
			
			
			tabGroup.setOnCheckedChangeListener(otl);
		}
		
		
		
	}
	
	 private class OnTabChangeListener implements OnCheckedChangeListener {

			@Override
			public void onCheckedChanged(RadioGroup group, int id) {
				// TODO Auto-generated method stub
				
				//button anim
				Animation scalybig = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f);
				scalybig.setDuration(500);
				
				
				switch(id){
				
				case R.id.tab1://�M��
					FP.p("in tab1");
					thost.setCurrentTabByTag("TAB1");					
					rb1.startAnimation(scalybig);					
					break;
					
				case R.id.tab2://����
					FP.p("in tab2");
					thost.setCurrentTabByTag("TAB2");		
					rb2.startAnimation(scalybig);					
					break;
					
				case R.id.tab3://���P
					FP.p("in tab3");
					thost.setCurrentTabByTag("TAB3");				
					rb3.startAnimation(scalybig);					
					break;
					
				case R.id.tab4://����
					FP.p("in tab4");
					thost.setCurrentTabByTag("TAB4");					
					rb4.startAnimation(scalybig);

					break;
				}
				
			}
			
	 }
	
	
	void showAdmob(){
		
	AdView adView;
		
//  	  Create the adView     
   	adView = new AdView(this, AdSize.BANNER, "a150189a3abeb1a");      
 
   	LinearLayout layout = (LinearLayout)findViewById(R.id.AdLayout);      
   	   
   	layout.addView(adView);
   	
   	adView.loadAd(new AdRequest()); 
	}
}





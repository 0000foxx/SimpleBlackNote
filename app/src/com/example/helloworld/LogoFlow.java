package com.example.helloworld;

import java.util.Random;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.tlyerfoxx.sbcnote.R;

@SuppressLint("NewApi")
public class LogoFlow extends Activity implements Runnable{

	ImageView logoView;
	
	Handler handler;
	
	float logoActIndex;
	
	boolean sleeping;

	ValueAnimator scalex;
	ValueAnimator scaley;
	ValueAnimator rotationY;
	ValueAnimator rotationX;
	AnimatorSet scalexy;
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.logoflow);
		
//		showAdmob();
		
		logoView = (ImageView)findViewById(R.id.imageView1);
		
//		useThreadFadeIn();
		
		Random r = new Random();
		useAnimfadein2(r.nextInt(4));
	}
	
	public void run() {
		// TODO Auto-generated method stub
		
		while(!sleeping){
			
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Message m = new Message();
			
            handler.sendMessage(m);
			
		}
	}
	
	void useThreadFadeIn(){
		
		logoView.setAlpha(0.0f);
		
		handler = new Handler(){
			
			public void handleMessage(Message msg) {

				Log.v("Trace Log", "revice maessage");
				
				if(logoView.getAlpha()<1.0f){
					logoActIndex+=0.01f;
					logoView.setAlpha(logoActIndex);
					Log.v("Trace Log", "test2");
				}
				else if(logoView.getAlpha()>=1.0f){
					
					LogoFlow.this.finish(); // close activity
					
					Log.v("Trace Log", "test3");
					sleeping = true;// stop Thread

					Intent goAct = new Intent();// new a Intent
					goAct.setClass(LogoFlow.this, Table_Flow.class); // set another activity
					startActivity(goAct); // start another Activity
					
					System.exit(0);// stop program

				}
				
				Log.v("Trace Log", ""+logoView.getAlpha());
				
                super.handleMessage(msg);

            }
			
		};
		
		Thread th = new Thread(this);
		th.start();
	}
	
	void useAnimfadein(){
		
		ValueAnimator fadein = ObjectAnimator.ofFloat(logoView, "alpha", 0f, 1f);
		
        fadein.setDuration(2000);
        
        ValueAnimator standby = ObjectAnimator.ofFloat(logoView, "alpha", 1f, 1f);
        standby.setDuration(1000);
        
        ValueAnimator fadeout = ObjectAnimator.ofFloat(logoView, "alpha", 1f, 0f);
        fadeout.setDuration(2000);
        
        AnimatorSet bouncer = new AnimatorSet();
        bouncer.playSequentially(fadein,standby,fadeout);
        
        bouncer.start();
        
        bouncer.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				
				LogoFlow.this.finish(); // close activity
				sleeping = true;// stop Thread
				Intent goAct = new Intent();// new a Intent
				goAct.setClass(LogoFlow.this, CopyRightFlow.class); // set another activity
				startActivity(goAct); // start another Activity
				System.exit(0);// stop program
				
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	void useAnimfadein2(int type){
	
		if(type==0){
			scalex = ObjectAnimator.ofFloat(logoView, "scaleX", 0.1f, 1f);
			scaley = ObjectAnimator.ofFloat(logoView, "scaleY", 0.1f, 1f);
			scalex.setDuration(2500);		        
			scaley.setDuration(1250);
			
			rotationY = ObjectAnimator.ofFloat(logoView, "rotationY", 0f, 360f);
			rotationX = ObjectAnimator.ofFloat(logoView, "rotationX", 0f, 360f);
			rotationY.setDuration(2500);		        
	        rotationX.setDuration(1250);
	        
	        scalexy = new AnimatorSet();
			scalexy.playTogether(scalex,scaley,rotationY,rotationX);
		}
		else if(type==1){
			scalex = ObjectAnimator.ofFloat(logoView, "scaleX", 2.0f, 1f);
			scaley = ObjectAnimator.ofFloat(logoView, "scaleY", 2.0f, 1f);
			scalex.setDuration(2500);		        
			scaley.setDuration(1250);
			
			rotationY = ObjectAnimator.ofFloat(logoView, "rotationY", 0f, 360f);
			rotationX = ObjectAnimator.ofFloat(logoView, "rotationX", 0f, 360f);
			rotationY.setDuration(2500);		        
	        rotationX.setDuration(1250);
	        
	        scalexy = new AnimatorSet();
			scalexy.playTogether(scalex,scaley,rotationY,rotationX);
		}
		else if(type==2){
			scalex = ObjectAnimator.ofFloat(logoView, "scaleX", 1f, 1f);
			scaley = ObjectAnimator.ofFloat(logoView, "scaleY", 1f, 1f);
			scalex.setDuration(2500);		        
			scaley.setDuration(1250);
			
			rotationY = ObjectAnimator.ofFloat(logoView, "rotationY", 0f, 720f);
			rotationX = ObjectAnimator.ofFloat(logoView, "rotationX", 0f, 0f);
			rotationY.setDuration(2500);		        
	        rotationX.setDuration(1250);
	        
	        scalexy = new AnimatorSet();
			scalexy.playTogether(scalex,scaley,rotationY,rotationX);
		}
		else if(type==3){
			scalex = ObjectAnimator.ofFloat(logoView, "scaleX", 1f, 1f);
			scaley = ObjectAnimator.ofFloat(logoView, "scaleY", 1f, 1f);
			scalex.setDuration(2500);		        
			scaley.setDuration(1250);
			
			rotationY = ObjectAnimator.ofFloat(logoView, "rotationY", 0f, 0f);
			rotationX = ObjectAnimator.ofFloat(logoView, "rotationX", 0f, 720f);
			rotationY.setDuration(2500);		        
	        rotationX.setDuration(1250);
	        
	        scalexy = new AnimatorSet();
			scalexy.playTogether(scalex,scaley,rotationY,rotationX);
		}
		
		
		
        ValueAnimator standby = ObjectAnimator.ofFloat(logoView, "alpha", 1f, 1f);
        standby.setDuration(1000);
        
        ValueAnimator fadeout = ObjectAnimator.ofFloat(logoView, "alpha", 1f, 0f);
        fadeout.setDuration(1000);
        
        AnimatorSet bouncer = new AnimatorSet();
        bouncer.playSequentially(scalexy,standby,fadeout);
        
        bouncer.start();
        
        bouncer.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				
				LogoFlow.this.finish(); // close activity
				sleeping = true;// stop Thread
				Intent goAct = new Intent();// new a Intent
				goAct.setClass(LogoFlow.this, Table_Flow.class); // set another activity CopyRightFlow.class
				startActivity(goAct); // start another Activity
				
				overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);//使用過場,第1個參數是下1個場景的進入,第2個參數是本身場景的離開
				
				System.exit(0);// stop program
				
				
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	void showAdmob(){
		
		 AdView adView;
		
//   	  Create the adView     
    	adView = new AdView(this, AdSize.BANNER, "a150189a3abeb1a");      
  
    	LinearLayout layout = (LinearLayout)findViewById(R.id.AdLayout);      
    	   
    	layout.addView(adView);
    	
    	adView.loadAd(new AdRequest()); 
	}
	
	public boolean onTouchEvent(MotionEvent event){
//		FP.p("in onTouchEvent");
		
		LogoFlow.this.finish(); // close activity
		sleeping = true;// stop Thread
		Intent goAct = new Intent();// new a Intent
		goAct.setClass(LogoFlow.this, Table_Flow.class); // set another activity CopyRightFlow.class
		startActivity(goAct); // start another Activity
		
		overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);//使用過場,第1個參數是下1個場景的進入,第2個參數是本身場景的離開
		
		System.exit(0);// stop program
		
		switch(event.getAction()){
		
		case MotionEvent.ACTION_DOWN:
//			dx = (int)event.getX();
			break;
			
		case MotionEvent.ACTION_UP:
			
			
			break;
		}
		
			return true;
	}
	
}

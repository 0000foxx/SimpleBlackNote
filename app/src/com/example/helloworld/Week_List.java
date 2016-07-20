package com.example.helloworld;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import FoXxLib.FP;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.tlyerfoxx.sbcnote.R;


@TargetApi(11)
public class Week_List extends Activity{
	
	Button bexit; // exit button
	Button benter; // exit button
	
	ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
	
	ListView lv;
	
	SimpleAdapter sa;
	
	// arraylist for accountdetail
	ArrayList<AccountDetail> al = new ArrayList<AccountDetail>();
	
	int accountdetailcount;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		FP.p("in today_list onCreate start");
		
		setContentView(R.layout.weeklist);
		
		initXml();
		
		loadAccountDetail();
		
		listShowAnim();
		
		callNowalcount();
		
		FP.p("in today_list onCreate end");
	}
	
	//switch tabwidget will call this
	public void onResume(){
		super.onResume();
		FP.p("on onResume");
		initXml();
		
		if(!al.isEmpty()){
			FP.p("!al.isEmpty()");
			al.clear();
			list.clear();
			lv = null;
			sa = null;
//			al.removeAll(al);
			FP.p("13"+al.size());
		}
		
		loadAccountDetail();
		
		listShowAnim();
		
		callNowalcount();
	}
	
	@SuppressLint("NewApi")
	void initXml(){

		// ButtonYes init
		bexit = (Button) findViewById(R.id.buttonexit); // get ButtonYes
		OnClickListener bExitOc = new OnClickListener() { // build ButtonYes Listener

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ValueAnimator rotationY = ObjectAnimator.ofFloat(bexit, "scaleY", 1.0f, 0.0f);
//				ValueAnimator rotationX = ObjectAnimator.ofFloat(bexit, "rotationX", 0f, 360f);
				rotationY.setDuration(250);		        
//		        rotationX.setDuration(500);
		       
		        AnimatorSet as = new AnimatorSet();
		        as.playTogether(rotationY);
		        as.start();
		        as.addListener(new AnimatorListener() {
					
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
						
//						// exit AlertDialog
						AlertDialog ad = new AlertDialog.Builder(Week_List.this,AlertDialog.THEME_TRADITIONAL).create();
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

//									CopyRightFlow.this.finish();//結束程式
									System.exit(0);
								}
								else if (ni != null) {//若有網路先連結到外部網頁

									if( ni.isConnected()){
										
									
									Uri uri = Uri.parse("https://play.google.com/store/apps/developer?id=Vulpse");
									Intent intent = new Intent(Intent.ACTION_VIEW, uri);
									startActivity(intent);

//									CopyRightFlow.this.finish();//再結束程序
									System.exit(0);
									}
								}
								
								Week_List.this.finish(); // exit program
								
							}
						});
						ad.setButton2("取消", new DialogInterface.OnClickListener() {//設定按鈕2
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								
								//點選按鈕2後執行的動作
								ValueAnimator rotationY = ObjectAnimator.ofFloat(bexit, "scaleY", 0.0f, 1.0f);
//								ValueAnimator rotationX = ObjectAnimator.ofFloat(bexit, "rotationX", 0f, 360f);
								rotationY.setDuration(250);		        
//						        rotationX.setDuration(500);
						       
						        AnimatorSet as = new AnimatorSet();
						        as.playTogether(rotationY);
						        as.start();
							}
						});
						
						ad.show();
						
//						System.exit(0);
						
					}
					
					@Override
					public void onAnimationCancel(Animator animation) {
						// TODO Auto-generated method stub
						
					}
				});
			}
		};
		bexit.setOnClickListener(bExitOc);// set ButtonYes Listener
		
		
		// ButtonYes init
				benter = (Button) findViewById(R.id.buttonadd); // get ButtonYes
				OnClickListener bEnterOc = new OnClickListener() { // build ButtonYes Listener

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						ValueAnimator rotationY = ObjectAnimator.ofFloat(benter, "scaleY", 1.0f, 0.0f);
//						ValueAnimator rotationX = ObjectAnimator.ofFloat(bexit, "rotationX", 0f, 360f);
						rotationY.setDuration(250);		        
//				        rotationX.setDuration(500);
				       
				        AnimatorSet as = new AnimatorSet();
				        as.playTogether(rotationY);
				        as.start();
				        as.addListener(new AnimatorListener() {
							
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
								
								
								
								// 呼叫All_List 的 增加消費項目								
								Intent inte = new Intent();
								inte.setClass(Week_List.this, Table_Flow.class);
								
								startActivity(inte);
								Week_List.this.finish();
								overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
								
							}
							
							@Override
							public void onAnimationCancel(Animator animation) {
								// TODO Auto-generated method stub
								
							}
						});
						
				
					}
				};
				
				
				benter.setOnClickListener(bEnterOc);// set ButtonYes Listener
		
	}
	
	void loadAccountDetail() {

//		test fake accountdetail store start
//		SharedPreferences sp2 = getSharedPreferences("bcn", 0);
//		SharedPreferences.Editor spe = sp2.edit();
//		int tempaccountdetailcount =5;
//		spe.putInt("adcount", tempaccountdetailcount);// for more putXXX function
//		for(int i=0; i<tempaccountdetailcount; i++){
//			
//			Random r = new Random();
//			
//			spe.putString("ad_time"+i, ""+r.nextInt(100)+1);
//			spe.putString("ad_money"+i, ""+r.nextInt(100)+1);
//			spe.putString("ad_detail"+i, ""+r.nextInt(100)+1);
//		}
//		spe.commit();
//		clear all data
//		SharedPreferences sp2 = getSharedPreferences("bcn", 0);
//		SharedPreferences.Editor spe = sp2.edit();
//		spe.clear();
//		spe.commit();
//		test fake accountdetail store end
		
		
		
		// get data for accountdetail
		SharedPreferences sp = getSharedPreferences("bcn", 0);
		
		// get accountdetail count
		accountdetailcount = sp.getInt("adcount", 0);
		
		FP.p("in today: "+accountdetailcount);
		
		// get today date
		Calendar cal = Calendar.getInstance();
		String dateStr = ""+cal.get(Calendar.YEAR) //2012
		+"/"+(cal.get(Calendar.MONTH)+1) //11 (add 1 because it start from 0)
		+"/"+cal.get(Calendar.DATE);//13
		
		//Calculate first date of this week
		Calendar c = new GregorianCalendar();
		c.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
		Date d = c.getTime();
		
		//first date of this week
		Date firstday = getFirstDayOfWeek(d);
		
		//first,second,third... date of this week
		Date[] tempd = new Date[7];
		//change to string for compare to store date
		String[] datestr = new String[7];
		for(int i=0; i<tempd.length; i++){
			
			tempd[i] = new Date(firstday.getTime()+1000*60*60*24*i);
			datestr[i] = ""+(tempd[i].getYear()+1900)+"/"+(tempd[i].getMonth()+1)+"/"+tempd[i].getDate();
			FP.p(""+datestr[i]);
		}
		
		// get accountdetail field
		for(int i=0; i<accountdetailcount; i++){
			
			String tempstr = sp.getString("ad_time"+i, "");// ad_time0 = accountdetail0's time
			sp.getString("ad_money"+i, ""); // ad_money0 = accountdetail0's money
			sp.getString("ad_remark"+i, "");// ad_detail0 = accountdetail0's detail
			
			//compare to store date
			for(int i2=0; i2<datestr.length ; i2++){
				
				if(tempstr.equals(datestr[i2])){
					
					al.add(new AccountDetail(sp.getString("ad_time"+i, ""),sp.getString("ad_money"+i, ""),sp.getString("ad_remark"+i, "")));
				}
				
			}
			
//			FP.p("in today"+al.get(i).time);
		}
		
		for (int i = 0; i < al.size(); i++) {

			HashMap<String, String> map = new HashMap<String, String>();

			map.put("str1",al.get(i).time);
			map.put("str2",al.get(i).money);
			map.put("str3",al.get(i).remark);

			FP.p("" + i);

			list.add(map);
			
		}
		
		
		sa = new SimpleAdapter(this, list, R.layout.test2, new String[] {
				"str1", "str2", "str3" }, new int[] { R.id.date, R.id.money,
				R.id.detail });

		lv = (ListView) findViewById(R.id.listview1);
		lv.setAdapter(sa);

		//lv long pressed
		lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				FP.p("long "+arg2);
				
				return false;
			}
		});
		
		// lv short pressed
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				FP.p(""+arg2);
				
				
			}
		});

		// lv scroll
		lv.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
				//no float
				if(scrollState==SCROLL_STATE_IDLE){
					benter.setAlpha(1.0f);
					bexit.setAlpha(1.0f);
				}
				else{
					benter.setAlpha(0.5f);
					bexit.setAlpha(0.5f);
				}
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
	}

	
	String getalmoney(){
		int tempi =0;
		for(int i=0; i<al.size(); i++){
			int tempi2 = Integer.parseInt(al.get(i).money);
			tempi = tempi2+tempi;
			FP.p("tempi: "+tempi);
		}
		String temps = String.valueOf(tempi);
		FP.p("temps: "+temps);
		
		return temps;
	}
	
	String getalcount(){
		int tempi =al.size();
		
		String temps = String.valueOf(tempi);
		FP.p("temps: "+temps);
		
		return temps;
	}
	
	void callNowalcount(){
//		
//		//use AlertDialog
//		AlertDialog ad = new AlertDialog.Builder(Table_widget4.this,AlertDialog.THEME_TRADITIONAL).create();
//        ad.setTitle("消費清單提醒!!");//設定警告標題
//        ad.setMessage("所有消費支出為 "+getalmoney()+" 元\n" +
//				"消費項目總數為 "+getalcount()+" 筆");
//        
//		ad.setButton2("點擊關閉提醒", new DialogInterface.OnClickListener() {// 設定按鈕2
//
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//
//						// 點選按鈕2後執行的動作
//						
//					}
//				});
//        
//        ad.setCanceledOnTouchOutside(true);//當警告提示出現後,點選提示以外範圍,是否會取消提示,預設是true
//        
//        ad.setCancelable(true);//當警告提示出現後,點選其他實體按鈕(backkey等等),是否會取消提示,預設是true
//        
//        ad.show();
        
		// use Toast
        Toast.makeText(Week_List.this, "本周所有消費支出為 "+getalmoney()+" 元\n" +
				"本周消費項目總數為 "+getalcount()+" 筆",Toast.LENGTH_SHORT).show();
        
        
	}
	
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime ();
	}
	
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}
	
	void listShowAnim(){
		
		AnimationSet set = new AnimationSet(true);

		Animation animation;
		
		animation = new TranslateAnimation(-500.0f,0.0f,0.0f,0.0f);
		animation.setDuration(500);
		set.addAnimation(animation);
		
//		Animation animation;
//		animation = new AlphaAnimation(0.0f, 1.0f);
//		animation.setDuration(500);
//		set.addAnimation(animation);
//
//		animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
//				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
//				-1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
//		animation.setDuration(100);
//		set.addAnimation(animation);

		//every sublist action move, 0.0f: action together
		LayoutAnimationController controller = new LayoutAnimationController(
				set, 0.1f);

		lv.setLayoutAnimation(controller);
		
	}
}

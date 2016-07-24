package com.foxx.main;

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
public class Month_List extends Activity{
	
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
		
		FP.p("in month_list onCreate start");
		
		setContentView(R.layout.monthlist);
		
		initXml();
		
		loadAccountDetail();
		
		listShowAnim();
		
		callNowalcount();
		
		FP.p("in month_list onCreate end");
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
						AlertDialog ad = new AlertDialog.Builder(Month_List.this,AlertDialog.THEME_TRADITIONAL).create();
						ad.setTitle("ĵ�i");//�]�wĵ�i���D
						ad.setMessage("�T�w���}??");//�]�wĵ�i���e
						ad.setButton("�T�w", new DialogInterface.OnClickListener() {//�]�w���s1
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								
								//�I����s1����檺�ʧ@
								//�ˬd�������A
								ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

								NetworkInfo ni = cm.getActiveNetworkInfo();
								if (ni == null) {//�S������

//									CopyRightFlow.this.finish();//�����{��
									System.exit(0);
								}
								else if (ni != null) {//�Y��������s����~������

									if( ni.isConnected()){
										
									
									Uri uri = Uri.parse("https://play.google.com/store/apps/developer?id=Vulpse");
									Intent intent = new Intent(Intent.ACTION_VIEW, uri);
									startActivity(intent);

//									CopyRightFlow.this.finish();//�A�����{��
									System.exit(0);
									}
								}
								
								Month_List.this.finish(); // exit program
								
							}
						});
						ad.setButton2("���", new DialogInterface.OnClickListener() {//�]�w���s2
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								
								//�I����s2����檺�ʧ@
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
								
								
								
								// �I�sAll_List �� �W�[��O����								
								Intent inte = new Intent();
								inte.setClass(Month_List.this, Table_Flow.class);
								
								startActivity(inte);
								Month_List.this.finish();
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
		;// -> 2012/11
		
		
		// get accountdetail field
		for(int i=0; i<accountdetailcount; i++){
			
			String tempstr = sp.getString("ad_time"+i, "");// ad_time0 = accountdetail0's time
			sp.getString("ad_money"+i, ""); // ad_money0 = accountdetail0's money
			sp.getString("ad_remark"+i, "");// ad_detail0 = accountdetail0's detail
			
			if(tempstr.contains(dateStr)){
				
				al.add(new AccountDetail(sp.getString("ad_time"+i, ""),sp.getString("ad_money"+i, ""),sp.getString("ad_remark"+i, "")));
				
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
//        ad.setTitle("��O�M�洣��!!");//�]�wĵ�i���D
//        ad.setMessage("�Ҧ���O��X�� "+getalmoney()+" ��\n" +
//				"��O�����`�Ƭ� "+getalcount()+" ��");
//        
//		ad.setButton2("�I����������", new DialogInterface.OnClickListener() {// �]�w���s2
//
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//
//						// �I����s2����檺�ʧ@
//						
//					}
//				});
//        
//        ad.setCanceledOnTouchOutside(true);//��ĵ�i���ܥX�{��,�I�ﴣ�ܥH�~�d��,�O�_�|����,�w�]�Otrue
//        
//        ad.setCancelable(true);//��ĵ�i���ܥX�{��,�I���L������s(backkey����),�O�_�|����,�w�]�Otrue
//        
//        ad.show();
        
		// use Toast
        Toast.makeText(Month_List.this, "����Ҧ���O��X�� "+getalmoney()+" ��\n" +
				"�����O�����`�Ƭ� "+getalcount()+" ��",Toast.LENGTH_SHORT).show();
        
        
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

package com.foxx.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.simpleblacklight.utility.TraceLogger;
import com.tlyerfoxx.sbcnote.R;


@SuppressLint({ "NewApi", "NewApi" })
public class All_List extends Activity {
	
	Button bexit; // exit button
	Button benter; // exit button
	Button bmore;
	
	String[] strArr1 = {"2012/01/01"
			};
	String[] strArr2 = {"0",
			};
	String[] strArr3 = {"�ϥλ���:�I��[�J��O���ؼW�[�O��,�i�����I���s�趵��"
			};
	
	ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
	
	ListView lv;
	
	SimpleAdapter sa;
	
	// arraylist for accountdetail
	ArrayList<AccountDetail> al = new ArrayList<AccountDetail>();
	
	int accountdetailcount;
	
	int d2x ,u2x =0;
	
	/**
	 * 0:�L�Ƨ� 1:���B�Ƨ�  2�ɶ��Ƨ�
	 */
	int sortIndex =2;
	
	int playNum =0;
	
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
////		test fake accountdetail store start
//		SharedPreferences sp2 = getSharedPreferences("bcn", 0);
//		SharedPreferences.Editor spe = sp2.edit();
////		int tempaccountdetailcount =5;
//		spe.putString("adlock", "123");// for more putXXX function
//		spe.commit();
		
		lock();
		
		TraceLogger.print("in all_list onCreate");
		
		setContentView(R.layout.alllist);
		
		initXml();
		
		loadAccountDetail();
		
		listShowAnim();
		
//		callNowalcount(0);
		
		for(int i=0; i<al.size(); i++)
		{
			TraceLogger.print(""+al.get(i));
		}
	}
	
	
	//switch tabwidget will call this
	public void onResume(){
		super.onResume();
		
		listShowAnim();		
//		callNowalcount(0);
	}
	
	void loadAccountDetail() {

//		test fake accountdetail store start
//		SharedPreferences sp2 = getSharedPreferences("bcn", 0);
//		SharedPreferences.Editor spe = sp2.edit();
//		int tempaccountdetailcount =5;
//		spe.putString("adlock", "123");// for more putXXX function
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
		
		TraceLogger.print("in all: "+accountdetailcount);
		
		// get accountdetail field
		for(int i=0; i<accountdetailcount; i++){
			
			sp.getString("ad_time"+i, "");// ad_time0 = accountdetail0's time
			sp.getString("ad_money"+i, ""); // ad_money0 = accountdetail0's money
			sp.getString("ad_remark"+i, "");// ad_detail0 = accountdetail0's detail
			
			al.add(new AccountDetail(sp.getString("ad_time"+i, ""),sp.getString("ad_money"+i, ""),sp.getString("ad_remark"+i, "")));
			
		}
		
		checkPlayNum();
		
		setSort();
		

		for (int i = 0; i < al.size(); i++) {

			HashMap<String, String> map = new HashMap<String, String>();

			map.put("str1",al.get(i).time);
			map.put("str2",al.get(i).money);
			map.put("str3",al.get(i).remark);

			TraceLogger.print("" + i);

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
				TraceLogger.print("long "+arg2);
				
				callFirstEditmenu(arg2);
				
				return false;
			}
		});
		
		// lv short pressed
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				TraceLogger.print(""+arg2);
				
				//�I�s�s�誺 activity (ADEdit_Flow2)
				Bundle b = new Bundle();
				b.putInt("alnum", arg2);
				b.putString("altime", al.get(arg2).time);
				b.putString("almoney", al.get(arg2).money);
				b.putString("alremark", al.get(arg2).remark);
				Intent inte = new Intent();
				inte.setClass(All_List.this, ADEdit_Flow2.class);
				inte.putExtras(b);
				startActivityForResult(inte, 1);
			}
		});

		// lv scroll
		lv.setOnScrollListener(new OnScrollListener() {
			
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
				
				//drag screen will change different list (undone)(d2x no value)
//				view.setOnTouchListener(new OnTouchListener() {
//					
//					@Override
//					public boolean onTouch(View v, MotionEvent event) {
//						// TODO Auto-generated method stub
//						switch(event.getAction()){
//						
//						
//						case MotionEvent.ACTION_DOWN:
//							
//							FP.p("d2x1:"+d2x);
//							break;
//							
//						case MotionEvent.ACTION_UP:
//							u2x = (int)event.getX();
//							
//							FP.p("d2x:"+d2x);
//							FP.p("u2x:"+u2x);
//							
//							if(d2x>u2x){
//								FP.p("in flat1");
//								// �I�sAll_List �� �W�[��O����
////								Bundle b = new Bundle();
////								b.putString("nextlist", "TAB4");
////								
////								Intent inte = new Intent();
////								inte.setClass(All_List.this, Table_Flow.class);
////								inte.putExtras(b);
////								
////								startActivity(inte);
////								All_List.this.finish();
////								overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
//							}
//							else if(d2x<u2x){
//								FP.p("in flat2");
//								// �I�sAll_List �� �W�[��O����
//								Bundle b = new Bundle();
//								b.putString("nextlist", "TAB4");
//								
//								Intent inte = new Intent();
//								inte.setClass(All_List.this, Table_Flow.class);
//								inte.putExtras(b);
//								
//								startActivity(inte);
//								All_List.this.finish();
//								overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
//							}
//							
//							break;
//						}
//						return true;
//					}
//				});
				
				//no float
				if(scrollState==SCROLL_STATE_IDLE){
					benter.setAlpha(1.0f);
					bexit.setAlpha(1.0f);
					bmore.setAlpha(1.0f);
				}
				else{
					benter.setAlpha(0.5f);
					bexit.setAlpha(0.5f);
					bmore.setAlpha(0.5f);
				}
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		

		
	}
	
	@SuppressLint("NewApi")
	void initXml(){

		// ButtonYes init
		bmore = (Button) findViewById(R.id.buttonmore); // get ButtonYes
		OnClickListener bmoreOc = new OnClickListener() { // build ButtonYes Listener

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ValueAnimator rotationY = ObjectAnimator.ofFloat(bmore, "scaleY", 1.0f, 0.0f);
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
						
						//�I�s��h���
						callMoreMenu();
						
						
						
//						sortIndex++;
//						if(sortIndex>2){
//							sortIndex =0;
//						}
//						FP.p(""+sortIndex);
//						
//						changeSort();
						
						//�I����s2����檺�ʧ@
						ValueAnimator rotationY = ObjectAnimator.ofFloat(bmore, "scaleY", 0.0f, 1.0f);
//						ValueAnimator rotationX = ObjectAnimator.ofFloat(bexit, "rotationX", 0f, 360f);
						rotationY.setDuration(250);		        
//				        rotationX.setDuration(500);
				       
				        AnimatorSet as = new AnimatorSet();
				        as.playTogether(rotationY);
				        as.start();
					}
					
					@Override
					public void onAnimationCancel(Animator animation) {
						// TODO Auto-generated method stub
						
					}
				});
				
		
			}
		};
		bmore.setOnClickListener(bmoreOc);// set ButtonYes Listener
		
		
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
						AlertDialog ad = new AlertDialog.Builder(All_List.this,AlertDialog.THEME_TRADITIONAL).create();
						ad.setTitle("ĵ�i");//�]�wĵ�i���D
						ad.setMessage("�T�w���}??");//�]�wĵ�i���e
						ad.setButton("�T�w", new DialogInterface.OnClickListener() {//�]�w���s1
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								
								showNotificationOnScreenTop();
								
								System.exit(0);
								All_List.this.finish(); // exit program
								
//								//�I����s1����檺�ʧ@
//								//�ˬd�������A
//								ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//
//								NetworkInfo ni = cm.getActiveNetworkInfo();
//								if (ni == null) {//�S������
//
////									CopyRightFlow.this.finish();//�����{��
//									System.exit(0);
//								}
//								else if (ni != null) {//�Y��������s����~������
//
//									if( ni.isConnected()){
//										
//									
////									Uri uri = Uri.parse("https://play.google.com/store/apps/developer?id=Vulpse");
////									Intent intent = new Intent(Intent.ACTION_VIEW, uri);
////									startActivity(intent);
//
////									CopyRightFlow.this.finish();//�A�����{��
//									System.exit(0);
//									}
//								}
//								
//								All_List.this.finish(); // exit program
								
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
								
								
								
								// �I�s�s�W�� activity(ADEdit_Flow)
								
								Intent inte = new Intent();
								inte.setClass(All_List.this, ADEdit_Flow.class);
								
								startActivityForResult(inte, 0);
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
	
	// get result from ADEdit_Flow or ADEdit_Flow2
	protected void onActivityResult (int requestCode, int resultCode, Intent data){
		
		//result from ADEdit_Flow
		if(requestCode==0){
			
			//�^�� benter ������
			ValueAnimator rotationY = ObjectAnimator.ofFloat(benter, "scaleY", 0.0f, 1.0f);
//			ValueAnimator rotationX = ObjectAnimator.ofFloat(bexit, "rotationX", 0f, 360f);
			rotationY.setDuration(500);		        
//	        rotationX.setDuration(500);
	       
	        AnimatorSet as = new AnimatorSet();
	        as.playTogether(rotationY);
	        as.start();
			
			switch(resultCode){
			
			//no edit
			case 0:
				
				TraceLogger.print("cancel");
				break;
				
			// add new record from ADEdit_Flow
			case 1:
				TraceLogger.print("return OK");
				Bundle b = data.getExtras();
				
				String temptime = b.getString("time");
				String tempmoney = b.getString("money");
				String tempremark = b.getString("remark");
				
				accountdetailcount++;
				al.add(new AccountDetail(temptime ,tempmoney ,tempremark));		
				
//				Collections.sort(al, al.get(0).timeComp());
				setSort();
				
				// save 
				SharedPreferences sp2 = getSharedPreferences("bcn", 0);
				SharedPreferences.Editor spe = sp2.edit();
				
				spe.putInt("adcount", accountdetailcount);// for more putXXX
																// function
				for (int i = 0; i < accountdetailcount; i++) {

					spe.putString("ad_time" + i, al.get(i).time);
					spe.putString("ad_money" + i, al.get(i).money);
					spe.putString("ad_remark" + i, al.get(i).remark);
					
				}
				spe.commit();
				
				
				list.clear();
				
				for (int i = 0; i < al.size(); i++) {

					HashMap<String, String> map2 = new HashMap<String, String>();

					map2.put("str1",al.get(i).time);
					map2.put("str2",al.get(i).money);
					map2.put("str3",al.get(i).remark);

					TraceLogger.print("" + i);

					list.add(map2);
				}

				sa = new SimpleAdapter(All_List.this, list, R.layout.test2,
						new String[] { "str1", "str2", "str3" }, new int[] {
								R.id.date, R.id.money, R.id.detail });

				lv.setAdapter(sa);
				
				
//					HashMap<String, String> map = new HashMap<String, String>();
//
//					map.put("str1",al.get(al.size()-1).time);
//					map.put("str2",al.get(al.size()-1).money);
//					map.put("str3",al.get(al.size()-1).remark);
//
//					list.add(map);
//				
//
//				sa = new SimpleAdapter(All_List.this, list,R.layout.test2,
//						new String[] { "str1", "str2", "str3" }, 
//						new int[] {R.id.date, R.id.money, R.id.detail });
//
//				lv.setAdapter(sa);
				
				callNowalcount(0);
				
				break;
			}
			
		}
		//result from ADEdit_Flow2
		else if(requestCode==1){
			
			switch(resultCode){
			//cancel
			case 0:
				TraceLogger.print("in 0");
				break;
			
			// edit old record from ADEdit_Flow2
			case 1:
				
				TraceLogger.print("in 1");
				
				Bundle b2 = data.getExtras();
				int tempalnum2 = b2.getInt("alnum");
				String temptime2 = b2.getString("time");
				String tempmoney2 = b2.getString("money");
				String tempremark2 = b2.getString("remark");
				
				al.get(tempalnum2).time = temptime2;
				al.get(tempalnum2).money = tempmoney2;
				al.get(tempalnum2).remark = tempremark2;
				
				// save first
				SharedPreferences sp3 = getSharedPreferences("bcn", 0);
				SharedPreferences.Editor spe2 = sp3.edit();

				spe2.putInt("adcount", accountdetailcount);// for more putXXX
															// function
				for (int i = 0; i < accountdetailcount; i++) {

					spe2.putString("ad_time" + i, al.get(i).time);
					spe2.putString("ad_money" + i, al.get(i).money);
					spe2.putString("ad_remark" + i, al.get(i).remark);
				}
				spe2.commit();

				list.clear();
				
				for (int i = 0; i < al.size(); i++) {

					HashMap<String, String> map2 = new HashMap<String, String>();

					map2.put("str1",al.get(i).time);
					map2.put("str2",al.get(i).money);
					map2.put("str3",al.get(i).remark);

					TraceLogger.print("" + i);

					list.add(map2);
				}

				sa = new SimpleAdapter(All_List.this, list, R.layout.test2,
						new String[] { "str1", "str2", "str3" }, new int[] {
								R.id.date, R.id.money, R.id.detail });

				lv.setAdapter(sa);
				
				callNowalcount(0);
				
				break;
				
				// delete record
			case 2:
				TraceLogger.print("in 2");
				Bundle b3 = data.getExtras();
				int tempalnum3 = b3.getInt("alnum");
				
				al.remove(tempalnum3);
				
				accountdetailcount--;
				
				// save first
				SharedPreferences sp4 = getSharedPreferences("bcn", 0);
				SharedPreferences.Editor spe3 = sp4.edit();

				spe3.putInt("adcount", accountdetailcount);// for more putXXX
															// function
				for (int i = 0; i < accountdetailcount; i++) {

					spe3.putString("ad_time" + i, al.get(i).time);
					spe3.putString("ad_money" + i, al.get(i).money);
					spe3.putString("ad_remark" + i, al.get(i).remark);
				}
				spe3.commit();

				list.clear();
				
				for (int i = 0; i < al.size(); i++) {

					HashMap<String, String> map2 = new HashMap<String, String>();

					map2.put("str1",al.get(i).time);
					map2.put("str2",al.get(i).money);
					map2.put("str3",al.get(i).remark);

					TraceLogger.print("" + i);

					list.add(map2);
				}

				sa = new SimpleAdapter(All_List.this, list, R.layout.test2,
						new String[] { "str1", "str2", "str3" }, new int[] {
								R.id.date, R.id.money, R.id.detail });

				lv.setAdapter(sa);
				
				callNowalcount(0);
				
				break;
			}
		}
		else if(requestCode==3){
			
			switch(resultCode){
			
			case 0:
				TraceLogger.print("get it");
				callNowalcount(0);
				break;
				
			case 1:
				lv.setAlpha(0);
				System.exit(0);
				All_List.this.finish(); // exit program
				break;
			}
		}
		else if(requestCode==4){
			
			switch(resultCode){
			
			//no lock 
			case 0:
				TraceLogger.print("get it");
				break;
			//lock 
			case 1:
				
				break;
			}
		}
		
	}
	
	String getalmoney(){
		int tempi =0;
		for(int i=0; i<al.size(); i++){
			int tempi2 = Integer.parseInt(al.get(i).money);
			tempi = tempi2+tempi;
			TraceLogger.print("tempi: "+tempi);
		}
		String temps = String.valueOf(tempi);
		TraceLogger.print("temps: "+temps);
		
		return temps;
	}
	
	String getalcount(){
		int tempi =al.size();
		
		String temps = String.valueOf(tempi);
		TraceLogger.print("temps: "+temps);
		
		return temps;
	}
	
	/**
	 * 0:toast��� 1:alertdialog���
	 */
	void callNowalcount(int chnum){
		
		if(chnum==1){
			
		
		//use AlertDialog
		AlertDialog ad = new AlertDialog.Builder(All_List.this,AlertDialog.THEME_TRADITIONAL).create();
        ad.setTitle("��O�M�洣��!!");//�]�wĵ�i���D
        ad.setMessage("�Ҧ���O��X�� "+getalmoney()+" ��\n" +
				"��O�����`�Ƭ� "+getalcount()+" ��");
        
		ad.setButton2("�I����������", new DialogInterface.OnClickListener() {// �]�w���s2

					@Override
					public void onClick(DialogInterface dialog, int which) {

						// �I����s2����檺�ʧ@
						
					}
				});
        
        ad.setCanceledOnTouchOutside(true);//��ĵ�i���ܥX�{��,�I�ﴣ�ܥH�~�d��,�O�_�|����,�w�]�Otrue
        
        ad.setCancelable(true);//��ĵ�i���ܥX�{��,�I���L������s(backkey����),�O�_�|����,�w�]�Otrue
        
        ad.show();
		}
		else if(chnum==0){
		// use Toast
        Toast.makeText(All_List.this, "�Ҧ���O��X�� "+getalmoney()+" ��\n" +
				"��O�����`�Ƭ� "+getalcount()+" ��",Toast.LENGTH_SHORT).show();
		}
        
	}
	
	@SuppressWarnings("deprecation")
	void callFirstEditmenu(final int tempi){
		
		
		AlertDialog ad = new AlertDialog.Builder(All_List.this,AlertDialog.THEME_TRADITIONAL).create();
		
//        ad.setTitle("���D");//�]�wĵ�i���D
        ad.setMessage("�ֳt�s��ﶵ");//�]�wĵ�i���e
        ad.setButton("��^", new DialogInterface.OnClickListener() {//�]�w���s1
            
            @Override
            public void onClick(DialogInterface dialog, int which) {
                
                //�I����s1����檺�ʧ@
            }
        });
        ad.setButton2("�s��", new DialogInterface.OnClickListener() {//�]�w���s2
            
            @Override
            public void onClick(DialogInterface dialog, int which) {
                
                //�I����s2����檺�ʧ@
            	Bundle b = new Bundle();
				b.putInt("alnum", tempi);
				b.putString("altime", al.get(tempi).time);
				b.putString("almoney", al.get(tempi).money);
				b.putString("alremark", al.get(tempi).remark);
				Intent inte = new Intent();
				inte.setClass(All_List.this, ADEdit_Flow2.class);
				inte.putExtras(b);
				startActivityForResult(inte, 1);
            }
        });
        
        
        //??
        ad.setButton3("�R��", new DialogInterface.OnClickListener() {//�]�w���s3
            
            @Override
            public void onClick(DialogInterface dialog, int which) {
                
                //�I����s3����檺�ʧ@
            	al.remove(tempi);
				
				accountdetailcount--;
				
				// save first
				SharedPreferences sp4 = getSharedPreferences("bcn", 0);
				SharedPreferences.Editor spe3 = sp4.edit();

				spe3.putInt("adcount", accountdetailcount);// for more putXXX
															// function
				for (int i = 0; i < accountdetailcount; i++) {

					spe3.putString("ad_time" + i, al.get(i).time);
					spe3.putString("ad_money" + i, al.get(i).money);
					spe3.putString("ad_remark" + i, al.get(i).remark);
				}
				spe3.commit();

				list.clear();
				
				for (int i = 0; i < al.size(); i++) {

					HashMap<String, String> map2 = new HashMap<String, String>();

					map2.put("str1",al.get(i).time);
					map2.put("str2",al.get(i).money);
					map2.put("str3",al.get(i).remark);

					TraceLogger.print("" + i);

					list.add(map2);
				}

				sa = new SimpleAdapter(All_List.this, list, R.layout.test2,
						new String[] { "str1", "str2", "str3" }, new int[] {
								R.id.date, R.id.money, R.id.detail });

				lv.setAdapter(sa);
				
				callNowalcount(0);
            }
        });
        
        ad.setCanceledOnTouchOutside(true);//��ĵ�i���ܥX�{��,�I�ﴣ�ܥH�~�d��,�O�_�|����,�w�]�Otrue
        
        ad.setCancelable(true);//��ĵ�i���ܥX�{��,�I���L������s(backkey����),�O�_�|����,�w�]�Otrue
        
        ad.show();//��ܫ��s
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
	
	int dx=0,ux=0;
	
	public boolean onTouchEvent(MotionEvent event){
//		FP.p("in onTouchEvent");
		
		switch(event.getAction()){
		
		case MotionEvent.ACTION_DOWN:
//			dx = (int)event.getX();
			break;
			
		case MotionEvent.ACTION_UP:
			
			
			break;
		}
		
			return true;
	}

	void changeSort() {

		setSort();

		// save first
		SharedPreferences sp4 = getSharedPreferences("bcn", 0);
		SharedPreferences.Editor spe3 = sp4.edit();

		spe3.putInt("adcount", accountdetailcount);// for more putXXX
													// function
		for (int i = 0; i < accountdetailcount; i++) {

			spe3.putString("ad_time" + i, al.get(i).time);
			spe3.putString("ad_money" + i, al.get(i).money);
			spe3.putString("ad_remark" + i, al.get(i).remark);
		}
		spe3.commit();

		list.clear();

		for (int i = 0; i < al.size(); i++) {

			HashMap<String, String> map2 = new HashMap<String, String>();

			map2.put("str1", al.get(i).time);
			map2.put("str2", al.get(i).money);
			map2.put("str3", al.get(i).remark);

			TraceLogger.print("" + i);

			list.add(map2);
		}

		sa = new SimpleAdapter(All_List.this, list, R.layout.test2,
				new String[] { "str1", "str2", "str3" }, new int[] { R.id.date,
						R.id.money, R.id.detail });

		lv.setAdapter(sa);

		callNowalcount(0);

		listShowAnim();

		saveSortIndex();
	}
	
	/**
	 * 0:�w�]�L�Ƨ� 1:���B�j�p�Ƨ�  2:����Ƨ� 
	 */
	void setSort(){
		
		AD_Sort ads;
		
		switch(sortIndex){
		
		case 0:
			
			break;
		case 1:
			ads = new MoneyComparator();
			Collections.sort(al, ads);
			break;
		case 2:
			ads = new TimeComparator();
			Collections.sort(al, ads);
			break;
			
			
		}
		
	}
	
	void loadSortIndex() {

		// get data for accountdetail
		SharedPreferences sp = getSharedPreferences("bcn", 0);

		// get accountdetail count
		sortIndex = sp.getInt("sortindex", 2);
	}
	
	void saveSortIndex(){
		// get data for accountdetail
		SharedPreferences sp = getSharedPreferences("bcn", 0);
		SharedPreferences.Editor spe = sp.edit();
		spe.putInt("sortindex", sortIndex);// for more putXXX
		spe.commit();
	}
	
	void callMoreMenu(){
		
		AlertDialog.Builder ab = new AlertDialog.Builder(All_List.this);
		ab.setTitle("��h�ﶵ");
		ab.setNegativeButton("���", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//do nothing back to All_List 
			}
		});
		ab.setItems(R.array.moremenu, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				TraceLogger.print("which:"+which);
				switch(which){
				
				case 0:
					callNowalcount(1);
					break;
				case 1:
					sortIndex = 1;
					changeSort();
					break;
				case 2:
					sortIndex = 2;
					changeSort();
					break;
				case 3:
					//use AlertDialog
					AlertDialog ad = new AlertDialog.Builder(All_List.this,AlertDialog.THEME_TRADITIONAL).create();
			        ad.setTitle("�R���Ҧ���O!!");//�]�wĵ�i���D
			        ad.setMessage("�T�w�R���Ҧ���O����??");
			        
					ad.setButton2("�T�w", new DialogInterface.OnClickListener() {// �]�w���s2

								@Override
								public void onClick(DialogInterface dialog, int which) {

									//�R�������O��
					            	al.clear();
									
									accountdetailcount=0;
									
									// save first
									SharedPreferences sp4 = getSharedPreferences("bcn", 0);
									SharedPreferences.Editor spe3 = sp4.edit();
									
									spe3.clear();
									spe3.putInt("adcount", accountdetailcount);// for more putXXX
																				// function
//									for (int i = 0; i < accountdetailcount; i++) {
//
//										spe3.putString("ad_time" + i, al.get(i).time);
//										spe3.putString("ad_money" + i, al.get(i).money);
//										spe3.putString("ad_remark" + i, al.get(i).remark);
//									}
									spe3.commit();

									list.clear();
									
									for (int i = 0; i < al.size(); i++) {

										HashMap<String, String> map2 = new HashMap<String, String>();

										map2.put("str1",al.get(i).time);
										map2.put("str2",al.get(i).money);
										map2.put("str3",al.get(i).remark);

										TraceLogger.print("" + i);

										list.add(map2);
									}

									sa = new SimpleAdapter(All_List.this, list, R.layout.test2,
											new String[] { "str1", "str2", "str3" }, new int[] {
													R.id.date, R.id.money, R.id.detail });

									lv.setAdapter(sa);
									
									callNowalcount(0);
									
								}
							});
					ad.setButton("���", new DialogInterface.OnClickListener() {// �]�w���s2

						@Override
						public void onClick(DialogInterface dialog, int which) {
							
						}
					});
			        
			        ad.setCanceledOnTouchOutside(true);//��ĵ�i���ܥX�{��,�I�ﴣ�ܥH�~�d��,�O�_�|����,�w�]�Otrue
			        
			        ad.setCancelable(true);//��ĵ�i���ܥX�{��,�I���L������s(backkey����),�O�_�|����,�w�]�Otrue
			        
			        ad.show();
					break;
				case 4:
					
					//call Lock_Flow
					Intent inte = new Intent();
					inte.setClass(All_List.this, Lock_Flow.class);
					
					startActivityForResult(inte, 4);
					overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
					
					break;
				case 5:
					
					//use AlertDialog
					AlertDialog ad2 = new AlertDialog.Builder(All_List.this,AlertDialog.THEME_TRADITIONAL).create();
					ad2.setTitle("�R���K�X��!!");//�]�wĵ�i���D
					ad2.setMessage("�T�w�R���K�X��??");
			        
					ad2.setButton2("�T�w", new DialogInterface.OnClickListener() {// �]�w���s2

								@Override
								public void onClick(DialogInterface dialog, int which) {

									
									// save first
									SharedPreferences sp4 = getSharedPreferences("bcn", 0);
									SharedPreferences.Editor spe3 = sp4.edit();
									
									spe3.putString("adlock", "");// for more putXXX

									spe3.commit();
								}
							});
					ad2.setButton("���", new DialogInterface.OnClickListener() {// �]�w���s2

						@Override
						public void onClick(DialogInterface dialog, int which) {
							
						}
					});
			        
					ad2.setCanceledOnTouchOutside(true);//��ĵ�i���ܥX�{��,�I�ﴣ�ܥH�~�d��,�O�_�|����,�w�]�Otrue
			        
					ad2.setCancelable(true);//��ĵ�i���ܥX�{��,�I���L������s(backkey����),�O�_�|����,�w�]�Otrue
			        
					ad2.show();
					
					break;
				}
			}
		});
		ab.show();
	}
	
	public boolean onKeyDown(int keycode, KeyEvent event){
		
		switch(keycode){
			
		// menu key
		case 82:
			callMoreMenu();
			break;
		}
		
		return super.onKeyDown(keycode, event);
	}
	
	void lock() {
		// get data for accountdetail
		SharedPreferences sp = getSharedPreferences("bcn", 0);

		// get accountdetail count
		String passstr = sp.getString("adlock", "");
		
		// no lock
		if(passstr.equals("")){
			
		}
		//lock exist
		else {
			
		//call Lock_Flow
			Intent inte = new Intent();
			inte.setClass(All_List.this, UnLock_Flow.class);
			
			startActivityForResult(inte, 3);
			overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
		}
	}
	
	void checkPlayNum(){

		// get data for accountdetail
		SharedPreferences sp = getSharedPreferences("bcn", 0);
		SharedPreferences.Editor spe3 = sp.edit();
		
		// get accountdetail count
		playNum = sp.getInt("adplaynum", playNum);
		
		if(playNum==0)
		{
			//use AlertDialog
			AlertDialog ad = new AlertDialog.Builder(All_List.this,AlertDialog.THEME_TRADITIONAL).create();
	        ad.setTitle("�w��ϥη�²�°O�b��!!");//�]�wĵ�i���D
	        ad.setMessage("�ϥλ���: \n"+"�W��4�Ӽ��ҥi�����[��P�ɴ�����O\n"
	        +"�W�[���� ���I�����U���[�J��O����\n");
	        
			ad.setButton2("�T�w", new DialogInterface.OnClickListener() {// �]�w���s2

						@Override
						public void onClick(DialogInterface dialog, int which) {

							
						}
					});
		
	        ad.setCanceledOnTouchOutside(true);//��ĵ�i���ܥX�{��,�I�ﴣ�ܥH�~�d��,�O�_�|����,�w�]�Otrue
	        
	        ad.setCancelable(true);//��ĵ�i���ܥX�{��,�I���L������s(backkey����),�O�_�|����,�w�]�Otrue
	        
	        ad.show();
		}
		else{
			
		}
		
		playNum++;
		
		spe3.putInt("adplaynum", playNum);// for more putXXX

		spe3.commit();
		
	}
	
	void showNotificationOnScreenTop(){
		
		// get data for accountdetail
		SharedPreferences sp = getSharedPreferences("bcn", 0);
		SharedPreferences.Editor spe3 = sp.edit();

		// get accountdetail count
		int tempi = sp.getInt("adplaynum", playNum);
		
		Intent it = new Intent(this,Table_Flow.class);
		it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent pi = PendingIntent.getActivity(All_List.this, 0, it, 0);
		
		Notification notification = new Notification();
		notification.icon = R.drawable.notpic;
		notification.tickerText = "�w��ϥη�²�°O�b��!!!";
		
		if(tempi==1)
		{
			notification.defaults = Notification.DEFAULT_ALL;
		}
		
		
		notification.setLatestEventInfo(All_List.this, "��²�°O�b��", "�w��ϥη�²�°O�b��!!!", pi);

		NotificationManager notificationManager;
		notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		notificationManager.notify(0, notification);
	}
}

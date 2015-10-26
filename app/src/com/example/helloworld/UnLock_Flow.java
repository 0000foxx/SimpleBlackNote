package com.example.helloworld;

import FoXxLib.FP;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.tlyerfoxx.sbcnote.R;

@SuppressLint("NewApi")
public class UnLock_Flow extends Activity{

	EditText et;
	Button byes;
	Button bno;
	
	TextView tv;
	
	int tryNum =2;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		initXml();
		
	}
	
	void initXml(){
		setContentView(R.layout.unlockflow);
		
		tv = (TextView)findViewById(R.id.textView1);
		
		tv.setText("請輸入密碼解鎖");
		
		et = (EditText)findViewById(R.id.username_edit);
		et.setHint("輸入密碼");
		
		byes = (Button)findViewById(R.id.buttonYes);
		byes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				checkPassWord();
			}
		});
		
		
		bno = (Button)findViewById(R.id.buttonNo);
		bno.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//use AlertDialog
				AlertDialog ad = new AlertDialog.Builder(UnLock_Flow.this,AlertDialog.THEME_TRADITIONAL).create();
		        ad.setTitle("警告!!");//設定警告標題
		        ad.setMessage("確定離開程序??");
		        
				ad.setButton2("確定", new DialogInterface.OnClickListener() {// 設定按鈕2

							@Override
							public void onClick(DialogInterface dialog, int which) {

								// 點選按鈕2後執行的動作
								finishEdit(1);
							}
						});
				ad.setButton("取消", new DialogInterface.OnClickListener() {// 設定按鈕2

					@Override
					public void onClick(DialogInterface dialog, int which) {

						// 點選按鈕2後執行的動作
						
					}
				});
				
		        ad.setCanceledOnTouchOutside(true);//當警告提示出現後,點選提示以外範圍,是否會取消提示,預設是true
		        
		        ad.setCancelable(true);//當警告提示出現後,點選其他實體按鈕(backkey等等),是否會取消提示,預設是true
		        
		        ad.show();
				
				
				
			}
		});
	}
	
	void checkPassWord(){
		
		if(et.getText()==null){
			// use Toast
			tryNum--;
	        Toast.makeText(UnLock_Flow.this, "未輸入密碼!!!"+"還剩"+tryNum+"次",Toast.LENGTH_SHORT).show();
	        
	        et.setText("");
		}
		else{
			
			String tempstr = et.getText().toString();
			
			// get data for accountdetail
			SharedPreferences sp = getSharedPreferences("bcn", 0);

			// get accountdetail count
			String passstr = sp.getString("adlock","");
			
			if(tempstr.equals(passstr)){
				Toast.makeText(UnLock_Flow.this, "密碼正確!!!",Toast.LENGTH_SHORT).show();
				finishEdit(0);
			}
			else{
				Toast.makeText(UnLock_Flow.this, "密碼錯誤!!!"+"還剩"+tryNum+"次",Toast.LENGTH_SHORT).show();
				tryNum--;
			}
			
		}
		
		if(tryNum<0){
			
			finishEdit(1);
		}
	}
	
	public boolean onKeyDown(int keycode, KeyEvent event){
		
		FP.p("keycode:"+keycode);
		FP.p("event:"+event.getAction());
		
		finishEdit(1);
		
		switch(keycode){
			
		// menu key
		case 82:
			
			break;
		}
		
		return super.onKeyDown(keycode, event);
	}
	
	/**
	 * 
	 * @param result 0 ok
	 */
	void finishEdit(int result){
		
		if(result==0){
			UnLock_Flow.this.setResult(0);
			
			UnLock_Flow.this.finish();
			
			overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
		}
		else if(result==1){
			
			UnLock_Flow.this.setResult(1);
			
			UnLock_Flow.this.finish();
			
		}
	}
	
	
}


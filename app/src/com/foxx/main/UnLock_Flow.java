package com.foxx.main;

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
		
		tv.setText("�п�J�K�X����");
		
		et = (EditText)findViewById(R.id.username_edit);
		et.setHint("��J�K�X");
		
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
		        ad.setTitle("ĵ�i!!");//�]�wĵ�i���D
		        ad.setMessage("�T�w���}�{��??");
		        
				ad.setButton2("�T�w", new DialogInterface.OnClickListener() {// �]�w���s2

							@Override
							public void onClick(DialogInterface dialog, int which) {

								// �I����s2����檺�ʧ@
								finishEdit(1);
							}
						});
				ad.setButton("���", new DialogInterface.OnClickListener() {// �]�w���s2

					@Override
					public void onClick(DialogInterface dialog, int which) {

						// �I����s2����檺�ʧ@
						
					}
				});
				
		        ad.setCanceledOnTouchOutside(true);//��ĵ�i���ܥX�{��,�I�ﴣ�ܥH�~�d��,�O�_�|����,�w�]�Otrue
		        
		        ad.setCancelable(true);//��ĵ�i���ܥX�{��,�I���L������s(backkey����),�O�_�|����,�w�]�Otrue
		        
		        ad.show();
				
				
				
			}
		});
	}
	
	void checkPassWord(){
		
		if(et.getText()==null){
			// use Toast
			tryNum--;
	        Toast.makeText(UnLock_Flow.this, "����J�K�X!!!"+"�ٳ�"+tryNum+"��",Toast.LENGTH_SHORT).show();
	        
	        et.setText("");
		}
		else{
			
			String tempstr = et.getText().toString();
			
			// get data for accountdetail
			SharedPreferences sp = getSharedPreferences("bcn", 0);

			// get accountdetail count
			String passstr = sp.getString("adlock","");
			
			if(tempstr.equals(passstr)){
				Toast.makeText(UnLock_Flow.this, "�K�X���T!!!",Toast.LENGTH_SHORT).show();
				finishEdit(0);
			}
			else{
				Toast.makeText(UnLock_Flow.this, "�K�X��~!!!"+"�ٳ�"+tryNum+"��",Toast.LENGTH_SHORT).show();
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


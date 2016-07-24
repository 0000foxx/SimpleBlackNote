package com.foxx.main;

import java.util.Calendar;

import FoXxLib.FP;
import android.R.string;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.tlyerfoxx.sbcnote.R;

public class ADEdit_Flow2 extends Activity{

	EditText etTime;
	EditText etMoney;
	EditText etDetail;
	
	Button Bok;
	Button Bcancel;
	Button Bdelete;
	
	int alnum;
	String alTime;
	String alMoney;
	String alRemark;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		Bundle b = this.getIntent().getExtras();
		alnum = b.getInt("alnum");
		alTime = b.getString("altime");
		alMoney = b.getString("almoney");
		alRemark = b.getString("alremark");
		
		initXml();
		initetTime();
		initetMoney();
		initetDetail();
	}
	
	void initXml(){
		
		setContentView(R.layout.adedit2);
		
		
		Bok = (Button)findViewById(R.id.buttonok);
		Bok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				finishEdit(1);
			}
		});
		
		Bcancel = (Button)findViewById(R.id.buttoncancel);
		Bcancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				finishEdit(0);
			}
		});
		
		Bdelete = (Button)findViewById(R.id.buttondelete);
		Bdelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				finishEdit(2);
			}
		});
	}
	
	void initetTime() {
		//get edTime
		etTime = (EditText) findViewById(R.id.editTextTime);		
		//get now date
		final Calendar cal = Calendar.getInstance();
		String dateStr = "" + cal.get(Calendar.YEAR) // 2012
				+ "/" + (cal.get(Calendar.MONTH) + 1) // 12 (add 1 because it start from 0)
				+ "/" + cal.get(Calendar.DATE);// 24
												// ....
		//now date set to etTime
		etTime.setText(alTime);
		// set keyboard disappear
		etTime.setInputType(InputType.TYPE_NULL);
		// touch etTime will show datePickDialog
		etTime.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				new DatePickerDialog(ADEdit_Flow2.this, new DatePickerDialog.OnDateSetListener()
				{
					
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						// TODO Auto-generated method stub
						etTime.setText(""+year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
						FP.p("touch");
					}
				}, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();
			}
		});
	}
	
	void initetMoney(){
		//get EditText
		etMoney = (EditText) findViewById(R.id.editTextMoney);
		etMoney.setText(alMoney);
		// add Listener for check input length < 8
		etMoney.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(s.length()>7){
					Toast.makeText(ADEdit_Flow2.this, "�̤j��J7��Ʀr,�Э��s��J",Toast.LENGTH_SHORT).show();
					s.clear();
				}
				else if(s.length()<=0){
					Toast.makeText(ADEdit_Flow2.this, "����J�Ʀr,�Э��s��J",Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	/**
	 * 
	 * @param result 0 cancel, 1 ok, 2 delete
	 */
	void finishEdit(int result){
		
        Intent inte = this.getIntent();
		
//        check etMoney is null?
        if(etMoney.getText().length()<=0){
        	etMoney.setText("0");
        }
      //handle Illegal number. exam: 007 -> 7 
        String temps =  etMoney.getText().toString();   
        int tempi = Integer.parseInt(temps);

//      check etDetail is null?
      if(etDetail.getText().length()<=0){
    	  etDetail.setText("");
      }
        

		
		//cancel
		if(result==0){
			
			ADEdit_Flow2.this.setResult(0);
			
			ADEdit_Flow2.this.finish();
			
			overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);

		}
		//edit old
		else if(result==1){
			
			Bundle b = new Bundle();
			b.putInt("alnum", alnum);
			b.putString("time", etTime.getText().toString());
			b.putString("money", String.valueOf(tempi));
			b.putString("remark", etDetail.getText().toString());
			
			inte.putExtras(b);
			
			ADEdit_Flow2.this.setResult(1, inte);
			
			ADEdit_Flow2.this.finish();
			
			overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
			
		}
		//delete
		else if(result==2){
			
			Bundle b = new Bundle();
			b.putInt("alnum", alnum);
			inte.putExtras(b);
			
			ADEdit_Flow2.this.setResult(2, inte);
			
			ADEdit_Flow2.this.finish();
		}
	}
	
	void initetDetail() {

		// get EditText
		etDetail = (EditText) findViewById(R.id.editTextDetail);
		etDetail.setText(alRemark);
		// add Listener for check input length < 8
		etDetail.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (s.length() <= 0) {
//					Toast.makeText(ADEdit_Flow.this, "����J�Ƶ�,�Э��s��J",
//							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		FP.p("Touch OK");
		
		return super.onTouchEvent(event);
	}
}

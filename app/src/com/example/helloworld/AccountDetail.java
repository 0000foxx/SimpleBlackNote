package com.example.helloworld;

import com.tlyerfoxx.sbcnote.R;
import java.util.Comparator;

import FoXxLib.FP;

public class AccountDetail 
//implements Comparable<Object>
{

	static int dataCount;
	public String time;
	public String money;
	public String remark;

	public AccountDetail(){
		dataCount++;
		time = "0000/00/00";
		money = "0";
		remark = "0";
	}
	
	public AccountDetail(String time,String money,String type){
		
		
		dataCount++;
		this.time = time;
		this.money = money;
		this.remark = type;
	}

//	@Override
//	public int compareTo(Object another) {
//		// TODO Auto-generated method stub
//		
//		AccountDetail adAnother = (AccountDetail)another;
//		
//		if(Integer.valueOf(this.money)>Integer.valueOf(adAnother.money))
//		{
//			return 1;// back
//		}
//		else if(Integer.valueOf(this.money)<Integer.valueOf(adAnother.money)){
//			
//			return -1;//forward
//		}
//		else{
//			return 0;
//		}
//		
//		
//	}
	
	public MoneyComparator moneyComp(){
		
		MoneyComparator mc = new MoneyComparator();
		
		return mc;
	}
	
	public TimeComparator timeComp(){
		
		TimeComparator tc = new TimeComparator();
		
		return tc;
	}
}

//class MoneyComparator implements Comparator<Object>{
//
//	@Override
//	public int compare(Object lhs, Object rhs) {
//		// TODO Auto-generated method stub
//		AccountDetail ad1 = (AccountDetail)lhs;
//		AccountDetail ad2 = (AccountDetail)rhs;
//		if(Integer.valueOf(ad1.money)>Integer.valueOf(ad2.money)){
//			return -1;
//		}
//		else if(Integer.valueOf(ad1.money)<Integer.valueOf(ad2.money)){
//			return 1;
//		}
//		else{
//			return 0;
//		}
//	}
//	
//}
//
//class TimeComparator implements Comparator<Object>{
//
//	@Override
//	public int compare(Object lhs, Object rhs) {
//		// TODO Auto-generated method stub
//		int tempReturn =0;
//		AccountDetail ad1 = (AccountDetail)lhs;
//		AccountDetail ad2 = (AccountDetail)rhs;
//		
//		String[] tempstr1 = ad1.time.split("[/]");
//		int[] tempint1 = new int[tempstr1.length];
//		for(int i=0; i<tempstr1.length; i++){
//			tempint1[i] = Integer.valueOf(tempstr1[i]);
////			FP.p(""+tempint1[i]);
//		}
//		
//		String[] tempstr2 = ad2.time.split("[/]");
//		int[] tempint2 = new int[tempstr2.length];
//		for(int i=0; i<tempstr2.length; i++){
//			tempint2[i] = Integer.valueOf(tempstr2[i]);
////			FP.p(""+tempint2[i]);
//		}
//		
//		for(int i=0; i<tempint1.length; i++){
//			
//			if(tempint1[i]>tempint2[i]){
//				tempReturn = -1;
//				break;
//			}
//			else if(tempint1[i]<tempint2[i]){
//				tempReturn = 1;
//				break;
//			}
//			else if(tempint1[i]==tempint2[i])
//			{
//				tempReturn =0;
//			}
//		}
//		
//			return tempReturn;
//
//	}
//	
//}



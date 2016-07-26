package com.simpleblacknote.main;

import com.tlyerfoxx.sbcnote.R;
import java.util.Comparator;

public abstract class AD_Sort implements Comparator<Object>{

}

class MoneyComparator extends AD_Sort{
	
	@Override
	public int compare(Object lhs, Object rhs) {
		// TODO Auto-generated method stub
		AccountDetail ad1 = (AccountDetail)lhs;
		AccountDetail ad2 = (AccountDetail)rhs;
		if(Integer.valueOf(ad1.money)>Integer.valueOf(ad2.money)){
			return -1;
		}
		else if(Integer.valueOf(ad1.money)<Integer.valueOf(ad2.money)){
			return 1;
		}
		else{
			return 0;
		}
	}
	
}

class TimeComparator extends AD_Sort{

	@Override
	public int compare(Object lhs, Object rhs) {
		// TODO Auto-generated method stub
		int tempReturn =0;
		AccountDetail ad1 = (AccountDetail)lhs;
		AccountDetail ad2 = (AccountDetail)rhs;
		
		String[] tempstr1 = ad1.time.split("[/]");
		int[] tempint1 = new int[tempstr1.length];
		for(int i=0; i<tempstr1.length; i++){
			tempint1[i] = Integer.valueOf(tempstr1[i]);
//			FP.p(""+tempint1[i]);
		}
		
		String[] tempstr2 = ad2.time.split("[/]");
		int[] tempint2 = new int[tempstr2.length];
		for(int i=0; i<tempstr2.length; i++){
			tempint2[i] = Integer.valueOf(tempstr2[i]);
//			FP.p(""+tempint2[i]);
		}
		
		for(int i=0; i<tempint1.length; i++){
			
			if(tempint1[i]>tempint2[i]){
				tempReturn = -1;
				break;
			}
			else if(tempint1[i]<tempint2[i]){
				tempReturn = 1;
				break;
			}
			else if(tempint1[i]==tempint2[i])
			{
				tempReturn =0;
			}
		}
		
			return tempReturn;

	}
	
}
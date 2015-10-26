package TouchDetect;

import com.example.helloworld.CopyRightFlow;
import com.tlyerfoxx.sbcnote.R;
//import com.example.helloworld.R.string;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.TextView;

public class TouchDetect extends Activity{
	
	static final String tLog = "Trace Log";
	
	TestTextView ttv;
	
	int dx;// get touch down x position
	int dy;// get touch down y position
	int mx;// get touch move x position
	int my;// get touch move y position 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        
        ttv = new TestTextView(this);
        
        ttv.setTextColor(0xff888888); // set color
        ttv.setTextSize(18.0f); // set size
        ttv.setTypeface(Typeface.SERIF);// set typeface 
        ttv.setText(R.string.copy_right);// change text
        ttv.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        setContentView(ttv);

        
    }
    
    public boolean onTouchEvent(MotionEvent event){
		
		int touchCount = event.getPointerCount(); // get touchcount
		
		
		switch(event.getAction()){
		
			case MotionEvent.ACTION_DOWN://touch down
				ttv.setTextColor(Color.RED);
				dx = (int) event.getX();// get x position
				dy = (int) event.getY();// get y position
				break;
			case MotionEvent.ACTION_UP://touch leave
				ttv.setTextColor(0xff888888);
				break;
			case MotionEvent.ACTION_MOVE://touch move
				
				mx = (int) event.getX();// get x position
				my = (int) event.getY();// get y position
				
				if(Math.abs(dx-mx)>20 || Math.abs(dy-my)>20)
				{
					ttv.setTextColor(Color.GREEN);
				}
				
				break;
		}
		
		return true;
	}

  
}


class TestTextView extends TextView{

	public TestTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
}
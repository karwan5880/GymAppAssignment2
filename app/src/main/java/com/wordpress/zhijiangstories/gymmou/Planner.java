package com.example.gymappassignment;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class Planner extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
		//ll.setOrientation(LinearLayout.HORIZONTAL);
        
        //CalendarView cv = new CalendarView(this);
        //ll.addView(cv);
        
        LinearLayout ll2 = new LinearLayout(this);
        ll2.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        ll2.setLayoutParams(lp);
        ll2.setGravity(Gravity.CENTER_HORIZONTAL);       
        ll.addView(ll2);        
        TextView tv2 = new TextView(this);
        tv2.setText("March 2017");
        ll2.addView(tv2);
        
        
                
        
        CustomView cv ;
        //List<CustomView> cvlist = new ArrayList<CustomView>();
        List<LinearLayout> lllist = new ArrayList<LinearLayout>();
    	for(int j=0;j<4;j++){
            LinearLayout ll3 = new LinearLayout(this);
            ll3.setOrientation(LinearLayout.HORIZONTAL);   
            lllist.add(ll3);
            ll.addView(ll3);
    	}
    	LinearLayout lltemp;
    	for(int j=0;j<4;j++){
    		lltemp = lllist.get(j);       
            for(int i=0;i<7;i++){
            	cv = new CustomView(this);
            	//cvlist.add(cv);
            	lltemp.addView(cv);
            }
    	}


        /*CustomView cv2 = new CustomView(this);
        ll3.addView(cv2);
        CustomView cv3 = new CustomView(this);
        ll3.addView(cv3);*/
        
        //Button btt = new Button(this);
        //ll3.addView(btt);

        //Button bt2t = new Button(this);
        //ll3.addView(bt2t);
        

        
        
        
        
		LinearLayout ll1 = new LinearLayout(this);
        ll1.setOrientation(LinearLayout.VERTICAL);


        final EditText et1 = new EditText(this);
        
        ll1.addView(et1);      

        MySqlHelper mysqlhelper;
        
        Button bt1 = new Button(this);
        bt1.setText("Submit");   
        bt1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if(et1.getText().toString().matches("")){
					Toast.makeText(Planner.this, "text", Toast.LENGTH_SHORT).show();
				}
				else{
					int value = Integer.valueOf(et1.getText().toString());
					
					MySqlHelper mysqlhelper;
					
			        mysqlhelper = new MySqlHelper(Planner.this);
			        mysqlhelper.openToWrite();
			        mysqlhelper.insert(value);
			        mysqlhelper.close();
				}
				
				
			}
        });
        ll1.addView(bt1);     
        
        

        /*
        mysqlhelper = new MySqlHelper(this);
        mysqlhelper.openToRead();
        String mycontent = mysqlhelper.queueAll();
        mysqlhelper.close();*/
        
        
        
        
        
        setContentView(ll);
        //setContentView(ll1);
        //setContentView(R.layout.activity_planner);
        
		
		
		
	}
	
	private static class CustomView extends View{
		
	    private Rect rectangle;
	    private Paint paint;
	    
        int x ;
        int y ;
        int sidelength ;
        double sidelength2;

	    public CustomView(Context context) {
	        super(context);
	        init(context);

	    }
	    
	    public void init(Context context){

	        x = 0;
	        y = 0;
	        //sidelength2 = Math.floor(getWidth() / 7 - 10) ;
	        sidelength2 = Math.floor(Resources.getSystem().getDisplayMetrics().widthPixels / 7 - 11);
	        sidelength = (int)sidelength2;

			//Toast.makeText(context, String.valueOf(sidelength2), Toast.LENGTH_SHORT).show();
	        
	        rectangle = new Rect(x, y, sidelength, sidelength);

	        paint = new Paint();
	        paint.setColor(Color.GRAY);
	    }
	    

	    @Override
	    protected void onDraw(Canvas canvas) {
	        //canvas.drawColor(Color.BLUE);
	        canvas.drawRect(rectangle, paint);
	    }

		@Override
		protected void onLayout(boolean changed, int l, int t, int r, int b) {
			//super.onLayout(changed,l,t,r,b);	
			MarginLayoutParams margins = MarginLayoutParams.class
					.cast(getLayoutParams());
			int margin = 10;
			margins.topMargin = margin;
			margins.bottomMargin = 0;
			margins.leftMargin = margin;
			margins.rightMargin = 0;
			setLayoutParams(margins);
		}
		
		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
			

		    int desiredWidth =  sidelength;
		    int desiredHeight =  sidelength;

		    int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		    int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		    int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		    int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		    int width;
		    int height;

		    //Measure Width
		    if (widthMode == MeasureSpec.EXACTLY) {
		        //Must be this size
		        width = widthSize;
		    } else if (widthMode == MeasureSpec.AT_MOST) {
		        //Can't be bigger than...
		        width = Math.min(desiredWidth, widthSize);
		    } else {
		        //Be whatever you want
		        width = desiredWidth;
		    }

		    //Measure Height
		    if (heightMode == MeasureSpec.EXACTLY) {
		        //Must be this size
		        height = heightSize;
		    } else if (heightMode == MeasureSpec.AT_MOST) {
		        //Can't be bigger than...
		        height = Math.min(desiredHeight, heightSize);
		    } else {
		        //Be whatever you want
		        height = desiredHeight;
		    }

			
			
		    setMeasuredDimension(width, height);
		
			
		    //setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
		}
		
		@Override
		public boolean onTouchEvent(MotionEvent event){
			
			int eventAction = event.getAction();
			
			int x = (int)event.getX();
			int y = (int)event.getY();
			
			switch(eventAction){
				case MotionEvent.ACTION_DOWN:
					break;
				case MotionEvent.ACTION_UP:
					break;
				case MotionEvent.ACTION_MOVE:
					break;
			}
			
			invalidate();
			
			return true;
		}
		
		private int measureHeight(int measureSpec) {
			/*int size = getPaddingTop() + getPaddingBottom();
			size += labelPaint.getFontSpacing();
			float maxValueTextSpacing = maxValuePaint.getFontSpacing();
			size += Math.max(maxValueTextSpacing, Math.max(barHeight, circleRadius * 2));
			return resolveSizeAndState(size, measureSpec, 0);		*/	
			return resolveSizeAndState(80, measureSpec, 0);
		}
		
		private int measureWidth(int measureSpec) {
		    /*int size = getPaddingLeft() + getPaddingRight();
		    Rect bounds = new Rect();
		    labelPaint.getTextBounds(labelText, 0, labelText.length(), bounds);
		    size += bounds.width();

		    bounds = new Rect();
		    String maxValueText = String.valueOf(maxValue);
		    maxValuePaint.getTextBounds(maxValueText, 0, maxValueText.length(), bounds);
		    size += bounds.width();

		    return resolveSizeAndState(size, measureSpec, 0);*/	
			return resolveSizeAndState(80, measureSpec, 0);
		}
		
		
		
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.planner, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

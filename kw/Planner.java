package com.example.gymappassignment;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
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
        
        
                
        /*
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
            	lltemp.addView(cv);
            	//cvlist.add(cv);
            }
    	}*/
        
        
        

        //CustomView cv2 = new CustomView(this);
        //CustomView cv3 = new CustomView(this);

        CustomViewGroup cvg = new CustomViewGroup(this);
        //cvg.addView(cv2);
        //cvg.addView(cv3);
        
        /*LayoutParams params = new LayoutParams(
        		LayoutParams.WRAP_CONTENT,
        		LayoutParams.WRAP_CONTENT
        		);*/
        //params.setMargins(10,10,0,0);
        CustomView cv;
        for(int i=0;i<28;i++){
        	cv = new CustomView(this);
        	cv.setOnTouchListener(new OnTouchListener(){

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					CustomViewGroup vv = (CustomViewGroup)v.getParent();
					int index = vv.indexOfChild(v);
					CustomView child2 = (CustomView)vv.getChildAt(index);
					child2.changeSelectedChildState();
					vv.setSelectedChild(index);
					
					int prevChild;
					prevChild = vv.getPrevChild();
					if(prevChild != -1){
						CustomView child3 = (CustomView)vv.getChildAt(prevChild);
						child3.changeSelectedChildState();
					}
					//tempChild = selectedChild;
					//selectedChild = child;

					//CustomView child2 = (CustomView) getChildAt(tempChild);
					//CustomView child3 = (CustomView) getChildAt(selectedChild);
					//prevChild = vv.changeSelectedChild(vv.indexOfChild(v));
					//if(prevChild == -1);
					//else CustomView child3 = (CustomView)vv.getChildAt(prevChild);
					
					
					return false;
				}
        		
        	});
        	//cv.setLayoutParams(params);
        	cvg.addView(cv);
        	//cvlist.add(cv);
        }
        
        ll.addView(cvg);

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

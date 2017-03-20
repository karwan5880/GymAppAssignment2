package com.example.gymappassignment;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.MeasureSpec;


//citation: http://stacktips.com/tutorials/android/how-to-create-custom-layout-in-android-by-extending-viewgroup-class

public class CustomViewGroup extends ViewGroup {
	
	int deviceWidth;
	
	int selectedChild;
	int tempChild;
	

	public CustomViewGroup(Context context) {
		super(context);
		init(context);
	}
	
	public void init(Context context){
		final Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point deviceDisplay = new Point();
        display.getSize(deviceDisplay);
        deviceWidth = deviceDisplay.x;
        
        selectedChild = -1;
        tempChild = -1;
        
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		
		final int count = getChildCount();

        int curWidth, curHeight, curLeft, curTop, maxHeight;
		
		
		//get the available size of child view
        final int childLeft = this.getPaddingLeft();
        final int childTop = this.getPaddingTop();
        final int childRight = this.getMeasuredWidth() - this.getPaddingRight();
        final int childBottom = this.getMeasuredHeight() - this.getPaddingBottom();
        final int childWidth = childRight - childLeft;
        final int childHeight = childBottom - childTop;
        
        

        maxHeight = 0;
        curLeft = childLeft;
        curTop = childTop;
        
        //modified code
        int modifiedcurleft;
        int modifiedcurtop;
        //modified code
        
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);

            if (child.getVisibility() == GONE)
                return;

            //Get the maximum size of the child
            child.measure(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.AT_MOST));
            curWidth = child.getMeasuredWidth();
            curHeight = child.getMeasuredHeight();
            //wrap is reach to the end
            //this if is check reach end of screen
            //if yes, layout at second line
            if (curLeft + curWidth + 10 >= childRight) {
                curLeft = childLeft;
                curTop = curTop + maxHeight + 10;
                maxHeight = 0;
            }
            
            //modified code
            modifiedcurleft = curLeft+10;
            modifiedcurtop = curTop+10;
            //modified code
            
            //do the layout
            child.layout(modifiedcurleft, modifiedcurtop, modifiedcurleft + curWidth, modifiedcurtop + curHeight);
            //store the max height
            if (maxHeight < curHeight)
                maxHeight = curHeight;
            curLeft = curLeft + curWidth + 10;
        }
        
        
		
		
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
		int count = getChildCount();
		// Measurement will ultimately be computing these values.
        int maxHeight = 0;
        int maxWidth = 0;
        int childState = 0;
        int mLeftWidth = 0;
        int rowCount = 0;
        

        // Iterate through all children, measuring them and computing our dimensions
        // from their size.
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);

            if (child.getVisibility() == GONE)
                continue;

            // Measure the child.
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            maxWidth = maxWidth + Math.max(maxWidth, child.getMeasuredWidth()) + 10;
            mLeftWidth = mLeftWidth + child.getMeasuredWidth() + 10;

            if ((mLeftWidth / deviceWidth) > rowCount) {
            	maxHeight = maxHeight + child.getMeasuredHeight() + 10;
                rowCount++;
            } else {
                maxHeight = Math.max(maxHeight, child.getMeasuredHeight() + 10);
            }
            childState = combineMeasuredStates(childState, child.getMeasuredState());
        }

        // Check against our minimum height and width
        maxHeight = Math.max(maxHeight, getSuggestedMinimumHeight());
        maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());

        // Report our final dimensions.
        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, childState),
                resolveSizeAndState(maxHeight, heightMeasureSpec, childState << MEASURED_HEIGHT_STATE_SHIFT));
    }
	
	public void setSelectedChild(int child){
		tempChild = selectedChild;
		selectedChild = child;

		//CustomView child2 = (CustomView)getChildAt(tempChild);
		//CustomView child3 = (CustomView)getChildAt(selectedChild);
		//child2.changeSelectedChildState();
		//child3.changeSelectedChildState();
		
		//return tempChild;
	}	
	
	public int getPrevChild(){
		return tempChild;
		

		//CustomView child2 = (CustomView)getChildAt(tempChild);
		//CustomView child3 = (CustomView)getChildAt(selectedChild);
		//child2.changeSelectedChildState();
		//child3.changeSelectedChildState();
		
		//return tempChild;
	}
	
}
	
	





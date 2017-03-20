package com.example.gymappassignment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.Toast;


public class CustomView extends View{

    private Rect rectangle;
    private Rect rectangle2;
    private Paint paint;
    private Paint paint2;
    
    int x ;
    int y ;
    int sidelength ;
    double sidelength2;
    
    boolean isSelected;
    
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
        rectangle2 = new Rect(x+3, y+3, sidelength-2, sidelength-2);

        paint = new Paint();
        paint.setColor(Color.GRAY);
        
        paint2 = new Paint();
        paint2.setColor(Color.MAGENTA);
        
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(5);
        

        isSelected = false;
        

    }
    

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawColor(Color.BLUE);
        canvas.drawRect(rectangle, paint);
        
        if(isSelected)canvas.drawRect(rectangle2, paint2);
        
    	
        
        /*Path p = new Path();
        p.moveTo(10, 10);
        p.lineTo(20, 20);
        p.lineTo(30, 30);
        p.lineTo(30, 40);
        p.lineTo(50, 50);
        
        ShapeDrawable frame = new ShapeDrawable(new PathShape(p,10,10));
        frame.setIntrinsicHeight(10);
        frame.setIntrinsicWidth(10);
        frame.getPaint().setColor(Color.MAGENTA);
        
       // iv.setImageDrawable(frame);
        
        canvas.drawPath(path, paint);*/
        
        
        
        
        
    }

	/*@Override
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
	}*/
	
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
	
	/*@Override
	public boolean onTouchEvent(MotionEvent event){
		

	    //Toast.makeText(this.getContext(), "Touched layout", Toast.LENGTH_SHORT).show();
		
		int eventAction = event.getAction();
		
		int x = (int)event.getX();
		int y = (int)event.getY();
		
		switch(eventAction){
			case MotionEvent.ACTION_DOWN:
				//postInvalidate();
				//this.getParent().changeSelectedChild();
			    //Toast.makeText(this.getContext(), "action dwn ", Toast.LENGTH_SHORT).show();
				break;
			case MotionEvent.ACTION_UP:
			    //Toast.makeText(this.getContext(), "action up ", Toast.LENGTH_SHORT).show();
				break;
			case MotionEvent.ACTION_MOVE:
			    //Toast.makeText(this.getContext(), "action mve ", Toast.LENGTH_SHORT).show();
				break;
		}
		
		//invalidate();
		
		return true;
	}*/
	
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
	
	public void changeSelectedChildState(){
		//Toast.makeText(this.getContext(), "action mve ", Toast.LENGTH_SHORT).show();		
		this.isSelected = !this.isSelected;
		invalidate();
	}
	
	
	
}


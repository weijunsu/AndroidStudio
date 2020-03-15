package lincyu.chapter14_orientation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class ArrowView extends View {

	float alength = (float)100.0;
	float arrowd = (float)20.0;
	float arroww = (float)10.0;

	float startX = (float)160.0;
	float startY = (float)240.0;
	float degree = (float)0.0;
	float stopX, stopY;
	
	Paint paint;
	
	public ArrowView(Context context) {
		super(context);
		paint = new Paint();		
	}

	public ArrowView(Context context, int width, int height) {
		super(context);
		startX = (float)(width/2);
		startY = (float)(height/2);
		degree = (float)0.0;
		paint = new Paint();
		paint.setStrokeWidth((float)5.0);
		int shorter = width;
		if (height < width)
			shorter = height;
		alength = (float)(shorter/3);
	}
	
	protected void setDegree(float degree) {
		this.degree = degree;
	}
		
	@Override
	protected void onDraw(Canvas canvas) {

		float radian = (float)(degree*Math.PI/180.0);
		stopX = startX + (float)(alength*Math.sin(radian));
		stopY = startY - (float)(alength*Math.cos(radian));
		canvas.drawColor(Color.WHITE);
		canvas.drawLine(startX, startY, stopX, stopY, paint);
	
		float v3x, v3y, diffx, diffy, leftax, leftay, rightax, rightay;
	
		v3x = stopX + ((startX-stopX)*arrowd)/alength;
		v3y = stopY + ((startY-stopY)*arrowd)/alength;
			
		diffx = (float)Math.abs((arroww*(stopY-startY))/alength);
		diffy = (float)Math.abs((arroww*(stopX-startX))/alength);
			
		if ((startX-stopX) < 0.0) {
			leftay = v3y - diffy;
			rightay = v3y + diffy;
		} else {
			leftay = v3y + diffy;
			rightay = v3y - diffy;
		}
			
		if ((startY-stopY) < 0.0) {
			leftax = v3x + diffx;
			rightax = v3x - diffx;
		} else {
			leftax = v3x - diffx;
			rightax = v3x + diffx;
		}
		canvas.drawLine(leftax, leftay, stopX, stopY, paint);
		canvas.drawLine(rightax, rightay, stopX, stopY, paint);			
	}
}

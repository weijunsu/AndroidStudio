package lincyu.chapter10_boundservice;

import android.os.Binder;

public class MyBinder extends Binder {
	
	int number;
	
	int getNumber() {
		return number;
	}
	void setNumber(int num) {
		number = num;
	}
}

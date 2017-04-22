
package com.yiwen.guet.utils;

import android.app.ProgressDialog;
import android.content.Context;



public class CommonUtil {
	

	public static int getRandom(int max){
		return (int)(Math.random()*max);
	}

	public static ProgressDialog getProcessDialog(Context context,String tips){
		ProgressDialog dialog = new ProgressDialog(context);
		dialog.setMessage(tips);
		dialog.setCancelable(false);
		return dialog;
	}
	
}

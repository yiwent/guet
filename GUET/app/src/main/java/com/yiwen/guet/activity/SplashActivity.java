package com.yiwen.guet.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import com.yiwen.guet.R;
import com.yiwen.guet.utils.SharedPreferenceUtil;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		((ImageView) findViewById(R.id.bg)).postDelayed(new Runnable() {

			@Override
			public void run() {
				SharedPreferenceUtil util = new SharedPreferenceUtil(
						getApplicationContext(), "accountInfo");
				String isLogin = util.getKeyData("isLogin");
				//String isLogin="false";
				//是否已登录
				if (isLogin.equals("TRUE")) {
					Intent intent = new Intent(SplashActivity.this,
							MainActivity.class);
					startActivity(intent);
					finish();
				} else {
					Intent intent = new Intent(SplashActivity.this,
							LoginActivity.class);
					startActivity(intent);
					finish();
				}
			}
		}, 10);
	}
}


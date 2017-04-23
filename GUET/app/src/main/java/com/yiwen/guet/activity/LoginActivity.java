package com.yiwen.guet.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.yiwen.guet.R;
import com.yiwen.guet.service.LinkService;
import com.yiwen.guet.utils.CommonUtil;
import com.yiwen.guet.utils.HttpUtil;
import com.yiwen.guet.utils.SharedPreferenceUtil;

import org.apache.http.Header;
import org.apache.http.client.params.ClientPNames;
import org.litepal.tablemanager.Connector;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class LoginActivity extends Activity {
    private EditText username, password, secrectCode;// 账号，密码，验证码
    private ImageView code;// 验证码
    private Button    flashCode, login;//刷新验证码，登录
    private PersistentCookieStore cookie;
    private SQLiteDatabase        db;
    private LinkService           linkService;

    private View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.getCode:
                    getCode();
                    break;
                case R.id.login:
                    login();
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initValue();//变量初始化
        initView();//视图初始化
        initEvent();// 事件初始化
        initCookie(this);// cookie初始化
        initDatabase();// 数据库初始化
    }

    private void initValue() {
        linkService = LinkService.getLinkService();
    }

    /**
     * 初始化View
     */
    private void initView() {
        secrectCode = (EditText) findViewById(R.id.secrectCode);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        flashCode = (Button) findViewById(R.id.getCode);
        login = (Button) findViewById(R.id.login);
        code = (ImageView) findViewById(R.id.codeImage);
    }

    /**
     * 初始事件
     */
    private void initEvent() {
        // 一些列点击事件的初始化
        flashCode.setOnClickListener(listener);
        login.setOnClickListener(listener);
    }

    /**
     * 初始化数据库
     */
    private void initDatabase() {
        db = Connector.getDatabase();
        // 在assets目录下的litepal.xml李配置数据库名，版本，映射关系
    }

    /**
     * 初始化Cookie
     */
    private void initCookie(Context context) {
        //必须在请求前初始化
        cookie = new PersistentCookieStore(context);
        HttpUtil.getClient().setCookieStore(cookie);
    }

    /**
     * 跳转到主页
     */
    private void jump2Main() {
        SharedPreferenceUtil util = new SharedPreferenceUtil(getApplicationContext(), "accountInfo");
        util.setKeyData("username", HttpUtil.txtUserName);
        util.setKeyData("password", HttpUtil.TextBox2);
        util.setKeyData("isLogin", "TRUE");
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 登录
     */
    private void login() {
        HttpUtil.txtUserName = username.getText().toString().trim();
        HttpUtil.TextBox2 = password.getText().toString().trim();
        //需要时打开验证码注释
        //HttpUtil.txtSecretCode = secrectCode.getText().toString().trim();
        if (TextUtils.isEmpty(HttpUtil.txtUserName)
                || TextUtils.isEmpty(HttpUtil.TextBox2)) {
            Toast.makeText(getApplicationContext(), "账号或者密码不能为空!",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        final ProgressDialog dialog = CommonUtil.getProcessDialog(LoginActivity.this, "正在登录中！！！");
        dialog.show();
        RequestParams params = HttpUtil.getLoginRequestParams();// 获得请求参数
        HttpUtil.URL_MAIN = HttpUtil.URL_MAIN.replace("XH",
                HttpUtil.txtUserName);// 获得请求地址
        HttpUtil.getClient().setURLEncodingEnabled(true);
        HttpUtil.getClient().getHttpClient().getParams().setParameter(ClientPNames.MAX_REDIRECTS, 3);
        HttpUtil.getClient().getHttpClient().getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
        HttpUtil.post(HttpUtil.URL_LOGIN, params,
                new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                        try {

                            String resultContent = new String(arg2, "gb2312");
                            Log.d("login", "login success:" + resultContent);
                            if (linkService.isLogin(resultContent) != null) {
                                Log.d("zafu", "login success0:");
                                getTite();
                                // String ret = linkService.parseMenu(resultContent);
                                //Log.d("zafu", "login success1:" + ret);
                                Toast.makeText(getApplicationContext(),
                                        "登录成功！！！", Toast.LENGTH_SHORT).show();
                                jump2Main();

                            } else {
                                Toast.makeText(getApplicationContext(), "账号或者密码错误！！！", Toast.LENGTH_SHORT).show();
                            }

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } finally {
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                          Throwable arg3) {
                        Toast.makeText(getApplicationContext(), "登录失败！！！！",
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }

                    @Override
                    public void onRetry(int retryNo) {
                        super.onRetry(retryNo);
                    }
                });
    }

    private void getTite() {
        HttpUtil.get(HttpUtil.URL_TITLE, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String resultContent = new String(responseBody, "gb2312");
                    Log.d("getTite", "onSuccess: " + resultContent);
                    String ret = linkService.parseMenu(resultContent);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                SharedPreferenceUtil util = new SharedPreferenceUtil(
                        getApplicationContext(), "accountInfo");
                util.setKeyData("isLogin", "false");
                Toast.makeText(getApplicationContext(), "请重新登陆",
                        Toast.LENGTH_SHORT).show();
                jump2Spash();
            }
        });


    }

    private void jump2Spash() {
        Intent intent = new Intent(LoginActivity.this, SplashActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 获得验证码
     */
    private void getCode() {
        final ProgressDialog dialog = CommonUtil.getProcessDialog(LoginActivity.this, "正在获取验证码");
        dialog.show();
        HttpUtil.get(HttpUtil.URL_CODE, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {

                InputStream is = new ByteArrayInputStream(arg2);
                Bitmap decodeStream = BitmapFactory.decodeStream(is);
                code.setImageBitmap(decodeStream);
                Toast.makeText(getApplicationContext(), "验证码获取成功！！！", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {

                Toast.makeText(getApplicationContext(), "验证码获取失败！！！",
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
    }
}
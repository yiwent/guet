package com.yiwen.guet.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.yiwen.guet.service.LinkService;

import org.apache.http.Header;

/**
 * @author Administrator
 */
public class HttpUtil {
    private static      MyHttpClient client       = new MyHttpClient(); // 实例话对象
    // Host地址
    public static final String       HOST         = "bkjw.guet.edu.cn";
    // 基础地址
    public static final String       URL_BASE     = "http://bkjw.guet.edu.cn/student/";
    // 验证码地址
    public static final String       URL_CODE     = "http://此处改成对应的地址/CheckCode.aspx";
    // 登陆地址
    public static final String       URL_LOGIN    = "http://bkjw.guet.edu.cn/student/public/login.asp?mCode=000703";
    // 登录成功的首页
    public static       String       URL_MAIN     = "http://bkjw.guet.edu.cn/student/public/menu.asp?menu=mnall.asp";
    // 请求地址
    public static       String       URL_QUERY    = "http://bkjw.guet.edu.cn/student/coursetable.asp";
    //title
    public static       String       URL_TITLE    = "http://bkjw.guet.edu.cn/student/public/mnall.asp";
    public static       String       URL_BASEINFO = "http://bkjw.guet.edu.cn/student/Info.asp";
    /**
     * 请求参数
     */
    //	public static String Button1 = "";
    //	public static String hidPdrs = "";
    //	public static String hidsc = "";
    //	public static String lbLanguage = "";
    //	public static String RadioButtonList1 = "学生";
    //	public static String __VIEWSTATE = "dDwyODE2NTM0OTg7Oz7YiHv1mHkLj1OkgkF90IvNTvBrLQ==";
    //__VIEWSTATE改成页面上对应的值
    public static       String       TextBox2     = null;
    public static       String       login        = "�ǡ�¼'";
    //   public static String txtSecretCode = null;
    public static       String       txtUserName  = null;

    // 静态初始化
    static {
        client.setTimeout(10000); // 设置链接超时，如果不设置，默认为10s
        // 设置请求头
        //client.addHeader("Host", HOST);
        client.addHeader("Referer", URL_LOGIN);
        client.addHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko");
    }

    /**
     * get,用一个完整url获取一个string对象
     *
     * @param urlString
     * @param res
     */
    public static void get(String urlString, AsyncHttpResponseHandler res) {
        client.get(urlString, res);
    }

    /**
     * get,url里面带参数
     *
     * @param urlString
     * @param params
     * @param res
     */
    public static void get(String urlString, RequestParams params,
                           AsyncHttpResponseHandler res) {
        client.get(urlString, params, res);
    }

    /**
     * get,下载数据使用，会返回byte数据
     *
     * @param uString
     * @param bHandler
     */
    public static void get(String uString, BinaryHttpResponseHandler bHandler) {
        client.get(uString, bHandler);
    }

    /**
     * post,不带参数
     *
     * @param urlString
     * @param res
     */
    public static void post(String urlString, AsyncHttpResponseHandler res) {
        client.post(urlString, res);
    }

    /**
     * post,带参数
     *
     * @param urlString
     * @param params
     * @param res
     */
    public static void post(String urlString, RequestParams params,
                            AsyncHttpResponseHandler res) {
        client.post(urlString, params, res);
    }

    /**
     * post,返回二进制数据时使用，会返回byte数据
     *
     * @param uString
     * @param bHandler
     */
    public static void post(String uString, BinaryHttpResponseHandler bHandler) {
        client.post(uString, bHandler);
    }

    /**
     * 返回请求客户端
     *
     * @return
     */
    public static MyHttpClient getClient() {
        return client;
    }

    /**
     * 获得登录时所需的请求参数
     *
     * @return
     */
    public static RequestParams getLoginRequestParams() {
        // 设置请求参数
        RequestParams params = new RequestParams();
        //		params.add("__VIEWSTATE", __VIEWSTATE);
        //		params.add("Button1", Button1);
        //		params.add("hidPdrs", hidPdrs);
        //		params.add("hidsc", hidsc);
        //		params.add("lbLanguage", lbLanguage);
        //		params.add("RadioButtonList1", RadioButtonList1);
        params.add("passwd", TextBox2);
        //  params.add("txtSecretCode", txtSecretCode);
        params.add("username", txtUserName);
        params.add("login", login);
        return params;
    }

    public interface QueryCallback {
        String handleResult(byte[] result);
    }

    public static void getQuery(final Context context, LinkService linkService,
                                final String urlName, final QueryCallback callback) {
        final ProgressDialog dialog = CommonUtil.getProcessDialog(context,
                "正在获取" + urlName);
        dialog.show();
        String link = linkService.getLinkByName(urlName);
        if (link != null) {
            HttpUtil.URL_QUERY = HttpUtil.URL_QUERY.replace("QUERY", link);
        } else {
            Toast.makeText(context, "链接出现错误", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpUtil.getClient().addHeader("Referer", HttpUtil.URL_MAIN);
        HttpUtil.getClient().setURLEncodingEnabled(true);
        HttpUtil.get(HttpUtil.URL_QUERY, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                if (callback != null) {
                    callback.handleResult(arg2);
                }
                Toast.makeText(context, urlName + "获取成功！！！", Toast.LENGTH_LONG)
                        .show();
                dialog.dismiss();
            }

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {
                dialog.dismiss();
                Toast.makeText(context, urlName + "获取失败！！！", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}
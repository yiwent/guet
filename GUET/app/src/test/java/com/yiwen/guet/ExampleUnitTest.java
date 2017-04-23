package com.yiwen.guet;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.yiwen.guet.service.LinkService;
import com.yiwen.guet.utils.HttpUtil;

import org.apache.http.Header;
import org.junit.Test;

import static com.yiwen.guet.utils.HttpUtil.login;
import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void TestLikeName (){
        String res="";
        LinkService linkservice=LinkService.getLinkService();
        res =LinkService.getLinkByName("个人信息");
        System.out.println(res);

    }
    @Test
    public void TestAS(){
         String pass="";
        for (int i=10;i<30;i++){
            pass="16008"+i;

        RequestParams params = new RequestParams();
        //		params.add("__VIEWSTATE", __VIEWSTATE);
        //		params.add("Button1", Button1);
        //		params.add("hidPdrs", hidPdrs);
        //		params.add("hidsc", hidsc);
        //		params.add("lbLanguage", lbLanguage);
        //		params.add("RadioButtonList1", RadioButtonList1);
        params.add("passwd", pass);
        //  params.add("txtSecretCode", txtSecretCode);
        params.add("username", pass);
        params.add("login", login);
        HttpUtil.getClient().setURLEncodingEnabled(true);
//        params.setParameter(ClientPNames.MAX_REDIRECTS, 3);
//        params.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
        HttpUtil.post(HttpUtil.URL_LOGIN, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                int i=0;
                System.out.println(i++);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    }

}
package fresh.com.informanag.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.sharesdk.wechat.utils.WechatHandlerActivity;
import fresh.com.informanag.App.MyApp;
import fresh.com.informanag.R;
import fresh.com.informanag.apimanager.OkHttpUtils;
import fresh.com.informanag.bean.Bean_wei_Login;
import fresh.com.informanag.ui.act.actMe.ActLogin;
import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import static fresh.com.informanag.App.MyApp.gson;
import static fresh.com.informanag.App.MyApp.mOkHttpClient;
import static fresh.com.informanag.App.MyApp.promptDialog;


public class WXEntryActivity extends WechatHandlerActivity implements IWXAPIEventHandler {



    private Bean_wei_Login bean_wei_login;
    private String message;
    private String code;
    private Bean_wei_Login.DataBean data;
    private String userNo;
    private Object username;
    private String headImg;
    private Object nickname;
    private Object phone;
    private Object birthday;
    private String wxOpenId;
    private Object type;
    private Object status;
    private Object loginStatus;
    private Object lastLoginTime;
    private String accessToken;












    private String openid = null;
    private String nickName = null;
    private String sex = null;
    private String city = null;
    private String province = null;
    private String country = null;
    private String headimgurl = null;
    private String unionid = null;



    /**
     * 处理微信发出的向第三方应用请求app message
     * <p>
     * 在微信客户端中的聊天页面有“添加工具”，可以将本应用的图标添加到其中
     * 此后点击图标，下面的代码会被执行。Demo仅仅只是打开自己而已，但你可
     * 做点其他的事情，包括根本不打开任何页面
     */
    public void onGetMessageFromWXReq(WXMediaMessage msg) {
        if (msg != null) {
            Intent iLaunchMyself = getPackageManager().getLaunchIntentForPackage(getPackageName());
            startActivity(iLaunchMyself);
        }
    }

    /**
     * 处理微信向第三方应用发起的消息
     * <p>
     * 此处用来接收从微信发送过来的消息，比方说本demo在wechatpage里面分享
     * 应用时可以不分享应用文件，而分享一段应用的自定义信息。接受方的微信
     * 客户端会通过这个方法，将这个信息发送回接收方手机上的本demo中，当作
     * 回调。
     * <p>
     * 本Demo只是将信息展示出来，但你可做点其他的事情，而不仅仅只是Toast
     */
    public void onShowMessageFromWXReq(WXMediaMessage msg) {
        if (msg != null && msg.mediaObject != null
                && (msg.mediaObject instanceof WXAppExtendObject)) {
            WXAppExtendObject obj = (WXAppExtendObject) msg.mediaObject;
            Toast.makeText(this, obj.extInfo, Toast.LENGTH_SHORT).show();
        }
    }

    private IWXAPI iwxapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_wxpay_entry);

//        api = WXAPIFactory.createWXAPI(this, Constant.PAY_WX_APP_ID);
//        api.handleIntent(getIntent(), this);


//        getSupportActionBar().hide();
//        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //接收到分享以及登录的intent传递handleIntent方法，处理结果  Constant.APP_ID
        iwxapi = WXAPIFactory.createWXAPI(this,"wx3a24619463b4acce" , false);
        iwxapi.handleIntent(getIntent(), this);
    }




    @Override
    public void onReq(BaseReq baseReq) {
        Log.i("sddsssds", "errCode:---->" + baseReq.getType());
    }

    //请求回调结果处理
    @Override
    public void onResp(BaseResp baseResp) {

        Log.i("baseResp_errCode",baseResp.errCode+"----"+BaseResp.ErrCode.ERR_OK);

        //登录回调
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                String code = ((SendAuth.Resp) baseResp).code;

                if (promptDialog == null) {
                    promptDialog = new PromptDialog(this);
                    promptDialog.showLoading("加载中");
                }

//                Toast.makeText(this,"授权登陆的回调!!!"+"----"+code,Toast.LENGTH_SHORT).show();

                //获取用户信息
                getAccessToken(code);
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
                finish();
                break;
            default:
                finish();
                break;
        }
    }





    private void getAccessToken(String code) {
        //获取授权
        StringBuffer loginUrl = new StringBuffer();
        loginUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token")
                .append("?appid=")
                .append("wx3a24619463b4acce")
//    Constant.APP_ID
                .append("&secret=")
                .append("701c9cc93325e07c468d82b88d09785b")
//        Constant.SECRET
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");
        OkHttpUtils.ResultCallback<String> resultCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {


                Log.i("onSuccessresponse",response.toString());

                String access = null;
                String openId = null;
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    access = jsonObject.getString("access_token");
                    openId = jsonObject.getString("openid");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //获取个人信息
                String getUserInfo = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access + "&openid=" + openId;
                OkHttpUtils.ResultCallback<String> reCallback = new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String responses) {
//                        {
//                            "openid": "oGvwp6H_q0SPyaUWZrrXpIuLLz-4",
//                                "nickname": "ymj",
//                                "sex": 1,
//                                "language": "zh_CN",
//                                "city": "",
//                                "province": "Chongqing",
//                                "country": "CN",
//                                "headimgurl": "http:\/\/thirdwx.qlogo.cn\/mmopen\/vi_32\/1U4VwrKCTd7dloPB8B4uib2ia6eGVrIyYiajeNwFK6hdoicuYhKhqlXGPKuLBMMZHYrudXLVfQuQdsKgbVBAamUEfA\/132",
//                                "privilege": [
//  ],
//                            "unionid": "oaesT5vOMFF38-lesw-HoBNmf3k0"
//                        }
                        try {
                            JSONObject jsonObject = new JSONObject(responses);
                            openid = jsonObject.getString("openid");
                            nickName = jsonObject.getString("nickname");
                            sex = jsonObject.getString("sex");
                            city = jsonObject.getString("city");
                            province = jsonObject.getString("province");
                            country = jsonObject.getString("country");
                            headimgurl = jsonObject.getString("headimgurl");
                            unionid = jsonObject.getString("unionid");
//                            loadNetData(1, openid, nickName, sex, province,
//                                    city, country, headimgurl, unionid);
                            Log.i("nickName_nickName",
                                    responses+"------"+
                                    nickName+"--"+
                                            sex +"---"+
                                    city +"---"+
                                    province +"---"+
                                    country +"---"+
                                    headimgurl +"---"+
                                    unionid +"---"
                            );
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
//                                    Toast.makeText(WXEntryActivity.this, "登录成功!", Toast.LENGTH_SHORT).show();
                                    post_modify(openid,headimgurl,nickName);

                                }
                            });


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(WXEntryActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                };
                OkHttpUtils.get(getUserInfo, reCallback);
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(WXEntryActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                finish();
            }
        };
        OkHttpUtils.get(loginUrl.toString(), resultCallback);
    }

    @Override
    protected void onPause() {
        overridePendingTransition(0, 0);
        super.onPause();
    }

    private void post_modify(final String openId, final String headimgurl,String nickName) {
//        Toast.makeText(this,"请求了",Toast.LENGTH_SHORT).show();
        Map<String, String> paramsheads = new HashMap<>();
        paramsheads.clear();
        paramsheads.put("nickname", nickName);
        paramsheads.put("headImg", headimgurl);
        paramsheads.put("wxOpenId", openId);
        paramsheads.put("type", "WECHAT");
        Request request = new Request.Builder().
                url(MyApp.PUBLIC_URL_shop + "user/register")
                .post(RequestBody.create(MediaType.parse("application/json"), gson.toJson(paramsheads)))
                .build();
        okhttp3.Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                final String resp = response.body().string();
                        Log.i("resp_Runnable",resp);
//                        private Bean_wei_Login bean_wei_login;
//                        private String message;
//                        private String code;
//                        private Bean_wei_Login.DataBean data;
//                        private String userNo;
//                        private Object username;
//                        private String headImg;
//                        private Object nickname;
//                        private Object phone;
//                        private Object birthday;
//                        private String wxOpenId;
//                        private Object type;
//                        private Object status;
//                        private Object loginStatus;
//                        private Object lastLoginTime;
//                        private String accessToken;


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (promptDialog != null) {
                            promptDialog.dismiss();
                            promptDialog=null;
                        }
                    }
                });



                        try {
                            bean_wei_login = gson.fromJson(resp,Bean_wei_Login.class);
                            data = bean_wei_login.getData();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(WXEntryActivity.this,bean_wei_login.getMessage(),Toast.LENGTH_SHORT).show();
                                    MyApp.user = data.getUserNo();
                                    MyApp.access_token = data.getAccessToken();
//                                    SharedPreferencesUtils.setParam(MyApp.context, "user", data.getUserNo());
//                                    SharedPreferencesUtils.setParam(MyApp.context, "access_token", data.getAccessToken());
                                    MyApp.putSharedPreference(WXEntryActivity.this, "user",  data.getUserNo() + "");
                                    MyApp.putSharedPreference(WXEntryActivity.this, "access_token", data.getAccessToken() + "");

                                    ActLogin.actLogin.finish();
                                }
                            });
                        }catch (Exception e){

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(WXEntryActivity.this,bean_wei_login.getMessage(),Toast.LENGTH_SHORT).show();

//                                    ActLogin.actLogin.finish();

                                }
                            });

                        }
                    }
                });

    }
}

package fresh.com.informanag.ui.act.actMe;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigmercu.cBox.CheckBox;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.OnClick;
import fresh.com.informanag.App.MyApp;
import fresh.com.informanag.R;
import fresh.com.informanag.base.BaseActivity;

import fresh.com.informanag.bean.Bean_infors;
import fresh.com.informanag.bean.BeanverCode;
import fresh.com.informanag.util.HttpUtil;
import fresh.com.informanag.util.Tool;

//登录
public class ActLogin extends BaseActivity {


    public static ActLogin actLogin;
//    是否选中
    private int check_login = 0;

    @BindView(R.id.checkbox)
    CheckBox checkbox;

    private IWXAPI iwxapi;

    @BindView(R.id.img_login)
    ImageView img_login;
    private BeanverCode beanverCode;
    private String message;
    private String code;
    private BeanverCode.DataBean data;
    private String code_message = "";
    private String phone;
    //    用户信息
    private Bean_infors bean_infor;
    private String message_infor;
    private String code_infor;
    private Bean_infors.DataBean data_infor;
    //用户 number
    private String userNo;

    @BindView(R.id.text_login)
    TextView text_login;

    @BindView(R.id.toolbar_all)
    Toolbar toolbar_all;

    @BindView(R.id.text_Send_out)
    TextView text_Send_out;

    @BindView(R.id.edit_phone)
    EditText edit_phone;

    private TimeCount time;

    //    验证码
    @BindView(R.id.edit_content)
    EditText edit_content;

    @BindView(R.id.lin_clear)
    LinearLayout lin_clear;


    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }


    @Override
    protected void findViews() {


        TextView text_center = (TextView) toolbar_all.findViewById(R.id.toolbar_center);
        text_center.setText("登录/注册");

    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_act_login;
    }


    @Override
    public void initData() {

        actLogin = this;

        time = new TimeCount(60000, 1000);

        checkbox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onChange(boolean checked) {
                if (checked){
                    check_login = 1;
                }else {
                    check_login = 0;
                }
            }
        });
    }

    @OnClick({R.id.text_login, R.id.text_Send_out, R.id.lin_clear, R.id.img_login})
    public void onClick(View view) {

        switch (view.getId()) {
//                登录
            case R.id.text_login:

                if (check_login == 1){
                    if (!edit_phone.getText().toString().trim().equals("") && edit_content.getText().toString().trim().length() > 0) {
//                    if (code_message.equals(edit_content.getText().toString().trim())) {


                        text_login.setClickable(false);

                        showLoadingDialog();
                        HttpUtil.addMapparams();
                        HttpUtil.params.put("type", "PHONE_VERIFY");
                        HttpUtil.params.put("phone", edit_phone.getText().toString().trim());
                        HttpUtil.params.put("verifyCode", edit_content.getText().toString().trim());
                        HttpUtil.post_Life("user/register", HttpUtil.params);
                        getdata("login_login");
//                    } else {
//                        Toast.makeText(this, "请输入正确的验证码!", Toast.LENGTH_SHORT).show();
//                    }
                    }
                }else {
//                    new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
//                            .setTitleText("万仓生活!")
//                            .setContentText("请选中协议！")
//                            .setCustomImage(R.drawable.tishi)
//                            .show();
                    Tool.setTips(this,"请选中协议！");
                }

                break;
//                发送验证码
            case R.id.text_Send_out:

                if (!edit_phone.getText().toString().trim().equals("") && edit_phone.getText().toString().trim().length() > 0) {
                    if (Tool.isMobileNO(edit_phone.getText().toString().trim())) {

                        text_Send_out.setClickable(false);

                        HttpUtil.addMapparams();
                        HttpUtil.params.put("phone", edit_phone.getText().toString().trim());
                        HttpUtil.params.put("type", "REGISTER_OR_LOGIN");
                        HttpUtil.post_Life("code/verifyCode", HttpUtil.params);
                        getdata("fasong");
                        time.start();
                    } else {
                        Toast.makeText(this, "请输入正确的手机号！", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.lin_clear:

                edit_phone.setHint("请输入电话号码");

                break;


            case R.id.img_login:

                iwxapi = WXAPIFactory.createWXAPI(this, "wx3a24619463b4acce", true);
                iwxapi.registerApp("wx3a24619463b4acce");
                if (!iwxapi.isWXAppInstalled()) {
//                    Intent intent = new Intent(this, MyDialogActivity.class);
//                    startActivity(intent);

                    Toast.makeText(ActLogin.this, "您还未安装微信！", Toast.LENGTH_SHORT).show();

                } else {



                    final SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state = "wechat_sdk_demo_test";
                    iwxapi.sendReq(req);
                }

                break;
        }
    }


    public void StringResulit(String key, String value) {
//发送验证码
        if (key.equals("fasong")){

            text_Send_out.setClickable(true);

            beanverCode = MyApp.gson.fromJson(value, BeanverCode.class);
            code = beanverCode.getCode();
            if (code.equals("200")) {
                Log.i("value", value);
//                private String code_message;
//                private String phone;
                code_message = beanverCode.getData().getCode();
                phone = beanverCode.getData().getPhone();
            }
        }


//        登录
        if (key.equals("login_login")) {
            dismissLoadingDialog();
//            Log.i(key, value);

            text_login.setClickable(true);

            Toast.makeText(this, "登陆成功!", Toast.LENGTH_SHORT).show();


//            private Bean_infor bean_infor;
//            private String message_infor;
//            private String code_infor;
//            private Bean_infor.DataBean data_infor;
//            用户 number
//            private String userNo;

            bean_infor = MyApp.gson.fromJson(value, Bean_infors.class);

            data_infor = bean_infor.getData();

//                  用户鉴权
//            public static String access_token = "";
//            //    签名
//            public static String sign = "";
//            //    请求时间
//            public static String timestamp = "";
//            //    随机数
//            public static String nonce = "";
//            //    用户号
//            public static String user = "";

            MyApp.user = data_infor.getUserNo();
            MyApp.access_token = data_infor.getAccessToken();

            MyApp.putSharedPreference(this, "user", data_infor.getUserNo() + "");
            MyApp.putSharedPreference(this, "access_token", data_infor.getAccessToken() + "");

            finish();
        }
    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
//            text_Send_out.setBackgroundColor(Color.parseColor("#B6B6D8"));
            text_Send_out.setClickable(false);
            text_Send_out.setText("(" + millisUntilFinished / 1000 + ") 秒");
        }

        @Override
        public void onFinish() {
            text_Send_out.setText("重新获取");
            text_Send_out.setClickable(true);
//            text_Send_out.setBackgroundColor(Color.parseColor("#4EB84A"));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (time != null) {
            time.cancel();
        }
    }
}

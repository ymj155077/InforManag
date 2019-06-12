package fresh.com.informanag.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import fresh.com.informanag.R;



public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    public static int zhifu_shangjia = 0;
    public static String shangjiaid = "";
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

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpay_entry_pay);
        api = WXAPIFactory.createWXAPI(this, "wx3a24619463b4acce");
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Log.i("sddsssds", "errCode:---->" + baseReq.getType());
    }

    @Override
    public void onResp(BaseResp resp) {
        int errCode = resp.errCode;
        Log.i("sddsds", "errCode:---->" + errCode);
        String result = "";
        switch (errCode) {
            case 0:
                result = "支付成功";
//                tvTitle.setText("支付成功");
//                Toast.makeText(WXPayEntryActivity.this,"支付成功",Toast.LENGTH_SHORT).show();
//                if (zhifu_shangjia==1){
//                    Intent intent = new Intent(WXPayEntryActivity.this, ActPaySucessful.class);
////                        type_choose = ;
//                    intent.putExtra("type_choose","wx_xx");
//
//                    intent.putExtra("shangjiaid",shangjiaid);
//
//                    intent.putExtra("id", Appid);
//
//                    Log.i("response_sanjia",shangjiaid);
//
//
//                    startActivity(intent);
//                }
//                finish();

//                是订单 的 支付  的 话 就
//                if (    ;)
//                if (MyApp.type_order == 1){
//                    MyApp.type_order = 0;
//                    PendDetailsAct.pendDetailsAct.finish();
//                }else {
//
//                    ActSuperShop.actSuperShop.finish();
//
//                    ConOrderAct.conOrderAct.finish();
//
//                    Intent intent = new Intent(this,MyOrderAct.class);
//
//                    intent.putExtra("order",0);
//
//                    startActivity(intent);

//                }
//                跳转到我的订单  的 界面
//                支付成功 以后 跳转到我的订单 的界面；
//                startActivity(new Intent(this, MyOrderAct.class));
                break;
            case -1:
                Toast.makeText(WXPayEntryActivity.this, "支付失败,请重试", Toast.LENGTH_SHORT).show();
                //可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
//                Log.i(TAG, "onResp:支付失败");
//                result = "支付失败,请重试";

//                ActSuperShop.actSuperShop.finish();
//
//                ConOrderAct.conOrderAct.finish();
//
//                Intent intent_ = new Intent(this,MyOrderAct.class);
//
//                intent_.putExtra("order",1);
//
//                startActivity(intent_);
//
//                finish();
                break;
            case -2:
                //用户取消支付
//                Log.i(TAG, "onResp:用户取消支付");
//                result = "您取消了支付";
                Toast.makeText(WXPayEntryActivity.this, "您取消了支付", Toast.LENGTH_SHORT).show();


//                ActSuperShop.actSuperShop.finish();
//
//                ConOrderAct.conOrderAct.finish();
//
//                Intent intent_we = new Intent(this,MyOrderAct.class);
//
//                intent_we.putExtra("order",1);
//
//                startActivity(intent_we);
//
//                finish();
                break;
        }
//        ToastUtils.showToast(this, result);
    }
}

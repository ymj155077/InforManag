package fresh.com.informanag.ui.act.actMe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;
import fresh.com.informanag.App.MyApp;
import fresh.com.informanag.R;
import fresh.com.informanag.base.BaseActivity;
import fresh.com.informanag.bean.Bean_shop;
import fresh.com.informanag.ui.act.actbusiness.ActAlready;
import fresh.com.informanag.ui.act.actbusiness.ActBillManagement;
import fresh.com.informanag.ui.act.actbusiness.ActEvalManage;
import fresh.com.informanag.ui.act.actbusiness.ActQuXiao;
import fresh.com.informanag.ui.act.actbusiness.ActShouHuo;
import fresh.com.informanag.ui.act.actbusiness.ActToShipped;
import fresh.com.informanag.ui.act.actbusiness.ActWaitOrder;
import fresh.com.informanag.util.HttpUtil;

//商家信息管理
public class ActMerManage extends BaseActivity {


    private Bean_shop bean_shop;
    private String message_shop;
    private String code_shop;
    private Bean_shop.DataBean data_shop;


    @BindView(R.id.text_address)
    TextView text_address;

    @BindView(R.id.text_shopname)
    TextView text_shopname;


    @BindView(R.id.toolbar_all)
    Toolbar toolbar_all;


    @BindView(R.id.img_shop_back)
    ImageView img_shop_back;

    @BindView(R.id.head_round_me)
    ImageView head_round_me;


    //    待接单
    @BindView(R.id.lin_wait)
    LinearLayout lin_wait;

    //    待发货
    @BindView(R.id.lin_shipped)
    LinearLayout lin_shipped;


    //    已发货
    @BindView(R.id.lin_fa)
    LinearLayout lin_fa;


    //    已收货
    @BindView(R.id.lin_shouhuo)
    LinearLayout lin_shouhuo;

    @BindView(R.id.lin_quxiao)
    LinearLayout lin_quxiao;

    //    收入管理
    @BindView(R.id.lin_Re_manager)
    LinearLayout lin_Re_manager;


    //评价管理
    @BindView(R.id.lin_evaluate)
    LinearLayout lin_evaluate;


    //    门店管理
    @BindView(R.id.lin_StoreManage)
    LinearLayout lin_StoreManage;


    private String merchantNo;


    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_act_mer_manage;
    }

    @Override
    protected void findViews() {

        TextView text_center = (TextView) toolbar_all.findViewById(R.id.toolbar_center);
        text_center.setText("我的店铺");
    }

    @Override
    public void initData() {


//        Glide.with(this).load(MyApp.url).into(img_shop_back);

//        Glide.with(this).load(MyApp.url).into(head_round_me);

    }


    @OnClick({R.id.lin_wait, R.id.lin_shipped, R.id.lin_fa, R.id.lin_shouhuo, R.id.lin_quxiao, R.id.lin_Re_manager, R.id.lin_evaluate, R.id.lin_StoreManage

    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_wait:

                startActivity(ActWaitOrder.class);

                break;
//               待发货
            case R.id.lin_shipped:

                startActivity(ActToShipped.class);

                break;
//               已发货
            case R.id.lin_fa:

                startActivity(ActAlready.class);

                break;
//               已收货

            case R.id.lin_shouhuo:

                startActivity(ActShouHuo.class);

                break;

//           已取消
            case R.id.lin_quxiao:

                startActivity(ActQuXiao.class);

                break;

//               收入管理

            case R.id.lin_Re_manager:

                startActivity(ActBillManagement.class);

                break;

//               评价管理
            case R.id.lin_evaluate:


                Intent intent = new Intent(this, ActEvalManage.class);
                intent.putExtra("merchantNo", merchantNo);
                startActivity(intent);

                break;


            case R.id.lin_StoreManage:

                Intent intent1 = new Intent(this,ActMertion.class);

                intent1.putExtra("merchantNo",merchantNo);

                startActivity(intent1);

                break;
        }
    }


    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
//        商户号
        merchantNo = intent.getStringExtra("merchantNo");

        Log.i("merchantNo", merchantNo);

        HttpUtil.addMapparams();
        HttpUtil.params.put("merchantNo", merchantNo);

        HttpUtil.post_Life("merchant/detail", HttpUtil.params);
        getdata("merchant/detail");
    }

    public void StringResulit(String key, String value) {

        if (key.equals("merchant/detail")) {


//            private Bean_shop bean_shop;
//            private String message_shop;
//            private String code_shop;
//            private Bean_shop.DataBean data_shop;
            bean_shop = MyApp.gson.fromJson(value, Bean_shop.class);
            data_shop = bean_shop.getData();
            /**
             * userNo : 1j9shm63u0S041bqq7q6
             * merchantNo : 1ja7rmz39wS041bqq7q6
             * storeName : YMJ
             * address : 南环路悦荟万科广场二楼
             * detailAddress : 阿斯顿撒
             * longitude : 116.238429
             * latitude : 40.212623
             * merchantName : 快快快
             * phone : 18725621750
             * tel : 428322681
             * storeImg : imgs.ejinlegou.com/goods/1559615991water.png
             * storeImgIn : ["imgs.ejinlegou.com/goods/1559615996calendar_icon.png","imgs.ejinlegou.com/goods/1559615996QQ图片20171027183216.png"]
             * applyTime : 1559662752000
             * state : AUDIT_PASSED
             */
            text_address.setText(data_shop.getAddress());
            text_shopname.setText(data_shop.getStoreName());
            Glide.with(this).load(data_shop.getStoreImg()).into(head_round_me);
        }
    }
}

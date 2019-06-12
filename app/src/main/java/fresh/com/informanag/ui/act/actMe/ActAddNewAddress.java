package fresh.com.informanag.ui.act.actMe;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import fresh.com.informanag.App.MyApp;
import fresh.com.informanag.R;
import fresh.com.informanag.base.BaseActivity;
import fresh.com.informanag.bean.Bean_address_list;
import fresh.com.informanag.bean.Bean_huo;
import fresh.com.informanag.ui.act.acthome.ActGaoMap;
import fresh.com.informanag.util.HttpUtil;

public class ActAddNewAddress extends BaseActivity {



    private Bean_huo bean_huo;
    private String message_huo;
    private String code_huo;
    private Bean_huo.DataBean data_huo;

    /**
     * userNo : 1j9shm63u0S041bqq7q6
     * addressNo : 1j9sna0w1wS041bqq7q6
     * consignee : 吞吞吐吐吞
     * tag : null
     * detail : 纯纯粹粹
     * phone : 18725621750
     * state : ENABLED
     * weight : null
     * nation : null
     * province :
     * city : 北京市东城区
     * district :
     * street : 体育馆路甲2号
     * streetNumber : null
     * longitude : 116.426403
     * latitude : 39.882828
     */

    private String userNo;
    private String addressNo;
    private String consignee;
    private Object tag;
    private String detail;
    private String phone;
    private String state;
    private Object weight;
    private Object nation;
    private String province;
    private String city;
//    private String district;
    private String street;
    private Object streetNumber;
    private String longitude;
    private String latitude;













    private String AddressNo = "";


    private String Longitude = "", Latitude = "", Address = "", district = "";


    private int REQUESTCODE = 1;


    @BindView(R.id.toolbar_all)
    Toolbar toolbar_all;


    @BindView(R.id.edit_user)
    EditText edit_user;

    @BindView(R.id.edit_phone)
    EditText edit_phone;

    @BindView(R.id.text_choice)
    TextView text_choice;

    @BindView(R.id.edit_menpai)
    EditText edit_menpai;

    TextView text_center;


    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_act_add_new_address;
    }

    @Override
    protected void findViews() {

        text_center = toolbar_all.findViewById(R.id.toolbar_center);


        TextView toolbar_right = toolbar_all.findViewById(R.id.toolbar_right);
        toolbar_right.setText("保存");
        toolbar_right.setTextColor(Color.parseColor("#FF6406"));
        toolbar_right.setVisibility(View.VISIBLE);


    }

    @Override
    public void initData() {


        Intent intent = getIntent();

        String  AddressNoa=  intent.getStringExtra("AddressNo");


        try {
            if (AddressNoa.length() > 0) {
                text_center.setText("编辑收货地址");
            } else {
                text_center.setText("添加收货地址");
            }
        }catch (Exception e){
            text_center.setText("添加收货地址");
        }





    }

    @OnClick({R.id.toolbar_right, R.id.text_choice})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.toolbar_right:
                //         intent.putExtra("Longitude", Longitude); //将计算的值回传回去
//
//                                intent.putExtra("Latitude", Latitude);
//
//                                intent.putExtra("Address", Address);
//
//                                intent.putExtra("district", district);

//                保存
Log.i("sadsadsad",MyApp.user);

                if (AddressNo==null){
                    HttpUtil.addMapparams();
                    HttpUtil.params.put("consignee", edit_user.getText().toString().trim());
                    HttpUtil.params.put("phone", edit_phone.getText().toString().trim());
                    HttpUtil.params.put("userNo", MyApp.user);
                    HttpUtil.params.put("city", district);
                    HttpUtil.params.put("detail", edit_menpai.getText().toString().trim());
                    HttpUtil.params.put("district", "");
                    HttpUtil.params.put("latitude", Latitude);
                    HttpUtil.params.put("longitude", Longitude);
                    HttpUtil.params.put("province", "");
                    HttpUtil.params.put("street", Address);
                    HttpUtil.params.put("street_number", "");
                    HttpUtil.params.put("defaultAddress", "false");
                    HttpUtil.post_Life("address/shippingAddressCreate", HttpUtil.params);
                    getdata("address/shippingAddressCreate");
                }else {
                    //                修改
                    HttpUtil.addMapparams();

                    HttpUtil.params.put("addressNo", addressNo);

                    HttpUtil.params.put("consignee", edit_user.getText().toString().trim());
                    HttpUtil.params.put("phone", edit_phone.getText().toString().trim());
                    HttpUtil.params.put("userNo", MyApp.user);
                    HttpUtil.params.put("city", district);
                    HttpUtil.params.put("detail", edit_menpai.getText().toString().trim());
                    HttpUtil.params.put("district", "");
                    HttpUtil.params.put("latitude", Latitude);
                    HttpUtil.params.put("longitude", Longitude);
                    HttpUtil.params.put("province", "");
                    HttpUtil.params.put("street", Address);
                    HttpUtil.params.put("street_number", "");


                    HttpUtil.params.put("defaultAddress", "false");


                    HttpUtil.post_Life("address/shippingAddressModify", HttpUtil.params);
                    getdata("address/shippingAddressModify");

                }

                break;
            case R.id.text_choice:
                Intent intent = new Intent(this,
                        ActGaoMap.class);
                intent.putExtra("mercertifiact", 2);
                // 这种启动方式：startActivity(intent);并不能返回结果
                startActivityForResult(intent, REQUESTCODE); //REQUESTCODE--->1
                break;
        }

    }


    public void StringResulit(String key, String value) {
//添加收货地址
        if (key.equals("address/shippingAddressCreate")) {
            finish();
        }


//        编辑 收货地址

        if (key.equals("address/shippingAddressModify")){

            finish();

        }






//        获取 收货地址
        if (key.equals("address/shippingAddress")) {

//            private Bean_huo bean_huo;
//            private String message_huo;
//            private String code_huo;
//            private Bean_huo.DataBean data_huo;
            bean_huo = MyApp.gson.fromJson(value,Bean_huo.class);

            data_huo = bean_huo.getData();

            edit_user.setText(data_huo.getConsignee());

            edit_phone.setText(data_huo.getPhone());

            text_choice.setText(data_huo.getCity()+data_huo.getStreet());

            edit_menpai.setText(data_huo.getDetail());

//          private String Longitude = "", Latitude = "", Address = "", district = "";

            Longitude = data_huo.getLongitude();

            Latitude = data_huo.getLatitude();

            Address = data_huo.getCity()+data_huo.getStreet();

            district = data_huo.getDistrict();

        }
    }


    // 为了获取结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RESULT_OK，判断另外一个activity已经结束数据输入功能，Standard activity result:
        // operation succeeded. 默认值是-1
        if (resultCode == 2) {
            if (requestCode == REQUESTCODE) {
//         intent.putExtra("Longitude", Longitude); //将计算的值回传回去
//                                intent.putExtra("Latitude", Latitude);
//                                intent.putExtra("Address", Address);
//                                intent.putExtra("district", district);
                Longitude = data.getStringExtra("Longitude");
                Latitude = data.getStringExtra("Latitude");
                Address = data.getStringExtra("Address");
                district = data.getStringExtra("district");
                text_choice.setText(Address);
            }
        }
    }

    //    address/shippingAddress
//    /**
//     *
//     * 重写此方法，加上setIntent(intent);否则在onResume里面得不到intent
//     * @param intent intent
//     */
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        setIntent(intent);
//    }
    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();

        AddressNo = intent.getStringExtra("AddressNo");

        try {
            if (AddressNo.equals("")) {

            } else {
                HttpUtil.addMapparams();

                HttpUtil.params.put("addressNo", AddressNo);

                HttpUtil.post_Life("address/shippingAddress", HttpUtil.params);

                getdata("address/shippingAddress");
            }
        }catch (Exception e){

        }
    }
}

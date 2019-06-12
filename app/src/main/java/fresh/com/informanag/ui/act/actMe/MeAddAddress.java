package fresh.com.informanag.ui.act.actMe;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.sak.ultilviewlib.UltimateRefreshView;
import com.sak.ultilviewlib.interfaces.OnFooterRefreshListener;
import com.sak.ultilviewlib.interfaces.OnHeaderRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import fresh.com.informanag.App.MyApp;
import fresh.com.informanag.R;
import fresh.com.informanag.adpter.JDAppFooterAdapter;
import fresh.com.informanag.adpter.JDAppHeaderAdpater;
import fresh.com.informanag.adpter.listadpter.MyLisAddress;
import fresh.com.informanag.adpter.listadpter.MyListAdapter;
import fresh.com.informanag.adpter.listadpter.MyListSub;
import fresh.com.informanag.base.BaseActivity;
import fresh.com.informanag.bean.Bean_address_list;
import fresh.com.informanag.util.HttpUtil;

public class MeAddAddress extends BaseActivity {



    private Bean_address_list bean_address_list;
    private String message;
    private String code;
    private List<Bean_address_list.DataBean> data;

    private ArrayList<Bean_address_list.DataBean> data_all = new ArrayList<>();









    private  int REQUESTCODE = 1; // 返回的结果码



    @BindView(R.id.toolbar_all)
    Toolbar toolbar_all;




    @BindView(R.id.refresh_address)
    UltimateRefreshView refresh_address;

    @BindView(R.id.rv_address)
    ListView rv_address;






    private MyLisAddress myLisAddress;







    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this,clz));
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_act_add_address;
    }

    @Override
    protected void findViews() {

        TextView text_center = (TextView) toolbar_all.findViewById(R.id.toolbar_center);
        text_center.setText("我的收货地址");

        TextView toolbar_right = (TextView) toolbar_all.findViewById(R.id.toolbar_right);
        toolbar_right.setText("添加新地址");
        toolbar_right.setVisibility(View.VISIBLE);

    }

    @Override
    public void initData() {


//        ArrayList<String> datas = new ArrayList<>();
//        datas.clear();
//        for (int i = 0; i < 5; i++) {
//            datas.add("");
//        }
//
//        myLisAddress =  new MyLisAddress(datas, this);
//
//        rv_address.setAdapter(myLisAddress);


        refresh_address.setBaseHeaderAdapter(new JDAppHeaderAdpater(this));
        refresh_address.setBaseFooterAdapter(new JDAppFooterAdapter(this));
        refresh_address.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(UltimateRefreshView view) {
                showLoadingDialog();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        mAnimationAdapter.replaceData(null);
//刷新


                        HttpUtil.addMapparams();
                        HttpUtil.params.put("userNo", MyApp.user);
                        HttpUtil.post_Life("address/shippingAddressList", HttpUtil.params);
                        getdata("address/shippingAddressList");


                        refresh_address.onHeaderRefreshComplete();
                        dismissLoadingDialog();
                    }
                }, 2000);
            }
        });
        refresh_address.setOnFooterRefreshListener(new OnFooterRefreshListener() {
            @Override
            public void onFooterRefresh(UltimateRefreshView view) {

                showLoadingDialog();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        refresh_address.onFooterRefreshComplete();

                        dismissLoadingDialog();
                    }
                }, 2000);
            }
        });
    }

    @OnClick({R.id.toolbar_right})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.toolbar_right:

                Intent intent = new Intent(this,
                        ActAddNewAddress.class);
                // 这种启动方式：startActivity(intent);并不能返回结果
                startActivityForResult(intent, REQUESTCODE); //REQUESTCODE--->1

                break;
        }
    }











    public void StringResulit(String key, String value) {

//        第一次  加载  数据的 时候
        if (key.equals("address/shippingAddressList")) {

//            myLisAddress.list_clear();
            bean_address_list = MyApp.gson.fromJson(value, Bean_address_list.class);
            data = bean_address_list.getData();
            data_all.clear();
            data_all.addAll(data);
            myLisAddress =  new MyLisAddress(data_all, this,MeAddAddress.this,myLisAddress);
            rv_address.setAdapter(myLisAddress);
            myLisAddress.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("onResume", "onResume called.");



        HttpUtil.addMapparams();
        HttpUtil.params.put("userNo", MyApp.user);
        HttpUtil.post_Life("address/shippingAddressList", HttpUtil.params);
        getdata("address/shippingAddressList");



    }




    // 为了获取结果
     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                 super.onActivityResult(requestCode, resultCode, data);
                 // RESULT_OK，判断另外一个activity已经结束数据输入功能，Standard activity result:
                 // operation succeeded. 默认值是-1
                 if (resultCode == 2) {
                         if (requestCode == REQUESTCODE) {
//                                 int three = data.getIntExtra("three", 0);
//                                 //设置结果显示框的显示数值
//                                 result.setText(String.valueOf(three));
//添加完成 刷新  数据
                             HttpUtil.addMapparams();
                             HttpUtil.params.put("userNo", MyApp.user);
                             HttpUtil.post_Life("address/shippingAddressList", HttpUtil.params);
                             getdata("address/shippingAddressList");
                             }
                     }
             }
}

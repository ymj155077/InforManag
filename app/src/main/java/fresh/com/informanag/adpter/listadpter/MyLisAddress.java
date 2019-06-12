package fresh.com.informanag.adpter.listadpter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import fresh.com.informanag.App.MyApp;
import fresh.com.informanag.R;
import fresh.com.informanag.base.BaseActivity;
import fresh.com.informanag.bean.Bean_address_list;
import fresh.com.informanag.bean.Bean_all_message;
import fresh.com.informanag.ui.act.actMe.ActAddNewAddress;
import fresh.com.informanag.ui.act.actMe.ActNewsdetails;
import fresh.com.informanag.ui.act.actbusiness.ActQuXiaoDetails;
import fresh.com.informanag.util.HttpUtil;
import okhttp3.Callback;
import okhttp3.Response;

import static fresh.com.informanag.App.MyApp.call;

/**
 * Created by Administrator
 * on 2019/5/16 0016
 * <p>
 * 收货地址列表
 */
public class MyLisAddress extends BaseAdapter {


    private ArrayList<Bean_address_list.DataBean> datas = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;
    MyViewHolder myViewHolder = null;

    private Activity activity;

    private MyLisAddress myLisAddress;


    public MyLisAddress(ArrayList<Bean_address_list.DataBean> datas, Context context,Activity activity,MyLisAddress myLisAddress) {
        this.datas.clear();
        this.datas.addAll(datas);
        this.context = context;
        this.inflater = LayoutInflater.from(context);

        this.activity = activity;
        this.myLisAddress = myLisAddress;

    }


    //清除数据
    public void list_clear(ArrayList<Bean_address_list.DataBean> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    //    添加数据
    public void list_add(ArrayList<Bean_address_list.DataBean> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            myViewHolder = new MyViewHolder();

            convertView = inflater.inflate(R.layout.item_list_address, parent, false);

            myViewHolder.lin_item_address = (LinearLayout) convertView.findViewById(R.id.lin_item_address);

            myViewHolder.text_name = (TextView) convertView.findViewById(R.id.text_name);
            myViewHolder.text_phone = (TextView) convertView.findViewById(R.id.text_phone);
            myViewHolder.text_detail = (TextView) convertView.findViewById(R.id.text_detail);
            myViewHolder.text_detle = (TextView) convertView.findViewById(R.id.text_detle);



            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }



        //            private TextView text_name;
//            private TextView text_phone;
//            private TextView text_detail;


        myViewHolder.text_name.setText(datas.get(position).getConsignee());

        myViewHolder.text_phone.setText(datas.get(position).getPhone());

        myViewHolder.text_detail.setText(datas.get(position).getCity()+"   "+datas.get(position).getStreet()+"  "+datas.get(position).getDetail());


        myViewHolder.lin_item_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActAddNewAddress.class);

                intent.putExtra("AddressNo",datas.get(position).getAddressNo());

                context.startActivity(intent);
            }
        });
        myViewHolder.text_detle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                        .setTitleText("万仓生活!")
                        .setContentText("是否删除")
                        .setCustomImage(R.drawable.tuichu)
                        .showCancelButton(true)
                        .setCancelText("取消")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();
                            }
                        })
                        .setConfirmText("确定")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(final SweetAlertDialog sweetAlertDialog) {
                                HttpUtil.addMapparams();
                                HttpUtil.params.put("addressNo", datas.get(position).getAddressNo());
                                HttpUtil.post_Life("address/shippingAddressDelete", HttpUtil.params);
                                call.enqueue(new Callback() {
                                    @Override
                                    public void onFailure(okhttp3.Call call, IOException e) {

//                Log.i("okhttp3_IOException",e.getLocalizedMessage()+"---"+e.getMessage());

                                    }

                                    @Override
                                    public void onResponse(okhttp3.Call call, Response response) throws IOException {
                                        final String resp = response.body().string();

                                        activity.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Log.i("reasdasd",resp.toString());
                                                datas.remove(position);
//                                              myLisAddress.list_clear();
                                                notifyDataSetChanged();
                                                Toast.makeText(context,"为什么要删我！！！",Toast.LENGTH_SHORT).show();
                                                sweetAlertDialog.dismissWithAnimation();
                                            }
                                        });
                                    }
                                });
                            }
                        })
                        .show();
            }
        });

        return convertView;
    }


    public class MyViewHolder {

        private LinearLayout lin_item_address;

        private TextView text_name;
        private TextView text_phone;
        private TextView text_detail;


        private TextView text_detle;




    }


}

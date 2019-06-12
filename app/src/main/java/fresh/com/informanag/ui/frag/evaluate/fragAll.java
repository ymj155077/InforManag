package fresh.com.informanag.ui.frag.evaluate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.sak.ultilviewlib.UltimateRefreshView;
import com.sak.ultilviewlib.interfaces.OnFooterRefreshListener;
import com.sak.ultilviewlib.interfaces.OnHeaderRefreshListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import fresh.com.informanag.App.MyApp;
import fresh.com.informanag.R;
import fresh.com.informanag.adpter.JDAppFooterAdapter;
import fresh.com.informanag.adpter.JDAppHeaderAdpater;
import fresh.com.informanag.adpter.listadpter.MyListNoRes;
import fresh.com.informanag.base.BaseFragment;
import fresh.com.informanag.bean.Bean_huifu;
import fresh.com.informanag.bean.Bean_no_pin;
import fresh.com.informanag.util.HttpUtil;
import fresh.com.informanag.util.Tool;
import fresh.com.informanag.view.Nine.TNinePlaceGridView;
import fresh.com.informanag.view.NoScrollListView;
import fresh.com.informanag.view.Textdialog.InputTextMsgDialog;

/**
 * Created by Administrator
 * on 2019/5/24 0024
 * <p>
 * 全部的回复
 */
public class fragAll extends BaseFragment {


    private int page = 1;
    //    分页
    public int paging = 0;


    //全部回复
    private Bean_huifu bean_huifu;
    private String message;
    private String code;
    private List<Bean_huifu.DataBean> data;

    private ArrayList<Bean_huifu.DataBean> data_all = new ArrayList<>();


    @BindView(R.id.refresh_all)
    UltimateRefreshView refresh_all;

    @BindView(R.id.rv_all)
    ListView rv_all;


    private InputTextMsgDialog inputTextMsgDialog;


    private MyAdapterRes myAdapterRes;


    @Override
    protected int getLayoutId() {
        return R.layout.frag_all;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

        inputTextMsgDialog = new InputTextMsgDialog(getActivity(), R.style.dialog_center);
        inputTextMsgDialog.setHint("请输入"); //设置输入提示文字
        inputTextMsgDialog.setBtnText("发送"); //设置按钮的文字 默认为：发送
        inputTextMsgDialog.setMaxNumber(200);//最大输入字数 默认200


        inputTextMsgDialog.setmOnTextSendListener(new InputTextMsgDialog.OnTextSendListener() {
            @Override
            public void onTextSend(String msg) {
                //点击发送按钮后，回调此方法，msg为输入的值

                HttpUtil.addMapparams();

                HttpUtil.params.put("evaluationNo", myAdapterRes.getPostion());

                HttpUtil.params.put("merchantReply", msg);

                HttpUtil.post_Life("goods/merchantReply", HttpUtil.params);
                getdata("goods/merchantReply");

                inputTextMsgDialog.dismiss(); //隐藏此dialog
            }
        });


        refresh_all.setBaseHeaderAdapter(new JDAppHeaderAdpater(getActivity()));
        refresh_all.setBaseFooterAdapter(new JDAppFooterAdapter(getActivity()));
        refresh_all.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(UltimateRefreshView view) {
                showLoadingDialog();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        mAnimationAdapter.replaceData(null);
//                        刷新

                        refresh_all.onHeaderRefreshComplete();
                        dismissLoadingDialog();
                    }
                }, 2000);
            }
        });
        refresh_all.setOnFooterRefreshListener(new OnFooterRefreshListener() {
            @Override
            public void onFooterRefresh(UltimateRefreshView view) {

                showLoadingDialog();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        refresh_all.onFooterRefreshComplete();

                        dismissLoadingDialog();
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(getActivity(), clz));
    }


    @Override
    public void onResume() {
        super.onResume();
        HttpUtil.addMapparams();
        Log.i("merchantNo", MyApp.merchantNo + "---");
//        MyApp.merchantNo
        HttpUtil.params.put("merchantNo", MyApp.merchantNo);

        HttpUtil.params.put("page", "1");

        HttpUtil.params.put("size", "10");

//        HttpUtil.params.put("isReply", "true");

        HttpUtil.post_Life("goods/evaluationList", HttpUtil.params);
        getdata("goods/evaluationList_all");
    }

    public void StringResulit(String key, String value) {
        if (key.equals("goods/evaluationList_all")) {
            data_all.clear();
            bean_huifu = MyApp.gson.fromJson(value, Bean_huifu.class);
            data = bean_huifu.getData();
            data_all.addAll(data);
            if (paging == 0) {
                myAdapterRes = new MyAdapterRes(data_all, getActivity());

                rv_all.setAdapter(myAdapterRes);
            } else if (paging == 1) {
                myAdapterRes.clear_data(data_all);
                myAdapterRes.notifyDataSetChanged();
            } else if (paging == 2) {
                myAdapterRes.add_data(data_all);
                myAdapterRes.notifyDataSetChanged();
            }
        }
    }


    public class MyAdapterRes extends BaseAdapter {


        public String getPostion() {
            return evaluationNo;
        }

        private String evaluationNo;


        private ArrayList<Bean_huifu.DataBean> datas = new ArrayList<>();
        private LayoutInflater inflater;


        public void clear_data(ArrayList<Bean_huifu.DataBean> datas) {
            this.datas.clear();
            this.datas.addAll(datas);
            notifyDataSetChanged();
        }


        public void add_data(ArrayList<Bean_huifu.DataBean> datas) {

            this.datas.addAll(datas);
            notifyDataSetChanged();

        }


        public MyAdapterRes(ArrayList<Bean_huifu.DataBean> datas, Context context) {
            this.datas.clear();
            this.datas.addAll(datas);

            this.inflater = LayoutInflater.from(context);
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

            MyViewHolder myViewHolder = null;
            if (convertView == null) {
                myViewHolder = new MyViewHolder();

                convertView = inflater.inflate(R.layout.layout_unanswered, parent, false);

                convertView.setTag(myViewHolder);

            } else {
                myViewHolder = (MyViewHolder) convertView.getTag();
            }


            myViewHolder.ninePlaceGridView = (TNinePlaceGridView) convertView.findViewById(R.id.ninePlaceGridView);
            myViewHolder.lin_Comment_on_others = (LinearLayout) convertView.findViewById(R.id.lin_Comment_on_others);
//            private RoundedImageView head_round_me;
//            private TextView text_name;
//            private RatingBar rb_bar;
//            private TextView text_time;
//            private TextView text_context;
            myViewHolder.head_round_me = (RoundedImageView) convertView.findViewById(R.id.head_round_me);
            myViewHolder.text_name = (TextView) convertView.findViewById(R.id.text_name);
            myViewHolder.rb_bar = (RatingBar) convertView.findViewById(R.id.rb_bar);
            myViewHolder.text_time = (TextView) convertView.findViewById(R.id.text_time);
            myViewHolder.text_context = (TextView) convertView.findViewById(R.id.text_context);

            if (datas.get(position).getImgUrls() != null) {
                List<Object> urls = new ArrayList<>();
                urls.clear();
                for (int i = 0; i < datas.get(position).getImgUrls().size(); i++) {
                    urls.add(datas.get(position).getImgUrls().get(i));
                }
                myViewHolder.ninePlaceGridView.setImageNames(urls);
            }


            try {
                Glide.with(getActivity()).load(datas.get(position).getHeadImg()).into(myViewHolder.head_round_me);
                myViewHolder.text_name.setText(datas.get(position).getNickname());

                try {
                    myViewHolder.text_time.setText(Tool.times(datas.get(position).getRawAddTime() + ""));
                }catch (Exception e){

                }


                myViewHolder.text_context.setText(datas.get(position).getContent());

            }catch (Exception e){

            }




//            myViewHolder.ninePlaceGridView.setImageNames(datas.get(position).getImgUrls());

            myViewHolder.lin_Comment_on_others.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("isIsReply", datas.get(position).isIsReply() + "");
                    if (datas.get(position).isIsReply()) {

                    } else {
//                        没有 回复
                        evaluationNo = position + "";
                        inputTextMsgDialog.show();
                    }
                }
            });
            return convertView;
        }

        public class MyViewHolder {
            private TNinePlaceGridView ninePlaceGridView;
            private LinearLayout lin_Comment_on_others;

            private RoundedImageView head_round_me;
            private TextView text_name;
            private RatingBar rb_bar;
            private TextView text_time;
            private TextView text_context;

        }
    }

    /*
     * 将时间戳转换为时间
     */
    public String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }


}

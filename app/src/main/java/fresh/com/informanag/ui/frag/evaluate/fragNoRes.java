package fresh.com.informanag.ui.frag.evaluate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.sak.ultilviewlib.UltimateRefreshView;
import com.sak.ultilviewlib.interfaces.OnFooterRefreshListener;
import com.sak.ultilviewlib.interfaces.OnHeaderRefreshListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import fresh.com.informanag.App.MyApp;
import fresh.com.informanag.R;
import fresh.com.informanag.adpter.JDAppFooterAdapter;
import fresh.com.informanag.adpter.JDAppHeaderAdpater;
import fresh.com.informanag.base.BaseFragment;
import fresh.com.informanag.bean.Bean_no_pin;
import fresh.com.informanag.util.HttpUtil;
import fresh.com.informanag.view.Nine.TNinePlaceGridView;
import fresh.com.informanag.view.NoScrollListView;
import fresh.com.informanag.view.Textdialog.InputTextMsgDialog;

/**
 * Created by Administrator
 * on 2019/5/24 0024
 * <p>
 * 未回复
 */
public class fragNoRes extends BaseFragment {








    private InputTextMsgDialog inputTextMsgDialog;




//    implements View.OnLayoutChangeListener
    private int page = 1;
    //    分页
    public int paging = 0;

    //Activity最外层的Layout视图
    private View activityRootView;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
//    private int keyHeight = 0;

    @BindView(R.id.refresh_no_res)
    UltimateRefreshView refresh_no_res;

    @BindView(R.id.rv_no_res)
    ListView rv_no_res;


//    private MyNoAdapter myNoAdapter;

    private MyListNoRes myListNoRes;


    private Bean_no_pin bean_no_pin;
    private String message_no_pin;
    private String code_no_pin;
    private List<Bean_no_pin.DataBean> data_no_pin;

    private ArrayList<Bean_no_pin.DataBean> data_no_pin_all = new ArrayList<>();

    private String evaluationNo;
    private String goodsNo;
    private String userNo;
    private String nickname;
    private String content;
    private String evaluationLevel;
    private boolean isReply;
    private long rawAddTime;
    private String headImg;
    private String merchantReply;
    private List<String> imgUrls;


    @Override
    protected int getLayoutId() {

        //让布局向上移来显示软键盘
//        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        return R.layout.frag_no_res;
    }

    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(getActivity(), clz));
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
//        activityRootView = view.findViewById(R.id.activity_act_management_details);
    }


    @Override
    public void onResume() {
        super.onResume();
        //添加layout大小发生改变监听器
//        activityRootView.addOnLayoutChangeListener(this);
        HttpUtil.addMapparams();

        Log.i("merchantNo", MyApp.merchantNo + "---");
//        MyApp.merchantNo

        page = 1;

        HttpUtil.params.put("merchantNo", MyApp.merchantNo);

        HttpUtil.params.put("page", "1");

        HttpUtil.params.put("size", "10");

        HttpUtil.params.put("isReply", "false");

        HttpUtil.post_Life("goods/evaluationList", HttpUtil.params);
        getdata("goods/evaluationList_no");
    }


    public void StringResulit(String key, String value) {
        if (key.equals("goods/evaluationList_no")) {

            bean_no_pin = MyApp.gson.fromJson(value, Bean_no_pin.class);
            data_no_pin = bean_no_pin.getData();
            data_no_pin_all.clear();
            data_no_pin_all.addAll(data_no_pin);

            if (paging == 0){

                myListNoRes = new MyListNoRes(data_no_pin_all, getActivity());
                rv_no_res.setAdapter(myListNoRes);

            }else if (paging==1){
//                刷新
                myListNoRes.clear_data(data_no_pin_all);
                myListNoRes.notifyDataSetChanged();

            }else if (paging==2){
//                加载
                myListNoRes.add_data(data_no_pin_all);
                myListNoRes.notifyDataSetChanged();
            }
        }
//        评论以后
        if (key.equals("goods/merchantReply")){

            HttpUtil.addMapparams();

            Log.i("merchantNo", MyApp.merchantNo + "---");
//        MyApp.merchantNo
            HttpUtil.params.put("merchantNo", "1");

            HttpUtil.params.put("page", "1");

            HttpUtil.params.put("size", "10");

            HttpUtil.params.put("isReply", "false");

            HttpUtil.post_Life("goods/evaluationList", HttpUtil.params);
            getdata("goods/evaluationList");
        }
    }

    @Override
    protected void initData() {

        refresh_no_res.setBaseHeaderAdapter(new JDAppHeaderAdpater(getActivity()));
        refresh_no_res.setBaseFooterAdapter(new JDAppFooterAdapter(getActivity()));
        refresh_no_res.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(UltimateRefreshView view) {
                showLoadingDialog();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        mAnimationAdapter.replaceData(null);
//                        刷新



                        paging = 1;

                        page = 1;

                        HttpUtil.params.put("merchantNo", page+"");

                        HttpUtil.params.put("page", "1");

                        HttpUtil.params.put("size", "10");

                        HttpUtil.params.put("isReply", "false");

                        HttpUtil.post_Life("goods/evaluationList", HttpUtil.params);
                        getdata("goods/evaluationList_no");

                        refresh_no_res.onHeaderRefreshComplete();
                        dismissLoadingDialog();
                    }
                }, 2000);
            }
        });
        refresh_no_res.setOnFooterRefreshListener(new OnFooterRefreshListener() {
            @Override
            public void onFooterRefresh(UltimateRefreshView view) {

                showLoadingDialog();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ++page;

                        paging = 2;

                        HttpUtil.params.put("merchantNo", "1");

                        HttpUtil.params.put("page",  page +"");

                        HttpUtil.params.put("size", "10");

                        HttpUtil.params.put("isReply", "false");

                        HttpUtil.post_Life("goods/evaluationList", HttpUtil.params);
                        getdata("goods/evaluationList_no");

                        refresh_no_res.onFooterRefreshComplete();

                        dismissLoadingDialog();
                    }
                }, 2000);
            }
        });


//        rv_no_res.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
////                    移动时操作
//                    case MotionEvent.ACTION_MOVE:
//                        InputMethodManager mm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                        if (mm.isActive()) {
//                            mm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
//                        }
//                        if (rl_comment_.getVisibility() == View.VISIBLE) {
//                            rl_comment_.setVisibility(View.GONE);
//                        }
////                        commernt = 1;
//                        break;
//                }
//                return false;
//            }
//        });




        inputTextMsgDialog = new InputTextMsgDialog(getActivity(), R.style.dialog_center);
        inputTextMsgDialog.setHint("请输入"); //设置输入提示文字
        inputTextMsgDialog.setBtnText("发送"); //设置按钮的文字 默认为：发送
        inputTextMsgDialog.setMaxNumber(200);//最大输入字数 默认200





        inputTextMsgDialog.setmOnTextSendListener(new InputTextMsgDialog.OnTextSendListener() {
            @Override
            public void onTextSend(String msg) {
                //点击发送按钮后，回调此方法，msg为输入的值



                HttpUtil.addMapparams();

                HttpUtil.params.put("evaluationNo",myListNoRes.getPostion());

                HttpUtil.params.put("merchantReply", msg);

                HttpUtil.post_Life("goods/merchantReply", HttpUtil.params);
                getdata("goods/merchantReply");


                inputTextMsgDialog.dismiss(); //隐藏此dialog

            }
        });

    }


    public class MyListNoRes extends BaseAdapter {

        private String evaluationNo;
        private ArrayList<Bean_no_pin.DataBean> datas = new ArrayList<>();
        private LayoutInflater inflater;
        private Context context;



//        刷新  数据



        public void clear_data(ArrayList<Bean_no_pin.DataBean> datas){

            this.datas.clear();
            this.datas.addAll(datas);
            notifyDataSetChanged();

        }





//        添加  数据


        public void add_data(ArrayList<Bean_no_pin.DataBean> datas){
            this.datas.addAll(datas);
            notifyDataSetChanged();
        }







        public MyListNoRes(ArrayList<Bean_no_pin.DataBean> datas, Context context) {
            this.datas.clear();
            this.datas.addAll(datas);

            this.inflater = LayoutInflater.from(context);

            this.context = context;

        }



        public String getPostion() {
            return evaluationNo;
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

                myViewHolder.lin_Comment_on_others = (LinearLayout) convertView.findViewById(R.id.lin_Comment_on_others);

                myViewHolder.ninePlaceGridView = (TNinePlaceGridView) convertView.findViewById(R.id.ninePlaceGridView);


                myViewHolder.list_pin = (NoScrollListView) convertView.findViewById(R.id.list_pin);

                myViewHolder.text_name = (TextView) convertView.findViewById(R.id.text_name);
                myViewHolder.head_round_me = (RoundedImageView) convertView.findViewById(R.id.head_round_me);
                myViewHolder.text_time = (TextView) convertView.findViewById(R.id.text_time);
                myViewHolder.text_context = (TextView) convertView.findViewById(R.id.text_context);

                convertView.setTag(myViewHolder);

            } else {
                myViewHolder = (MyViewHolder) convertView.getTag();
            }


//                private TextView text_name;
//
//                private RoundedImageView head_round_me;
//
//                private TextView text_time;
//
//                private TextView text_context;


            myViewHolder.text_name.setText(datas.get(position).getNickname());

            myViewHolder.text_time.setText(stampToDate(datas.get(position).getRawAddTime() + ""));

            myViewHolder.text_context.setText(datas.get(position).getContent());


            Glide.with(context).load(datas.get(position).getHeadImg()).into(myViewHolder.head_round_me);


            if (datas.get(position).getImgUrls() != null) {

                List<Object> urls = new ArrayList<>();

                urls.clear();

                for (int i = 0; i < datas.get(position).getImgUrls().size(); i++) {
                    urls.add(datas.get(position).getImgUrls().get(i));
                }
                myViewHolder.ninePlaceGridView.setImageNames(urls);
            }

            myViewHolder.lin_Comment_on_others.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//                    if (rl_comment_.getVisibility() == View.GONE) {
//                        rl_comment_.setVisibility(View.VISIBLE);
////                                     设置获取焦点 editext
//                        rl_comment_.requestFocus();
////                                     直接 评论 发布 动态的 人
//                    } else {
//                        rl_comment_.setVisibility(View.GONE);
//                    }
//
//
//                    evaluationNo = datas.get(position).getEvaluationNo()+"";
//
//                    Log.i("position",position+"----"+evaluationNo);

                    evaluationNo = position+"";


                    inputTextMsgDialog.show();




                }
            });
            return convertView;
        }


        public class MyViewHolder {


            private TNinePlaceGridView ninePlaceGridView;

            private LinearLayout lin_Comment_on_others;

            private NoScrollListView list_pin;


            private TextView text_name;

            private RoundedImageView head_round_me;

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

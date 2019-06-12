package fresh.com.informanag.ui.frag.order;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.TextViewCompat;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sak.ultilviewlib.UltimateRefreshView;
import com.sak.ultilviewlib.interfaces.OnFooterRefreshListener;
import com.sak.ultilviewlib.interfaces.OnHeaderRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import fresh.com.informanag.App.MyApp;
import fresh.com.informanag.R;
import fresh.com.informanag.adpter.JDAppFooterAdapter;
import fresh.com.informanag.adpter.JDAppHeaderAdpater;
import fresh.com.informanag.adpter.listadpter.MyListSub;
import fresh.com.informanag.base.BaseFragment;
import fresh.com.informanag.bean.Bean_wait;
import fresh.com.informanag.util.HttpUtil;
import fresh.com.informanag.util.MdFTion;

import static fresh.com.informanag.App.MyApp.Time_stamp;
import static fresh.com.informanag.App.MyApp.gson;
import static fresh.com.informanag.App.MyApp.pageSize;
import static fresh.com.informanag.App.MyApp.ran_number;
import static fresh.com.informanag.App.MyApp.sign;


/**
 * Created by Administrator
 * on 2019/5/14 0014
 * <p>
 * 代付款
 */
public class FragSubPay extends BaseFragment  {


    private int page = 1;
    private int type = 0;


    private MyListSub myListSub;


    private Bean_wait bean_wait;
    private String code;
    private String message;
    private List<Bean_wait.DataBean> data;

    private ArrayList<Bean_wait.DataBean> datas = new ArrayList<>();


    @BindView(R.id.refresh_sub)
    UltimateRefreshView mUltimateRefreshView;

    @BindView(R.id.rv_sub)
    ListView rv_sub;


    @Override
    protected int getLayoutId() {
        return R.layout.frag_sub_pay;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {


//        mUltimateRefreshView.setBaseHeaderAdapter(new JDAppHeaderAdpater(getActivity()));
//        mUltimateRefreshView.setBaseFooterAdapter(new JDAppFooterAdapter(getActivity()));
//        mUltimateRefreshView.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
//            @Override
//            public void onHeaderRefresh(UltimateRefreshView view) {
//                showLoadingDialog();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
////                        mAnimationAdapter.replaceData(null);
////刷新
//                        Toast.makeText(getActivity(), "刷新", Toast.LENGTH_SHORT).show();
//                        page = 1;
//                        type = 1;
//                        HttpUtil.addMapparams();
//                        HttpUtil.params.put("page", page + "");
//                        HttpUtil.params.put("size", pageSize + "");
//                        HttpUtil.params.put("userNo", "1jagh4213cS041bqq7q6");
//                        HttpUtil.params.put("state", "WAIT_BUYER_PAY");
//                        HttpUtil.post_Life("order/goods/listForUser", HttpUtil.params);
//                        getdata("WAIT_BUYER_PAY");
//
//                        mUltimateRefreshView.onHeaderRefreshComplete();
//                        dismissLoadingDialog();
//                    }
//                }, 2000);
//            }
//        });
//        mUltimateRefreshView.setOnFooterRefreshListener(new OnFooterRefreshListener() {
//            @Override
//            public void onFooterRefresh(UltimateRefreshView view) {
//
//                showLoadingDialog();
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        Toast.makeText(getActivity(), "加载更多", Toast.LENGTH_SHORT).show();
//
//
//                        ++page;
//
//                        type = 2;
//                        HttpUtil.addMapparams();
//                        HttpUtil.params.put("page", page + "");
//                        HttpUtil.params.put("size", pageSize + "");
//                        HttpUtil.params.put("userNo", "1jagh4213cS041bqq7q6");
//                        HttpUtil.params.put("state", "WAIT_BUYER_PAY");
//                        HttpUtil.post_Life("order/goods/listForUser", HttpUtil.params);
//                        getdata("WAIT_BUYER_PAY");
//
//                        mUltimateRefreshView.onFooterRefreshComplete();
//
//                        dismissLoadingDialog();
//                    }
//                }, 2000);
//            }
//        });


//        myListSub.setOnCall(new MyListSub.OnCall() {
//            @Override
//            public void setInfo(String nm, int position) {
//
//
//            }
//        });
    }

    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(getActivity(), clz));
    }


    //viewpager切换的时候 刷新
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        try {
//            if (getUserVisibleHint()) {//界面可见时
//
//                HttpUtil.addMapparams();
//                HttpUtil.params.put("page", MyApp.page);
//                HttpUtil.params.put("size", pageSize);
//                HttpUtil.params.put("userNo", "1jagh4213cS041bqq7q6");
//                HttpUtil.params.put("state", "WAIT_BUYER_PAY");
//                HttpUtil.post_Life("order/goods/listForUser", HttpUtil.params);
//                getdata("WAIT_BUYER_PAY");
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
//        if (hidden) {
//
//            HttpUtil.addMapparams();
//            HttpUtil.params.put("page", MyApp.page);
//            HttpUtil.params.put("size", pageSize);
//            HttpUtil.params.put("userNo", "1jagh4213cS041bqq7q6");
//            HttpUtil.params.put("state", "WAIT_BUYER_PAY");
//            HttpUtil.post_Life("user/register", HttpUtil.params);
//            getdata("WAIT_BUYER_PAY");
//
//        } else {
//            //...隐藏的操作
//        }
    }


    public void StringResulit(String key, String value) {

//        第一次  加载  数据的 时候
        if (key.equals("WAIT_BUYER_PAY")) {

//            private Bean_wait bean_wait;
//            private String code;
//            private String message;
//            private List<Bean_wait.DataBean> data;
//            private ArrayList<Bean_wait.DataBean> datas = new ArrayList<>();

            bean_wait = gson.fromJson(value, Bean_wait.class);

            data = bean_wait.getData();

            if (type == 0) {
                datas.clear();
                datas.addAll(data);

                myListSub = new MyListSub(datas, getActivity());
                rv_sub.setAdapter(myListSub);
            } else if (type == 1) {


                datas.clear();
                datas.addAll(data);

                myListSub.clear_data(datas);
                myListSub.notifyDataSetChanged();

            } else if (type == 2) {
                datas.clear();
                datas.addAll(data);
                myListSub.add_data(datas);
                myListSub.notifyDataSetChanged();

            }
        }
    }
}

package fresh.com.informanag.ui.frag.order;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;

import com.sak.ultilviewlib.UltimateRefreshView;
import com.sak.ultilviewlib.interfaces.OnFooterRefreshListener;
import com.sak.ultilviewlib.interfaces.OnHeaderRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import fresh.com.informanag.R;
import fresh.com.informanag.adpter.JDAppFooterAdapter;
import fresh.com.informanag.adpter.JDAppHeaderAdpater;
import fresh.com.informanag.adpter.listadpter.MyListAdapter;
import fresh.com.informanag.base.BaseFragment;


/**
 * Created by Administrator
 * on 2019/5/14 0014
 * <p>
 * 全部的订单
 */
public class FragAllOrders extends BaseFragment {

    private UltimateRefreshView mUltimateRefreshView;

    @BindView(R.id.rv_list)
    ListView rv_list;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_all_orders;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mUltimateRefreshView = (UltimateRefreshView) view.findViewById(R.id.refreshView);
    }

    @Override
    protected void initData() {

//        ArrayList<String> datas = new ArrayList<>();
//
//        for (int i = 0; i < 9; i++) {
//            datas.add("");
//        }
//
//        rv_list.setAdapter(new MyListAdapter(datas,getActivity()));
//
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
//                        mUltimateRefreshView.onFooterRefreshComplete();
//
//                        dismissLoadingDialog();
//                    }
//                }, 2000);
//            }
//        });
    }

    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(getActivity(), clz));
    }


    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.text_click:
//                Toast.makeText(getActivity(), "点击！！！！", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getActivity(), SecondAct.class));
//                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
//        可见的时候 刷新 数据
//        mUltimateRefreshView.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
//            @Override
//            public void onHeaderRefresh(UltimateRefreshView view) {
//                showLoadingDialog();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mUltimateRefreshView.onHeaderRefreshComplete();
//                        dismissLoadingDialog();
//                    }
//                }, 2000);
//            }
//        });
    }


}

package fresh.com.informanag.ui.frag.bill;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
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
import fresh.com.informanag.adpter.listadpter.MyListAlready;
import fresh.com.informanag.adpter.listadpter.MyListIncome;
import fresh.com.informanag.base.BaseFragment;

/**
 * Created by Administrator
 * on 2019/5/23 0023
 * <p>
 * <p>
 * 所有的收入
 */
public class fragAllIncome extends BaseFragment {


    @BindView(R.id.refresh_in)
    UltimateRefreshView refresh_in;


    @BindView(R.id.rv_in)
    ListView rv_in;


    @Override
    protected int getLayoutId() {
        return R.layout.frag_all_income;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(getActivity(), clz));
    }

    @Override
    protected void initData() {
        ArrayList<String> datas = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            datas.add("");
        }




        View view = LayoutInflater.from(getActivity()).inflate(R.layout.head_view_layout, null);
        rv_in.addHeaderView(view);




        rv_in.setAdapter(new MyListIncome(datas, getActivity()));
        refresh_in.setBaseHeaderAdapter(new JDAppHeaderAdpater(getActivity()));
        refresh_in.setBaseFooterAdapter(new JDAppFooterAdapter(getActivity()));
        refresh_in.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(UltimateRefreshView view) {
                showLoadingDialog();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        mAnimationAdapter.replaceData(null);
//刷新
                        refresh_in.onHeaderRefreshComplete();
                        dismissLoadingDialog();
                    }
                }, 2000);
            }
        });
        refresh_in.setOnFooterRefreshListener(new OnFooterRefreshListener() {
            @Override
            public void onFooterRefresh(UltimateRefreshView view) {

                showLoadingDialog();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        refresh_in.onFooterRefreshComplete();

                        dismissLoadingDialog();
                    }
                }, 2000);
            }
        });
    }
    }


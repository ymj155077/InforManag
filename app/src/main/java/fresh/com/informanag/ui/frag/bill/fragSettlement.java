package fresh.com.informanag.ui.frag.bill;

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
import fresh.com.informanag.adpter.listadpter.MyListIncome;
import fresh.com.informanag.adpter.listadpter.MyListment;
import fresh.com.informanag.base.BaseFragment;

/**
 * Created by Administrator
 * on 2019/5/23 0023
 *
 * 待结算
 *
 */
public class fragSettlement extends BaseFragment {





    @BindView(R.id.refresh_lement)
    UltimateRefreshView refresh_lement;


    @BindView(R.id.rv_lement)
    ListView rv_lement;





    @Override
    protected int getLayoutId() {
        return R.layout.frag_settlement;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            datas.add("");
        }
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.head_view_you, null);
        rv_lement.addHeaderView(view);
        rv_lement.setAdapter(new MyListment(datas, getActivity()));
        refresh_lement.setBaseHeaderAdapter(new JDAppHeaderAdpater(getActivity()));
        refresh_lement.setBaseFooterAdapter(new JDAppFooterAdapter(getActivity()));
        refresh_lement.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(UltimateRefreshView view) {
                showLoadingDialog();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        mAnimationAdapter.replaceData(null);
//刷新
                        refresh_lement.onHeaderRefreshComplete();
                        dismissLoadingDialog();
                    }
                }, 2000);
            }
        });
        refresh_lement.setOnFooterRefreshListener(new OnFooterRefreshListener() {
            @Override
            public void onFooterRefresh(UltimateRefreshView view) {

                showLoadingDialog();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        refresh_lement.onFooterRefreshComplete();

                        dismissLoadingDialog();
                    }
                }, 2000);
            }
        });

    }

    @Override
    public void startActivity(Class<?> clz) {

    }
}

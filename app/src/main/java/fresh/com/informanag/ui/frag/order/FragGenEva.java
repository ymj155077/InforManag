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
import fresh.com.informanag.adpter.listadpter.MyListCollBe;
import fresh.com.informanag.adpter.listadpter.MyListGenEva;
import fresh.com.informanag.base.BaseFragment;

/**
 * Created by Administrator
 * on 2019/5/14 0014
 *
 * 待使用
 *
 */
public class FragGenEva extends BaseFragment {




    private UltimateRefreshView refreshrv_eva;

    @BindView(R.id.rv_eva)
    ListView rv_eva;



    @Override
    protected int getLayoutId() {
        return R.layout.frag_gen_eva;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        refreshrv_eva = (UltimateRefreshView) view.findViewById(R.id.refreshrv_eva);
    }

    @Override
    protected void initData() {
//        ArrayList<String> datas = new ArrayList<>();
//
//        for (int i = 0; i < 9; i++) {
//            datas.add("");
//        }
//
//        rv_eva.setAdapter(new MyListGenEva(datas, getActivity()));
//
//        refreshrv_eva.setBaseHeaderAdapter(new JDAppHeaderAdpater(getActivity()));
//        refreshrv_eva.setBaseFooterAdapter(new JDAppFooterAdapter(getActivity()));
//        refreshrv_eva.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
//            @Override
//            public void onHeaderRefresh(UltimateRefreshView view) {
//                showLoadingDialog();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
////                        mAnimationAdapter.replaceData(null);
////刷新
//
//                        refreshrv_eva.onHeaderRefreshComplete();
//                        dismissLoadingDialog();
//                    }
//                }, 2000);
//            }
//        });
//        refreshrv_eva.setOnFooterRefreshListener(new OnFooterRefreshListener() {
//            @Override
//            public void onFooterRefresh(UltimateRefreshView view) {
//
//                showLoadingDialog();
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        refreshrv_eva.onFooterRefreshComplete();
//
//                        dismissLoadingDialog();
//                    }
//                }, 2000);
//            }
//        });
    }

    @Override
    public void startActivity(Class<?> clz) {
startActivity(new Intent(getActivity(),clz));
    }
}

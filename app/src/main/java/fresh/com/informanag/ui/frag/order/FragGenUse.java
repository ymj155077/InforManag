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
import fresh.com.informanag.adpter.listadpter.MyListGenUse;
import fresh.com.informanag.base.BaseFragment;

/**
 * Created by Administrator
 * on 2019/5/14 0014
 * <p>
 * 待发货
 */
public class FragGenUse extends BaseFragment {

    private UltimateRefreshView refreshGenUse;

    @BindView(R.id.rv_GenUse)
    ListView rv_GenUse;

    private ArrayList<String> mList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.frag_gen_use;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        refreshGenUse = (UltimateRefreshView) view.findViewById(R.id.refreshGenUse);
    }

    @Override
    protected void initData() {

//        ArrayList<String> datas = new ArrayList<>();
//
//        for (int i = 0; i < 9; i++) {
//            datas.add("");
//        }
//
//        rv_GenUse.setAdapter(new MyListGenUse(datas,getActivity()));
//
//        refreshGenUse.setBaseHeaderAdapter(new JDAppHeaderAdpater(getActivity()));
//        refreshGenUse.setBaseFooterAdapter(new JDAppFooterAdapter(getActivity()));
//        refreshGenUse.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
//            @Override
//            public void onHeaderRefresh(UltimateRefreshView view) {
//                showLoadingDialog();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
////                        mAnimationAdapter.replaceData(null);
////刷新
//
//                        refreshGenUse.onHeaderRefreshComplete();
//                        dismissLoadingDialog();
//                    }
//                }, 2000);
//            }
//        });
//        refreshGenUse.setOnFooterRefreshListener(new OnFooterRefreshListener() {
//            @Override
//            public void onFooterRefresh(UltimateRefreshView view) {
//
//                showLoadingDialog();
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        refreshGenUse.onFooterRefreshComplete();
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


}

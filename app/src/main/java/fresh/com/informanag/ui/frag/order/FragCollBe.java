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
import fresh.com.informanag.adpter.listadpter.MyListCollBe;
import fresh.com.informanag.base.BaseFragment;

/**
 * Created by Administrator
 * on 2019/5/14 0014
 * <p>
 * 代收货
 */
public class FragCollBe extends BaseFragment {


    private UltimateRefreshView refreshCollbe;

    @BindView(R.id.rv_Collbe)
    ListView rv_Collbe;


    @Override
    protected int getLayoutId() {
        return R.layout.frag_coll_be;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        refreshCollbe = (UltimateRefreshView) view.findViewById(R.id.refreshCollbe);
    }

    @Override
    protected void initData() {

//        ArrayList<String> datas = new ArrayList<>();
//
//        for (int i = 0; i < 9; i++) {
//            datas.add("");
//        }
//
//        rv_Collbe.setAdapter(new MyListCollBe(datas, getActivity()));
//
//        refreshCollbe.setBaseHeaderAdapter(new JDAppHeaderAdpater(getActivity()));
//        refreshCollbe.setBaseFooterAdapter(new JDAppFooterAdapter(getActivity()));
//        refreshCollbe.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
//            @Override
//            public void onHeaderRefresh(UltimateRefreshView view) {
//                showLoadingDialog();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
////                        mAnimationAdapter.replaceData(null);
////刷新
//
//                        refreshCollbe.onHeaderRefreshComplete();
//                        dismissLoadingDialog();
//                    }
//                }, 2000);
//            }
//        });
//        refreshCollbe.setOnFooterRefreshListener(new OnFooterRefreshListener() {
//            @Override
//            public void onFooterRefresh(UltimateRefreshView view) {
//
//                showLoadingDialog();
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        refreshCollbe.onFooterRefreshComplete();
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

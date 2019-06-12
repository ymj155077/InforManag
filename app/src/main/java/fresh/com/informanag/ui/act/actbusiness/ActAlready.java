package fresh.com.informanag.ui.act.actbusiness;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import com.sak.ultilviewlib.UltimateRefreshView;
import com.sak.ultilviewlib.interfaces.OnFooterRefreshListener;
import com.sak.ultilviewlib.interfaces.OnHeaderRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import fresh.com.informanag.R;
import fresh.com.informanag.adpter.JDAppFooterAdapter;
import fresh.com.informanag.adpter.JDAppHeaderAdpater;
import fresh.com.informanag.adpter.listadpter.MyListAlready;
import fresh.com.informanag.adpter.listadpter.MyListShip;
import fresh.com.informanag.base.BaseActivity;

//已发货
public class ActAlready extends BaseActivity {


    @BindView(R.id.toolbar_all)
    Toolbar toolbar_all;


    @BindView(R.id.refresh_already)
    UltimateRefreshView refresh_already;


    @BindView(R.id.rv_already)
    ListView rv_already;


    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this,clz));
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_act_already;
    }

    @Override
    protected void findViews() {

        TextView text_center = (TextView) toolbar_all.findViewById(R.id.toolbar_center);
        text_center.setText("已发货");

    }

    @Override
    public void initData() {

//        ArrayList<String> datas = new ArrayList<>();
//
//        for (int i = 0; i < 9; i++) {
//            datas.add("");
//        }
//
//        rv_already.setAdapter(new MyListAlready(datas, this));
//        refresh_already.setBaseHeaderAdapter(new JDAppHeaderAdpater(this));
//        refresh_already.setBaseFooterAdapter(new JDAppFooterAdapter(this));
//        refresh_already.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
//            @Override
//            public void onHeaderRefresh(UltimateRefreshView view) {
//                showLoadingDialog();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
////                        mAnimationAdapter.replaceData(null);
////刷新
//                        refresh_already.onHeaderRefreshComplete();
//                        dismissLoadingDialog();
//                    }
//                }, 2000);
//            }
//        });
//        refresh_already.setOnFooterRefreshListener(new OnFooterRefreshListener() {
//            @Override
//            public void onFooterRefresh(UltimateRefreshView view) {
//
//                showLoadingDialog();
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        refresh_already.onFooterRefreshComplete();
//
//                        dismissLoadingDialog();
//                    }
//                }, 2000);
//            }
//        });
    }
    }

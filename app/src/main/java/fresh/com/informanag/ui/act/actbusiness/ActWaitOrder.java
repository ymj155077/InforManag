package fresh.com.informanag.ui.act.actbusiness;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
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
import fresh.com.informanag.adpter.listadpter.MyListAdapter;
import fresh.com.informanag.adpter.listadpter.MyListPerAdapter;
import fresh.com.informanag.base.BaseActivity;

//商家待接单
public class ActWaitOrder extends BaseActivity {


    @BindView(R.id.toolbar_all)
    Toolbar toolbar_all;


    @BindView(R.id.refresh_wait)
    UltimateRefreshView refresh_wait;


    @BindView(R.id.rv_wait)
    ListView rv_wait;

//    @BindView(R.id.lin_order)
//    LinearLayout lin_order;

    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_act_wait_order;
    }

    @Override
    protected void findViews() {

        TextView text_center = (TextView) toolbar_all.findViewById(R.id.toolbar_center);
        text_center.setText("待接单");

    }

    @Override
    public void initData() {

//        ArrayList<String> datas = new ArrayList<>();
//
//        for (int i = 0; i < 9; i++) {
//            datas.add("");
//        }
//
//        rv_wait.setAdapter(new MyListPerAdapter(datas, this, lin_order));
//        refresh_wait.setBaseHeaderAdapter(new JDAppHeaderAdpater(this));
//        refresh_wait.setBaseFooterAdapter(new JDAppFooterAdapter(this));
//        refresh_wait.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
//            @Override
//            public void onHeaderRefresh(UltimateRefreshView view) {
//                showLoadingDialog();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
////                        mAnimationAdapter.replaceData(null);
////刷新
//
//                        refresh_wait.onHeaderRefreshComplete();
//                        dismissLoadingDialog();
//                    }
//                }, 2000);
//            }
//        });
//        refresh_wait.setOnFooterRefreshListener(new OnFooterRefreshListener() {
//            @Override
//            public void onFooterRefresh(UltimateRefreshView view) {
//
//                showLoadingDialog();
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        refresh_wait.onFooterRefreshComplete();
//
//                        dismissLoadingDialog();
//                    }
//                }, 2000);
//            }
//        });
    }
}

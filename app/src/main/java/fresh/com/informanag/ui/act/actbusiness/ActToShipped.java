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
import fresh.com.informanag.adpter.listadpter.MyListPerAdapter;
import fresh.com.informanag.adpter.listadpter.MyListShip;
import fresh.com.informanag.base.BaseActivity;

//商家待发货
public class ActToShipped extends BaseActivity {


    @BindView(R.id.toolbar_all)
    Toolbar toolbar_all;


    @BindView(R.id.refresh_shipped)
    UltimateRefreshView refresh_shipped;


    @BindView(R.id.rv_shipped)
    ListView rv_shipped;





    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this,clz));
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_act_to_shipped;
    }

    @Override
    protected void findViews() {
        TextView text_center = (TextView) toolbar_all.findViewById(R.id.toolbar_center);
        text_center.setText("待发货");
    }

    @Override
    public void initData() {
//        ArrayList<String> datas = new ArrayList<>();
//
//        for (int i = 0; i < 9; i++) {
//            datas.add("");
//        }
//
//        rv_shipped.setAdapter(new MyListShip(datas, this));
//        refresh_shipped.setBaseHeaderAdapter(new JDAppHeaderAdpater(this));
//        refresh_shipped.setBaseFooterAdapter(new JDAppFooterAdapter(this));
//        refresh_shipped.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
//            @Override
//            public void onHeaderRefresh(UltimateRefreshView view) {
//                showLoadingDialog();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
////                        mAnimationAdapter.replaceData(null);
////刷新
//
//                        refresh_shipped.onHeaderRefreshComplete();
//                        dismissLoadingDialog();
//                    }
//                }, 2000);
//            }
//        });
//        refresh_shipped.setOnFooterRefreshListener(new OnFooterRefreshListener() {
//            @Override
//            public void onFooterRefresh(UltimateRefreshView view) {
//
//                showLoadingDialog();
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        refresh_shipped.onFooterRefreshComplete();
//
//                        dismissLoadingDialog();
//                    }
//                }, 2000);
//            }
//        });
    }
}

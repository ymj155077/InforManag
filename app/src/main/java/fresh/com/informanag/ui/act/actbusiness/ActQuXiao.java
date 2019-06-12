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
import fresh.com.informanag.adpter.listadpter.MyLisShouhuo;
import fresh.com.informanag.adpter.listadpter.MyLisquxiao;
import fresh.com.informanag.base.BaseActivity;
//已取消
public class ActQuXiao extends BaseActivity {



    @BindView(R.id.toolbar_all)
    Toolbar toolbar_all;


    @BindView(R.id.refresh_quxiao)
    UltimateRefreshView refresh_quxiao;


    @BindView(R.id.rv_quxiao)
    ListView rv_quxiao;








    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this,clz));
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_act_qu_xiao;
    }

    @Override
    protected void findViews() {
        TextView text_center = (TextView) toolbar_all.findViewById(R.id.toolbar_center);
        text_center.setText("已取消");
    }

    @Override
    public void initData() {
//        ArrayList<String> datas = new ArrayList<>();
//
//        for (int i = 0; i < 9; i++) {
//            datas.add("");
//        }
//
//        rv_quxiao.setAdapter(new MyLisquxiao(datas, this));
//        refresh_quxiao.setBaseHeaderAdapter(new JDAppHeaderAdpater(this));
//        refresh_quxiao.setBaseFooterAdapter(new JDAppFooterAdapter(this));
//        refresh_quxiao.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
//            @Override
//            public void onHeaderRefresh(UltimateRefreshView view) {
//                showLoadingDialog();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
////                        mAnimationAdapter.replaceData(null);
////刷新
//                        refresh_quxiao.onHeaderRefreshComplete();
//                        dismissLoadingDialog();
//                    }
//                }, 2000);
//            }
//        });
//        refresh_quxiao.setOnFooterRefreshListener(new OnFooterRefreshListener() {
//            @Override
//            public void onFooterRefresh(UltimateRefreshView view) {
//
//                showLoadingDialog();
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        refresh_quxiao.onFooterRefreshComplete();
//
//                        dismissLoadingDialog();
//                    }
//                }, 2000);
//            }
//        });
    }

}

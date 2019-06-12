package fresh.com.informanag.ui.act.actbusiness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import fresh.com.informanag.R;
import fresh.com.informanag.base.BaseActivity;

//待接单详情
public class ActWaitOrderDetails extends BaseActivity {


    @BindView(R.id.toolbar_all)
    Toolbar toolbar_all;

    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this,clz));
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_act_wait_order_details;
    }

    @Override
    protected void findViews() {

        TextView textView = (TextView) toolbar_all.findViewById(R.id.toolbar_center);
textView.setText("订单详情");

    }

    @Override
    public void initData() {

    }
}

package fresh.com.informanag.ui.act.actOrderpersonal;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import butterknife.BindView;
import fresh.com.informanag.R;
import fresh.com.informanag.base.BaseActivity;
import fresh.com.informanag.util.StatusBarUtil;


//订单待收货详情页
public class ActsubCollBeDestails extends BaseActivity {

    @BindView(R.id.toolbar_all)
    Toolbar toolbar_all;

    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_actsub_coll_be_destails;
    }


    @Override
    protected void findViews() {
//        Color.TRANSPARENT
//        StatusBarUtil.setStatusBarTranslucent(this, R.drawable.tool_back_order);

        TextView textView_center = (TextView) toolbar_all.findViewById(R.id.toolbar_center);
        textView_center.setText("待收货");

        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.tool_back_order, null);
        toolbar_all.setBackground(drawable);


    }

    @Override
    public void initData() {

    }
}

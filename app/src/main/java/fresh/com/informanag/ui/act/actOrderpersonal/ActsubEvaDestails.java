package fresh.com.informanag.ui.act.actOrderpersonal;

import android.content.Intent;

import fresh.com.informanag.R;
import fresh.com.informanag.base.BaseActivity;

//待使用
public class ActsubEvaDestails extends BaseActivity {


    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this,clz));
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_actsub_eva;
    }

    @Override
    protected void findViews() {

    }

    @Override
    public void initData() {

    }
}

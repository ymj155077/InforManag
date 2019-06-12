package fresh.com.informanag.ui.act.actbusiness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fresh.com.informanag.R;
import fresh.com.informanag.base.BaseActivity;

//已发货
public class ActAlreadyDetails extends BaseActivity {


    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this,clz));
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_act_already_details;
    }

    @Override
    protected void findViews() {

    }

    @Override
    public void initData() {

    }
}

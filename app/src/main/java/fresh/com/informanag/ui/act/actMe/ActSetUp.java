package fresh.com.informanag.ui.act.actMe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import fresh.com.informanag.R;
import fresh.com.informanag.base.BaseActivity;

public class ActSetUp extends BaseActivity {

    @BindView(R.id.lin_Logout)
    LinearLayout lin_Logout;

    @BindView(R.id.toolbar_all)
    Toolbar toolbar_all;

    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this,clz));
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_act_set_up;
    }

    @Override
    protected void findViews() {
        TextView text_center = toolbar_all.findViewById(R.id.toolbar_center);
        text_center.setText("设置");
    }

    @Override
    public void initData() {

    }


    public void onClick(View view){
        switch (view.getId()){
            case R.id.lin_Logout:

                finish();

                break;
        }
    }


}

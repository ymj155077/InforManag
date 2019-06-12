package fresh.com.informanag.ui.act.actMe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import fresh.com.informanag.R;
import fresh.com.informanag.base.BaseActivity;

public class ActNewsdetails extends BaseActivity {


    @BindView(R.id.toolbar_all)
    Toolbar toolbar_all;


    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this,clz));
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_act_newsdetails;
    }

    @Override
    protected void findViews() {

        TextView text_center = toolbar_all.findViewById(R.id.toolbar_center);
        text_center.setText("消息详情");

        TextView text_right = (TextView) toolbar_all.findViewById(R.id.toolbar_right);

        text_right.setText("删除");
        text_right.setVisibility(View.VISIBLE);




    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.toolbar_right})
    public void onClick(View view){

        switch (view.getId()){
            case R.id.toolbar_right:

                Toast.makeText(this,"删除",Toast.LENGTH_SHORT).show();

                break;
        }

    }


}

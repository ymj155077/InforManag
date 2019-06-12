package fresh.com.informanag.ui.act.actMe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import fresh.com.informanag.R;
import fresh.com.informanag.base.BaseActivity;

public class ActEvaCenter extends BaseActivity {


    @BindView(R.id.toolbar_all)
    Toolbar toolbar_all;

//    评价
    @BindView(R.id.lin_evaluate)
    LinearLayout lin_evaluate;


    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this,clz));
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_act_eva_center;
    }

    @Override
    protected void findViews() {

        TextView text_center = toolbar_all.findViewById(R.id.toolbar_center);
        text_center.setText("评价中心");

        TextView text_right = (TextView) toolbar_all.findViewById(R.id.toolbar_right);

        text_right.setText("全部订单");
        text_right.setVisibility(View.VISIBLE);


    }

    @Override
    public void initData() {

    }




    @OnClick({R.id.toolbar_right,R.id.lin_evaluate})
    public void onClick(View view){
        switch (view.getId()){

            case R.id.toolbar_right:

                Toast.makeText(this,"全部订单！！！",Toast.LENGTH_SHORT).show();

                break;

            case R.id.lin_evaluate:

                startActivity(ActComment.class);

                break;

        }
    }
}

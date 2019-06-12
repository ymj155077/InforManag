package fresh.com.informanag.ui.act.actMe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import fresh.com.informanag.R;
import fresh.com.informanag.base.BaseActivity;


//审核中
public class ToExamine extends BaseActivity {



    @BindView(R.id.img_In_audit)
    ImageView img_In_audit;

    @BindView(R.id.img_Resubmission)
    ImageView img_Resubmission;

    @BindView(R.id.img_Audit_success)
    ImageView img_Audit_success;




    //    审核中
    @BindView(R.id.lin_In_audit)
    LinearLayout lin_In_audit;
    //    审核失败
    @BindView(R.id.lin_Audit_failure)
    LinearLayout lin_Audit_failure;
    //    审核成功
    @BindView(R.id.lin_Audit_Success)
    LinearLayout lin_Audit_Success;

    @BindView(R.id.text_add_shop)
    TextView text_add_shop;




    @BindView(R.id.text_Audit_failure)
    TextView text_Audit_failure;




    private Toolbar toolbar;


    private int intent_type;


    private String intent_name;

    private TextView textView_center;


    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_to_examine;
    }

    @Override
    protected void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_all);
        if (toolbar != null) {
            textView_center = (TextView) toolbar.findViewById(R.id.toolbar_center);
            textView_center.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void initData() {

        Intent intent = getIntent();

        intent_type = intent.getIntExtra("intent_type", 0);

        intent_name = intent.getStringExtra("intent_name");

//      审核中
//        @BindView(R.id.lin_In_audit)
//        LinearLayout lin_In_audit;
////    审核失败
//        @BindView(R.id.lin_Audit_failure)
//        LinearLayout lin_Audit_failure;
////    审核成功
//        @BindView(R.id.lin_Audit_Success)
//        LinearLayout lin_Audit_Success;

        // 审核中:0/未通过:1/已通过:2
        if (intent_type == 0) {
            lin_In_audit.setVisibility(View.VISIBLE);
            lin_Audit_failure.setVisibility(View.GONE);
            lin_Audit_Success.setVisibility(View.GONE);
        } else if (intent_type == 1) {
            lin_In_audit.setVisibility(View.GONE);
            lin_Audit_failure.setVisibility(View.VISIBLE);
            lin_Audit_Success.setVisibility(View.GONE);
        } else if (intent_type == 2) {
            lin_In_audit.setVisibility(View.GONE);
            lin_Audit_failure.setVisibility(View.GONE);
            lin_Audit_Success.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.text_add_shop,R.id.img_In_audit,R.id.img_Resubmission,R.id.img_Audit_success,R.id.text_Audit_failure})
    public void onClick(View view) {
        switch (view.getId()) {
//                添加商品
            case R.id.text_add_shop:
//                Toast.makeText(this, "添加商品", Toast.LENGTH_SHORT).show();
//                startActivity(PerStoresAct.class);
                finish();
                break;
//                重新 进行审核
            case R.id.text_Audit_failure:
//              startActivity(MerCertifiAct.class);
              finish();
                break;

            case R.id.img_In_audit:

                finish();

                break;

            case R.id.img_Resubmission:

                finish();

                break;

            case R.id.img_Audit_success:

                finish();

                break;

        }
    }











}

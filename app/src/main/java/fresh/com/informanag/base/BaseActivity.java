package fresh.com.informanag.base;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.andsync.xpermission.XPermissionUtils;

import java.io.IOException;
import java.lang.reflect.Method;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import fresh.com.informanag.App.MyApp;
import fresh.com.informanag.R;
import fresh.com.informanag.util.ActivityCollector;
import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.Callback;
import okhttp3.Response;
import qiu.niorgai.StatusBarCompat;

import static fresh.com.informanag.App.MyApp.promptDialog;

/**
 * Created by Administrator
 * on 2019/6/6 0006
 */

public abstract class BaseActivity extends AppCompatActivity {


    //    View view_item;
//    去掉阴影，去掉边框
//    android:overScrollMode="never"
//    android:fadingEdge="none"
//    android:scrollbars="none"
//    textview 去掉边框
//    android:includeFontPadding="false"
    private static final String TAG = BaseActivity.class.getSimpleName();

    private Toolbar toolbar_all;
    private int two;
    private int three;
    protected Unbinder mBinder;

    private android.widget.ProgressBar ProgressBar;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        setContentView(getContentViewId());
        StatusBarCompat.setStatusBarColor(this, R.color.colorAccentb);
        Log.d("BaseActivity", getClass().getSimpleName());
//        Knife
        ActivityCollector.addActivity(this);
        findViews();

        initData();

    }

    /**
     * 使用频率高 一般用于Activity初始化界面
     * 例如 onCreate()里。初始化布局就用到setContentView(R.layout.xxxx) 就是初始化页面布局
     */
    @Override
    public void setContentView(int layoutResId) {
        super.setContentView(layoutResId);
        //Butter Knife初始化
        mBinder = ButterKnife.bind(this);
    }

    /**
     * 这个一般用于加载自定义控件，或者系统空间的布局
     */
    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        //Butter Knife初始化
        mBinder = ButterKnife.bind(this);
    }

    /**
     *
     */
    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        //Butter Knife初始化
        mBinder = ButterKnife.bind(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        XPermissionUtils.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public abstract void startActivity(Class<?> clz);

    @Override
    protected void onStart() {
        super.onStart();
        toolbar_all = (Toolbar) findViewById(R.id.toolbar_all);

        if (toolbar_all != null) {

            LinearLayout lin_lin_lin = (LinearLayout) toolbar_all.findViewById(R.id.lin_lin_lin);

            lin_lin_lin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }


        /**
         * 判断是否有Toolbar,并默认显示返回按钮
         */
        if (null != getToolbar() && isShowBacking()) {
            showBack();
        }
    }

    /**
     * this Activity of tool bar.
     * 获取头部.
     *
     * @return support.v7.widget.Toolbar.
     */
    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    /**
     * 版本号小于21的后退按钮图片
     */
    private void showBack() {
        //setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
        getToolbar().setNavigationIcon(R.mipmap.toolbar_back);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * 是否显示后退按钮,默认显示,可在子类重写该方法.
     *
     * @return
     */
    protected boolean isShowBacking() {
        return true;
    }


    public void showLoadingDialog() {


        if (promptDialog == null) {
            promptDialog = new PromptDialog(this);
            promptDialog.showLoading("加载中");
        }


    }

    public void dismissLoadingDialog() {

        if (promptDialog != null) {
            promptDialog.dismiss();
            promptDialog=null;
        }


    }


    public abstract int getContentViewId();

    public abstract void initData();

    /**
     * 初始化 Priority 1
     */
    protected abstract void findViews();

    @Override
    protected void onDestroy() {
        mBinder.unbind();
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }


    //    获取请求 返回的  数据
    public void StringResulit(String key, String value) {

//        try {
//            beab_pass = MyApp.gson.fromJson(value,Beab_pass.class);
//
//            code = beab_pass.getCode();
//
//            msg = beab_pass.getMsg();
//
//            if (code == 5){
//
//                MyApp.uesr_id = "";
//                MyApp.phone = "";
//                MyApp.token = "";
//                MyApp.clear(this);
//
//                Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(this,LoginAct.class);
//
//                startActivity(intent);
//
//            }else {
//
//            }
//        }catch (Exception e){
//
//        }

    }

    //    请求 返回的 数据
    public void getdata(final String key) {

//        view_item.setVisibility(View.VISIBLE);
//        customDialog.dismiss();//消失

        MyApp.call.enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {

                final String resp = response.body().string();

                Log.i(key, resp);

                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

//                            MdFTion.mBottomSheetPop.dismiss();

                            StringResulit(key, resp);
                        }
                    });
                } catch (Exception e) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "数据有误", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
//                StringResulit(resp);
            }
        });
//                StringResulit("sadsaassda");
    }
}

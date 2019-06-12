package fresh.com.informanag.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import fresh.com.informanag.App.MyApp;
import fresh.com.informanag.R;
import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.Callback;
import okhttp3.Response;

import static fresh.com.informanag.App.MyApp.promptDialog;

/**
 * Created by Administrator
 * on 2019/6/6 0006
 */
public abstract class BaseFragment extends Fragment {
    public void showLoadingDialog() {


        if (promptDialog == null) {
            promptDialog = new PromptDialog(getActivity());
            promptDialog.showLoading("加载中");
        }


    }

    public void dismissLoadingDialog() {

        if (promptDialog != null) {
            promptDialog.dismiss();
        }


    }

    private Unbinder unbinder;

    protected Activity mActivity;
    protected View view;

    AppCompatActivity mAppCompatActivity;


    private TextView mToolbarTitle;
    private TextView mToolbarSubTitle;
    private Toolbar mToolbar;


    /**
     * 获得全局的，防止使用getActivity()为空
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }

    @Override
    public void onDestroyView() {

        if (unbinder != null) {
            unbinder.unbind();
        }

        super.onDestroyView();
        view = null;
    }



    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(mActivity)
                .inflate(getLayoutId(), container, false);

        unbinder = ButterKnife.bind(this, view);

        initView(view, savedInstanceState);


        //返回一个Unbinder值（进行解绑），注意这里的this不能使用getActivity()
//        unbinder = ButterKnife.bind(this, view);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_all);
        if (toolbar != null) {
            LinearLayout lin_lin_lin = (LinearLayout) toolbar.findViewById(R.id.lin_lin_lin);
            lin_lin_lin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                }
            });
        }
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 该抽象方法就是 onCreateView中需要的layoutID
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 该抽象方法就是 初始化view
     *
     * @param view
     * @param savedInstanceState
     */
    protected abstract void initView(View view, Bundle savedInstanceState);

    /**
     * 执行数据的加载
     */
    protected abstract void initData();

    public abstract void startActivity(Class<?> clz);



    @Override
    public void onStart() {

        //调用配置
//        init();
        super.onStart();
    }




    /**
     * 获取头部标题的TextView
     *
     * @return
     */
    public TextView getToolbarTitle() {
        return mToolbarTitle;
    }

    /**
     * 获取头部标题的TextView
     *
     * @return
     */
    public TextView getSubTitle() {
        return mToolbarSubTitle;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }








    //    获取请求 返回的  数据
    public void StringResulit(String key,String value)  {

//        try {
//            beab_pass = MyApp.gson.fromJson(value,Beab_pass.class);
//
//            code = beab_pass.getCode();
//            msg = beab_pass.getMsg();
//
//            if (code == 5){
//
//
//                MyApp.uesr_id = "";
//                MyApp.phone = "";
//                MyApp.token = "";
//                MyApp.clear(getActivity());
//
//
//                Intent intent = new Intent(getActivity(),LoginAct.class);
//
//                startActivity(intent);
//
//                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
//            }else {
//
//
//            }
//        }catch (Exception e){
//
//        }
    }

    //    请求 返回的 数据
    public void getdata(final String key) {


        MyApp.call.enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {

                final String resp = response.body().string();

                Log.i(key, resp);

                try {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            StringResulit(key,resp);

                        }
                    });
                }catch (Exception e){

//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getActivity(),"数据有误",Toast.LENGTH_SHORT).show();
//                        }
//                    });

                }
//                StringResulit(resp);
            }
        });
//                StringResulit("sadsaassda");
    }
}

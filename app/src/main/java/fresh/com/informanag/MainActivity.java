package fresh.com.informanag;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import fresh.com.informanag.base.BaseActivity;
import fresh.com.informanag.ui.frag.fragHome;
import fresh.com.informanag.ui.frag.fragMe;
import fresh.com.informanag.ui.frag.fragOrder;
import fresh.com.informanag.util.HttpUtil;
import fresh.com.informanag.view.BottomBar;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.functions.Action1;

import static fresh.com.informanag.App.MyApp.PUBLIC_URL_shop;
import static fresh.com.informanag.App.MyApp.bottomBar;
import static fresh.com.informanag.App.MyApp.gson;
import static fresh.com.informanag.App.MyApp.mOkHttpClient;


public class MainActivity extends BaseActivity {

    //SDK在Android 6.0下需要进行运行检测的权限如下：
    String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_SETTINGS
    };

    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void initData() {

//        post_yanzhengma();


        //Settings.System.canWrite(MainActivity.this)检测是否拥有写入系统权限的权限
//        if (!Settings.System.canWrite(MainActivity.this)) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
////            builder.setTitle(R.string.notifyTitle);
//
//            builder.setTitle("您好！");
//
//            builder.setMessage("我们的应用需要您授权\"修改系统设置\"的权限,请点击\"设置\"确认开启");
//
//            // 拒绝, 退出应用
////            builder.setNegativeButton(R.string.cancel,
//
//            builder.setNegativeButton("取消",
//
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            finish();
//                        }
//                    });
//
////            builder.setPositiveButton(R.string.setting,
//
//            builder.setPositiveButton("设置",
//
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,
//                                    Uri.parse("package:" + getPackageName()));
//                            startActivityForResult(intent, 1);
//                        }
//                    });
//
//            builder.setCancelable(false);
//
//            builder.show();
//        }


//分别申请多个权限
        RxPermissions.getInstance(MainActivity.this)
                //分别申请多个权限时，使用requestEach
                .requestEach(Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE
//                        ,
//                        Manifest.permission.WRITE_SETTINGS
                )
                .subscribe(new Action1<Permission>() {
                    @Override
                    public void call(Permission permission) {
                        if (permission.name.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                            //当ACCESS_FINE_LOCATION权限获取成功时，permission.granted=true
                            Log.i("permissions", Manifest.permission.ACCESS_FINE_LOCATION + "：" + permission.granted);
                        }
                    }
                });

        bottomBar = findViewById(R.id.bottom_bar);
        bottomBar.setContainer(R.id.fl_container)
                .setTitleBeforeAndAfterColor("#999999", "#ff5d5e")
                .addItem(fragHome.class,
                        "首页",
                        R.drawable.item1_before,
                        R.drawable.item1_after)
                .addItem(fragOrder.class,
                        "订单",
                        R.drawable.item2_before,
                        R.drawable.item2_after)
                .addItem(fragMe.class,
                        "我的",
                        R.drawable.item3_before,
                        R.drawable.item3_after)
                .build();
    }

    @Override
    protected void findViews() {

    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    //  权限 失败 的话 就是  可以  继续 再进行 申请
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //可在此继续其他操作。
    }


    //设置登录密码
    private void post_yanzhengma() {


        HashMap<String, String> map = new HashMap<>();
        map.clear();
        map.put("phone", "18725621750");
        map.put("type", "REGISTER_OR_LOGIN");
//        Toast.makeText(this,"请求了",Toast.LENGTH_SHORT).show();
//        RequestBody formBody = new FormBody.Builder()
//                .add("phone", phone)
//                .add("type", "" + 7)
//                .build();
        Request request = new Request.Builder().
                url(PUBLIC_URL_shop + "code/verifyCode")
                .post(RequestBody.create(MediaType.parse("application/json"), gson.toJson(map)))
                .build();
        okhttp3.Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                String resp = response.body().string();

                Log.i("mainceshi", resp);
            }
        });
    }
}

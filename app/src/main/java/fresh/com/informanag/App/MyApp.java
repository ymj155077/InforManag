package fresh.com.informanag.App;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import fresh.com.informanag.apimanager.CookiesManager;
import fresh.com.informanag.view.BottomBar;
import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Administrator
 * on 2018/11/20 0020
 * <p>
 * <p>
 * recycle_order_right.setHasFixedSize(true);
 * recycle_order_right.setNestedScrollingEnabled(false);
 */
public class MyApp extends MultiDexApplication {


    public static BottomBar bottomBar;




    //    商户号
    public static String merchantNo = "";


    //随机数
    public static String ran_number = "";
    //时间戳
    public static String Time_stamp = "";


    public static String mig_url = "";

    public static String mig_url_two = "";

    public static String page = "1";

    public static String pageSize = "10";


    //    用户鉴权
    public static String access_token = "";
    //    签名
    public static String sign = "";
    //    请求时间
    public static String timestamp = "";
    //    随机数
    public static String nonce = "";
    //    用户号
    public static String user = "0";

    public static PromptDialog promptDialog;

    public static int login_ceshi = 1;

    public static Gson gson;

    public static String push_RegistrationID = "";

    public static OkHttpClient mOkHttpClient = new OkHttpClient();

    public static OkHttpClient.Builder Cookiebuilder = new OkHttpClient.Builder();
    public static Context context;
    public static Call call;
//    public static String PUBLIC_URL_shop = "http://192.168.1.181:8091/";

    public static String PUBLIC_URL_shop = "https://api.ejinlegou.com/";


    public static String url = "https://img3.duitang.com/uploads/item/201606/04/20160604192444_tnShM.jpeg";

    public void onCreate() {
        super.onCreate();
        //获取Context
        context = getApplicationContext();

//      AutoLayoutConifg.getInstance().useDeviceSize();
        mOkHttpClient.cookieJar();
        gson = new Gson();

        File sdcache = getExternalCacheDir();

        long maxCacheSize = 100 * 1024 * 1024;


        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        Cookiebuilder.cookieJar(new CookiesManager())

//                .addNetworkInterceptor(new CacheInterceptor())

                .addNetworkInterceptor(logInterceptor)
                .cache(new Cache(sdcache.getAbsoluteFile(), maxCacheSize))
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
//                .addInterceptor(loggingInterceptor)
                .build();


        user = (String) MyApp.getSharedPreference(context, "user", "");

        access_token = (String) MyApp.getSharedPreference(context, "access_token", "");


    }

    //返回
    public static Context getContextObject() {
        return context;
    }

    public class CacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            Response response1 = response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    //cache for 30 days
                    .header("Cache-Control", "max-age=" + 3600 * 24 * 30)
                    .build();
            return response1;
        }
    }


    public class HttpLogger implements HttpLoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            Log.d("HttpLogInfo", message);
        }
    }


    //存储
    public static void putSharedPreference(Context context, String key, Object object) {
        SharedPreferences sp = context.getSharedPreferences("jinLife", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.commit();
    }


    //获取
    public static Object getSharedPreference(Context context, String key, Object defaultObject) {
        SharedPreferences sp = context.getSharedPreferences("jinLife", Context.MODE_PRIVATE);
        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }
        return null;
    }


    //清除
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences("jinLife", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }


}

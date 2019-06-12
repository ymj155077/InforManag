package fresh.com.informanag.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import fresh.com.informanag.App.MyApp;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import static fresh.com.informanag.App.MyApp.Cookiebuilder;
import static fresh.com.informanag.App.MyApp.PUBLIC_URL_shop;
import static fresh.com.informanag.App.MyApp.Time_stamp;
import static fresh.com.informanag.App.MyApp.gson;
import static fresh.com.informanag.App.MyApp.ran_number;
import static fresh.com.informanag.App.MyApp.sign;


/**
 * Created by Administrator
 * on 2018/11/22 0022
 */
public class HttpUtil {


    public static Map<String, String> params;

    public static void addMapparams() {
        params = new HashMap<>();
        params.clear();
    }


    public static Map<String, String> paramsheads;


    public static void addMapheads() {
        paramsheads = new HashMap<>();
        paramsheads.clear();
    }

    public static void post_Life(String url, Map<String, String> params) {

        Request request;
        if (MyApp.getSharedPreference(MyApp.context, "user", "").equals("")) {
            request = new Request.Builder().url(PUBLIC_URL_shop + url)
                    .post(RequestBody.create(MediaType.parse("application/json"), gson.toJson(params)))
                    .build();
        } else {
//            登录了的
            ran_number = HttpUtil.getNumberString();
//
            Time_stamp = HttpUtil.getSecondTimestamp();
//
            sign = HttpUtil.getSignature(ran_number,
                    Time_stamp,
                    "hello");


            Log.i("Time_stamp",ran_number+"==="+Time_stamp+"---"+MyApp.access_token+"---"+MyApp.user+"---"+sign);

            HttpUtil.addMapheads();
            HttpUtil.paramsheads.put("access-token", MyApp.access_token);
            HttpUtil.paramsheads.put("sign", sign);
            HttpUtil.paramsheads.put("timestamp", Time_stamp);
            HttpUtil.paramsheads.put("nonce", ran_number);
            HttpUtil.paramsheads.put("user", MyApp.user);
            request = new Request.Builder().url(PUBLIC_URL_shop + url)
                    .post(RequestBody.create(MediaType.parse("application/json"), gson.toJson(params)))
                    .headers(Headers.of(paramsheads))
                    .build();
        }


//        if (paramsheads!=null){
//            for (String keys : paramsheads.keySet()) {
//                request.newBuilder().addHeader(keys, paramsheads.get(keys));
//            }
//        }
//        request.newBuilder().put(RequestBody.create(MediaType.parse("application/json"),gson.toJson(params)));


        MyApp.call = Cookiebuilder.build().newCall(request);
    }

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


//    ran_number = HttpUtil.getNumberString();
//
//    Time_stamp = HttpUtil.getSecondTimestamp();
//
//    sign =  HttpUtil.sortString(ran_number,
//    Time_stamp,
//            "hello");


    //    得到 sign
    public static String getSign(String NumberString, String SecondTimestamp) {


        return HttpUtil.sortString(NumberString,
                SecondTimestamp,
                "hello");

    }


    public static String sortString(String timestamp, String nonce, String key) {
        //对token,timestamp nonce 按字典排序
        String[] paramArr = new String[]{key, timestamp, nonce};
        Arrays.sort(paramArr);
        //将排序后的结果拼接成一个字符串
        return paramArr[0].concat(paramArr[1]).concat(paramArr[2]);
    }

    /**
     * 生成签名 android使用
     *
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @return
     */
    public static String getSignature(String timestamp, String nonce, String key) {
        String content = sortString(timestamp, nonce, key);
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        String ciphertext = null;

        try {
            //对拼接后的字符串进行sha1加密 update
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // 使用指定的字节数组对摘要进行最后更新
            byte[] digest = md.digest(content.getBytes());
            //完成摘要计算
            ciphertext = byteToStr(digest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ciphertext;
    }

    /**
     * 将字节数组转换成十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }

//获取时间蹉

//    方法一：通过String.substring()方法将最后的三位去掉

    /**
     * 获取精确到秒的时间戳
     *
     * @return
     */
    public static String getSecondTimestamp() {
        String timeStampSec = System.currentTimeMillis()+"";
//        String timestamp = String.format("%010d", timeStampSec);
        return timeStampSec;
    }

//获取  随机数

    public static String getNumberString() {

        String ran = Math.random() * 100 + "";

        return ran;

    }

}

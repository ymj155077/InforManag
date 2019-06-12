package fresh.com.informanag.util;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;
import fresh.com.informanag.R;

/**
 * Created by Administrator
 * on 2019/5/28 0028
 */
public class Tool {

//获取时间蹉

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014年06月14日16时09分00秒"）
     *
     * @param time
     * @return
     */
    public static String times(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }



    // 验证手机号是否为正确手机号
    public static boolean isMobileNO(String mobiles) {

        Pattern p = Pattern
                .compile("^(0|86|17951)?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$");

        Matcher m = p.matcher(mobiles);

        return m.matches();
    }




    public static void setTips(Context context,String contentText) {
        new SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
//                设置标题
                .setTitleText("万仓生活!")
//                设置内容
//                .setContentText("请选中协议！")
                .setTitleText(contentText)
//                设置图标
                .setCustomImage(R.drawable.tishi)
                .show();
    }



    public static void fraJson(String response,Object object){



    }


//    方法一：通过String.substring()方法将最后的三位去掉
    /**
     * 获取精确到秒的时间戳
     * @return
     */
    public static int getTimestamp(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0,length-3));
        } else {
            return 0;
        }
    }





}

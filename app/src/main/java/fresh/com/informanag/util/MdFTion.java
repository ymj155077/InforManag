package fresh.com.informanag.util;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import fresh.com.informanag.App.MyApp;
import fresh.com.informanag.R;

/**
 * Created by Administrator
 * on 2019/6/4 0004
 */
public class MdFTion {




//    判断  是否 登录
    public static String login(Context context){

        String user = (String) MyApp.getSharedPreference(context, "user", "");

        return user;

    }





    public static PopupWindow mBottomSheetPop;


    //全局的自定义的弹窗
    public static View setRebuildPop(Context context,

//    当前pop的布局
                                     @LayoutRes int layoutResID_pop,
//    当前activity的布局
                                     @LayoutRes int layoutResID_act
    ) {

        View view = LayoutInflater.from(context)
                .inflate(layoutResID_pop, null);
        //设置contentView
        mBottomSheetPop = new PopupWindow(view,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT, true);
        mBottomSheetPop.setContentView(view);
        //显示PopupWindow  act_hotel_book_layout
        View rootview = LayoutInflater.from(context).inflate(layoutResID_act, null);
        mBottomSheetPop.showAtLocation(rootview, Gravity.CENTER_VERTICAL, 0, 0);
        view.setFocusable(true);//comment by danielinbiti,设置view能够接听事件，标注1
        view.setFocusableInTouchMode(true); //comment by danielinbiti,设置view能够接听事件 标注2
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
                if (arg1 == KeyEvent.KEYCODE_BACK) {
                    if (mBottomSheetPop != null) {
                        mBottomSheetPop.dismiss();
                    }
                }
                return false;
            }
        });
//        点击取消
        LinearLayout lin_pop_type = (LinearLayout) view.findViewById(R.id.lin_pop_type);
        lin_pop_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetPop.dismiss();
            }
        });
        return view;
    }














}

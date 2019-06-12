package fresh.com.informanag.util;

import android.support.v4.app.Fragment;

import fresh.com.informanag.ui.frag.bill.fragAllIncome;
import fresh.com.informanag.ui.frag.bill.fragSettlement;
import fresh.com.informanag.ui.frag.evaluate.fragAll;
import fresh.com.informanag.ui.frag.evaluate.fragNoRes;
import fresh.com.informanag.ui.frag.evaluate.fragReplied;
import fresh.com.informanag.ui.frag.order.FragAllOrders;
import fresh.com.informanag.ui.frag.order.FragCollBe;
import fresh.com.informanag.ui.frag.order.FragGenEva;
import fresh.com.informanag.ui.frag.order.FragGenUse;
import fresh.com.informanag.ui.frag.order.FragSubPay;



/**
 * Created by shan_yao on 2016/6/17.
 */
public class FragmentFactory {
    public static Fragment createForNoExpand(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragAllOrders();
                break;
            case 1:
                fragment = new FragSubPay();
                break;
            case 2:
                fragment = new FragGenUse();
                break;

            case 3:
//              代收货
                fragment = new FragCollBe();
                break;

            case 4:
                fragment = new FragGenEva();
                break;
        }
        return fragment;
    }


//商家  管理  账单
    public static Fragment createbill(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new fragAllIncome();
                break;
            case 1:
                fragment = new fragSettlement();
                break;
        }
        return fragment;
    }





//    评价管理


    public static Fragment createEvalman(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new fragNoRes();
                break;
            case 1:
                fragment = new fragReplied();
                break;
            case 2:
                fragment = new fragAll();
                break;
        }
        return fragment;
    }












}

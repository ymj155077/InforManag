package fresh.com.informanag.ui.frag;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import fresh.com.informanag.App.MyApp;
import fresh.com.informanag.R;
import fresh.com.informanag.base.BaseFragment;
import fresh.com.informanag.util.CommonUtils;
import fresh.com.informanag.util.FragmentFactory;
import fresh.com.informanag.view.tabpagerindictor.TabPageIndicator;
import qiu.niorgai.StatusBarCompat;


/**
 * Created by Administrator
 * on 2019/5/13 0013
 */
public class fragOrder extends BaseFragment {

    private TabPageIndicator indicator;
    private ViewPager viewPager;


    private Toolbar toolbar_all;


    @Override
    protected int getLayoutId() {
        return R.layout.frag_order;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        indicator = (TabPageIndicator) view.findViewById(R.id.indicator);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        toolbar_all = (Toolbar) view.findViewById(R.id.toolbar_all);
        toolbar_all.setBackgroundColor(Color.parseColor("#f2f2f2"));

        TextView text_center = (TextView) toolbar_all.findViewById(R.id.toolbar_center);
        text_center.setText("我的订单");
//        StatusBarCompat.setStatusBarColor(getActivity(), getActivity().getResources().getColor(R.color.Write), 0);


        LinearLayout lin_lin_lin = (LinearLayout) view.findViewById(R.id.lin_lin_lin);

        lin_lin_lin.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void initData() {

        BasePagerAdapter adapter = new BasePagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        setTabPagerIndicator();
    }


    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(getActivity(), clz));
    }

    private void setTabPagerIndicator() {

        // 设置模式，一定要先设置模式
        indicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_NOWEIGHT_EXPAND_SAME);
//设置分割线高度
        indicator.setIndicatorHeight(8);

//  设置右侧 颜色
        indicator.setDividerColor(Color.parseColor("#f2f2f2"));// 设置分割线的颜色
        indicator.setDividerPadding(CommonUtils.dip2px(MyApp.getContextObject(), 10));
        indicator.setIndicatorColor(Color.parseColor("#FF5B05"));// 设置底部导航线的颜色 f2f2f2
        indicator.setTextColorSelected(Color.parseColor("#606368"));// 设置tab标题选中的颜色
        indicator.setTextColor(Color.parseColor("#606368"));// 设置tab标题未被选中的颜色
        indicator.setTextSize(CommonUtils.sp2px(MyApp.getContextObject(), 16));// 设置字体大小
//设置最下面一条的横线的颜色
        indicator.setUnderlineColor(Color.parseColor("#f2f2f2"));
    }


    class BasePagerAdapter extends FragmentPagerAdapter {
        String[] titles = new String[]{"全部","待付款","待发货","待收货","待使用"};

        public BasePagerAdapter(FragmentManager fm) {
            super(fm);
//            this.titles = CommonUtils.getStringArray(R.array.no_expand_titles);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.createForNoExpand(position);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }


}

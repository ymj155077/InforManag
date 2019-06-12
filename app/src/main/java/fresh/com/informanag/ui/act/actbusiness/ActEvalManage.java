package fresh.com.informanag.ui.act.actbusiness;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import fresh.com.informanag.App.MyApp;
import fresh.com.informanag.R;
import fresh.com.informanag.base.BaseActivity;
import fresh.com.informanag.util.CommonUtils;
import fresh.com.informanag.util.FragmentFactory;
import fresh.com.informanag.view.tabpagerindictor.TabPageIndicator;


//评价管理

public class ActEvalManage extends BaseActivity {

    private TabPageIndicator indicator_eval;
    private ViewPager viewPager_eval;
    @BindView(R.id.toolbar_all)
    Toolbar toolbar_all;
    private String merchantNo;

    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this,clz));
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_act_eval_manage;
    }

    @Override
    protected void findViews() {
        toolbar_all.setBackgroundColor(Color.parseColor("#f2f2f2"));
        TextView text_center = (TextView) toolbar_all.findViewById(R.id.toolbar_center);
        text_center.setText("评价管理");

        indicator_eval = (TabPageIndicator)findViewById(R.id.indicator_eval);
        viewPager_eval = (ViewPager)findViewById(R.id.viewPager_eval);    }

    @Override
    public void initData() {


        Intent intent = getIntent();

        merchantNo = intent.getStringExtra("merchantNo");

        BasePagerAdapter adapter = new BasePagerAdapter(getSupportFragmentManager());
        viewPager_eval.setAdapter(adapter);
        indicator_eval.setViewPager(viewPager_eval);
        setTabPagerIndicator();
    }


    private void setTabPagerIndicator() {

        // 设置模式，一定要先设置模式     MODE_NOWEIGHT_EXPAND_SAME
        indicator_eval.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_WEIGHT_NOEXPAND_SAME);
//设置分割线高度
        indicator_eval.setIndicatorHeight(8);
//  设置右侧 颜色
        indicator_eval.setDividerColor(Color.parseColor("#ffffff"));// 设置分割线的颜色
        indicator_eval.setDividerPadding(CommonUtils.dip2px(MyApp.getContextObject(), 30));
        indicator_eval.setIndicatorColor(Color.parseColor("#FF5B05"));// 设置底部导航线的颜色 f2f2f2
        indicator_eval.setTextColorSelected(Color.parseColor("#606368"));// 设置tab标题选中的颜色
        indicator_eval.setTextColor(Color.parseColor("#606368"));// 设置tab标题未被选中的颜色
        indicator_eval.setTextSize(CommonUtils.sp2px(MyApp.getContextObject(), 16));// 设置字体大小
//设置最下面一条的横线的颜色
        indicator_eval.setUnderlineColor(Color.parseColor("#f2f2f2"));
    }


    class BasePagerAdapter extends FragmentPagerAdapter {

        String[] titles = new String[]{"未回复","已回复","全部"};

        public BasePagerAdapter(FragmentManager fm) {
            super(fm);
//            CommonUtils.getStringArray(R.array.no_expand_titles)
//            this.titles = String[]{};
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.createEvalman(position);
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

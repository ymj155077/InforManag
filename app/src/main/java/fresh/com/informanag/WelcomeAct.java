package fresh.com.informanag;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnPageChangeListener;

import java.util.ArrayList;

import butterknife.Unbinder;

public class WelcomeAct extends Activity {

    Unbinder unbinder;
    private ArrayList<Integer> arrayList;
    private ConvenientBanner cbTest;
    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        uiFlags |= 0x00001000;
        getWindow().getDecorView().setSystemUiVisibility(uiFlags);
        setContentView(R.layout.activity_welcome);
        Log.d("BaseActivity", getClass().getSimpleName());
//        Knife
//        String key = (String) getSharedPreference(WelcomeAct.this, "welcome", "");
//        Log.i("keykeykeykey", key);
//        if (key != null && key.equals("keykeykeykey")) {
            startActivity(new Intent(this,MainActivity.class));
            finish();
//        } else {
//            putSharedPreference(WelcomeAct.this, "welcome", "keykeykeykey");
//        }
        arrayList = new ArrayList<>();
        arrayList.add(R.drawable.indicator1);
        arrayList.add(R.drawable.indicator1);
        arrayList.add(R.drawable.indicator1);
        initGuidePage();
    }




    private void initGuidePage() {

//        @BindView(R.id.cb_test)
//        ConvenientBanner cbTest;

        cbTest = (ConvenientBanner)findViewById(R.id.cb_test);

//        @BindView(R.id.btn_test)
//        Button btnTest;

        btnTest = (Button) findViewById(R.id.btn_test);

        cbTest.setPages(new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View itemView) {
                return new LocalImageHolderView(itemView);
            }

            @Override
            public int getLayoutId() {
                //设置加载哪个布局
                return R.layout.item_guide_page;
            }

        }, arrayList)
//                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setPointViewVisible(true)
                .setCanLoop(false)
                .setOnPageChangeListener(new OnPageChangeListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                    }

                    @Override
                    public void onPageSelected(int index) {
                        //总共添加了三张图片，如果index为2表示到了最后一张图片，隐藏下面的指示器，显示跳转到主页的按钮
//                        if (index == 2) {
//                            btnTest.setVisibility(View.VISIBLE);
//                            cbTest.setPointViewVisible(false);
//                        } else {
//                            btnTest.setVisibility(View.GONE);
//                            cbTest.setPointViewVisible(true);
//                        }
//                        btnTest.setVisibility(View.GONE);
//                        cbTest.setPointViewVisible(false);
                        if (index == 2) {
                            btnTest.setVisibility(View.VISIBLE);
                        } else {
                            btnTest.setVisibility(View.GONE);
                            cbTest.setPointViewVisible(false);
                        }
                    }
                });



        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到主activity
                Intent intent = new Intent(WelcomeAct.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }

    /**
     * 轮播图2 对应的holder
     */
    public class LocalImageHolderView extends Holder<Integer> {
        private ImageView mImageView;

        //构造器
        public LocalImageHolderView(View itemView) {
            super(itemView);
        }

        @Override
        protected void initView(View itemView) {
            mImageView = itemView.findViewById(R.id.iv_guide_page);
            mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        @Override
        public void updateUI(Integer data) {
            mImageView.setImageResource(data);
        }
    }

}

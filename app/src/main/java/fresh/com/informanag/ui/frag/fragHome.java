package fresh.com.informanag.ui.frag;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import fresh.com.informanag.R;
import fresh.com.informanag.base.BaseFragment;
import fresh.com.informanag.ui.act.acthome.ActGaoMap;
import fresh.com.informanag.util.HttpUtil;
import fresh.com.informanag.util.MdFTion;
import fresh.com.informanag.util.onekeyshare.OnekeyShare;
import me.leefeng.promptlibrary.PromptDialog;
import qiu.niorgai.StatusBarCompat;

import static fresh.com.informanag.App.MyApp.bottomBar;
import static fresh.com.informanag.App.MyApp.gson;


/**
 * Created by Administrator
 * on 2019/5/13 0013
 */
public class fragHome extends BaseFragment {


    private String share_img = "http://bpic.588ku.com/element_origin_min_pic/18/06/10/cd64b78e84917a726fa3a0b2856a8827.jpg";


    @BindView(R.id.lin_search)
    LinearLayout lin_search;


    @BindView(R.id.fra_home)
    FrameLayout fra_home;


    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {

            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {


                    aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    aMapLocation.getLatitude();//获取纬度
                    aMapLocation.getLongitude();//获取经度
                    aMapLocation.getAccuracy();//获取精度信息
                    aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    aMapLocation.getCountry();//国家信息
                    aMapLocation.getProvince();//省信息
                    aMapLocation.getCity();//城市信息
                    aMapLocation.getDistrict();//城区信息
                    aMapLocation.getStreet();//街道信息
                    aMapLocation.getStreetNum();//街道门牌号信息
                    aMapLocation.getCityCode();//城市编码
                    aMapLocation.getAdCode();//地区编码
                    aMapLocation.getAoiName();//获取当前定位点的AOI信息
                    aMapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                    aMapLocation.getFloor();//获取当前室内定位的楼层
                    aMapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
//获取定位时间
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(aMapLocation.getTime());
                    df.format(date);


//可在其中解析amapLocation获取相应内容。
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }

        }
    };

    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;


    @BindView(R.id.img_home)
    ImageView img_home;


    @BindView(R.id.lin_current)
    LinearLayout lin_current;

    private static final String APP_CACHE_DIRNAME = "/webcache"; // web缓存目录
    //
    private WebView web_view;
//    private UltimateRefreshView mUltimateRefreshView;

    @Override
    protected int getLayoutId() {
        return R.layout.home_frag;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        web_view = (WebView) view.findViewById(R.id.web_view);
//        mUltimateRefreshView = (UltimateRefreshView) view.findViewById(R.id.refreshView);

//        StatusBarCompat.setStatusBarColor(getActivity(), getActivity().getResources().getColor(R.color.color_title), 0);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initData() {






//        Glide.with(getActivity()).load("Http://imgs.ejinlegou.com/goods/15601677601559478773752.jpg").into(img_home);
//        web_view.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
////              Log.e("Test", "url....=" + url);
//                return true;
//            }
//            @Override
//            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                super.onReceivedSslError(view, handler, error);
//                handler.proceed();
//            }
//        });
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            web_view.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
//        } else {
//            web_view.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
//        }
//        WebSettings settings = web_view.getSettings();
//        settings.setJavaScriptEnabled(true);
//
//        //Android 私有缓存存储，如果你不调用setAppCachePath方法，WebView将不会产生这个目录。
//        settings.setAppCachePath(getActivity().getCacheDir().getAbsolutePath());
////设置是否启用缓存，不过需要设置好缓存路径，默认false
//        settings.setAppCacheEnabled(true);
////        ----------------  第二种 方式
//        //获取WebSetting对象
        final WebSettings settings = web_view.getSettings();
        settings.setJavaScriptEnabled(true);//支持js脚本


        // 设置允许JS弹窗
        settings.setJavaScriptCanOpenWindowsAutomatically(true);


        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);//设置渲染优先级
        //隐藏缩放控件
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        //禁止缩放
        settings.setSupportZoom(false);
        //文件权限
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);
        //缓存相关
        if (HttpUtil.isNetworkConnected(getActivity())) {
            //有网络，则加载网络地址
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);//设置缓存模式LOAD_CACHE_ELSE_NETWORK
        } else {
            //无网络，则加载缓存路径
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        settings.setDomStorageEnabled(true);//开启DOM storage API功能
        settings.setDatabaseEnabled(true);//开启database storeage API功能
        String cacheDirPath = getActivity().getFilesDir().getAbsolutePath() + "/webcache";//缓存路径
        settings.setDatabasePath(cacheDirPath);//设置数据库缓存路径
        settings.setAppCachePath(cacheDirPath);//设置AppCaches缓存路径
        settings.setAppCacheEnabled(true);//开启AppCaches功能
        settings.setBlockNetworkImage(false);//解决图片不显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }


        //方式1. 加载一个网页：

//

//        web_view.loadUrl("http://192.168.1.109:5000/#/");


//        web_view.loadUrl("http://139.224.65.28/index.html");

        web_view.loadUrl("https://api.ejinlegou.com");

//        web_view.loadUrl("http://192.168.1.122:5000/#/");


        /*** 打开js接口給H5调用，参数1为本地类名，参数2为别名；h5用window.别名.类名里的方法名才能调用方法里面的内容，例如：window.android.back();
         * */
        web_view.addJavascriptInterface(new AndroidAndJSInterface(), "Android");

//        web_view.setWebViewClient(new WebViewClient() {
//            //设置在webView点击打开的新网页在当前界面显示,而不跳转到新的浏览器中
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
//        WebSettings settings = web_view.getSettings();
//        settings.setJavaScriptEnabled(true);
////        //设置缓存模式
//        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
////        // 开启DOM storage API 功能
//        settings.setDomStorageEnabled(true);
////        // 开启database storage API功能
//        settings.setDatabaseEnabled(true);
//        String cacheDirPath = getActivity().getFilesDir().getAbsolutePath() + APP_CACHE_DIRNAME;
//        Log.i("cachePath", cacheDirPath);
//        设置数据库缓存路径
//        settings.setAppCachePath(cacheDirPath);
//        settings.setAppCacheEnabled(true);
//        mUltimateRefreshView.setBaseHeaderAdapter(new JDAppHeaderAdpater(getActivity()));
//        mUltimateRefreshView.setBaseFooterAdapter(new JDAppFooterAdapter(getActivity()));
//        mUltimateRefreshView.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
//            @Override
//            public void onHeaderRefresh(UltimateRefreshView view) {
//                showLoadingDialog();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                    刷新 webview
//                        web_view.reload();
//                        mUltimateRefreshView.onHeaderRefreshComplete();
//                        dismissLoadingDialog();
//                    }
//                }, 2000);
//            }
//        });
//        mUltimateRefreshView.setOnFooterRefreshListener(new OnFooterRefreshListener() {
//            @Override
//            public void onFooterRefresh(UltimateRefreshView view) {
//                showLoadingDialog();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mUltimateRefreshView.onFooterRefreshComplete();
//                        dismissLoadingDialog();
//                    }
//                }, 2000);
//            }
//        });
        // 由于设置了弹窗检验调用结果,所以需要支持js对话框
        // webview只是载体，内容的渲染需要使用webviewChromClient类去实现
        // 通过设置WebChromeClient对象处理JavaScript的对话框
        //设置响应js 的Alert()函数
        web_view.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }
        });

        web_view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    //按返回键操作并且能回退网页
                    if (keyCode == KeyEvent.KEYCODE_BACK && web_view.canGoBack()) {
                        //后退
                        web_view.goBack();
//                        Intent intent = new Intent(Mai, SecondAct.class);
//                        intent.putExtra("sad", "asdas");
//                        startActivity(intent);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(getActivity(), clz));
    }

    /**
     * 自己写一个类，里面是提供给H5访问的方法
     */

    public class AndroidAndJSInterface {
        @JavascriptInterface//一定要写，不然H5调不到这个方法
        public String back(final String key) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.i("getActivity", key + "--");
//                    if (key.equals("home")) {
//                        Toast.makeText(getActivity(), "显示", Toast.LENGTH_SHORT).show();
                        bottomBar.setVisibility(View.VISIBLE);
//                bottomBar.notify();
//                    } else {
//                        bottomBar.setVisibility(View.GONE);
//                    }
//            Toast.makeText(getActivity(), "调用了android的方法!!!" + key, Toast.LENGTH_SHORT).show();
                }
            });
            return "我是java里的方法返回值" + key;
        }

        @JavascriptInterface//一定要写，不然H5调不到这个方法
        public String share(final String[] keyone, final double keytwo) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    StringBuffer sb = new StringBuffer();

                    for (int i = 0; i < keyone.length; i++) {
                        sb.append(keyone[i]);        //append String并不拥有该方法，所以借助StringBuffer
                    }
                    String sb1 = sb.toString();
                    System.out.println(sb1 + ",");    //0123sb12f
                    Log.i("key", sb1 + "----");
                    Log.i("key", keytwo + "----");
//                    Toast.makeText(getActivity(),"分享！！！",Toast.LENGTH_SHORT).show();



                    setUpPop(keyone[0], keyone[1], keytwo + "");

                }
            });
            return "万仓生活分享";
        }





        @JavascriptInterface//一定要写，不然H5调不到这个方法
        public String hide(final String keyone) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.i("sadsadsa",keyone);
                    bottomBar.setVisibility(View.GONE);
                }
            });
            return "万仓生活分享";
        }
















    }

    @OnClick({R.id.lin_current, R.id.lin_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_current:
                startActivity(ActGaoMap.class);
                break;
            case R.id.lin_search:
//                web_view.loadUrl("javascript:goSearchList(" + "'" + "min" + "'" + ")");
                break;
        }
    }


    //    执行该方法时，Fragment处于活动状态，用户可与之交互。
    public void onResume() {
        super.onResume();

//初始化定位
        mLocationClient = new AMapLocationClient(getActivity());
//设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        AMapLocationClientOption option = new AMapLocationClientOption();
        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        if (null != mLocationClient) {
            mLocationClient.setLocationOption(option);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }
//设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);

//获取一次定位结果：
//该方法默认为false。
        mLocationOption.setOnceLocation(true);

//获取最近3s内精度最高的一次定位结果：
//设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);

//设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);


        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
//启动定位
        mLocationClient.startLocation();

    }


    public void onDestroy() {

        super.onDestroy();

        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。

    }


    //    弹窗
    private void setUpPop(String img_veg, String text_n, String text_pr) {
       View view = MdFTion.setRebuildPop(getActivity(), R.layout.layout_pop_vegetables, R.layout.home_frag);

        ImageView img_vegetables = (ImageView) view.findViewById(R.id.img_vegetables);

        TextView text_name = (TextView) view.findViewById(R.id.text_name);

        TextView text_price = (TextView) view.findViewById(R.id.text_price);

        ImageView img_head = (ImageView) view.findViewById(R.id.img_head);

        TextView text_name_boom = (TextView) view.findViewById(R.id.text_name_boom);

        LinearLayout lin_pop_ve = (LinearLayout) view.findViewById(R.id.lin_pop_ve);

        Glide.with(getActivity()).load(img_veg).into(img_vegetables);

        text_name.setText(text_n);

        text_price.setText(text_pr);

//        getViewBp(lin_pop_ve);

//        try {
//            captureView(lin_pop_ve);
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }


        showShare("", "", "", "", "", "", "");
    }

    private void showShare(String Title, String uri, String text, String ImagePath, final String Url_wei, String qq_kongjian, String siteUrl) {
        Log.i("Url_wei", Url_wei);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
//       分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("万仓分享");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(share_img);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("万仓生活:" + "shop_name");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        oks.setImageUrl(share_img + "");


//        oks.setImageData();

//        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
//            @Override
//            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
//                if (platform.getName().equalsIgnoreCase(QQ.NAME)) {
//                    paramsToShare.setText("由你点商家：" + "shop_name");
//                    paramsToShare.setTitle("由你点店铺分享");
//                    paramsToShare.setTitleUrl(share_img);
//                    paramsToShare.setImageUrl(share_img + "");
//                } else if (platform.getName().equalsIgnoreCase(QZone.NAME)) {
//                    paramsToShare.setText("由你点商家：" + "shop_name");
//                    paramsToShare.setTitle("由你点店铺分享");
//                    paramsToShare.setTitleUrl(share_img);
//                    paramsToShare.setImageUrl(share_img + "");
//                }
//            }
//        });
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(share_img);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("万仓生活");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("万仓生活");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(share_img);
        // 启动分享GUI
        oks.show(getActivity());
    }





//诺,主要观察这两个方法

    /**
     * 压缩图片
     *
     * @param bgimage
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static Bitmap zoomImage(Bitmap bgimage, double newWidth, double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        //matrix.postScale(scaleWidth, scaleHeight);//TODO 因为宽高不确定的因素,所以不缩放
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }

    /**
     * 截取指定View为图片
     *
     * @param view
     * @return
     * @throws Throwable
     */
    public  Bitmap captureView(View view) throws Throwable {
        Bitmap bm = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(bm));
        return bm;
    }










}

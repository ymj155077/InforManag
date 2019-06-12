package fresh.com.informanag.ui.act.acthome;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import fresh.com.informanag.R;
import fresh.com.informanag.util.Constants;
import fresh.com.informanag.util.PoiOverlay;
import fresh.com.informanag.util.ToastUtil;
import fresh.com.informanag.view.NoScrollListView;
import qiu.niorgai.StatusBarCompat;


//高德地图定位
public class ActGaoMap extends AppCompatActivity implements
        AMap.OnMarkerClickListener, AMap.InfoWindowAdapter,
        PoiSearch.OnPoiSearchListener, View.OnClickListener,
        Inputtips.InputtipsListener, SearchView.OnQueryTextListener {

    private double getLatitude;//获取纬度
    private double getLongitude;//获取经度
    private String getAddress;
    private TextView text_sue;
    public static final int RESULT_CODE_INPUTTIPS = 101;
    public static final int REQUEST_CODE = 100;
    public static final int RESULT_CODE_KEYWORDS = 102;
    private MapView map_view;
    private AMap aMap;
    //    获取得到  当前 输入框的 数据；
    private String edit_text = "";

    //    搜索  的 按钮
    private LinearLayout lin_serch;


    private String mKeyWords = "";// 要输入的poi搜索关键字
    private ProgressDialog progDialog = null;// 搜索时进度条

    private PoiResult poiResult; // poi返回的结果
    private int currentPage = 1;
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;// POI搜索
    private Marker mPoiMarker;
    @BindView(R.id.text_address)
    TextView text_address;
    private NoScrollListView mInputListView;
    private List<Tip> mCurrentTipList;
    private InputTipsAdapterMap mIntipAdapter;


    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;


    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;


    private SearchView mSearchView;// 输入搜索关键字
    private ImageView mBack;


    private Toolbar toolbar;

//    首页 0

//    商家认证点击进来的 1

//    创建地址 列表 2

    private int mercertifiact;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_address);

        StatusBarCompat.setStatusBarColor(this, R.color.item_ti);

        map_view = (MapView) findViewById(R.id.map_view);
//在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        map_view.onCreate(savedInstanceState);

        initSearchView();

        Intent intent = getIntent();


//    首页 0

//    商家认证点击进来的 1

//    创建地址 列表 2


        mercertifiact = intent.getIntExtra("mercertifiact", 0);






//        int id = mSearchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
//        TextView textView = (TextView) mSearchView.findViewById(id);
//        textView.setTextSize(13);
//        textView.setTextColor(Color.parseColor("#2b2b2b"));


        text_sue = (TextView) findViewById(R.id.text_sue);
        text_sue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                // 获取用户计算后的结果
//                private  double getLatitude;//获取纬度
//                private  double getLongitude;//获取经度
//                intent.putExtra("Longitude", Longitude); //将计算的值回传回去
//                intent.putExtra("Latitude",Latitude);
//                intent.putExtra("Address",Address);
                intent.putExtra("Latitude", getLatitude + "");   //将计算的值回传回去

                intent.putExtra("Longitude", getLongitude + ""); //将计算的值回传回去

                intent.putExtra("Address", getAddress);                //地址信息
                //通过intent对象返回结果，必须要调用一个setResult方法，
                //setResult(resultCode, data);第一个参数表示结果返回码，一般只要大于1就可以，但是
                setResult(2, intent);
                finish(); //结束当前的activity的生命周期
            }
        });


        toolbar = (Toolbar) findViewById(R.id.toolbar_all);

        if (toolbar != null) {

            TextView text_center = toolbar.findViewById(R.id.toolbar_center);
            text_center.setText("请选择收货地址");

            ImageView back_tool = (ImageView) toolbar.findViewById(R.id.back_tool);
            back_tool.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
        lin_serch = (LinearLayout) findViewById(R.id.lin_serch);
        lin_serch.setOnClickListener(this);
        mInputListView = (NoScrollListView) findViewById(R.id.list_map);
//初始化地图控制器对象
        if (aMap == null) {
            aMap = map_view.getMap();
        }
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
//设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
//初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
//设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
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


//        MyLocationStyle myLocationStyle;
//        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);////定位一次，且将视角移动到地图中心点。
////        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。


        //定位的小图标 默认是蓝点 这里自定义一团火，其实就是一张图片
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.tishi));
        myLocationStyle.radiusFillColor(android.R.color.transparent);
        myLocationStyle.strokeColor(android.R.color.transparent);
        aMap.setMyLocationStyle(myLocationStyle);


        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
//aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

        aMap.moveCamera(CameraUpdateFactory.zoomTo(12));//这个数值越大显示的越详细


        aMap.setMapType(AMap.MAP_TYPE_NAVI);

    }

    /**
     * 输入提示activity选择结果后的处理逻辑
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        aMap.clear();
//        mLocationClient.stopLocation();


        aMap.setMyLocationEnabled(true);


        if (resultCode == RESULT_CODE_INPUTTIPS && data
                != null) {
//            aMap.clear();
            Tip tip = data.getParcelableExtra(Constants.EXTRA_TIP);
            if (tip.getPoiID() == null || tip.getPoiID().equals("")) {
                doSearchQuery(tip.getName());
            } else {
                addTipMarker(tip);
            }
            text_address.setText(tip.getName());
            if (!tip.getName().equals("")) {
//                mCleanKeyWords.setVisibility(View.VISIBLE);
            }
        } else if (resultCode == RESULT_CODE_KEYWORDS && data != null) {
//            aMap.clear();
            String keywords = data.getStringExtra(Constants.KEY_WORDS_NAME);
            if (keywords != null && !keywords.equals("")) {
                doSearchQuery(keywords);
            }
            text_address.setText(keywords);
            if (!keywords.equals("")) {
//                mCleanKeyWords.setVisibility(View.VISIBLE);
            }
        }


        if (data != null) {
//            Toast.makeText(MapAct.this, "过来了", Toast.LENGTH_SHORT).show();
            String mSearchView = data.getStringExtra("mSearchView");
            if (!IsEmptyOrNullString(mSearchView)) {
                search(mSearchView);
            }
        }


    }


    /**
     * 设置页面监听
     */
    private void setUpMap() {
        aMap.setOnMarkerClickListener(this);// 添加点击marker监听事件
        aMap.setInfoWindowAdapter(this);// 添加显示infowindow监听事件
        aMap.getUiSettings().setRotateGesturesEnabled(false);
    }


    /**
     * 显示进度框
     */
    private void showProgressDialog() {
        if (progDialog == null)
            progDialog = new ProgressDialog(this);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(false);
        progDialog.setMessage("正在搜索:\n" + mKeyWords);
        progDialog.show();
    }


    /**
     * 隐藏进度框
     */
    private void dissmissProgressDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }


    /**
     * 开始进行poi搜索
     */
    protected void doSearchQuery(String keywords) {
        showProgressDialog();// 显示进度框
        currentPage = 1;
        // 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query = new PoiSearch.Query(keywords, "", Constants.DEFAULT_CITY);
        // 设置每页最多返回多少条poiitem
        query.setPageSize(10);
        // 设置查第一页
        query.setPageNum(currentPage);

        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return false;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public View getInfoWindow(final Marker marker) {
        View view = getLayoutInflater().inflate(R.layout.poikeywordsearch_uri,
                null);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(marker.getTitle());

        TextView snippet = (TextView) view.findViewById(R.id.snippet);
        snippet.setText(marker.getSnippet());
        return view;
    }

    /**
     * poi没有搜索到数据，返回一些推荐城市的信息
     */
    private void showSuggestCity(List<SuggestionCity> cities) {
        String infomation = "推荐城市\n";
        for (int i = 0; i < cities.size(); i++) {
            infomation += "城市名称:" + cities.get(i).getCityName() + "城市区号:"
                    + cities.get(i).getCityCode() + "城市编码:"
                    + cities.get(i).getAdCode() + "\n";
        }
        ToastUtil.show(ActGaoMap.this, infomation);

    }


    /**
     * POI信息查询回调方法
     */
    @Override
    public void onPoiSearched(PoiResult result, int rCode) {
        dissmissProgressDialog();// 隐藏对话框
        if (rCode == 1000) {


//            search(aMapLocation.getCity());


            if (result != null && result.getQuery() != null) {// 搜索poi的结果
                if (result.getQuery().equals(query)) {// 是否是同一条
                    poiResult = result;
                    Log.i("assadsadssddsa", result + "");
                    // 取得搜索到的poiitems有多少页
                    List<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    List<SuggestionCity> suggestionCities = poiResult
                            .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                    if (poiItems != null && poiItems.size() > 0) {
                        aMap.clear();// 清理之前的图标
                        PoiOverlay poiOverlay = new PoiOverlay(aMap, poiItems);
                        poiOverlay.removeFromMap();
                        poiOverlay.addToMap();
                        poiOverlay.zoomToSpan();
                    } else if (suggestionCities != null
                            && suggestionCities.size() > 0) {
                        showSuggestCity(suggestionCities);
                    } else {
                        ToastUtil.show(ActGaoMap.this,
                                "对不起，没有搜索到相关数据！");
                    }
                }
            } else {
                ToastUtil.show(ActGaoMap.this,
                        "对不起，没有搜索到相关数据！");
            }
        } else {
            ToastUtil.showerror(this, rCode);
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem item, int rCode) {
        // TODO Auto-generated method stub

    }

    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {


            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
//可在其中解析amapLocation获取相应内容。

                    aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    getLatitude = aMapLocation.getLatitude();//获取纬度
                    getLongitude = aMapLocation.getLongitude();//获取经度
                    aMapLocation.getAccuracy();//获取精度信息
                    getAddress = aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
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
                    Log.e("AmapsasaError", aMapLocation.getLocationType() + "---" +//获取当前定位结果来源，如网络定位结果，详见定位类型表
                            aMapLocation.getLatitude() + "---" +//获取纬度
                            aMapLocation.getLongitude() + "---" +//获取经度
                            aMapLocation.getAccuracy() + "---" +//获取精度信息
                            aMapLocation.getAddress() + "---" +//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                            aMapLocation.getCountry() + "---" +//国家信息
                            aMapLocation.getProvince() + "---" +//省信息
                            aMapLocation.getCity() + "---" +//城市信息
                            aMapLocation.getDistrict() + "---" +//城区信息
                            aMapLocation.getStreet() + "---" +//街道信息
                            aMapLocation.getStreetNum() + "---" +//街道门牌号信息
                            aMapLocation.getCityCode() + "---" +//城市编码
                            aMapLocation.getAdCode() + "---" +//地区编码
                            aMapLocation.getAoiName() + "---" +//获取当前定位点的AOI信息
                            aMapLocation.getBuildingId() + "---" +//获取当前室内定位的建筑物Id
                            aMapLocation.getFloor() + "---" +//获取当前室内定位的楼层
                            aMapLocation.getGpsAccuracyStatus());//获取GPS的当前状态);
//搜索 城市  信息
                    search(aMapLocation.getAoiName());
                    text_address.setText(aMapLocation.getCity());
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();

//        map_view.onDestroy();
//        if(null != mLocationClient) {
//            mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
//        }

    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
//        map_view.onResume();
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
//        map_view.onPause();
//    }
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
//        map_view.onSaveInstanceState(outState);
//    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_serch:
//              mLocationClient.stopLocation();
//                Intent intent = new Intent(this, InputTipsActivity.class);
//                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }


    /**
     * 用marker展示输入提示list选中数据
     *
     * @param tip
     */
    private void addTipMarker(Tip tip) {
        if (tip == null) {
            return;
        }
        aMap.clear();
        mPoiMarker = aMap.addMarker(new MarkerOptions());
        LatLonPoint point = tip.getPoint();
        if (point != null) {
            LatLng markerPosition = new LatLng(point.getLatitude(), point.getLongitude());
            mPoiMarker.setPosition(markerPosition);
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerPosition, 17));
        }
        mPoiMarker.setTitle(tip.getName());
        mPoiMarker.setSnippet(tip.getAddress());
    }


    public static boolean IsEmptyOrNullString(String s) {
        return (s == null) || (s.trim().length() == 0);
    }


//    关键字 搜索

    private void search(String content) {
        if (!IsEmptyOrNullString(content)) {
            InputtipsQuery inputquery = new InputtipsQuery(content, Constants.DEFAULT_CITY);
            Inputtips inputTips = new Inputtips(ActGaoMap.this.getApplicationContext(), inputquery);
            inputTips.setInputtipsListener(this);
            inputTips.requestInputtipsAsyn();
        } else {
            if (mIntipAdapter != null && mCurrentTipList != null) {
                mCurrentTipList.clear();
                mIntipAdapter.notifyDataSetChanged();
            }
        }

    }


    @Override
    public void onGetInputtips(List<Tip> list, int i) {
        if (i == 1000) {
            // 正确返回
            mCurrentTipList = list;
            Log.i("mCurrentTipList", mCurrentTipList.size() + "");
//            List<String> listString = new ArrayList<String>();
//            for (int i = 0; i < tipList.size(); i++) {
//                listString.add(tipList.get(i).getName());
//            }
            mIntipAdapter = new InputTipsAdapterMap(getApplicationContext(), mCurrentTipList);
            mInputListView.setAdapter(mIntipAdapter);
            mIntipAdapter.notifyDataSetChanged();
        }

        if (i == 1000) {// 正确返回
            mCurrentTipList = list;
//            List<String> listString = new ArrayList<String>();
//            for (int i = 0; i < tipList.size(); i++) {
//                listString.add(tipList.get(i).getName());
//            }
            mIntipAdapter = new InputTipsAdapterMap(getApplicationContext(), mCurrentTipList);
            mInputListView.setAdapter(mIntipAdapter);
            mIntipAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_WORDS_NAME, query);
        if (!IsEmptyOrNullString(edit_text)) {
            intent.putExtra("mSearchView", edit_text);
        }
        setResult(ActGaoMap.RESULT_CODE_KEYWORDS, intent);
//        this.finish();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!IsEmptyOrNullString(newText)) {
            edit_text = newText;
        }
        if (!IsEmptyOrNullString(newText)) {
            InputtipsQuery inputquery = new InputtipsQuery(newText, Constants.DEFAULT_CITY);
            Inputtips inputTips = new Inputtips(ActGaoMap.this.getApplicationContext(), inputquery);
            inputTips.setInputtipsListener(this);
            inputTips.requestInputtipsAsyn();
        } else {
            if (mIntipAdapter != null && mCurrentTipList != null) {
                mCurrentTipList.clear();
                mIntipAdapter.notifyDataSetChanged();
            }
        }
        return false;
    }


    public class InputTipsAdapterMap extends BaseAdapter {
        private Context mContext;
        private ArrayList<Tip> mListTips = new ArrayList<>();

        public InputTipsAdapterMap(Context context, List<Tip> tipList) {

            this.mListTips.clear();
            this.mListTips.addAll(tipList);
            mContext = context;
            notifyDataSetChanged();
//            mListTips = tipList;
        }

        @Override
        public int getCount() {
            if (mListTips != null) {
                return mListTips.size();
            }
            return 0;
        }


        @Override
        public Object getItem(int i) {
            if (mListTips != null) {
                return mListTips.get(i);
            }
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            Holder holder;
            if (view == null) {
                holder = new Holder();
                view = LayoutInflater.from(mContext).inflate(R.layout.adapter_inputtips, null);
                holder.mName = (TextView) view.findViewById(R.id.name);
                holder.mAddress = (TextView) view.findViewById(R.id.adress);
                holder.lin_tip = (LinearLayout) view.findViewById(R.id.lin_tip);
                view.setTag(holder);
            } else {
                holder = (Holder) view.getTag();
            }
            if (mListTips == null) {
                return view;
            }

            holder.mName.setText(mListTips.get(i).getName());
            String address = mListTips.get(i).getAddress();

            try {
                LatLonPoint b = mListTips.get(i).getPoint();
                b.getLatitude();
                b.getLongitude();
            } catch (Exception e) {

            }

            if (address == null || address.equals("")) {
                holder.mAddress.setVisibility(View.GONE);
            } else {
                holder.mAddress.setVisibility(View.VISIBLE);
                holder.mAddress.setText(address);
            }

            try {
                if (mListTips != null && !mListTips.isEmpty()) {
                    holder.lin_tip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String Longitude = mListTips.get(i).getPoint().getLongitude() + "";
                            String Latitude = mListTips.get(i).getPoint().getLatitude() + "";
                            String Address = mListTips.get(i).getAddress();

                            String district = mListTips.get(i).getDistrict();


                            Log.i("asdsadsa", Longitude + "---" + Latitude
                                    +
                                    Address
+"----"+district

                            );

//                            商家申请 入驻 的时候 选择的界面
//                            if (mercertifiact == 0) {
//                                // TODO Auto-generated method stub
//                                Intent intent = new Intent();
//                                intent.putExtra("Longitude", Longitude); //将计算的值回传回去
//                                intent.putExtra("Latitude", Latitude);
//                                intent.putExtra("Address", Address);
//                                //通过intent对象返回结果，必须要调用一个setResult方法，
//                                //setResult(resultCode, data);第一个参数表示结果返回码，一般只要大于1就可以，但是
//                                setResult(2, intent);
//                                finish();
//                            }

//                            商家申请入驻
                            if (mercertifiact == 1) {
                                mercertifiact = 0;
                                // TODO Auto-generated method stub
                                Intent intent = new Intent();

                                intent.putExtra("Longitude", Longitude); //将计算的值回传回去

                                intent.putExtra("Latitude", Latitude);

                                intent.putExtra("Address", Address);

                                intent.putExtra("district", district);
                                //通过intent对象返回结果，必须要调用一个setResult方法，
                                //setResult(resultCode, data);第一个参数表示结果返回码，一般只要大于1就可以，但是
                                setResult(2, intent);
                                finish();
                            }

//                        添加  创建 收货地址的 时候
                            if (mercertifiact == 2) {
                                mercertifiact = 0;
                                // TODO Auto-generated method stub
                                Intent intent = new Intent();

                                intent.putExtra("Longitude", Longitude); //将计算的值回传回去

                                intent.putExtra("Latitude", Latitude);

                                intent.putExtra("Address", Address);

                                intent.putExtra("district", district);
                                //通过intent对象返回结果，必须要调用一个setResult方法，
                                //setResult(resultCode, data);第一个参数表示结果返回码，一般只要大于1就可以，但是
                                setResult(2, intent);
                                finish();
                            }
                        }
                    });
                }
            } catch (Exception e) {

            }

            return view;
        }

        class Holder {

            private LinearLayout lin_tip;

            TextView mName;
            TextView mAddress;
        }
    }


    private void initSearchView() {
        mSearchView = (SearchView) findViewById(R.id.keyWord);


//        //获取到TextView的ID
//        int id = mSearchView.getContext().getResources().getIdentifier("android:id/search_src_text",null,null);
////获取到TextView的控件
//        TextView textView = (TextView) mSearchView.findViewById(id);
////设置字体大小为14sp
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);//14sp
////设置字体颜色
//        textView.setTextColor(getResources().getColor(R.color.corlor_hit));
////设置提示文字颜色
//        textView.setHintTextColor(getResources().getColor(R.color.corlor_hit));
        mSearchView.setOnQueryTextListener(this);
        //设置SearchView默认为展开显示
        mSearchView.setIconified(false);
        mSearchView.onActionViewExpanded();
        mSearchView.setIconifiedByDefault(true);
        mSearchView.setSubmitButtonEnabled(false);
        if (mSearchView == null) {
            return;
        }

    }

    @Override

    protected void onResume() {

// TODO Auto-generated method stub

        super.onResume();

        lin_serch.setFocusable(true);

        lin_serch.setFocusableInTouchMode(true);

        lin_serch.requestFocus();

    }

}

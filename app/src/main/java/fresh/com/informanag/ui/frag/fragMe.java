package fresh.com.informanag.ui.frag;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.makeramen.roundedimageview.RoundedImageView;
import com.sak.ultilviewlib.UltimateRefreshView;
import com.sak.ultilviewlib.interfaces.OnHeaderRefreshListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import fresh.com.informanag.App.MyApp;
import fresh.com.informanag.R;
import fresh.com.informanag.adpter.JDAppFooterAdapter;
import fresh.com.informanag.adpter.JDAppHeaderAdpater;
import fresh.com.informanag.base.BaseFragment;
import fresh.com.informanag.bean.Bean_merchant;
import fresh.com.informanag.bean.Bean_use_destail;
import fresh.com.informanag.ui.act.actMe.ActEvaCenter;
import fresh.com.informanag.ui.act.actMe.ActLogin;
import fresh.com.informanag.ui.act.actMe.ActMerManage;
import fresh.com.informanag.ui.act.actMe.ActMertion;
import fresh.com.informanag.ui.act.actMe.ActMessCenter;
import fresh.com.informanag.ui.act.actMe.MeAddAddress;
import fresh.com.informanag.ui.act.actMe.ToExamine;
import fresh.com.informanag.util.HttpUtil;
import fresh.com.informanag.util.MdFTion;
import fresh.com.informanag.util.OssService;

import static android.app.Activity.RESULT_CANCELED;
import static com.mob.tools.utils.DeviceHelper.getApplication;
import static fresh.com.informanag.util.MdFTion.mBottomSheetPop;


/**
 * Created by Administrator
 * on 2019/5/13 0013
 */

public class fragMe extends BaseFragment {


    private int getNickname = 0;


    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";
    private static final String CROP_IMAGE_FILE_NAME = "bala_crop.jpg";
    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private String mExtStorDir;
    private String getRandom;
    private final int PERMISSION_READ_AND_CAMERA = 0;//读和相机权限
    private final int PERMISSION_READ = 1;//读取权限
    //商户号
    private String merchantNo = "";
    //    是否已经提交了  申请
    private boolean isApplied;
    private Bean_merchant bean_merchant;
    private String message_merchant;
    private String code_merchant;
    private Bean_merchant.DataBean data_merchant;
    private String state;
    //    判断是否是 商户
    private boolean isMerchant;
    @BindView(R.id.text_user_phone)
    TextView text_user_phone;
    private Bean_use_destail bean_use_destail;
    private String message;
    private String code;
    private Bean_use_destail.DataBean data;
    private String userNo;
    private String username;
    private String headImg;
    private String nickname;
    private String phone;
    private String birthday;
    private String wxOpenId;
    private String type;
    private String status;
    private String loginStatus;
    private String lastLoginTime;

    private Uri mUriPath;
    @BindView(R.id.lin_Mer_vice)
    LinearLayout lin_Mer_vice;

    @BindView(R.id.head_round_me)
    RoundedImageView head_round_me;

    @BindView(R.id.img_me_back)
    ImageView img_me_back;

    @BindView(R.id.lin_res)
    LinearLayout lin_res;

    @BindView(R.id.lin_set_up)
    LinearLayout lin_set_up;

    @BindView(R.id.lin_me_message)
    LinearLayout lin_me_message;

    @BindView(R.id.lin_evaluate)
    LinearLayout lin_evaluate;


    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    private static int output_X = 480;
    private static int output_Y = 480;


    @Override
    protected int getLayoutId() {
        return R.layout.frag_me;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(getActivity(), clz));
    }

    @Override
    protected void initData() {

        mExtStorDir = Environment.getExternalStorageDirectory().toString();

//        Glide.with(getActivity()).load(MyApp.url).into(head_round_me);

//        Glide.with(getActivity()).load(MyApp.url).into(img_me_back);


//        refreshView_me.setBaseHeaderAdapter(new JDAppHeaderAdpater(getActivity()));
//        refreshView_me.setBaseFooterAdapter(new JDAppFooterAdapter(getActivity()));
//        refreshView_me.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
//            @Override
//            public void onHeaderRefresh(UltimateRefreshView view) {
//                showLoadingDialog();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
////                        mAnimationAdapter.replaceData(null);
////刷新
//
//                        refreshView_me.onHeaderRefreshComplete();
//
//                    }
//                }, 2000);
//            }
//        });
//        refreshView_me.setOnFooterRefreshListener(new OnFooterRefreshListener() {
//            @Override
//            public void onFooterRefresh(UltimateRefreshView view) {
//
//                showLoadingDialog();
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        refreshView_me.onFooterRefreshComplete();
//
//
//                    }
//                }, 2000);
//            }
//        });
    }


    @OnClick({R.id.lin_Mer_vice, R.id.lin_res, R.id.lin_set_up, R.id.lin_me_message, R.id.lin_evaluate, R.id.head_round_me})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_Mer_vice:


                if (!MyApp.user.equals("")) {

                    //                是否已经提交了
                    if (isApplied) {
//                有商铺
                        if (isMerchant) {
//                进行   商家   认证

                            Intent intent_is = new Intent(getActivity(), ActMerManage.class);
                            intent_is.putExtra("merchantNo", merchantNo);

                            MyApp.merchantNo = merchantNo;

                            startActivity(intent_is);

                        } else {

                            Intent intent = new Intent(getActivity(), ToExamine.class);

                            //                审核失败
                            if (state.equals("AUDIT_FAILURE")) {
                                intent.putExtra("intent_type", 1);
                                intent.putExtra("intent_name", "审核失败");
                                startActivity(intent);
                            }
//                审核中
                            if (state.equals("AUDIT_PROCESSING")) {
                                intent.putExtra("intent_type", 0);
                                intent.putExtra("intent_name", "审核中");
                                startActivity(intent);
                            }
//                审核通过
                            if (state.equals("AUDIT_PASSED")) {
                                Intent intent_yes = new Intent(getActivity(), ActMerManage.class);
                                intent_yes.putExtra("merchantNo", merchantNo);
                                startActivity(intent_yes);
                            }
                        }
                    } else {
                        startActivity(ActMertion.class);
                    }


                }else {
                    startActivity(ActLogin.class);
                }


                break;
            case R.id.lin_res:


                if (!MyApp.user.equals("")) {

                    startActivity(MeAddAddress.class);

                }else {
                    startActivity(ActLogin.class);
                }

                break;
//          退出
            case R.id.lin_set_up:

                if (!MyApp.user.equals("")) {

                    new SweetAlertDialog(getActivity(), SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                            .setTitleText("万仓生活!")
                            .setContentText("退出登录")
                            .setCustomImage(R.drawable.tuichu)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {


                                    Glide.with(getActivity()).load(R.drawable.touxiang)
                                            .into(head_round_me);
                                    text_user_phone.setText("Tel");



                                    HttpUtil.addMapparams();
                                    HttpUtil.params.put("userNo", MyApp.user);
                                    HttpUtil.post_Life("user/logout", HttpUtil.params);
                                    getdata("user/logout");
                                    sweetAlertDialog.dismissWithAnimation();


                                }
                            })
                            .show();

                }


//                startActivity(ActSetUp.class);
                break;
//                我的消息
            case R.id.lin_me_message:


                if (!MyApp.user.equals("")) {
                    startActivity(ActMessCenter.class);
                }else {
                    startActivity(ActLogin.class);
                }


                break;

            case R.id.lin_evaluate:
                if (!MyApp.user.equals("")) {
                    startActivity(ActEvaCenter.class);
                }else {
                    startActivity(ActLogin.class);
                }
                break;

            case R.id.head_round_me:
                if (!MyApp.user.equals("")) {

                    if (getNickname==0){
                        choosehead();
                    }

                }else {
                    Glide.with(getActivity()).load(R.drawable.touxiang)
                            .into(head_round_me);
                    text_user_phone.setText("Tel");

                    startActivity(ActLogin.class);
                }
                break;
        }
    }

    public void StringResulit(String key, String value) {

//        商家 认证的 状态
        if (key.equals("merchant/resultQuery")) {

            try {
                //   resultQuery         private Bean_merchant bean_merchant;
//            private String message_merchant;
//            private String code_merchant;
//            private Bean_merchant.DataBean data_merchant;

                bean_merchant = MyApp.gson.fromJson(value, Bean_merchant.class);

                data_merchant = bean_merchant.getData();

                state = data_merchant.getState();

                isMerchant = data_merchant.isIsMerchant();

                isApplied = data_merchant.isIsApplied();

                try {
                    //商户号
                    merchantNo = data_merchant.getMerchantNo();
                } catch (Exception e) {

                }
            } catch (Exception e) {

            }
        }


//退出登录
        if (key.equals("user/logout")) {
//          SharedPreferencesUtils.setParam(MyApp.context, "user", "");
            MyApp.clear(getActivity());

            MyApp.user = "";

            startActivity(ActLogin.class);
        }
//        获取 用户 信息
        if (key.equals("user/detail")) {
//            private String message;
//            private String code;
//            private Bean_use_destail.DataBean data;
//            private String userNo;
//            private String username;
//            private String headImg;
//            private String nickname;
//            private String phone;
//            private String birthday;
//            private String wxOpenId;
//            private String type;
//            private String status;
//            private String loginStatus;
//            private String lastLoginTime;
            try {
                bean_use_destail = MyApp.gson.fromJson(value, Bean_use_destail.class);
                data = bean_use_destail.getData();


                RequestOptions options = new RequestOptions()
                        .placeholder(R.drawable.touxiang)//图片加载出来前，显示的图片
                        .fallback( R.drawable.touxiang) //url为空的时候,显示的图片
                        .error(R.drawable.touxiang);//图片加载失败后，显示的图片
//头像

//背景图
//              Glide.with(getActivity()).load(data.getHeadImg()).into(img_me_back);

            if (data.getPhone()!=null){
                getNickname = 0;
                Log.i("text_user_phone",data.getPhone());
                phone = data.getPhone();
                String phoneNumber = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
                text_user_phone.setText(phoneNumber);
            }else {

                Log.i("text_user_phone",data.getNickname()+"");
                getNickname = 1;
                text_user_phone.setText(data.getNickname());
            }
            Glide.with(getActivity()).load(data.getHeadImg())
                    .apply(options)
                    .into(head_round_me);
//          type = data.getType();
            } catch (Exception e) {
//                text_user_phone.setText(data.getNickname());


                Glide.with(getActivity()).load(R.drawable.touxiang)
                        .into(head_round_me);
                text_user_phone.setText("Tel");


            }
        }
    }

    public void onResume() {
        super.onResume();
//        String user = (String) MyApp.getSharedPreference(getActivity(), "user", "");
//        Toast.makeText(getActivity(), "看到了！" + "----" + user, Toast.LENGTH_SHORT).show();
        if (!MyApp.getSharedPreference(getActivity(), "user", "").equals("")) {
//            用户信息
            HttpUtil.addMapparams();
            HttpUtil.params.put("userNo", MyApp.user);
            HttpUtil.post_Life("user/detail", HttpUtil.params);
            getdata("user/detail");
//        商户认证信息
            HttpUtil.addMapparams();
            HttpUtil.params.put("userNo", MyApp.user);
            HttpUtil.post_Life("merchant/resultQuery", HttpUtil.params);
            getdata("merchant/resultQuery");
        }
    }

    //    头像上传
    private void choosehead() {
        //                弹窗
        View view_headimg = MdFTion.setRebuildPop(getActivity(), R.layout.pop_shot, R.layout.frag_me);
//相册
        TextView text_Album = (TextView) view_headimg.findViewById(R.id.text_Album);
        text_Album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkReadPermission();
            }
        });
//                拍照
        TextView text_Photograph = (TextView) view_headimg.findViewById(R.id.text_Photograph);
        text_Photograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkStoragePermission();//检查是否有权限
            }
        });
//取消
        TextView text_cancel_ = (TextView) view_headimg.findViewById(R.id.text_cancel);
        text_cancel_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetPop.dismiss();
            }
        });
    }


    private void checkReadPermission() {
        int permission = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permission == PackageManager.PERMISSION_DENIED) {
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(getActivity(), permissions, PERMISSION_READ);
        } else {
            choseHeadImageFromGallery();
        }

    }


    private void checkStoragePermission() {
        int result = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_DENIED) {
            String[] permissions = {/*Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ,*/Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(getActivity(), permissions, PERMISSION_READ_AND_CAMERA);
        } else {
            choseHeadImageFromCameraCapture();
        }
    }


    // 从本地相册选取图片作为头像
    private void choseHeadImageFromGallery() {
        // 设置文件类型    （在华为手机中不能获取图片，要替换代码）
        /*Intent intentFromGallery = new Intent();
        intentFromGallery.setType("image*//*");
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);*/

        Intent intentFromGallery = new Intent(Intent.ACTION_PICK, null);
        intentFromGallery.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);

    }


    // 启动手机相机拍摄照片作为头像
    private void choseHeadImageFromCameraCapture() {
        String savePath = mExtStorDir;
        Intent intent = null;
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            //设定拍照存放到自己指定的目录,可以先建好
            File file = new File(savePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            Uri pictureUri;
            getRandom = getRandomString2(6);
            File pictureFile = new File(savePath, getRandom + IMAGE_FILE_NAME);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                pictureUri = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".fileProvider", pictureFile);
            } else {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                pictureUri = Uri.fromFile(pictureFile);
            }
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                ContentValues contentValues = new ContentValues(1);
                contentValues.put(MediaStore.Images.Media.DATA, pictureFile.getAbsolutePath());
                pictureUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            } else {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                pictureUri = Uri.fromFile(pictureFile);
            }*/
            if (intent != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        pictureUri);
                startActivityForResult(intent, CODE_CAMERA_REQUEST);
            }
        }
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }


    /**
     * 第二种方法
     */
    public String getRandomString2(int length) {
        //产生随机数
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        //循环length次
        for (int i = 0; i < length; i++) {
            //产生0-2个随机数，既与a-z，A-Z，0-9三种可能
            int number = random.nextInt(3);
            long result = 0;
            switch (number) {
                //如果number产生的是数字0；
                case 0:
                    //产生A-Z的ASCII码
                    result = Math.round(Math.random() * 25 + 65);
                    //将ASCII码转换成字符
                    sb.append(String.valueOf((char) result));
                    break;
                case 1:
                    //产生a-z的ASCII码
                    result = Math.round(Math.random() * 25 + 97);
                    sb.append(String.valueOf((char) result));
                    break;
                case 2:
                    //产生0-9的数字
                    sb.append(String.valueOf
                            (new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent intent) {

        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getActivity().getApplication(), "取消", Toast.LENGTH_LONG).show();
            return;
        }

        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(intent.getData());
                break;

            case CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            getRandom + IMAGE_FILE_NAME);
//                    cropRawPhoto(Uri.fromFile(tempFile));
                    cropRawPhoto(getImageContentUri(tempFile));
                } else {
                    Toast.makeText(getActivity().getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
                            .show();
                }

                break;

            case CODE_RESULT_REQUEST:
                /*if (intent != null) {
                    setImageToHeadView(intent);    //此代码在小米有异常，换以下代码
                }*/
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(mUriPath));

                    Log.i("mUriPath", mUriPath + "----");

                    setImageToHeadView(intent, bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                break;
        }

        super.onActivityResult(requestCode, resultCode, intent);
    }


    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Intent intent, Bitmap b) {
        /*Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            headImage.setImageBitmap(photo);
        }*/
        try {
            if (intent != null) {
//              Toast.makeText(ActMeSet.this,"过来了",Toast.LENGTH_SHORT).show();
                Bitmap bitmap = imageZoom(b);//看个人需求，可以不压缩
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] bytes = baos.toByteArray();
                Glide.with(getActivity()).load(bytes).into(head_round_me);
                File file = new File(mUriPath.getPath());
//      上传 头像
                shangchuan(getActivity(),
                        "LTAIkud1MXnSG5k0",
                        "oBanvYOmHZzEZDb268EDd1hbg6ETbo",
                        "oss-cn-beijing.aliyuncs.com",
                        "jinlegou",
                        file.getName(),
                        mUriPath.getPath());
//              cir_headimg.setImageBitmap(b);
                mBottomSheetPop.dismiss();
//                long millis = System.currentTimeMillis();
                /*File file = FileUtil.saveFile(mExtStorDir, millis+CROP_IMAGE_FILE_NAME, bitmap);
                if (file!=null){
                    //传递新的头像信息给我的界面
                    Intent ii = new Intent();
                    setResult(new_icon,ii);
                    Glide.with(this).load(file).apply(RequestOptions.circleCropTransform())
//                                .apply(RequestOptions.fitCenterTransform())
                            .apply(RequestOptions.placeholderOf(R.mipmap.user_logo)).apply(RequestOptions.errorOf(R.mipmap.user_logo))
                            .into(mIvTouxiangPersonal);
//                uploadImg(mExtStorDir,millis+CROP_IMAGE_FILE_NAME);
                  uploadImg(mExtStorDir,millis+CROP_IMAGE_FILE_NAME);
                }*/
            }
        } catch (Exception e) {
            e.printStackTrace();

            Toast.makeText(getActivity(), "有异常", Toast.LENGTH_SHORT).show();


        }
    }

    private Bitmap imageZoom(Bitmap bitMap) {
        //图片允许最大空间   单位：KB
        double maxSize = 1000.00;
        //将bitmap放至数组中，意在bitmap的大小（与实际读取的原文件要大）
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitMap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        //将字节换成KB
        double mid = b.length / 1024;
        //判断bitmap占用空间是否大于允许最大空间  如果大于则压缩 小于则不压缩
        if (mid > maxSize) {
            //获取bitmap大小 是允许最大大小的多少倍
            double i = mid / maxSize;
            //开始压缩  此处用到平方根 将宽带和高度压缩掉对应的平方根倍 （1.保持刻度和高度和原bitmap比率一致，压缩后也达到了最大大小占用空间的大小）
            bitMap = zoomImage(bitMap, bitMap.getWidth() / Math.sqrt(i),
                    bitMap.getHeight() / Math.sqrt(i));
        }
        return bitMap;
    }

    public Uri getImageContentUri(File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = getActivity().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return getActivity().getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }


    //    上传头像
    private void shangchuan(Context context,
                            String accesskeyid,
                            String accesskeysecret,
                            String endpoint,
                            String bucketname,
                            String filename,
                            String filePath) {
        Log.i("accesskeyid", accesskeyid + "---" + accesskeysecret + "---" + endpoint + "---" + bucketname + "---" + filename + "---" + filePath);
        //初始化OssService类，参数分别是Content，accessKeyId，accessKeySecret，endpoint，bucketName（后4个参数是您自己阿里云Oss中参数）
//        OssService ossService = new OssService(this, accessKeyId, accessKeySecret, endpoint, bucketName);
        OssService ossService = new OssService(context, accesskeyid, accesskeysecret, endpoint, bucketname);
//初始化OSSClient
        ossService.initOSSClient();
//开始上传，参数分别为content，上传的文件名filename，上传的文件路径filePath
//        ossService.beginupload(this, filename, filePath);

        ossService.beginupload(getActivity(), filename, filePath, 100);

//上传的进度回调
//        ossService.setProgressCallback(new OssService.ProgressCallback() {
//            @Override
//            public void onProgressCallback(final double progress) {
//                Log.i("上传进度：",  progress+"");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                });
//            }
//          }
        ossService.setProgressCallback(new OssService.ProgressCallback() {
            @Override
            public void onProgressCallback(final double progress) {
                Log.i("上传进度：", progress + "");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String progressaa = progress + "";

                    }
                });
            }
        });
    }


    /***
     * 图片的缩放方法
     *
     * @param bgimage
     *            ：源图片资源
     * @param newWidth
     *            ：缩放后宽度
     * @param newHeight
     *            ：缩放后高度
     * @return
     */
    public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
                                   double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }


    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);

        //startActivityForResult(intent, CODE_RESULT_REQUEST); //直接调用此代码在小米手机有异常，换以下代码
        String mLinshi = System.currentTimeMillis() + CROP_IMAGE_FILE_NAME;
        File mFile = new File(mExtStorDir, mLinshi);
//        mHeadCachePath = mHeadCacheFile.getAbsolutePath();

        mUriPath = Uri.parse("file://" + mFile.getAbsolutePath());
        //将裁剪好的图输出到所建文件中
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUriPath);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        //注意：此处应设置return-data为false，如果设置为true，是直接返回bitmap格式的数据，耗费内存。设置为false，然后，设置裁剪完之后保存的路径，即：intent.putExtra(MediaStore.EXTRA_OUTPUT, uriPath);
//        intent.putExtra("return-data", true);
        intent.putExtra("return-data", false);
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }


}

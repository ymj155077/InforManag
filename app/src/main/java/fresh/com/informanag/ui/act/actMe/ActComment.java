package fresh.com.informanag.ui.act.actMe;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.DateUtils;
import com.luck.picture.lib.tools.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import fresh.com.informanag.App.MyApp;
import fresh.com.informanag.R;
import fresh.com.informanag.base.BaseActivity;
import fresh.com.informanag.bean.Bean_mer;
import fresh.com.informanag.util.HttpUtil;
import fresh.com.informanag.util.Tool;
import fresh.com.informanag.view.FullyGridLayoutManager;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static fresh.com.informanag.App.MyApp.PUBLIC_URL_shop;
import static fresh.com.informanag.App.MyApp.Time_stamp;
import static fresh.com.informanag.App.MyApp.gson;
import static fresh.com.informanag.App.MyApp.mOkHttpClient;
import static fresh.com.informanag.App.MyApp.mig_url;
import static fresh.com.informanag.App.MyApp.ran_number;
import static fresh.com.informanag.App.MyApp.sign;
import static fresh.com.informanag.util.HttpUtil.paramsheads;

public class ActComment extends BaseActivity {


    @BindView(R.id.toolbar_all)
    Toolbar toolbar_all;


    //张数
    private int maxSelectNum = 5;
    private GridImageAdapter adapter;

    //多图
    List<LocalMedia> selectLists = new ArrayList<>();

    @BindView(R.id.recycler)
    RecyclerView recycler;


    private int themeId;
    private int chooseMode = PictureMimeType.ofImage();

    ArrayList<String> img_body = new ArrayList<>();
    @Override
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_act_comment;
    }

    @Override
    protected void findViews() {

        TextView text_center = (TextView) toolbar_all.findViewById(R.id.toolbar_center);
        text_center.setText("发表评论");


        TextView toolbar_right = (TextView) toolbar_all.findViewById(R.id.toolbar_right);

        toolbar_right.setTextColor(android.graphics.Color.parseColor("#FF5B05")); // 使用Color类转换


        toolbar_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ActComment.this, "发标评论！！！", Toast.LENGTH_SHORT).show();


                for (LocalMedia media : selectLists) {
                    Log.i("图片-----》", media.getPath());
                    File file = new File(media.getPath());
                    // 上传 商品
                    shangchuan(ActComment.this,
                            "LTAIbXI51kLkbdX0",
                            "0AY96544qTwx88oS2twVlRLtNhtchW",
                            "imgs.ejinlegou.com",
                            "wcsx",
                            file.getName(),
                            media.getPath(), 1, 0, "");
                }


            }
        });

    }

    @Override
    public void initData() {

        img_body.clear();

        themeId = R.style.picture_default_style;

        FullyGridLayoutManager managers = new FullyGridLayoutManager(ActComment.this, 3, GridLayoutManager.VERTICAL, false);

        recycler.setLayoutManager(managers);
        adapter = new GridImageAdapter(ActComment.this, onAddPicClickListener);
        adapter.setList(selectLists);
        adapter.setSelectMax(1);
        recycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
//单个  图片

                if (selectLists.size() > 0) {
                    LocalMedia media = selectLists.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(ActComment.this).themeStyle(themeId).openExternalPreview(position, selectLists);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(ActComment.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(ActComment.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
    }


    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {

//            相册 或 单独 拍照

//            boolean mode = cb_mode.isChecked();
            boolean mode = true;
            if (mode) {
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(ActComment.this)
                        .openGallery(chooseMode)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                        .maxSelectNum(1)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .imageSpanCount(4)// 每行显示个数
//                        单选或者多选
                        .selectionMode(true ?
                                PictureConfig.MULTIPLE : PictureConfig.SINGLE)// 多选 or 单选
                        .previewImage(true)// 是否可预览图片
                        .previewVideo(false)// 是否可预览视频
                        .enablePreviewAudio(false) // 是否可播放音频
                        .isCamera(true)// 是否显示拍照按钮
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                        //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                        .enableCrop(false)// 是否裁剪
                        .compress(true)// 是否压缩
                        .synOrAsy(true)//同步true或异步false 压缩 默认同步
                        //.compressSavePath(getPath())//压缩图片保存地址
                        //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .hideBottomControls(false ? false : true)// 是否显示uCrop工具栏，默认不显示
                        .isGif(false)// 是否显示gif图片
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                        .circleDimmedLayer(false)// 是否圆形裁剪
                        .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                        .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        .openClickSound(true)// 是否开启点击声音
                        .selectionMedia(selectLists)// 是否传入已选图片
                        .isDragFrame(true)// 是否可拖动裁剪框(固定)
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                        //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        .cropCompressQuality(90)// 裁剪压缩质量 默认100
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                        //.rotateEnabled(true) // 裁剪是否可旋转图片
                        //.scaleEnabled(true)// 裁剪是否可放大缩小图片
                        //.videoQuality()// 视频录制质量 0 or 1
                        //.videoSecond()//显示多少秒以内的视频or音频也可适用
                        //.recordVideoSecond()//录制视频秒数 默认60s
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            } else {
                // 单独拍照
                PictureSelector.create(ActComment.this)
                        .openCamera(chooseMode)// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                        .theme(themeId)// 主题样式设置 具体参考 values/styles
                        .maxSelectNum(1)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .selectionMode(true ?
                                PictureConfig.MULTIPLE : PictureConfig.SINGLE)// 多选 or 单选
                        .previewImage(true)// 是否可预览图片
                        .previewVideo(false)// 是否可预览视频
                        .enablePreviewAudio(false) // 是否可播放音频
                        .isCamera(true)// 是否显示拍照按钮
                        .enableCrop(false)// 是否裁剪
                        .compress(true)// 是否压缩
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .hideBottomControls(false ? false : true)// 是否显示uCrop工具栏，默认不显示
                        .isGif(false)// 是否显示gif图片
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                        .circleDimmedLayer(false)// 是否圆形裁剪
                        .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                        .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        .openClickSound(true)// 是否开启点击声音
                        .selectionMedia(selectLists)// 是否传入已选图片
                        .previewEggs(false)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        .cropCompressQuality(90)// 裁剪压缩质量 默认为100
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                        //.rotateEnabled() // 裁剪是否可旋转图片
//                        .scaleEnabled()// 裁剪是否可放大缩小图片
                        //.videoQuality()// 视频录制质量 0 or 1
                        //.videoSecond()////显示多少秒以内的视频or音频也可适用
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            }
        }
    };


    /**
     * author：luck
     * project：PictureSelector
     * package：com.luck.pictureselector.adapter
     * email：893855882@qq.com
     * data：16/7/27
     */
    public static class GridImageAdapter extends
            RecyclerView.Adapter<GridImageAdapter.ViewHolder> {
        public static final int TYPE_CAMERA = 1;
        public static final int TYPE_PICTURE = 2;
        private LayoutInflater mInflater;
        private List<LocalMedia> list = new ArrayList<>();
        private int selectMax = 3;
        private Context context;
        /**
         * 点击添加图片跳转
         */
        private GridImageAdapter.onAddPicClickListener mOnAddPicClickListener;

        //      返回 当前的 图片
        public List<LocalMedia> getdata_imgs() {

            return list;

        }

        public interface onAddPicClickListener {
            void onAddPicClick();
        }

        public GridImageAdapter(Context context, GridImageAdapter.onAddPicClickListener mOnAddPicClickListener) {
            this.context = context;
            mInflater = LayoutInflater.from(context);
            this.mOnAddPicClickListener = mOnAddPicClickListener;
        }

        public void setSelectMax(int selectMax) {
            this.selectMax = selectMax;
        }

        public void setList(List<LocalMedia> list) {
            this.list = list;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            ImageView mImg;
            LinearLayout ll_del;
            TextView tv_duration;

            public ViewHolder(View view) {
                super(view);
                mImg = (ImageView) view.findViewById(R.id.fiv);
                ll_del = (LinearLayout) view.findViewById(R.id.ll_del);
                tv_duration = (TextView) view.findViewById(R.id.tv_duration);
            }
        }

        @Override
        public int getItemCount() {
            if (list.size() < selectMax) {
                return list.size() + 1;
            } else {
                return list.size();
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (isShowAddItem(position)) {
                return TYPE_CAMERA;
            } else {
                return TYPE_PICTURE;
            }
        }

        /**
         * 创建ViewHolder
         */
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = mInflater.inflate(R.layout.gv_filter_image,
                    viewGroup, false);
            final ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        private boolean isShowAddItem(int position) {
            int size = list.size() == 0 ? 0 : list.size();
            return position == size;
        }

        /**
         * 设置值
         */
        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
            //少于8张，显示继续添加的图标
            if (getItemViewType(position) == TYPE_CAMERA) {
                viewHolder.mImg.setImageResource(R.drawable.frag_back);
                viewHolder.mImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnAddPicClickListener.onAddPicClick();
                    }
                });
                viewHolder.ll_del.setVisibility(View.INVISIBLE);
            } else {
                viewHolder.ll_del.setVisibility(View.VISIBLE);
                viewHolder.ll_del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int index = viewHolder.getAdapterPosition();
                        // 这里有时会返回-1造成数据下标越界,具体可参考getAdapterPosition()源码，
                        // 通过源码分析应该是bindViewHolder()暂未绘制完成导致，知道原因的也可联系我~感谢
                        if (index != RecyclerView.NO_POSITION) {
                            list.remove(index);
                            notifyItemRemoved(index);
                            notifyItemRangeChanged(index, list.size());
                        }
                    }
                });
                LocalMedia media = list.get(position);
                int mimeType = media.getMimeType();
                String path = "";
                if (media.isCut() && !media.isCompressed()) {
                    // 裁剪过
                    path = media.getCutPath();
                } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
                    // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                    path = media.getCompressPath();
                } else {
                    // 原图
                    path = media.getPath();
                }
                // 图片
                if (media.isCompressed()) {
                    Log.i("compress image result:", new File(media.getCompressPath()).length() / 1024 + "k");
                    Log.i("压缩地址::", media.getCompressPath());
                }

                Log.i("原图地址::", media.getPath());
                int pictureType = PictureMimeType.isPictureType(media.getPictureType());
                if (media.isCut()) {
                    Log.i("裁剪地址::", media.getCutPath());
                }
                long duration = media.getDuration();
                viewHolder.tv_duration.setVisibility(pictureType == PictureConfig.TYPE_VIDEO
                        ? View.VISIBLE : View.GONE);
                if (mimeType == PictureMimeType.ofAudio()) {
                    viewHolder.tv_duration.setVisibility(View.VISIBLE);
                    Drawable drawable = ContextCompat.getDrawable(context, R.drawable.picture_audio);
                    StringUtils.modifyTextViewDrawable(viewHolder.tv_duration, drawable, 0);
                } else {
                    Drawable drawable = ContextCompat.getDrawable(context, R.drawable.video_icon);
                    StringUtils.modifyTextViewDrawable(viewHolder.tv_duration, drawable, 0);
                }
                viewHolder.tv_duration.setText(DateUtils.timeParse(duration));
                if (mimeType == PictureMimeType.ofAudio()) {
                    viewHolder.mImg.setImageResource(R.drawable.audio_placeholder);
                } else {
                    RequestOptions options = new RequestOptions()
                            .centerCrop()
                            .placeholder(R.color.Write)
                            .diskCacheStrategy(DiskCacheStrategy.ALL);
                    try {
//                    正常情况下
                        Glide.with(viewHolder.itemView.getContext())
                                .load(path)
                                .apply(options)
                                .into(viewHolder.mImg);
                    } catch (Exception e) {
                        Glide.with(viewHolder.itemView.getContext())
                                .load(list.get(position).getPath())
                                .apply(options)
                                .into(viewHolder.mImg);
                    }
                }
                //itemView 的点击事件
                if (mItemClickListener != null) {
                    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int adapterPosition = viewHolder.getAdapterPosition();
                            mItemClickListener.onItemClick(adapterPosition, v);
                        }
                    });
                }
            }
        }

        protected OnItemClickListener mItemClickListener;

        public interface OnItemClickListener {
            void onItemClick(int position, View v);
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.mItemClickListener = listener;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:

                    // 图片选择结果回调
                    selectLists = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    for (LocalMedia media : selectLists) {
                        Log.i("图片-----》", media.getPath());
                    }
                    adapter

                            .setList(selectLists);
                    adapter


                            .notifyDataSetChanged();

                    break;
            }
        }
    }


    public void shangchuan(Context context,
                           String accesskeyid,
                           String accesskeysecret,
                           String endpoint,
                           String bucketname,
                           String filename,
                           String filePath,
                           int size,
//                           0  店铺 头像
//                           1  店铺 内 图片
                           int type,
                           String img
    ) {


        Log.i("accesskeyid", accesskeyid + "---" + accesskeysecret + "---" + endpoint + "---" + bucketname + "---" + filename + "---" + filePath);

        OssService ossService = new OssService(context, accesskeyid, accesskeysecret, endpoint, bucketname);

        ossService.initOSSClient();

        ossService.beginupload(ActComment.this, filename, filePath, size, type, img);
    }


    public class OssService {
        private String path_path = "OssService";
        private OSS oss;
        private String accessKeyId;
        private String bucketName;
        private String accessKeySecret;
        private String endpoint;
        private Context context;
        private int order;

        //      private ProgressCallback progressCallback;
        public OssService(Context context, String accessKeyId, String accessKeySecret, String endpoint, String bucketName) {
            this.context = context;
            this.endpoint = endpoint;
            this.bucketName = bucketName;
            this.accessKeyId = accessKeyId;
            this.accessKeySecret = accessKeySecret;
        }

        public void initOSSClient() {
            //OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider("<StsToken.AccessKeyId>", "<StsToken.SecretKeyId>", "<StsToken.SecurityToken>");
            //这个初始化安全性没有Sts安全，如需要很高安全性建议用OSSStsTokenCredentialProvider创建（上一行创建方式）多出的参数SecurityToken为临时授权参数
            OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(accessKeyId, accessKeySecret);
            ClientConfiguration conf = new ClientConfiguration();
            conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
            conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
            conf.setMaxConcurrentRequest(8); // 最大并发请求数，默认5个
            conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次

            // oss为全局变量，endpoint是一个OSS区域地址
            oss = new OSSClient(context, endpoint, credentialProvider, conf);

        }


        public void beginupload(Context context, String filename, String path, final int size, final int type, final String img) {

            //通过填写文件名形成objectname,通过这个名字指定上传和下载的文件

            String objectname = filename;
            if (objectname == null || objectname.equals("")) {
                Toast.makeText(context, "文件名不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            //下面3个参数依次为bucket名，Object名，上传文件路径
            String data_time = Tool.getTimestamp(new Date()) + "";

            objectname = "goods/" + data_time + objectname;

            PutObjectRequest put = new PutObjectRequest(bucketName, objectname, path);
            if (path == null || path.equals("")) {
                Log.d(path, "请选择图片....");
                //ToastUtils.showShort("请选择图片....");
                return;
            }
            Log.d(path, "正在上传中....");
            //ToastUtils.showShort("正在上传中....");
            // 异步上传，可以设置进度回调
            put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
                @Override
                public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                    Log.d(path_path, "currentSize: " + currentSize + " totalSize: " + totalSize);
                    double progress = currentSize * 1.0 / totalSize * 100.f;
//                    if (progressCallback != null) {
//                        progressCallback.onProgressCallback(progress);
//                    }
                }
            });
//        异步上传
            @SuppressWarnings("rawtypes")
            OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
                @Override
                public void onSuccess(PutObjectRequest request, PutObjectResult result) {

//                        ++sds;
//                        sd = new String[size];
                    String url_one = "imgs.ejinlegou.com/" + request.getObjectKey();
                    Log.d("UploadSuccesssassa", "UploadSuccess" + "地址-----" + url_one + "-----地址");
                        img_body.add(url_one);

//                            sd[sds - 1] = url_one;

//                        Log.d("222222222", "UploadSuccess" + "地址-----" + size_curpace + "-----地址" + foods_size + "---" + gson.toJson(img_body));

//                        if (img_body.size() == size) {
//                            Upload_pictures(img_body, img);
//                        }
                }

                @Override
                public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                    // 请求异常
                    Log.d(path_path, "UploadFailure");
                    Toast.makeText(ActComment.this, "UploadFailure", Toast.LENGTH_SHORT).show();
                    if (clientExcepion != null) {
                        // 本地异常如网络异常等
                        Log.e(path_path, "UploadFailure：表示向OSS发送请求或解析来自OSS的响应时发生错误。\n" +
                                "  *例如，当网络不可用时，这个异常将被抛出");
                        clientExcepion.printStackTrace();
                    }
                    if (serviceException != null) {
                        // 服务异常
                        Log.i(path_path, "UploadFailure：表示在OSS服务端发生错误");
                        Log.i("ErrorCode", serviceException.getErrorCode());
                        Log.i("RequestId", serviceException.getRequestId());
                        Log.i("HostId", serviceException.getHostId());
                        Log.i("RawMessage", serviceException.getRawMessage());
                    }
                }
            });
        }
    }


    //上传 图片    ArrayList<String> storeImgIn
    private void Upload_pictures(ArrayList<String> storeImgIn, String img) {

        HashMap<String, Object> map = new HashMap<>();
        map.clear();
//        map.put("address", text_address.getText().toString().trim());
//        map.put("detailAddress", edit_stree.getText().toString().trim());
//        map.put("latitude", Latitude);
//        map.put("longitude", Longitude);
//        map.put("merchantName", edit_Per_charge.getText().toString().trim());
//        map.put("phone", edit_phone.getText().toString().trim());
//
////        gson.toJson(storeImgIn)
//        map.put("storeImgIn", storeImgIn);
//
//        map.put("storeName", edit_shop_name.getText().toString().trim());
//        map.put("tel", edit_Landline.getText().toString().trim());
        map.put("userNo", MyApp.user);
        map.put("storeImg", img + "");
//        Toast.makeText(this,"请求了",Toast.LENGTH_SHORT).show();
//        RequestBody formBody = new FormBody.Builder()
//                .add("phone", phone)
//                .add("type", "" + 7)
//                .build();

        ran_number = HttpUtil.getNumberString();

        Time_stamp = HttpUtil.getSecondTimestamp();

        sign = HttpUtil.getSignature(ran_number,
                Time_stamp,
                "hello");


        HttpUtil.addMapheads();
        paramsheads.put("access_token", MyApp.access_token);
        paramsheads.put("sign", sign);
        paramsheads.put("timestamp", Time_stamp);
        paramsheads.put("nonce", ran_number);
        paramsheads.put("user", MyApp.user);

        Log.i("sadtoJsonasdasdas", gson.toJson(map) + "");

        Request request = new Request.Builder().
                url(PUBLIC_URL_shop + "merchant/authentication")
                .post(RequestBody.create(MediaType.parse("application/json"),
                        gson.toJson(map))
                )
                .headers(Headers.of(paramsheads))

                .build();
        okhttp3.Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                String resp = response.body().string();
//                Bean_mer
//                private String message;
//                private String code;
//                private Bean_mer.DataBean data;
                Log.i("mainceshi", resp);

            }
        });
    }
}

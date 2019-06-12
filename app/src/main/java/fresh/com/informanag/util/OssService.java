package fresh.com.informanag.util;

import android.content.Context;
import android.util.Log;
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

import java.io.IOException;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by Administrator
 * on 2018/12/27 0027
 */
public class OssService {



    private String path_path = "OssService";

    private OSS oss;
    private String accessKeyId;
    private String bucketName;
    private String accessKeySecret;
    private String endpoint;
    private Context context;

    private int order;

    private ProgressCallback progressCallback;

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

    public void beginupload(final Context context, String filename, String path, final int order) {
        this.order = order;
        //通过填写文件名形成objectname,通过这个名字指定上传和下载的文件
        String objectname = filename;
        if (objectname == null || objectname.equals("")) {
            Toast.makeText(context,"文件名不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        //下面3个参数依次为bucket名，Object名，上传文件路径
        String data_time =  getTimestamp(new Date())+"";
        objectname = "goods/"+data_time + objectname;
//        22的时候是添加商品
//        if (order==22){
//            objectname = "goods/"+ objectname;
//        }
//        else {
//            objectname = "goods/"+ objectname;
//        }
        PutObjectRequest put = new PutObjectRequest(bucketName, objectname, path);
        if (path == null || path.equals("")) {
            Log.d(path,"请选择图片....");
            //ToastUtils.showShort("请选择图片....");
            return;
        }
        Log.d(path,"正在上传中....");
        //ToastUtils.showShort("正在上传中....");
        // 异步上传，可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.d(path_path,"currentSize: " + currentSize + " totalSize: " + totalSize);
                double progress = currentSize * 1.0 / totalSize * 100.f;
                if (progressCallback != null) {
                    progressCallback.onProgressCallback(progress);
                }
            }
        });
//        异步上传
        @SuppressWarnings("rawtypes")
        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {

//                MdFTion.url_one = "https://jinlegou.oss-cn-beijing.aliyuncs.com/"+request.getObjectKey();

//                Log.d(path_path,"UploadSuccess"+"地址-----"+result.getServerCallbackReturnBody()+"-----地址"+request.getUploadData()+MdFTion.url_one );

            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                Log.d(path_path,"UploadFailure");
                Toast.makeText(context,"UploadFailure",Toast.LENGTH_SHORT).show();
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    Log.e(path_path,"UploadFailure：表示向OSS发送请求或解析来自OSS的响应时发生错误。\n" +
                            "  *例如，当网络不可用时，这个异常将被抛出");
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.i(path_path,"UploadFailure：表示在OSS服务端发生错误");
                    Log.i("ErrorCode", serviceException.getErrorCode());
                    Log.i("RequestId", serviceException.getRequestId());
                    Log.i("HostId", serviceException.getHostId());
                    Log.i("RawMessage", serviceException.getRawMessage());
                }
            }
        });
        //task.cancel(); // 可以取消任务
        //task.waitUntilFinished(); // 可以等待直到任务完成
    }

    public ProgressCallback getProgressCallback() {
        return progressCallback;
    }

    public void setProgressCallback(ProgressCallback progressCallback) {
        this.progressCallback = progressCallback;
    }

    public interface ProgressCallback {
        void onProgressCallback(double progress);
    }






    private void post_modify(String url_path) {

//        Toast.makeText(this,"请求了",Toast.LENGTH_SHORT).show();

//        RequestBody formBody = new FormBody.Builder()
//                .add("uid", uesr_id)
//                .add("token", "" + token)
//                .add("headImg", url_path)
//                .build();
//        Request request = new Request.Builder().
//                url(PUBLIC_URL + "changeHeadImg")
//                .post(formBody)
//                .build();
//        Call call = mOkHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//            }
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String resp = response.body().string();
//
//                Log.i("respa",resp);
//            }
//        });
    }
















}

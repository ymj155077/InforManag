package fresh.com.informanag.bean;

import java.util.List;

/**
 * Created by Administrator
 * on 2019/6/5 0005
 */
public class Bean_shop {


    /**
     * message : 执行成功
     * code : 200
     * data : {"userNo":"1j9shm63u0S041bqq7q6","merchantNo":"1ja7rmz39wS041bqq7q6","storeName":"YMJ","address":"南环路悦荟万科广场二楼","detailAddress":"阿斯顿撒","longitude":"116.238429","latitude":"40.212623","merchantName":"快快快","phone":"18725621750","tel":"428322681","storeImg":"imgs.ejinlegou.com/goods/1559615991water.png","storeImgIn":["imgs.ejinlegou.com/goods/1559615996calendar_icon.png","imgs.ejinlegou.com/goods/1559615996QQ图片20171027183216.png"],"applyTime":1559662752000,"state":"AUDIT_PASSED"}
     */

    private String message;
    private String code;
    private DataBean data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userNo : 1j9shm63u0S041bqq7q6
         * merchantNo : 1ja7rmz39wS041bqq7q6
         * storeName : YMJ
         * address : 南环路悦荟万科广场二楼
         * detailAddress : 阿斯顿撒
         * longitude : 116.238429
         * latitude : 40.212623
         * merchantName : 快快快
         * phone : 18725621750
         * tel : 428322681
         * storeImg : imgs.ejinlegou.com/goods/1559615991water.png
         * storeImgIn : ["imgs.ejinlegou.com/goods/1559615996calendar_icon.png","imgs.ejinlegou.com/goods/1559615996QQ图片20171027183216.png"]
         * applyTime : 1559662752000
         * state : AUDIT_PASSED
         */

        private String userNo;
        private String merchantNo;
        private String storeName;
        private String address;
        private String detailAddress;
        private String longitude;
        private String latitude;
        private String merchantName;
        private String phone;
        private String tel;
        private String storeImg;
        private long applyTime;
        private String state;
        private List<String> storeImgIn;

        public String getUserNo() {
            return userNo;
        }

        public void setUserNo(String userNo) {
            this.userNo = userNo;
        }

        public String getMerchantNo() {
            return merchantNo;
        }

        public void setMerchantNo(String merchantNo) {
            this.merchantNo = merchantNo;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDetailAddress() {
            return detailAddress;
        }

        public void setDetailAddress(String detailAddress) {
            this.detailAddress = detailAddress;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getStoreImg() {
            return storeImg;
        }

        public void setStoreImg(String storeImg) {
            this.storeImg = storeImg;
        }

        public long getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(long applyTime) {
            this.applyTime = applyTime;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public List<String> getStoreImgIn() {
            return storeImgIn;
        }

        public void setStoreImgIn(List<String> storeImgIn) {
            this.storeImgIn = storeImgIn;
        }
    }
}

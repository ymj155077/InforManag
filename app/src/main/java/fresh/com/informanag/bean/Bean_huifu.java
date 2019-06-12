package fresh.com.informanag.bean;

import java.util.List;

/**
 * Created by Administrator
 * on 2019/6/10 0010
 */
public class Bean_huifu {


    /**
     * message : 执行成功
     * code : 200
     * data : [{"evaluationNo":"1","goodsNo":"1javvofv58S081bqq7qb","userNo":"1j9shm63u0S041bqq7q6","nickname":"杨幂","content":"123","evaluationLevel":"2","imgUrls":["11","22"],"merchantReply":"单位","isReply":true,"rawAddTime":1560145940000,"headImg":null},{"evaluationNo":"2","goodsNo":"1javvofv58S081bqq7qb","userNo":"1j9shm63u0S041bqq7q6","nickname":"杨幂1","content":"123","evaluationLevel":"2","imgUrls":["11","22"],"merchantReply":"单位","isReply":true,"rawAddTime":1560145940000,"headImg":null},{"evaluationNo":"3","goodsNo":"1javvofv58S081bqq7qb","userNo":"1j9shm63u0S041bqq7q6","nickname":"杨幂2","content":"123","evaluationLevel":"2","imgUrls":["11","22"],"merchantReply":"单位","isReply":true,"rawAddTime":1560145940000,"headImg":null}]
     */

    private String message;
    private String code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * evaluationNo : 1
         * goodsNo : 1javvofv58S081bqq7qb
         * userNo : 1j9shm63u0S041bqq7q6
         * nickname : 杨幂
         * content : 123
         * evaluationLevel : 2
         * imgUrls : ["11","22"]
         * merchantReply : 单位
         * isReply : true
         * rawAddTime : 1560145940000
         * headImg : null
         */

        private String evaluationNo;
        private String goodsNo;
        private String userNo;
        private String nickname;
        private String content;
        private String evaluationLevel;
        private String merchantReply;
        private boolean isReply;
        private long rawAddTime;
        private String headImg;
        private List<String> imgUrls;

        public String getEvaluationNo() {
            return evaluationNo;
        }

        public void setEvaluationNo(String evaluationNo) {
            this.evaluationNo = evaluationNo;
        }

        public String getGoodsNo() {
            return goodsNo;
        }

        public void setGoodsNo(String goodsNo) {
            this.goodsNo = goodsNo;
        }

        public String getUserNo() {
            return userNo;
        }

        public void setUserNo(String userNo) {
            this.userNo = userNo;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getEvaluationLevel() {
            return evaluationLevel;
        }

        public void setEvaluationLevel(String evaluationLevel) {
            this.evaluationLevel = evaluationLevel;
        }

        public String getMerchantReply() {
            return merchantReply;
        }

        public void setMerchantReply(String merchantReply) {
            this.merchantReply = merchantReply;
        }

        public boolean isIsReply() {
            return isReply;
        }

        public void setIsReply(boolean isReply) {
            this.isReply = isReply;
        }

        public long getRawAddTime() {
            return rawAddTime;
        }

        public void setRawAddTime(long rawAddTime) {
            this.rawAddTime = rawAddTime;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public List<String> getImgUrls() {
            return imgUrls;
        }

        public void setImgUrls(List<String> imgUrls) {
            this.imgUrls = imgUrls;
        }
    }
}

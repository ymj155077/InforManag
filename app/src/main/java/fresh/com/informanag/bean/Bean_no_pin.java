package fresh.com.informanag.bean;

import java.util.List;

/**
 * Created by Administrator
 * on 2019/6/5 0005
 */
public class Bean_no_pin {


    /**
     * message : 执行成功
     * code : 200
     * data : [{"evaluationNo":"1j22qp740kS081bqq7qb","goodsNo":"2","userNo":"1j9sjw5mp0S041bqq7q6","nickname":"杨幂7","content":"额额","evaluationLevel":"1","imgUrls":["1","2"],"isReply":false,"rawAddTime":1559751127000,"headImg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLbTp9nZtjwVklOgGyDpqoqgDLUniaV36Fx50icJajkjibybXHwGKBE1Q4iccO76V60OjXwIgPSuicTsyA/132"},{"evaluationNo":"1ja3qp740kS081bqq7qb","goodsNo":"2","userNo":"1j9sjw5mp0S041bqq7q6","nickname":"杨幂5","content":"额额","evaluationLevel":"3","imgUrls":["1","2"],"merchantReply":"好的","isReply":false,"rawAddTime":1559751125000,"headImg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLbTp9nZtjwVklOgGyDpqoqgDLUniaV36Fx50icJajkjibybXHwGKBE1Q4iccO76V60OjXwIgPSuicTsyA/132"},{"evaluationNo":"1ja2qp240kS081bqq7qb","goodsNo":"2","userNo":"1j9sjw5mp0S041bqq7q6","nickname":"杨幂4","content":"额额","evaluationLevel":"2","imgUrls":["1","2"],"isReply":false,"rawAddTime":1559751125000,"headImg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLbTp9nZtjwVklOgGyDpqoqgDLUniaV36Fx50icJajkjibybXHwGKBE1Q4iccO76V60OjXwIgPSuicTsyA/132"},{"evaluationNo":"1ja2qp740kS081bqq7qb","goodsNo":"2","userNo":"1j9sjw5mp0S041bqq7q6","nickname":"杨幂3","content":"额额","evaluationLevel":"3","imgUrls":["1","2"],"merchantReply":"商户回复","isReply":false,"rawAddTime":1559751124000,"headImg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLbTp9nZtjwVklOgGyDpqoqgDLUniaV36Fx50icJajkjibybXHwGKBE1Q4iccO76V60OjXwIgPSuicTsyA/132"},{"evaluationNo":"1ja2q2740kS081bqq7qb","goodsNo":"2","userNo":"1j9sjw5mp0S041bqq7q6","nickname":"杨幂2","content":"额额","evaluationLevel":"5","imgUrls":["1","2"],"isReply":false,"rawAddTime":1559751123000,"headImg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLbTp9nZtjwVklOgGyDpqoqgDLUniaV36Fx50icJajkjibybXHwGKBE1Q4iccO76V60OjXwIgPSuicTsyA/132"}]
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
         * evaluationNo : 1j22qp740kS081bqq7qb
         * goodsNo : 2
         * userNo : 1j9sjw5mp0S041bqq7q6
         * nickname : 杨幂7
         * content : 额额
         * evaluationLevel : 1
         * imgUrls : ["1","2"]
         * isReply : false
         * rawAddTime : 1559751127000
         * headImg : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLbTp9nZtjwVklOgGyDpqoqgDLUniaV36Fx50icJajkjibybXHwGKBE1Q4iccO76V60OjXwIgPSuicTsyA/132
         * merchantReply : 好的
         */

        private String evaluationNo;
        private String goodsNo;
        private String userNo;
        private String nickname;
        private String content;
        private String evaluationLevel;
        private boolean isReply;
        private long rawAddTime;
        private String headImg;
        private String merchantReply;
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

        public String getMerchantReply() {
            return merchantReply;
        }

        public void setMerchantReply(String merchantReply) {
            this.merchantReply = merchantReply;
        }

        public List<String> getImgUrls() {
            return imgUrls;
        }

        public void setImgUrls(List<String> imgUrls) {
            this.imgUrls = imgUrls;
        }
    }
}

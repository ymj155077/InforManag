package fresh.com.informanag.bean;

import java.util.List;

/**
 * Created by Administrator
 * on 2019/6/11 0011
 */
public class Bean_wait {


    /**
     * code :
     * data : [{"applyTime":"","goodsCount":0,"goodsImg":"","goodsName":"","orderAmount":0,"orderNo":"","realAmount":0,"state":""}]
     * message :
     */

    private String code;
    private String message;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * applyTime :
         * goodsCount : 0
         * goodsImg :
         * goodsName :
         * orderAmount : 0
         * orderNo :
         * realAmount : 0
         * state :
         */

        private String applyTime;
        private int goodsCount;
        private String goodsImg;
        private String goodsName;
        private int orderAmount;
        private String orderNo;
        private int realAmount;
        private String state;

        public String getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(String applyTime) {
            this.applyTime = applyTime;
        }

        public int getGoodsCount() {
            return goodsCount;
        }

        public void setGoodsCount(int goodsCount) {
            this.goodsCount = goodsCount;
        }

        public String getGoodsImg() {
            return goodsImg;
        }

        public void setGoodsImg(String goodsImg) {
            this.goodsImg = goodsImg;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public int getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(int orderAmount) {
            this.orderAmount = orderAmount;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getRealAmount() {
            return realAmount;
        }

        public void setRealAmount(int realAmount) {
            this.realAmount = realAmount;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}

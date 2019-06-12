package fresh.com.informanag.bean;

/**
 * Created by Administrator
 * on 2019/6/4 0004
 */
public class Bean_merchant {


    /**
     * message : 执行成功
     * code : 200
     * data : {"isMerchant":false,"isApplied":true,"state":"UNDER_REVIEW","memo":null,"merchantNo":null}
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
         * isMerchant : false
         * isApplied : true
         * state : UNDER_REVIEW
         * memo : null
         * merchantNo : null
         */

        private boolean isMerchant;
        private boolean isApplied;
        private String state;
        private String memo;
        private String merchantNo;

        public boolean isIsMerchant() {
            return isMerchant;
        }

        public void setIsMerchant(boolean isMerchant) {
            this.isMerchant = isMerchant;
        }

        public boolean isIsApplied() {
            return isApplied;
        }

        public void setIsApplied(boolean isApplied) {
            this.isApplied = isApplied;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getMerchantNo() {
            return merchantNo;
        }

        public void setMerchantNo(String merchantNo) {
            this.merchantNo = merchantNo;
        }
    }
}

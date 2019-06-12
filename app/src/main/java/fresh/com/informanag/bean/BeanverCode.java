package fresh.com.informanag.bean;

/**
 * Created by Administrator
 * on 2019/5/27 0027
 */
public class BeanverCode {


    /**
     * message : 执行成功
     * code : 200
     * data : {"phone":"18725621750","code":"656689","sendTime":1558982425000}
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
         * phone : 18725621750
         * code : 656689
         * sendTime : 1558982425000
         */

        private String phone;
        private String code;
        private long sendTime;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public long getSendTime() {
            return sendTime;
        }

        public void setSendTime(long sendTime) {
            this.sendTime = sendTime;
        }
    }
}

package fresh.com.informanag.bean;

/**
 * Created by Administrator
 * on 2019/5/30 0030
 */
public class Bean_wei_Login {


    /**
     * message : 执行成功
     * code : 200
     * data : {"userNo":"1j9nyqnrksS041bqq7q6","username":null,"headImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/1U4VwrKCTd7dloPB8B4uib2ia6eGVrIyYiajeNwFK6hdoicuYhKhqlXGPKuLBMMZHYrudXLVfQuQdsKgbVBAamUEfA/132","nickname":null,"phone":null,"birthday":null,"wxOpenId":"oGvwp6H_q0SPyaUWZrrXpIuLLz-4","type":null,"status":null,"loginStatus":null,"lastLoginTime":null,"accessToken":"18f5ef0d-e1c7-4fa5-9523-09bc07f5a510"}
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
         * userNo : 1j9nyqnrksS041bqq7q6
         * username : null
         * headImg : http://thirdwx.qlogo.cn/mmopen/vi_32/1U4VwrKCTd7dloPB8B4uib2ia6eGVrIyYiajeNwFK6hdoicuYhKhqlXGPKuLBMMZHYrudXLVfQuQdsKgbVBAamUEfA/132
         * nickname : null
         * phone : null
         * birthday : null
         * wxOpenId : oGvwp6H_q0SPyaUWZrrXpIuLLz-4
         * type : null
         * status : null
         * loginStatus : null
         * lastLoginTime : null
         * accessToken : 18f5ef0d-e1c7-4fa5-9523-09bc07f5a510
         */

        private String userNo;
        private Object username;
        private String headImg;
        private Object nickname;
        private Object phone;
        private Object birthday;
        private String wxOpenId;
        private Object type;
        private Object status;
        private Object loginStatus;
        private Object lastLoginTime;
        private String accessToken;

        public String getUserNo() {
            return userNo;
        }

        public void setUserNo(String userNo) {
            this.userNo = userNo;
        }

        public Object getUsername() {
            return username;
        }

        public void setUsername(Object username) {
            this.username = username;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public String getWxOpenId() {
            return wxOpenId;
        }

        public void setWxOpenId(String wxOpenId) {
            this.wxOpenId = wxOpenId;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public Object getLoginStatus() {
            return loginStatus;
        }

        public void setLoginStatus(Object loginStatus) {
            this.loginStatus = loginStatus;
        }

        public Object getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(Object lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }
}

package fresh.com.informanag.bean;

/**
 * Created by Administrator
 * on 2019/5/27 0027
 */
public class Bean_infor {


    /**
     * message : 执行成功
     * code : 200
     * data : {"userNo":"1jagjzh51cS041bqq7q6","username":null,"headImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/1U4VwrKCTd7dloPB8B4uib2ia6eGVrIyYiajeNwFK6hdoicuYhKhqlXGPKuLBMMZHYrudXLVfQuQdsKgbVBAamUEfA/132","nickname":null,"phone":null,"birthday":null,"wxOpenId":"oGvwp6H_q0SPyaUWZrrXpIuLLz-4","type":null,"status":null,"loginStatus":null,"lastLoginTime":null,"accessToken":"b611f1aa-ecac-4b44-9f37-c141099b1269"}
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
         * userNo : 1jagjzh51cS041bqq7q6
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
         * accessToken : b611f1aa-ecac-4b44-9f37-c141099b1269
         */

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
        private String accessToken;

        public String getUserNo() {
            return userNo;
        }

        public void setUserNo(String userNo) {
            this.userNo = userNo;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getWxOpenId() {
            return wxOpenId;
        }

        public void setWxOpenId(String wxOpenId) {
            this.wxOpenId = wxOpenId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLoginStatus() {
            return loginStatus;
        }

        public void setLoginStatus(String loginStatus) {
            this.loginStatus = loginStatus;
        }

        public String getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(String lastLoginTime) {
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

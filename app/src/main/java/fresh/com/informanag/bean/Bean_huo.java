package fresh.com.informanag.bean;

/**
 * Created by Administrator
 * on 2019/5/31 0031
 */
public class Bean_huo {


    /**
     * message : 执行成功
     * code : 200
     * data : {"userNo":"1j9shm63u0S041bqq7q6","addressNo":"1j9sna0w1wS041bqq7q6","consignee":"吞吞吐吐吞","tag":null,"detail":"纯纯粹粹","phone":"18725621750","state":"ENABLED","weight":null,"nation":null,"province":"","city":"北京市东城区","district":"","street":"体育馆路甲2号","streetNumber":null,"longitude":"116.426403","latitude":"39.882828"}
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
         * addressNo : 1j9sna0w1wS041bqq7q6
         * consignee : 吞吞吐吐吞
         * tag : null
         * detail : 纯纯粹粹
         * phone : 18725621750
         * state : ENABLED
         * weight : null
         * nation : null
         * province :
         * city : 北京市东城区
         * district :
         * street : 体育馆路甲2号
         * streetNumber : null
         * longitude : 116.426403
         * latitude : 39.882828
         */

        private String userNo;
        private String addressNo;
        private String consignee;
        private Object tag;
        private String detail;
        private String phone;
        private String state;
        private Object weight;
        private Object nation;
        private String province;
        private String city;
        private String district;
        private String street;
        private Object streetNumber;
        private String longitude;
        private String latitude;

        public String getUserNo() {
            return userNo;
        }

        public void setUserNo(String userNo) {
            this.userNo = userNo;
        }

        public String getAddressNo() {
            return addressNo;
        }

        public void setAddressNo(String addressNo) {
            this.addressNo = addressNo;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public Object getTag() {
            return tag;
        }

        public void setTag(Object tag) {
            this.tag = tag;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Object getWeight() {
            return weight;
        }

        public void setWeight(Object weight) {
            this.weight = weight;
        }

        public Object getNation() {
            return nation;
        }

        public void setNation(Object nation) {
            this.nation = nation;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public Object getStreetNumber() {
            return streetNumber;
        }

        public void setStreetNumber(Object streetNumber) {
            this.streetNumber = streetNumber;
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
    }
}

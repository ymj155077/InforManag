package fresh.com.informanag.bean;

import java.util.List;

/**
 * Created by Administrator
 * on 2019/5/31 0031
 */
public class Bean_address_list {


    /**
     * message : 执行成功
     * code : 200
     * data : [{"userNo":"1j9owgfkt0S041bqq7q6","addressNo":"1j9rzn23w4S041bqq7q6","consignee":"郁美净","tag":null,"detail":"冯风风光光","phone":"18725621750","state":"ENABLED","weight":null,"nation":null,"province":"","city":"新溉大道与红锦大道交叉口东50米","district":"","street":"重庆市渝北区","streetNumber":null,"longitude":"106.520637","latitude":"29.59929"}]
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
         * userNo : 1j9owgfkt0S041bqq7q6
         * addressNo : 1j9rzn23w4S041bqq7q6
         * consignee : 郁美净
         * tag : null
         * detail : 冯风风光光
         * phone : 18725621750
         * state : ENABLED
         * weight : null
         * nation : null
         * province :
         * city : 新溉大道与红锦大道交叉口东50米
         * district :
         * street : 重庆市渝北区
         * streetNumber : null
         * longitude : 106.520637
         * latitude : 29.59929
         */

        private String userNo;
        private String addressNo;
        private String consignee;
        private String tag;
        private String detail;
        private String phone;
        private String state;
        private String weight;
        private String nation;
        private String province;
        private String city;
        private String district;
        private String street;
        private String streetNumber;
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

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
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

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
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

        public String getStreetNumber() {
            return streetNumber;
        }

        public void setStreetNumber(String streetNumber) {
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

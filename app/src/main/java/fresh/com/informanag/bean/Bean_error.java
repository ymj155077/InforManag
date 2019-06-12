package fresh.com.informanag.bean;

/**
 * Created by Administrator
 * on 2019/5/30 0030
 */
public class Bean_error {


    /**
     * code : 401
     * message : 调用服务超时，请重试！
     */

    private String code;
    private String message;

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
}

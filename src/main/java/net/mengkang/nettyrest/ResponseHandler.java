package net.mengkang.nettyrest;

import net.mengkang.nettyrest.vo.Info;
import net.mengkang.nettyrest.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class ResponseHandler {

    private static final Logger logger = LoggerFactory.getLogger(ResponseHandler.class);

    private static final Map<Integer, String> codeMap = new HashMap<>();


    public static final int CREATED_SUCCESS       = 201;
    public static final int UNKNOWN_ERROR         = 1000;
    public static final int API_NOT_FOUND         = 1001;
    public static final int API_CAN_NOT_BE_NULL   = 1002;
    public static final int PARAM_FORMAT_ERROR    = 1003;
    public static final int PARAM_CAN_NOT_BE_NULL = 1004;
    public static final int VERSION_IS_TOO_LOW    = 1005;
    public static final int REQUEST_MODE_ERROR    = 1006;
    public static final int API_SERVER_ERROR      = 1007;

    static {
        codeMap.put(CREATED_SUCCESS, "created success");
        codeMap.put(UNKNOWN_ERROR, "unknown error");
        codeMap.put(API_NOT_FOUND, "the api can't be found");
        codeMap.put(API_CAN_NOT_BE_NULL, "can't request without a api name");
        codeMap.put(PARAM_FORMAT_ERROR, "param : %s format error");
        codeMap.put(PARAM_CAN_NOT_BE_NULL, "param : %s can't be null");
        codeMap.put(VERSION_IS_TOO_LOW, "version is too low, please update your client");
        codeMap.put(REQUEST_MODE_ERROR, "the http request method is not allow");
        codeMap.put(API_SERVER_ERROR, "api server error");
    }

    public static Object error(int errorCode) {
        return response(errorCode);
    }

    /**
     * error for {@link #PARAM_FORMAT_ERROR} {@link #PARAM_CAN_NOT_BE_NULL}
     *
     * @param parameter
     * @return
     */
    public static Result error(int errorCode,String parameter) {
        Result result = new Result<>(new Info());
        result.getInfo()
                .setCode(errorCode)
                .setCodeMessage(String.format(codeMap.get(errorCode), parameter));
        return result;
    }

    public static Result success(){
        return new Result<>(new Info());
    }

    public static Object success(int code){
        return response(code);
    }

    public static Object response(int code) {
        Result result = new Result<>(new Info());
        result.getInfo().setCode(code).setCodeMessage(codeMap.get(code));
        return result;
    }
}
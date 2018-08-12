package top.deramertn9527.center.web.config.http;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.deramertn9527.center.common.exception.WhzServerException;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JSONObject serviceException(Throwable t) {
        log.error("Server Exception: ", t);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 500);
        jsonObject.put("message", t.getMessage());
        return jsonObject;
    }

    @ExceptionHandler(value = WhzServerException.class)
    @ResponseBody
    public JSONObject serviceException(WhzServerException e) {
        log.error("Server Exception: ", e);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", e.getCode());
        jsonObject.put("message", e.getMess());
        return jsonObject;
    }
}
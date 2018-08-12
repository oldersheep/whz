package top.deramertn9527.center.common.exception;

import lombok.Getter;
import top.deramertn9527.center.common.exception.enums.ExceptionEnum;

/**
 * 服务端异常类
 * <p>
 */
@Getter
public class WhzServerException extends RuntimeException {

    private static final long serialVersionUID = 5904750613753251808L;

    /**
     * 异常 code
     */
    private final long code;

    /**
     * 异常 描述信息
     */
    private final String mess;

    /**
     * 标签中台 服务端 异常 构造方法
     *
     * @param code 异常码
     * @param mess 异常信息
     */
    public WhzServerException(long code, String mess) {
        super(mess);
        this.code = code;
        this.mess = mess;
    }

    /**
     * 标签中台 服务端 异常 构造方法
     *
     * @param cause Throwable
     */
    public WhzServerException(Throwable cause) {
        super(cause);
        this.code = ExceptionEnum.SYSTEM_ERROR.getErrCode();
        this.mess = cause.toString();
    }

    /**
     * 标签中台 服务端 异常 构造方法
     *
     * @param exceptionEnum 异常枚举类
     */
    public WhzServerException(ExceptionEnum exceptionEnum) {
        super("errCode:" + exceptionEnum.getErrCode() +
                ", errMessage:" + exceptionEnum.getErrZHMessage());
        this.code = exceptionEnum.getErrCode();
        this.mess = exceptionEnum.getErrZHMessage();
    }
}

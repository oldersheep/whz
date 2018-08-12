package top.deramertn9527.center.common.exception;


import top.deramertn9527.center.common.exception.enums.ExceptionEnum;

/**
 * 检查异常类
 */
public class CheckException extends WhzServerException {

    private static final long serialVersionUID = -2210856991031392402L;

    public CheckException(long code, String message) {
        super(code, message);
    }

    public CheckException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
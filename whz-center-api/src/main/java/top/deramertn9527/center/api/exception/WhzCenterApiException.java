package top.deramertn9527.center.api.exception;


public class WhzCenterApiException extends RuntimeException {
    private static final long serialVersionUID = 2738957113732614499L;

    private static final long DEFAULT_EXCEPTION_CODE = 500;

    /**
     * 异常码
     */
    private final long code;

    /**
     * 构造方法 抛出系统异常码
     * <p>
     * 不指定异常码使用默认的异常码：500
     *
     * @param message 抛出异常信息
     */
    public WhzCenterApiException(String message) {
        this(DEFAULT_EXCEPTION_CODE, message);
    }

    /**
     * 构造方法 指定异常码和异常信息
     *
     * @param code    异常码
     * @param message 异常信息
     */
    public WhzCenterApiException(long code, String message) {
        super(message);
        this.code = code;
    }

    public long getCode() {
        return code;
    }
}

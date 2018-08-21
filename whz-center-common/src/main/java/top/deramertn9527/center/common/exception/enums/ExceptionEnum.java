package top.deramertn9527.center.common.exception.enums;

/**
 * 异常枚举
 */
public enum ExceptionEnum {

    /**
     * 参数不合法！
     */
    CHECK_PARAM_ERROR(403, "参数不合法！", ""),

    CHECK_PARAM_GROUP_NAME_ERROR(403010001, "groupName为空", ""),
    CHECK_PARAM_NAME_SERVER_ERROR(403010002, "nameServer为空", ""),
    CHECK_PARAM_TOPIC_ERROR(403010002, "topic为空", ""),



    /**
     * 不能为空
     */
    NOT_NULL(404020000, "不能为空!", "not null"),
    /**
     * 必须为空
     */
    IS_NULL(404030000, "为空!", "is null"),
    /**
     * 长度不为0
     */
    NOT_EMPTY(404050000, "长度不为0!", "the size is not zero"),
    /**
     * 结果为真
     */
    IS_TRUE(404060000, "结果为真", "the result is true"),
    /**
     * 长度为0
     */
    IS_EMPTY(404070000, "长度为0", "the result is true"),

    NOT_TRUE(404080000, "结果不为真", "the result is not true"),
    /**
     * 系统异常
     */
    SYSTEM_ERROR(500, "系统异常!", "server exception"),

    DELETE_ERROR(502, "删除数据异常", "delete_exception"),
    ;

    private long errCode;
    private String errZHMessage;
    private String errENMessage;

    ExceptionEnum(long errCode, String errZHMessage, String errENMessage) {
        this.errCode = errCode;
        this.errZHMessage = errZHMessage;
        this.errENMessage = errENMessage;
    }

    public long getErrCode() {
        return errCode;
    }

    public String getErrZHMessage() {
        return errZHMessage;
    }

    public String getErrENMessage() {
        return errENMessage;
    }
}

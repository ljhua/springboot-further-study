package me.junhua.common.enums;

public enum ThirdResponseStatus implements EnumType<ThirdResponseStatus, Integer, String> {

    FAILURE(0, "发送请求失败"),
    SUCCESS(1, "请求成功并获得有效应答报文"),
    UNKNOWN(2, "请求成功，未收到应答 或者 发生未知异常"),
    SYSTEM_ERROR(3, "系统错误"),
    ARGS_ERROR(4, "调用参数错误"),
    TRIPARTITE_ERROR(5, "HTTP请求失败调用第三方失败"),
    SIGN_ERROR(6, "签名出现未知异常"),
    BEAN_TO_XML_ERROR(7, "转换为XML出现未知异常"),
    XML_TO_BEAN_ERROR(8, "转换为实体类出现未知异常"),
    ENCRYPT_ERROR(9, "des加密出现未知异常"),
    DECRYPT_ERROR(10, "des解密出现异常");


    private int status;
    private String desc;

    ThirdResponseStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }


    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}

package me.junhua.common.enums;


/**
 * Created by yankaili on 2017/8/21.
 */
public enum ThirdPayResponseStatus implements EnumTypeTriple<ThirdPayResponseStatus, String, Integer, String> {

    SUCCESS("0000", 0, "请求成功并获得有效应答报文"),
    // 需要重新查询交易状态
    UNKNOW("3000", 2, "请求成功，未收到应答 或者 发生未知异常"),

    SEND_FAIL("3003", 1, "发送请求失败"),
    // 系统参数问题 请联系管理员
    SYS_FAIL("4000", 1, "系统参数错误"),
    // 用户填写参数异常
    USE_FAIL("5000", 1, "用户填写参数异常"),
    THIRD_FAIL("3001", 1, "HTTP请求失败调用第三方失败");


    private String status;

    private Integer value;

    private String desc;

    ThirdPayResponseStatus(String status, Integer value, String desc) {
        this.status = status;
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String getType() {
        return status;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}

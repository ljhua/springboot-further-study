package me.junhua.common.enums;

public class ThirdInvokeResult<P, R> {

    // 请求参数
    private P p;
    // 请求结果
    private R r;
    // 请求状态
    private ThirdResponseStatus thirdResponseStatus;


    public P getP() {
        return p;
    }

    public void setP(P p) {
        this.p = p;
    }

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }

    public ThirdResponseStatus getThirdResponseStatus() {
        return thirdResponseStatus;
    }

    public void setThirdResponseStatus(ThirdResponseStatus thirdResponseStatus) {
        this.thirdResponseStatus = thirdResponseStatus;
    }
}

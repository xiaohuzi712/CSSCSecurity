package controller.enumerate;

/**
 * Created by wenqing on 2016/5/26.
 * 返回状态枚举值
 */
public enum ResponseStatusEnum {
    /** 调用成功 */
    OK(0),
    /** 服务器处理异常 */
    SERVER_ERROR(1),
    /** 数据库访问异常 */
    DB_ERROR(2),
    /** 未登陆状态不能访问 */
    NO_LOGIN(3);

    private int value;

    ResponseStatusEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}

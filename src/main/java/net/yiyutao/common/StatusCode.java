package net.yiyutao.common;

import java.util.Objects;

/**
 * @author masterYI
 * Date: 2017/9/18
 * Time: 10:20
 * Description:
 */
public enum StatusCode {

    Success(1000, "成功"),
    SysException(-1, "系统错误"),
    DBException(-2, "数据库错误"),
    AccessDenied(-3, "拒绝访问"),
    UndefinedAction(-4, "未定义操作错误"),
    UndefinedParam(-5, "未知参数"),
    UnknownException(-6, "未知异常"),
    IPCreateError(-7, "IP生成错误"),
    MissingParams(-8, "缺少参数"),
    SqlExecuteError(-9, "数据库语句执行错误(非语法,可能是受影响行数为0)"),

    /**
     * 文件系统管理
     * -1351~-1370
     */
    UploadFileFaild (-1351, "上传文件失败"),
    FileIsNull (-1352, "请选择上传文件"),

    /**
     * 登录错误代码段[-1371,-1390]
     */
    UsernameOrPassNotNull (-1371, "用户名或密码不能为空"),
    UsernameNotExsits (-1372, "用户名不存在"),
    UsernameOrPassError (-1373, "用户名或密码错误"),
    UserStatusFail (-1374, "用户未启用"),
    UserRoleStatusFail (-1375, "用户角色状态不可用"),
    UserLoginFail (-1376, "用户登录失败"),
    Unauthorized (-1377, "没有权限"),
    NotLogon (-1378, "未登录"),

    /**
     * 外部接口使用
     **/
    TimeStampOver (1901, "请求时间戳超时"),
    LackParam (1902, "参数校验异常"),
    SignFail (1903, "Sign验证错误"),
    ParamIsNull (1904, "不能为空"),
    TerminalUnregistered (1904, "取票机终端未注册"),
    TerminalDisable (1905, "取票机终端已禁用"),
    ServerAbnormal (1909, "服务器处理异常"),;


    public Integer code;
    public String message;

    private StatusCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(Integer code) {
        for (StatusCode rm : values()) {
            if (Objects.equals(rm.code, code)) {
                return rm.message;
            }
        }
        return "";
    }

    public static StatusCode getStatusCode(Integer code) {
        for (StatusCode rm : values()) {
            if (Objects.equals(rm.code, code)) {
                return rm;
            }
        }
        return null;
    }

    public String sCode() {
        return String.valueOf(code);
    }

    @Override
    public String toString() {
        return "错误代码：" + code + " 描述: " + message;
    }
}

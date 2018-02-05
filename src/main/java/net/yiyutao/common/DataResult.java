package net.yiyutao.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author masterYI
 * Date: 2017/9/18
 * Time: 10:19
 * Description:
 */
@Component
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class DataResult implements Serializable {

    private static final long serialVersionUID = 7581164121147997425L;
    private int code;
    private String msg;
    private int pageNum = 0;
    private int pageSize = -1;
    private Long total;
    private Object data;

    public void setStatusCode(StatusCode statusCode) {
        this.code = statusCode.code;
        this.msg = statusCode.message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}

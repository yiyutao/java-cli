package net.yiyutao.common;

/**
 * @author: masterYI
 * @date: 2017/10/30
 * @time: 14:34
 * Description:ID拼接业务码
 */
public enum IdCodeEnum {

	
    ORDER("111"),
	ADJUSTPRICE("01");

    private String code;

    IdCodeEnum(String code) {
        this.code = code;
    }

    public String getCode(){
        if (code == null) {
            code = "";
        }
        return code;
    }
}

package net.yiyutao.common;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Objects;

/**
 * Author: PCF
 * Date: 2017/11/27 10:10 
 * Description:终端页面类型对应信息
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PageTypeCode {

	MOBILE(1, "手机号取票+订单号取票"),
	ORDER(2, "订单号取票+手机号取票"),
	ALLMOBILE(3, "手机号取票"),
	ALLORDER(4, "订单号取票");

	public Integer code;
	public String pageName;
	
	private PageTypeCode(Integer code, String pageName) {
		this.code = code;
		this.pageName = pageName;
	}

    public static PageTypeCode getPageName(Integer code){
        for (PageTypeCode rm : values()){
            if (Objects.equals(rm.code, code)){
                return rm;
            }
        }
        return null;
    }
    public String sCode(){
        return String.valueOf(code);
    }

}

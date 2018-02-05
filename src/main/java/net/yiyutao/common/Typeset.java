package net.yiyutao.common;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Objects;

/**
 * Created by 37017 on 2017/11/8.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  Typeset {
    NORMAL(0,"正常"),
    SZS(1,"石嘴山"),
    LL(2,"醴陵")
    ;
    public   Integer code;
    public   String typesetName;

    Typeset(Integer code, String typesetName) {
        this.code = code;
        this.typesetName = typesetName;
    }
    public   static Typeset  getTypeset(Integer code){
        for (Typeset typeset : values()) {
            if(Objects.equals(code,typeset.code)){
                return typeset;
            }
        }
        return null;
    }

}

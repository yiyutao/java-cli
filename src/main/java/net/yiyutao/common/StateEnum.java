package net.yiyutao.common;

/**
 * @author: masterYI
 * @date: 2017/10/25
 * @time: 10:33
 * Description:
 */
public enum  StateEnum {

    ENABLE(0),
    DISABLE(1),
    THIRD(2),
    FOURTH(3);

    public int state;

    StateEnum(int state) {
        this.state = state;
    }
}

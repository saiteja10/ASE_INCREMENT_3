package com.ase.domain;

/**
 * Created by Gurrala on 4/5/2015.
 */
public enum OrderStatus {
    ACCEPTED(0), PLACED(1), DELIVERED(2), REJECTED(3), RETURNED(4);

    private int code;

    private OrderStatus(int x) {
        this.code = x;
    }

    public Integer getCode() {
        return code;
    }

    public static OrderStatus getEnum(Integer x) {
        for (OrderStatus stage : OrderStatus.values())
            if (stage.getCode() == x.intValue())
                return stage;
        return null;
    }
}

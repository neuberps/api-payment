package com.caiobruno.payments.domain.enums;

public enum StatusPayment {
    WAITING_PAYMENT(0),
    PAID(1),
    CANCELED(2);

    private int code ;

    private StatusPayment(int code ) {
        this.code = code;
    }


    public int getCode() {
        return code ;

    }

    public static StatusPayment  valueOf(int code ) {
        for (StatusPayment value : StatusPayment.values()) {
            if(value.getCode() == code ) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid order Satatus code");
    }
}

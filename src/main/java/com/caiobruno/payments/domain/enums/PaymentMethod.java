package com.caiobruno.payments.domain.enums;

public enum PaymentMethod {

    card(0),
    pix(1),
    ticket(2);

    private int code ;

    private PaymentMethod(int code ) {
        this.code = code;
    }


    public int getCode() {
        return code ;

    }

    public static PaymentMethod  valueOf(int code ) {
        for (PaymentMethod value : PaymentMethod.values()) {
            if(value.getCode() == code ) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid order Satatus code");
    }
}

package com.puer.pay.ee;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PayStatusEnum {

    STATUS_UNPAID(1,"未支付"),

    STATUS_PAYING(2,"支付(第三方受理)中"),

    STATUS_PAID_SUCCESS(3,"支付成功"),

    STATUS_PAID_FAIL(4,"支付失败"),

    STATUS_REFUND_ING(5,"退款申请中"),

    STATUS_REFUND_SUCCESS(6,"退款成功");

    private int code;

    private String name;
}

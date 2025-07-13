package com.shubham.ecommerce.payment;

import com.shubham.ecommerce.customer.CustomerResponse;
import com.shubham.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}

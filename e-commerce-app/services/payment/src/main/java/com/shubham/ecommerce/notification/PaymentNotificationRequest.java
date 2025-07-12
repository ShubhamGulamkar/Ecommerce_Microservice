package com.shubham.ecommerce.notification;

import com.shubham.ecommerce.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String CustomerLastName,
        String CustomerEmail

) {
}

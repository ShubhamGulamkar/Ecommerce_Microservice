package com.shubham.ecommerce.kafka;

import com.shubham.ecommerce.customer.CustomerResponse;
import com.shubham.ecommerce.order.PaymentMethod;
import com.shubham.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}

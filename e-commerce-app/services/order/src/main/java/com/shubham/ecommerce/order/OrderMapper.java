package com.shubham.ecommerce.order;

import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder (OrderRequest request){
        return Order.builder()
                .id(request.Id())
                .customerId((request.customerId()))
                .reference(request.reference())
                .totalAmount((request.amount()))
                .paymentMethod((request.paymentMethod()))
                .build();



    }
}

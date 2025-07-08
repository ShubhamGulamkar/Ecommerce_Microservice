package com.shubham.ecommerce.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {


    public Integer CreateOrder(@Valid OrderRequest request) {
        return null;
    }
}

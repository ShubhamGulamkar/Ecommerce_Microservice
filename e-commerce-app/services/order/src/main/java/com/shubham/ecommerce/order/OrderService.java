package com.shubham.ecommerce.order;

import com.shubham.ecommerce.customer.CustomerClient;
import com.shubham.ecommerce.exception.BusinessException;
import com.shubham.ecommerce.kafka.OrderConfirmation;
import com.shubham.ecommerce.kafka.OrderProducer;
import com.shubham.ecommerce.orderline.OrderLineRequest;
import com.shubham.ecommerce.orderline.OrderLineService;
import com.shubham.ecommerce.product.ProductClient;
import com.shubham.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public final CustomerClient customerClient;

    private final ProductClient productClient;

    private final OrderMapper mapper;

    private final OrderLineService orderLineService;

    private final OrderProducer orderProducer;


    public Integer CreateOrder(@Valid OrderRequest request) {

        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(()->new BusinessException("CAnnot create order :: No Customer exists with the provided ID"));

        var purchasedProducts  = this.productClient.purchaseProducts(request.products());

        var order = this.repository.save(mapper.toOrder(request));

        for(PurchaseRequest purchaseRequest : request.products())
        {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }


        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                   request.reference(),
                   request.amount(),
                   request.paymentMethod(),
                   customer,
                   purchasedProducts
                )
        );

        return order.getId();

    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(()->new EntityNotFoundException(String.format("No Order found with the provided ID : %d", orderId)));
    }
}

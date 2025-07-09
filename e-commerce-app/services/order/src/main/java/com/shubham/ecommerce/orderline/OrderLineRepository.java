package com.shubham.ecommerce.orderline;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine,Integer> {
}

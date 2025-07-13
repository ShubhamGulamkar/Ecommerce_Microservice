package com.shubham.ecommerce.kafka;

import com.shubham.ecommerce.kafka.order.OrderConfirmation;
import com.shubham.ecommerce.kafka.payment.PaymentConfirmation;
import com.shubham.ecommerce.notification.Notification;
import com.shubham.ecommerce.notification.NotificationRepository;
import com.shubham.ecommerce.notification.NotificationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.shubham.ecommerce.notification.NotificationType.ORDER_CONFIGURATION;
import static com.shubham.ecommerce.notification.NotificationType.PAYMENT_CONFIGURATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository repository;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation)
    {
        log.info(String.format("Consuming the message from payment Topic:: %s",paymentConfirmation));
        repository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIGURATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation)
    {
        log.info(String.format("Consuming the message from payment Topic:: %s",orderConfirmation));
        repository.save(
                Notification.builder()
                        .type(ORDER_CONFIGURATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

    }
}

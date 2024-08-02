package ir.mostafa.semnani.order.service;

public interface OrderKafkaService {

    void publishOrderCreatedEvent(String orderCreatedEvent);

}

package ir.mostafa.semnani.inventory.service;

public interface OrderKafkaService {

    void handleOrderCreatedEvent(String orderCreatedEvent);

}

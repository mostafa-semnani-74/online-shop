package ir.mostafa.semnani.order.service;

public interface InventoryKafkaService {

    void handleQuantityReservedEvent(String quantityReservedEvent);

}

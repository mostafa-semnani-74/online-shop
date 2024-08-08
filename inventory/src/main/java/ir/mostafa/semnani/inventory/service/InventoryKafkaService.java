package ir.mostafa.semnani.inventory.service;

public interface InventoryKafkaService {

    void publishQuantityReservedEvent(String quantityReservedEvent);

}

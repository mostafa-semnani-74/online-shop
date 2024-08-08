package ir.mostafa.semnani.inventory.service.impl;

import ir.mostafa.semnani.inventory.service.InventoryKafkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryKafkaServiceImpl implements InventoryKafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void publishQuantityReservedEvent(String quantityReservedEvent) {
        kafkaTemplate.send("inventory", quantityReservedEvent);
        log.info("quantity reserved event message sent to inventory topic with body {}", quantityReservedEvent);
    }

}

package ir.mostafa.semnani.order.service.impl;

import ir.mostafa.semnani.order.service.OrderKafkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderKafkaServiceImpl implements OrderKafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void publishOrderCreatedEvent(String orderCreatedEvent) {
        Thread.ofVirtual()
                .name("publishOrderCreatedEvent-vthread-")
                .start(() -> {
            kafkaTemplate.send("order", orderCreatedEvent);
            log.info("order created : message with body : {} ,sent to kafka", orderCreatedEvent);
        });
    }
}

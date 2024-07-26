package ir.mostafa.semnani.order.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import ir.mostafa.semnani.order.dto.OrderDTO;
import ir.mostafa.semnani.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @CircuitBreaker(name = "save-order", fallbackMethod = "saveFallback")
    public ResponseEntity<OrderDTO> save(@RequestBody OrderDTO orderDTO) {
        return new ResponseEntity<>(orderService.save(orderDTO), HttpStatus.CREATED);
    }

    @PostMapping("cancel/{id}")
    public ResponseEntity<OrderDTO> cancel(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.cancel(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    private ResponseEntity<String> saveFallback(Exception e) {
        return new ResponseEntity<>("save order is done , please try later !!!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

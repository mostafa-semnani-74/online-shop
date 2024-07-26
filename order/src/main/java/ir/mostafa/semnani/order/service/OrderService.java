package ir.mostafa.semnani.order.service;

import ir.mostafa.semnani.order.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO save(OrderDTO orderDTO);

    List<OrderDTO> findAll();

    OrderDTO cancel(Long id);
}

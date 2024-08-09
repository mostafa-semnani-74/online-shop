package ir.mostafa.semnani.order.service;

import ir.mostafa.semnani.order.dto.OrderDTO;
import ir.mostafa.semnani.order.entity.Order;

import java.util.List;

public interface OrderService {
    OrderDTO save(OrderDTO orderDTO);

    List<OrderDTO> findAll();

    Order findById(Long id);

    OrderDTO cancel(Long id);
}

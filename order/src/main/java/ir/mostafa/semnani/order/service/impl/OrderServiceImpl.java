package ir.mostafa.semnani.order.service.impl;

import ir.mostafa.semnani.order.dto.OrderDTO;
import ir.mostafa.semnani.order.repository.OrderRepository;
import ir.mostafa.semnani.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static ir.mostafa.semnani.order.mapper.OrderMapper.*;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        return toDTO(orderRepository.save(toEntity(orderDTO)));
    }

    @Override
    public List<OrderDTO> findAll() {
        return toDTOs(orderRepository.findAll());
    }

}

package ir.mostafa.semnani.order.mapper;

import ir.mostafa.semnani.order.dto.OrderDTO;
import ir.mostafa.semnani.order.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderDTO dto);

    OrderDTO toDTO(Order entity);

    List<OrderDTO> toDTOs(List<Order> orders);

}

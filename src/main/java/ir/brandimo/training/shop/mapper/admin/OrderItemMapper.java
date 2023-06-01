package ir.brandimo.training.shop.mapper.admin;

import ir.brandimo.training.shop.dto.admin.OrderItemDto;
import ir.brandimo.training.shop.entity.OrderItemEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",  collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface OrderItemMapper {

    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "productId", source = "product.id")
    OrderItemDto toDTO(OrderItemEntity orderItem);

    @InheritInverseConfiguration
    OrderItemEntity toEntity(OrderItemDto orderItemDTO);

    List<OrderItemDto> toDTOList(List<OrderItemEntity> orderItems);

    List<OrderItemEntity> toEntityList(List<OrderItemDto> orderItemDTOs);

}

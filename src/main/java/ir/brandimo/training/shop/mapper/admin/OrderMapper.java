package ir.brandimo.training.shop.mapper.admin;

import ir.brandimo.training.shop.dto.admin.OrderDto;
import ir.brandimo.training.shop.entity.OrderEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, uses = {CategoryMapper.class})
public interface OrderMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "bankId", source = "bank.id")
    OrderDto toDTO(OrderEntity product);

    @InheritInverseConfiguration
    OrderEntity toEntity(OrderDto productDTO);


    List<OrderDto> toDTOList(List<OrderEntity> products);

    List<OrderEntity> toEntityList(List<OrderDto> productDTOs);
}

package ir.brandimo.training.shop.mapper.admin;

import ir.brandimo.training.shop.dto.admin.ProductDetailDto;
import ir.brandimo.training.shop.entity.ProductDetailEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",  collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface ProductDetailMapper {

    ProductDetailDto toDTO(ProductDetailEntity productDetail);

    ProductDetailEntity toEntity(ProductDetailDto productDetailDTO);

    List<ProductDetailDto> toDTOList(List<ProductDetailEntity> productDetails);

    List<ProductDetailEntity> toEntityList(List<ProductDetailDto> productDetailDTOs);
}

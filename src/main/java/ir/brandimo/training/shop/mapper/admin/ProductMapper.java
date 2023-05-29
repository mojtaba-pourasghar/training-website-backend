package ir.brandimo.training.shop.mapper.admin;

import ir.brandimo.training.shop.dto.admin.ProductDetailDto;
import ir.brandimo.training.shop.dto.admin.ProductDto;
import ir.brandimo.training.shop.entity.CategoryEntity;
import ir.brandimo.training.shop.entity.ProductDetailEntity;
import ir.brandimo.training.shop.entity.ProductEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, uses = {CategoryMapper.class})
public interface ProductMapper {

    @Mapping(source = "productDetails", target = "images")
    @Mapping(target = "categoryId", source = "category.id")
    ProductDto toDTO(ProductEntity product);

    @InheritInverseConfiguration
    ProductEntity toEntity(ProductDto productDTO);


    List<ProductDto> toDTOList(List<ProductEntity> products);

    List<ProductEntity> toEntityList(List<ProductDto> productDTOs);
}

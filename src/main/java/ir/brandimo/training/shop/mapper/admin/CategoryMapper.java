package ir.brandimo.training.shop.mapper.admin;

import ir.brandimo.training.shop.entity.CategoryEntity;
import ir.brandimo.training.shop.dto.admin.CategoryDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDTO(CategoryEntity category);

    CategoryEntity toEntity(CategoryDto categoryDTO);

    List<CategoryDto> toDTOList(List<CategoryEntity> categories);

    List<CategoryEntity> toEntityList(List<CategoryDto> categoryDTOs);

}

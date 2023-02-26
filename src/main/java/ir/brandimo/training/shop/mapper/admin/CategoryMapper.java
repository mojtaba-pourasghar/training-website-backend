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

    CategoryMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "categoryEntity.id", target = "categoryId")
    @Mapping(source = "categoryEntity.parentCategory.id", target = "parentId")
    CategoryDto categoryEntityToDto(CategoryEntity categoryEntity);
    List<CategoryDto> categoryEntitiesToDtos(List<CategoryEntity> categoryEntities);

    @InheritInverseConfiguration
    CategoryEntity categoryDtoToEntity(CategoryDto categoryDto);
    List<CategoryEntity> categoryDtosToEntities(List<CategoryDto> categoryDtos);

}

package ir.brandimo.training.shop.mapper.admin;

import ir.brandimo.training.shop.dto.admin.VariationDto;
import ir.brandimo.training.shop.entity.VariationEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VariationMapper {

    VariationMapper VARIATION_MAPPER = Mappers.getMapper(VariationMapper.class);

    @Mapping(source = "variationEntity.id", target = "variationId")
    VariationDto variationEntityToDto(VariationEntity variationEntity);
    List<VariationDto> variationEntitiesToDtos(List<VariationEntity> variationEntities);

    @InheritInverseConfiguration
    VariationEntity variationDtoToEntity(VariationDto variationDto);
    List<VariationEntity> variationDtoToEntities(List<VariationDto> variationDtos);
}

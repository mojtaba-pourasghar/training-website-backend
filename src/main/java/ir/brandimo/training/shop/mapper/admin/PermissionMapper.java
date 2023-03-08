package ir.brandimo.training.shop.mapper.admin;

import ir.brandimo.training.shop.dto.admin.PermissionDto;
import ir.brandimo.training.shop.entity.PermissionEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionMapper PERMISSION_MAPPER = Mappers.getMapper(PermissionMapper.class);

    @Mapping(source = "permissionEntity.id", target = "permissionId")
    PermissionDto permissionEntityToDto(PermissionEntity permissionEntity);
    List<PermissionDto> permissionEntitiesToDtos(List<PermissionEntity> categoryEntities);

    @InheritInverseConfiguration
    PermissionEntity permissionDtoToEntity(PermissionDto categoryDto);
    List<PermissionEntity> permissionDtosToEntities(List<PermissionDto> permissionDtos);
}

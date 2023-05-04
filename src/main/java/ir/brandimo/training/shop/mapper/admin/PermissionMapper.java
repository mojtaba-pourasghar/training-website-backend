package ir.brandimo.training.shop.mapper.admin;

import ir.brandimo.training.shop.dto.admin.PermissionDto;
import ir.brandimo.training.shop.entity.PermissionEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",  collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, uses = {PermissionMapper.class})
public interface PermissionMapper {
    PermissionMapper PERMISSION_MAPPER = Mappers.getMapper(PermissionMapper.class);

    @Mapping(source = "permissionEntity.id", target = "permissionId")
    @Mapping(source = "permissionEntity.name", target = "name")
//    @Mapping(source = "permissionEntity.permission_roles", target = "roles")
    PermissionDto permissionEntityToDto(PermissionEntity permissionEntity);
    List<PermissionDto> permissionEntitiesToDtos(List<PermissionEntity> categoryEntities);

    @InheritInverseConfiguration
    PermissionEntity permissionDtoToEntity(PermissionDto permissionDto);
    List<PermissionEntity> permissionDtosToEntities(List<PermissionDto> permissionDtos);
}

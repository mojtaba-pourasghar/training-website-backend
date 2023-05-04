package ir.brandimo.training.shop.mapper.admin;

import ir.brandimo.training.shop.dto.admin.PermissionDto;
import ir.brandimo.training.shop.dto.admin.RoleDto;
import ir.brandimo.training.shop.entity.PermissionEntity;
import ir.brandimo.training.shop.entity.RoleEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, uses = {PermissionMapper.class, UserMapper.class})
public interface RoleMapper {

    RoleMapper ROLE_MAPPER = Mappers.getMapper(RoleMapper.class);
    @Mapping(source = "roleEntity.id", target = "roleId")
//    @Mapping(source = "roleEntity.role_permissions", target = "permissions")
//    @Mapping(source = "roleEntity.role_permissions.permission_id", target = "permissions.permissionId")
    @Mapping(source = "role_permissions", target = "permissions")
    RoleDto roleEntityToDto(RoleEntity roleEntity);
//    default Set<PermissionDto> toPermissionDTOs(Set<PermissionEntity> permissionEntities) {
//        return permissionEntities.stream()
//                .map(permission -> new PermissionDto(permission.getId(),permission.getName(),permission.getController(),permission.getAction(),permission.getStatus()))
//                .collect(Collectors.toSet());
//    }

    List<RoleDto> roleEntitiesToDtos(List<RoleEntity> roleEntities);

    @InheritInverseConfiguration
    RoleEntity roleDtoToEntity(RoleDto roleDto);
    List<RoleEntity> roleDtoToEntities(List<RoleDto> roleDtos);
}

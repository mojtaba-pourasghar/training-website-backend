package ir.brandimo.training.shop.mapper.admin;

import ir.brandimo.training.shop.dto.admin.RoleDto;
import ir.brandimo.training.shop.entity.RoleEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper ROLE_MAPPER = Mappers.getMapper(RoleMapper.class);
    @Mapping(source = "roleEntity.id", target = "roleId")
    RoleDto roleEntityToDto(RoleEntity roleEntity);
    List<RoleDto> roleEntitiesToDtos(List<RoleEntity> roleEntities);

    @InheritInverseConfiguration
    RoleEntity roleDtoToEntity(RoleDto roleDto);
    List<RoleEntity> roleDtoToEntities(List<RoleDto> roleDtos);

}

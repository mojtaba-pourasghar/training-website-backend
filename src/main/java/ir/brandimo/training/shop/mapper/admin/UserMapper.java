package ir.brandimo.training.shop.mapper.admin;

import ir.brandimo.training.shop.dto.admin.UserDto;
import ir.brandimo.training.shop.entity.RoleEntity;
import ir.brandimo.training.shop.entity.UserEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

//@Mapper(componentModel = "spring")
@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, uses = {RoleMapper.class,PermissionMapper.class})
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);


    @Mapping(source = "userEntity.role.id", target = "role.roleId")
    @Mapping(source = "userEntity.role.role_permissions", target = "role.permissions")
    UserDto userEntityToDto(UserEntity userEntity);
    List<UserDto> userEntitiesToDtos(List<UserEntity> userEntities);

    @InheritInverseConfiguration
    UserEntity userDtoToEntity(UserDto userDto);
    List<UserEntity> userDtoToEntities(List<UserDto> userDtos);

}

package ir.brandimo.training.shop.service.admin;

import ir.brandimo.training.shop.config.LangConfiguration;
import ir.brandimo.training.shop.dto.admin.RoleDto;
import ir.brandimo.training.shop.entity.PermissionEntity;
import ir.brandimo.training.shop.entity.RoleEntity;

import ir.brandimo.training.shop.error.EntityExist;
import ir.brandimo.training.shop.error.EntityNotFound;
import ir.brandimo.training.shop.mapper.admin.RoleMapper;
import ir.brandimo.training.shop.repository.PermissionRepository;
import ir.brandimo.training.shop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import javax.xml.crypto.Data;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    LangConfiguration langConfiguration;


    @Override
    @Transactional
    public List<RoleEntity> getAllRoles() {

//        List<RoleEntity> roleEntities = roleRepository.findAll();
//        List<RoleDto> roleDtos = roleEntities.stream()
//                .map(RoleMapper.ROLE_MAPPER::roleEntityToDto)
//                .collect(Collectors.toList());
//
//        return roleEntities;

        List<RoleEntity> roleEntities = roleRepository.findAll();
        if (roleEntities.size() > 0) {
            return roleEntities;
        }
        else {
            return new ArrayList<RoleEntity>();
        }
    }

    @Override
    public RoleEntity getRoleById(Integer id) {
        Optional<RoleEntity> roleEntity =  Optional.ofNullable(roleRepository.findRoleWithPermissions(id)
                .orElseThrow(() -> new EntityNotFound(langConfiguration.role().getMessage("notFound.message", null, Locale.ENGLISH))));

        if (roleEntity.isPresent()) {
            return roleEntity.get();
        }
        else {
            return null;
        }
    }


    @Override
    @Transactional
    public void deleteRoleById(Integer id) {
        if (roleRepository.existsById(id)) {
            RoleEntity role = roleRepository.findById(id).orElseThrow();
            Set<PermissionEntity> permissionEntities = role.getRole_permissions();
            role.getRole_permissions().removeAll(role.getRole_permissions());
            roleRepository.deleteById(id);
            permissionRepository.saveAll(permissionEntities);
        } else {
            throw new EntityNotFound(langConfiguration.role().getMessage("notFound.message", null, Locale.ENGLISH));
        }
    }

    @Override
    public RoleEntity createRole(RoleEntity roleEntity) {
        Optional<RoleEntity> role = roleRepository.findById(roleEntity.getId());
        if(role.isPresent()) {
            throw new EntityExist(langConfiguration.role().getMessage("exist.message", null, Locale.ENGLISH));
        }
        else {
            Set<PermissionEntity> permissionEntitySet = new HashSet<>();
            PermissionEntity permission = null;
            for (PermissionEntity p : roleEntity.getRole_permissions()) {
                permission = permissionRepository.findById(p.getId()).get();
                permissionEntitySet.add(permission);
            }
            roleEntity.setRole_permissions(permissionEntitySet);
            return roleRepository.save(roleEntity);
        }
    }

    @Override
    public RoleEntity updateRole(RoleEntity roleEntity) {
        Optional<RoleEntity> role = roleRepository.findById(roleEntity.getId());

        if(role.isPresent()) {
            RoleEntity newRoleEntity = role.get();
            newRoleEntity.setCreateDate(newRoleEntity.getCreateDate());
            newRoleEntity.setName(roleEntity.getName());
            newRoleEntity.setDescription(roleEntity.getDescription());
            newRoleEntity.setState(roleEntity.getState());

            Set<PermissionEntity> permissionEntitySet = new HashSet<>();
            PermissionEntity permission = null;
            for (PermissionEntity p: roleEntity.getRole_permissions()) {
                permission = permissionRepository.findById(p.getId()).get();
                permissionEntitySet.add(permission);
            }
            newRoleEntity.setRole_permissions(permissionEntitySet);
            roleRepository.save(newRoleEntity);
            return newRoleEntity;
        }
        else {
            throw new NotFoundException(langConfiguration.role().getMessage("notFound.message", null, Locale.ENGLISH));
        }
    }
}

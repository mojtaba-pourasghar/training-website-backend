package ir.brandimo.training.shop.service.admin;

import ir.brandimo.training.shop.config.LangConfiguration;
import ir.brandimo.training.shop.entity.RoleEntity;

import ir.brandimo.training.shop.error.EntityExist;
import ir.brandimo.training.shop.error.EntityNotFound;
import ir.brandimo.training.shop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    LangConfiguration langConfiguration;


    @Override
    public List<RoleEntity> getAllRoles() {
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
        Optional<RoleEntity> roleEntity =  Optional.ofNullable(roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound(langConfiguration.role().getMessage("notFound.message", null, Locale.ENGLISH))));

        if (roleEntity.isPresent()) {
            return roleEntity.get();
        }
        else {
            return null;
        }
    }

    @Override
    public void deleteRoleById(Integer id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
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
            return roleRepository.save(roleEntity);
        }
    }

    @Override
    public RoleEntity updateRole(RoleEntity roleEntity) {
        Optional<RoleEntity> role = roleRepository.findById(roleEntity.getId());

        if(role.isPresent()) {
            RoleEntity newRoleEntity = new RoleEntity();
            newRoleEntity.setUpdateDate(roleEntity.getUpdateDate());
            newRoleEntity.setCreateDate(roleEntity.getCreateDate());
            newRoleEntity.setName(roleEntity.getName());
            newRoleEntity.setDescription(roleEntity.getDescription());

            roleRepository.save(newRoleEntity);
            return newRoleEntity;
        }
        else {
            throw new NotFoundException(langConfiguration.role().getMessage("notFound.message", null, Locale.ENGLISH));
        }
    }
}

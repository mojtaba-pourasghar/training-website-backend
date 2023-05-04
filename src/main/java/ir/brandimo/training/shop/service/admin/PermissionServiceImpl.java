package ir.brandimo.training.shop.service.admin;

import ir.brandimo.training.shop.config.LangConfiguration;
import ir.brandimo.training.shop.entity.PermissionEntity;
import ir.brandimo.training.shop.error.EntityExist;
import ir.brandimo.training.shop.error.EntityNotFound;
import ir.brandimo.training.shop.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    LangConfiguration langConfiguration;

    @Override
    public List<PermissionEntity> getAllPermissions() {
        List<PermissionEntity> permissionEntities = permissionRepository.findAll();
        if (permissionEntities.size() > 0) {
            return permissionEntities;
        }
        else {
            return new ArrayList<PermissionEntity>();
        }
    }

    @Override
    public PermissionEntity getPermissionById(Integer id) {
        Optional<PermissionEntity> permissionEntity =  Optional.ofNullable(permissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound(langConfiguration.permission().getMessage("notFound.message", null, Locale.ENGLISH))));

        if (permissionEntity.isPresent()) {
            return permissionEntity.get();
        }
        else {
            return null;
        }
    }

    @Override
    public void deletePermissionById(Integer id) {
//        Optional<PermissionEntity> permission = permissionRepository.findById(id);
//        if (id == null || id <= 0) {
//            throw new ValidationException(langConfiguration.permission().getMessage("invalid.message", null, Locale.ENGLISH))));
//        }
//        else if (!permissionEntityIsReferenced(permission.get())){
//            throw new ValidationException(langConfiguration.permission().getMessage("invalid.message", null, Locale.ENGLISH))));
//        }
        if (permissionRepository.existsById(id)) {
            permissionRepository.deleteById(id);
        } else {
            throw new EntityNotFound(langConfiguration.permission().getMessage("notFound.message", null, Locale.ENGLISH));
        }
    }

    @Override
    public PermissionEntity createPermission(PermissionEntity permissionEntity) {
        Optional<PermissionEntity> permission = permissionRepository.findById(permissionEntity.getId());

        if(permission.isPresent()) {
            throw new EntityExist(langConfiguration.permission().getMessage("exist.message", null, Locale.ENGLISH));
        }
        else {
            return permissionRepository.save(permissionEntity);
        }
    }

    @Override
    public PermissionEntity updatePermission(PermissionEntity permissionEntity) {

        Optional<PermissionEntity> permission = permissionRepository.findById(permissionEntity.getId());

        if(permission.isPresent()) {
            PermissionEntity newPermissionEntity = permission.get();
//            newPermissionEntity.setUpdateDate(permissionEntity.getUpdateDate());
//            newPermissionEntity.setCreateDate(permissionEntity.getCreateDate());
            newPermissionEntity.setName(permissionEntity.getName());
            newPermissionEntity.setController(permissionEntity.getController());
            newPermissionEntity.setAction(permissionEntity.getAction());
            newPermissionEntity.setState(permissionEntity.getState());

            permissionRepository.save(newPermissionEntity);
            return newPermissionEntity;
        }
        else {
            throw new NotFoundException(langConfiguration.permission().getMessage("notFound.message", null, Locale.ENGLISH));
        }
    }

    private boolean permissionEntityIsReferenced(PermissionEntity permissionEntity) {
        if (!permissionEntity.getPermission_roles().isEmpty()) {
            return true;
        }
        return false;
    }
}

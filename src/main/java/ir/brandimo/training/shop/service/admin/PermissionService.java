package ir.brandimo.training.shop.service.admin;


import ir.brandimo.training.shop.entity.PermissionEntity;

import java.util.List;

public interface PermissionService {
    public List<PermissionEntity> getAllPermissions();
    public PermissionEntity getPermissionById(Integer id);
    public void deletePermissionById(Integer id);
    public PermissionEntity createPermission(PermissionEntity permissionEntity);
    public PermissionEntity updatePermission(PermissionEntity permissionEntity);
}

package ir.brandimo.training.shop.service.admin;

import ir.brandimo.training.shop.entity.RoleEntity;

import java.util.List;

public interface RoleService {
    public List<RoleEntity> getAllRoles();
    public RoleEntity getRoleById(Integer id);
    public void deleteRoleById(Integer id);
    public RoleEntity createRole(RoleEntity roleEntity);
    public RoleEntity updateRole(RoleEntity roleEntity);
}

package ir.brandimo.training.shop.repository;

import ir.brandimo.training.shop.dto.ERole;
import ir.brandimo.training.shop.entity.RoleEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {
    public RoleEntity findRoleByName(String name);
    Optional<RoleEntity> findByName(String name);

//    @Fetch(FetchMode.JOIN)
//    @Transactional

    @Query("SELECT r,s FROM RoleEntity r LEFT JOIN FETCH r.role_permissions s WHERE r.id = :roleId")
    Optional<RoleEntity> findRoleWithPermissions(@Param("roleId") Integer roleId);

//    @Modifying
//    @Query(value = "DELETE FROM roles WHERE id = :roleId; DELETE FROM role_permissions WHERE role_id = :roleId; ", nativeQuery = true)
//    void deleteRolePermissionByRoleId(@Param("roleId") Integer roleId);
}


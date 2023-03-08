package ir.brandimo.training.shop.repository;

import ir.brandimo.training.shop.dto.ERole;
import ir.brandimo.training.shop.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {
    public RoleEntity findRoleByName(String name);
    Optional<RoleEntity> findByName(String name);
}

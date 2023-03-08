package ir.brandimo.training.shop.repository;

import ir.brandimo.training.shop.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {
}

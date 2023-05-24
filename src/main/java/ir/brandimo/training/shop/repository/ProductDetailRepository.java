package ir.brandimo.training.shop.repository;

import ir.brandimo.training.shop.entity.PermissionEntity;
import ir.brandimo.training.shop.entity.ProductDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity, Integer> {
}

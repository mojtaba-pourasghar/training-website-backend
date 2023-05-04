package ir.brandimo.training.shop.repository;

import ir.brandimo.training.shop.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Mahsa Ranjbar
 * @
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}

package ir.brandimo.training.shop.repository;

import ir.brandimo.training.shop.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Mahsa Ranjbar
 * @
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

   // Optional<ProductEntity> findByName(String name);

    @Query("SELECT r,s FROM ProductEntity r LEFT JOIN FETCH r.productDetails s WHERE r.id = :productId")
    Optional<ProductEntity> findDetailWithProduct(@Param("productId") Integer productId);
}

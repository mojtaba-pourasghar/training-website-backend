package ir.brandimo.training.shop.repository;

import ir.brandimo.training.shop.entity.OrderEntity;
import ir.brandimo.training.shop.entity.PermissionEntity;
import ir.brandimo.training.shop.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    @Query("SELECT r,s FROM OrderEntity r LEFT JOIN FETCH r.orderItems s WHERE r.id = :orderId")
    Optional<OrderEntity> findItemsWithOrder(@Param("orderId") Integer orderId);
}

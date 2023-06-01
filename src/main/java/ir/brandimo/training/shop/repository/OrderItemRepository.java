package ir.brandimo.training.shop.repository;

import ir.brandimo.training.shop.entity.OrderEntity;
import ir.brandimo.training.shop.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Integer> {
}

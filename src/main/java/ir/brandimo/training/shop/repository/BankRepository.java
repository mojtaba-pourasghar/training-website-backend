package ir.brandimo.training.shop.repository;

import ir.brandimo.training.shop.entity.BankEntity;
import ir.brandimo.training.shop.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, Integer> {
}

package ir.brandimo.training.shop.repository.admin;

import ir.brandimo.training.shop.entity.VariationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariationRepository extends JpaRepository<VariationEntity, Integer> {

}

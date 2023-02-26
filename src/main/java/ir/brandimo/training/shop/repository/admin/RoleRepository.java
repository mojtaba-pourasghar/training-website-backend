package ir.brandimo.training.shop.repository.admin;



import ir.brandimo.training.shop.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {
    public RoleEntity findRoleByName(String name);
}

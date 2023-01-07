package ir.brandimo.training.shop.repository;



import ir.brandimo.training.shop.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    public Role findRoleByName(String name);
    public Role findRoleBySlug(String slug);
}

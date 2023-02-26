package ir.brandimo.training.shop.repository.admin;



import ir.brandimo.training.shop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    public List<UserEntity> findUserByName(String name);
}

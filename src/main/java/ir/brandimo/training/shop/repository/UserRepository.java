package ir.brandimo.training.shop.repository;

import ir.brandimo.training.shop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    public List<UserEntity> findUserByName(String name);
    Optional<UserEntity> findByEmail(String email);
    Boolean existsByMobile(String mobile);
    Boolean existsByEmail(String email);
}

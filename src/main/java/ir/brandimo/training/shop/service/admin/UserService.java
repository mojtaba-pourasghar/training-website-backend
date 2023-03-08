package ir.brandimo.training.shop.service.admin;

import ir.brandimo.training.shop.entity.UserEntity;

import java.util.List;

public interface UserService {
    public List<UserEntity> getAllUsers();
    public UserEntity getUserById(Integer id);
    public void deleteUserById(Integer id);
    public UserEntity createUser(UserEntity userEntity);
    public UserEntity updateUser(UserEntity userEntity);
}

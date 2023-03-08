package ir.brandimo.training.shop.service.admin;

import ir.brandimo.training.shop.config.LangConfiguration;
import ir.brandimo.training.shop.entity.UserEntity;
import ir.brandimo.training.shop.error.EntityExist;
import ir.brandimo.training.shop.error.EntityNotFound;
import ir.brandimo.training.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LangConfiguration langConfiguration;

    @Override
    public List<UserEntity> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        if (userEntities.size() > 0) {
            return userEntities;
        }
        else {
            return new ArrayList<UserEntity>();
        }
    }

    @Override
    public UserEntity getUserById(Integer id) {
        Optional<UserEntity> userEntity =  Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound(langConfiguration.user().getMessage("notFound.message", null, Locale.ENGLISH))));

        if (userEntity.isPresent()) {
            return userEntity.get();
        }
        else {
            return null;
        }
    }

    @Override
    public void deleteUserById(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new EntityNotFound(langConfiguration.user().getMessage("notFound.message", null, Locale.ENGLISH));
        }
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        Optional<UserEntity> user = userRepository.findById(userEntity.getId());

        if(user.isPresent()) {
            throw new EntityExist(langConfiguration.user().getMessage("exist.message", null, Locale.ENGLISH));
        }
        else {
            return userRepository.save(userEntity);
        }
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        Optional<UserEntity> user = userRepository.findById(userEntity.getId());

        if(user.isPresent()) {
            UserEntity newUserEntity = new UserEntity();
            newUserEntity.setUpdateDate(userEntity.getUpdateDate());
            newUserEntity.setCreateDate(userEntity.getCreateDate());
            newUserEntity.setName(userEntity.getName());
            newUserEntity.setAddresses(userEntity.getAddresses());
            newUserEntity.setEmail(userEntity.getEmail());
            newUserEntity.setMobile(userEntity.getMobile());
            newUserEntity.setPassword(userEntity.getPassword());
            newUserEntity.setState(userEntity.getState());
            newUserEntity.setUserType(userEntity.getUserType());

            userRepository.save(newUserEntity);
            return newUserEntity;
        }
        else {
            throw new NotFoundException(langConfiguration.user().getMessage("notFound.message", null, Locale.ENGLISH));
        }
    }
}

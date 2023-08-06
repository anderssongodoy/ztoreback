package pe.idat.ztore.service;

import java.util.List;
import java.util.Optional;

import pe.idat.ztore.model.UserEntity;

public interface UserService {

    UserEntity save(UserEntity user);

    Optional<UserEntity> findById(Long id);

    boolean delete(Long id);

    UserEntity update(UserEntity user);

    List<UserEntity> getAll();
}

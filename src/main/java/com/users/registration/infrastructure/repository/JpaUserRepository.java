package com.users.registration.infrastructure.repository;

import com.users.registration.domain.model.User;
import com.users.registration.domain.repository.UserRepository;
import com.users.registration.infrastructure.entity.UserEntity;
import com.users.registration.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    private final SpringDataUserRepository springDataUserRepository;
    private final UserMapper userMapper;

    public JpaUserRepository(SpringDataUserRepository springDataUserRepository, UserMapper userMapper) {
        this.springDataUserRepository = springDataUserRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springDataUserRepository.findByEmail(email).map(userMapper::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity entity = userMapper.toEntity(user);
        return userMapper.toDomain(springDataUserRepository.save(entity));
    }
}


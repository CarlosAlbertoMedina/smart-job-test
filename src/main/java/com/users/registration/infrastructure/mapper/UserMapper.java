package com.users.registration.infrastructure.mapper;

import com.users.registration.domain.model.Phone;
import com.users.registration.domain.model.User;
import com.users.registration.infrastructure.dto.UserRequestDto;
import com.users.registration.infrastructure.entity.PhoneEntity;
import com.users.registration.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User toDomain(UserEntity entity) {
        if (entity == null) return null;

        User user = new User();
        user.setId(entity.getExternalId());
        user.setName(entity.getName());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setToken(entity.getToken());
        user.setCreated(entity.getCreated());
        user.setModified(entity.getModified());
        user.setLastLogin(entity.getLastLogin());
        user.setIsActive(entity.getIsActive());

        if (entity.getPhones() != null) {
            user.setPhones(entity.getPhones().stream().map(phoneEntity -> {
                Phone phone = new Phone();
                phone.setNumber(phoneEntity.getNumber());
                phone.setCityCode(phoneEntity.getCityCode());
                phone.setCountryCode(phoneEntity.getCountryCode());
                return phone;
            }).collect(Collectors.toList()));
        }

        return user;
    }

    public UserEntity toEntity(User user) {
        if (user == null) return null;

        UserEntity entity = new UserEntity();
        entity.setExternalId(user.getId() != null ? user.getId() : UUID.randomUUID());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setToken(user.getToken());
        entity.setCreated(user.getCreated());
        entity.setModified(user.getModified());
        entity.setLastLogin(user.getLastLogin());
        entity.setIsActive(user.getIsActive());

        if (user.getPhones() != null) {
            entity.setPhones(user.getPhones().stream().map(phone -> {
                PhoneEntity phoneEntity = new PhoneEntity();
                phoneEntity.setNumber(phone.getNumber());
                phoneEntity.setCityCode(phone.getCityCode());
                phoneEntity.setCountryCode(phone.getCountryCode());
                phoneEntity.setUser(entity);
                return phoneEntity;
            }).collect(Collectors.toList()));
        }

        return entity;
    }

    public User fromRequestDto(UserRequestDto dto) {
        if (dto == null) return null;

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        if (dto.getPhones() != null) {
            user.setPhones(dto.getPhones().stream().map(phoneDto -> {
                Phone phone = new Phone();
                phone.setNumber(phoneDto.getNumber());
                phone.setCityCode(phoneDto.getCityCode());
                phone.setCountryCode(phoneDto.getCountryCode());
                return phone;
            }).collect(Collectors.toList()));
        }

        return user;
    }


}

package com.scholarium.profiles.service;


import com.scholarium.profiles.dto.CreateGenericUserDto;
import com.scholarium.profiles.dto.GenericUserDto;
import com.scholarium.profiles.enums.RoleEnum;
import com.scholarium.profiles.model.Profile;
import com.scholarium.profiles.repository.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileService {

    private final ProfileRepository userRepository;

    private static final String USER_NOT_FOUND = "User not found.";

    private static final Logger log = LoggerFactory.getLogger(ProfileService.class);


    public ProfileService(ProfileRepository userRepository) {
        this.userRepository = userRepository;
    }

    public GenericUserDto convertToRecord(Profile user) {
        return new GenericUserDto(
                user.getName(),
                user.getCpf(),
                user.getAddress(),
                user.getPhoto(),
                user.getRole()
        );
    }

    public Profile create(CreateGenericUserDto userRecord) throws IllegalAccessException {
        Optional<Profile> byLogin = userRepository.findByLogin(userRecord.login());

        if (byLogin.isPresent()){
            throw new IllegalAccessException("User Alredy exists");
        }

        log.info("Saving user: {}", userRecord.name());

        Profile user = new Profile();
        user.setUuid(UUID.randomUUID().toString());
        user.setName(userRecord.name());
        user.setCpf(userRecord.cpf());
        user.setActivated(true);
        user.setCreateDate(LocalDateTime.now());
        user.setDeleted(false);
        user.setAddress(userRecord.address());
        user.setPhoto(userRecord.photo());
        user.setRole(RoleEnum.valueOf(userRecord.role()));
        user.setLogin(userRecord.login());
        user.setPassword(userRecord.password());

        return userRepository.save(user);
    }

    public GenericUserDto findById(Long id) {
        Profile user = userRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
        return convertToRecord(user);
    }

    public void deleteById(Long id) {
        Profile user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
        user.setDeleted(true);
        user.setDeletedDate(LocalDateTime.now());
        userRepository.save(user);
    }


    public Profile findByLogin (String login){
        return userRepository.findByLogin(login).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
    }




}

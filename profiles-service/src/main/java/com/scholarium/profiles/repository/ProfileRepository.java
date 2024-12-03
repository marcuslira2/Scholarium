package com.scholarium.profiles.repository;

import com.scholarium.profiles.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByIdAndDeletedFalse(Long id);

    Optional<Profile> findByLogin(String login);

}

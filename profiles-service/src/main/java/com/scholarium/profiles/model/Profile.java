package com.scholarium.profiles.model;


import com.scholarium.profiles.dto.CreateProfileDTO;
import com.scholarium.profiles.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@NoArgsConstructor
@Data
@Entity(name = "profile")
public class Profile {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    String uuid;
    String name;
    String login;
    boolean activated;
    String password;
    LocalDateTime createDate;
    LocalDateTime deletedDate;
    boolean deleted;
    String cpf;
    String address;
    String photo;
    RoleEnum role;

    public Profile(CreateProfileDTO user) {
        this.uuid = UUID.randomUUID().toString();
        this.name = user.name();
        this.login = user.login();
        this.password = user.password();
        this.activated = true;
        this.createDate = LocalDateTime.now();
        this.deleted = false;
        this.cpf = user.cpf();
        this.address = user.address();
        this.photo = user.photo();
        this.role = RoleEnum.valueOf(user.role());
    }
}

package com.petadoption.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "petadoption_user")
public class User {
    @Id
    @NotNull
    @Column(unique = true)
    @Size(min = 3, max = 50)
    private String username;

    @JsonIgnore
    @NotNull
    private String password;

    private String registerDate;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @Column(columnDefinition = "varchar(1) default 'Y'")
    private String activeFlag = "Y";





}

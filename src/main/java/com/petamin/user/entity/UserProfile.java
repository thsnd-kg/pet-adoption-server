package com.petamin.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class UserProfile {
    @Id
    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "username")
    private String username;

    private String displayName;
    private String dateOfBirth;
    private String imgUrl;
    private String bio;
    private String address;
    private String phoneNo;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "username")
    private User user;
}

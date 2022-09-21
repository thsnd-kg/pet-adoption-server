package com.petamin.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petamin.common.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "peatamin_user")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @NotNull
    @Column(unique = true)
    @Size(min = 3, max = 50)
    private String username;

    @JsonIgnore
    @NotNull
    private String password;

    @CreatedDate
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private String registerDate;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @Column(columnDefinition = "varchar(1) default 'Y'")
    private String activeFlag = "Y";

    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private UserProfile profile;

    public void setProfile(UserProfile profile){
        if (profile == null) {
            if (this.profile != null) {
                this.profile.setUser(null);
            }
        }
        else {
            profile.setUser(this);
        }
        this.profile = profile;
    }

}

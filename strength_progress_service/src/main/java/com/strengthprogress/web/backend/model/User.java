package com.strengthprogress.web.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.strengthprogress.web.backend.model.token.NewEmailToken;
import com.strengthprogress.web.backend.model.token.NewPasswordToken;
import com.strengthprogress.web.backend.model.token.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity(name = "USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id @GeneratedValue
    private Long userId;
    @NotNull @NotEmpty
    @Column(unique = true, nullable = false)
    @Email
    private String username;
    @NotNull @NotEmpty
    private String firstName;
    @NotNull @NotEmpty
    private String lastName;
    @NotNull @NotEmpty @JsonIgnore
    private String password;
    @ManyToMany
    private Set<Role> authorities;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToOne(cascade = CascadeType.ALL)
    private Token accountVerificationToken;
    @OneToOne(cascade = CascadeType.ALL)
    private NewEmailToken emailVerificationToken;
    @OneToOne(cascade = CascadeType.ALL)
    private NewPasswordToken passwordVerificationToken;
    @JsonIgnore
    private Boolean enabled;
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}

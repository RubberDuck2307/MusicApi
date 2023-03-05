package com.example.music.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@Entity
@Table(schema = "music", name = "user_credentials",
        uniqueConstraints = {

                @UniqueConstraint(name = "user_email_unique", columnNames = "userEmail")
        }
)
public class UserCredentials implements UserDetails {
    @SequenceGenerator(name = "user_credentials_sequence",
            sequenceName = "user_credentials_sequence",
            allocationSize = 1)
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_credentials_sequence"
    )
    private Integer id;

    @Column( nullable = false)
    private String userEmail;
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "userCredentials", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;

    public UserCredentials(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return userEmail;
    }

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
        return true;
    }
}

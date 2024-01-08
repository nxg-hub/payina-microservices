package com.nxg.payina.external.customer.entity;

import com.nxg.payina.enums.UserType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
@Entity
@Data
@Table(name = "customers")
public class Customer implements UserDetails{

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String firstName;


        @Column(nullable = false, unique = true)
        private String email;

        private String lastName;


        private String phoneNumber;


        @Column(nullable = false)
        private String password;

        @Column(name = "roles")
        private String roles;

        @Column(name = "user_type")
        @Enumerated(EnumType.STRING)
        private UserType userType;


        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }
        @Override
        public String getUsername() {
            return email;
        }

        @Override
        public String getPassword(){
            return password;
        }



        @Override
        public boolean isAccountNonExpired() {
            return false;
        }

        @Override
        public boolean isAccountNonLocked() {
            return false;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return false;
        }

        @Column(name = "enabled")
        public boolean enabled;
        @Override
        public boolean isEnabled() {

            return enabled;
        }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}


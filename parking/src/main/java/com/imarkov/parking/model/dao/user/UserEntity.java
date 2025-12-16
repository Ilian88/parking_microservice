package com.imarkov.parking.model.dao.user;

import com.imarkov.parking.model.dao.BaseEntity;
import jakarta.persistence.*;

@Entity
public class UserEntity extends BaseEntity {
    private String username;
    private String encPassword;
    private Role role;

    @Column(nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false)
    public String getEncPassword() {
        return encPassword;
    }

    public void setEncPassword(String encPassword) {
        this.encPassword = encPassword;
    }

    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "username='" + username + '\'' +
                ", encPassword='" + "*******" + '\'' +
                ", role=" + role.toString() +
                '}';
    }
}

package com.imarkov.parking.model.dao.user;

import com.imarkov.parking.model.dao.BaseEntity;
import jakarta.persistence.*;

@Entity
public class UserEntity extends BaseEntity {
    private String username;
    private String encPassword;
    private String accountType;
    private String email;
    private String phone;
    private String companyName;
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

    @Column(nullable = false)
    public String getAccountType() {
        return accountType;
    }

    public UserEntity setAccountType(String accountType) {
        this.accountType = accountType;
        return this;
    }
    @Column
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @Column
    public String getPhone() {
        return phone;
    }

    public UserEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Column
    public String getCompanyName() {
        return companyName;
    }

    public UserEntity setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
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

package com.imarkov.parking.model.dao.user;

import com.imarkov.parking.model.dao.BaseEntity;
import jakarta.persistence.*;

@Entity
public class UserEntity extends BaseEntity {
    private String username;
    private String password;
    private AccountType accountType;
    private String email;
    private String phone;
    private String companyName;
    private Role role;

    @Column(nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public UserEntity setRole(Role role) {
        this.role = role;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public AccountType getAccountType() {
        return accountType;
    }

    public UserEntity setAccountType(AccountType accountType) {
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
}

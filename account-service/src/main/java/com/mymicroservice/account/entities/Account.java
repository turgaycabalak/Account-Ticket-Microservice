package com.mymicroservice.account.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Table(value = "accounts")
public class Account {
    @PrimaryKey
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private Boolean isActive;


    public Account(String firstName,
                   String lastName,
                   String email,
                   String password,
                   LocalDateTime createdAt,
                   Boolean isActive) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.isActive = isActive;
    }
}

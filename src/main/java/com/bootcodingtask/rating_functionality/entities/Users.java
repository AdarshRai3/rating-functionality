package com.bootcodingtask.rating_functionality.entities;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@Table (name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name ="email", nullable = false, unique = true,length = 100)
    private String email;

    @Column(name = "password", nullable = false)
    private String passwordHash;

    @Column(name = "createdAt",nullable = false,updatable = false )
    private LocalDateTime createdAt;


    @OneToMany(mappedBy = "user")
    private List<Reviews>reviews;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

}

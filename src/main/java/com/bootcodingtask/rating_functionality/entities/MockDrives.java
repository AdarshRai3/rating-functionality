package com.bootcodingtask.rating_functionality.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "mock_drives")
public class MockDrives {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "mock_drive_id")
    private Integer mock_drive_id;

    @Column (name = "title" ,nullable = false ,length = 100)
    private String title;

    @Column ( name = "createdAt", nullable = false , updatable = false)
    private LocalDateTime createdAt;

    @Column (name = "avg_rating", precision = 3, scale = 2 )
    private BigDecimal avg_rating;

    @OneToMany (mappedBy = "mockDrive")
    private List<Reviews> reviews;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }
}

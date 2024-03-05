package org.example.webflux1.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("users")
public class User {
    @Id
    private Long id;
    private String name;
    private String email;
    @CreatedDate
    @Column(value = "created_at")
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(value = "updated_at")
    private LocalDateTime updatedAt;
}

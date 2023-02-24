package com.adityasrivastava.taskmanagerwithauth.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity(name = "tasks")
@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class taskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private long id;

    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    private Boolean isCompleted;

    @ManyToOne
    @JoinColumn(name="belongTo",nullable = false)
    private userEntity belongTo;


}

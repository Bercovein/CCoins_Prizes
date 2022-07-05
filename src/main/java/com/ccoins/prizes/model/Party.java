package com.ccoins.prizes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="PARTIES")
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="start_date")
    private LocalDateTime startDate;

    @Column(name="active", columnDefinition = "boolean default true")
    private boolean active;

    @Column(name="fk_table")
    private Long table;
}

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
@Table(name="PRIZES")
public class Prize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="points")
    private Long points;

    @Column(name="start_date")
    private LocalDateTime startDate;

    @Column(name="end_date")
    private LocalDateTime endDate;

    @Column(name="active", columnDefinition = "boolean default true")
    private boolean active;

    @Column(name="fk_bar")
    private Long bar;
}

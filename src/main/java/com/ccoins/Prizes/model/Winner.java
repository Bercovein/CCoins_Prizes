package com.ccoins.Prizes.model;

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
@Table(name="WINNERS")
public class Winner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_prize", referencedColumnName = "id")
    private Prize prize;

    @Column(name = "points")
    private Long points;

    @Column(name="start_date")
    private LocalDateTime startDate;

    @Column(name="active", columnDefinition = "boolean default true")
    private boolean active;
}

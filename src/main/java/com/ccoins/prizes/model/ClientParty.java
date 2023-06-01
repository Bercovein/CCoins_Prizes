package com.ccoins.prizes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="CLIENTS_PARTIES")
public class ClientParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="FK_CLIENT")
    private Long client;

    @Column(name="FK_PARTY")
    private Long party;

    @Column(name = "ACTIVE")
    private boolean active;

    @Column(name = "LEADER")
    private boolean leader;

}

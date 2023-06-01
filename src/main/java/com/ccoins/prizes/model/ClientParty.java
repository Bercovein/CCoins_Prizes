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
@IdClass(CompositeKey.class)
public class ClientParty {

    @Id
    @Column(name="FK_CLIENT")
    private Long client;

    @Id
    @Column(name="FK_PARTY")
    private Long party;

    @Column(name = "ACTIVE")
    private boolean active;

    @Column(name = "LEADER")
    private boolean leader;

}

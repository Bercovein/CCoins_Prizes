package com.ccoins.prizes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientPartyDTO {

    private Long id;

    private Long client;

    private Long party;

    private boolean active;

    private boolean leader;

}

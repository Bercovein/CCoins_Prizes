package com.ccoins.prizes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartyTableDTO {

    private Long id;
    private String name;
    private LocalDateTime startDate;
    private boolean active;
    private Long tableId;
    private Long tableNumber;
}


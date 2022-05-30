package com.ccoins.Prizes.dto;

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
public class PartyDTO {

    private Long id;

    private String name;

    private LocalDateTime startDate;

    private boolean active;

    private Long table;
}

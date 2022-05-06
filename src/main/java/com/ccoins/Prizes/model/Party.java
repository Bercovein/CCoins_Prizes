package com.ccoins.Prizes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Party {
    private Long id;
    private String name;
    private LocalDateTime startDate;
    private boolean active;
    private Long table;
}

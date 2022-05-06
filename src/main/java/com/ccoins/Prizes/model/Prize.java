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
public class Prize {
    private Long id;
    private String name;
    private Long points;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean active;
}

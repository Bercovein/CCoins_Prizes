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
public class Winner {
    private Prize prize;
    private Long points;
    private LocalDateTime startDate;
    private boolean active;
}

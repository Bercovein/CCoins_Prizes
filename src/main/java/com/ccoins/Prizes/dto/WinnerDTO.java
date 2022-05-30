package com.ccoins.Prizes.dto;

import com.ccoins.Prizes.model.Prize;
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
public class WinnerDTO {


    private Long id;

    private PrizeDTO prize;

    private Long points;

    private LocalDateTime startDate;

    private boolean active;
}

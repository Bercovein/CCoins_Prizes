package com.ccoins.Prizes.model.projection;

import java.time.LocalDateTime;

public interface IPPrize {

    Long getId();
    Long getName();
    boolean getActive();
    String getPoints();
    LocalDateTime getStartDate();
    LocalDateTime getEndDate();

    Long setId();
    Long setName();
    boolean setActive();
    String setPoints();
    LocalDateTime setStartDate();
    LocalDateTime setEndDate();

}

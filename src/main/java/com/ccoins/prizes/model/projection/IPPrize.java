package com.ccoins.prizes.model.projection;

import java.time.LocalDateTime;

public interface IPPrize {

    Long getId();
    String getName();
    boolean getActive();
    String getPoints();
    LocalDateTime getStartDate();
    LocalDateTime getEndDate();

    Long setId();
    String setName();
    boolean setActive();
    String setPoints();
    LocalDateTime setStartDate();
    LocalDateTime setEndDate();

}

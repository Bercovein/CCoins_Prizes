package com.ccoins.prizes.model.projection;

import java.time.LocalDateTime;

public interface IPParty {

    Long getId();
    Long getName();
    boolean getActive();
    LocalDateTime getStartDate();
    Long getTable();

    Long setId();
    Long setName();
    boolean setActive();
    LocalDateTime setStartDate();
    Long setTable();

}

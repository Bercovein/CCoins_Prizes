package com.ccoins.prizes.model.projection;

import java.time.LocalDateTime;

public interface IPParty {

    Long getId();
    String getName();
    boolean getActive();
    LocalDateTime getStartDate();
    Long getTable();

    Long setId();
    String setName();
    boolean setActive();
    LocalDateTime setStartDate();
    Long setTable();

}

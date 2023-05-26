package com.ccoins.prizes.model.projection;

import java.time.LocalDateTime;

public interface IPPartyTable {

    Long getId();
    String getName();
    boolean getActive();
    LocalDateTime getStartDate();
    Long getTableId();
    Long getTableNumber();

    Long setId();
    String setName();
    boolean setActive();
    LocalDateTime setStartDate();
    Long setTableId();
    Long setTableNumber();

}

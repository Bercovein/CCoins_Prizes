package com.ccoins.Prizes.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StateEnum {

    ACTIVE("active", true),
    UNACTIVE("unactive", false);

    String value;
    boolean status;



}

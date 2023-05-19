package com.ccoins.prizes.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ClosePartyEnum {

    CLOSED_PARTY(null, "Se há cerrado la party"),
    CLIENTS_ALREADY_ON_PARTY("1", "No se ha cerrado ya que hay gente todavía en la mesa.");

    final String code;
    final String message;

}

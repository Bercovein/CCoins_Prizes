package com.ccoins.prizes.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GiveLeaderEnum {

    LEADER_NOT_FOUND("1", "No se pudo encontrar al líder de la mesa. Por favor, reintente."),
    YOU_ARE_NOT_LEADER_FOUND("2", "No puedes hacer esta operación. Solo el líder de la mesa puede hacerlo."),
    NEW_LEADER_NOT_FOUND("3", "No se pudo encontrar al candidato a líder. Por favor, reintente o elija a otra persona."),
    ALL_HAIL_NEW_LEADER_NAME(null, "¡Todos saluden al nuevo líder! Se le ha cedido el puesto de líder a "),

    ALL_HAIL_NEW_LEADER(null, "Se le ha cedido el puesto de líder. ¡Todos saluden al nuevo líder!");

    final String code;
    final String message;

}

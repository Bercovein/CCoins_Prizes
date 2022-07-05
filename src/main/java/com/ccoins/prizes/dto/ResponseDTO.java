package com.ccoins.prizes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class ResponseDTO {

    private String code;
    private Object message;

}

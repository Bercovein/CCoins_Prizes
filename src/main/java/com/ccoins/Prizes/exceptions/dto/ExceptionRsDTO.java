package com.ccoins.Prizes.exceptions.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionRsDTO {

    private String code;
    private Object message;

}

package com.ccoins.Prizes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericRsDTO<T> extends ResponseDTO{

    private T data;

    public GenericRsDTO(String code, Object message, T data) {
        super(code, message);
        this.data = data;
    }

    public GenericRsDTO() {
        super();
    }


}

package com.ccoins.prizes.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class CompositeKey implements Serializable {

    private Long client;
    private Long party;

}

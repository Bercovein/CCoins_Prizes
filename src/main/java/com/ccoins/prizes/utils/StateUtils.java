package com.ccoins.prizes.utils;


import com.ccoins.prizes.utils.enums.StateEnum;

public class StateUtils {

    public static boolean isActive(String status){
        boolean state;
        if(StateEnum.ACTIVE.getValue().equals(status)){
            state = StateEnum.ACTIVE.isStatus();
        }else{
            state = StateEnum.UNACTIVE.isStatus();
        }
        return state;
    }
}

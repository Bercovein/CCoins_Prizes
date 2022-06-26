package com.ccoins.Prizes.exceptions.constant;

public class ExceptionConstant {

    //LABELS
    public static final String ERROR_LABEL = "Error when trying to ";
    public static final String GET_ERROR_LABEL = ERROR_LABEL.concat("get ");
    public static final String CREATE_NEW_ERROR_LABEL = ERROR_LABEL.concat("create new ");

    public static final String ACTIVE_UNACTIVE_ERROR_LABEL = ERROR_LABEL.concat("change state of ");

    public static final String CREATE_OR_REPLACE_ERROR_LABEL = ERROR_LABEL.concat("create or replace ");

    //ERRORS
    public static final String GENERIC_ERROR_CODE = "0001";
    public static final String GENERIC_ERROR = "Something went wrong! Check with your administrator";

    public static final String PRIZE_CREATE_OR_UPDATE_ERROR_CODE = "0002";
    public static final String PRIZE_CREATE_OR_UPDATE_ERROR = CREATE_NEW_ERROR_LABEL.concat("prize");

    public static final String PRIZE_FIND_BY_BAR_ERROR_CODE = "0003";
    public static final String PRIZE_FIND_BY_BAR_ERROR = GET_ERROR_LABEL.concat("prize by bar");

    public static final String PRIZE_FIND_BY_ID_ERROR_CODE = "0004";
    public static final String PRIZE_FIND_BY_ID_ERROR = GET_ERROR_LABEL.concat("prize by id");

    public static final String TABLE_FIND_PRIZE_BY_ID_ERROR_CODE = "0005";
    public static final String TABLE_FIND_PRIZE_BY_ID_ERROR = PRIZE_FIND_BY_ID_ERROR;

    public static final String TABLE_FIND_BY_OWNER_ERROR_CODE = "0006";
    public static final String TABLE_FIND_BY_OWNER_ERROR = GET_ERROR_LABEL.concat("tables by prizes");


    public static final String TABLE_FIND_BY_ID_ERROR_CODE = "0007";
    public static final String TABLE_FIND_BY_ID_ERROR = GET_ERROR_LABEL.concat("table by id");

    public static final String PRIZE_UPDATE_ACTIVE_ERROR_CODE = "0008";
    public static final String PRIZE_UPDATE_ACTIVE_ERROR = ACTIVE_UNACTIVE_ERROR_LABEL.concat("prize");

    public static final String PRIZE_UPDATE_LIST_ERROR_CODE = "0009";
    public static final String PRIZE_UPDATE_LIST_ERROR = ACTIVE_UNACTIVE_ERROR_LABEL.concat("prize by list");



    public static final String PRIZE_FIND_LIST_ERROR_CODE = "0010";
    public static final String PRIZE_FIND_LIST_ERROR = GET_ERROR_LABEL.concat("prize by list");


}

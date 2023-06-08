package com.ccoins.prizes.exceptions.constant;

public class ExceptionConstant {

    //LABELS
    public static final String ERROR_LABEL = "Error when trying to ";
    public static final String GET_ERROR_LABEL = ERROR_LABEL.concat("get ");
    public static final String CREATE_NEW_ERROR_LABEL = ERROR_LABEL.concat("create new ");
    public static final String ADDING_ERROR_LABEL = ERROR_LABEL.concat("add ");

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

    public static final String PARTY_FIND_ERROR_CODE = "0011";
    public static final String PARTY_FIND_ERROR = GET_ERROR_LABEL.concat("party by id");


    public static final String PARTY_CREATE_ERROR_CODE = "0012";
    public static final String PARTY_CREATE_ERROR = CREATE_NEW_ERROR_LABEL.concat("party");

    public static final String RANDOM_NAME_ERROR_CODE = "0013";
    public static final String RANDOM_NAME_ERROR = CREATE_NEW_ERROR_LABEL.concat("random name");

    public static final String CLIENT_PARTY_SAVE_ERROR_CODE = "0014";
    public static final String CLIENT_PARTY_SAVE_ERROR = ADDING_ERROR_LABEL.concat("client to party");

    public static final String PARTY_ID_BY_CLIENTS_ERROR_CODE = "0015";
    public static final String PARTY_ID_BY_CLIENTS_ERROR = GET_ERROR_LABEL.concat("parties from clients");

    public static final String PARTY_FIND_ACTIVE_ERROR_CODE = "0016";
    public static final String PARTY_FIND_ACTIVE_ERROR = GET_ERROR_LABEL.concat("active parties");

    public static final String LOGOUT_CLIENTS_ERROR_CODE = "0017";
    public static final String LOGOUT_CLIENTS_ERROR = "Error trying to logout clients from table";

    public static final String ACTIVE_PARTY_BY_TABLE_ERROR_CODE = "0018";
    public static final String ACTIVE_PARTY_BY_TABLE_ERROR = "Error finding party by table code";

    public static final String SAVE_ALL_CLIENTS_ERROR_CODE = "0019";
    public static final String SAVE_ALL_CLIENTS_ERROR = "Error saving all clients when leader was changing";

    public static final String CLOSING_PARTY_ERROR_CODE = "0020";
    public static final String CLOSING_PARTY_ERROR = "Error closing party if it has no clients";

    public static final String BAN_CLIENT_ERROR_CODE = "0021";
    public static final String BAN_CLIENT_ERROR = "Error banning client";

    public static final String IS_BANNED_CLIENT_ERROR_CODE = "0022";
    public static final String IS_BANNED_CLIENT_ERROR = "Error verifying banned client";

    public static final String GET_LEADER_FROM_PARTY_ERROR_CODE = "0023";
    public static final String GET_LEADER_FROM_PARTY_ERROR = "Error when get leader from party";

    public static final String GET_CLIENT_PARTY_BY_IP_ERROR_CODE = "0024";
    public static final String GET_CLIENT_PARTY_BY_IP_ERROR = "Error when get client party by ip";

}

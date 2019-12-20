package com.onlinetext.core;

public interface Constants {
    /**
     * Command constant
     */
    public static final int GET = 1;
    public static final int PUT = 2;
    public static final int APPEND = 3;

    /**
     * Alias constant
     */
    public static final String GET_ALIAS_1 = "get";
    public static final String GET_ALIAS_2 = "r";
    public static final String PUT_ALIAS_1 = "put";
    public static final String PUT_ALIAS_2 = "w";
    public static final String APPEND_ALIAS_1 = "append";
    public static final String APPEND_ALIAS_2 = "a";

    /**
     *  Option Constants
     */
    public static final int CLIPBOARD = 4;

    /**
     * Argument Constants
     */
    public static final String FILE_NAME = "(.*)\\.(.*)";
    public static final String STRING = ".*";

    public static final String DEFAULT_ERR_MSG = "Error: Something went wrong";
}

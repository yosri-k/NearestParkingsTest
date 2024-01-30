package com.nearestParks.nearestParks.exceptions;

public class IllegalLongituteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IllegalLongituteException(String msg){
        super(msg);
    }

}

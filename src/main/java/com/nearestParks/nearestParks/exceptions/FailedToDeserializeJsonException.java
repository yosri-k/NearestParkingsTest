package com.nearestParks.nearestParks.exceptions;

public class FailedToDeserializeJsonException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public FailedToDeserializeJsonException(String msg){
        super(msg);
    }
}

package com.strengthprogress.web.backend.expection;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(Class classOfObject, String field, String value){
        super(classOfObject.getSimpleName() + " with " + field + ":" + value + " not found");
    }

    public ObjectNotFoundException(Class classOfObject){
        super(classOfObject.getSimpleName() + " not found");
    }

}
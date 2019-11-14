package com.strengthprogress.web.backend.expection;

public class ObjectAlreadyExistException extends RuntimeException {

    public ObjectAlreadyExistException(Class classOfObject,String field,String val){
        super(classOfObject.getSimpleName() + " with " + field + ":" + val + " already exist");
    }
}

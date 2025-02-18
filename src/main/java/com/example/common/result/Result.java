package com.example.common.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private boolean successful;
    private String msg;
    private T data;
    public static <T> Result<T> success(){
        Result<T> result = new Result<>();
        result.successful = true;
        return result;
    }

    public static <T> Result<T> success(T object){
        Result<T> result = new Result<>();
        result.data = object;
        result.successful = true;
        return result;
    }

    public static <T> Result<T> error(String msg){
        Result<T> result = new Result<>();
        result.msg = msg;
        result.successful = false;
        return result;
    }
}

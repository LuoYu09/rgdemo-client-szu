package com.szu.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result <T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(){
        Result<T> result = new Result<>();
        result.code = 200;
        return result;
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.code = 200;
        result.data = data;
        return result;
    }

    public static <T> Result<T> error(String message){
        Result<T> result = new Result<>();
        result.code = 500;
        result.message = message;
        return result;
    }
}

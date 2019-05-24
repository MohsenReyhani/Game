package com.mohsen.game.application.restService;

import java.util.Map;

public class StandardResponse<T> {

    private int statusCode;
    private Map<String, String> responseHeader;
    private T result;


    public StandardResponse(int statusCode, Map<String, String> responseHeader, T resutl) {
        setStatusCode(statusCode);
        setResponseHeader(responseHeader);
        setResult(resutl);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(Map<String, String> responseHeader) {
        this.responseHeader = responseHeader;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}

package com.imarkov.paring_history.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PageRequestWrapper<T> extends PageInfo {
    private T payload;

    @JsonCreator
    public PageRequestWrapper(@JsonProperty T payload, int page, int size, String sortBy, String direction) {
        super(page, size, sortBy, direction);
        this.payload = payload;
    }


    public T getPayload() {
        return payload;
    }

    public PageRequestWrapper<T> setPayload(T payload) {
        this.payload = payload;
        return this;
    }
}

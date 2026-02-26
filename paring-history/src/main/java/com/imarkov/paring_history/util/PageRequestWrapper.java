package com.imarkov.paring_history.util;

public class PageRequestWrapper<T> {
    private T payload;
    private PageInfo pageInfo;

    public PageRequestWrapper(T payload, PageInfo pageInfo) {
        this.payload = payload;
        this.pageInfo = pageInfo;
    }

    public T getPayload() {
        return payload;
    }

    public PageRequestWrapper<T> setPayload(T payload) {
        this.payload = payload;
        return this;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public PageRequestWrapper<T> setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
        return this;
    }
}

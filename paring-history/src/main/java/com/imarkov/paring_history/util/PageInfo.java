package com.imarkov.paring_history.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public class PageInfo {
    @Min(0)
    private int page;

    @Min(0)
    private int size;

    private String sortBy;

    @Pattern(regexp = "^(?i)(desc|asc)$", message = "Must be 'asc' or 'desc'")
    @Nullable
    private String direction;

    @JsonCreator
    public PageInfo(
            @JsonProperty int page,
            @JsonProperty int size,
            @JsonProperty String sortBy,
            @JsonProperty @Nullable String direction) {
        this.page = page;
        this.size = size;
        this.sortBy = sortBy;
        this.direction = direction;
    }

    // Getters and Setters
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    @Nullable
    public String getDirection() {
        return direction;
    }

    public void setDirection(@Nullable String direction) {
        this.direction = direction;
    }
}

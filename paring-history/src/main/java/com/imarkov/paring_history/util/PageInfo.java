package com.imarkov.paring_history.util;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;

public record PageInfo (@Min(0) int page,
                        @Min(0) int size,
                        String sortBy,
                        @Pattern(regexp = "^(?i)(desc|asc)$") @Nullable String direction){
}

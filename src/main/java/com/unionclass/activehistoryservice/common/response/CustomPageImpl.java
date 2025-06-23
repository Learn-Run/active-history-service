package com.unionclass.activehistoryservice.common.response;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

public record CustomPageImpl<T> (List<T> posts, int page, int size, boolean hasNext, int totalPages, long totalElements) {

    public static <T> CustomPageImpl<T> from(Page<T> pageData) {
        return new CustomPageImpl<>(
                pageData.getContent(),
                pageData.getNumber(),
                pageData.getSize(),
                pageData.hasNext(),
                pageData.getTotalPages(),
                pageData.getTotalElements()
        );
    }

    public <R> CustomPageImpl<R> map(Function<T, R> mapper) {

        return new CustomPageImpl<>(this.posts().stream().map(mapper).toList(), page, size, hasNext, totalPages, totalElements);
    }
}

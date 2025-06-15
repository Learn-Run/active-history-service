package com.unionclass.activehistoryservice.common.response;

import java.util.List;
import java.util.function.Function;

public record CursorPage<T>(List<T> content, String nextCursor, String prevCursor,
                            boolean hasNext, boolean hasPrevious, int page, int size) {

    public static <T> CursorPage<T> of(List<T> content, int size, int page, String nextCursor, String prevCursor) {

        boolean hasNext = content.size() == size;
        boolean hasPrevious = page > 1;

        return new CursorPage<>(content, nextCursor, prevCursor, hasNext, hasPrevious, page, size);
    }

    public <R> CursorPage<R> map(Function<T,R> mapper) {
        return CursorPage.of(content().stream().map(mapper).toList(), size, page, nextCursor, prevCursor);
    }
}
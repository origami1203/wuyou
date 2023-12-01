package org.wuyou.core.data.query;

import java.util.function.Function;

/**
 * @author origami
 * @date 2023/10/17 17:14
 */
public interface PageQuery<T> extends Query<T> {

    int getPageNum();

    int getPageSize();

    <R> PageQuery<R> mapToPageQuery(Function<T, R> converter);
}

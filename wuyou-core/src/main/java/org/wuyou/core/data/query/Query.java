package org.wuyou.core.data.query;

import java.util.function.Function;

/**
 * @author origami
 * @date 2023/10/17 17:01
 */
public interface Query<T> extends Condition<T>, Sort {

    <R> Query<R> mapToQuery(Function<T, R> trFunction);

}

package org.wuyou.core.data.query.impl;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import org.wuyou.core.data.query.Order;
import org.wuyou.core.data.query.Query;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author origami
 * @date 2023/10/17 17:17
 */
@Getter
public class QueryImpl<T> implements Query<T> {

    private static final List<Order> UNSORTED = Collections.emptyList();

    /**
     * 查询条件，不为null的字段将使用and组合查询
     */

    protected final T condition;
    /**
     * 排序
     */
    protected final List<Order> sort;

    @JsonCreator
    public QueryImpl(T condition, List<Order> sort) {
        this.condition = condition;
        if (sort == null) {
            sort = UNSORTED;
        }
        this.sort = sort;
    }

    public static <T> QueryImpl<T> of() {
        return new QueryImpl<>(null, null);
    }

    public static <T> QueryImpl<T> of(T condition, List<Order> sort) {
        return new QueryImpl<>(condition, sort);
    }

    @Override
    public T getEntity() {
        return condition;
    }

    @Override
    public List<Order> getSort() {
        return sort;
    }

    @Override
    public Map<String, Object> getCondition() {
        return this.condition == null ? Collections.emptyMap() : BeanUtil.beanToMap(condition, false, false);
    }

    @Override
    public <R> Query<R> mapToQuery(Function<T, R> converter) {
        return of(converter.apply(condition), sort);
    }
}

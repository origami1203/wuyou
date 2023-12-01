package org.wuyou.core.data.query.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.wuyou.core.data.query.Order;
import org.wuyou.core.data.query.PageQuery;
import org.wuyou.core.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @author origami
 * @date 2023/10/17 17:18
 */
@Getter
public class PageQueryImpl<T> extends QueryImpl<T> implements PageQuery<T> {

    private static final int DEFAULT_PAGE_SIZE = 10;

    private final int pageSize;
    private final int pageNum;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PageQueryImpl(@JsonProperty("pageNum") Integer pageNum, @JsonProperty("pageSize") Integer pageSize,
                         @JsonProperty("condition") T condition, @JsonProperty("sort") List<Order> sort) {
        super(condition, sort);

        Assert.isTrue(pageNum >= 1, "页码必须大于0");
        Assert.isTrue(pageSize >= 1, "页面数量必须大于0");

        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public static <T> PageQueryImpl<T> of(int pageNum, int pageSize) {
        return of(pageNum, pageSize, null);
    }

    public static <T> PageQueryImpl<T> of(T condition) {
        return of(1, DEFAULT_PAGE_SIZE, condition, Collections.emptyList());
    }

    public static <T> PageQueryImpl<T> of(int pageNum, int pageSize, T condition) {
        return of(pageNum, pageSize, condition, Collections.emptyList());
    }

    public static <T> PageQueryImpl<T> of(int pageNum, int pageSize, T condition, List<Order> sort) {
        return new PageQueryImpl<>(pageNum, pageSize, condition, sort);
    }

    @Override
    public <R> PageQuery<R> mapToPageQuery(Function<T, R> converter) {
        return of(pageNum, pageSize, converter.apply(condition), sort);
    }
}

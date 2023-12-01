package org.wuyou.core.data.page;

import java.util.List;
import java.util.function.Function;

/**
 * @author origami
 * @date 2023/10/19 10:31
 */
public interface Page<T> {
    /**
     * 获取每页大小
     *
     * @return page size
     */
    long getPageSize();

    /**
     * 获取当前页码
     *
     * @return page number
     */
    long getPageNum();

    /**
     * 获取数据内容
     *
     * @return {@code List<T>}
     */
    List<T> getContent();

    /**
     * 获取总记录数
     *
     * @return long
     */
    long getTotal();

    /**
     * 获取总页数
     *
     * @return 总页数
     */
    default long getTotalPages() {
        return getPageSize() == 0 ? 1 : (long) Math.ceil((double) getTotal() / (double) getPageSize());
    }

    /**
     * 类型转换
     *
     * @param converter 转换函数
     * @param <U>       转换后类型
     * @return 转换后分页数据
     */
    <U> Page<U> map(Function<? super T, ? extends U> converter);
}

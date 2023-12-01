package org.wuyou.core.data.uitl;

import cn.hutool.core.convert.Convert;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.wuyou.core.data.query.PageQuery;
import org.wuyou.core.data.query.Query;
import org.wuyou.core.data.query.impl.PageQueryImpl;

/**
 * 仿照spring-data-jpa的方式
 *
 * @author origami
 * @date 2023/10/20 11:17
 */
public abstract class QueryHelper {

    private static final String PAGE_NUMBER_PARAMETER = "pageNum";
    private static final String PAGE_SIZE_PARAMETER = "pageSize";
    private static final int DEFAULT_PAGE_NUM = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final String DEFAULT_PROPERTY_DELIMITER = ",";
    private static final String DEFAULT_QUALIFIER_DELIMITER = "_";

    public static <T> PageQuery<T> getPageQuery(MethodParameter parameter) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return new PageQueryImpl<>(DEFAULT_PAGE_NUM, DEFAULT_PAGE_SIZE, null, null);
        }

        HttpServletRequest httpservletRequest = (HttpServletRequest) requestAttributes;
        int pageNum = Convert.toInt(httpservletRequest.getParameter(PAGE_NUMBER_PARAMETER), DEFAULT_PAGE_NUM);
        int pageSize = Convert.toInt(httpservletRequest.getParameter(PAGE_SIZE_PARAMETER), DEFAULT_PAGE_SIZE);
        return null;
    }

    private static <T> Query<T> getQuery() {
        return null;
    }

}

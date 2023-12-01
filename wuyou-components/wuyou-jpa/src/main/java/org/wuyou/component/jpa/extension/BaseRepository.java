package org.wuyou.component.jpa.extension;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.wuyou.component.jpa.entity.BaseEntity;
import org.wuyou.core.data.page.Page;
import org.wuyou.core.data.page.impl.PageImpl;
import org.wuyou.core.data.query.Order;
import org.wuyou.core.data.query.PageQuery;
import org.wuyou.core.data.query.Query;

import java.util.List;

/**
 * @author origami
 * @date 2023/10/17 10:27
 */
@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, Long> {

    /**
     * Retrieves a page of elements based on the provided page query.
     *
     * @param pageQuery the page query object containing the necessary parameters for pagination
     * @return the page of elements that match the page query
     */
    default Page<E> getPage(PageQuery<E> pageQuery) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
            .withIgnoreNullValues()
            .withIgnorePaths("version", "is_deleted");

        Sort jpaSort = getJpaSort(pageQuery.getSort());
        PageRequest pageRequest = PageRequest.of(pageQuery.getPageNum() - 1, pageQuery.getPageSize(), jpaSort);

        E entity = pageQuery.getEntity();

        if (entity == null) {
            org.springframework.data.domain.Page<E> page = findAll(pageRequest);
            return PageImpl.of(pageQuery.getPageNum(), pageQuery.getPageSize(), page.getTotalElements(),
                page.getContent());
        }

        Example<E> example = Example.of(entity, exampleMatcher);
        org.springframework.data.domain.Page<E> page = this.findAll(example, pageRequest);
        return PageImpl.of(pageQuery.getPageNum(), pageQuery.getPageSize(), page.getTotalElements(), page.getContent());

    }

    /**
     * Retrieves all elements that match the given query.
     *
     * @param query the query used to filter the elements
     * @return a list of elements that match the query
     */
    default List<E> getAll(Query<E> query) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
            .withIgnoreNullValues()
            .withIgnorePaths("version", "is_deleted");

        Example<E> example = Example.of(query.getEntity(), exampleMatcher);

        Sort jpaSort = getJpaSort(query.getSort());
        return findAll(example, jpaSort);
    }

    private Sort getJpaSort(List<Order> sorts) {

        List<Sort.Order> orders = sorts.stream().map(sort -> {
            String property = sort.getProperty();
            Order.Direction direction = sort.getDirection();
            return new Sort.Order(direction.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC, property);
        }).toList();

        return Sort.by(orders);
    }
}

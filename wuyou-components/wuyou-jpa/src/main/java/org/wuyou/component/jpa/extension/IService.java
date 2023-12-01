package org.wuyou.component.jpa.extension;

import org.wuyou.component.jpa.entity.BaseEntity;

import java.util.Collection;
import java.util.List;

/**
 * @author origami
 * @date 2023/10/17 10:24
 */
public interface IService<E extends BaseEntity> {

    /**
     * Retrieves an object by its ID.
     *
     * @param id the ID of the object to retrieve
     * @return the object with the specified ID, or null if not found
     */
    E getById(Long id);

    /**
     * Retrieves all elements in the list.
     *
     * @return a list containing all elements
     */
    List<E> getAll();

    /**
     * Retrieves a list of elements by their IDs.
     *
     * @param ids a collection of IDs for the elements
     * @return a list of elements matching the provided IDs
     */
    List<E> getAllByIds(Collection<Long> ids);

    /**
     * Removes an element from the collection by its ID.
     *
     * @param id the ID of the element to be removed
     * @return the removed element, or null if no element with the given ID exists
     */
    E removeById(Long id);

    /**
     * Retrieves and removes elements from the collection based on the specified IDs.
     *
     * @param ids a collection of Long values representing the IDs of the elements to be removed
     * @return the elements that were removed from the collection
     */
    List<E> removeByIds(Collection<Long> ids);

}

package org.wuyou.core.data.query;

import java.util.Map;

/**
 * @author origami
 * @date 2023/10/17 17:04
 */
public interface Condition<T> {

    T getEntity();

    Map<String, Object> getCondition();

}

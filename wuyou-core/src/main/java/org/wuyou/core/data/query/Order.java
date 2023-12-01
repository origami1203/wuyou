package org.wuyou.core.data.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author origami
 * @date 2023/10/17 17:11
 */
@Getter
@AllArgsConstructor
public class Order {

    private final String property;

    private final Direction direction;

    public enum Direction {

        ASC,

        DESC;

        /**
         * 是否升序
         *
         * @return boolean
         */
        public boolean isAscending() {
            return ASC == this;
        }

        /**
         * 是否降序
         *
         * @return boolean
         */
        public boolean isDescending() {
            return !isAscending();
        }
    }
}

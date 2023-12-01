package org.wuyou.component.jpa.config;

import cn.hutool.core.util.IdUtil;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;

import java.util.EnumSet;

/**
 * id生成器
 *
 * @author origami
 * @date 2023/10/14 16:34
 */
public class SnowflakeIdGenerator implements BeforeExecutionGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor session, Object owner, Object currentValue,
                           EventType eventType) {
        return IdUtil.getSnowflakeNextId();
    }

    @Override
    public EnumSet<EventType> getEventTypes() {
        return EnumSet.of(EventType.INSERT);
    }
}

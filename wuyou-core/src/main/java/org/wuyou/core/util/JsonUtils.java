package org.wuyou.core.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.wuyou.core.exception.BizException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * json工具类，封装jackson
 *
 * @author origami
 * @date 2023/10/12 15:53
 */
public abstract class JsonUtils {

    private static final String DEFAULT_DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER
            // 日期格式
            .setDateFormat(new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT_PATTERN))
            // 序列化,字段对应的值为null也显示出来
            .setSerializationInclusion(JsonInclude.Include.ALWAYS)
            // json中有实体类没有的字段时,不报错
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            // 忽略空bean转json错误
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // LocalDateTime转换,javaTimeModule中已经添加,但格式是iso格式,我们添加自定义格式
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT_PATTERN);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT_PATTERN);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        // Long转String，防止js精度丢失
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, new ToStringSerializer());
        simpleModule.addSerializer(Long.TYPE, new ToStringSerializer());

        MAPPER.registerModules(javaTimeModule, simpleModule);
    }

    private JsonUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * 获取一个新的mapper
     *
     * @return objectMapper
     */
    public static ObjectMapper getObjectMapper() {
        return MAPPER.copy();
    }

    /**
     * 对象转换为json
     *
     * @param obj bean
     * @return 对象不能为空，解析失败抛异常
     */
    public static String toJson(Object obj) {

        Assert.nonNull(obj, "object不能为null");

        try {
            return obj instanceof String str ? str : MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new BizException("json解析失败：" + e.getMessage());
        }
    }

    /**
     * 返回美化json
     *
     * @param obj bean
     * @return 美化的json
     */
    public static String toJsonPretty(Object obj) {

        Assert.nonNull(obj, "bean不能为null");

        try {
            return obj instanceof String str ? str : MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new BizException("json解析失败：" + e.getMessage());
        }
    }

    /**
     * json转bean
     *
     * @param json     json
     * @param beanType bean的类型
     * @param <T>      类型
     * @return bean实例
     */
    public static <T> T fromJson(String json, Class<T> beanType) {

        Assert.nonNull(json, "json字符串不能为null");
        Assert.nonNull(beanType, "bean类型不能为null");

        try {
            return MAPPER.readValue(json, beanType);
        } catch (JsonProcessingException e) {
            throw new BizException("json解析失败：" + e.getMessage());
        }
    }

    /**
     * json转泛型类
     *
     * @param json        json串
     * @param beanTypeRef 泛型类型
     * @param <T>         泛型
     * @return 泛型实例
     */
    public static <T> T fromJson(String json, TypeReference<T> beanTypeRef) {

        Assert.nonNull(json, "json字符串不能为null");
        Assert.nonNull(beanTypeRef, "bean泛型类型不能为null");

        try {
            return MAPPER.readValue(json, beanTypeRef);
        } catch (JsonProcessingException e) {
            throw new BizException("json解析失败：" + e.getMessage());
        }
    }

}

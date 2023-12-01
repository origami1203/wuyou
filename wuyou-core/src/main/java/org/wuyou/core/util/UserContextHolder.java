package org.wuyou.core.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author origami
 * @date 2023/10/14 15:54
 */
public abstract class UserContextHolder {

    private static final String DEFAULT_ANONYMOUS_USERNAME = "AnonymousUser";
    private static final ThreadLocal<UserInfo<?>> USER_CONTEXT_HOLDER = ThreadLocal.withInitial(
        UserContextHolder::createAnonymousUser);

    private UserContextHolder() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * 获取当前用户用户名
     *
     * @return {@code String}
     */
    public static String getUsername() {
        return USER_CONTEXT_HOLDER.get().getUsername();
    }

    /**
     * 获取用户id
     *
     * @return 用户id可能为null
     */
    @SuppressWarnings("unchecked")
    public static <T> T getUserId() {
        return (T) USER_CONTEXT_HOLDER.get().getId();
    }

    public static void clearUser() {
        USER_CONTEXT_HOLDER.remove();
    }

    public static <T> void setUserInfo(T id, String username) {
        Assert.nonNull(id, "id不能为null");
        Assert.notBlank(username, "username不能为空");
        USER_CONTEXT_HOLDER.set(new UserInfo<>(id, username));
    }

    private static UserInfo<?> createAnonymousUser() {
        return new UserInfo<>(null, DEFAULT_ANONYMOUS_USERNAME);
    }

    @Data
    @AllArgsConstructor
    private static class UserInfo<T> {
        private T id;
        private String username;
    }
}

package org.bukkit.permissions;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示权限的默认值.
 * <p>原文：
 * Represents the possible default values for permissions
 */
public enum PermissionDefault {
    TRUE("true"),
    FALSE("false"),
    OP("op", "isop", "operator", "isoperator", "admin", "isadmin"),
    NOT_OP("!op", "notop", "!operator", "notoperator", "!admin", "notadmin");

    private final String[] names;
    private static final Map<String, PermissionDefault> lookup = new HashMap<String, PermissionDefault>();

    private PermissionDefault(/*@NotNull*/ String... names) {
        this.names = names;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 计算此默认权限给予某op的值.
     * <p>原文：
     * Calculates the value of this PermissionDefault for the given operator
     * value
     *
     * @param op 如果此目标是op
     * <p>原文：If the target is op
     * @return 如果默认是true，返回true，如果不是true的返回false
     * <p>原文：True if the default should be true, or false
     */
    public boolean getValue(boolean op) {
        switch (this) {
        case TRUE:
            return true;
        case FALSE:
            return false;
        case OP:
            return op;
        case NOT_OP:
            return !op;
        default:
            return false;
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 查找一个默认权限的名称.
     * <p>原文：
     * Looks up a PermissionDefault by name
     *
     * @param name 默认名称
     * <p>原文：Name of the default
     * @return 指定的值，如果为null即未找到
     * <p>原文：Specified value, or null if not found
     */
    @Nullable
    public static PermissionDefault getByName(@NotNull String name) {
        return lookup.get(name.toLowerCase(Locale.ROOT).replaceAll("[^a-z!]", ""));
    }

    @Override
    public String toString() {
        return names[0];
    }

    static {
        for (PermissionDefault value : values()) {
            for (String name : value.names) {
                lookup.put(name, value);
            }
        }
    }
}

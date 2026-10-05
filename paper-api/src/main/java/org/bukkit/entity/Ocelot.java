
package org.bukkit.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表豹猫.
 * <p>原文：
 * A wild tameable cat
 */
public interface Ocelot extends Animals {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此豹猫是否信任玩家.
     * <p>原文：
     * Checks if this ocelot trusts players.
     *
     * @return 如果信任玩家则为true
     * <p>原文：true if it trusts players
     */
    public boolean isTrusting();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此豹猫是否信任玩家.
     * <p>原文：
     * Sets if this ocelot trusts players.
     *
     * @param trust 如果信任玩家则为true
     * <p>原文：true if it trusts players
     */
    public void setTrusting(boolean trust);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这只猫的种类.
     * <p>原文：
     * Gets the current type of this cat.
     *
     * @return 猫的种类
     * <p>原文：Type of the cat.
     * @deprecated 猫现在是单独的实体
     * <p>原文：Cats are now a separate entity.
     */
    @NotNull
    @Deprecated(since = "1.19.4")
    public Type getCatType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这只猫的种类.
     * <p>原文：
     * Sets the current type of this cat.
     *
     * @param type 这只猫的新种类
     * <p>原文：New type of this cat.
     * @deprecated 猫现在是单独的实体
     * <p>原文：Cats are now a separate entity.
     */
    @Deprecated(since = "1.19.4")
    public void setCatType(@NotNull Type type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表不同的猫的种类.
     * <p>
     * 译注：具体有哪些种类及特征可以查阅MineCraft Wiki.
     * <p>原文：
     * Represents the various different cat types there are.
     *
     * @deprecated 不同猫种现在属于不同的实体.
     * <p>原文：Cats are now a separate entity.
     */
    @Deprecated(since = "1.14")
    public enum Type {
        WILD_OCELOT(0),
        BLACK_CAT(1),
        RED_CAT(2),
        SIAMESE_CAT(3);

        private static final Type[] types = new Type[Type.values().length];
        private final int id;

        static {
            for (Type type : values()) {
                types[type.getId()] = type;
            }
        }

        private Type(int id) {
            this.id = id;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 获取这个猫的种类的id.
         * <p>原文：
         * Gets the ID of this cat type.
         *
         * @return 种类id
         * <p>原文：Type ID.
         * @deprecated 不安全的参数
         * <p>原文：Magic value
         */
        @Deprecated(since = "1.6.2", forRemoval = true)
        public int getId() {
            return id;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 用id获取猫的种类.
         * <p>原文：
         * Gets a cat type by its ID.
         *
         * @param id 要获取这个种类的id
         * <p>原文：ID of the cat type to get.
         * @return 种类,如果是null的话就是没找到
         * <p>原文：Resulting type, or null if not found.
         * @deprecated 不安全的参数
         * <p>原文：Magic value
         */
        @Deprecated(since = "1.6.2", forRemoval = true)
        @Nullable
        public static Type getType(int id) {
            return (id >= types.length) ? null : types[id];
        }
    }
}

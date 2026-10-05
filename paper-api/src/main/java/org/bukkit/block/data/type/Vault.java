package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'vault_state' 表示宝库方块当前的运行阶段.
 * <br>
 * 'ominous' 表示方块是否具有不祥效果.
 * <p>原文：
 * 'vault_state' indicates the current operational phase of the vault block.
 * <br>
 * 'ominous' indicates if the block has ominous effects.
 */
public interface Vault extends Directional {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'vault_state' 属性的值.
     * <p>原文：
     * Gets the value of the 'vault_state' property.
     *
     * @return 'vault_state' 的值
     * <p>原文：the 'vault_state' value
     */
    @NotNull
    State getVaultState();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'vault_state' 属性的值.
     * <p>原文：
     * Gets the value of the 'vault_state' property.
     *
     * @return 'vault_state' 的值
     * <p>原文：the 'vault_state' value
     * @deprecated 参见 {@link #getVaultState()}
     * <p>原文：see {@link #getVaultState()}
     */
    @Deprecated(since = "1.21.3", forRemoval = true)
    @NotNull
    default State getTrialSpawnerState() {
        return this.getVaultState();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'vault_state' 属性的值.
     * <p>原文：
     * Sets the value of the 'vault_state' property.
     *
     * @param state 新的 'vault_state' 值
     * <p>原文：the new 'vault_state' value
     */
    void setVaultState(@NotNull State state);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'vault_state' 属性的值.
     * <p>原文：
     * Sets the value of the 'vault_state' property.
     *
     * @param state 新的 'vault_state' 值
     * <p>原文：the new 'vault_state' value
     * @deprecated 参见 {@link #setVaultState(State)}
     * <p>原文：see {@link #setVaultState(State)}
     */
    @Deprecated(since = "1.21.3", forRemoval = true)
    default void setTrialSpawnerState(@NotNull State state) {
        this.setVaultState(state);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'ominous' 属性的值.
     * <p>原文：
     * Gets the value of the 'ominous' property.
     *
     * @return 'ominous' 的值
     * <p>原文：the 'ominous' value
     */
    boolean isOminous();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'ominous' 属性的值.
     * <p>原文：
     * Sets the value of the 'ominous' property.
     *
     * @param ominous 新的 'ominous' 值
     * <p>原文：the new 'ominous' value
     */
    void setOminous(boolean ominous);

    public enum State {

        INACTIVE,
        ACTIVE,
        UNLOCKING,
        EJECTING
    }
}

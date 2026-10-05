package org.bukkit.material;

import org.bukkit.Material;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表绊线。
 * <p>原文：
 * Represents the tripwire
 *
 * @deprecated 所有 MaterialData 的用法都已弃用并可能被移除。
 * 请使用 {@link org.bukkit.block.data.BlockData}。
 * <p>原文：all usage of MaterialData is deprecated and subject to removal.
 * Use {@link org.bukkit.block.data.BlockData}.
 */
@Deprecated(since = "1.13", forRemoval = true)
public class Tripwire extends MaterialData {

    public Tripwire() {
        super(Material.LEGACY_TRIPWIRE);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param type 物品类型
     * <p>原文：the type
     * @param data 原始数据值
     * <p>原文：the raw data value
     * @deprecated 魔法值
     * <p>原文：Magic value
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public Tripwire(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 测试绊线当前是否被激活。
     * <p>原文：
     * Test if tripwire is currently activated
     *
     * @return 如果被激活则返回 true，否则返回 false
     * <p>原文：Test if tripwire is currently activated
     * <p>原文：true if activated, false if not
     */
    public boolean isActivated() {
        return (getData() & 0x4) != 0;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置绊线的激活状态。
     * <p>原文：
     * Set tripwire activated state
     *
     * @param act - 如果被激活则为 true，否则为 false
     * <p>原文：Set tripwire activated state
     * <p>原文：- true if activated, false if not
     */
    public void setActivated(boolean act) {
        int dat = getData() & (0x8 | 0x3);
        if (act) {
            dat |= 0x4;
        }
        setData((byte) dat);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 测试是否有物体直接触发此绊线。
     * <p>原文：
     * Test if object triggering this tripwire directly
     *
     * @return 如果有物体触发绊线则返回 true，否则返回 false
     * <p>原文：Test if object triggering this tripwire directly
     * <p>原文：true if object activating tripwire, false if not
     */
    public boolean isObjectTriggering() {
        return (getData() & 0x1) != 0;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此绊线的物体触发状态。
     * <p>原文：
     * Set object triggering state for this tripwire
     *
     * @param trig - 如果有物体触发绊线则为 true，否则为 false
     * <p>原文：Set object triggering state for this tripwire
     * <p>原文：- true if object activating tripwire, false if not
     */
    public void setObjectTriggering(boolean trig) {
        int dat = getData() & 0xE;
        if (trig) {
            dat |= 0x1;
        }
        setData((byte) dat);
    }

    @Override
    public Tripwire clone() {
        return (Tripwire) super.clone();
    }

    @Override
    public String toString() {
        return super.toString() + (isActivated() ? " Activated" : "") + (isObjectTriggering() ? " Triggered" : "");
    }
}

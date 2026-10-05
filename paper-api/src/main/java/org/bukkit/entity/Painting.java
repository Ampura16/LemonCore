package org.bukkit.entity;

import org.bukkit.Art;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表画.
 * <p>
 * 前往<a href="http://zh.minecraft.wiki/w/%E7%94%BB">wiki</a>了解更多
 * <p>原文：
 * Represents a Painting.
 */
public interface Painting extends Hanging {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这幅画上的画面/图案.
     * <p>原文：
     * Get the art on this painting
     *
     * @return 画面/图案
     * <p>原文：The art
     */
    @NotNull
    public Art getArt();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这幅画上的画面/图案.
     * <p>原文：
     * Set the art on this painting
     *
     * @param art 新的画面/图案
     * <p>原文：The new art
     * @return 如果新的画面/图案不适合这幅画的位置、尺寸等将会返回false
     * <p>原文：False if the new art won't fit at the painting's current
     *     location
     */
    public boolean setArt(@NotNull Art art);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这幅画的画面/图案.
     * <p>原文：
     * Set the art on this painting
     *
     * @param art 新的画面/图案
     * <p>原文：The new art
     * @param force 如果这个设为true，无论当前的位置、尺寸是否适合这幅画面/图案.注意，强制执行这可能会使这幅画掉落成物品，除非你用{@link HangingBreakEvent}重写
     * <p>原文：If true, force the new art regardless of whether it fits
     *     at the current location. Note that forcing it where it can't fit
     *     normally causes it to drop as an item unless you override this by
     *     catching the {@link HangingBreakEvent}.
     * @return 如果force参数是false或新的画面/图案不适合这幅画的位置、尺寸等将会返回false
     * <p>原文：False if force was false and the new art won't fit at the
     *     painting's current location
     */
    public boolean setArt(@NotNull Art art, boolean force);
}

package org.bukkit.block;

import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当方块或实体与正在推拉的活塞交互时, 它们作何反应.
 * <p>原文：
 * Represents how a block or entity will react when interacting with a piston
 * when it is extending or retracting.
 */
public enum PistonMoveReaction {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表示方块可以推拉.
     * <p>原文：
     * Indicates that the block can be pushed or pulled.
     */
    MOVE(0),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当被活塞推动时, 方块变得脆弱并被破坏.
     * <p>原文：
     * Indicates the block is fragile and will break if pushed on.
     */
    BREAK(1),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表明此方块不能被推或拉.
     * <p>原文：
     * Indicates that the block will resist being pushed or pulled.
     */
    BLOCK(2),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表明实体将忽略任何与活塞的交互.
     * <br>
     * 方块一类应使用 {@link PistonMoveReaction#BLOCK}.
     * <p>原文：
     * Indicates that the entity will ignore any interaction(s) with
     * pistons.
     * <br>
     * Blocks should use {@link PistonMoveReaction#BLOCK}.
     */
    IGNORE(3),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表明方块只可被活塞推动, 不能被拉动.
     * <p>原文：
     * Indicates that the block can only be pushed by pistons, not pulled.
     */
    PUSH_ONLY(4);

    private int id;
    private static Map<Integer, PistonMoveReaction> byId = new HashMap<Integer, PistonMoveReaction>();
    static {
        for (PistonMoveReaction reaction : PistonMoveReaction.values()) {
            byId.put(reaction.id, reaction);
        }
    }

    private PistonMoveReaction(int id) {
        this.id = id;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 此移动的反应ID
     * <p>原文：The ID of the move reaction
     * @apiNote Internal Use Only
     */
    @org.jetbrains.annotations.ApiStatus.Internal // Paper
    public int getId() {
        return this.id;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param id An ID
     * @return 这个移动反应的ID
     * <p>原文：The move reaction with that ID
     * @apiNote Internal Use Only
     */
    @org.jetbrains.annotations.ApiStatus.Internal // Paper
    @Nullable
    public static PistonMoveReaction getById(int id) {
        return byId.get(id);
    }
}

package net.minecraft.world.level.pathfinder;

/**
 * 表示寻路系统中方块/位置的"路径类型",用于描述实体在该位置移动的难度或危险性.
 * 每种类型带有一个惩罚值（malus）,寻路时会作为节点代价的一部分参与计算,
 * 负值表示该类型完全不可通行（阻塞）,见 {@link WalkNodeEvaluator#getPathTypeStatic}
 * 与 {@link net.minecraft.world.entity.Mob#getPathfindingMalus}.
 */
public enum PathType {
    /** 完全阻塞,不可通行的方块. */
    BLOCKED(-1.0F),
    /** 空气或无碰撞的空旷位置. */
    OPEN(0.0F),
    /** 可正常行走的地面. */
    WALKABLE(0.0F),
    /** 可通行的门（实体可以打开或穿过）. */
    WALKABLE_DOOR(0.0F),
    /** 活板门（trapdoor）等半通行方块. */
    TRAPDOOR(0.0F),
    /** 细雪（Powder Snow）,实体会陷入下沉,视为不可通行. */
    POWDER_SNOW(-1.0F),
    /** 细雪附近的危险区域（提示危险但仍可通行）. */
    DANGER_POWDER_SNOW(0.0F),
    /** 栅栏（Fence）方块,默认不可通行（除非允许跨越栅栏）. */
    FENCE(-1.0F),
    /** 岩浆,默认不可通行. */
    LAVA(-1.0F),
    /** 水中,带有较高的移动惩罚. */
    WATER(8.0F),
    /** 水域边界,带有移动惩罚. */
    WATER_BORDER(8.0F),
    /** 铁轨,可正常通行. */
    RAIL(0.0F),
    /** 无法到达/离开的铁轨（例如孤立铁轨）,不可通行. */
    UNPASSABLE_RAIL(-1.0F),
    /** 靠近火源的危险区域. */
    DANGER_FIRE(8.0F),
    /** 正在燃烧、会造成伤害的方块. */
    DAMAGE_FIRE(16.0F),
    /** 靠近其他伤害来源的危险区域. */
    DANGER_OTHER(8.0F),
    /** 会造成伤害的其他方块（如仙人掌、甜浆果丛）,默认不可通行. */
    DAMAGE_OTHER(-1.0F),
    /** 已打开的门. */
    DOOR_OPEN(0.0F),
    /** 关闭的木门,不可通行（除非实体能打开门）. */
    DOOR_WOOD_CLOSED(-1.0F),
    /** 关闭的铁门,不可通行（无法手动打开）. */
    DOOR_IRON_CLOSED(-1.0F),
    /** 水面（用于水生实体的破水而出,Breach）. */
    BREACH(4.0F),
    /** 树叶方块,默认不可通行. */
    LEAVES(-1.0F),
    /** 蜂蜜块,移动会被减速,带有较高惩罚. */
    STICKY_HONEY(8.0F),
    /** 可可豆方块. */
    COCOA(0.0F),
    /** 需要谨慎对待的伤害方块（如凋零玫瑰、垂滴石）. */
    DAMAGE_CAUTIOUS(0.0F),
    /** 活板门附近的危险区域. */
    DANGER_TRAPDOOR(0.0F);

    /** 该路径类型对应的移动惩罚值（cost malus）,负值表示不可通行. */
    private final float malus;

    /**
     * 构造一个路径类型枚举常量.
     *
     * @param malus 该类型对应的移动惩罚值
     */
    private PathType(final float malus) {
        this.malus = malus;
    }

    /**
     * 获取该路径类型默认的移动惩罚值.
     * 实际使用时可能会被 {@link net.minecraft.world.entity.Mob#getPathfindingMalus}
     * 中针对具体实体设置的自定义惩罚值覆盖.
     *
     * @return 惩罚值,负值表示该类型不可通行
     */
    public float getMalus() {
        return this.malus;
    }
}

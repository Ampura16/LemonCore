package net.minecraft.world.entity.ai.attributes;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

/**
 * 实体属性注册类
 * 定义了 Minecraft 中所有实体的基础属性
 */
public class Attributes {
    /** 默认攻击速度 */
    public static final double DEFAULT_ATTACK_SPEED = 4.0;

    /** 护甲值 (范围: 0.0-30.0, 默认: 0.0) */
    public static final Holder<Attribute> ARMOR = register("armor", new RangedAttribute("attribute.name.armor", 0.0, 0.0, 30.0).setSyncable(true));

    /** 护甲韧性 (范围: 0.0-20.0, 默认: 0.0) */
    public static final Holder<Attribute> ARMOR_TOUGHNESS = register(
        "armor_toughness", new RangedAttribute("attribute.name.armor_toughness", 0.0, 0.0, 20.0).setSyncable(true)
    );

    /** 攻击伤害 (默认: 2.0, 最大值由 Spigot 配置决定) */
    public static final Holder<Attribute> ATTACK_DAMAGE = register("attack_damage", new RangedAttribute("attribute.name.attack_damage", 2.0, 0.0, org.spigotmc.SpigotConfig.attackDamage)); // Spigot

    /** 攻击击退 (范围: 0.0-5.0, 默认: 0.0) */
    public static final Holder<Attribute> ATTACK_KNOCKBACK = register("attack_knockback", new RangedAttribute("attribute.name.attack_knockback", 0.0, 0.0, 5.0));

    /** 攻击速度 (范围: 0.0-1024.0, 默认: 4.0) */
    public static final Holder<Attribute> ATTACK_SPEED = register(
        "attack_speed", new RangedAttribute("attribute.name.attack_speed", 4.0, 0.0, 1024.0).setSyncable(true)
    );

    /** 方块破坏速度 (范围: 0.0-1024.0, 默认: 1.0) */
    public static final Holder<Attribute> BLOCK_BREAK_SPEED = register(
        "block_break_speed", new RangedAttribute("attribute.name.block_break_speed", 1.0, 0.0, 1024.0).setSyncable(true)
    );

    /** 方块交互范围 (范围: 0.0-64.0, 默认: 4.5) */
    public static final Holder<Attribute> BLOCK_INTERACTION_RANGE = register(
        "block_interaction_range", new RangedAttribute("attribute.name.block_interaction_range", 4.5, 0.0, 64.0).setSyncable(true)
    );

    /** 燃烧时间倍率 (范围: 0.0-1024.0, 默认: 1.0, 负面属性) */
    public static final Holder<Attribute> BURNING_TIME = register(
        "burning_time", new RangedAttribute("attribute.name.burning_time", 1.0, 0.0, 1024.0).setSyncable(true).setSentiment(Attribute.Sentiment.NEGATIVE)
    );

    /** 相机距离 (范围: 0.0-32.0, 默认: 4.0) */
    public static final Holder<Attribute> CAMERA_DISTANCE = register(
        "camera_distance", new RangedAttribute("attribute.name.camera_distance", 4.0, 0.0, 32.0).setSyncable(true)
    );

    /** 爆炸击退抗性 (范围: 0.0-1.0, 默认: 0.0) */
    public static final Holder<Attribute> EXPLOSION_KNOCKBACK_RESISTANCE = register(
        "explosion_knockback_resistance", new RangedAttribute("attribute.name.explosion_knockback_resistance", 0.0, 0.0, 1.0).setSyncable(true)
    );

    /** 实体交互范围 (范围: 0.0-64.0, 默认: 3.0) */
    public static final Holder<Attribute> ENTITY_INTERACTION_RANGE = register(
        "entity_interaction_range", new RangedAttribute("attribute.name.entity_interaction_range", 3.0, 0.0, 64.0).setSyncable(true)
    );

    /** 摔落伤害倍率 (范围: 0.0-100.0, 默认: 1.0, 负面属性) */
    public static final Holder<Attribute> FALL_DAMAGE_MULTIPLIER = register(
        "fall_damage_multiplier",
        new RangedAttribute("attribute.name.fall_damage_multiplier", 1.0, 0.0, 100.0).setSyncable(true).setSentiment(Attribute.Sentiment.NEGATIVE)
    );

    /** 飞行速度 (范围: 0.0-1024.0, 默认: 0.4) */
    public static final Holder<Attribute> FLYING_SPEED = register(
        "flying_speed", new RangedAttribute("attribute.name.flying_speed", 0.4, 0.0, 1024.0).setSyncable(true)
    );

    /** 跟随范围 (范围: 0.0-2048.0, 默认: 32.0) */
    public static final Holder<Attribute> FOLLOW_RANGE = register("follow_range", new RangedAttribute("attribute.name.follow_range", 32.0, 0.0, 2048.0));

    /** 重力 (范围: -1.0-1.0, 默认: 0.08, 中性属性) */
    public static final Holder<Attribute> GRAVITY = register(
        "gravity", new RangedAttribute("attribute.name.gravity", 0.08, -1.0, 1.0).setSyncable(true).setSentiment(Attribute.Sentiment.NEUTRAL)
    );

    /** 跳跃力量 (范围: 0.0-32.0, 默认: 0.42) */
    public static final Holder<Attribute> JUMP_STRENGTH = register(
        "jump_strength", new RangedAttribute("attribute.name.jump_strength", 0.42F, 0.0, 32.0).setSyncable(true)
    );

    /** 击退抗性 (范围: 0.0-1.0, 默认: 0.0) */
    public static final Holder<Attribute> KNOCKBACK_RESISTANCE = register(
        "knockback_resistance", new RangedAttribute("attribute.name.knockback_resistance", 0.0, 0.0, 1.0)
    );

    /** 幸运值 (范围: -1024.0-1024.0, 默认: 0.0) */
    public static final Holder<Attribute> LUCK = register("luck", new RangedAttribute("attribute.name.luck", 0.0, -1024.0, 1024.0).setSyncable(true));

    /** 最大伤害吸收值 (默认: 0.0, 最大值由 Spigot 配置决定) */
    public static final Holder<Attribute> MAX_ABSORPTION = register(
        "max_absorption", new RangedAttribute("attribute.name.max_absorption", 0.0, 0.0, org.spigotmc.SpigotConfig.maxAbsorption).setSyncable(true) // Spigot
    );

    /** 最大生命值 (范围: 1.0-配置上限, 默认: 20.0) */
    public static final Holder<Attribute> MAX_HEALTH = register(
        "max_health", new RangedAttribute("attribute.name.max_health", 20.0, 1.0, org.spigotmc.SpigotConfig.maxHealth).setSyncable(true) // Spigot
    );

    /** 挖掘效率 (范围: 0.0-1024.0, 默认: 0.0) */
    public static final Holder<Attribute> MINING_EFFICIENCY = register(
        "mining_efficiency", new RangedAttribute("attribute.name.mining_efficiency", 0.0, 0.0, 1024.0).setSyncable(true)
    );

    /** 移动效率 (范围: 0.0-1.0, 默认: 0.0) */
    public static final Holder<Attribute> MOVEMENT_EFFICIENCY = register(
        "movement_efficiency", new RangedAttribute("attribute.name.movement_efficiency", 0.0, 0.0, 1.0).setSyncable(true)
    );

    /** 移动速度 (默认: 0.7, 最大值由 Spigot 配置决定) */
    public static final Holder<Attribute> MOVEMENT_SPEED = register(
        "movement_speed", new RangedAttribute("attribute.name.movement_speed", 0.7, 0.0, org.spigotmc.SpigotConfig.movementSpeed).setSyncable(true) // Spigot
    );

    /** 额外氧气值 (范围: 0.0-1024.0, 默认: 0.0) */
    public static final Holder<Attribute> OXYGEN_BONUS = register(
        "oxygen_bonus", new RangedAttribute("attribute.name.oxygen_bonus", 0.0, 0.0, 1024.0).setSyncable(true)
    );

    /** 安全掉落距离 (范围: -1024.0-1024.0, 默认: 3.0) */
    public static final Holder<Attribute> SAFE_FALL_DISTANCE = register(
        "safe_fall_distance", new RangedAttribute("attribute.name.safe_fall_distance", 3.0, -1024.0, 1024.0).setSyncable(true)
    );

    /** 实体缩放比例 (范围: 0.0625-16.0, 默认: 1.0, 中性属性) */
    public static final Holder<Attribute> SCALE = register(
        "scale", new RangedAttribute("attribute.name.scale", 1.0, 0.0625, 16.0).setSyncable(true).setSentiment(Attribute.Sentiment.NEUTRAL)
    );

    /** 潜行速度 (范围: 0.0-1.0, 默认: 0.3) */
    public static final Holder<Attribute> SNEAKING_SPEED = register(
        "sneaking_speed", new RangedAttribute("attribute.name.sneaking_speed", 0.3, 0.0, 1.0).setSyncable(true)
    );

    /** 生成增援概率 (范围: 0.0-1.0, 默认: 0.0) */
    public static final Holder<Attribute> SPAWN_REINFORCEMENTS_CHANCE = register(
        "spawn_reinforcements", new RangedAttribute("attribute.name.spawn_reinforcements", 0.0, 0.0, 1.0)
    );

    /** 台阶高度 (范围: 0.0-10.0, 默认: 0.6) */
    public static final Holder<Attribute> STEP_HEIGHT = register(
        "step_height", new RangedAttribute("attribute.name.step_height", 0.6, 0.0, 10.0).setSyncable(true)
    );

    /** 水下挖掘速度 (范围: 0.0-20.0, 默认: 0.2) */
    public static final Holder<Attribute> SUBMERGED_MINING_SPEED = register(
        "submerged_mining_speed", new RangedAttribute("attribute.name.submerged_mining_speed", 0.2, 0.0, 20.0).setSyncable(true)
    );

    /** 横扫伤害比例 (范围: 0.0-1.0, 默认: 0.0) */
    public static final Holder<Attribute> SWEEPING_DAMAGE_RATIO = register(
        "sweeping_damage_ratio", new RangedAttribute("attribute.name.sweeping_damage_ratio", 0.0, 0.0, 1.0).setSyncable(true)
    );

    /** 诱惑范围 (范围: 0.0-2048.0, 默认: 10.0) */
    public static final Holder<Attribute> TEMPT_RANGE = register("tempt_range", new RangedAttribute("attribute.name.tempt_range", 10.0, 0.0, 2048.0));

    /** 水中移动效率 (范围: 0.0-1.0, 默认: 0.0) */
    public static final Holder<Attribute> WATER_MOVEMENT_EFFICIENCY = register(
        "water_movement_efficiency", new RangedAttribute("attribute.name.water_movement_efficiency", 0.0, 0.0, 1.0).setSyncable(true)
    );

    /** 航点发送范围 (范围: 0.0-60000000.0, 默认: 0.0, 中性属性) */
    public static final Holder<Attribute> WAYPOINT_TRANSMIT_RANGE = register(
        "waypoint_transmit_range", new RangedAttribute("attribute.name.waypoint_transmit_range", 0.0, 0.0, 6.0E7).setSentiment(Attribute.Sentiment.NEUTRAL)
    );

    /** 航点接收范围 (范围: 0.0-60000000.0, 默认: 0.0, 中性属性) */
    public static final Holder<Attribute> WAYPOINT_RECEIVE_RANGE = register(
        "waypoint_receive_range", new RangedAttribute("attribute.name.waypoint_receive_range", 0.0, 0.0, 6.0E7).setSentiment(Attribute.Sentiment.NEUTRAL)
    );

    /**
     * 注册属性到内置注册表
     * @param name 属性名称
     * @param attribute 属性对象
     * @return 属性持有者
     */
    private static Holder<Attribute> register(String name, Attribute attribute) {
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, Identifier.withDefaultNamespace(name), attribute);
    }

    /**
     * 引导方法，返回最大生命值属性
     * @param registry 属性注册表
     * @return 最大生命值属性持有者
     */
    public static Holder<Attribute> bootstrap(Registry<Attribute> registry) {
        return MAX_HEALTH;
    }
}

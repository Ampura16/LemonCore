package io.papermc.paper;

import com.destroystokyo.paper.SkinParts;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.datacomponent.item.ResolvableProfile;
import io.papermc.paper.world.damagesource.CombatEntry;
import io.papermc.paper.world.damagesource.FallLocationType;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.util.Services;
import org.bukkit.GameRule;
import org.bukkit.block.Biome;
import org.bukkit.damage.DamageEffect;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pose;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 通往服务器内部的静态桥接。
 * <p>
 * 此处的任何方法*都不*应由插件开发者调用，它们可能随时更改，并且在意外情况下调用时通常会导致问题。
 * <p>原文：
 * Static bridge to the server internals.
 * <p>
 * Any and all methods in here are *not* to be called by plugin developers, may change at any time and may generally
 * cause issues when called under unexpected circumstances.
 */
@ApiStatus.Internal
@NullMarked
public interface InternalAPIBridge {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 通过 Java 服务加载器 API 惰性请求并返回此 API 桥接的实例。
     * <p>原文：
     * Yields the instance of this API bridge by lazily requesting it from the java service loader API.
     *
     * @return 实例。
     * <p>原文：the instance.
     */
    static InternalAPIBridge get() {
        class Holder {
            public static final InternalAPIBridge INSTANCE = Services.service(InternalAPIBridge.class).orElseThrow();
        }

        return Holder.INSTANCE;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为传入的键创建伤害效果实例。
     * <p>原文：
     * Creates a damage effect instance for the passed key.
     *
     * @param key 字符串键。
     * <p>原文：the string key.
     * @return 伤害效果。
     * <p>原文：the damage effect.
     */
    DamageEffect getDamageEffect(String key);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为生物群系枚举构造旧版自定义生物群系实例。
     * <p>原文：
     * Constructs the legacy custom biome instance for the biome enum.
     *
     * @return 创建的生物群系。
     * <p>原文：the created biome.
     */
    @Deprecated(forRemoval = true, since = "1.21.5")
    @ApiStatus.ScheduledForRemoval(inVersion = "1.22")
    Biome constructLegacyCustomBiome();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建新的战斗记录。
     * <p>
     * 摔落位置和摔落距离将根据实体的当前状态计算。
     * <p>原文：
     * Creates a new combat entry.
     * <p>
     * The fall location and fall distance will be calculated from the entity's current state.
     *
     * @param entity 实体
     * <p>原文：entity
     * @param damageSource 伤害来源
     * <p>原文：damage source
     * @param damage 伤害量
     * <p>原文：damage amount
     * @return 新的战斗记录
     * <p>原文：new combat entry
     */
    CombatEntry createCombatEntry(LivingEntity entity, DamageSource damageSource, float damage);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建新的战斗记录
     * <p>原文：
     * Creates a new combat entry
     *
     * @param damageSource 伤害来源
     * <p>原文：damage source
     * @param damage 伤害量
     * <p>原文：damage amount
     * @param fallLocationType 摔落位置类型
     * <p>原文：fall location type
     * @param fallDistance 摔落距离
     * <p>原文：fall distance
     * @return 战斗记录
     * <p>原文：combat entry
     */
    CombatEntry createCombatEntry(DamageSource damageSource, float damage, @Nullable FallLocationType fallLocationType, float fallDistance);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使该谓词被视为受限的。将其应用于命令节点可防止该命令在无人值守的上下文（例如点击事件）中执行。
     * <p>原文：
     * Causes this predicate to be considered restricted.
     * Applying this to a command node prevents this command from being executed from an
     * unattended context, such as click events.
     *
     * @param predicate 包装的谓词
     * <p>原文：wrapped predicate
     * @return 包装的谓词
     * <p>原文：wrapped predicate
     */
    Predicate<CommandSourceStack> restricted(Predicate<CommandSourceStack> predicate);

    ResolvableProfile defaultMannequinProfile();

    @Contract(value = "-> new", pure = true)
    SkinParts.Mutable allSkinParts();

    Component defaultMannequinDescription();

    <MODERN, LEGACY> GameRule<LEGACY> legacyGameRuleBridge(GameRule<MODERN> rule, Function<LEGACY, MODERN> fromLegacyToModern, Function<MODERN, LEGACY> toLegacyFromModern, Class<LEGACY> legacyClass);

    Set<Pose> validMannequinPoses();
}

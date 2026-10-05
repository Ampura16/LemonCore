package org.bukkit.entity;

import com.google.common.base.Preconditions;
import java.util.Locale;
import java.util.Map; // Paper
import java.util.UUID; // Paper
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;
import org.bukkit.Keyed;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.util.OldEnum;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示村民NPC
 * <p>原文：
 * Represents a villager NPC
 */
public interface Villager extends AbstractVillager {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此村民的当前职业。
     * <p>原文：
     * Gets the current profession of this villager.
     *
     * @return 当前职业。
     * <p>
     * 原文：Gets the current profession of this villager.
     * <p>原文：Current profession.
     */
    @NotNull
    public Profession getProfession();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此村民的新职业。
     * <p>原文：
     * Sets the new profession of this villager.
     *
     * @param profession 新职业。
     * <p>
     * 原文：Sets the new profession of this villager.
     * <p>原文：New profession.
     */
    public void setProfession(@NotNull Profession profession);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此村民的当前类型。
     * <p>原文：
     * Gets the current type of this villager.
     *
     * @return 当前类型。
     * <p>
     * 原文：Gets the current type of this villager.
     * <p>原文：Current type.
     */
    @NotNull
    public Type getVillagerType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此村民的新类型。
     * <p>原文：
     * Sets the new type of this villager.
     *
     * @param type 新类型。
     * <p>
     * 原文：Sets the new type of this villager.
     * <p>原文：New type.
     */
    public void setVillagerType(@NotNull Type type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此村民的等级。
     *
     * 等级为1且没有经验的村民可能会失去其职业。
     * <p>原文：
     * Gets the level of this villager.
     *
     * A villager with a level of 1 and no experience is liable to lose its
     * profession.
     *
     * @return 此村民的等级
     * <p>
     * 原文：Gets the level of this villager.
     *
     * A villager with a level of 1 and no experience is liable to lose its
     * profession.
     * <p>原文：this villager's level
     */
    public int getVillagerLevel();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此村民的等级。
     *
     * 等级为1且没有经验的村民可能会失去其职业。
     * <p>原文：
     * Sets the level of this villager.
     *
     * A villager with a level of 1 and no experience is liable to lose its
     * profession.
     *
     * This doesn't update the trades of this villager.
     *
     * @param level 新等级
     * <p>原文：the new level
     * @throws IllegalArgumentException 如果等级不在[1, 5]范围内
     * <p>
     * 原文：Sets the level of this villager.
     *
     * A villager with a level of 1 and no experience is liable to lose its
     * profession.
     * <p>原文：if level not between [1, 5]
     * @see #increaseLevel(int)
     */
    public void setVillagerLevel(int level);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此村民的交易经验。
     * <p>原文：
     * Gets the trading experience of this villager.
     *
     * @return 交易经验
     * <p>
     * 原文：Gets the trading experience of this villager.
     * <p>原文：trading experience
     */
    public int getVillagerExperience();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此村民的交易经验。
     * <p>原文：
     * Sets the trading experience of this villager.
     *
     * @param experience 新经验
     * <p>原文：new experience
     * @throws IllegalArgumentException 如果经验 &lt; 0
     * <p>
     * 原文：Sets the trading experience of this villager.
     * <p>原文：if experience &lt; 0
     */
    public void setVillagerExperience(int experience);

    // Paper start
    /**
     * Increases the level of this villager.
     * The villager will also unlock new recipes unlike the raw
     * method {@link #setVillagerLevel(int)}.
     * <p>
     * A villager with a level of 1 and no experience is liable to lose its
     * profession.
     * <p>
     * A master villager has a level of 5 in its profession and
     * will unlock 10 trades (2 per level).
     *
     * @param amount The amount of level
     * @return Whether trades are unlocked
     * @throws IllegalArgumentException if current level plus the amount
     * isn't between [1, 5] or the amount isn't positive
     * @see #setVillagerLevel(int)
     */
    boolean increaseLevel(int amount);

    /**
     * Gives to this villager some potential new trades
     * based to its profession and level.
     * @param amount The amount of trades to give
     * @return Whether trades are added
     * @throws IllegalArgumentException if the amount isn't positive
     */
    boolean addTrades(int amount);

    /**
     * Gets the amount of times a villager has restocked their trades today
     * @return The amount of trade restocks.
     */
    public int getRestocksToday();

    /**
     * Sets the amount of times a villager has restocked their trades today
     * @param restocksToday new restock count
     */
    public void setRestocksToday(int restocksToday);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 尝试让此村民在给定位置睡觉。
     * <br>
     * 该位置必须在当前世界中，并且在该位置放置了床。村民在睡觉时会将头放在指定的方块上。
     * <p>原文：
     * Attempts to make this villager sleep at the given location.
     * <br>
     * The location must be in the current world and have a bed placed at the
     * location. The villager will put its head on the specified block while
     * sleeping.
     *
     * @param location 床的位置
     * <p>原文：the location of the bed
     * @return 睡觉是否成功
     * <p>
     * 原文：Attempts to make this villager sleep at the given location.
     * <br>
     * The location must be in the current world and have a bed placed at the
     * location. The villager will put its head on the specified block while
     * sleeping.
     * <p>原文：whether the sleep was successful
     */
    public boolean sleep(@NotNull Location location);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 如果此村民正在睡觉，则唤醒他。
     * <p>原文：
     * Causes this villager to wake up if he's currently sleeping.
     *
     * @throws IllegalStateException 如果没有在睡觉
     * <p>
     * 原文：Causes this villager to wake up if he's currently sleeping.
     * <p>原文：if not sleeping
     */
    public void wakeup();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 让此村民摇头。
     * <p>原文：
     * Causes this villager to shake his head.
     */
    public void shakeHead();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将此村民转换为僵尸村民，就像被僵尸杀死一样。
     *
     * <b>注意：</b>这将触发EntityTransformEvent
     * <p>原文：
     * Convert this Villager into a ZombieVillager as if it was killed by a
     * Zombie.
     *
     * <b>Note:</b> this will fire a EntityTransformEvent
     *
     * @return 转换后的实体{@link ZombieVillager}，如果转换被取消则返回null
     * <p>
     * 原文：Convert this Villager into a ZombieVillager as if it was killed by a
     * Zombie.
     *
     * <b>Note:</b> this will fire a EntityTransformEvent
     * <p>原文：the converted entity {@link ZombieVillager} or null if the
     * conversion its cancelled
     */
    @Nullable
    public ZombieVillager zombify();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表示村民类型，通常对应它们生成的生物群系。
     * <p>原文：
     * Represents Villager type, usually corresponding to what biome they spawn
     * in.
     */
    interface Type extends OldEnum<Type>, Keyed {

        // Start generate - VillagerType
        Type DESERT = getType("desert");

        Type JUNGLE = getType("jungle");

        Type PLAINS = getType("plains");

        Type SAVANNA = getType("savanna");

        Type SNOW = getType("snow");

        Type SWAMP = getType("swamp");

        Type TAIGA = getType("taiga");
        // End generate - VillagerType

        @NotNull
        private static Type getType(@NotNull @KeyPattern.Value String key) {
            return Registry.VILLAGER_TYPE.getOrThrow(Key.key(Key.MINECRAFT_NAMESPACE, key));
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         *
         *
         * @param name 村民类型名称。
         * <p>原文：of the villager type.
         * @return 具有给定名称的村民类型。
         * <p>原文：the villager type with the given name.
         * @deprecated 仅为向后兼容，请使用{@link Registry#get(NamespacedKey)}代替。
         * <p>
         * 原文：@param name of the villager type.
         * <p>原文：only for backwards compatibility, use {@link Registry#get(NamespacedKey)} instead.
         */
        @NotNull
        @Deprecated(since = "1.21", forRemoval = true) @org.jetbrains.annotations.ApiStatus.ScheduledForRemoval(inVersion = "1.22") // Paper - will be removed via asm-utils
        static Type valueOf(@NotNull String name) {
            final NamespacedKey key = NamespacedKey.fromString(name.toLowerCase(Locale.ROOT));
            Type type = key == null ? null : Registry.VILLAGER_TYPE.get(key);
            Preconditions.checkArgument(type != null, "No villager type found with the name %s", name);
            return type;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         *
         *
         * @return 所有已知村民类型的数组。
         * <p>原文：an array of all known villager types.
         * @deprecated 请使用{@link Registry#iterator()}。
         * <p>
         * 原文：@return an array of all known villager types.
         * <p>原文：use {@link Registry#stream()}.
         */
        @NotNull
        @Deprecated(since = "1.21", forRemoval = true) @org.jetbrains.annotations.ApiStatus.ScheduledForRemoval(inVersion = "1.22") // Paper - will be removed via asm-utils
        static Type[] values() {
            return Registry.VILLAGER_TYPE.stream().toArray(Type[]::new);
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表示村民可能拥有的各种不同职业。
     * 村民根据其职业有不同的交易选项。
     * <p>原文：
     * Represents the various different Villager professions there may be.
     * Villagers have different trading options depending on their profession,
     */
    interface Profession extends OldEnum<Profession>, Keyed, net.kyori.adventure.translation.Translatable {

        // Start generate - VillagerProfession
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 盔甲匠职业。穿着黑色围裙。盔甲匠主要交易铁盔甲、锁链盔甲，有时还有钻石盔甲。
         * <p>原文：
         * Armorer profession. Wears a black apron. Armorers primarily trade for
         * iron armor, chainmail armor, and sometimes diamond armor.
         */
        Profession ARMORER = getProfession("armorer");

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 屠夫职业。穿着白色围裙。屠夫主要交易生食和熟食。
         * <p>原文：
         * Butcher profession. Wears a white apron. Butchers primarily trade for
         * raw and cooked food.
         */
        Profession BUTCHER = getProfession("butcher");

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 制图师职业。穿着白色长袍。制图师主要交易探索地图和一些纸张。
         * <p>原文：
         * Cartographer profession. Wears a white robe. Cartographers primarily
         * trade for explorer maps and some paper.
         */
        Profession CARTOGRAPHER = getProfession("cartographer");

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 牧师职业。穿着紫色长袍。牧师主要交易腐肉、金锭、红石、青金石、末影珍珠、荧石和附魔之瓶。
         * <p>原文：
         * Cleric profession. Wears a purple robe. Clerics primarily trade for
         * rotten flesh, gold ingot, redstone, lapis, ender pearl, glowstone,
         * and bottle o' enchanting.
         */
        Profession CLERIC = getProfession("cleric");

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 农民职业。穿着棕色长袍。农民主要交易与食物相关的物品。
         * <p>原文：
         * Farmer profession. Wears a brown robe. Farmers primarily trade for
         * food-related items.
         */
        Profession FARMER = getProfession("farmer");

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 渔夫职业。穿着棕色长袍。渔夫主要交易鱼类，也可能出售线和/或煤炭。
         * <p>原文：
         * Fisherman profession. Wears a brown robe. Fisherman primarily trade
         * for fish, as well as possibly selling string and/or coal.
         */
        Profession FISHERMAN = getProfession("fisherman");

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 制箭师职业。穿着棕色长袍。制箭师主要交易线、弓和箭。
         * <p>原文：
         * Fletcher profession. Wears a brown robe. Fletchers primarily trade
         * for string, bows, and arrows.
         */
        Profession FLETCHER = getProfession("fletcher");

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 皮匠职业。穿着白色围裙。皮匠主要交易皮革和皮革盔甲，以及鞍。
         * <p>原文：
         * Leatherworker profession. Wears a white apron. Leatherworkers
         * primarily trade for leather, and leather armor, as well as saddles.
         */
        Profession LEATHERWORKER = getProfession("leatherworker");

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 图书管理员职业。穿着白色长袍。图书管理员主要交易纸张、书籍和附魔书。
         * <p>原文：
         * Librarian profession. Wears a white robe. Librarians primarily trade
         * for paper, books, and enchanted books.
         */
        Profession LIBRARIAN = getProfession("librarian");

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 石匠职业。
         * <p>原文：
         * Mason profession.
         */
        Profession MASON = getProfession("mason");

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 傻瓜职业。穿着绿色围裙，不能交易。傻瓜村民不做任何事情。默认情况下他们没有任何交易。
         * <p>原文：
         * Nitwit profession. Wears a green apron, cannot trade. Nitwit
         * villagers do not do anything. They do not have any trades by default.
         */
        Profession NITWIT = getProfession("nitwit");

        Profession NONE = getProfession("none");

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 牧羊人职业。穿着棕色长袍。牧羊人主要交易羊毛物品和剪刀。
         * <p>原文：
         * Shepherd profession. Wears a brown robe. Shepherds primarily trade for
         * wool items, and shears.
         */
        Profession SHEPHERD = getProfession("shepherd");

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 工具匠职业。穿着黑色围裙。工具匠主要交易铁制和钻石工具。
         * <p>原文：
         * Toolsmith profession. Wears a black apron. Tool smiths primarily
         * trade for iron and diamond tools.
         */
        Profession TOOLSMITH = getProfession("toolsmith");

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 武器匠职业。穿着黑色围裙。武器匠主要交易铁制和钻石武器，有时是附魔的。
         * <p>原文：
         * Weaponsmith profession. Wears a black apron. Weapon smiths primarily
         * trade for iron and diamond weapons, sometimes enchanted.
         */
        Profession WEAPONSMITH = getProfession("weaponsmith");
        // End generate - VillagerProfession

        @NotNull
        private static Profession getProfession(@NotNull @KeyPattern.Value String key) {
            return Registry.VILLAGER_PROFESSION.getOrThrow(Key.key(Key.MINECRAFT_NAMESPACE, key));
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         *
         *
         * @param name 村民职业名称。
         * <p>原文：of the villager profession.
         * @return 具有给定名称的村民职业。
         * <p>原文：the villager profession with the given name.
         * @deprecated 仅为向后兼容，请使用{@link Registry#get(NamespacedKey)}代替。
         * <p>
         * 原文：@param name of the villager profession.
         * <p>原文：only for backwards compatibility, use {@link Registry#get(NamespacedKey)} instead.
         */
        @NotNull
        @Deprecated(since = "1.21", forRemoval = true) @org.jetbrains.annotations.ApiStatus.ScheduledForRemoval(inVersion = "1.22") // Paper - will be removed via asm-utils
        static Profession valueOf(@NotNull String name) {
            final NamespacedKey key = NamespacedKey.fromString(name.toLowerCase(Locale.ROOT));
            Profession profession = key == null ? null : Registry.VILLAGER_PROFESSION.get(key);
            Preconditions.checkArgument(profession != null, "No villager profession found with the name %s", name);
            return profession;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         *
         *
         * @return 所有已知村民职业的数组。
         * <p>原文：an array of all known villager professions.
         * @deprecated 请使用{@link Registry#iterator()}。
         * <p>
         * 原文：@return an array of all known villager professions.
         * <p>原文：use {@link Registry#stream()}.
         */
        @NotNull
        @Deprecated(since = "1.21", forRemoval = true) @org.jetbrains.annotations.ApiStatus.ScheduledForRemoval(inVersion = "1.22") // Paper - will be removed via asm-utils
        static Profession[] values() {
            return Registry.VILLAGER_PROFESSION.stream().toArray(Profession[]::new);
        }

        // Paper start
        @Override
        default @NotNull String translationKey() {
            return "entity.minecraft.villager." + this.getKey().getKey();
        }
        // Paper end
    }

    /**
     * Get the {@link com.destroystokyo.paper.entity.villager.Reputation reputation}
     * for a specific player by {@link UUID}.
     *
     * @param uniqueId The {@link UUID} of the player to get the reputation of.
     * @return The player's copied reputation with this villager.
     */
    @NotNull
    public com.destroystokyo.paper.entity.villager.Reputation getReputation(@NotNull UUID uniqueId);

    /**
     * Get all {@link com.destroystokyo.paper.entity.villager.Reputation reputations}
     * for all players mapped by their {@link UUID unique IDs}.
     *
     * @return All {@link com.destroystokyo.paper.entity.villager.Reputation reputations} for all players
     * in a copied map.
     */
    @NotNull
    public Map<UUID, com.destroystokyo.paper.entity.villager.Reputation> getReputations();

    /**
     * Set the {@link com.destroystokyo.paper.entity.villager.Reputation reputation}
     * for a specific player by {@link UUID}.
     *
     * @param uniqueId The {@link UUID} of the player to set the reputation of.
     * @param reputation The {@link com.destroystokyo.paper.entity.villager.Reputation reputation} to set.
     */
    public void setReputation(@NotNull UUID uniqueId, @NotNull com.destroystokyo.paper.entity.villager.Reputation reputation);

    /**
     * Set all {@link com.destroystokyo.paper.entity.villager.Reputation reputations}
     * for all players mapped by their {@link UUID unique IDs}.
     *
     * @param reputations All {@link com.destroystokyo.paper.entity.villager.Reputation reputations}
     * for all players mapped by their {@link UUID unique IDs}.
     */
    public void setReputations(@NotNull Map<UUID, com.destroystokyo.paper.entity.villager.Reputation> reputations);

    /**
     * Clear all reputations from this villager. This removes every single
     * reputation regardless of its impact and the player associated.
     */
    public void clearReputations();

    /**
     * Updates the demand for Villager offers.
     * Demand can rise and fall based on how often offers are traded.
     * They can fall when an item is not traded for a while, or rise when the item is resupplied next.
     * Demand is used to calculate the price of items in the Villager's offers.
     * <br>
     * <b>Note: Demand is stored per item and not per Villager.</b>
     */
    public void updateDemand();

    /**
     * Resets uses of all offers for the Villager. This also internally calls {@link #updateDemand()}.
     * Calling this will trigger a {@link org.bukkit.event.entity.VillagerReplenishTradeEvent} for each offer that is restocked.
     * Demand is still updated even if all events are canceled.
     */
    public void restock();
}

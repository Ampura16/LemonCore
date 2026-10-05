package org.bukkit.event.entity;

import java.util.ArrayList;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个{@link Player 玩家}死亡时触发本事件
 * <p>原文：
 * Thrown whenever a {@link Player} dies
 */
public class PlayerDeathEvent extends EntityDeathEvent {

    private int newExp = 0;
    private int newLevel = 0;
    private int newTotalExp = 0;
    private boolean showDeathMessages;
    private Component deathMessage;
    private Component deathScreenMessageOverride = null;
    private boolean doExpDrop;
    private boolean keepLevel = false;
    private boolean keepInventory = false;
    @Deprecated
    private final List<ItemStack> itemsToKeep = new ArrayList<>();

    @ApiStatus.Internal
    public PlayerDeathEvent(final @NotNull Player player, final @NotNull DamageSource damageSource, final @NotNull List<ItemStack> drops, final int droppedExp, final @Nullable Component deathMessage, final boolean showDeathMessages) {
        this(player, damageSource, drops, droppedExp, 0, deathMessage, showDeathMessages);
    }

    @ApiStatus.Internal
    public PlayerDeathEvent(final @NotNull Player player, final @NotNull DamageSource damageSource, final @NotNull List<ItemStack> drops, final int droppedExp, final int newExp, final @Nullable Component deathMessage, final boolean showDeathMessages) {
        this(player, damageSource, drops, droppedExp, newExp, 0, 0, deathMessage, showDeathMessages);
    }

    @ApiStatus.Internal
    public PlayerDeathEvent(final @NotNull Player player, final @NotNull DamageSource damageSource, final @NotNull List<ItemStack> drops, final int droppedExp, final int newExp, final int newTotalExp, final int newLevel, final @Nullable Component deathMessage, final boolean showDeathMessages) {
        this(player, damageSource, drops, droppedExp, newExp, newTotalExp, newLevel, deathMessage, showDeathMessages, true);
    }

    @ApiStatus.Internal
    public PlayerDeathEvent(final @NotNull Player player, final @NotNull DamageSource damageSource, final @NotNull List<ItemStack> drops, final int droppedExp, final int newExp, final int newTotalExp, final int newLevel, final @Nullable Component deathMessage, final boolean showDeathMessages, final boolean doExpDrop) {
        super(player, damageSource, drops, droppedExp);
        this.newExp = newExp;
        this.newTotalExp = newTotalExp;
        this.newLevel = newLevel;
        this.deathMessage = deathMessage;
        this.showDeathMessages = showDeathMessages;
        this.doExpDrop = doExpDrop;
    }

    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    public PlayerDeathEvent(@NotNull final Player player, @NotNull DamageSource damageSource, @NotNull final List<ItemStack> drops, final int droppedExp, @Nullable final String deathMessage) {
        this(player, damageSource, drops, droppedExp, 0, deathMessage);
    }

    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    public PlayerDeathEvent(@NotNull final Player player, @NotNull DamageSource damageSource, @NotNull final List<ItemStack> drops, final int droppedExp, final int newExp, @Nullable final String deathMessage) {
        this(player, damageSource, drops, droppedExp, newExp, 0, 0, deathMessage);
    }

    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    public PlayerDeathEvent(@NotNull final Player player, @NotNull DamageSource damageSource, @NotNull final List<ItemStack> drops, final int droppedExp, final int newExp, final int newTotalExp, final int newLevel, @Nullable final String deathMessage) {
        this(player, damageSource, drops, droppedExp, newExp, newTotalExp, newLevel, deathMessage, true);
    }

    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    public PlayerDeathEvent(@NotNull final Player player, final @NotNull DamageSource damageSource, @NotNull final List<ItemStack> drops, final int droppedExp, final int newExp, final int newTotalExp, final int newLevel, @Nullable final String deathMessage, boolean doExpDrop) {
        super(player, damageSource, drops, droppedExp);
        this.newExp = newExp;
        this.newTotalExp = newTotalExp;
        this.newLevel = newLevel;
        this.showDeathMessages = true;
        this.deathMessage = LegacyComponentSerializer.legacySection().deserializeOrNull(deathMessage);
        this.doExpDrop = doExpDrop;
    }

    @NotNull
    @Override
    public Player getEntity() {
        return (Player) this.entity;
    }

    /**
     * Get whether the death message should be shown.
     * By default, this is determined by {@link org.bukkit.GameRules#SHOW_DEATH_MESSAGES}.
     *
     * @return whether the death message should be shown
     * @see #deathMessage()
     * @see #deathScreenMessageOverride()
     */
    public boolean getShowDeathMessages() {
        return showDeathMessages;
    }

    /**
     * Set whether the death message should be shown.
     * By default, this is determined by {@link org.bukkit.GameRules#SHOW_DEATH_MESSAGES}.
     *
     * @param displayDeathMessage whether the death message should be shown
     * @see #deathMessage()
     * @see #deathScreenMessageOverride()
     */
    public void setShowDeathMessages(boolean displayDeathMessage) {
        this.showDeathMessages = displayDeathMessage;
    }

    /**
     * Clarity method for getting the player. Not really needed except
     * for reasons of clarity.
     *
     * @return Player who is involved in this event
     */
    public @NotNull Player getPlayer() {
        return this.getEntity();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家重生时有多少经验.
     * <p>
     * 不包含多少经验会掉落，要做到这点请看{@link #getDroppedExp()}.
     * <p>原文：
     * Gets how much EXP the Player should have at respawn.
     * <p>
     * This does not indicate how much EXP should be dropped, please see
     * {@link #getDroppedExp()} for that.
     *
     * @return 重生后的玩家有多少经验
     * <p>原文：New EXP of the respawned player
     */
    public int getNewExp() {
        return this.newExp;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家在重生时应该拥有多少经验值。
     * <p>
     * 这并不表示应该掉落多少经验值，请参见 {@link #setDroppedExp(int)}。
     * <p>原文：
     * Sets how much EXP the Player should have at respawn.
     * <p>
     * This does not indicate how much EXP should be dropped, please see
     * {@link #setDroppedExp(int)} for that.
     *
     * @param exp 重生玩家的新经验值
     * <p>原文：New EXP of the respawned player
     */
    public void setNewExp(int exp) {
        this.newExp = exp;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家在重生时应该拥有的等级。
     * <p>原文：
     * Gets the Level the Player should have at respawn.
     *
     * @return 重生玩家的新等级
     * <p>原文：New Level of the respawned player
     */
    public int getNewLevel() {
        return this.newLevel;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家在重生时应该拥有的等级。
     * <p>原文：
     * Sets the Level the Player should have at respawn.
     *
     * @param level 重生玩家的新等级
     * <p>原文：New Level of the respawned player
     */
    public void setNewLevel(int level) {
        this.newLevel = level;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家在重生时应该拥有的总经验值。
     * <p>原文：
     * Gets the Total EXP the Player should have at respawn.
     *
     * @return 重生玩家的新总经验值
     * <p>原文：New Total EXP of the respawned player
     */
    public int getNewTotalExp() {
        return this.newTotalExp;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家在重生时应该拥有的总经验值。
     * <p>原文：
     * Sets the Total EXP the Player should have at respawn.
     *
     * @param totalExp 重生玩家的新总经验值
     * <p>原文：New Total EXP of the respawned player
     */
    public void setNewTotalExp(int totalExp) {
        this.newTotalExp = totalExp;
    }

    /**
     * Set the death message that will appear to everyone on the server.
     *
     * @param deathMessage Component message to appear to other players on the server.
     */
    public void deathMessage(final @Nullable Component deathMessage) {
        this.deathMessage = deathMessage;
    }

    /**
     * Get the death message that will appear to everyone on the server.
     *
     * @return Component message to appear to other players on the server.
     */
    public @Nullable Component deathMessage() {
        return this.deathMessage;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置广播给所有人的死亡提示.
     * <p>原文：
     * Set the death message that will appear to everyone on the server.
     *
     * @param deathMessage 死亡提示
     * <p>原文：message to appear to other players on the server.
     * @deprecated in favour of {@link #deathMessage(Component)}
     */
    @Deprecated
    public void setDeathMessage(@Nullable String deathMessage) {
        this.deathMessage = LegacyComponentSerializer.legacySection().deserializeOrNull(deathMessage);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取广播给所有人的死亡提示.
     * <p>原文：
     * Get the death message that will appear to everyone on the server.
     *
     * @return 死亡提示
     * <p>原文：Message to appear to other players on the server.
     * @deprecated in favour of {@link #deathMessage()}
     */
    @Nullable
    @Deprecated
    public String getDeathMessage() {
        return LegacyComponentSerializer.legacySection().serializeOrNull(this.deathMessage);
    }

    /**
     * Overrides the death message that will appear on the death screen of the dying player.
     * By default, this is null.
     * <p>
     * If set to null, death screen message will be same as {@code deathMessage()}.
     * <p>
     * If the message exceeds 256 characters it will be truncated.
     *
     * @param deathScreenMessageOverride Message to appear on the death screen to the dying player.
     */
    public void deathScreenMessageOverride(@Nullable Component deathScreenMessageOverride) {
        this.deathScreenMessageOverride = deathScreenMessageOverride;
    }

    /**
     * Get the death message override that will appear on the death screen of the dying player.
     * By default, this is null.
     * <p>
     * If set to null, death screen message will be same as {@code deathMessage()}.
     * <p>
     * @return Message to appear on the death screen to the dying player.
     */
    public @Nullable Component deathScreenMessageOverride() {
        return this.deathScreenMessageOverride;
    }

    /**
     * @return should experience be dropped from this death
     */
    public boolean shouldDropExperience() {
        return this.doExpDrop;
    }

    /**
     * @param doExpDrop sets if experience should be dropped from this death
     */
    public void setShouldDropExperience(boolean doExpDrop) {
        this.doExpDrop = doExpDrop;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家在重生时是否应该保留所有经验值。
     * <p>
     * 此标志会覆盖其他经验值设置。
     * <p>原文：
     * Gets if the Player should keep all EXP at respawn.
     * <p>
     * This flag overrides other EXP settings
     *
     * @return 如果玩家应该保留所有死亡前的经验值，则返回 true
     * <p>原文：{@code true} if Player should keep all pre-death exp
     */
    public boolean getKeepLevel() {
        return this.keepLevel;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家在重生时是否应该保留所有经验值。
     * <p>
     * 这会覆盖所有其他经验值设置。
     * <p>
     * <b>这并不会阻止经验值掉落。应使用 {@link #setDroppedExp(int)} 来阻止经验值掉落。</b>
     * <p>原文：
     * Sets if the Player should keep all EXP at respawn.
     * <p>
     * This overrides all other EXP settings
     * <p>
     * <b>This doesn't prevent the EXP from dropping.
     * {@link #setDroppedExp(int)} should be used stop the
     * EXP from dropping.</b>
     *
     * @param keepLevel 如果为 true，则保留所有当前经验值
     * <p>原文：{@code true} to keep all current value levels
     */
    public void setKeepLevel(boolean keepLevel) {
        this.keepLevel = keepLevel;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家在死亡时是否保留物品栏。
     * <p>
     * <b>这并不会阻止物品掉落。应使用 {@code getDrops().clear()}来阻止物品掉落。</b>
     * <p>原文：
     * Sets if the Player keeps inventory on death.
     * <p>
     * <b>This doesn't prevent the items from dropping.
     * {@code getDrops().clear()} should be used stop the
     * items from dropping.</b>
     *
     * @param keepInventory 如果为 true，则保留物品栏
     * <p>原文：{@code true} to keep the inventory
     */
    public void setKeepInventory(boolean keepInventory) {
        this.keepInventory = keepInventory;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家在死亡时是否保留物品栏。
     * <p>原文：
     * Gets if the Player keeps inventory on death.
     *
     * @return 如果玩家在死亡时保留物品栏，则返回 true
     * <p>原文：{@code true} if the player keeps inventory on death
     */
    public boolean getKeepInventory() {
        return this.keepInventory;
    }

    /**
     * A mutable collection to add items that the player should retain in their inventory on death (Similar to KeepInventory game rule)
     * <br>
     * You <b>MUST</b> remove the item from the .getDrops() collection too or it will duplicate!
     * <pre>{@code
     * private static final NamespacedKey SOULBOUND_KEY = new NamespacedKey("testplugin", "soulbound");
     *
     * @EventHandler(ignoreCancelled = true)
     * public void onPlayerDeath(PlayerDeathEvent event) {
     *     for (Iterator<ItemStack> iterator = event.getDrops().iterator(); iterator.hasNext(); ) {
     *         ItemStack drop = iterator.next();
     *         if (drop.getPersistentDataContainer().getOrDefault(SOULBOUND_KEY, PersistentDataType.BOOLEAN, false)) {
     *             iterator.remove();
     *             event.getItemsToKeep().add(drop);
     *         }
     *     }
     * }
     * }</pre>
     * <p>
     * Adding an item to this list that the player did not previously have will give them the item on death.
     * An example case could be a "Note" that "You died at X/Y/Z coordinates"
     *
     * @return The list to hold items to keep
     */
    @NotNull
    public List<ItemStack> getItemsToKeep() {
        return this.itemsToKeep;
    }
}

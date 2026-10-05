package org.bukkit.block;

import org.bukkit.DyeColor;
import org.bukkit.block.sign.Side;
import org.bukkit.block.sign.SignSide;
import org.bukkit.entity.Player;
import org.bukkit.material.Colorable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表告示牌或者墙上告示牌.
 * <p>原文：
 * Represents a captured state of either a SignPost or a WallSign.
 */
public interface Sign extends TileState, Colorable {
    // Paper start
    /**
     * Gets all the lines of text currently on the {@link Side#FRONT} of this sign.
     *
     * @return List of components containing each line of text
     * @deprecated A sign may have multiple writable sides now. Use {@link Sign#getSide(Side)} and {@link SignSide#lines()}.
     */
    @NotNull
    @Deprecated
    public java.util.List<net.kyori.adventure.text.Component> lines();

    /**
     * Gets the line of text at the specified index on the {@link Side#FRONT}.
     * <p>
     * For example, getLine(0) will return the first line of text.
     *
     * @param index Line number to get the text from, starting at 0
     * @throws IndexOutOfBoundsException Thrown when the line does not exist
     * @return Text on the given line
     * @deprecated A sign may have multiple writable sides now. Use {@link #getSide(Side)} and {@link SignSide#line(int)}.
     */
    @NotNull
    @Deprecated
    public net.kyori.adventure.text.Component line(int index) throws IndexOutOfBoundsException;

    /**
     * Sets the line of text at the specified index on the {@link Side#FRONT}.
     * <p>
     * For example, setLine(0, "Line One") will set the first line of text to
     * "Line One".
     *
     * @param index Line number to set the text at, starting from 0
     * @param line New text to set at the specified index
     * @throws IndexOutOfBoundsException If the index is out of the range 0..3
     * @deprecated A sign may have multiple writable sides now. Use {@link #getSide(Side)} and {@link SignSide#line(int, net.kyori.adventure.text.Component)}.
     */
    @Deprecated
    public void line(int index, net.kyori.adventure.text.@NotNull Component line) throws IndexOutOfBoundsException;
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个告示牌前面板上的文本.
     * <p>原文：
     * Gets all the lines of text currently on the {@link Side#FRONT} of this sign.
     *
     * @return 每行文本字符串数组
     * <p>原文：Array of Strings containing each line of text
     * @deprecated 告示牌现在多面可写. 请使用 {@link Sign#getSide(Side)} 和 {@link SignSide#getLines()}.
     * <p>原文：A sign may have multiple writable sides now. Use {@link Sign#getSide(Side)} and {@link SignSide#lines()}.
     */
    @Deprecated(since = "1.20")
    @NotNull
    public String[] getLines();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定行的文本.
     * <p>
     * 例如, getLine(0)将返回告示牌前面板第一行的文本.
     * <p>原文：
     * Gets the line of text at the specified index.
     * <p>
     * For example, getLine(0) will return the first line of text on the {@link Side#FRONT}.
     *
     * @param index 要获取的文本的行数,从0开始
     * <p>原文：Line number to get the text from, starting at 0
     * @return 这个行的文本
     * <p>原文：Text on the given line
     * @throws IndexOutOfBoundsException 当此行不存在(尚未设置)时抛出
     * <p>原文：Thrown when the line does not exist
     * @deprecated 告示牌现在多面可写. 请使用 {@link Sign#getSide(Side)} 和 {@link SignSide#getLines()}.
     * <p>原文：A sign may have multiple writable sides now. Use {@link #getSide(Side)} and {@link SignSide#line(int)}.
     */
    @Deprecated(since = "1.20")
    @NotNull
    public String getLine(int index) throws IndexOutOfBoundsException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置指定索引行的文本.
     * <p>
     * 例如,setLine(0, "行1") 将设置告示牌第一行的文本为"行1".
     * <p>
     * 请注意:从0开始数行.
     * <p>原文：
     * Sets the line of text at the specified index.
     * <p>
     * For example, setLine(0, "Line One") will set the first line of text to
     * "Line One".
     *
     * @param index 要设置的文本的行数,从0开始
     * <p>原文：Line number to set the text at, starting from 0
     * @param line 新的指定的文本索引
     * <p>原文：New text to set at the specified index
     * @throws IndexOutOfBoundsException 如果索引超出了0-3的范围
     * <p>原文：If the index is out of the range 0..3
     * @deprecated 告示牌现在多面可写. 请使用 {@link Sign#getSide(Side)} 和 {@link SignSide#getLines()}.
     * <p>原文：A sign may have multiple writable sides now. Use {@link #getSide(Side)} and {@link SignSide#line(int, net.kyori.adventure.text.Component)}.
     */
    @Deprecated(since = "1.20")
    public void setLine(int index, @NotNull String line) throws IndexOutOfBoundsException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测此告示是否可被玩家编辑.
     * <p>原文：
     * Marks whether this sign can be edited by players.
     *
     * @return 告示牌目前是否可被编辑
     * <p>原文：if this sign is currently editable
     * @deprecated 请使用 {@link #isWaxed()}
     * <p>原文：use {@link #isWaxed()} instead
     */
    @Deprecated(since = "1.20.1")
    public boolean isEditable();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此告示是否可被玩家编辑.
     * <p>原文：
     * Marks whether this sign can be edited by players.
     *
     * @param editable 告示牌目前是否可被编辑
     * <p>原文：if this sign is currently editable
     * @deprecated 请使用 {@link #isWaxed()}
     * <p>原文：use {@link #setWaxed(boolean)} instead
     */
    @Deprecated(since = "1.20.1")
    public void setEditable(boolean editable);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此告示牌是否已被涂蜡. 如果告示牌已被涂蜡, 则玩家无法编辑.
     * <p>原文：
     * Gets whether or not this sign has been waxed. If a sign has been waxed, it
     * cannot be edited by a player.
     *
     * @return 此告示牌是否已被涂蜡
     * <p>原文：if this sign is waxed
     */
    public boolean isWaxed();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此告示牌是否已被涂蜡. 如果告示牌已被涂蜡, 则玩家无法编辑.
     * <p>原文：
     * Sets whether or not this sign has been waxed. If a sign has been waxed, it
     * cannot be edited by a player.
     *
     * @param waxed 此告示牌是否已被涂蜡
     * <p>原文：if this sign is waxed
     */
    public void setWaxed(boolean waxed);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此告示牌是否有发光文本. 仅影响 {@link Side#FRONT}.
     * <p>原文：
     * Gets whether this sign has glowing text. Only affects the {@link Side#FRONT}.
     *
     * @return 此告示牌是否有发光文本
     * <p>原文：if this sign has glowing text
     * @deprecated 告示牌现在可以有多个可写面. 请使用 {@link #getSide(Side)} 和 {@link SignSide#isGlowingText()}.
     * <p>原文：A sign may have multiple writable sides now. Use {@link #getSide(Side)} and {@link SignSide#isGlowingText()}.
     */
    @Deprecated(since = "1.20")
    public boolean isGlowingText();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此告示牌是否有发光文本. 仅影响 {@link Side#FRONT}.
     * <p>原文：
     * Sets whether this sign has glowing text. Only affects the {@link Side#FRONT}.
     *
     * @param glowing 此告示牌是否有发光文本
     * <p>原文：if this sign has glowing text
     * @deprecated 告示牌现在可以有多个可写面. 请使用 {@link #getSide(Side)} 和 {@link SignSide#setGlowingText(boolean)}.
     * <p>原文：A sign may have multiple writable sides now. Use {@link #getSide(Side)} and {@link SignSide#setGlowingText(boolean)}.
     */
    @Deprecated(since = "1.20")
    public void setGlowingText(boolean glowing);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * {@inheritDoc}
     *
     * @deprecated 告示牌现在可以有多个可写面. 请使用 {@link #getSide(Side)} 和 {@link SignSide#getColor()}.
     * <p>原文：A sign may have multiple writable sides now. Use {@link #getSide(Side)} and {@link SignSide#getColor()}.
     */
    @NotNull
    @Override
    @Deprecated(since = "1.20")
    public DyeColor getColor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * {@inheritDoc}
     *
     * @deprecated 告示牌现在可以有多个可写面. 请使用 {@link #getSide(Side)} 和 {@link SignSide#setColor(org.bukkit.DyeColor)}.
     * <p>原文：A sign may have multiple writable sides now. Use {@link #getSide(Side)} and {@link SignSide#setColor(org.bukkit.DyeColor)}.
     */
    @Override
    @Deprecated(since = "1.20")
    public void setColor(@NotNull DyeColor color);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回告示牌的指定面.
     * <p>原文：
     * Return the side of the sign.
     *
     * @param side 告示牌的面
     * <p>原文：the side of the sign
     * @return 告示牌的选定面
     * <p>原文：the selected side of the sign
     */
    @NotNull
    public SignSide getSide(@NotNull Side side);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取给定玩家当前所站的告示牌面.
     * <p>原文：
     * Gets the side of this sign the given player is currently standing on.
     *
     * @param player 玩家
     * <p>原文：the player
     * @return 玩家所站的告示牌面
     * <p>原文：the side the player is standing on
     */
    @NotNull
    public SignSide getTargetSide(@NotNull Player player);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前被允许编辑此告示牌的玩家. <br>
     * 如果此值不为 null, 则其他玩家的编辑将被拒绝.
     * <p>原文：
     * Gets the player that is currently allowed to edit this sign. <br>
     * Edits from other players will be rejected if this value is not null.
     * <br><br>You should prefer {@link #getAllowedEditorUniqueId()} if you don't
     * need the player instance as this method will fetch the player from UUID.
     *
     * @return 被允许编辑此告示牌的玩家, 或 null
     * <p>原文：the player allowed to edit this sign, or null
     */
    @Nullable
    public Player getAllowedEditor();
    // Paper start - More Sign Block API
    /**
     * Gets the allowed editor's UUID.
     * <br>Edits from other players will be rejected if this value is not null.
     *
     * @return the allowed editor's UUID, or null
     */
    @Nullable java.util.UUID getAllowedEditorUniqueId();

    /**
     * Sets the allowed editor's UUID.
     * <br><br><b>Note:</b> the server sets the UUID back to null if the player can't
     * interact with the sign (is either offline or outside the allowed interaction range).
     *
     * @param uuid the allowed editor's UUID
     */
    void setAllowedEditorUniqueId(@Nullable java.util.UUID uuid);

    /**
     * Compute the side facing the specified entity.
     *
     * @param entity the entity
     * @return the side it is facing
     */
    default @NotNull Side getInteractableSideFor(org.bukkit.entity.@NotNull Entity entity) {
        return this.getInteractableSideFor(entity.getLocation());
    }

    /**
     * Compute the side facing the specific position.
     *
     * @param position the position
     * @return the side the position is facing
     */
    default @NotNull Side getInteractableSideFor(io.papermc.paper.math.@NotNull Position position) {
        return this.getInteractableSideFor(position.x(), position.z());
    }

    /**
     * Compute the side facing the specific x and z coordinates.
     *
     * @param x the x coord
     * @param z the z coord
     * @return the side the coordinates are facing
     */
    @NotNull Side getInteractableSideFor(double x, double z);
    // Paper end - More Sign Block API
}

package io.papermc.paper.event.world;

import io.papermc.paper.math.Position;
import java.util.Collections;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.world.WorldEvent;
import org.bukkit.generator.structure.Structure;
import org.bukkit.generator.structure.StructureType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.UnmodifiableView;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 在定位一组已配置的结构<b>之前</b>调用。发生在以下情况：
 * <ul>
 * <li>使用 /locate 命令。<br></li>
 * <li>使用末影之眼。</li>
 * <li>激活探险家地图/藏宝图。</li>
 * <li>海豚游向宝藏位置。</li>
 * <li>与村民交易获得地图。</li>
 * <li>调用 {@link World#locateNearestStructure(Location, StructureType, int, boolean)}。</li>
 * <li>调用 {@link World#locateNearestStructure(Location, Structure, int, boolean)}。</li>
 * </ul>
 * <p>原文：
 * Called <b>before</b> a set of configured structures is located.
 * This happens when:
 * <ul>
 *     <li>The /locate command is used.<br></li>
 *     <li>An Eye of Ender is used.</li>
 *     <li>An Explorer/Treasure Map is activated.</li>
 *     <li>A dolphin swims to a treasure location.</li>
 *     <li>A trade is done with a villager for a map.</li>
 *     <li>{@link World#locateNearestStructure(Location, StructureType, int, boolean)} is invoked.</li>
 *     <li>{@link World#locateNearestStructure(Location, Structure, int, boolean)} is invoked.</li>
 * </ul>
 */
@NullMarked
public class StructuresLocateEvent extends WorldEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Location origin;
    private @Nullable Result result;
    private List<Structure> structures;
    private int radius;
    private boolean findUnexplored;

    private boolean cancelled;

    @ApiStatus.Internal
    public StructuresLocateEvent(final World world, final Location origin, final List<Structure> structures, final int radius, final boolean findUnexplored) {
        super(world);
        this.origin = origin;
        this.structures = structures;
        this.radius = radius;
        this.findUnexplored = findUnexplored;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取进行搜索的起点 {@link Location}。
     * <p>原文：
     * Gets the {@link Location} from which the search is to be conducted.
     *
     * @return 搜索开始的 {@link Location}
     * <p>原文：{@link Location} where search begins
     */
    public Location getOrigin() {
        return this.origin.clone();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取设为结果的 {@link Location} 和 {@link Structure}（如果已定义）。
     * <p>
     * 如果未通过 {@link StructuresLocateEvent#setResult(Result)} 设置，则返回 {@code null}。由于该事件在搜索完成<i>之前</i>触发，此时实际结果尚不可知。
     * <p>原文：
     * Gets the {@link Location} and {@link Structure} set as the result, if it was defined.
     * <p>
     * Returns {@code null} if it has not been set by {@link StructuresLocateEvent#setResult(Result)}.
     * Since this event fires <i>before</i> the search is done, the actual result is unknown at this point.
     *
     * @return 结果位置和结构（如果已设置）；未设置则为 {@code null}。
     * <p>原文：The result location and structure, if it has been set. {@code null} if it has not.
     * @see World#locateNearestStructure(Location, StructureType, int, boolean)
     */
    public @Nullable Result getResult() {
        return this.result;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置结果 {@link Location} 和 {@link Structure}。这会跳过搜索，并将此处传入的结果对象用作结果。
     * <p>原文：
     * Sets the result {@link Location} and {@link Structure}. This causes the search to be
     * skipped, and the result object passed here to be used as the result.
     *
     * @param result 搜索的 {@link Location} 和 {@link Structure}。
     * <p>原文：the {@link Location} and {@link Structure} of the search.
     */
    public void setResult(final @Nullable Result result) {
        this.result = result;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取作为搜索有效目标的结构的不可修改列表。
     * <p>原文：
     * Gets an unmodifiable list of Structures that are valid targets for the search.
     *
     * @return 结构的不可修改列表
     * <p>原文：an unmodifiable list of Structures
     */
    public @UnmodifiableView List<Structure> getStructures() {
        return Collections.unmodifiableList(this.structures);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置作为搜索有效目标的结构列表。
     * <p>原文：
     * Sets the list of Structures that are valid targets for the search.
     *
     * @param structures 结构目标列表
     * <p>原文：a list of Structures targets
     */
    public void setStructures(final List<Structure> structures) {
        this.structures = structures;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取尝试定位结构的搜索半径。
     * <p>
     * 在结构搜索期间不一定总会遵守该半径！
     * <p>原文：
     * Gets the search radius in which to attempt locating the structure.
     * <p>
     * This radius may not always be obeyed during the structure search!
     *
     * @return 搜索半径（以区块计）
     * <p>原文：the search radius (in chunks)
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置尝试定位结构的搜索半径。
     * <p>
     * 在结构搜索期间不一定总会遵守该半径！
     * <p>原文：
     * Sets the search radius in which to attempt locating the structure.
     * <p>
     * This radius may not always be obeyed during the structure search!
     *
     * @param radius 搜索半径（以区块计）
     * <p>原文：the search radius (in chunks)
     */
    public void setRadius(final int radius) {
        this.radius = radius;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取是否只搜索未探索的结构。
     * <p>
     * 与搜索半径一样，该值不一定总会被遵守。
     * <p>原文：
     * Gets whether to search exclusively for unexplored structures.
     * <p>
     * As with the search radius, this value is not always obeyed.
     *
     * @return 是否只搜索未探索的结构。
     * <p>原文：Whether to search for only unexplored structures.
     */
    public boolean shouldFindUnexplored() {
        return this.findUnexplored;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置是否只搜索未探索的结构。
     * <p>
     * 与搜索半径一样，该值不一定总会被遵守。
     * <p>原文：
     * Sets whether to search exclusively for unexplored structures.
     * <p>
     * As with the search radius, this value is not always obeyed.
     *
     * @param findUnexplored 是否只搜索未探索过的结构。
     * <p>原文：Whether to search for only unexplored structures.
     */
    public void setFindUnexplored(final boolean findUnexplored) {
        this.findUnexplored = findUnexplored;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link StructuresLocateEvent} 的结果。
     * <p>原文：
     * Result for {@link StructuresLocateEvent}.
     */
    public record Result(Position pos, Structure structure) {

        @Deprecated(forRemoval = true)
        public Location position() {
            //noinspection DataFlowIssue
            return this.pos.toLocation(null);
        }
    }
}

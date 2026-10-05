package org.bukkit.event.block;

import com.google.common.base.Preconditions;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 音符盒播放音符的事件[玩家点击/红石触发]
 * <p>原文：
 * Called when a note block is being played through player interaction or a
 * redstone current.
 */
public class NotePlayEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private Instrument instrument;
    private Note note;

    private boolean cancelled;

    @ApiStatus.Internal
    public NotePlayEvent(@NotNull Block block, @NotNull Instrument instrument, @NotNull Note note) {
        super(block);
        this.instrument = instrument;
        this.note = note;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取要播放的音符的乐器({@link Instrument}).
     * <p>原文：
     * Gets the {@link Instrument} to be used.
     *
     * @return 乐器
     * <p>原文：the Instrument
     */
    @NotNull
    public Instrument getInstrument() {
        return this.instrument;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取要播放的音符({@link Note}).
     * <p>原文：
     * Gets the {@link Note} to be played.
     *
     * @return 音符
     * <p>原文：the Note
     */
    @NotNull
    public Note getNote() {
        return this.note;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置播放音符用的乐器({@link Instrument}).
     * <p>原文：
     * Overrides the {@link Instrument} to be used.
     * <p>
     * Only works when the note block isn't under a player head.
     * For this specific case the 'note_block_sound' property of the
     * player head state takes the priority.
     *
     * @param instrument 乐器，没有为null.
     * <p>原文：the Instrument.
     */
    public void setInstrument(@NotNull Instrument instrument) {
        Preconditions.checkArgument(instrument != null, "instrument cannot be null");
        this.instrument = instrument;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置要播放的音符({@link Note}).
     * <p>原文：
     * Overrides the {@link Note} to be played.
     *
     * @param note 音符，没有为null
     * <p>原文：the Note.
     */
    public void setNote(@NotNull Note note) {
        Preconditions.checkArgument(note != null, "note cannot be null");
        this.note = note;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}

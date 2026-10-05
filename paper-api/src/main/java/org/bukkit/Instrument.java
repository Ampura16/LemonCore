package org.bukkit;

import com.google.common.collect.Maps;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表音符盒可以演奏的乐器类型.
 */
public enum Instrument {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 钢琴是音符盒的标准乐器.
     * <p>原文：
     * Piano (Harp) is the standard instrument for a note block.
     */
    PIANO(0x0, Sound.BLOCK_NOTE_BLOCK_HARP),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当音符盒在一个石类方块的顶部时通常会以低音鼓的形式演奏.
     * <p>原文：
     * Bass drum is normally played when a note block is on top of a
     * stone-like block.
     */
    BASS_DRUM(0x1, Sound.BLOCK_NOTE_BLOCK_BASEDRUM),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当音符盒在一个沙类方块的顶部时通常会以小军鼓的形式演奏.
     * <p>原文：
     * Snare drum is normally played when a note block is on top of a sandy
     * block.
     */
    SNARE_DRUM(0x2, Sound.BLOCK_NOTE_BLOCK_SNARE),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当音符盒在一个玻璃方块的顶部时通常会以鼓槌的形式演奏.
     * <p>原文：
     * Sticks (Hat) are normally played when a note block is on top of a glass
     * block.
     */
    STICKS(0x3, Sound.BLOCK_NOTE_BLOCK_HAT),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当音符盒在一个木类方块的顶部时通常会以低音吉他的形式演奏.
     * <p>原文：
     * Bass guitar is normally played when a note block is on top of a wooden
     * block.
     */
    BASS_GUITAR(0x4, Sound.BLOCK_NOTE_BLOCK_BASS),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当音符盒在一个黏土方块的顶部时通常会以长笛的形式演奏.
     * <p>原文：
     * Flute is normally played when a note block is on top of a clay block.
     */
    FLUTE(0x5, Sound.BLOCK_NOTE_BLOCK_FLUTE),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当音符盒在一个金块的顶部时通常会以钟的形式演奏.
     * <p>原文：
     * Bell is normally played when a note block is on top of a gold block.
     */
    BELL(0x6, Sound.BLOCK_NOTE_BLOCK_BELL),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当音符盒在一个羊毛方块的顶部时通常会以吉他的形式演奏.
     * <p>原文：
     * Guitar is normally played when a note block is on top of a woolen block.
     */
    GUITAR(0x7, Sound.BLOCK_NOTE_BLOCK_GUITAR),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当音符盒在一个浮冰的顶部时通常会以管钟的形式演奏.
     * <p>原文：
     * Chime is normally played when a note block is on top of a packed ice
     * block.
     */
    CHIME(0x8, Sound.BLOCK_NOTE_BLOCK_CHIME),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当音符盒在一个骨块的顶部时通常会以木琴的形式演奏.
     * <p>原文：
     * Xylophone is normally played when a note block is on top of a bone block.
     */
    XYLOPHONE(0x9, Sound.BLOCK_NOTE_BLOCK_XYLOPHONE),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当音符盒在一个铁块的顶部时通常会以铁木琴的形式演奏.
     * <p>原文：
     * Iron Xylophone is normally played when a note block is on top of an iron block.
     */
    IRON_XYLOPHONE(0xA, Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当音符盒在一个灵魂沙的顶部时通常会以牛铃的形式演奏.
     * <p>原文：
     * Cow Bell is normally played when a note block is on top of a soul sand block.
     */
    COW_BELL(0xB, Sound.BLOCK_NOTE_BLOCK_COW_BELL),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当音符盒在一个南瓜的顶部时通常会以迪吉里杜管的形式演奏.
     * <p>原文：
     * Didgeridoo is normally played when a note block is on top of a pumpkin block.
     */
    DIDGERIDOO(0xC, Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当音符盒在一个绿宝石块的顶部时通常会以比特音乐的形式演奏.
     * <p>原文：
     * Bit is normally played when a note block is on top of an emerald block.
     */
    BIT(0xD, Sound.BLOCK_NOTE_BLOCK_BIT),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当音符盒在一个干草块的顶部时通常会以班卓琴的形式演奏.
     * <p>原文：
     * Banjo is normally played when a note block is on top of a hay block.
     */
    BANJO(0xE, Sound.BLOCK_NOTE_BLOCK_BANJO),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当音符盒在一个荧石块的顶部时通常会以电子音的形式演奏.
     * <p>原文：
     * Pling is normally played when a note block is on top of a glowstone block.
     */
    PLING(0xF, Sound.BLOCK_NOTE_BLOCK_PLING),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当僵尸头颅在音符盒的顶部时通常会以僵尸音效的形式演奏.
     * <p>原文：
     * Zombie is normally played when a Zombie Head is on top of the note block.
     */
    ZOMBIE(0x10, Sound.BLOCK_NOTE_BLOCK_IMITATE_ZOMBIE), // Paper
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当骷髅头颅在音符盒的顶部时通常会以骷髅音效的形式演奏.
     * <p>原文：
     * Skeleton is normally played when a Skeleton Head is on top of the note block.
     */
    SKELETON(0x11, Sound.BLOCK_NOTE_BLOCK_IMITATE_SKELETON), // Paper
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当苦力怕头颅在音符盒的顶部时通常会以苦力怕音效的形式演奏.
     * <p>原文：
     * Creeper is normally played when a Creeper Head is on top of the note block.
     */
    CREEPER(0x12, Sound.BLOCK_NOTE_BLOCK_IMITATE_CREEPER), // Paper
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当末影龙头颅在音符盒的顶部时通常会以末影龙音效的形式演奏.
     * <p>原文：
     * Dragon is normally played when a Dragon Head is on top of the note block.
     */
    DRAGON(0x13, Sound.BLOCK_NOTE_BLOCK_IMITATE_ENDER_DRAGON), // Paper
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当凋灵骷髅头颅在音符盒的顶部时通常会以凋灵骷髅音效的形式演奏.
     * <p>原文：
     * Wither Skeleton is normally played when a Wither Skeleton Head is on top of the note block.
     */
    WITHER_SKELETON(0x14, Sound.BLOCK_NOTE_BLOCK_IMITATE_WITHER_SKELETON), // Paper
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当猪灵头颅在音符盒的顶部时通常会以猪灵音效的形式演奏.
     * <p>原文：
     * Piglin is normally played when a Piglin Head is on top of the note block.
     */
    PIGLIN(0x15, Sound.BLOCK_NOTE_BLOCK_IMITATE_PIGLIN), // Paper
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当具有所需数据的玩家头颅在音符盒的顶部时通常会以自定义音效的形式演奏.
     * <p>原文：
     * Custom Sound is normally played when a Player Head with the required data is on top of the note block.
     */
    CUSTOM_HEAD(0x16, null); // Paper

    private final byte type;
    private final Sound sound;
    private static final Map<Byte, Instrument> BY_DATA = Maps.newHashMap();

    // Paper - remove ctor (the server still uses the byte magic value)

    private Instrument(final int type, final Sound sound) {
        this.type = (byte) type;
        this.sound = sound;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取与此乐器关联的音效.
     * <br>
     * 对于 {@link Instrument#CUSTOM_HEAD} 将返回 null.
     * <p>原文：
     * Gets the sound associated with this instrument. <br>
     * Will be null for {@link Instrument#CUSTOM_HEAD}
     *
     * @return 音效, 如果不存在则返回 null
     * <p>原文：the sound or null
     */
    @Nullable
    public Sound getSound() {
        return this.sound;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 乐器的类型ID
     * <p>原文：The type ID of this instrument.
     */
    @org.jetbrains.annotations.ApiStatus.Internal // Paper
    public byte getType() {
        return this.type;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 根据类型ID获取Instrument对象。
     * <p>原文：
     * Get an instrument by its type ID.
     *
     * @param type 类型ID
     * <p>原文：The type ID
     * @return 相应的Instrument对象
     * <p>原文：The instrument
     */
    @org.jetbrains.annotations.ApiStatus.Internal // Paper
    @Nullable
    public static Instrument getByType(final byte type) {
        return BY_DATA.get(type);
    }

    static {
        for (Instrument instrument : Instrument.values()) {
            BY_DATA.put(instrument.getType(), instrument);
        }
    }
}

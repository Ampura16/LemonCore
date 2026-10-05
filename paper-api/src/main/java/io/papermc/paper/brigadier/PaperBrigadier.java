package io.papermc.paper.brigadier;

import com.mojang.brigadier.Message;
import io.papermc.paper.command.brigadier.MessageComponentSerializer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.TextComponent;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 用于弥合 Brigadier 与 Paper-MojangAPI 之间差异的辅助方法。
 * <p>原文：
 * Helper methods to bridge the gaps between Brigadier and Paper-MojangAPI.
 *
 * @deprecated 将被移除。此类中功能的直接替代参见 {@link MessageComponentSerializer}。Paper 上 Brigadier 的通用入口参见 {@link io.papermc.paper.command.brigadier.Commands}。
 * <p>原文：for removal. See {@link MessageComponentSerializer} for a direct replacement of functionality found in
 * this class.
 * As a general entrypoint to brigadier on paper, see {@link io.papermc.paper.command.brigadier.Commands}.
 */
@Deprecated(forRemoval = true, since = "1.20.6")
public final class PaperBrigadier {
    private PaperBrigadier() {
        throw new RuntimeException("PaperBrigadier is not to be instantiated!");
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 从 {@link ComponentLike} 创建新的 Brigadier {@link Message}。
     * <p>
     * 主要用于与其他 Paper-MojangAPI API 结合，创建丰富的建议提示。
     * </p>
     * <p>原文：
     * Create a new Brigadier {@link Message} from a {@link ComponentLike}.
     *
     * <p>Mostly useful for creating rich suggestion tooltips in combination with other Paper-MojangAPI APIs.</p>
     *
     * @param componentLike {@link ComponentLike}，用于 {@link Message} 的内容
     * <p>原文：The {@link ComponentLike} to use for the {@link Message} contents
     * @return 新的 Brigadier {@link Message}
     * <p>原文：A new Brigadier {@link Message}
     */
    public static @NonNull Message message(final @NonNull ComponentLike componentLike) {
        return MessageComponentSerializer.message().serialize(componentLike.asComponent());
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 从 Brigadier {@link Message} 创建新的 {@link Component}。
     * <p>
     * 如果该 {@link Message} 是从 {@link Component} 创建的，则只会简单地转换回去，否则将以 {@link Message#getString()} 的内容创建新的 {@link TextComponent}
     * </p>
     * <p>原文：
     * Create a new {@link Component} from a Brigadier {@link Message}.
     *
     * <p>If the {@link Message} was created from a {@link Component}, it will simply be
     * converted back, otherwise a new {@link TextComponent} will be created with the
     * content of {@link Message#getString()}</p>
     *
     * @param message 源 {@link Message}，用于创建 {@link Component}
     * <p>原文：The {@link Message} to create a {@link Component} from
     * @return 创建的 {@link Component}
     * <p>原文：The created {@link Component}
     */
    public static @NonNull Component componentFromMessage(final @NonNull Message message) {
        return MessageComponentSerializer.message().deserialize(message);
    }
}

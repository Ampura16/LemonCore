package io.papermc.paper.dialog;

import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryBuilderFactory;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.data.InlinedRegistryBuilderProvider;
import io.papermc.paper.registry.data.dialog.DialogRegistryEntry;
import java.util.function.Consumer;
import net.kyori.adventure.dialog.DialogLike;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示对话框。可在服务器正常运行期间通过 {@link #create(Consumer)} 创建，也可在引导期间通过 {@link io.papermc.paper.registry.event.RegistryEvents#DIALOG} 创建。
 * <p>原文：
 * Represents a dialog. Can be created during normal server operation via {@link #create(Consumer)}.
 * Can also be created during bootstrap via {@link io.papermc.paper.registry.event.RegistryEvents#DIALOG}.
 */
@ApiStatus.NonExtendable
public interface Dialog extends Keyed, DialogLike {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用提供的构建器创建新对话框。
     * <p>原文：
     * Creates a new dialog using the provided builder.
     *
     * @param value 用于创建对话框的构建器
     * <p>原文：the builder to use for creating the dialog
     * @return 新的对话框实例
     * <p>原文：a new dialog instance
     */
    @ApiStatus.Experimental
    static Dialog create(final Consumer<RegistryBuilderFactory<Dialog, ? extends DialogRegistryEntry.Builder>> value) {
        return InlinedRegistryBuilderProvider.instance().createDialog(value);
    }

    // Start generate - Dialog
    Dialog CUSTOM_OPTIONS = getDialog("custom_options");

    Dialog QUICK_ACTIONS = getDialog("quick_actions");

    Dialog SERVER_LINKS = getDialog("server_links");
    // End generate - Dialog

    private static Dialog getDialog(@KeyPattern.Value final String value) {
        final Registry<Dialog> registry = RegistryAccess.registryAccess().getRegistry(RegistryKey.DIALOG);
        return registry.getOrThrow(Key.key(Key.MINECRAFT_NAMESPACE, value));
    }

    /**
     * @deprecated 请使用 {@link Registry#getKey(Keyed)}、{@link io.papermc.paper.registry.RegistryAccess#getRegistry(io.papermc.paper.registry.RegistryKey)} 和 {@link io.papermc.paper.registry.RegistryKey#DIALOG}。对话框可以没有键。
     * <p>原文：use {@link Registry#getKey(Keyed)}, {@link io.papermc.paper.registry.RegistryAccess#getRegistry(io.papermc.paper.registry.RegistryKey)},
     * and {@link io.papermc.paper.registry.RegistryKey#DIALOG}. Dialogs can exist without a key.
     */
    @Deprecated(since = "1.21.8", forRemoval = true)
    @Override
    NamespacedKey getKey();

    /**
     * @deprecated 请使用 {@link Registry#getKey(Keyed)}、{@link io.papermc.paper.registry.RegistryAccess#getRegistry(io.papermc.paper.registry.RegistryKey)} 和 {@link io.papermc.paper.registry.RegistryKey#DIALOG}。对话框可以没有键。
     * <p>原文：use {@link Registry#getKey(Keyed)}, {@link io.papermc.paper.registry.RegistryAccess#getRegistry(io.papermc.paper.registry.RegistryKey)},
     * and {@link io.papermc.paper.registry.RegistryKey#DIALOG}. Dialogs can exist without a key.
     */
    @Deprecated(since = "1.21.8", forRemoval = true)
    @Override
    default Key key() {
        return Keyed.super.key();
    }
}

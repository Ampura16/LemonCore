package org.bukkit.conversations;

import com.google.common.base.Joiner;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * FixedSetPrompt 是任何需要用户从固定集合中响应的提示的基类。
 * <p>原文：
 * FixedSetPrompt is the base class for any prompt that requires a fixed set
 * response from the user.
 *
 * @deprecated The conversation API has been deprecated for removal. This system does not support component based messages
 * and has been slowly losing functionality over the years as Minecraft has changed that this API can not adapt to.
 * It is recommended you instead manually listen to the {@link io.papermc.paper.event.player.AsyncChatEvent}
 * or alternatively using {@link io.papermc.paper.dialog.Dialog} to get user input.
 */
@Deprecated(forRemoval = true)
public abstract class FixedSetPrompt extends ValidatingPrompt {

    protected List<String> fixedSet;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从一组字符串创建一个 FixedSetPrompt。
     * <p>
     * foo = new FixedSetPrompt("bar", "cheese", "panda");
     * <p>原文：
     * Creates a FixedSetPrompt from a set of strings.
     * <p>
     * foo = new FixedSetPrompt("bar", "cheese", "panda");
     *
     * @param fixedSet 一组固定的字符串，用户必须输入其中之一。
     * <p>原文：A fixed set of strings, one of which the user must
     *     type.
     */
    public FixedSetPrompt(@NotNull String... fixedSet) {
        super();
        this.fixedSet = Arrays.asList(fixedSet);
    }

    private FixedSetPrompt() {}

    @Override
    protected boolean isInputValid(@NotNull ConversationContext context, @NotNull String input) {
        return fixedSet.contains(input);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个包含构造函数中声明的所有选项的格式化字符串的实用函数。
     * <p>原文：
     * Utility function to create a formatted string containing all the
     * options declared in the constructor.
     *
     * @return 格式化的选项，如 "[bar, cheese, panda]"（如果 bar、cheese 和 panda 是使用的选项）。
     * <p>原文：the options formatted like "[bar, cheese, panda]" if bar,
     *     cheese, and panda were the options used
     */
    @NotNull
    protected String formatFixedSet() {
        return "[" + Joiner.on(", ").join(fixedSet) + "]";
    }
}

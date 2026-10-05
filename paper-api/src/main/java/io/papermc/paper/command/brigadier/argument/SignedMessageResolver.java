package io.papermc.paper.command.brigadier.argument;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import java.util.concurrent.CompletableFuture;
import net.kyori.adventure.chat.SignedMessage;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link SignedMessage} 的解析器
 * <p>原文：
 * A resolver for a {@link SignedMessage}
 *
 * @see ArgumentTypes#signedMessage()
 */
@ApiStatus.NonExtendable
public interface SignedMessageResolver {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取消息的字符串内容
     * <p>原文：
     * Gets the string content of the message
     *
     * @return 字符串内容
     * <p>原文：string content
     */
    String content();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 解析此签名消息。这将使用客户端发送的 {@link CommandContext} 和签名参数。
     * <p>
     * 如果未提供签名消息信息，将改为返回“系统”签名消息。
     * <p>原文：
     * Resolves this signed message. This will the {@link CommandContext}
     * and signed arguments sent by the client.
     * <p>
     * In the case that signed message information isn't provided, a "system"
     * signed message will be returned instead.
     *
     * @param argumentName 参数名称
     * <p>原文：argument name
     * @param context 命令上下文
     * <p>原文：the command context
     * @return {@link SignedMessage} 的 CompletableFuture
     * <p>原文：a completable future for the {@link SignedMessage}
     * @throws CommandSyntaxException 语法异常
     * <p>原文：syntax exception
     */
    CompletableFuture<SignedMessage> resolveSignedMessage(String argumentName, CommandContext<CommandSourceStack> context) throws CommandSyntaxException;
}

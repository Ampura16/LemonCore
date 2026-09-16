package io.papermc.paper;

import java.time.Instant;
import java.util.Optional;
import java.util.OptionalInt;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.util.Services;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * 当前服务器构建的相关信息.
 */
@NullMarked
@ApiStatus.NonExtendable
public interface ServerBuildInfo {
    /**
     * Paper 的品牌 id.
     */
    Key BRAND_PAPER_ID = Key.key("papermc", "paper");

    /**
     * 获取 {@code ServerBuildInfo}.
     *
     * @return {@code ServerBuildInfo}
     */
    static ServerBuildInfo buildInfo() {
        //<editor-fold defaultstate="collapsed" desc="Holder">
        final class Holder {
            static final Optional<ServerBuildInfo> INSTANCE = Services.service(ServerBuildInfo.class);
        }
        //</editor-fold>
        return Holder.INSTANCE.orElseThrow();
    }

    /**
     * 获取服务器的品牌 id.
     *
     * @return 服务器的品牌 id（例如 "papermc:paper"）
     */
    Key brandId();

    /**
     * 检查当前服务器是否兼容指定的品牌.
     *
     * @param brandId 要检查的品牌（例如 "papermc:folia"）
     * @return 如果服务器兼容指定品牌则返回 {@code true}
     */
    @ApiStatus.Experimental
    boolean isBrandCompatible(final Key brandId);

    /**
     * 获取服务器的品牌名称.
     *
     * @return 服务器的品牌名称（例如 "Paper"）
     */
    String brandName();

    /**
     * 获取 Minecraft 版本 id.
     *
     * @return Minecraft 版本 id（例如 "1.20.4"、"1.20.2-pre2"、"23w31a"）
     */
    String minecraftVersionId();

    /**
     * 获取 Minecraft 版本名称.
     *
     * @return Minecraft 版本名称（例如 "1.20.4"、"1.20.2 Pre-release 2"、"23w31a"）
     */
    String minecraftVersionName();

    /**
     * 获取构建编号.
     *
     * @return 构建编号
     */
    OptionalInt buildNumber();

    /**
     * 获取构建时间.
     *
     * @return 构建时间
     */
    Instant buildTime();

    /**
     * 获取 git 提交分支.
     *
     * @return git 提交分支
     */
    Optional<String> gitBranch();

    /**
     * 获取 git 提交哈希值.
     *
     * @return git 提交哈希值
     */
    Optional<String> gitCommit();

    /**
     * 创建服务器构建信息的字符串表示形式.
     *
     * @param representation 表示形式的类型
     * @return 字符串
     */
    String asString(final StringRepresentation representation);

    /**
     * 字符串表示形式类型.
     */
    enum StringRepresentation {
        /**
         * 简单版本字符串,格式为 {@code <minecraftVersionId>-<buildNumber>-<gitCommit>}.
         */
        VERSION_SIMPLE,
        /**
         * 简单版本字符串,格式为 {@code <minecraftVersionId>-<buildNumber>-<gitBranch>@<gitCommit> (<buildTime>)}.
         */
        VERSION_FULL,
    }
}

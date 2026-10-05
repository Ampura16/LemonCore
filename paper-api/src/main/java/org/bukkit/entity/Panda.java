package org.bukkit.entity;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表熊猫.
 * <p>原文：
 * Panda entity.
 */
public interface Panda extends Animals, Sittable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得熊猫的显性基因.
     * <p>原文：
     * Gets this Panda's main gene.
     *
     * @return 显性基因
     * <p>原文：main gene
     */
    @NotNull
    Gene getMainGene();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置熊猫的显性基因.
     * <p>原文：
     * Sets this Panda's main gene.
     *
     * @param gene 显性基因
     * <p>原文：main gene
     */
    void setMainGene(@NotNull Gene gene);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取隐性基因.
     * <p>原文：
     * Gets this Panda's hidden gene.
     *
     * @return 隐性基因
     * <p>原文：hidden gene
     */
    @NotNull
    Gene getHiddenGene();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置隐性基因.
     * <p>原文：
     * Sets this Panda's hidden gene.
     *
     * @param gene 隐性基因
     * <p>原文：hidden gene
     */
    void setHiddenGene(@NotNull Gene gene);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取熊猫是否正在翻滚。
     * <p>原文：
     * Gets whether the Panda is rolling
     *
     * @return 是否正在翻滚
     * <p>原文：Whether the Panda is rolling
     */
    boolean isRolling();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置熊猫是否正在翻滚。
     * <p>原文：
     * Sets whether the Panda is rolling
     *
     * @param flag 是否正在翻滚
     * <p>原文：Whether the Panda is rolling
     */
    void setRolling(boolean flag);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取熊猫是否正在打喷嚏。
     * <p>原文：
     * Gets whether the Panda is sneezing
     *
     * @return 是否正在打喷嚏
     * <p>原文：Whether the Panda is sneezing
     */
    boolean isSneezing();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置熊猫是否正在打喷嚏。
     * <p>原文：
     * Sets whether the Panda is sneezing
     *
     * @param flag 是否正在打喷嚏
     * <p>原文：Whether the Panda is sneezing
     */
    void setSneezing(boolean flag);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取熊猫是否正在翻倒（背朝下）。
     * <p>原文：
     * Gets whether the Panda is on its back
     *
     * @return 是否正在翻倒（背朝下）
     * <p>原文：Whether the Panda is on its back
     */
    boolean isOnBack();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置熊猫是否正在翻倒（背朝下）。
     * <p>原文：
     * Sets whether the Panda is on its back
     *
     * @param flag 是否正在翻倒（背朝下）
     * <p>原文：Whether the Panda is on its back
     */
    void setOnBack(boolean flag);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取熊猫是否正在进食。
     * <p>原文：
     * Gets whether the Panda is eating
     *
     * @return 是否正在进食
     * <p>原文：Whether the Panda is eating
     */
    boolean isEating();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置熊猫的进食状态。熊猫必须持有食物才能生效。
     * <p>原文：
     * Sets the Panda's eating status. The panda must be holding food for this to work
     *
     * @param flag 是否正在进食
     * <p>原文：Whether the Panda is eating
     */
    void setEating(boolean flag);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取熊猫是否害怕。
     * <p>原文：
     * Gets whether the Panda is scared
     *
     * @return 是否害怕
     * <p>原文：Whether the Panda is scared
     */
    boolean isScared();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取熊猫将不开心持续多少tick。
     * <p>原文：
     * Gets how many ticks the panda will be unhappy for
     *
     * @return 熊猫将不开心持续的tick数
     * <p>原文：The number of ticks the panda will be unhappy for
     */
    int getUnhappyTicks();

    // Paper start - Panda API
    /**
     * Sets the sneeze progress in this animation.
     * This value counts up only if {@link Panda#isSneezing()} is true
     *
     * @param ticks sneeze progress
     */
    void setSneezeTicks(int ticks);

    /**
     * Gets the current sneeze progress, or how many ticks this panda will sneeze for.
     *
     * @return sneeze progress
     */
    int getSneezeTicks();

    /**
     * Sets the eating ticks for this panda.
     * <p>
     *
     * This starts counting up as long as it is greater than 0.
     *
     * @param ticks eating ticks
     */
    void setEatingTicks(int ticks);

    /**
     * Gets the current eating progress, or how many ticks this panda has been eating for.
     *
     * @return eating progress
     */
    int getEatingTicks();

    /**
     * Sets the number of ticks this panda will be unhappy for.
     * <p>
     * This value counts down.
     *
     * @param ticks unhappy ticks
     */
    void setUnhappyTicks(int ticks);

    /**
     * Sets if this panda is currently on its back.
     *
     * @param onBack is on its back
     * @deprecated use {@link #setOnBack(boolean)}
     */
    @Deprecated(forRemoval = true, since = "1.19")
    default void setIsOnBack(boolean onBack) {
        this.setOnBack(onBack);
    }

    /**
     * Sets if this panda is currently sitting.
     *
     * @param sitting is currently sitting
     * @deprecated use {@link #setSitting(boolean)}
     */
    @Deprecated(forRemoval = true, since = "1.19")
    default void setIsSitting(boolean sitting) {
        this.setSitting(sitting);
    }

    /**
     * Gets this Panda's combined gene.
     * <p>
     * The combined gene can be modified using
     * {@link #setMainGene(Gene)} or {@link #setHiddenGene(Gene)}.
     *
     * @return combined gene
     */
    @NotNull
    Gene getCombinedGene();
    // Paper end - Panda API

    public enum Gene {

        // Start generate - PandaGene
        NORMAL(false),
        LAZY(false),
        WORRIED(false),
        PLAYFUL(false),
        BROWN(true),
        WEAK(true),
        AGGRESSIVE(false);
        // End generate - PandaGene

        private final boolean recessive;

        private Gene(boolean recessive) {
            this.recessive = recessive;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 获得此基因是否为隐性基因, 也就是说要求父母需要遗传给孩子.
         * <p>原文：
         * Gets whether this gene is recessive, i.e. required in both parents to
         * propagate to children.
         *
         * @return 隐性状态
         * <p>原文：recessive status
         */
        public boolean isRecessive() {
            return recessive;
        }
    }
}

package com.destroystokyo.paper.entity.villager;

import com.google.common.base.Preconditions;
import java.util.EnumMap;
import java.util.Map;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 玩家在某个村民处的声望分数。
 * <p>原文：
 * A reputation score for a player on a villager.
 */
@NullMarked
public final class Reputation {

    private final Map<ReputationType, Integer> reputation;

    public Reputation() {
        this(new EnumMap<>(ReputationType.class));
    }

    public Reputation(final Map<ReputationType, Integer> reputation) {
        Preconditions.checkNotNull(reputation, "reputation cannot be null");
        this.reputation = reputation;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取特定 {@link ReputationType} 的声望值。
     * <p>原文：
     * Gets the reputation value for a specific {@link ReputationType}.
     *
     * @param type 要获取的声望 {@link ReputationType type}。
     * <p>原文：The {@link ReputationType type} of reputation to get.
     * @return {@link ReputationType type} 的值。
     * <p>原文：The value of the {@link ReputationType type}.
     */
    public int getReputation(final ReputationType type) {
        Preconditions.checkNotNull(type, "the reputation type cannot be null");
        return this.reputation.getOrDefault(type, 0);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置特定 {@link ReputationType} 的声望值。
     * <p>原文：
     * Sets the reputation value for a specific {@link ReputationType}.
     *
     * @param type 要设置的声望 {@link ReputationType type}。
     * <p>原文：The {@link ReputationType type} of reputation to set.
     * @param value {@link ReputationType type} 的值。
     * <p>原文：The value of the {@link ReputationType type}.
     */
    public void setReputation(final ReputationType type, final int value) {
        Preconditions.checkNotNull(type, "the reputation type cannot be null");
        this.reputation.put(type, value);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取当前是否为特定 {@link ReputationType} 设置了声望值。
     * <p>原文：
     * Gets if a reputation value is currently set for a specific {@link ReputationType}.
     *
     * @param type 要检查的 {@link ReputationType type}
     * <p>原文：The {@link ReputationType type} to check
     * @return 是否为此 {@link ReputationType type} 设置了值。
     * <p>原文：If there is a value for this {@link ReputationType type} set.
     */
    public boolean hasReputationSet(final ReputationType type) {
        return this.reputation.containsKey(type);
    }
}

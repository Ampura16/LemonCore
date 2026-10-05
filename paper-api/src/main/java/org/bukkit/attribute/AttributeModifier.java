package org.bukkit.attribute;

import com.google.common.base.Preconditions;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.util.NumberConversions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 一个属性修饰符的具体实现.
 * <p>原文：
 * Concrete implementation of an attribute modifier.
 */
public class AttributeModifier implements ConfigurationSerializable, Keyed {

    private static final Pattern UUID_PATTERN = Pattern.compile("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$");
    private final NamespacedKey key;
    private final double amount;
    private final Operation operation;
    private final EquipmentSlotGroup slot;

    @Deprecated(since = "1.21", forRemoval = true)
    public AttributeModifier(@NotNull String name, double amount, @NotNull Operation operation) {
        this(UUID.randomUUID(), name, amount, operation);
    }

    @Deprecated(since = "1.21", forRemoval = true)
    public AttributeModifier(@NotNull UUID uuid, @NotNull String name, double amount, @NotNull Operation operation) {
        this(uuid, name, amount, operation, (EquipmentSlot) null);
    }

    @Deprecated(since = "1.21", forRemoval = true)
    public AttributeModifier(@NotNull UUID uuid, @NotNull String name, double amount, @NotNull Operation operation, @Nullable EquipmentSlot slot) {
        this(uuid, name, amount, operation, (slot) == null ? EquipmentSlotGroup.ANY : slot.getGroup());
    }

    @Deprecated(since = "1.21", forRemoval = true)
    public AttributeModifier(@NotNull UUID uuid, @NotNull String name, double amount, @NotNull Operation operation, @NotNull EquipmentSlotGroup slot) {
        this(NamespacedKey.fromString(uuid.toString()), amount, operation, slot);
    }

    // Paper start - Add constructor without EquipmentSlotGroup
    public AttributeModifier(@NotNull NamespacedKey key, double amount, @NotNull Operation operation) {
        this(key, amount, operation, EquipmentSlotGroup.ANY);
    }
    // Paper end

    public AttributeModifier(@NotNull NamespacedKey key, double amount, @NotNull Operation operation, @NotNull EquipmentSlotGroup slot) {
        Preconditions.checkArgument(key != null, "Key cannot be null");
        Preconditions.checkArgument(operation != null, "Operation cannot be null");
        Preconditions.checkArgument(slot != null, "EquipmentSlotGroup cannot be null");
        this.key = key;
        this.amount = amount;
        this.operation = operation;
        this.slot = slot;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该修饰符的 UUID.
     * <p>原文：
     * Get the unique ID for this modifier.
     *
     * @return unique id
     * @see #getKey()
     * @deprecated 属性现通过 key 区分
     * <p>原文：attributes are now identified by keys
     */
    @NotNull
    @Deprecated(since = "1.21", forRemoval = true)
    public UUID getUniqueId() {
        NamespacedKey namespacedKey = getKey();
        if (namespacedKey.getNamespace().equals(NamespacedKey.MINECRAFT)) {
            String key = namespacedKey.getKey();

            if (key.length() == 36 && UUID_PATTERN.matcher(key).matches()) {
                return UUID.fromString(key);
            }
        }

        return UUID.nameUUIDFromBytes(namespacedKey.toString().getBytes(StandardCharsets.UTF_8));
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return key;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该修饰符的名称.
     * <p>原文：
     * Get the name of this modifier.
     *
     * @return 名称
     * <p>原文：name
     */
    @NotNull
    public String getName() {
        return key.getKey();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该修饰符在根据其 {@link Operation} 运算模式进行计算时的修饰值.
     * <p>原文：
     * Get the amount by which this modifier will apply its {@link Operation}.
     *
     * @return 修饰值
     * <p>原文：modification amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该修饰符的运算模式.
     * <p>原文：
     * Get the operation this modifier will apply.
     *
     * @return 运算模式
     * <p>原文：operation
     */
    @NotNull
    public Operation getOperation() {
        return operation;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该修饰符生效的 {@link EquipmentSlot},
     * <p>
     * 若该修饰符可以在任意槽位生效, 返回 null.
     * <p>原文：
     * Get the {@link EquipmentSlot} this AttributeModifier is active on,
     * or null if this modifier is applicable for any slot.
     *
     * @return 目标槽位
     * <p>原文：the slot
     * @deprecated 请使用 {@link #getSlotGroup()}
     * <p>原文：use {@link #getSlotGroup()}
     */
    @Nullable
    @Deprecated(since = "1.20.5")
    public EquipmentSlot getSlot() {
        return slot == EquipmentSlotGroup.ANY ? null : slot.getExample();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该修饰符生效的 {@link EquipmentSlotGroup}.
     * <p>
     * 若该修饰符可以在任意槽位生效, 返回 {@link EquipmentSlotGroup#ANY}.
     * <p>原文：
     * Get the {@link EquipmentSlotGroup} this AttributeModifier is active on.
     *
     * @return 目标槽位组
     * <p>原文：the slot
     */
    @NotNull
    public EquipmentSlotGroup getSlotGroup() {
        return slot;
    }

    @NotNull
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("key", key.toString());
        data.put("operation", operation.ordinal());
        data.put("amount", amount);
        if (slot != null && slot != EquipmentSlotGroup.ANY) {
            data.put("slot", slot.toString());
        }
        return data;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AttributeModifier)) {
            return false;
        }
        AttributeModifier mod = (AttributeModifier) other;
        boolean slots = (this.slot != null ? (this.slot == mod.slot) : mod.slot == null);
        return this.key.equals(mod.key) && this.amount == mod.amount && this.operation == mod.operation && slots;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.key);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.amount) ^ (Double.doubleToLongBits(this.amount) >>> 32));
        hash = 17 * hash + Objects.hashCode(this.operation);
        hash = 17 * hash + Objects.hashCode(this.slot);
        return hash;
    }

    @Override
    public String toString() {
        return "AttributeModifier{"
                + "key=" + this.key.toString()
                + ", operation=" + this.operation.name()
                + ", amount=" + this.amount
                + ", slot=" + (this.slot != null ? this.slot.toString() : "")
                + "}";
    }

    @NotNull
    public static AttributeModifier deserialize(@NotNull Map<String, Object> args) {
        NamespacedKey key;
        if (args.containsKey("uuid")) {
            key = NamespacedKey.fromString((String) args.get("uuid"));
        } else {
            key = NamespacedKey.fromString((String) args.get("key"));
        }
        if (args.containsKey("slot")) {
            EquipmentSlotGroup slotGroup = EquipmentSlotGroup.getByName(args.get("slot").toString().toLowerCase(Locale.ROOT));
            if (slotGroup == null) {
                slotGroup = EquipmentSlotGroup.ANY;

                EquipmentSlot slot = EquipmentSlot.valueOf((args.get("slot").toString().toUpperCase(Locale.ROOT)));
                if (slot != null) {
                    slotGroup = slot.getGroup();
                }
            }

            return new AttributeModifier(key, NumberConversions.toDouble(args.get("amount")), Operation.values()[NumberConversions.toInt(args.get("operation"))], slotGroup);
        }
        return new AttributeModifier(key, NumberConversions.toDouble(args.get("amount")), Operation.values()[NumberConversions.toInt(args.get("operation"))], EquipmentSlotGroup.ANY);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 可用的运算模式枚举.
     * <p>原文：
     * Enumerable operation to be applied.
     */
    public enum Operation {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 在基值上直接相加 (或相减) 该值
         * <p>原文：
         * Adds (or subtracts) the specified amount to the base value.
         */
        ADD_NUMBER,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 在基值上增加该值的标量
         * <p>原文：
         * Adds this scalar of amount to the base value.
         */
        ADD_SCALAR,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 将该值 +1 后乘以基值.
         * <p>原文：
         * Multiply amount by this value, after adding 1 to it.
         */
        MULTIPLY_SCALAR_1;
    }
}

package com.destroystokyo.paper.profile;

import com.google.common.base.Preconditions;
import java.util.Objects;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示 {@link PlayerProfile} 上的属性
 * <p>原文：
 * Represents a property on a {@link PlayerProfile}
 */
@NullMarked
public final class ProfileProperty {

    private final String name;
    private final String value;
    private final @Nullable String signature;

    public ProfileProperty(final String name, final String value) {
        this(name, value, null);
    }

    public ProfileProperty(final String name, final String value, final @Nullable String signature) {
        this.name = Preconditions.checkNotNull(name, "ProfileProperty name can not be null");
        this.value = Preconditions.checkNotNull(value, "ProfileProperty value can not be null");
        this.signature = signature;
        Preconditions.checkArgument(name.length() <= 64, "ProfileProperty name can not be longer than 64 characters");
        Preconditions.checkArgument(value.length() <= Short.MAX_VALUE, "ProfileProperty value can not be longer than 32767 characters");
        Preconditions.checkArgument(signature == null || signature.length() <= 1024, "ProfileProperty signature can not be longer than 1024 characters");
    }

    /**
     * @return 属性名称，例如“textures”
     * <p>原文：The property name, ie "textures"
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return 属性值，很可能是 base64 编码的
     * <p>原文：The property value, likely to be base64 encoded
     */
    public String getValue() {
        return this.value;
    }

    /**
     * @return Mojang 为签名属性提供的签名
     * <p>原文：A signature from Mojang for signed properties
     */
    public @Nullable String getSignature() {
        return this.signature;
    }

    /**
     * @return 此属性是否具有签名
     * <p>原文：If this property has a signature or not
     */
    public boolean isSigned() {
        return this.signature != null;
    }

    @Override
    public boolean equals(final @Nullable Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        final ProfileProperty that = (ProfileProperty) o;
        return Objects.equals(this.name, that.name) &&
            Objects.equals(this.value, that.value) &&
            Objects.equals(this.signature, that.signature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}

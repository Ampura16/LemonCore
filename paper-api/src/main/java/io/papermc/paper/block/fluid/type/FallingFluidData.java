package io.papermc.paper.block.fluid.type;

import io.papermc.paper.block.fluid.FluidData;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link FluidData} 的特定子类型，API 会为可能正在下落的流体的流体数据返回该类型。
 * <p>原文：
 * A specific subtype of {@link FluidData} that is returned by the API for fluid data of potentially falling fluids.
 */
public interface FallingFluidData extends FluidData {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此液体是否正在下落。
     * <p>原文：
     * Get if this liquid is falling.
     *
     * @return 如果正在下落则为 true
     * <p>原文：true if falling
     */
    boolean isFalling();
}

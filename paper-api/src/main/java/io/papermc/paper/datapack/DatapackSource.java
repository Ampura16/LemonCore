package io.papermc.paper.datapack;

import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 数据包的来源。
 * <p>原文：
 * Source of a datapack.
 */
@NullMarked
public sealed interface DatapackSource permits DatapackSourceImpl {

    DatapackSource DEFAULT = create("default");
    DatapackSource BUILT_IN = create("built_in");
    DatapackSource FEATURE = create("feature");
    DatapackSource WORLD = create("world");
    DatapackSource SERVER = create("server");
    DatapackSource PLUGIN = create("plugin");

    private static DatapackSource create(final String name) {
        return new DatapackSourceImpl(name);
    }
}

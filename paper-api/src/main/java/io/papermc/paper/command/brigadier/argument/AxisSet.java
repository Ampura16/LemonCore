package io.papermc.paper.command.brigadier.argument;

import com.mojang.brigadier.context.CommandContext;
import org.bukkit.Axis;
import java.util.Set;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 一组 {@link Axis}，用于在 {@link CommandContext#getArgument(String, Class)} 中代替原始 {@link Set}。
 * <p>原文：
 * A set of {@link Axis} intended for use in {@link CommandContext#getArgument(String, Class)} instead of a raw {@link Set}.
 */
public interface AxisSet extends Set<Axis> {

}

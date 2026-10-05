package org.bukkit.util;

import com.google.common.base.Preconditions;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;

public class StringUtil {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 复制所有可迭代集合中的元素到给定的另一个集合中.
     * <p>原文：
     * Copies all elements from the iterable collection of originals to the
     * collection provided.
     *
     * @param <T> 字符串的集合
     * <p>原文：the collection of strings
     * @param token 要寻找的字符串
     * <p>原文：String to search for
     * @param originals 用来检索的可遍历字符串集合
     * <p>原文：An iterable collection of strings to filter.
     * @param collection 用来储存检索到的对象的集合
     * <p>原文：The collection to add matches to
     * @return 用来储存检索到的元素拷贝的集合
     * <p>原文：the collection provided that would have the elements copied
     *     into
     * @throws UnsupportedOperationException 如果这个集合是不可变的,
     * 并且源中包含一个以特定检索字符串开头的元素,则抛出
     * <p>原文：if the collection is immutable
     *     and originals contains a string which starts with the specified
     *     search string.
     * @throws IllegalArgumentException 当任何一个参数是null时抛出
     * <p>原文：if any parameter is null
     * @throws IllegalArgumentException 当任何一个参数是null时抛出
     * <p>原文：if originals contains a null element.
     *     <b>Note: the collection may be modified before this is thrown</b>
     */
    @NotNull
    public static <T extends Collection<? super String>> T copyPartialMatches(@NotNull final String token, @NotNull final Iterable<String> originals, @NotNull final T collection) throws UnsupportedOperationException, IllegalArgumentException {
        Preconditions.checkArgument(token != null, "Search token cannot be null");
        Preconditions.checkArgument(collection != null, "Collection cannot be null");
        Preconditions.checkArgument(originals != null, "Originals cannot be null");

        for (String string : originals) {
            if (startsWithIgnoreCase(string, token)) {
                collection.add(string);
            }
        }

        return collection;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查一个字符串是否以另一个字符串开头, 不区分大小写.
     * <p>
     * 这个方法使用 {@link java.lang.String#regionMatches(boolean, int, String, int, int)} 进行比较,
     * 因此不像 {@link java.lang.String#toLowerCase()} 一样需要拷贝整个数组.
     * <p>
     * This method uses a region to check case-insensitive equality. This
     * means the internal array does not need to be copied like a
     * toLowerCase() call would.
     * <p>原文：
     * This method uses a region to check case-insensitive equality. This
     * means the internal array does not need to be copied like a
     * toLowerCase() call would.
     *
     * @param string 要检查的字符串
     * <p>原文：String to check
     * @param prefix 用来比较的字符串前缀
     * <p>原文：Prefix of string to compare
     * @return 若提供的字符串忽略大小写符合前缀则返回true
     * <p>原文：true if provided string starts with, ignoring case, the prefix
     *     provided
     * @throws NullPointerException 若前缀为null则抛出
     * <p>原文：if prefix is null
     * @throws IllegalArgumentException 若提供的字符串为null则抛出
     * <p>原文：if string is null
     */
    public static boolean startsWithIgnoreCase(@NotNull final String string, @NotNull final String prefix) throws IllegalArgumentException, NullPointerException {
        Preconditions.checkArgument(string != null, "Cannot check a null string for a match");
        if (string.length() < prefix.length()) {
            return false;
        }
        return string.regionMatches(true, 0, prefix, 0, prefix.length());
    }
}

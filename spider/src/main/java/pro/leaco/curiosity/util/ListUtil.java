package pro.leaco.curiosity.util;


import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 通用集合API 工具
 * 提供各种集合的通用操作方法
 *
 * @author Leaco
 */
public class ListUtil {
    /**
     * 返回一个空列表
     *
     * @param <T> 任意类型
     * @return 空列表
     */
    public static <T> List<T> emptyList() {
        return Collections.emptyList();
    }

    public static <T> Stream<T> concatStream(Stream<? extends T> a, Stream<? extends T> b) {
        return Stream.concat(a, b);
    }

    public static <T> Stream<T> concatStream(Stream<? extends T> a, Stream<? extends T> b, Stream<? extends T>... more) {
        Stream<T> result = concatStream(a, b);
        for (Stream<? extends T> s : more) {
            result = Stream.concat(result, s);
        }
        return result;
    }

    /**
     * 单个家具转为 stream
     *
     * @param single 单独家具
     * @param <T>
     * @return stream
     */
    @SuppressWarnings("unchecked")
    public static <T> Stream<T> stream(T single) {
        if (single == null) {
            return Stream.empty();
        }
        return stream(ListUtil.asList(single));
    }

    /**
     * 集合转为 stream
     *
     * @param collection 集合
     * @param <T>
     * @return stream
     */
    @SuppressWarnings("unchecked")
    public static <T> Stream<T> stream(Collection<T> collection) {
        if (collection == null) {
            return Stream.empty();
        }
        return collection.stream();
    }

    /**
     * 迭代器转为 stream
     *
     * @param iterable 集合
     * @param <T>
     * @return stream
     */
    @SuppressWarnings("unchecked")
    public static <T> Stream<T> stream(Iterable<T> iterable) {
        if (iterable == null) {
            return Stream.empty();
        }
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    /**
     * 数组转为 stream
     *
     * @param arrays 数组
     * @param <T>
     * @return stream
     */
    @SuppressWarnings("unchecked")
    public static <T> Stream<T> stream(T[] arrays) {
        if (arrays == null) {
            return Stream.empty();
        }
        return Stream.of(arrays);
    }


    /**
     * 判断列表是否为空或者null
     *
     * @param list
     * @return
     */
    public static final boolean isNullOrEmpty(Collection<?> list) {
        return list == null || list.isEmpty();
    }

    /**
     * 判断迭代器是否为空
     *
     * @param iterable 迭代器
     * @return
     */
    public static final boolean isNullOrEmpty(Iterable<?> iterable) {
        if (iterable == null) {
            return true;
        }
        Iterator<?> iterator = iterable.iterator();
        return !iterator.hasNext();
    }


    /**
     * 判断列表是否不为空
     *
     * @param list
     * @return
     */
    public static final boolean isNotEmpty(Collection<?> list) {
        return list != null && !list.isEmpty();
    }

    /**
     * 判断迭代器是否不为空
     *
     * @param iterable 迭代器
     * @return
     */
    public static final boolean isNotEmpty(Iterable<?> iterable) {
        if (iterable != null) {
            Iterator<?> iterator = iterable.iterator();
            return iterator.hasNext();
        }
        return false;
    }

    /**
     * 多个实体转换为列表
     *
     * @param objs 多个实体参数
     * @param <T>
     * @return
     */
    public static final <T extends Object> List<T> asList(T... objs) {
        if (objs == null) {
            return emptyList();
        }
        List<T> result = new ArrayList<>();
        for (T obj : objs) {
            result.add(obj);
        }
        return result;
    }


    /**
     * 两个子集合并为一个列表
     *
     * @param aList
     * @param bList
     * @param <T>
     * @param <a>
     * @param <b>
     * @param <ALIST>
     * @param <BLIST>
     * @return
     */
    public static <T extends Object, a extends T, b extends T,
            ALIST extends Collection<a>, BLIST extends Collection<b>>
    List<T> asList(ALIST aList, BLIST bList) {
        if (aList == null && bList == null) {
            return null;
        }
        List<T> result = new ArrayList<>();
        if (aList != null) {
            for (a list : aList) {
                result.add(list);
            }
        }
        if (bList != null) {
            for (b list : bList) {
                result.add(list);
            }
        }
        return result;
    }

    /**
     * 两个子集合并为一个set
     *
     * @param aList
     * @param bList
     * @param <T>
     * @param <a>
     * @param <b>
     * @param <ALIST>
     * @param <BLIST>
     * @return
     */
    public static <T extends Object, a extends T, b extends T,
            ALIST extends Collection<a>, BLIST extends Collection<b>>
    Set<T> asSet(ALIST aList, BLIST bList) {
        if (aList == null && bList == null) {
            return null;
        }
        Set<T> result = new HashSet<>();
        if (aList != null) {
            for (a list : aList) {
                result.add(list);
            }
        }
        if (bList != null) {
            for (b list : bList) {
                result.add(list);
            }
        }
        return result;
    }


    /**
     * 吧一个由 逗号组成的 列表，分割为数组
     *
     * @param listStr
     * @return
     */
    public static Integer[] split2Array(String listStr) {
        if (StringUtils.isEmpty(listStr)) {
            return null;
        }

        String[] split = StringUtils.split(listStr, ",");
        if (split == null || split.length <= 0) {
            return null;
        }

        Integer[] result = new Integer[split.length];
        int i = 0;
        for (String s : split) {
            int val = Integer.parseInt(s);
            result[i] = val;
            i++;
        }
        return result;
    }


}

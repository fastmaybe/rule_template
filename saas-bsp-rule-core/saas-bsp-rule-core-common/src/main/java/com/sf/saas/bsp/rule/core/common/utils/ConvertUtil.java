package com.sf.saas.bsp.rule.core.common.utils;

import com.google.common.base.Throwables;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 数据转换工具类
 *
 * @author 80003774
 * @since 1.8
 */
public class ConvertUtil {

    private ConvertUtil() {
    }


    /**
     * 集合数据转换
     *
     * @param source    原集合数据
     * @param convector 实体转换函数
     * @param <P>       原实体类型
     * @param <R>       转换后实体类型
     * @return 转换后集合，直接返回List集合类型
     */
    public static <P, R> List<R> convertList(Collection<P> source, Function<P, R> convector) {
        List<R> result;
        if (null != source) {
            result = source.stream().filter(Objects::nonNull).map(convector).filter(Objects::nonNull).collect(Collectors.toList());
        } else {
            result = new ArrayList<>();
        }
        return result;
    }

    /**
     * 实体转换工具，转换方法由参数提供
     *
     * @param p         原实体
     * @param convector 转换函数
     * @param <P>       原类型
     * @param <R>       目标类型
     * @return 目标实体
     */
    public static <P, R> R convertBean(P p, Function<P, R> convector) {
        R r = null;
        if (null != p && null != convector) {
            r = convector.apply(p);
        }
        return r;
    }

    /**
     * 实体转换工具,指定转换类型
     *
     * @param p       原实体
     * @param rClass  目标类型
     * @param filters 目标忽略字段
     * @param <P>     原类型
     * @param <R>     目标类型
     * @return 目标实体
     */
    public static <P, R> R convertBean(P p, Class<R> rClass, String... filters) {
        R r = null;
        try {
            if (null != p) {
                r = rClass.newInstance();
                BeanUtils.copyProperties(p, r, filters);
            }
        } catch (Exception e) {
            Throwables.throwIfUnchecked(e);
        }
        return r;
    }

    @SuppressWarnings("unchecked")
    public static <T> T copy(T source) {
        if (null == source) {
            return null;
        }
        T r = null;
        try {
            r = (T) source.getClass().newInstance();
            BeanUtils.copyProperties(source, r);
        } catch (Exception e) {
            Throwables.throwIfUnchecked(e);
        }
        return r;
    }

    /**
     * 集合转Map工具，转换方法由参数提供
     *
     * @param collection     原集合
     * @param keyGenerator   map Key获取方法
     * @param valueGenerator map Value获取方法
     * @param <T>            原实体类型
     * @param <K>            Key类型
     * @param <V>            Value类型
     * @return Map
     */
    public static <T, K, V> Map<K, V> listToMap(Collection<T> collection, Function<T, K> keyGenerator, Function<T, V> valueGenerator) {
        Map<K, V> map = new LinkedHashMap<>();
        if (null != collection && null != keyGenerator && null != valueGenerator) {
            for (T t : collection) {
                K key = keyGenerator.apply(t);
                V value = valueGenerator.apply(t);
                if (null != key) {
                    map.put(key, value);
                }
            }
        }
        return map;
    }

    /**
     * 集合转Map工具,相同的key分组收集，转换方法由参数提供
     *
     * @param collection     原集合
     * @param keyGenerator   map Key获取方法
     * @param valueGenerator map Value获取方法
     * @param <T>            原实体类型
     * @param <K>            Key类型
     * @param <V>            Value类型
     * @return Map
     */
    public static <T, K, V> Map<K, List<V>> groupToMap(Collection<T> collection, Function<T, K> keyGenerator, Function<T, V> valueGenerator) {
        Map<K, List<V>> map = new LinkedHashMap<>();
        if (null != collection && null != keyGenerator && null != valueGenerator) {
            for (T t : collection) {
                K key = keyGenerator.apply(t);
                V value = valueGenerator.apply(t);
                List<V> list = map.computeIfAbsent(key, k -> new ArrayList<V>());
                list.add(value);
            }
        }
        return map;
    }

    /**
     * List转String，以连接符连接
     *
     * @param collection    原集合
     * @param stringBuilder 实体转String函数
     * @param delimiter     连接符
     * @param <T>           原实体类型
     * @return String
     */
    public static <T> String listToString(Collection<T> collection, Function<T, String> stringBuilder, String delimiter) {
        return String.join(delimiter, convertList(collection, stringBuilder));
    }

    public static List<String> splitConfig(String configs, String sp) {
        if (isEmpty(configs)) {
            return Collections.emptyList();
        }
        if (isEmpty(sp)) {
            List<String> res = new ArrayList<>();
            res.add(configs);
            return res;
        }
        String[] splits = configs.split(sp);
        return Arrays.stream(splits).map(String::trim).filter(t -> !t.isEmpty()).collect(Collectors.toList());
    }

    public static boolean isEmpty(Object obj) {
        boolean result = null == obj;
        result = (obj instanceof String) ? ((String) obj).isEmpty() : result;
        result = (obj instanceof Collection) ? ((Collection<?>) obj).isEmpty() : result;
        return result;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static boolean hasId(Long id) {
        return null != id && 0 != id && -1 != id;
    }

    /**
     * 通过字段get方法获取值，字段类型要是包装类型
     *
     * @param arg
     * @param paramKey
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object invokeParamVal(Object arg, String paramKey)    //NOSONAR
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {   //NOSONAR
        String getMethodName = getSetMethodName("get", paramKey);
        Method getMe = arg.getClass().getMethod(getMethodName);
        return getMe.invoke(arg);
    }

    private static String getSetMethodName(String method, String param) {
        char[] chars = param.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return method + String.valueOf(chars);
    }

}

package com.portfolio.demo.util;

import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class HelperFunctions {

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static String createPATCHsql(Map<String, String> keyValuePair) {
        StringJoiner sqlBuilder = new StringJoiner("", "", "");
        keyValuePair.entrySet().stream().forEachOrdered(entry -> {
            sqlBuilder.add(entry.getKey());
            sqlBuilder.add(" = '");
            sqlBuilder.add(entry.getValue());
            sqlBuilder.add("', ");
        });
        final String sql = sqlBuilder.toString().substring(0, sqlBuilder.length()-2);
        return sql;
    }

}

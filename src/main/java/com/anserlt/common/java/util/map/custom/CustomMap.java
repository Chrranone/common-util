package com.anserlt.common.java.util.map.custom;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 自定义的类型安全的数据容器
 */
public class CustomMap {

    private final Map<CustomMapKey<?>, Object> values = new HashMap<>();

    public <T> void put(CustomMapKey<T> key, T value) {
        values.put(key, value);
    }

    public <T> T get(CustomMapKey<T> key) {
        return key.type.cast(values.get(key));
    }

    public Set<CustomMapKey<?>> getKeyList() {
        return values.keySet();
    }

}

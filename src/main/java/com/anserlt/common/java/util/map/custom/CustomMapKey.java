package com.anserlt.common.java.util.map.custom;

import java.util.Objects;

/**
 * 自定义的类型安全的数据容器的key
 */
public class CustomMapKey<T> {

    final String keyName;

    final Class<T> type;

    public CustomMapKey(String keyName, Class<T> type) {
        this.keyName = keyName;
        this.type = type;
    }

    @Override
    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
//        result = prime * result + ((type == null) ? 0 : type.getName().hashCode());
//        return result;
        return Objects.hash(keyName, type.getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        CustomMapKey key = (CustomMapKey) obj;
        return keyName.equals(key.getKeyName()) && type.getName().equals(key.getType().getName());
    }

    public String getKeyName() {
        return keyName;
    }

    public Class<T> getType() {
        return type;
    }
}


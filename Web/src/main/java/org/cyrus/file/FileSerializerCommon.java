package org.cyrus.file;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface FileSerializerCommon<K> {

    Optional<FileSerializerCommon<?>> getRaw(K node);

    FileSerializerCommon<K> setRaw(K node, FileSerializerCommon<?> common);

    Optional<String> getString(K key);

    Optional<Integer> getInteger(K key);

    Optional<Double> getDouble(K key);

    FileSerializerCommon<K> set(K key, String value);

    FileSerializerCommon<K> set(K key, int value);

    FileSerializerCommon<K> set(K key, double value);

    FileSerializerCommon<K> setStringList(K key, Collection<String> value);

    FileSerializerCommon<K> setIntegerList(K key, Collection<Integer> value);

    FileSerializerCommon<K> setDoubleList(K key, Collection<Double> value);

    FileSerializerArray setRawMap(String key, Map<String, Object> map);

    FileSerializerArray setRawList(String key, Collection<Object> list);

    default Optional<FileSerializerObject> getNode(K node) {
        Optional<FileSerializerCommon<?>> opCommon = this.getRaw(node);
        if (opCommon.isEmpty()) {
            return Optional.empty();
        }
        if (opCommon.get() instanceof FileSerializerObject) {
            return opCommon.map(common -> (FileSerializerObject) common);
        }
        return Optional.empty();
    }

    default Optional<FileSerializerArray> getArray(K node) {
        Optional<FileSerializerCommon<?>> opCommon = this.getRaw(node);
        if (opCommon.isEmpty()) {
            return Optional.empty();
        }
        if (opCommon.get() instanceof FileSerializerArray) {
            return opCommon.map(common -> (FileSerializerArray) common);
        }
        return Optional.empty();
    }
}

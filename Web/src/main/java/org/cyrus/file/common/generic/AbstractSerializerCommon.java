package org.cyrus.file.common.generic;

import org.cyrus.file.FileSerializerArray;
import org.cyrus.file.FileSerializerCommon;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AbstractSerializerCommon<K> implements FileSerializerCommon<K> {

    private final Map<K, FileSerializerCommon<?>> childNodes = new HashMap<>();
    private final Map<K, Object> values = new HashMap<>();

    public AbstractSerializerCommon(Map<K, ? extends FileSerializerCommon<?>> childNodes, Map<K,
            ? extends Object> obj) {
        this.childNodes.putAll(childNodes);
        this.values.putAll(obj);
    }

    public Map<K, ? extends FileSerializerCommon<?>> getChildren() {
        return this.childNodes;
    }

    public Map<K, ? extends Object> getValues() {
        return this.values;
    }

    @Override
    public Optional<FileSerializerCommon<?>> getRaw(K node) {
        return Optional.ofNullable(this.childNodes.get(node));
    }

    @Override
    public FileSerializerCommon<K> setRaw(K node, FileSerializerCommon<?> common) {
        this.childNodes.put(node, common);
        return this;
    }

    private <T> Optional<T> getObject(K key) {
        return Optional.ofNullable((T) this.values.get(key));

    }

    @Override
    public Optional<String> getString(K key) {
        return this.getObject(key);
    }

    @Override
    public Optional<Integer> getInteger(K key) {
        return this.getObject(key);
    }

    @Override
    public Optional<Double> getDouble(K key) {
        return this.getObject(key);
    }

    private void setObject(K key, Object value) {
        if (this.values.containsKey(key)) {
            this.values.replace(key, value);
            return;
        }
        this.values.put(key, value);
    }

    @Override
    public FileSerializerCommon<K> set(K key, String value) {
        this.setObject(key, value);
        return this;
    }

    @Override
    public FileSerializerCommon<K> set(K key, int value) {
        this.setObject(key, value);
        return this;

    }

    @Override
    public FileSerializerCommon<K> set(K key, double value) {
        this.setObject(key, value);
        return this;

    }

    @Override
    public FileSerializerCommon<K> setStringList(K key, Collection<String> value) {
        this.setObject(key, value);
        return this;
    }

    @Override
    public FileSerializerCommon<K> setIntegerList(K key, Collection<Integer> value) {
        this.setObject(key, value);
        return this;
    }

    @Override
    public FileSerializerCommon<K> setDoubleList(K key, Collection<Double> value) {
        this.setObject(key, value);
        return this;

    }

    @Override
    public FileSerializerArray setRawMap(String key, Map<String, Object> map) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public FileSerializerArray setRawList(String key, Collection<Object> list) {
        throw new RuntimeException("Not implemented yet");
    }
}

package org.cyrus.file.common.generic;

import org.cyrus.file.FileSerializerCommon;
import org.cyrus.file.FileSerializerObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AbstractSerializerObject extends AbstractSerializerCommon<String> implements FileSerializerObject {

    public AbstractSerializerObject() {
        this(new HashMap<>(), new HashMap<>());
    }

    public AbstractSerializerObject(Map<String, ? extends FileSerializerCommon<?>> childNodes,
                                    Map<String, Object> obj) {
        super(childNodes, obj);
    }

    @Override
    public AbstractSerializerObject set(String key, String value) {
        return (AbstractSerializerObject) super.set(key, value);
    }

    @Override
    public AbstractSerializerObject set(String key, int value) {
        return (AbstractSerializerObject) super.set(key, value);
    }

    @Override
    public AbstractSerializerObject set(String key, double value) {
        return (AbstractSerializerObject) super.set(key, value);
    }

    @Override
    public AbstractSerializerObject setStringList(String key, Collection<String> value) {
        return (AbstractSerializerObject) super.setStringList(key, value);
    }

    @Override
    public AbstractSerializerObject setIntegerList(String key, Collection<Integer> value) {
        return (AbstractSerializerObject) super.setIntegerList(key, value);
    }

    @Override
    public AbstractSerializerObject setDoubleList(String key, Collection<Double> value) {
        return (AbstractSerializerObject) super.setDoubleList(key, value);
    }

}

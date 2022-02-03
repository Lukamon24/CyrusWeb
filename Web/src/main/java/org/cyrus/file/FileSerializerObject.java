package org.cyrus.file;

import java.util.Collection;

public interface FileSerializerObject extends FileSerializerCommon<String> {

    @Override
    FileSerializerObject set(String key, String value);

    @Override
    FileSerializerObject set(String key, int value);

    @Override
    FileSerializerObject set(String key, double value);

    @Override
    FileSerializerObject setStringList(String key, Collection<String> value);

    @Override
    FileSerializerObject setIntegerList(String key, Collection<Integer> value);

    @Override
    FileSerializerObject setDoubleList(String key, Collection<Double> value);
}

package org.cyrus.file.common.generic;

import org.cyrus.file.FileSerializerArray;
import org.cyrus.file.FileSerializerCommon;
import org.cyrus.webserver.request.serialize.DataSerialize;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AbstractSerializerArray extends AbstractSerializerCommon<Integer> implements FileSerializerArray {

    public AbstractSerializerArray() {
        this(new HashMap<>(), new HashMap<>());
    }

    public AbstractSerializerArray(Map<Integer, ? extends FileSerializerCommon<?>> childNodes,
                                   Map<Integer, Object> obj) {
        super(childNodes, obj);
    }

    public static <T> AbstractSerializerArray from(Collection<? extends T> values,
                                                   DataSerialize.Serialize<T, ? extends FileSerializerCommon<?>> format) {
        List<FileSerializerCommon<?>> list = values.stream().map(format::serialize).collect(Collectors.toList());
        AbstractSerializerArray array = new AbstractSerializerArray();
        for (int A = 0; A < list.size(); A++) {
            array.setRaw(A, list.get(A));
        }
        return array;
    }
}

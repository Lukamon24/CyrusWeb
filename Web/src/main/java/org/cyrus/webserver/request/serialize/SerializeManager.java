package org.cyrus.webserver.request.serialize;

import org.cyrus.file.FileSerializerCommon;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class SerializeManager {

    private final Collection<DataSerialize<?, ? extends FileSerializerCommon<?>>> serializers = new HashSet<>();

    public void register(DataSerialize<?, ? extends FileSerializerCommon<?>> serialize) {
        this.serializers.add(serialize);
    }

    public void register(Collection<? extends DataSerialize<?, ? extends FileSerializerCommon<?>>> serializes) {
        this.serializers.addAll(serializes);
    }

    public Collection<DataSerialize<?, ? extends FileSerializerCommon<?>>> getSerializers() {
        return Collections.unmodifiableCollection(this.serializers);
    }
}

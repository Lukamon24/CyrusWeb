package org.cyrus.webserver.request.serialize;

import org.cyrus.file.FileSerializerCommon;

import java.io.IOException;

public interface DataSerialize<T, S extends FileSerializerCommon<?>> {

    interface Serialize<T, S extends FileSerializerCommon<?>> extends DataSerialize<T, S> {

        S serialize(T value);

    }

    interface DeSerialize<T, S extends FileSerializerCommon<?>> extends DataSerialize<T, S> {
        T deserialize(S serializer) throws IOException;

    }


}

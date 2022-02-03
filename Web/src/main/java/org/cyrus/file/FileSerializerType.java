package org.cyrus.file;

import java.io.IOException;

public interface FileSerializerType<O extends FileSerializerObject, A extends FileSerializerArray> {

    O deserialize(String input) throws IOException;

    String serialize(O serialize);

}

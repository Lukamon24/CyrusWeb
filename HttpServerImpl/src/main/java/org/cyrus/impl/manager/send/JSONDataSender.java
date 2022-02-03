package org.cyrus.impl.manager.send;

import org.cyrus.file.FileSerializerTypes;
import org.cyrus.file.common.generic.AbstractSerializerObject;

public class JSONDataSender implements DataSender {

    private final AbstractSerializerObject json;

    public JSONDataSender(AbstractSerializerObject json) {
        this.json = json;
    }

    @Override
    public String asString() {
        return FileSerializerTypes.JSON.serialize(this.json);
    }

}

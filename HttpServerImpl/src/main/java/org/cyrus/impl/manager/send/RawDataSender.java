package org.cyrus.impl.manager.send;

public class RawDataSender implements DataSender {

    private final String data;

    public RawDataSender(String data) {
        this.data = data;
    }

    @Override
    public String asString() {
        return this.data;
    }
}

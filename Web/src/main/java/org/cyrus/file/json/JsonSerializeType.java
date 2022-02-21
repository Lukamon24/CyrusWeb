package org.cyrus.file.json;

import org.cyrus.file.FileSerializerCommon;
import org.cyrus.file.FileSerializerType;
import org.cyrus.file.common.generic.AbstractSerializerArray;
import org.cyrus.file.common.generic.AbstractSerializerCommon;
import org.cyrus.file.common.generic.AbstractSerializerObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonSerializeType implements FileSerializerType<AbstractSerializerObject, AbstractSerializerArray> {
    @Override
    public AbstractSerializerObject deserialize(String input) throws IOException {
        return (AbstractSerializerObject) this.deserializeObject(input.substring(1, input.length() - 1), false);
    }

    private AbstractSerializerCommon<?> deserializeObject(String input, boolean array) {
        Map<Object, FileSerializerCommon<?>> children = new HashMap<>();
        Map<Object, Object> childValues = new HashMap<>();


        input = input.replaceAll("\n", "");
        int start = -1;
        int end = 0;
        int braces = 0;
        String pre = "";
        for (int a = 0; a < input.length(); a++) {
            char at = input.charAt(a);
            if (start==-1) {
                pre = pre + at;
            }
            if (at=='{' || at=='[') {
                if (braces==0) {
                    start = (a + 1);
                }
                braces++;

            }
            if ((at=='}' || at==']') && start!=-1) {
                braces--;
                if (braces==0) {
                    String key = pre.substring(1);
                    FileSerializerCommon<?> common = this.deserializeObject(input.substring(start, a), (at!='}'));
                    if (array) {
                        children.put(children.size(), common);
                    } else {
                        children.put(key, common);
                    }
                    start = -1;
                    pre = "";
                    end = a;
                }
            }
        }
        input = input.substring(end);
        boolean inQuote = false;
        List<String> values = new ArrayList<>(2);
        String buffer = "";
        for (int a = 0; a < input.length(); a++) {
            char at = input.charAt(a);
            buffer = buffer + at;
            if (at=='"') {
                buffer = "";
                if (inQuote) {
                    values.add(input.substring(start, a) + "\"");
                } else {
                    start = a;
                }
                inQuote = !inQuote;

            }
            if (at==',') {
                if (!buffer.replaceAll(" ", "").equals(":")) {
                    System.out.println(buffer);
                }
                if (values.size()==2) {
                    String key = values.get(0);
                    if (key.startsWith("\"") && key.endsWith("\"")) {
                        key = key.substring(1, key.length() - 1);
                    }
                    String value = values.get(1);
                    values.clear();
                    inQuote = false;
                    if (value.startsWith("\"") && value.endsWith("\"")) {
                        childValues.put(key, value.substring(1, value.length() - 1));
                        continue;
                    }
                    try {
                        double doValue = Double.parseDouble(value);
                        if (((int) doValue)==doValue) {
                            childValues.put(key, (int) doValue);
                            continue;
                        }
                        childValues.put(key, doValue);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                if (values.size()==1) {
                    String value = values.get(0);
                    values.clear();
                    inQuote = false;
                    if (value.startsWith("\"") && value.endsWith("\"")) {
                        childValues.put(childValues.size(), value.substring(1, value.length() - 1));
                        continue;
                    }
                    try {
                        double doValue = Double.parseDouble(value);
                        if (((int) doValue)==doValue) {
                            childValues.put(childValues.size(), (int) doValue);
                            continue;
                        }
                        childValues.put(childValues.size(), doValue);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (!values.isEmpty()) {
            String bufferNoSpace = buffer.replaceAll(" ", "");
            if (!bufferNoSpace.equals(":") && !bufferNoSpace.isEmpty()) {
                values.add(bufferNoSpace.substring(1));
            }
            if (values.size()==2) {
                String key = values.get(0);
                String value = values.get(1);
                if (value.startsWith("\"") && value.endsWith("\"")) {
                    childValues.put(key.substring(1, key.length() - 1), value.substring(1, value.length() - 1));
                } else {
                    try {
                        double doValue = Double.parseDouble(value);
                        if (((int) doValue)==doValue) {
                            childValues.put(key, (int) doValue);
                        } else {
                            childValues.put(key, doValue);
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (values.size()==1) {
                String value = values.get(0);
                if (value.startsWith("\"") && value.endsWith("\"")) {
                    childValues.put(childValues.size(), value.substring(1, value.length() - 1));
                } else {
                    try {
                        double doValue = Double.parseDouble(value);
                        if (((int) doValue)==doValue) {
                            childValues.put(childValues.size(), (int) doValue);
                        } else {
                            childValues.put(childValues.size(), doValue);
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (array) {
            return new AbstractSerializerArray((Map<Integer, ? extends FileSerializerCommon<?>>) (Object) children,
                    (Map<Integer, Object>) (Object) childValues);
        }
        return new AbstractSerializerObject((Map<String, ? extends FileSerializerCommon<?>>) (Object) children,
                (Map<String, Object>) (Object) childValues);
    }

    private String serializeCommon(AbstractSerializerCommon<?> common, int tab) {
        String children = common.getChildren().entrySet().stream().map(entry -> {
            String value = this.serializeCommon((AbstractSerializerCommon<?>) entry.getValue(), tab + 1);
            if (entry.getKey() instanceof String) {
                return "\"" + entry.getKey() + "\": " + value;
            }
            return value;
        }).collect(Collectors.joining(",\n" + this.getTab(tab)));
        String values = common.getValues().entrySet().stream().map(entry -> {
            String value = entry.getValue().toString();
            String key = entry.getKey().toString();
            if (entry.getValue() instanceof String) {
                value = "\"" + value + "\"";
            }
            if (entry.getKey() instanceof String) {
                return "\"" + key + "\": " + value;
            }
            return value;
        }).collect(Collectors.joining(",\n" + this.getTab(tab + 1)));
        String jsonData = null;
        if (children.isBlank() && values.isBlank()) {
        } else if (children.isBlank()) {
            jsonData = this.getTab(tab + 1) + values;
        } else if (values.isBlank()) {
            jsonData = children;
        } else {
            jsonData = children + ",\n" + this.getTab(tab + 1) + values;
        }
        if (common instanceof AbstractSerializerArray) {
            return "[\n" + this.getTab(tab) + jsonData + "\n" + this.getTab(tab) + "]";
        }
        return "{\n" + this.getTab(tab) + jsonData + "\n" + this.getTab(tab) + "}";
    }

    @Override
    public String serialize(AbstractSerializerObject serialize) {
        return this.serializeCommon(serialize, 0);
    }

    private String getTab(int tab) {
        String tabs = "";
        for (int a = 0; a < tab; a++) {
            tabs = tabs + "  ";
        }
        return tabs;
    }
}

package ru.otus;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;

public class MyGson {

    public String toJson(Object object) throws IllegalAccessException {
        StringBuffer buffer = new StringBuffer();
        String serializedObject;
        if (object == null) {
            buffer.append(serializeNull());
        } else {
            serializedObject = serializeFieldObject(object);
            if (serializedObject == null) {
                serializeMixed(object, buffer);
            } else {
                buffer.append(serializedObject);
            }
        }
        return String.valueOf(buffer);
    }

    private String serializeNull() {
        return null;
    }

    private String serializeWithQuotes(Object object) {
        return String.format("\"%s\"", object);
    }

    private String serializeWithoutQuotes(Object object) {
        return String.valueOf(object);
    }

    private String serializePrimitives(Object object) {
        Class<?> clazz = object.getClass();
        if (clazz == Character.class) {
            return serializeWithQuotes(object);
        } else {
            return serializeWithoutQuotes(object);
        }
    }

    private String serializeCollection(Collection<?> collection) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (Object element : collection) {
            builder.append(serializeFieldObject(element));
            builder.append(",");
        }
        builder.deleteCharAt(builder.length() - 1).append("]");
        return builder.toString();
    }

    private String serializeArray(Object array) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < Array.getLength(array); i++) {
            builder.append(serializeFieldObject(Array.get(array, i)));
            builder.append(",");
        }
        builder.deleteCharAt(builder.length() - 1).append("]");
        return builder.toString();
    }

    private String serializeFieldObject(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        if (object instanceof String || object instanceof Character) {
            return serializeWithQuotes(object);
        } else if (clazz.isPrimitive() || object instanceof Number ||
                object instanceof Boolean) {
            return serializeWithoutQuotes(object);
        } else if (clazz.isArray()) {
            return serializeArray(object);
        } else if (object instanceof Collection<?>) {
            return serializeCollection((Collection<?>) object);
        } else {
            return null;
        }
    }

    private void serializeMixed(Object object, StringBuffer buffer) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        String key;
        Object value;
        buffer.append("{");
        for (Field field : fields
        ) {
            field.setAccessible(true);
            if (Modifier.isTransient(field.getModifiers())) continue;
            if (Modifier.isFinal(field.getModifiers())) continue;
            key = field.getName();
            value = field.get(object);
            if (value != null) {
                buffer.append(serializeFieldObject(key));
                buffer.append(":");
                buffer.append(serializeFieldObject(value));
                buffer.append(",");
            }
        }
        if (buffer.toString().endsWith(",")) {
            buffer.deleteCharAt(buffer.length() - 1).append("}");
        } else buffer.append("}");

    }

}
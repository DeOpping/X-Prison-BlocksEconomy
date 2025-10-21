package io.github.deopping.xprisonblockseconomy.utils.configuration;

import java.util.*;

public final class JsonConfiguration {

    private Map<String, Object> map;

    public JsonConfiguration() {
        map = new HashMap<>();
    }

    void setMap(final Map<String, Object> newMap) {
        map = newMap;
    }

    Map<String, Object> getMap() {
        return map;
    }

    public void set(final String path, final Object object) {
        if (object == null) {
            map.remove(path);
            return;
        }

        map.put(path, object);
    }

    private <T> T get(final String path, final T def, final Class<T> clazz) {
        final Object object = map.getOrDefault(path, null);

        if (!clazz.isInstance(object)) {
            return def;
        }

        return clazz.cast(object);
    }

    public boolean has(final String key) {
        return map.containsKey(key);
    }

    public Object get(final String path, final Object def) {
        return map.getOrDefault(path, def);
    }

    public Object get(final String path) {
        return get(path, null);
    }

    public String getString(final String path, final String def) {
        return get(path, def, String.class);
    }

    public String getString(final String path) {
        return getString(path, null);
    }

    public boolean getBoolean(final String path, final boolean def) {
        return get(path, def, Boolean.class);
    }

    public boolean getBoolean(final String path) {
        return getBoolean(path, false);
    }

    public int getByte(final String path, final byte def) {
        return get(path, def, Byte.class);
    }

    public int getByte(final String path) {
        return getByte(path, (byte) 0);
    }

    public int getInteger(final String path, final int def) {
        return get(path, def, Integer.class);
    }

    public int getInteger(final String path) {
        return getInteger(path, 0);
    }

    public long getLong(final String path, final long def) {
        return get(path, def, Long.class);
    }

    public long getLong(final String path) {
        return getLong(path, 0L);
    }

    public float getFloat(final String path, final float def) {
        return get(path, def, Float.class);
    }

    public float getFloat(final String path) {
        return getFloat(path, 0f);
    }

    public double getDouble(final String path, final double def) {
        return get(path, def, Double.class);
    }

    public double getDouble(final String path) {
        return getDouble(path, 0d);
    }

    public List<String> getStringList(final String path, final List<String> def) {
        final Object object = map.getOrDefault(path, null);
        if (object == null) {
            return def;
        }

        if (object instanceof List<?>) {
            final List<?> list = (List<?>) object;
            final List<String> stringList = new ArrayList<>();
            list.forEach(v -> stringList.add(v.toString()));
            return stringList;
        }

        return Collections.singletonList(object.toString());
    }

    public List<String> getStringList(final String path) {
        return getStringList(path, null);
    }

    public Set<String> getConfigurationSection(final String parent) {
        final Set<String> set = new HashSet<>();
        final String parentKey = parent.endsWith(".") ? parent : parent + ".";

        map.keySet().forEach(key -> {
            if (!key.startsWith(parentKey)) {
                return;
            }

            final String sub = key.substring(parentKey.length());
            final String[] split = sub.split("\\.");

            if (split.length > 0) {
                set.add(split[0]);
                return;
            }

            set.add(sub);
        });

        return set;
    }

    public void clear() {
        map.clear();
    }

}

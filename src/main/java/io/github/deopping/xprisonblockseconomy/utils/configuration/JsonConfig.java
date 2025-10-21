package io.github.deopping.xprisonblockseconomy.utils.configuration;

import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public final class JsonConfig {

    private final String filePath;
    private final JsonConfiguration jsonConfiguration;
    private File file;

    public JsonConfig(final String filePath) {
        this.filePath = filePath.endsWith(".json") ? filePath : filePath + ".json";
        jsonConfiguration = new JsonConfiguration();
        save();
        reload();
    }

    private File file() {
        return new File(ConfigUtils.getDataFolder(), filePath);
    }

    public File getFile() {
        return file;
    }

    public JsonConfiguration options() {
        return jsonConfiguration;
    }

    public void save() {
        if (file == null) {
            file = file();
        }

        if (file.exists()) {
            return;
        }

        final InputStream inputStream = ConfigUtils.getResource(filePath);
        if (inputStream == null) {
            return;
        }

        final boolean replace = false;
        final boolean logIfExists = false;
        ConfigUtils.saveResource(filePath, replace, logIfExists);
    }

    public void saveChanges(final boolean truncate) {
        final String jsonString = JsonUtils.toJsonString(jsonConfiguration.getMap());

        //noinspection ResultOfMethodCallIgnored
        file.delete();

        try {
            if (truncate) {
                Files.write(file.toPath(), jsonString.getBytes());
                return;
            }

            Files.write(
                    file.toPath(),
                    jsonString.getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE
            );
        }
        catch (IOException e) {
            Bukkit.getLogger().warning(ThrowableUtils.getStackTrace(e));
        }
    }

    public void saveChanges() {
        saveChanges(false);
    }

    public void reload() {
        try {
            jsonConfiguration.setMap(JsonUtils.toMap(
                    Files.readString(file().toPath(), StandardCharsets.UTF_8))
            );
        }
        catch (IOException e) {
            Bukkit.getLogger().warning(ThrowableUtils.getStackTrace(e));
        }
    }

}

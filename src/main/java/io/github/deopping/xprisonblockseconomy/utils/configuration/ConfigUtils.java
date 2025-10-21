package io.github.deopping.xprisonblockseconomy.utils.configuration;

import io.github.deopping.xprisonblockseconomy.BlocksEconomyAddon;
import org.bukkit.Bukkit;

import java.io.*;
import java.net.URL;

public final class ConfigUtils {

    private static ConfigUtils instance;

    public static ConfigUtils getInstance() {
        if (instance == null) {
            instance = new ConfigUtils();
        }

        return instance;
    }

    private final File dataFolder;

    private ConfigUtils() {
        this.dataFolder = new File(Bukkit.getPluginManager().getPlugin("X-Prison").getDataFolder(), "addons-data/" + BlocksEconomyAddon.ADDON_NAME);
    }

    public static File getDataFolder() {
        if (!getInstance().dataFolder.exists()) {
            instance.dataFolder.mkdirs();
        }

        return instance.dataFolder;
    }

    public static InputStream getResource(final String filePath, final boolean printStackTrace) {
        if (!getInstance().dataFolder.exists()) {
            instance.dataFolder.mkdirs();
        }

        final URL resource = BlocksEconomyAddon.class.getResource("/" + filePath);
        if (resource == null) {
            return null;
        }

        try {
            return resource.openStream();
        }
        catch (IOException e) {
            if (printStackTrace) {
                Bukkit.getLogger().warning(ThrowableUtils.getStackTrace(e));
            }
        }

        return null;
    }

    public static InputStream getResource(final String filePath) {
        return getResource(filePath, true);
    }

    public static void saveResource(String resourcePath, final boolean replace, final boolean logIfExists) {
        if (!getInstance().dataFolder.exists()) {
            instance.dataFolder.mkdirs();
        }

        if (resourcePath.isEmpty()) {
            return;
        }

        resourcePath = resourcePath.replace('\\', '/');
        final InputStream resource = getResource(resourcePath);
        if (resource == null) {
            Bukkit.getLogger().warning(BlocksEconomyAddon.ADDON_LOG_PREFIX + "No embedded resource file: '" + resourcePath + "'");
            return;
        }

        final int lastIndex = resourcePath.lastIndexOf(47);
        final File outDir = new File(instance.dataFolder, resourcePath.substring(0, Math.max(lastIndex, 0)));
        if (!outDir.exists()) {
            outDir.mkdirs();
        }

        final File outFile = new File(instance.dataFolder, resourcePath);
        if (outFile.exists() && !replace) {
            if (logIfExists) {
                Bukkit.getLogger().warning(
                        BlocksEconomyAddon.ADDON_LOG_PREFIX +
                                "Could not save " + outFile.getName() + " to " +
                                outFile + " because " + outFile.getName() +
                                " already exists!"
                );
            }
            return;
        }

        try {
            final OutputStream outputStream = new FileOutputStream(outFile);
            byte[] buffer = new byte[1024];

            int length;
            while ((length = resource.read(buffer)) > 0) {
               outputStream.write(buffer, 0, length);
            }

            outputStream.close();
            resource.close();
        }
        catch (IOException e) {
            Bukkit.getLogger().warning(ThrowableUtils.getStackTrace(e));
        }
    }

    public static void saveResource(final String resourcePath, final boolean replace) {
        saveResource(resourcePath, replace, true);
    }

}

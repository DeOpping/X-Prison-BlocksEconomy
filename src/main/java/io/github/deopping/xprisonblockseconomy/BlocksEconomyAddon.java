package io.github.deopping.xprisonblockseconomy;

import dev.drawethree.xprison.api.XPrisonAPI;
import dev.drawethree.xprison.api.addons.XPrisonAddon;
import io.github.deopping.xprisonblockseconomy.currencies.BlocksCurrency;
import io.github.deopping.xprisonblockseconomy.utils.configuration.JsonConfig;

public final class BlocksEconomyAddon implements XPrisonAddon {

    public static final String ADDON_NAME;
    public static final String ADDON_LOG_PREFIX;
    private static BlocksEconomyAddon instance;

    static {
        ADDON_NAME = "BlocksEconomy";
        ADDON_LOG_PREFIX = "[" + ADDON_NAME + "Addon] ";
    }

    public static BlocksEconomyAddon getInstance() {
        return instance;
    }

    private XPrisonAPI api;
    private JsonConfig config;
    private BlocksCurrency currency;

    @Override
    public void onEnable() {
        instance = this;
        api = XPrisonAPI.getInstance();

        config = new JsonConfig("config");

        currency = new BlocksCurrency();
        api.getCurrencyApi().registerCurrency(currency);
    }

    @Override
    public void onDisable() {
        api.getCurrencyApi().registerCurrency(currency);
    }

    public XPrisonAPI getApi() {
        return api;
    }

    public JsonConfig getConfig() {
        return config;
    }

}

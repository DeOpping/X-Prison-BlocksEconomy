package io.github.deopping.xprisonblockseconomy.currencies;

import dev.drawethree.xprison.api.currency.model.XPrisonCurrency;
import dev.drawethree.xprison.api.currency.model.XPrisonCurrencyHandler;
import io.github.deopping.xprisonblockseconomy.BlocksEconomyAddon;
import io.github.deopping.xprisonblockseconomy.currencies.handlers.BlocksCurrencyHandler;
import io.github.deopping.xprisonblockseconomy.utils.configuration.JsonConfig;

public class BlocksCurrency implements XPrisonCurrency {

    private final BlocksCurrencyHandler handler;
    private final String display;
    private final String prefix;
    private final String suffix;

    public BlocksCurrency() {
        handler = new BlocksCurrencyHandler();

        final JsonConfig config = BlocksEconomyAddon.getInstance().getConfig();
        display = config.options().getString("currency.display", "Blocks");
        prefix = config.options().getString("currency.prefix");
        suffix = config.options().getString("currency.suffix");
    }

    @Override
    public String getName() {
        return "blocks";
    }

    @Override
    public double getMaxAmount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public String getDisplayName() {
        return display;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public String getSuffix() {
        return suffix;
    }

    @Override
    public String format(double v) {
        return String.valueOf(v);
    }

    @Override
    public XPrisonCurrencyHandler getHandler() {
        return handler;
    }

}

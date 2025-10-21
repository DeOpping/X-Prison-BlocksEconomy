package io.github.deopping.xprisonblockseconomy.currencies.handlers;

import dev.drawethree.xprison.api.currency.enums.LostCause;
import dev.drawethree.xprison.api.currency.enums.ReceiveCause;
import dev.drawethree.xprison.api.currency.model.XPrisonCurrencyHandler;
import dev.drawethree.xprison.api.miningstats.XPrisonMiningStatsAPI;
import io.github.deopping.xprisonblockseconomy.BlocksEconomyAddon;
import org.bukkit.OfflinePlayer;

public class BlocksCurrencyHandler implements XPrisonCurrencyHandler {

    private final XPrisonMiningStatsAPI miningStatsApi;

    public BlocksCurrencyHandler() {
        miningStatsApi = BlocksEconomyAddon.getInstance().getApi().getMiningStatsApi();
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        if (!offlinePlayer.isOnline()) {
            return 0.0d;
        }

        return miningStatsApi.getStats(offlinePlayer.getPlayer()).getBlocksMined();
    }

    @Override
    public boolean setBalance(OfflinePlayer offlinePlayer, double v) {
        return true;
    }

    @Override
    public boolean addBalance(OfflinePlayer offlinePlayer, double v, ReceiveCause receiveCause) {
        return true;
    }

    @Override
    public boolean removeBalance(OfflinePlayer offlinePlayer, double v, LostCause lostCause) {
        return true;
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double v) {
        if (!offlinePlayer.isOnline()) {
            return false;
        }

        int blocksMined = miningStatsApi.getStats(offlinePlayer.getPlayer()).getBlocksMined();
        return blocksMined >= Math.min(v, Integer.MAX_VALUE);
    }

}

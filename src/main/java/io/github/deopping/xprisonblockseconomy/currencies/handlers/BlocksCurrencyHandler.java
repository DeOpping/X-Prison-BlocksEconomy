package io.github.deopping.xprisonblockseconomy.currencies.handlers;

import dev.drawethree.xprison.api.currency.enums.LostCause;
import dev.drawethree.xprison.api.currency.enums.ReceiveCause;
import dev.drawethree.xprison.api.currency.model.XPrisonCurrencyHandler;
import dev.drawethree.xprison.blocks.XPrisonBlocks;
import org.bukkit.OfflinePlayer;

public class BlocksCurrencyHandler implements XPrisonCurrencyHandler {

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        if (!XPrisonBlocks.getInstance().isEnabled()) {
            return 0.0d;
        }

        return XPrisonBlocks.getInstance().getBlocksManager().getPlayerBrokenBlocks(offlinePlayer);
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
        final long blocksMined = XPrisonBlocks.getInstance().getBlocksManager().getPlayerBrokenBlocks(offlinePlayer);
        return blocksMined >= Math.min(v, Long.MAX_VALUE);
    }

}

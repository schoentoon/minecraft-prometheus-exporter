package de.sldk.mc.metrics;

import io.prometheus.client.Gauge;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlayerPing extends PlayerMetric {

    private static final Gauge PLAYERS_WITH_NAMES = Gauge.build()
            .name(prefix("player_ping"))
            .help("The ping time to the player")
            .labelNames("name", "uid", "brand")
            .create();

    public PlayerPing(Plugin plugin) {
        super(plugin, PLAYERS_WITH_NAMES);
    }

    @Override
    protected void clear() {
        PLAYERS_WITH_NAMES.clear();
    }

    @Override
    public void collect(OfflinePlayer player) {
        final Player onlinePlayer = player.getPlayer();
        if (onlinePlayer != null) {
            PLAYERS_WITH_NAMES.labels(getNameOrUid(player), getUid(player), getClientBrandName(onlinePlayer)).set(onlinePlayer.spigot().getPing());
        }
    }

    protected String getClientBrandName(Player player) {
        String brand = player.getClientBrandName();
        if (brand == null) {
            return "";
        }
        return brand;
    }
}

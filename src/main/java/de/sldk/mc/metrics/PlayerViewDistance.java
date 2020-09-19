package de.sldk.mc.metrics;

import io.prometheus.client.Gauge;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlayerViewDistance extends PlayerMetric {

    private static final Gauge PLAYERS_WITH_NAMES = Gauge.build()
            .name(prefix("player_view_distance"))
            .help("The view distance this player has configured")
            .labelNames("name", "uid")
            .create();

    public PlayerViewDistance(Plugin plugin) {
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
            PLAYERS_WITH_NAMES.labels(getNameOrUid(player), getUid(player)).set(onlinePlayer.getClientViewDistance());
        }
    }
}

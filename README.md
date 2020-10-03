This is a fork of https://github.com/sladkoff/minecraft-prometheus-exporter
Major difference is that it has a hard dependency on paper, rather than just bukkit.
Due to this I also was able to add a few extra metrics that would not have been possible to add through an extra plugin.
This includes the ping of all online players, the brand of their client and the view distance they have configured on their client (can come in handy when tuning your view distance).
These could be disabled using the `players_ping` and `player_view_distance` in the config file as indicated by the README of the original project.
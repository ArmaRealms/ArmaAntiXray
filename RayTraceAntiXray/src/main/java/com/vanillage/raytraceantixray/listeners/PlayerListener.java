package com.vanillage.raytraceantixray.listeners;

import com.vanillage.raytraceantixray.RayTraceAntiXray;
import com.vanillage.raytraceantixray.data.PlayerData;
import com.vanillage.raytraceantixray.tasks.UpdateBukkitRunnable;
import com.vanillage.raytraceantixray.util.BukkitUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.logging.Level;

public final class PlayerListener implements Listener {
    private final RayTraceAntiXray plugin;

    public PlayerListener(RayTraceAntiXray plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        try {
            if (plugin.tryCreatePlayerDataFor(player) == null)
                return;

            if (BukkitUtil.IS_FOLIA) {
                event.getPlayer().getScheduler().runAtFixedRate(plugin, new UpdateBukkitRunnable(plugin, event.getPlayer()), null, 1L, plugin.getUpdateTicks());
            }
        } catch (Throwable t) {
            player.kick(Component.text("RayTraceAntiXray encountered an error for your connection, please contact server administrators: " + t.getMessage()));
            if (t instanceof Exception) {
                plugin.getLogger().log(Level.SEVERE, "Exception raised while creating data for \"" + player + "\" during player join", t);
            } else {
                throw t;
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        PlayerData data = plugin.getPlayerData().get(event.getPlayer().getUniqueId());
        if (data != null) {
            data.getPacketHandler().detach();
            plugin.getPlayerData().remove(event.getPlayer().getUniqueId(), data);
        }
    }

}

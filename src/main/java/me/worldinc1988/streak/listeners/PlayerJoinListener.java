package me.worldinc1988.streak.listeners;

import me.worldinc1988.streak.StreakPlugin;
import me.worldinc1988.streak.data.StreakData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerJoinListener implements Listener {
    
    private final StreakPlugin plugin;
    private final StreakData streakData;
    private final Map<UUID, LocalDate> lastLoginDate = new HashMap<>();
    
    public PlayerJoinListener(StreakPlugin plugin, StreakData streakData) {
        this.plugin = plugin;
        this.streakData = streakData;
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String playerName = event.getPlayer().getName();
        UUID playerUUID = event.getPlayer().getUniqueId();
        
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        LocalDate lastLogin = lastLoginDate.get(playerUUID);
        
        if (lastLogin == null) {
            // First join, increment streak
            streakData.incrementStreak(playerName);
            plugin.getLogger().info(playerName + " streak: " + streakData.getStreak(playerName));
        } else if (lastLogin.isBefore(today)) {
            // Last login was before today, increment streak
            streakData.incrementStreak(playerName);
            plugin.getLogger().info(playerName + " streak: " + streakData.getStreak(playerName));
        } else if (lastLogin.equals(today)) {
            // Already logged in today, do nothing
            plugin.getLogger().info(playerName + " already logged in today");
        }
        
        lastLoginDate.put(playerUUID, today);
    }
}
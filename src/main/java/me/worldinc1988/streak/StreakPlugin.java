package me.worldinc1988.streak;

import org.bukkit.plugin.java.JavaPlugin;

public class StreakPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("StreakPlugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("StreakPlugin has been disabled!");
    }
}
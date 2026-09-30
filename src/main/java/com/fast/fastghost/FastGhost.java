package com.fast.fastghost;

import org.bukkit.plugin.java.JavaPlugin;

public class FastGhost extends JavaPlugin {

    private GhostHitManager ghostHitManager;
    private Stats stats;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        
        this.stats = new Stats();
        this.ghostHitManager = new GhostHitManager(this);
        
        GhostHitListener ghostHitListener = new GhostHitListener(this);
        ghostHitListener.registerPacketListener();

        if (getCommand("fastghost") != null) {
            getCommand("fastghost").setExecutor(new FastGhostCommand(this));
        }

        getLogger().info("FastGhost has been enabled successfully!");
    }

    @Override
    public void onDisable() {
        getLogger().info("FastGhost has been disabled.");
    }

    public GhostHitManager getGhostHitManager() {
        return ghostHitManager;
    }

    public Stats getStats() {
        return stats;
    }
}

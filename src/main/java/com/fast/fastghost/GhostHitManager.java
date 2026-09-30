package com.fast.fastghost;

import org.bukkit.FluidCollisionMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;

public class GhostHitManager {

    private final FastGhost plugin;

    public GhostHitManager(FastGhost plugin) {
        this.plugin = plugin;
    }

    public void processSwing(Player attacker) {
        double reach = plugin.getConfig().getDouble("reach-distance", 4.0);
        
        plugin.getServer().getScheduler().runTask(plugin, () -> {
            RayTraceResult result = attacker.getWorld().rayTrace(
                attacker.getEyeLocation(),
                attacker.getEyeLocation().getDirection(),
                reach,
                FluidCollisionMode.NEVER,
                true,
                0.4,
                entity -> !entity.equals(attacker) && entity instanceof LivingEntity
            );

            if (result != null && result.getHitEntity() != null) {
                Entity hitEntity = result.getHitEntity();
                if (hitEntity instanceof LivingEntity) {
                    LivingEntity target = (LivingEntity) hitEntity;
                    
                    target.setNoDamageTicks(0);
                    target.damage(1.0, attacker);
                    
                    plugin.getStats().incrementProcessedHits();
                }
            }
            plugin.getStats().incrementTotalSwings();
        });
    }
}

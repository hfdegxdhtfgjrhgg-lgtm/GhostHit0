package com.fast.fastghost;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.entity.Player;

public class GhostHitListener {

    private final FastGhost plugin;

    public GhostHitListener(FastGhost plugin) {
        this.plugin = plugin;
    }

    public void registerPacketListener() {
        ProtocolLibrary.getProtocolManager().addPacketListener(
            new PacketAdapter(plugin, PacketType.Play.Client.ARM_ANIMATION) {
                @Override
                public void onPacketReceiving(PacketEvent event) {
                    Player player = event.getPlayer();
                    if (player != null && player.isOnline()) {
                        plugin.getGhostHitManager().processSwing(player);
                    }
                }
            }
        );
    }
}

package com.fast.fastghost;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FastGhostCommand implements CommandExecutor {

    private final FastGhost plugin;

    public FastGhostCommand(FastGhost plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("fastghost.admin")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "[FastGhost] Configuration reloaded successfully!");
            return true;
        }

        if (args.length > 0 && args[0].equalsIgnoreCase("stats")) {
            long total = plugin.getStats().getTotalSwings();
            long processed = plugin.getStats().getProcessedHits();
            sender.sendMessage(ChatColor.GOLD + "=== FastGhost Stats ===");
            sender.sendMessage(ChatColor.YELLOW + "Total Swings: " + ChatColor.WHITE + total);
            sender.sendMessage(ChatColor.YELLOW + "Hits Processed: " + ChatColor.WHITE + processed);
            return true;
        }

        sender.sendMessage(ChatColor.AQUA + "FastGhost v" + plugin.getDescription().getVersion() + " - Usage: /fastghost [reload/stats]");
        return true;
    }
}

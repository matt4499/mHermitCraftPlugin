package me.matt4499.hermitcraft;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.minecraft.server.v1_16_R1.MinecraftServer;

public class AfkStuff implements Listener,CommandExecutor {
	
	HashMap<Player, Boolean> afkmap = new HashMap<Player, Boolean>();

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if (!afkmap.containsKey(event.getPlayer())) {
			afkmap.put(event.getPlayer(), false);
		}
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		afkmap.remove(event.getPlayer());
	}

	public void setAfk(Player player, Boolean bool) {
		afkmap.put(player, bool);
		if(bool == true) {
			Bukkit.broadcastMessage(player.getName() + " is now afk");
		} else {
			Bukkit.broadcastMessage(player.getName() + " is no longer afk");
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		setAfk((Player)sender, !(afkmap.get(sender)));
		return true;
	}
	
	public void startTimer() {
		Runtime r = Runtime.getRuntime();
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
		    @Override
		    public void run() {
		    	if(afkmap.isEmpty()) { return; }
		    	afkmap.forEach((player,bool)->{ 
		    		CraftPlayer user = (CraftPlayer) player;
		    		int ping = user.getHandle().ping;
		    		int deaths = player.getStatistic(Statistic.DEATHS);
		    		if (bool == true) {
						player.setPlayerListName(player.getDisplayName() + " P: " + ping + " " + ChatColor.RED + " *AFK*");
					} else {
						player.setPlayerListName(player.getDisplayName() + ChatColor.YELLOW + " P: " + ping + ChatColor.RED + " D: " + deaths + " ");
					}
		    		player.setPlayerListFooter("Ram: " + (r.freeMemory() / 1048576 + "MB / " + r.totalMemory() / 1048576 + "MB") + "\n" + "TPS: " + Math.floor(MinecraftServer.getServer().recentTps[0]));
		    		player.setPlayerListHeader("Join HermitCraft: discord.gg/vsweS9k");
		    	});    
		    }
		}, 0L, 100L);
	}

}

package me.matt4499.hermitcraft;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static Plugin plugin; //declaring a public static so we can use the instance of Main in other classes
	
	@Override // Using @Override annotation while overriding a method is considered as a best practice for coding in java
	public void onEnable() { //Called when the plugin is enabled/loaded
		plugin = this;
		Bukkit.getServer().getPluginManager().registerEvents(new MobKillListener(), this); //Register our event with bukkit so it fires
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[mPlugin] Successfully registered MobKillListener for: Mob Heads"); //Print a neat message to console	
		Bukkit.getServer().getPluginManager().registerEvents(new BedEnterListener(), this); //Register our event with bukkit so it fires
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[mPlugin] Successfully registered BedEnterListener for: One Player Sleep"); //Print a neat message to console
		Bukkit.getServer().getPluginManager().registerEvents(new EndermenListener(), this); //Register our event with bukkit so it fires
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[mPlugin] Successfully registered EndermenListener for: Anti Endermen Grief"); //Print a neat message to console
		Bukkit.getServer().getPluginManager().registerEvents(new ShulkerDeathListener(), this); //Register our event with bukkit so it fires
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[mPlugin] Successfully registered ShulkerDeathListener for: Double Shell on Shulker Death"); //Print a neat message to console

//		Bukkit.getServer().getPluginManager().registerEvents(new NametagListener(), this); //Register our event with bukkit so it fires
//		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[mPlugin] Successfully registered NametagListener for: Silenced Mobs"); //Print a neat message to console
//      ^ disabled due to not working yet	
		Bukkit.getServer().getWorld("world").setGameRule(GameRule.DO_FIRE_TICK, false); //specific gamerule in hermit craft
		Bukkit.getServer().getWorld("world").getWorldBorder().setCenter(0, 0); //center the world border
		Bukkit.getServer().getWorld("world").getWorldBorder().setSize(12000); //set to our own size we wanted
	}
	
	@Override // Using @Override annotation while overriding a method is considered as a best practice for coding in java
	public void onDisable() { //Called when the plugin is disabled/unloaded
		EntityDeathEvent.getHandlerList().unregister(this); //Gets all handlers listening to EntityDeathEvent, and removes us from that list
		PlayerBedEnterEvent.getHandlerList().unregister(this); // same as that ^
		EntityChangeBlockEvent.getHandlerList().unregister(this); // same as that ^
//		PlayerInteractEntityEvent.getHandlerList().unregister(this); // same as that ^
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[mPlugin] Successfully unregistered events! Bye bye!"); //Print a neat message to console
	}
}

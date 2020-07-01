package me.matt4499.hermitcraft;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
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
	}
	
	@Override // Using @Override annotation while overriding a method is considered as a best practice for coding in java
	public void onDisable() { //Called when the plugin is disabled/unloaded
		EntityDeathEvent.getHandlerList().unregister(this); //Gets all handlers listening to EntityDeathEvent, and removes us from that list
		PlayerBedEnterEvent.getHandlerList().unregister(this); // same as that ^ 
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[mPlugin] Successfully unregistered events! Bye bye!"); //Print a neat message to console
	}
}

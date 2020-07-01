package me.matt4499.hermitcraft;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class MobKillListener implements Listener { //The class must implement Listener to listen for events
	
	@EventHandler(ignoreCancelled = true) //An annotation to mark method as being an event handler method, if the event was cancelled for some reason, ignore it
	public void onMobDeath(EntityDeathEvent event) { //Event thrown when a LivingEntity is killed
		if(!(event.getEntity().getKiller() instanceof Player)) { //If the entity killed is not a player
			return; //stop running further code
		}
		if(event.getEntity() == null || event.getEntityType() == null){ //If the entity killed doesn't exist for some reason
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[mPlugin] An entity died but was NULL or no type!"); //send a nifty message
			return; //stop running code or stuff will error badly
		}
		Bukkit.broadcastMessage(ChatColor.RED + "[Debug] " + event.getEntityType().toString() + " was killed by " + event.getEntity().getKiller().getDisplayName()); //otherwise print a message in chat
		switch(event.getEntityType()) {
			case CREEPER:
				ItemStack CreeperHead = new ItemStack(Material.CREEPER_HEAD);
				event.getDrops().add(CreeperHead);
			case ZOMBIE:
				ItemStack ZombieHead = new ItemStack(Material.ZOMBIE_HEAD);
				event.getDrops().add(ZombieHead);
			case SKELETON:
				ItemStack SkeletonHead = new ItemStack(Material.SKELETON_SKULL);
				event.getDrops().add(SkeletonHead);
			case ENDER_DRAGON:
				ItemStack DragonHead = new ItemStack(Material.DRAGON_HEAD);
				event.getDrops().add(DragonHead);
				ItemStack ElytraItem = new ItemStack(Material.ELYTRA);
				event.getDrops().add(ElytraItem);
			default:
				Bukkit.getServer().getConsoleSender().sendMessage("[mHermitcraft] An unsupported mob has been killed.");
				return;
		}
	}
}

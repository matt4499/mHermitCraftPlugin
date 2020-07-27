package me.matt4499.hermitcraft;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class NametagListener implements Listener { //credit to JoelGodOfwar for some of this code, i just severely editted it
	
	@EventHandler //declare an event handler method
	public void onNametag(PlayerInteractEntityEvent event) { //listen for PlayerInteractEvent
		if(!(event.getPlayer() instanceof Player)) { //if for some reason a player didnt call this event
			Bukkit.getServer().getConsoleSender().sendMessage("player was not detected");
			return; //dont run further code
		}
		ItemStack nametag = new ItemStack(Material.NAME_TAG);
		if(event.getPlayer().getInventory().getItemInMainHand().equals(nametag)) {
			Bukkit.getServer().getConsoleSender().sendMessage("player was not holding name tag");
			return; //if the player isnt holding a nametag, dont run further code
		}
			Player player = event.getPlayer(); //set player to the event player
			Material iteminhand = player.getInventory().getItemInMainHand().getType(); //get the item in their hand
			Material iteminoffhand = player.getInventory().getItemInOffHand().getType();//and offhand
			String name = null; //new empty variable called name, for storing the name of the name tag
			String hand = null; //new empty varible called hand, to store whether or not the nametag is in off or main hand
			if(iteminhand.equals(Material.NAME_TAG)){ //if they have a nametag in main hand
				name = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName(); //set name to the nametag name
				hand = "main"; //set hand to main
			}
			if(iteminoffhand.equals(Material.NAME_TAG)){//if they have a nametag in off hand
				name = player.getInventory().getItemInOffHand().getItemMeta().getDisplayName();//set name to the nametag name
				hand = "off";//set hand to off
			}
			if(name == null) {
				Bukkit.getServer().getConsoleSender().sendMessage("name is null");
				return;
			}
			if(name.toLowerCase() == "silent" || name.toLowerCase() == "silence") { //if the nametag was named silent or silence
				Bukkit.getServer().getConsoleSender().sendMessage("silent/silence nametag detected");
				event.getRightClicked().setSilent(true);
				event.getRightClicked().setCustomName("Silenced Mob");
				event.setCancelled(true); //cancel the event so the actual name of the nametag isnt used
				Bukkit.getServer().getConsoleSender().sendMessage("Mob should be silent: " + event.getRightClicked().getType().toString());
				if(hand == "main") { //if they were holding the nametag in the main hand
					player.getInventory().remove(player.getInventory().getItemInMainHand()); //remove it
				}
				if(hand == "off") { //if they were holding the nametag in the off hand
					player.getInventory().remove(player.getInventory().getItemInOffHand()); //remove it
				}
				//Right here is where you would want to "silence" event.getRightClicked()
				//i tried **several** different things and none worked on paper spigot 1.16.1
			}
			
	}

}

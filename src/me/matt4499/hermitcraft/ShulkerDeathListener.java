package me.matt4499.hermitcraft;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class ShulkerDeathListener implements Listener {

	@EventHandler(ignoreCancelled = true)
	public void onShulkerDeath(EntityDeathEvent event) {
		if (event.getEntity().getType() == EntityType.SHULKER) {
			event.getDrops().clear();
			ItemStack shell = new ItemStack(Material.SHULKER_SHELL);
			event.getDrops().add(shell);
			event.getDrops().add(shell); //add 2 shells when a shulker is killed
		}
	}
}

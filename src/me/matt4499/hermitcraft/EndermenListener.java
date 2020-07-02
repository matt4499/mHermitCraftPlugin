package me.matt4499.hermitcraft;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class EndermenListener implements Listener {
	@EventHandler(ignoreCancelled = true)
	public void endermanChangeBlock(EntityChangeBlockEvent event) {
		if (event.getEntity().getType() == EntityType.ENDERMAN) {
			event.setCancelled(true); //anti endermen grief
		}
	}
}

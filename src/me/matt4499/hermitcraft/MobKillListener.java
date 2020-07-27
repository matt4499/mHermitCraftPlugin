package me.matt4499.hermitcraft;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

@SuppressWarnings("unused")
public class MobKillListener implements Listener { // The class must implement Listener to listen for events

	@EventHandler(ignoreCancelled = true) // An annotation to mark method as being an event handler method, if the event
											// was cancelled for some reason, ignore it
	public void onMobDeath(EntityDeathEvent event) { // Event thrown when a LivingEntity is killed
		if (!(event.getEntity().getKiller() instanceof Player)) { // If the entity killed is not a player
			return; // stop running further code
		}
		if (event.getEntity() == null || event.getEntityType() == null) { // If the entity killed doesn't exist for some
																			// reason
			Bukkit.getServer().getConsoleSender()
					.sendMessage(ChatColor.RED + "[mPlugin] An entity died but was NULL or no type!"); // send a nifty
																										// message
			return; // stop running code or stuff will error badly
		}
		// Bukkit.broadcastMessage(ChatColor.RED + "[Debug] " +
		// event.getEntityType().toString() + " was killed by " +
		// event.getEntity().getKiller().getDisplayName()); // otherwise print a message
		// in chat
		Random r = new Random();
		float chance = r.nextFloat();
		if (chance <= 0.10f) {
			switch (event.getEntityType()) {
			case CREEPER:
				ItemStack CreeperHead = new ItemStack(Material.CREEPER_HEAD);
				event.getDrops().add(CreeperHead);
				break;
			case ZOMBIE:
				ItemStack ZombieHead = new ItemStack(Material.ZOMBIE_HEAD);
				event.getDrops().add(ZombieHead);
				break;
			case SKELETON:
				ItemStack SkeletonHead = new ItemStack(Material.SKELETON_SKULL);
				event.getDrops().add(SkeletonHead);
				break;
			case ENDER_DRAGON:
				ItemStack DragonHead = new ItemStack(Material.DRAGON_HEAD);
				event.getDrops().add(DragonHead);
				ItemStack ElytraItem = new ItemStack(Material.ELYTRA);
				event.getDrops().add(ElytraItem); // dropping an elytra on dragon death cuz hermitcraft does that
				break;
			case SPIDER:
				event.getDrops().add(generateSkull("Spider"));
				break;
			case BLAZE:
				event.getDrops().add(generateSkull("Blaze"));
				break;
			case CHICKEN:
				event.getDrops().add(generateSkull("Chicken"));
				break;
			case ENDERMAN:
				event.getDrops().add(generateSkull("Enderman"));
				break;
			case SHEEP:
				event.getDrops().add(generateSkull("Sheep"));
				break;
			case CAVE_SPIDER:
				event.getDrops().add(generateSkull("CaveSpider"));
				break;
			case VILLAGER:
				event.getDrops().add(generateSkull("Villager"));
				break;
			case PIG:
				event.getDrops().add(generateSkull("Pig"));
				break;
			case WITHER:
				event.getDrops().add(generateSkull("Wither"));
				break;
			case SLIME:
				event.getDrops().add(generateSkull("Slime"));
				break;
			case OCELOT:
				event.getDrops().add(generateSkull("Ocelot"));
				break;
			case PIGLIN:
				event.getDrops().add(generateSkull("PigZombie"));
				break;
			case IRON_GOLEM:
				event.getDrops().add(generateSkull("Golem"));
				break;
			case COW:
				event.getDrops().add(generateSkull("Cow"));
				break;
			default: // if the mob that was killed isnt on the list then run this
//			Bukkit.getServer().getConsoleSender().sendMessage("the switch in mPlugin is default for some reason");
				break; // do nothing
			}
		}
	}

	private ItemStack generateSkull(String mob) {
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner("MHF_" + mob);
		meta.setDisplayName(mob + " Head");
		skull.setItemMeta(meta);
		return skull;
	}
}

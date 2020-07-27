package me.matt4499.hermitcraft;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Admin implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if((sender.isOp())) {
			if(Bukkit.getPlayer(args[0]) == null) {
				sender.sendMessage("Player not found");
				return true;
			}
			Player target = Bukkit.getPlayer(args[0]);
			Inventory inventory = Bukkit.createInventory(null, 54, "invsee");
			ItemStack health = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE);
            ItemMeta healthMeta = health.getItemMeta();
            healthMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lHealth"));
            ArrayList<String> healthLore = new ArrayList<String>();
            healthLore.add(ChatColor.translateAlternateColorCodes('&', "&c" + Math.round(target.getHealth()) + " &rhealth"));
            healthLore.add(ChatColor.translateAlternateColorCodes('&', "&c" + Math.round(target.getHealth()) / 2 + " &rhearths"));
            healthMeta.setLore(healthLore);
            health.setItemMeta(healthMeta);
            inventory.setItem(51, health);

            ItemStack hunger = new ItemStack(Material.COOKED_BEEF);
            ItemMeta hungerMeta = hunger.getItemMeta();
            hungerMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lHunger"));
            ArrayList<String> hungerLore = new ArrayList<String>();
            hungerLore.add(ChatColor.translateAlternateColorCodes('&', "&e" + target.getFoodLevel() + " &rhunger"));
            hungerMeta.setLore(hungerLore);
            hunger.setItemMeta(hungerMeta);
            inventory.setItem(52, hunger);

            ItemStack xp = new ItemStack(Material.EXPERIENCE_BOTTLE);
            ItemMeta xpMeta = xp.getItemMeta();
            xpMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lExperience"));
            ArrayList<String> xpLore = new ArrayList<String>();
            xpLore.add(ChatColor.translateAlternateColorCodes('&', "&a" + target.getTotalExperience() + " &rexperience (&a" + target.getLevel() + "&r)"));
            xpMeta.setLore(xpLore);
            xp.setItemMeta(xpMeta);
            inventory.setItem(53, xp);

            ItemStack stainedGlass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta stainedGlassMeta = stainedGlass.getItemMeta();
            stainedGlassMeta.setDisplayName(" ");
            stainedGlass.setItemMeta(stainedGlassMeta);

            for (int i = 0; i < 9; i++) {
                inventory.setItem(36 + i, stainedGlass);
            }
            inventory.setItem(50, stainedGlass);

            for (int i = 0; i < 9; i++) {
                inventory.setItem(27 + i, target.getInventory().getItem(i));
            }

            ItemStack[] armorContent = target.getInventory().getArmorContents();
            for (int i = 0; i < 4; i++) {
                inventory.setItem(45 + i, armorContent[3 - i]);
            }
            inventory.setItem(49, target.getInventory().getItemInOffHand());

            for (int i = 0; i < 27; i++) {
                inventory.setItem(i, target.getInventory().getItem(i + 9));
            }
            Player admin = (Player) sender;
            admin.openInventory(inventory);
            return true;
		}
		return false;
	}
	
	@EventHandler
    public void invsee(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("invsee")) {
            event.setCancelled(true);
        }
    }
}

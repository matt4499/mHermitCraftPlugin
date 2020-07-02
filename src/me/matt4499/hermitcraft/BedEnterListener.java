package me.matt4499.hermitcraft;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedEnterEvent.BedEnterResult;

public class BedEnterListener implements Listener {
	private boolean debounce = false; //debounce to prevent multiple people triggering multiple time changes
	
	@EventHandler
	public void onBedEnter(PlayerBedEnterEvent event) { //when a player attempts to enter a bed
		if(event.getBedEnterResult() == BedEnterResult.OK) { //and they dont get cancelled (i.e. due to Monsters too close, too far away)
			if(debounce) { //debouncing meaning whether or not somebody is already sleeping/has entered a bed recently
				event.getPlayer().sendMessage("[Sleep] Someone else is currently sleeping, waiting for time change...");
				return; //if somebody else is already sleeping, dont run anymore code
			}
			debounce = true; //activate debounce so it can't be ran multiple times, wait until a already sleeping player finishes the event
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() { //schedule a task to run at a later time
				public void run() { //what to run once the delay is up
					if(event.getPlayer().isSleeping()) { //make sure the player is still sleeping, and didnt leave the bed
						Bukkit.getServer().getConsoleSender().sendMessage("[mDebug] " + event.getPlayer().getDisplayName() + " is still sleeping. changing time...");
						Bukkit.getServer().getWorld("world").setTime(0);
						Bukkit.getServer().broadcastMessage(event.getPlayer().getDisplayName() + " slept and skipped night time!"); //anounce the change
						debounce = false; //reset the debounce, the next event will be at night time anyways /shrug
					} else {
						Bukkit.getServer().getConsoleSender().sendMessage("[mDebug] " + event.getPlayer().getDisplayName() + " got out of bed. not changing time.");
						debounce = false; //reset the debounce so the next person will trigger the code
					}
				}
				
			}, 5 * 20); //the delay in which it will wait then run the code inside of the task, aka the sleep animation
		}
	}
}

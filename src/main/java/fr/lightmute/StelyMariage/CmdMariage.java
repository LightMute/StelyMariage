package fr.lightmute.StelyMariage;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.scheduler.BukkitTask;

public class CmdMariage implements CommandExecutor {

	Location locmarierHomme = new Location(Bukkit.getWorld("eventkayno19"), 1728.5, 5, 167.5);
	Location locmarierFemme = new Location(Bukkit.getWorld("eventkayno19"), 1728.5, 5, 161.5);
	Location loceglise = new Location(Bukkit.getWorld("eventkayno19"), 1549.5, 4, 164.5);
	ArrayList<String> joueurs = new ArrayList<String>();
	Boolean mariageencours = false;
	String mariHomme = "";
	String mariFemme = "";

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(sender instanceof Player) {

			Player player = (Player) sender;
			@SuppressWarnings("unused")
			BukkitTask task;

			if(args.length == 0) {
				//comandes
				player.sendMessage(App.Prefix + " /mariage <pseudoHomme> <pseudoFemme>");
				return true;
			}else if(Bukkit.getPlayer(args[0]) != null && Bukkit.getPlayer(args[1]) != null && mariageencours == false) {
				mariHomme = args[0];
				mariFemme = args[1];
				mariageencours = true;
				Bukkit.getPlayer(mariFemme).teleport(loceglise, TeleportCause.COMMAND);
				Bukkit.getPlayer(mariHomme).teleport(loceglise, TeleportCause.COMMAND);
				Bukkit.getPlayer(mariFemme).sendMessage(App.Prefix + "§aTu as 1 minute avant que les joueurs arrive !");
				Bukkit.getPlayer(mariHomme).sendMessage(App.Prefix + "§aTu as 1 minute avant que les joueurs arrive !");
				for(Player pls : Bukkit.getOnlinePlayers()) {
					pls.playSound(pls.getLocation(), "clochemariage", 900.0F, 1.0F);
				}
				task = Bukkit.getScheduler().runTaskLater(App.instance, new Runnable() {
					@SuppressWarnings("deprecation")
					public void run() {
						Bukkit.broadcastMessage(App.Prefix + " §e" + args[0] + " §ase mari avec §e" + args[1]);
						for(Player pls : Bukkit.getOnlinePlayers()) {
							pls.sendTitle("§e"+mariHomme + " §aet §e" + mariFemme,"§aLe mariage commence dans 30 seconde !");
						}
					}
				}, 30 * 20L);
				task = Bukkit.getScheduler().runTaskLater(App.instance, new Runnable() {
					public void run() {
						for(Player pls : Bukkit.getOnlinePlayers()) {
							if(pls.getName().equals(mariFemme)) {
								pls.teleport(locmarierFemme, TeleportCause.COMMAND);
							}
							if(pls.getName().equals(mariHomme)) {
								pls.teleport(locmarierHomme, TeleportCause.COMMAND);
							}
							if(!pls.getName().equals(mariHomme) && !pls.getName().equals(mariFemme)) {
								pls.teleport(loceglise, TeleportCause.COMMAND);
							}
						}
					}
				}, 50 * 20L);
				task = Bukkit.getScheduler().runTaskLater(App.instance, new Runnable() {
					public void run() {
						for(Player pls : Bukkit.getOnlinePlayers()) {
							pls.playSound(pls.getLocation(), "mariage1", 900.0F, 1.0F);
						}
					}
				}, 60 * 20L);
				task = Bukkit.getScheduler().runTaskLater(App.instance, new Runnable() {
					@SuppressWarnings("deprecation")
					public void run() {
						for(Player pls : Bukkit.getOnlinePlayers()) {
							pls.sendTitle("§e"+mariHomme + " §aet §e" + mariFemme,"§aFini");
						}
					}
				}, 360 * 20L);
				return true;

			}else {
				if(mariageencours == true) {
					player.sendMessage(App.Prefix + "§cUn mariage est déjà en cours !");
					return true;
				}
			}
		}
		return false;
	}

}

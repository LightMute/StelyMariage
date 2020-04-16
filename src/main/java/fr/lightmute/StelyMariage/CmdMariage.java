package fr.lightmute.StelyMariage;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.scheduler.BukkitTask;

public class CmdMariage implements CommandExecutor {

	Location locmariage = new Location(Bukkit.getWorld("Spawn"), 12, 64, 12);
	Boolean mariageencours = false;
	Boolean Teleportinvit = false;
	String mariHomme = "";
	String mariFemme = "";

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(sender instanceof Player) {

			Player player = (Player) sender;
			@SuppressWarnings("unused")
			BukkitTask task;

			if(args.length == 0) {
				player.sendMessage(App.Prefix + " /mariage <pseudoHomme> <pseudoFemme>");
				return true;
			}else if(args[0].equalsIgnoreCase("participer") && mariageencours == true) {
				if(Teleportinvit == true) {
					player.teleport(locmariage, TeleportCause.COMMAND);
				}else {
					player.sendMessage(App.Prefix + " Le mariage est encore en préparation !");
				}
				return true;


			}else if(Bukkit.getPlayer(args[0]) != null && Bukkit.getPlayer(args[1]) != null && mariageencours == false) {
				mariHomme = args[0];
				mariFemme = args[1];
				mariageencours = true;
				task = Bukkit.getScheduler().runTaskLater(App.instance, new Runnable() {
					public void run() {
						Bukkit.broadcastMessage(App.Prefix + args[0] + " ce mari avec " + args[1] + " Vous pouvez vous teleporter /mariage participer");
						Teleportinvit = true;
					}
				}, 10 * 20L);
				return true;

			}else {
				if(mariageencours == true) {
					player.sendMessage(App.Prefix + " Un mariage est déjà en cours !");
					return true;
				}
			}
		}
		return false;
	}

}

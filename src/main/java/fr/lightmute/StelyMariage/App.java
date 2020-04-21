package fr.lightmute.StelyMariage;

import java.util.logging.Level;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {
	
	public static String Prefix = "§7[§5StelyMariage§7]";
	public static Plugin instance;
	
	public void onEnable() {
		
		instance = this;
		//metric
		Metrics metric = new Metrics(this, 7213);
		
		if(metric.isEnabled()) {
			getLogger().log(Level.INFO, "Metric activé !");
		}
		//cmd
		getCommand("mariage").setExecutor(new CmdMariage());
	}
	public void onDisable() {
	}
	
}

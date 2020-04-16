package fr.lightmute.StelyMariage;

import java.util.logging.Level;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {
	
	public static String Prefix = "";
	public static Plugin instance;
	
	public void onEnable() {
		instance = this;
		Metrics metric = new Metrics(this, 1);
		if(metric.isEnabled()) {
			getLogger().log(Level.INFO, "Metric activé !");
		}
		getCommand("mariage").setExecutor(new CmdMariage());
	}
	public void onDisable() {
	}
	
}

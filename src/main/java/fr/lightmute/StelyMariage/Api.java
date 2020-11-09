package fr.lightmute.StelyMariage;

public class Api {

	public String GetMariéFemme() {
		CmdMariage cmdmariage = new CmdMariage();
		return cmdmariage.mariFemme;
	}
	public String GetMariéHomme() {
		CmdMariage cmdmariage = new CmdMariage();
		return cmdmariage.mariHomme;
	}
	public boolean HasMariage() {
		CmdMariage cmdmariage = new CmdMariage();
		return cmdmariage.mariageencours;
	}
}

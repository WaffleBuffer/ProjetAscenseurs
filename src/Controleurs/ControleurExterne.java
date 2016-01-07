package Controleurs;

import java.util.ArrayList;
import Requetes.RequeteExterne;

public class ControleurExterne {

	final private static ControleurExterne singleton = new ControleurExterne ();
	
	private ArrayList<RequeteExterne> requetes = new ArrayList<RequeteExterne>();
	
	private ControleurExterne () {}
	
	public ControleurExterne getControleurExterne() {
		return singleton;
	}
	
	public void addRequeteExt (int etage, String direction) {
		requetes.add(new RequeteExterne(etage, (direction == "Haut") ? true : false));
	}
	
	public void choisirAsc (ArrayList<ControleurInterne> controleurs) {
		controleurs.get(0).addRequete(requetes.get(0));
	}
}

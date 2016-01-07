package Controleurs;

import java.util.ArrayList;

import Requetes.Requete;
import Requetes.RequeteExterne;

public class ControleurExterne implements IControleur{

	final private static ControleurExterne singleton = new ControleurExterne ();
	
	private ArrayList<Requete> requetes = new ArrayList<Requete>();
	
	private ControleurExterne () {}
	
	public static ControleurExterne getControleurExterne() {
		return singleton;
	}
	
	public void addRequete (int etage, String direction) {
		requetes.add(new RequeteExterne(etage, (direction == "Haut") ? true : false));
	}
	
	public void traiterRequetes (ArrayList<ControleurInterne> controleurs) {
		for (int i = 0; i < requetes.size(); ++i) {
			controleurs.get(0).addRequete(requetes.get(0));
			requetes.remove(requetes.get(i));
		}
	}

	public void addRequete(Requete requete) {
		// TODO Auto-generated method stub
		requetes.add(requete);
	}
}

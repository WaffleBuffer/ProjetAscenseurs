package Controleurs;

import java.util.ArrayList;

import Client.Batiment;
import Requetes.Requete;
import Requetes.RequeteExterne;

public class ControleurExterne implements IControleur{

	final private static ControleurExterne singleton = new ControleurExterne ();
	
	private ArrayList<Requete> requetes = new ArrayList<Requete>();
	
	private ArrayList<ControleurInterne> controleurs;
	private ControleurInterne aUtiliser;
	private Batiment batiment;
	
	private ControleurExterne () {}
	
	public void defineControleursInterne (ArrayList<ControleurInterne> controleurs) {
		this.controleurs = controleurs;
	}
	
	public void defineBatiment (Batiment batiment) {
		this.batiment = batiment;
	}
	
	public static ControleurExterne getControleurExterne() {
		return singleton;
	}
	
	public void addRequete (int etage, String direction) {
		requetes.add(new RequeteExterne(etage, direction));
	}
	
	public void traiterRequetes (ArrayList<ControleurInterne> controleurs) {

		//Algo Naif
//		for (int i = 0; i < requetes.size(); ++i) {
//			controleurs.get(0).addRequete(requetes.get(i));
//			requetes.remove(requetes.get(i));
//		}
		
		//Algo "avancé"
		for (int i = 0; i < requetes.size();++i) {
			
			if (searchForInactive()) {}
			else if (requetes.get(i).getLibelle().equals("Haut")) {
				if (searchToUp(requetes.get(i).getEtage())) {}
				else {
					searchLessActive ();
				}
			}
			else {
				if(searchToDown(requetes.get(i).getEtage())) {}
				else {
					searchLessActive ();
				}				
			}
			aUtiliser.addRequete(requetes.get(i));
			requetes.remove(requetes.get(i));
		}
		

	}
	
	private boolean searchForInactive () {
		for (ControleurInterne controleur : controleurs) {
			if (!controleur.getAscenceur().isEstEnMouvement()) {
				this.aUtiliser = controleur;
				return true;
			}
		}
		return false;
	}
	
	private boolean searchToUp (int etage) {
		for (ControleurInterne controleur : controleurs) {
			if (controleur.prochaineDest() != -1 && 
					(controleur.getAscenceur().getEtage() < etage && controleur.prochaineDest() >= etage)) {
				this.aUtiliser = controleur;
				return true;
			}
		}
		return false;
	}
	
	private boolean searchToDown (int etage) {
		for (ControleurInterne controleur : controleurs) {
			if (controleur.prochaineDest() != -1 && 
					(controleur.getAscenceur().getEtage() > etage && controleur.prochaineDest() <= etage)) {
				this.aUtiliser = controleur;
				return true;
			}
		}
		return false;
	}
	
	private void searchLessActive () {
		int min = batiment.getNbEtages() + 1;
		for (ControleurInterne controleur : controleurs) {
			if (controleur.getNumberOfRequete() == 0) {
				this.aUtiliser = controleur;
				return;
			}
			else {
				if (controleur.getNumberOfRequete() < min) {
					min = controleur.getNumberOfRequete();
				}
			}
		}
		for (ControleurInterne controleur : controleurs) {
			if (controleur.getNumberOfRequete() == min) {
				this.aUtiliser = controleur;
				return;
			}
		}
	}

	public void addRequete(Requete requete) {
		requetes.add(requete);
	}
}

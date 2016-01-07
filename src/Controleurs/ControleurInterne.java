package Controleurs;

import java.util.ArrayList;

import Client.Ascenseur;
import Requetes.Requete;


public class ControleurInterne {
	
	private Ascenseur ascenseur;
	
	public ControleurInterne (Ascenseur ascenseur) {
		this.ascenseur = ascenseur;
	}
	
	private ArrayList<Requete> requetes = new ArrayList<Requete>();
	
	public void traiterRequete(Requete requete, Ascenseur ascenseur){
		if (requete.getLibelle() == "Allez � l'�tage")
			ascenseur.setEtage(requete.getEtage());
		else if (requete.getLibelle() == "Haut")
			ascenseur.setEtage(requete.getEtage());
	}
	
	public void addRequete (Requete requete) {
		requetes.add(requete);
	}
}

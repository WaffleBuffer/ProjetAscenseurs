package Controleurs;

import java.util.ArrayList;

import Client.Ascenseur;
import Requetes.Requete;


public class ControleurInterne {
	
	private Ascenseur ascenseur;
	private ArrayList<Requete> requetes = new ArrayList<Requete>();
	
	public ControleurInterne (Ascenseur ascenseur) {
		this.ascenseur = ascenseur;
	}
	
	public void traiterRequetes(Ascenseur ascenseur){
		for (int i = 0; i < requetes.size(); ++i) {
			if (requetes.get(i).getLibelle() == "Allez � l'�tage")
				ascenseur.setEtage(requetes.get(i).getEtage());
			else if (requetes.get(i).getLibelle() == "Haut")
				ascenseur.setEtage(requetes.get(i).getEtage());
			else if (requetes.get(i).getLibelle() == "Bas")
				ascenseur.setEtage(requetes.get(i).getEtage());
		}
	}
	
	public void addRequete (Requete requete) {
		requetes.add(requete);
	}
}

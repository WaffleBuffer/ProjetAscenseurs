package Controleurs;

import java.util.ArrayList;

import Client.Ascenseur;
import Requetes.Requete;
import Requetes.RequeteExterne;
import Requetes.RequeteInterne;


public class ControleurInterne implements IControleur{
	
	private Ascenseur ascenseur;
	private ArrayList<Requete> requetes = new ArrayList<Requete>();
	
	public ControleurInterne (Ascenseur ascenseur) {
		this.ascenseur = ascenseur;
	}
	
	public void traiterRequetes(){
		for (int i = 0; i < requetes.size(); ++i) {
			if (requetes.get(i).getLibelle() == "Allez � l'�tage") {
				System.out.println("Ascenceur va de l'étage : " + ascenseur.getEtage() + " à l'étage " + requetes.get(i).getEtage());
				ascenseur.setEtage(requetes.get(i).getEtage());	
				requetes.remove(requetes.get(i));
			}
			else if (requetes.get(i).getLibelle() == "Haut") {
				System.out.println("Ascenceur va de l'étage : " + ascenseur.getEtage() + " à l'étage " + requetes.get(i).getEtage());
				ascenseur.setEtage(requetes.get(i).getEtage());
				requetes.remove(requetes.get(i));
			}
			else if (requetes.get(i).getLibelle() == "Bas") {
				System.out.println("Ascenceur va de l'étage : " + ascenseur.getEtage() + " à l'étage " + requetes.get(i).getEtage());
				ascenseur.setEtage(requetes.get(i).getEtage());
				requetes.remove(requetes.get(i));
			}
		}
	}
	
	public void addRequete (Requete requete) {
		requetes.add(requete);
	}
	
	public void addRequete (int etage) {
		requetes.add(new RequeteInterne(etage));
	}
}

package Boutons;

import Controleurs.IControleur;

public class Bouton {
	/*Identifiant du bouton:
	 * boutons etages :
	 * 	-etage 0    -> "Rez-de-chaussé"
	 * 	-1er étage  -> "1er étage"
	 * 	-ieme étage -> i + "e étage"
	 * 
	 * bouton stop -> "Stop"
	*/
	private String libelle;

	public String getLibelle() {
		return libelle;
	}

	public Bouton(String libelle) {
		
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Bouton [libelle=" + libelle + "]";
	}
	
	//Fonction d'action de bouton
	public void appuyer (IControleur controleur) {}
}

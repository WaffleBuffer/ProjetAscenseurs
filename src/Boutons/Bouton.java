package Boutons;

import Controleurs.IControleur;

public class Bouton {
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
	
	public void appuyer (IControleur controleur) {}
}

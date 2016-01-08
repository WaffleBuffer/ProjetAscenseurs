package Boutons;

import Controleurs.IControleur;
import Requetes.RequeteExterne;

public class BoutonExterne extends Bouton {
	private int etage;

	public BoutonExterne(int etage, String libelle) {
		super(libelle);
		this.etage = etage;
		// TODO Auto-generated constructor stub
	}

	//Ajout d'une requete externe. Le fonctionnement est le meme pour les boutons Haut et Bas
	@Override
	public void appuyer (IControleur controleur) {
		RequeteExterne requete = new RequeteExterne(etage, (this.getLibelle().equals("Haut")) ? true : false);
		controleur.addRequete(requete);
	}

	@Override
	public String toString() {
		return "BoutonExterne [libelle=" + this.getLibelle() + "; etage=" + this.getEtage() + "]";
	}
	
	public int getEtage() {
		return etage;
	}
}

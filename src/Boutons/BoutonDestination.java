package Boutons;

import Controleurs.IControleur;
import Requetes.RequeteInterne;

public class BoutonDestination extends BoutonInterne {

	private int etage;
	
	public BoutonDestination(String libelle, int eta) {
		super(libelle);
		this.etage = eta;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "BoutonDestination [libelle=" + this.getLibelle() + "; etage ciblé=" + etage + "]";
	}

	//Ajout d'une requete interne
	@Override
	public void appuyer (IControleur controleur) {
		controleur.addRequete(new RequeteInterne (etage));
	}
}

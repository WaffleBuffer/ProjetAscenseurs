package Boutons;

import Controleurs.ControleurExterne;
import Controleurs.ControleurInterne;
import Requetes.RequeteExterne;

public class BoutonExterne extends Bouton {
	int etage;

	public BoutonExterne(int etage, String libelle) {
		super(libelle);
		// TODO Auto-generated constructor stub
	}

	public void appuyer (ControleurExterne controleur) {

	}

	@Override
	public String toString() {
		return "BoutonExterne [libelle=" + this.getLibelle() + "; etage=" + etage + "]";
	}
}

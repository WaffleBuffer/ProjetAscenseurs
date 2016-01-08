package Boutons;

import Controleurs.ControleurInterne;
import Requetes.RequeteInterne;

public class BoutonStop extends BoutonInterne{

	public BoutonStop() {
		super("Stop");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "BoutonStop [" + super.toString() + "]";
	}

	//Creation requete d'arret
	@Override
	public void appuyer (ControleurInterne controleur) {
		controleur.addRequete(new RequeteInterne());
	}
}

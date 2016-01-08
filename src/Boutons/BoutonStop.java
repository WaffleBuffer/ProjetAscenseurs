package Boutons;

import Controleurs.IControleur;
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
	public void appuyer (IControleur controleur) {
		controleur.addRequete(new RequeteInterne());
	}
}

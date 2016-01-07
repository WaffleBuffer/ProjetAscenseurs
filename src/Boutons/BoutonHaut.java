package Boutons;

import Controleurs.ControleurExterne;
import Requetes.RequeteExterne;

public class BoutonHaut extends BoutonExterne{

	public BoutonHaut(int etage) {
		super(etage, "Haut");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void appuyer (ControleurExterne controleur) {
		RequeteExterne requete = new RequeteExterne (this.getEtage(), true);
		controleur.addRequeteExt(requete);
	}
}

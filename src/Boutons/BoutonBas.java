package Boutons;

import Controleurs.ControleurExterne;
import Requetes.RequeteExterne;


public class BoutonBas extends BoutonExterne{

	public BoutonBas(int etage) {
		super(etage, "Bas");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void appuyer (ControleurExterne controleur) {
		RequeteExterne requete = new RequeteExterne (etage, false);
		controleur.addRequeteExt(requete);
	}
}

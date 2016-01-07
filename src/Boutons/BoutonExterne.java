package Boutons;

import Controleurs.ControleurExterne;

public class BoutonExterne extends Bouton {
	private int etage;

	public BoutonExterne(int etage, String libelle) {
		super(libelle);
		this.etage = etage;
		// TODO Auto-generated constructor stub
	}
	
	public void appuyer (ControleurExterne controleur) {
		controleur.addRequeteExt(etage, getLibelle());
	}

	@Override
	public String toString() {
		return "BoutonExterne [libelle=" + this.getLibelle() + "; etage=" + this.getEtage() + "]";
	}
	
	public int getEtage() {
		return etage;
	}
}

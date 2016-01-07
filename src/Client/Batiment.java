package Client;

import java.util.ArrayList;

import Boutons.BoutonBas;
import Boutons.BoutonExterne;
import Boutons.BoutonHaut;

public class Batiment {
	private String nom;
	private int nbEtages;
	private ArrayList<BoutonExterne> listeBoutons = new ArrayList<BoutonExterne>();
	
	public Batiment(String nom, int nbEtages) {
		this.nom = nom;
		this.nbEtages = nbEtages;
		listeBoutons.add(new BoutonHaut(0));
		
		for (int i = 1 ; i < nbEtages; ++i){
			listeBoutons.add(new BoutonHaut(i));
			listeBoutons.add(new BoutonBas(i));
		}
		
		listeBoutons.add(new BoutonBas(nbEtages));
	}

	public String getNom() {
		return nom;
	}

	public int getNbEtages() {
		return nbEtages;
	}
	

	public ArrayList<BoutonExterne> getListeBoutons() {
		return listeBoutons;
	}

	@Override
	public String toString() {
		return "Batiment [nom=" + nom + ", nbEtages=" + nbEtages + "]";
	}

}

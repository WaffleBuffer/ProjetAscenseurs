package Client;

import java.util.ArrayList;

import Boutons.BoutonBas;
import Boutons.BoutonExterne;
import Boutons.BoutonHaut;
import Controleurs.ControleurExterne;
import Controleurs.ControleurInterne;

public class Batiment {
	private String nom; // le batiment a un nom
	private int nbEtages; //le batiment a un certain nombre d'etages
	private int nbAscenseur; //le batiment a un certain nombre d'ascenseur
	private ControleurExterne controleurExt = ControleurExterne.getControleurExterne();
	
	//liste des controleurs internes
	private ArrayList<ControleurInterne> controleursInt = new ArrayList<ControleurInterne>();
	
	//le batiment possede une liste de bouton
	private ArrayList<BoutonExterne> listeBoutons = new ArrayList<BoutonExterne>();
	
	public Batiment(String nom, int nbEtages, int nbAscenseur) {
		this.nom = nom;
		this.nbEtages = nbEtages;
		this.nbAscenseur = nbAscenseur;
		listeBoutons.add(new BoutonHaut(0)); //le rez de chaussé n'a qu'un bouton haut et as de boton bas
		
		//a chaque étage, le batiment possede deux boutons : haut et bas
		for (int i = 1 ; i < nbEtages; ++i){
			listeBoutons.add(new BoutonHaut(i));
			listeBoutons.add(new BoutonBas(i));
		} 
		
		//Creation de tous les ascenseurs
		for (int i = 0; i < nbAscenseur; ++i) {
			controleursInt.add(new ControleurInterne(new Ascenseur(nbEtages, i + 1)));
		}
		
		//parametrage du controleur Externe
		controleurExt.defineBatiment (this);
		controleurExt.defineControleursInterne(controleursInt);
		
		listeBoutons.add(new BoutonBas(nbEtages)); // le dernier étage n'a qu'un bouton bas et pas de bouton haut
	}
	
	public void traiterControleurs () {
		for (ControleurInterne i : controleursInt) {
			i.traiterRequetes();
		}
	}
	
	public void appuyerBoutonEtage (int num) {
		listeBoutons.get(num).appuyer(controleurExt);
	}
	
	public Ascenseur getAscenseur(int num) {
		return controleursInt.get(num - 1).getAscenceur();
	}

	public String getNom() {
		return nom;
	}

	public int getNbEtages() {
		return nbEtages;
	}
	
	public int getNbAscenseur () {
		return nbAscenseur;
	}
	
	public ControleurExterne getControleurExt () {
		return controleurExt;
	}
	
	public ArrayList<ControleurInterne> getControleursInterne () {
		return controleursInt;
	}

	public ArrayList<BoutonExterne> getListeBoutons() {
		return listeBoutons;
	}
	
	public void appuyerBoutonAscenseur (int numAsc, int numBouton) {
		this.getAscenseur(numAsc).getListeBoutons().get(numBouton).appuyer(this.getControleursInterne().get(numAsc - 1));
	}

	@Override
	public String toString() {
		return "Batiment [nom=" + nom + ", nbEtages=" + nbEtages + "]";
	} //affichage de l'etat du batiment

}

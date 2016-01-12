package Client;

import java.util.ArrayList;

import Boutons.Bouton;
import Boutons.BoutonBas;
import Boutons.BoutonExterne;
import Boutons.BoutonHaut;
import Controleurs.ControleurExterne;
import Controleurs.ControleurInterne;

/**Contient les principales informations: les {@link ControleurInterne} (et donc les {@link Ascenseur})
 * et le {@link ControleurExterne} correspondant a ce Batiment.
 * @author Thomas
 * @see ControleurInterne
 */
public class Batiment {
	
	/**Le nom de ce Batiment.
	 * 
	 */
	private String nom;
	
	/**Le nombre d'etage de ce Batiment.
	 * 
	 */
	private int nbEtages;
	
	/**Le nombre d'{@link Ascenseur} de ce Batiment.
	 * 
	 */
	private int nbAscenseur;
	
	/**le {@link ControleurExterne} de ce Batiment.
	 * 
	 */
	private ControleurExterne controleurExt = ControleurExterne.getControleurExterne();
	
	/**La liste des {@link ControleurInterne} de ce Batiment.
	 * @see ControleurInterne
	 */
	private ArrayList<ControleurInterne> controleursInt = new ArrayList<ControleurInterne>();
	
	/**le batiment possede une liste de boutons
	 * 
	 */
	private ArrayList<BoutonExterne> listeBoutons = new ArrayList<BoutonExterne>();
	
	public Batiment(String nom, int nbEtages, int nbAscenseur) {
		this.nom = nom;
		this.nbEtages = nbEtages;
		this.nbAscenseur = nbAscenseur;
		listeBoutons.add(new BoutonHaut(0)); //le rez de chaussé n'a qu'un bouton haut et pas de bouton bas
		
		//a chaque etage, le batiment possede deux boutons : haut et bas
		for (int i = 1 ; i < nbEtages; ++i){
			listeBoutons.add(new BoutonHaut(i));
			listeBoutons.add(new BoutonBas(i));
		} 
		
		//Creation de tous les ascenseurs
		for (int i = 0; i < nbAscenseur; ++i) {
			controleursInt.add(new ControleurInterne(new Ascenseur(nbEtages, i + 1)));
		}
		
		//parametrage du controleur Externe
		controleurExt.defineBatiment (this.getNbEtages());
		controleurExt.defineControleursInterne(controleursInt);
		
		listeBoutons.add(new BoutonBas(nbEtages)); // le dernier étage n'a qu'un bouton bas et pas de bouton haut
	}
	
	/**
	 * 
	 */
	public void traiterControleurs () {
		for (ControleurInterne i : controleursInt) {
			System.out.println(i.traiterRequetes());
		}
	}
	
	/**
	 * 
	 * @param num
	 */
	public void appuyerBoutonEtage (int num) {
		listeBoutons.get(num).appuyer(controleurExt);
	}
	
	/**
	 * 
	 * @param num numero de l'{@link Ascenseur}
	 * @return {@link ControleurInterne#controleursInt}
	 */
	public Ascenseur getAscenseur(int num) {
		return controleursInt.get(num - 1).getAscenceur();
	}

	/**
	 * Permet d'obtenir le nom du Batiment
	 * @return {@link Batiment#nom}
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Permet d'obtenir le nombre d'etage du batiment
	 * @return {@link Batiment#nbEtage}
	 */
	public int getNbEtages() {
		return nbEtages;
	}
	
	/**
	 * Permet d'obtenir le nombre d'ascenseurs contenus dans le batiment
	 * @return {@link Batiment#nbAscenceur}
	 */
	public int getNbAscenseur () {
		return nbAscenseur;
	}
	
	/**
	 * 
	 * @return {@link ControleurExterne#controleurExt}
	 */
	public ControleurExterne getControleurExt () {
		return controleurExt;
	}
	
	/**
	 * 
	 * @return {@link ControleurInterne#controleursInt}
	 */
	public ArrayList<ControleurInterne> getControleursInterne () {
		return controleursInt;
	}

	/**
	 * 
	 * @return la {@link Batiment#listeBoutons} de ce Batiment
	 */
	public ArrayList<BoutonExterne> getListeBoutons() {
		return listeBoutons;
	}
	
	/**Appuie sur le {@link Bouton} d'un {@link Ascenseur} de ce Batiment
	 * @param numAsc numero de l'{@link Ascenseur) concerne
	 * @param numBouton numero du {@link Bouton} de {@link Ascenseur) 
	 */
	public void appuyerBoutonAscenseur (int numAsc, int numBouton) {
		this.getAscenseur(numAsc).appuyerBouton(numBouton, this.getControleursInterne().get(numAsc - 1));
	}
	
	/**affichage de l'etat de ce Batiment
	 * 
	 */
	@Override
	public String toString() {
		return "Batiment [nom=" + nom + ", nbEtages=" + nbEtages + "]";
	} 

}

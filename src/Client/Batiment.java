package Client;

import java.util.ArrayList;

import Boutons.Bouton;
import Boutons.BoutonBas;
import Boutons.BoutonExterne;
import Boutons.BoutonHaut;
import Controleurs.ControleurExterne;
import Controleurs.ControleurInterne;
import Requetes.Requete;

/**Contient les principales informations: les {@link Controleurs.ControleurInterne} (et donc les {@link Ascenseur})
 * et le {@link ControleurExterne} correspondant a ce Batiment.
 * @author Thomas
 * @see ControleurInterne
 */
public class Batiment {
	
	/**Le nom de ce Batiment.
	 */
	private String nom;
	
	/**Le nombre d'etage de ce Batiment.
	 */
	private int nbEtages;
	
	/**Le nombre d'{@link Ascenseur} de ce Batiment.
	 */
	private int nbAscenseur;
	
	/**La liste des {@link ControleurInterne} de ce Batiment.
	 * @see ControleurInterne
	 */
	private ArrayList<ControleurInterne> controleursInt = new ArrayList<ControleurInterne>();
	
	/**le {@link ControleurExterne} de ce Batiment.
	 */
	private ControleurExterne controleurExt;
	
	/**le batiment possede une liste de {@link Bouton}
	 */
	private ArrayList<BoutonHaut> listeBoutonsHaut = new ArrayList<BoutonHaut>();
	private ArrayList<BoutonBas> listeBoutonsBas = new ArrayList<BoutonBas>();
	
	public Batiment(String nom, int nbEtages, int nbAscenseur) {
		this.nom = nom;
		this.nbEtages = nbEtages;
		this.nbAscenseur = nbAscenseur;
		listeBoutonsHaut.add(new BoutonHaut(0)); //le rez de chaussé n'a qu'un bouton haut et pas de bouton bas
		
		//a chaque etage, le batiment possede deux boutons : haut et bas
		for (int i = 1 ; i < nbEtages; ++i){
			listeBoutonsHaut.add(new BoutonHaut(i));
			listeBoutonsBas.add(new BoutonBas(i));
		} 
		
		//Creation de tous les ascenseurs
		for (int i = 0; i < nbAscenseur; ++i) {
			controleursInt.add(new ControleurInterne(new Ascenseur(nbEtages, i + 1)));
		}
		
		listeBoutonsBas.add(new BoutonBas(nbEtages)); // le dernier étage n'a qu'un bouton bas et pas de bouton haut
		controleurExt = new ControleurExterne(controleursInt, this.getNbEtages());
	}
	
	/**permet de traiter les {@link Requete} des {@link ControleurInterne} contenus dans {@link #controleursInt}
	 * @see Controleurs.ControleurInterne#traiterRequetes()
	 */
	public String traiterControleurs () {
		String strToReturn = "";
		for (ControleurInterne i : controleursInt) {
			String strResult = i.traiterRequetes();
			String frame = "";
			for (int j = 0; j < strResult.length() + 4; ++j) {
				frame += "=";
			}
			strToReturn += frame + "\n";
			strToReturn += "| " + strResult + " |\n";
			strToReturn += frame + "\n";
		}
		return strToReturn;
	}
	
	/**permet d'appuyer sur le {@link Bouton}
	 * @param numEtage numero du {@link Bouton}
	 */
	public void appuyerBoutonEtage (int numEtage, int hautOuBas) {
		if (hautOuBas == Constantes.HAUT)
			listeBoutonsHaut.get(numEtage).appuyer(controleurExt);
		else if (hautOuBas == Constantes.BAS)
			listeBoutonsBas.get(numEtage-1).appuyer(controleurExt);
	}
	
	/**permet d'obtenir les {@link Ascenseur} de ce Batiment
	 * @param num numero de l'{@link Ascenseur}
	 * @return {@link #controleursInt}
	 */
	public Ascenseur getAscenseur(int num) {
		return controleursInt.get(num - 1).getAscenceur();
	}

	/** Permet d'obtenir le {@link Batiment#nom} de ce Batiment
	 * @return {@link #nom}
	 */
	public String getNom() {
		return nom;
	}

	/**Permet d'obtenir le {@link Batiment#nbEtages} de ce Batiment
	 * @return {@link #nbEtages}
	 */
	public int getNbEtages() {
		return nbEtages;
	}
	
	/** Permet d'obtenir le {@link Batiment#nbAscenseur} contenus dans ce Batiment
	 * @return le {@link #nbAscenseur} contenus dans ce Batiment
	 */
	public int getNbAscenseur () {
		return nbAscenseur;
	}
	
	/**permet d'obtenir le {@link #controleurExt}
	 * @return {@link #controleurExt}
	 */
	public ControleurExterne getControleurExt () {
		return controleurExt;
	}
	
	/**permet d'obtenir la liste des {@link #controleursInt} de ce Batiment
	 * @return {@link #controleursInt}
	 */
	public ArrayList<ControleurInterne> getControleursInterne () {
		return controleursInt;
	}
	
	
	/**Appuie sur le {@link Bouton} d'un {@link Ascenseur} de ce Batiment
	 * @param numAsc numero de l'{@link Ascenseur} concerne
	 * @param numBouton numero du {@link Bouton} de {@link Ascenseur} 
	 */
	public void appuyerBoutonAscenseur (int numAsc, int numBouton) {
		this.getAscenseur(numAsc).appuyerBouton(numBouton, this.getControleursInterne().get(numAsc - 1));
	}
	
	@Override
	public String toString() {
		return "Batiment [nom=" + nom + ", nbEtages=" + nbEtages
				+ ", nbAscenseur=" + nbAscenseur + ", controleursInt="
				+ controleursInt + ", controleurExt=" + controleurExt
				+ ", listeBoutonsHaut=" + listeBoutonsHaut
				+ ", listeBoutonsBas=" + listeBoutonsBas + "]";
	}

	public ArrayList<BoutonHaut> getListeBoutonsHaut() {
		return listeBoutonsHaut;
	}

	public ArrayList<BoutonBas> getListeBoutonsBas() {
		return listeBoutonsBas;
	} 

}

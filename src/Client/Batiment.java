package Client;

import java.util.ArrayList;
import java.util.Observable;
import Boutons.Bouton;
import Boutons.BoutonBas;
import Boutons.BoutonExterne;
import Boutons.BoutonHaut;
import AlgosTraitement.AlgoTraitementExterneStandard;
import AlgosTraitement.AlgoTraitementInterneStandard;
import Controleurs.ControleurExterne;
import Controleurs.ControleurInterne;
import Requetes.Requete;
import Requetes.RequeteExterne;

/**Contient les principales informations: les {@link Controleurs.ControleurInterne} (et donc les {@link Ascenseur})
 * et le {@link ControleurExterne} correspondant a ce Batiment.
 * @author Thomas
 * @see ControleurInterne
 */
public class Batiment extends Observable {
	
	/**Le nom de ce Batiment.
	 */
	private String nom;
	
	/**Le nombre d'etage de ce Batiment.
	 */
	private int nbEtages;
	
	/**Le nombre d'{@link Ascenseur} de ce Batiment.
	 */
	private int nbAscenseurs;
	
	/**La liste des {@link ControleurInterne} de ce Batiment.
	 * @see ControleurInterne
	 */
	private ArrayList<ControleurInterne> listeControleursInternes = new ArrayList<ControleurInterne>();
	
	/**le {@link ControleurExterne} de ce Batiment.
	 */
	private ControleurExterne controleurExterne;
	
	/**le batiment possede une liste de {@link Boutons.BoutonExterne} vers le haut
	 */
	private ArrayList<BoutonHaut> listeBoutonsHaut = new ArrayList<BoutonHaut>();
	
	/**le batiment possede une liste de {@link Boutons.BoutonExterne} vers le bas
	 */
	private ArrayList<BoutonBas> listeBoutonsBas = new ArrayList<BoutonBas>();
	
	/**La String representant la derniere iteration de ce Batiment
	 */
	private String ResultatDerniereIteration;

	/**Construit un Batiment.
	 * @param nom le nom de ce Batiment.
	 * @param nbEtages le nombre d'etage de ce Batiment.
	 * @param nbAscenseurs le nombre d'{@link Ascenseur} de ce Batiment.
	 */
	public Batiment(String nom, int nbEtages, int nbAscenseurs) {
		this.nom = nom;
		this.nbEtages = nbEtages;
		this.nbAscenseurs = nbAscenseurs;
		listeBoutonsHaut.add(new BoutonHaut(0)); //le rez de chaussé n'a qu'un bouton haut et pas de bouton bas
		
		//a chaque etage, le batiment possede deux boutons : haut et bas
		for (int i = 1 ; i < nbEtages; ++i){
			listeBoutonsHaut.add(new BoutonHaut(i));
			listeBoutonsBas.add(new BoutonBas(i));
		} 
		
		//Creation de tous les ascenseurs
		for (int i = 0; i < nbAscenseurs; ++i) {
			listeControleursInternes.add(new ControleurInterne(new Ascenseur(nbEtages, i + 1), new AlgoTraitementInterneStandard()));
		}
		
		//this.ascenseurSelectionne = getAscenseur(0);
		
		listeBoutonsBas.add(new BoutonBas(nbEtages)); // le dernier étage n'a qu'un bouton bas et pas de bouton haut
		controleurExterne = new ControleurExterne(listeControleursInternes, this.getNbEtages(), new AlgoTraitementExterneStandard());
	}
	
	/**permet de traiter les {@link Requete} des {@link ControleurInterne} contenus dans {@link #listeControleursInternes}
	 * @return String representant le resultat de l'iteration. Inutilise a ce jour. Etait utilisee pour des tests dans un terminal.
	 * @see Controleurs.ControleurInterne#traiterRequetes()
	 */
	public String traiterControleurs () {
		controleurExterne.traiterRequetes();
		ResultatDerniereIteration = "";
		for (ControleurInterne controleurInterne : listeControleursInternes) {
			String resultat = controleurInterne.traiterRequetes();
			if ("" != resultat) {
				String cadre = "";
				for (int j = 0; j < resultat.length() + 4; ++j) {
					cadre += "=";
				}
				ResultatDerniereIteration += cadre + "\n";
				ResultatDerniereIteration += "| " + resultat + " |\n";
				ResultatDerniereIteration += cadre + "\n";
			}
			//Si l'ascenseur s'est bloque, alors traitement exceptionnel : on reaffecte toutes les RequeteExterne de celui-ci au ControleurExterne.
			//Cela evite que si un ascenseur se bloque de facon infinie, alors la requete externe n'est jamais satisfaite.
			if (resultat.equals("Elevator " + controleurInterne.getAscenseur().getNumAsc() + " is blocked")) {
				for (int j = 0; j < controleurInterne.getRequetes().size(); ++j) {
					if (controleurInterne.getRequetes().get(j).getClass() == RequeteExterne.class) {
						controleurExterne.ajouterRequete(controleurInterne.getRequetes().get(j));
						controleurInterne.getRequetes().remove(controleurInterne.getRequetes().get(j));
						--j;
						setChanged();
						notifyObservers();
					}
				}
			}
		}//boucle for
		setChanged();
		notifyObservers();
		return ResultatDerniereIteration;
	}//traiterControleurs()
	
	/**permet d'appuyer sur le {@link BoutonExterne}
	 * @param numEtage numero du {@link BoutonExterne}
	 * @param hautOuBas la direction demandee par le {@link BoutonExterne} : {@link Constantes#HAUT} ou {@link Constantes#BAS}
	 * @see Constantes
	 */
	public void appuyerBoutonEtage (int numEtage, int hautOuBas) {
		if (hautOuBas == Constantes.HAUT) {
			listeBoutonsHaut.get(numEtage).appuyer(controleurExterne);
		}
		else if (hautOuBas == Constantes.BAS) {
			listeBoutonsBas.get(numEtage).appuyer(controleurExterne);
		}
		setChanged();
		notifyObservers();
	}//appuerBoutonEtage()
	
	/**permet d'obtenir un {@link Ascenseur} de ce Batiment
	 * @param index l'index de l'{@link Ascenseur}
	 * @return l'{@link Ascenseur} a l'index index.
	 */
	public Ascenseur getAscenseur(int index) {
		return listeControleursInternes.get(index).getAscenseur();
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
	
	/** Permet d'obtenir le {@link Batiment#nbAscenseurs} contenus dans ce Batiment
	 * @return le {@link #nbAscenseurs} contenus dans ce Batiment
	 */
	public int getNbAscenseurs () {
		return nbAscenseurs;
	}
	
	/**permet d'obtenir le {@link #controleurExterne}
	 * @return {@link #controleurExterne}
	 */
	public ControleurExterne getControleurExt () {
		return controleurExterne;
	}
	
	/**permet d'obtenir la liste des {@link #listeControleursInternes} de ce Batiment
	 * @return {@link #listeControleursInternes}
	 */
	public ArrayList<ControleurInterne> getControleursInternes () {
		return listeControleursInternes;
	}
	
	
	/**Appuie sur le {@link Bouton} d'un {@link Ascenseur}. L'{@link Ascenseur} devrait etre dans ce Batiment
	 * sinon la {@link Requete} sera envoye a ce Batiment quoi qu'il arrive.
	 * @param ascenseur numero de l'{@link Ascenseur} concerne
	 * @param numBouton numero du {@link Bouton} de {@link Ascenseur} 
	 */
	public void appuyerBoutonAscenseur (Ascenseur ascenseur, int numBouton) {
		ascenseur.appuyerBouton(numBouton, this.getControleursInternes().get(ascenseur.getNumAsc() - 1));
		//setChanged();
		//notifyObservers();
	}
	
	/**Permet d'obtenir l'etat de ce Batiment.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Batiment [nom=" + nom + ", nbEtages=" + nbEtages
				+ ", nbAscenseur=" + nbAscenseurs + ", controleursInt="
				+ listeControleursInternes + ", controleurExt=" + controleurExterne
				+ ", listeBoutonsHaut=" + listeBoutonsHaut
				+ ", listeBoutonsBas=" + listeBoutonsBas + "]";
	}

	/**Permet d'obtenir la {@link #listeBoutonsHaut} des {@link BoutonHaut} de ce Batiment
	 * @return {@link #listeBoutonsHaut} de ce Batiment
	 */
	public ArrayList<BoutonHaut> getListeBoutonsHaut() {
		return listeBoutonsHaut;
	}

	/**Permet d'obtenir la {@link #listeBoutonsBas} des {@link BoutonBas} de ce Batiment
	 * @return {@link #listeBoutonsBas} de ce Batiment
	 */
	public ArrayList<BoutonBas> getListeBoutonsBas() {
		return listeBoutonsBas;
	}
	
	/**Permet d'obtenir le {@link #ResultatDerniereIteration} de ce Batiment
	 * @return le {@link #ResultatDerniereIteration} de ce Batiment
	 */
	public String getResultatDerniereIteration() {
		return ResultatDerniereIteration;
	}
}

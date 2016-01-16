package Client;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

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
public class Batiment extends java.util.Observable implements Observer {
	
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
	private ArrayList<ControleurInterne> controleursInt = new ArrayList<ControleurInterne>();
	
	/**le {@link ControleurExterne} de ce Batiment.
	 */
	private ControleurExterne controleurExt;
	
	/**le batiment possede une liste de {@link Boutons.BoutonExterne} vers le haut
	 */
	private ArrayList<BoutonHaut> listeBoutonsHaut = new ArrayList<BoutonHaut>();
	
	/**le batiment possede une liste de {@link Boutons.BoutonExterne} vers le bas
	 */
	private ArrayList<BoutonBas> listeBoutonsBas = new ArrayList<BoutonBas>();
	
	private Ascenseur ascenseurSelectionne;
	
	public Batiment(String nom, int nbEtages, int nbAscenseur) {
		this.nom = nom;
		this.nbEtages = nbEtages;
		this.nbAscenseurs = nbAscenseur;
		listeBoutonsHaut.add(new BoutonHaut(0)); //le rez de chaussé n'a qu'un bouton haut et pas de bouton bas
		
		//a chaque etage, le batiment possede deux boutons : haut et bas
		for (int i = 1 ; i < nbEtages; ++i){
			listeBoutonsHaut.add(new BoutonHaut(i));
			listeBoutonsBas.add(new BoutonBas(i));
		} 
		
		//Creation de tous les ascenseurs
		for (int i = 0; i < nbAscenseur; ++i) {
			controleursInt.add(new ControleurInterne(new Ascenseur(nbEtages, i + 1), new AlgoTraitementInterneStandard()));
			//controleursInt.get(i).getAscenseur().addObserver(this);
		}
		
		//this.ascenseurSelectionne = getAscenseur(0);
		
		listeBoutonsBas.add(new BoutonBas(nbEtages)); // le dernier étage n'a qu'un bouton bas et pas de bouton haut
		controleurExt = new ControleurExterne(controleursInt, this.getNbEtages(), new AlgoTraitementExterneStandard());
	}
	
	/**permet de traiter les {@link Requete} des {@link ControleurInterne} contenus dans {@link #controleursInt}
	 * @return String representant le resultat de l'iteration. Inutilise a ce jour. Etait utilisee pour des tests dans un terminal.
	 * @see Controleurs.ControleurInterne#traiterRequetes()
	 */
	public String traiterControleurs () {
		controleurExt.traiterRequetes();
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
			//Si l'ascenseur s'est bloque, alors traitement exceptionnel : on reaffecte toutes les RequeteExterne de celui-ci au ControleurExterne.
			//Cela evite que si un ascenseur se bloque de facon infini, alors la requete externe n'est jamais satisfaite.
			if (strResult.equals("ascenseur " + i.getAscenseur().getNumAsc() + " est bloque.")) {
				for (int j = 0; j < i.getRequetes().size(); ++j) {
					if (i.getRequetes().get(j) instanceof RequeteExterne) {
						controleurExt.addRequete(i.getRequetes().get(j));
						i.getRequetes().remove(i.getRequetes().get(j));
						--j;
						setChanged();
						notifyObservers();
					}
				}
			}
		}
		return strToReturn;
	}
	
	/**permet d'appuyer sur le {@link BoutonExterne}
	 * @param numEtage numero du {@link BoutonExterne}
	 * @param hautOuBas la direction demandee par le {@link BoutonExterne} : {@link Constantes#HAUT} ou {@link Constantes#BAS}
	 * @see Constantes
	 */
	public void appuyerBoutonEtage (int numEtage, int hautOuBas) {
		if (hautOuBas == Constantes.HAUT) {
			listeBoutonsHaut.get(numEtage).appuyer(controleurExt);
		}
		else if (hautOuBas == Constantes.BAS) {
			listeBoutonsBas.get(numEtage).appuyer(controleurExt);
		}
		setChanged();
		notifyObservers();
	}
	
	/**permet d'obtenir un {@link Ascenseur} de ce Batiment
	 * @param index l'index de l'{@link Ascenseur}
	 * @return l'{@link Ascenseur} a l'index index.
	 */
	public Ascenseur getAscenseur(int index) {
		return controleursInt.get(index).getAscenseur();
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
	
	/**permet d'obtenir le {@link #controleurExt}
	 * @return {@link #controleurExt}
	 */
	public ControleurExterne getControleurExt () {
		return controleurExt;
	}
	
	/**permet d'obtenir la liste des {@link #controleursInt} de ce Batiment
	 * @return {@link #controleursInt}
	 */
	public ArrayList<ControleurInterne> getControleursInternes () {
		return controleursInt;
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
	
	@Override
	public String toString() {
		return "Batiment [nom=" + nom + ", nbEtages=" + nbEtages
				+ ", nbAscenseur=" + nbAscenseurs + ", controleursInt="
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

	public Ascenseur getAscenseurSelectionne() {
		return ascenseurSelectionne;
	}

	public void setAscenseurSelectionne(Ascenseur ascenseurSelectionne) {
		this.ascenseurSelectionne = ascenseurSelectionne;
		setChanged();
		notifyObservers();
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers();
	} 

}

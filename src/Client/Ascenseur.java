package Client;

import java.util.ArrayList;
import java.util.Observable;

import Boutons.BoutonDestination;
import Boutons.BoutonInterne;
import Boutons.BoutonStop;
import Controleurs.ControleurInterne;
import IHM.FonctionsUtiles;
import Options.GestionnaireOption;
import Options.Option;
import Requetes.RequeteInterne;

/**Description de l'etat de l'Ascenseur. Le traitement des {@link Requetes.Requete} se fait dans le {@link ControleurInterne} correspondant.
 * @author Thomas
 */
public class Ascenseur extends Observable{
	
	/**{@link GestionnaireOption} de cet Ascenseur
	 * @see GestionnaireOption
	 */
	private GestionnaireOption gestionnaireOption;
	
	/**Etage auquel se situe actuellement cet Ascenseur.
	 * 
	 */
	private int etage;
	
	/**Liste des {@link BoutonInterne} situes dans cet Ascenseur.
	 * 
	 */
	private ArrayList<BoutonInterne> listeBoutonsInternes = new ArrayList<BoutonInterne>();

	/**Permet de savoir si cet Ascenseur est en mouvement (true) ou non (false).
	 * 
	 */
	private boolean estEnMouvement;

	/**Permet de savoir si les portes de cet Ascenseur sont fermees (true) ou non (false).
	 * 
	 */
	private boolean portesOuvertes;

	/**Permet de connaitre le poid maximum que peut supporter cet Ascenseur.
	 * 
	 */
	@SuppressWarnings("unused")
	private int poidsMax;

	/**Permet de savoir si cet Ascenseur est vide (true) ou non (false).
	 * 
	 */
	@SuppressWarnings("unused")
	private boolean estVide;

	/**Permet de savoir si cet Ascenseur est bloque (true) ou non (false).
	 * @see BoutonStop
	 * @see RequeteInterne#RequeteInterne()
	 */
	private boolean estBloque;

	/**Permet d'indentifier les differents Ascenseur.
	 * 
	 */
	private int numAscenseur;
	
	/**Construit un Ascenseur et initialise tous ses attributs.
	 * @param nbEtage permet de connaitre le nombre de {@link BoutonDestination} que devrait posseder cet Ascenseur.
	 * @param numAscenseur le numero que l'on attribut a cet Ascenseur.
	 */
	public Ascenseur (int nbEtage, int numAscenseur){
		gestionnaireOption 	= new GestionnaireOption ();
		etage 				= 0;			//un nouvel ascenseur est assemble au rez-de-chaussee (niveau 0)
		estEnMouvement 		= false;		//un nouvel ascenseur est immobile car n'a pas encore recu de requete
		estVide 			= true;			//un nouvel ascenseur ne contient aucun usager
		portesOuvertes 		= false;		//un nouvel acsenseur a les portes fermees
		poidsMax 			= 300;			//parametre par defaut		
		this.numAscenseur 	= numAscenseur;
		for (int i = 0; i <= nbEtage; ++i){
			listeBoutonsInternes.add(new BoutonDestination(FonctionsUtiles.nommerEtage(i), i)); //i est numero de l'etage correspondant au bouton
		} //initialisation des boutons : autant de boutons qu'il y a d'etages
		listeBoutonsInternes.add(new BoutonStop()); // tout ascenseur a un bouton stop
	}

	@Override
	public String toString() {
		return "Ascenseur " + numAscenseur;
	}

	/**Permet de faire bouger concretement l'Ascenseur en modifiant {@link Ascenseur#etage}.
	 * @param etage l'etage ou doit etre l'Ascenseur.
	 */
	public void setEtage(int etage) {
		this.etage = etage;
		setChanged();
		notifyObservers();
	}

	/**Permet d'obtenir {@link Ascenseur#listeBoutonsInternes}
	 * @return {@link Ascenseur#listeBoutonsInternes}
	 */
	public ArrayList<BoutonInterne> getListeBoutons() {
		return listeBoutonsInternes;
	}

	/**Retourne le {@link Ascenseur#gestionnaireOption} de cet Ascenseur
	 * @return {@link Ascenseur#gestionnaireOption}
	 */
	public GestionnaireOption getGestionnaireOption() {
		return gestionnaireOption;
	}

	/**Permet d'obtenir {@link Ascenseur#etage}.
	 * @return {@link Ascenseur#etage}
	 */
	public int getEtage() {
		return etage;
	}

	/**Permet d'obtenir {@link Ascenseur#numAscenseur}.
	 * @return {@link Ascenseur#numAscenseur}
	 */
	public int getNumAsc() {
		return numAscenseur;
	}

	/**Ouvre les portes en modifiant {@link Ascenseur#portesOuvertes} en prenant du temps.
	 * 
	 */
	public void ouvrirPortes () {
		portesOuvertes = true;
		setChanged();
		notifyObservers();
	}
	
	/**Ferme les portes en modifiant {@link Ascenseur#portesOuvertes} en prenant du temps.
	 * 
	 */
	public void fermerPortes () {
		portesOuvertes = false;
		setChanged();
		notifyObservers();
	}

	/**Met l'Ascenseur en mouvement en modifiant {@link Ascenseur#estEnMouvement}
	 * @param estEnMouvement true si doit etre en mouvement false sinon.
	 */
	public void setEstEnMouvement(boolean estEnMouvement) {
		this.estEnMouvement = estEnMouvement;
		setChanged();
		notifyObservers();
	}

	/**Permet d'obtenir {@link Ascenseur#portesOuvertes}.
	 * @return {@link Ascenseur#portesOuvertes}
	 */
	public boolean isPortesOuvertes() {
		return portesOuvertes;
	}
	
	/**Permet d'obtenir {@link Ascenseur#estBloque}.
	 * @return {@link Ascenseur#estBloque}
	 */
	public boolean estBloquer() {
		return estBloque;
	}
	
	/**Permet d'obtenir {@link Ascenseur#estEnMouvement}.
	 * @return {@link Ascenseur#estEnMouvement}
	 */
	public boolean isEstEnMouvement() {
		return estEnMouvement;
	}
	
	/**Bloque l'Ascenseur en modifiant {@link Ascenseur#estBloque}.
	 * 
	 */
	public void bloquer () {
		estEnMouvement = false;
		estBloque = true;
		setChanged();
		notifyObservers();
	}
	
	/**Debloque l'Ascenseur en modifiant {@link Ascenseur#estBloque}.
	 * 
	 */
	public void debloquer () {
		estBloque = false;
		setChanged();
		notifyObservers();
	}
	
	/**Permet d'appuyer sur un {@link BoutonInterne} de cet Ascenseur
	 * @param numBouton {@link BoutonInterne} de cet Ascenseur sur lequel on veut appuyer
	 * @param controleurInterne {@link ControleurInterne} de cet Ascenseur
	 */
	public void appuyerBouton (int numBouton, ControleurInterne controleurInterne) {
		listeBoutonsInternes.get(numBouton).appuyer(controleurInterne);
		setChanged();
		notifyObservers();
	}
	
	/**Active l'{@link Option} de numero numOption
	 * @param numOption le numero de l'{@link Option} a activer
	 */
	public void activerOption (int numOption) {
		gestionnaireOption.activerOption(numOption);
		setChanged();
		notifyObservers();
	}
	
	/**Ajoute une {@link Option} au {@link Ascenseur#gestionnaireOption} de ce Ascenseur
	 * @param option {@link Option} a ajouter
	 */
	public void ajouterOption (Option option) {
		gestionnaireOption.addOption(option);
		setChanged();
		notifyObservers();
	}
	
	/**Supprime une {@link Option} au {@link Ascenseur#gestionnaireOption} de ce Ascenseur
	 * @param option {@link Option} a supprimer
	 */
	public void supprimerOption (Option option) {
		gestionnaireOption.supprimerOption(option);
		setChanged();
		notifyObservers();
	}
}

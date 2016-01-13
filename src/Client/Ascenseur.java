package Client;

import java.util.ArrayList;

import Boutons.BoutonDestination;
import Boutons.BoutonInterne;
import Boutons.BoutonStop;
import Controleurs.ControleurInterne;
import Options.GestionnaireOption;
import Options.IOption;
import Requetes.RequeteInterne;

/**Description de l'etat de l'Ascenseur. Le traitement des {@link Requetes.Requete} se fait dans le {@link ControleurInterne} correspondant.
 * @author Thomas
 */
public class Ascenseur {
	
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
	private ArrayList<BoutonInterne> listeBoutons = new ArrayList<BoutonInterne>();

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
	private int poidsMax;

	/**Permet de savoir si cet Ascenseur est vide (true) ou non (false).
	 * 
	 */
	private boolean estVide;

	/**Permet de savoir si cet Ascenseur est bloque (true) ou non (false).
	 * @see BoutonStop
	 * @see RequeteInterne#RequeteInterne()
	 */
	private boolean estBloque;

	/**Permet d'indentifier les differents Ascenseur.
	 * 
	 */
	private int numAsc;
	
	/**Construit un Ascenseur et initialise tous ses attributs.
	 * @param nbEtage permet de connaitre le nombre de {@link BoutonDestination} que devrait posseder cet Ascenseur.
	 * @param num le numero que l'on attribut a cet Ascenseur.
	 */
	public Ascenseur (int nbEtage, int num){
		gestionnaireOption = new GestionnaireOption ();
		etage = 0;					//un nouvel ascenseur est assemble au rez-de-chaussee (niveau 0)
		estEnMouvement = false;		//un nouvel ascenseur est immobile car n'a pas encore recu de requete
		estVide = true;				//un nouvel ascenseur ne contient aucun usager
		portesOuvertes = false;		//un nouvel acsenseur a les portes fermees
		poidsMax = 300;				//parametre par defaut		
		numAsc = num;
		listeBoutons.add(new BoutonDestination("Rez-de-chausse", 0)); //tout ascenseur a un bouton rez-de chausse
		listeBoutons.add(new BoutonDestination("1er etage", 1)); //tout ascenseur a un bouton 1er etage
		for (int i = 2; i <= nbEtage; ++i){
			listeBoutons.add(new BoutonDestination(i+"e etage", i)); //i est numero de l'etage correspondant au bouton
		} //initialisation des boutons : autant de boutons qu'il y a d'etages
		listeBoutons.add(new BoutonStop()); // tout ascenseur a un bouton stop
	}
	
	/**Construit un Ascenseur et initialise tous ses attributs avec en plus le {@link Ascenseur#poidsMax} en parametre.
	 * @param nbEtage permet de connaitre le nombre de {@link BoutonDestination} que devrait posseder cet Ascenseur.
	 * @param num le numero que l'on attribut a cet Ascenseur.
	 */
	public Ascenseur (int nbEtage, int num, int poidsMax){
		gestionnaireOption = new GestionnaireOption ();
		etage = 0;					//un nouvel ascenseur est assemble au rez-de-chaussee (niveau 0)
		estEnMouvement = false;		//un nouvel ascenseur est immobile car n'a pas encore recu de requete
		estVide = true;				//un nouvel ascenseur ne contient aucun usager
		portesOuvertes = false;		//un nouvel acsenseur a les portes fermees
		this.poidsMax = poidsMax;	//poids max donne par l'utilisateur			
		numAsc = num;
		listeBoutons.add(new BoutonDestination("Rez-de-chausse", 0)); //tout ascenseur a un bouton rez-de chausse
		listeBoutons.add(new BoutonDestination("1er etage", 1)); //tout ascenseur a un bouton 1er etage
		for (int i = 2; i <= nbEtage; ++i){
			listeBoutons.add(new BoutonDestination(i+"e etage", i)); //i est numero de l'etage correspondant au bouton
		} //initialisation des boutons : autant de boutons qu'il y a d'etages
		listeBoutons.add(new BoutonStop()); // tout ascenseur a un bouton stop
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ascenseur [etage=" + etage + ", estEnMouvement=" + estEnMouvement + ", portesOuvertes="
				+ portesOuvertes + ", poidsMax=" + poidsMax + ", estVide="
				+ estVide + ", estBloque=" + estBloque
				+ ", numAsc=" + numAsc + "]";
	} // affichage de l'etat de l'ascenseur

	/**Permet de faire bouger concretement l'Ascenseur en modifiant {@link Ascenseur#etage}.
	 * @param etage l'etage ou doit etre l'Ascenseur.
	 */
	public void setEtage(int etage) {
		this.etage = etage;
	}

	/**Permet d'obtenir {@link Ascenseur#listeBoutons}
	 * @return {@link Ascenseur#listeBoutons}
	 */
	public ArrayList<BoutonInterne> getListeBoutons() {
		return listeBoutons;
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
	
	/**Retourne le {@link Ascenseur#poidsMax} de l'ascenseur
	 * @return {@link Ascenseur#poidsMax}
	 */
	public int getPoidsMax() {
		return poidsMax;
	}

	/**Permet d'obtenir {@link Ascenseur#numAsc}.
	 * @return {@link Ascenseur#numAsc}
	 */
	public int getNumAsc() {
		return numAsc;
	}

	/**Ouvre les portes en modifiant {@link Ascenseur#portesOuvertes} en prenant du temps.
	 * 
	 */
	public void ouvrirPortes () {
		portesOuvertes = true;
	}
	
	/**Ferme les portes en modifiant {@link Ascenseur#portesOuvertes} en prenant du temps.
	 * 
	 */
	public void fermerPortes () {
		portesOuvertes = false;
	}

	/**Met l'Ascenseur en mouvement en modifiant {@link Ascenseur#estEnMouvement}
	 * @param estEnMouvement true si doit etre en mouvement false sinon.
	 */
	public void setEstEnMouvement(boolean estEnMouvement) {
		this.estEnMouvement = estEnMouvement;
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
	}
	
	/**Debloque l'Ascenseur en modifiant {@link Ascenseur#estBloque}.
	 * 
	 */
	public void debloque () {
		estBloque = false;
	}
	
	/**Permet d'appuyer sur un {@link BoutonInterne} de cet Ascenseur
	 * @param numBouton {@link BoutonInterne} de cet Ascenseur sur lequel on veut appuyer
	 * @param controleur {@link ControleurInterne} de cet Ascenseur
	 */
	public void appuyerBouton (int numBouton, ControleurInterne controleur) {
		listeBoutons.get(numBouton).appuyer(controleur);
	}
	
	/**Active l'{@link IOption} de numero numOption
	 * @param numOption le numero de l'{@link IOption} a activer
	 */
	public void activerOption (int numOption) {
		gestionnaireOption.activerOption(numOption);
	}
	
	/**Ajoute une {@link IOption} au {@link Ascenseur#gestionnaireOption} de ce Ascenseur
	 * @param option {@link IOption} a ajouter
	 */
	public void ajouterOption (IOption option) {
		gestionnaireOption.addOption(option);
	}
}

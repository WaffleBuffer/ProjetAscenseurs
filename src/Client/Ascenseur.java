package Client;

import java.util.ArrayList;

import Boutons.BoutonDestination;
import Boutons.BoutonInterne;
import Boutons.BoutonStop;
import Controleurs.ControleurInterne;
import Requetes.RequeteInterne;

/**Description de l'etat de l'Ascenseur. Le traitement des {@link Requete} se fait dans le {@link ControleurInterne} correspondant.
 * @author Thomas
 */
public class Ascenseur {
	
	/**Etage auquel se situe acutellement cet Ascenseur.
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

	/**Permet de connaitre le poid maximume que peut supporter cet Ascenseur.
	 * 
	 */
	private int poidsMax;

	/**Permet de savoir cet Ascenseur est vide (true) ou non (false).
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
	 * @param nbEtage permet de connaitre le nombre de {@link BoutonDestination} que devrair posseder cet Ascenseur.
	 * @param num le numero que l'on attribut a cet Ascenseur.
	 */
	public Ascenseur (int nbEtage, int num){
		etage = 0;					//un nouvel ascenseur est assemble au rez-de-chaussee (niveau 0)
		estEnMouvement = false;		//un nouvel ascenseur est immobile car n'a pas encore recu de requete
		estVide = true;				//un nouvel ascenseur ne contient aucun usager
		portesOuvertes = false;		//un nouvel acsenseur a les portes fermees
		poidsMax = 300;				//parametre par defaut - a changer ou rendre parametrable par l'utilisateur				
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

	/**Permet d'obtenir {@link Ascenseur#etage}.
	 * @return {@link Ascenseur#etage}
	 */
	public int getEtage() {
		return etage;
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
		System.out.println("les portes s'ouvrent");
		try {
			Thread.sleep(1000); //temps d'ouverture des portes
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		portesOuvertes = true;
		System.out.println("les portes sont ouvertes");
	}
	
	/**Ferme les portes en modifiant {@link Ascenseur#portesOuvertes} en prenant du temps.
	 * 
	 */
	public void fermerPortes () {
		System.out.println("les portes se ferment");
		try {
			Thread.sleep(1000); //temps de fermeture des portes
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		portesOuvertes = false;
		System.out.println("les portes sont fermés");
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
		estBloque = true;
	}
	
	/**Debloque l'Ascenseur en modifiant {@link Ascenseur#estBloque}.
	 * 
	 */
	public void debloque () {
		estBloque = false;
	}
}

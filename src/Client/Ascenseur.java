package Client;

import java.util.ArrayList;

import Boutons.Bouton;
import Boutons.BoutonDestination;
import Boutons.BoutonInterne;
import Boutons.BoutonStop;



//Salut, je suis un comm
public class Ascenseur {
	//� quel �tage se trouve  l'ascenseur
	private int etage;
	//liste des boutons présents sur le panneau interne de l'ascenseur
	private ArrayList<BoutonInterne> listeBoutons = new ArrayList<BoutonInterne>();
	//l'ascenseur est-il en mouvement ou bien � l'arr�t
	private boolean estEnMouvement;
	//les portes de l'ascenseur sont-elles ouvertes ou fermées
	private boolean portesOuvertes;
	//quel est le poids maximum en kg que l'ascenseur est cens� pouvoir supporter
	private int poidsMax;
	//l'ascenseur est-il vide
	private boolean estVide;
	//le batiment dans lequel est l'ascenseur
	private Batiment bat;
	//L'ascenseur est-il bloque
	private boolean estBloque;
	//numero de l'ascenseur
	private int numAsc;
	
	
	//constructeur
	public Ascenseur (Batiment bati, int num){
		etage = 0;					//un nouvel ascenseur est assembl� au rez-de-chauss�e (niveau 0)
		estEnMouvement = false;		//un nouvel ascenseur est immobile car n'a pas encore re�u de requ�te
		estVide = true;				//un nouvel ascenseur ne contient aucun usager
		portesOuvertes = false;		//un nouvel acsenseur à les portes fermées
		poidsMax = 300;				//param�tre par d�faut - � changer ou rendre param�trable par l'utilisateur
		bat = bati;					
		numAsc = num;
		listeBoutons.add(new BoutonDestination("Rez-de-chaussé", 0)); //tout ascenseur à un bouton rez-de chaussé
		listeBoutons.add(new BoutonDestination("1er étage", 1)); //tout ascenseur à un bouton 1er etage
		for (int i = 2; i <= bat.getNbEtages(); ++i){
			listeBoutons.add(new BoutonDestination(i+"e étage", i)); //i est numero de l'etage correspondant au bouton
		} //initialisation des boutons : autant de boutons qu'il y a d'étages
		listeBoutons.add(new BoutonStop()); // tout ascenseur à un bouton stop
	}

	@Override
	public String toString() {
		return "Ascenseur [etage=" + etage + ", estEnMouvement=" + estEnMouvement + ", portesOuvertes="
				+ portesOuvertes + ", poidsMax=" + poidsMax + ", estVide="
				+ estVide + ", bat=" + bat + ", estBloque=" + estBloque
				+ ", numAsc=" + numAsc + "]";
	} // affichage de l'etat de l'ascenseur

	public void setEtage(int etage) {
		this.etage = etage;
	}

	public ArrayList<BoutonInterne> getListeBoutons() {
		return listeBoutons;
	}

	public int getEtage() {
		return etage;
	}
	
	public int getNumAsc() {
		return numAsc;
	}

	public void setListeBoutons(ArrayList<BoutonInterne> listeBoutons) {
		this.listeBoutons = listeBoutons;
	}
	
	
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

	public void setEstEnMouvement(boolean estEnMouvement) {
		this.estEnMouvement = estEnMouvement;
	}

	public boolean isPortesOuvertes() {
		return portesOuvertes;
	}
	
	public boolean estBloquer() {
		return estBloque;
	}
	
	public boolean isEstEnMouvement() {
		return estEnMouvement;
	}
	
	
	public void bloquer () {
		estBloque = true;
	}
	
	public void debloque () {
		estBloque = false;
	}
}

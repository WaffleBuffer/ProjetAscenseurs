package Client;

import java.util.ArrayList;

import Boutons.Bouton;
import Boutons.BoutonDestination;
import Boutons.BoutonInterne;



//Salut, je suis un comm
public class Ascenseur {
	//� quel �tage se trouve  l'ascenseur
	private int etage;
	//liste des boutons présents sur le panneau interne de l'ascenseur
	private ArrayList<BoutonInterne> listeBoutons = new ArrayList<BoutonInterne>();
	//l'ascenseur est-il en mouvement ou bien � l'arr�t
	private boolean estEnMouvement;
	//quel est le poids maximum en kg que l'ascenseur est cens� pouvoir supporter
	private int poidsMax;
	//l'ascenseur est-il vide
	private boolean estVide;
	private Batiment bat;
	
	//constructeur
	public Ascenseur (Batiment bati){
		etage = 0;					//un nouvel ascenseur est assembl� au rez-de-chauss�e (niveau 0)
		estEnMouvement = false;		//un nouvel ascenseur est immobile car n'a pas encore re�u de requ�te
		estVide = true;				//un nouvel ascenseur ne contient aucun usager
		poidsMax = 300;				//param�tre par d�faut - � changer ou rendre param�trable par l'utilisateur
		bat = bati;
		for (int i = 0; i <= bat.getNbEtages(); ++i){
			listeBoutons.add(new BoutonDestination("Etage"+i, i)); //i = numero de l'etage correspondant au bouton
		} //initialisation des boutons
	}

	@Override
	public String toString() {
		return "Ascenseur [etage=" + etage + ", estArrete=" + estEnMouvement + ", poidsMax=" + poidsMax + ", estVide="
				+ estVide + "]";
	}

	public void setEtage(int etage) {
		this.etage = etage;
	}

	public ArrayList<BoutonInterne> getListeBoutons() {
		return listeBoutons;
	}

	public void setListeBoutons(ArrayList<BoutonInterne> listeBoutons) {
		this.listeBoutons = listeBoutons;
	}
}
package Controleurs;

import java.util.ArrayList;

import Client.Ascenseur;
import Requetes.Requete;
import Requetes.RequeteInterne;


public class ControleurInterne implements IControleur{
	
	//ascenseur gere par le controleur interne
	private Ascenseur ascenseur;
	//liste des requetes
	private ArrayList<Requete> requetes = new ArrayList<Requete>();
	
	public ControleurInterne (Ascenseur ascenseur) {
		this.ascenseur = ascenseur;
	}
	
	public Ascenseur getAscenceur() {
		return this.ascenseur;
	}
	
	//Fonction permettant de traiter les requetes de l'ascenseur
	public void traiterRequetes(){
		//Si le bouton stop a ete appuyer, alors on ignore le traitement
		if (ascenseur.estBloquer()) {
			return;
		}
		//Parcour de la liste des requetes
		for (int i = 0; i < requetes.size(); ++i) {
			//Si c'est une requete de mouvement
			if (requetes.get(i).getLibelle() == "Allez a l'etage" || 
					requetes.get(i).getLibelle() == "Haut" || 
					requetes.get(i).getLibelle() == "Bas") {
				
				//Si les portes sont ouvertes alors on les fermes
				if (ascenseur.isPortesOuvertes()) {
					ascenseur.fermerPortes();
				}
				
				//On met l'ascenseur en mouvement
				ascenseur.setEstEnMouvement(true);				
				System.out.println("Ascenceur " + ascenseur.getNumAsc() + " va de l'étage : " + ascenseur.getEtage() + " à l'étage " + requetes.get(i).getEtage());
				try {
					//Le temps d'attente correspond a 2 secondes par etage
					Thread.sleep((Math.abs(ascenseur.getEtage() - requetes.get(i).getEtage())) * 2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//L'ascenseur arrive et on retire la requete
				ascenseur.setEtage(requetes.get(i).getEtage());	
				requetes.remove(requetes.get(i));
				
				//L'ascenseur s'arrrete
				System.out.println("l'ascenseur s'arrête");
				ascenseur.setEstEnMouvement(false);
				
				//On ouvre les portes
				ascenseur.ouvrirPortes();
				//temps d'attente avant fermeture automatique des portes
				try {
					//2 secondes
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//On referme les portes
				ascenseur.fermerPortes();
			}
		}
	}
	
	//Ajout d'une Requete specifique
	public void addRequete (Requete requete) {
		requetes.add(requete);
	}
	
	//Ajout d'une RequeteInterne de destination
	public void addRequete (int etage) {
		requetes.add(new RequeteInterne(etage));
	}
	
	//Renvoie le numero d'etage de la prochaine Requete
	public int prochaineDest () {
		if (requetes.size() > 0) {
			return requetes.get(0).getEtage();
		}
		//S'il n'y a pas de requete retourne -1
		else
			return -1;
	}
	
	public int getNumberOfRequete () {
		return requetes.size();
	}

	@Override
	public String toString() {
		return "ControleurInterne [ascenseur=" + ascenseur + ", requetes="
				+ requetes + "]";
	}
}

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
	
	public void traiterRequetes(){
		if (ascenseur.estBloquer()) {
			return;
		}
		for (int i = 0; i < requetes.size(); ++i) {
			if (requetes.get(i).getLibelle() == "Allez � l'�tage" || 
					requetes.get(i).getLibelle() == "Haut" || 
					requetes.get(i).getLibelle() == "Bas") {
				
				if (ascenseur.isPortesOuvertes()) {
					ascenseur.fermerPortes();
				}
				
				ascenseur.setEstEnMouvement(true);				
				System.out.println("Ascenceur va de l'étage : " + ascenseur.getEtage() + " à l'étage " + requetes.get(i).getEtage());
				try {
					Thread.sleep((Math.abs(ascenseur.getEtage() - requetes.get(i).getEtage())) * 2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ascenseur.setEtage(requetes.get(i).getEtage());	
				requetes.remove(requetes.get(i));
				
				System.out.println("l'ascenseur s'arrête");
				ascenseur.setEstEnMouvement(false);
				
				ascenseur.ouvrirPortes();
				//temps d'attente avant fermeture automatique des portes
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ascenseur.fermerPortes();

			}
			/*else if (requetes.get(i).getLibelle() == "Haut") {
				
				ascenseur.fermerPortes();
				ascenseur.setEstEnMouvement(true);
				
				System.out.println("Ascenceur va de l'étage : " + ascenseur.getEtage() + " à l'étage " + requetes.get(i).getEtage());
				ascenseur.setEtage(requetes.get(i).getEtage());
				requetes.remove(requetes.get(i));
				
				ascenseur.setEstEnMouvement(false);
				ascenseur.ouvrirPortes();
				ascenseur.fermerPortes();
			}
			else if (requetes.get(i).getLibelle() == "Bas") {
				
				ascenseur.fermerPortes();
				ascenseur.setEstEnMouvement(true);
				
				System.out.println("Ascenceur va de l'étage : " + ascenseur.getEtage() + " à l'étage " + requetes.get(i).getEtage());
				ascenseur.setEtage(requetes.get(i).getEtage());
				requetes.remove(requetes.get(i));
				
				ascenseur.setEstEnMouvement(false);
				ascenseur.ouvrirPortes();
				ascenseur.fermerPortes();
			}*/
		}
	}
	
	public void addRequete (Requete requete) {
		requetes.add(requete);
	}
	
	public void addRequete (int etage) {
		requetes.add(new RequeteInterne(etage));
	}

	@Override
	public String toString() {
		return "ControleurInterne [ascenseur=" + ascenseur + ", requetes="
				+ requetes + "]";
	}
}

package Controleurs;

import java.util.ArrayList;

import Client.Ascenseur;
import Requetes.Requete;
import Requetes.RequeteInterne;


public class ControleurInterne implements IControleur{
	
	private Ascenseur ascenseur;
	private ArrayList<Requete> requetes = new ArrayList<Requete>();
	
	public ControleurInterne (Ascenseur ascenseur) {
		this.ascenseur = ascenseur;
	}
	
	public void traiterRequetes(){
		for (int i = 0; i < requetes.size(); ++i) {
			if (requetes.get(i).getLibelle() == "Allez � l'�tage" || 
					requetes.get(i).getLibelle() == "Haut" || 
					requetes.get(i).getLibelle() == "Bas") {
				
				if (ascenseur.isPortesOuvertes()) {
					System.out.println("les portes se ferment");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ascenseur.fermerPortes();
					System.out.println("les portes sont fermés");
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
				
				System.out.println("les portes s'ouvrent");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ascenseur.ouvrirPortes();
				System.out.println("les portes sont ouvertes");
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("les portes se ferment");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("les portes sont fermées");
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
}

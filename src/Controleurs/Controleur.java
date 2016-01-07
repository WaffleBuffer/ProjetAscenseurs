package Controleurs;
import Client.Ascenseur;
import Requetes.Requete;


public class Controleur {
	
	//on emp�che la classe d'�tre instanci�e car elle est statique
	private Controleur () {}
	
	public static void traiterRequete(Requete requete, Ascenseur ascenseur){
		if (requete.getLibelle() == "Allez � l'�tage")
			ascenseur.setEtage(requete.getEtage());
	}
}

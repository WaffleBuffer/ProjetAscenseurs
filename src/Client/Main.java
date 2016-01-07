package Client;
import java.util.ArrayList;

import Boutons.Bouton;
import Boutons.BoutonInterne;
import Controleurs.Controleur;
import IHM.MainWindow;
import Requetes.Requete;
import Requetes.RequeteInterne;

public class Main {

	public static void main(String[] args) {
		MainWindow fenetre = new MainWindow();
		Batiment batim = new Batiment("Hotel", 5);
		Ascenseur asc1 = new Ascenseur (batim);
		System.out.println(asc1.toString());
		Requete test = new RequeteInterne (2);
		System.out.println("L'ascenseur va de l'étage" + asc1.getEtage() + " a l'étage" + test.getEtage());
		
		try {
			Thread.sleep(4000);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Controleur.traiterRequete(test, asc1);
		System.out.println(asc1.toString());
		System.out.println(batim.toString());
		ArrayList<BoutonInterne> listeBoutonsAsc1 = new ArrayList<BoutonInterne>();
		listeBoutonsAsc1 = asc1.getListeBoutons();
		for (BoutonInterne i : listeBoutonsAsc1){
			System.out.println(i.toString());
		}
	}

}


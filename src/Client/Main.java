package Client;
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
		Controleur.traiterRequete(test, asc1);
		System.out.println(asc1.toString());
		System.out.println(batim.toString());
	}

}


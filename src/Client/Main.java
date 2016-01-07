package Client;
import Controleurs.Controleur;
import Requetes.Requete;
import Requetes.RequeteInterne;

public class Main {

	public static void main(String[] args) {
		Batiment batim = new Batiment("Hotel", 5);
		Ascenseur asc1 = new Ascenseur (batim);
		System.out.println(asc1.toString());
		Requete test = new RequeteInterne (2);
		Controleur.traiterRequete(test, asc1);
		System.out.println(asc1.toString());
		System.out.println(batim.toString());
	}

}


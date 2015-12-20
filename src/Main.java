
public class Main {

	public static void main(String[] args) {
		Ascenseur asc1 = new Ascenseur ();
		System.out.println(asc1.toString());
		Requete test = new RequeteInterne (2);
		Controleur.traiterRequete(test, asc1);
		System.out.println(asc1.toString());
	}

}

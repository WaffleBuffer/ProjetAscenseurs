
public class Controleur {
	
	//on empêche la classe d'être instanciée car elle est statique
	private Controleur () {}
	
	public static void traiterRequete(Requete requete, Ascenseur ascenseur){
		if (requete.getLibelle() == "Allez à l'étage")
			ascenseur.setEtage(requete.getEtage());
	}
}

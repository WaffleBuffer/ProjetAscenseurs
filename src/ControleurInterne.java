import java.util.ArrayList;


public class ControleurInterne {
	
	//on emp�che la classe d'�tre instanci�e car elle est statique
	private ControleurInterne () {}
	
	private ArrayList<Requete> requetes = new ArrayList<Requete>();
	
	public static void traiterRequete(Requete requete, Ascenseur ascenseur){
		if (requete.getLibelle() == "Allez � l'�tage")
			ascenseur.setEtage(requete.getEtage());
	}
	
	public void addRequete (Requete requete) {
		requetes.add(requete);
	}
}

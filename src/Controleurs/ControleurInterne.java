package Controleurs;

import java.util.ArrayList;

import Client.Ascenseur;
import Requetes.Requete;
import Requetes.RequeteInterne;

/**Designe le traitement des {@link Requete} d'un {@link Ascenseur}. Il existe un ControleurInterne par {@link Ascenseur}.
 * @author Thomas
 */
public class ControleurInterne implements IControleur{
	
	/**{@link Ascenseur} gere par ce ControleurInterne.
	 * 
	 */
	private Ascenseur ascenseur;
	//liste des requetes
	
	/**Liste des {@link Requete} affectees a ce ControleurInterne.
	 * 
	 */
	private ArrayList<Requete> requetes = new ArrayList<Requete>();
	
	/**Construit un ControleurInterne.
	 * @param ascenseur {@link Ascenseur} lie a ce ControleurInterne.
	 */
	public ControleurInterne (Ascenseur ascenseur) {
		this.ascenseur = ascenseur;
	}
	
	/**Renvoie {@link Ascenseur} gere par ce ControleurInterne
	 * @return {@link Ascenseur} gere par ce ControleurInterne
	 */
	public Ascenseur getAscenceur() {
		return this.ascenseur;
	}
	
	/**Fonction permettant de traiter les {@link Requete} de l'{@link Ascenseur} gere par ce ControleurInterne
	 * 
	 */
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
				System.out.println("Ascenceur " + ascenseur.getNumAsc() + " va de l'étage : " + ascenseur.getEtage() + " à l'étage " + requetes.get(i).getEtageDemande());
				try {
					//Le temps d'attente correspond a 2 secondes par etage
					Thread.sleep((Math.abs(ascenseur.getEtage() - requetes.get(i).getEtageDemande())) * 2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//L'ascenseur arrive et on retire la requete
				ascenseur.setEtage(requetes.get(i).getEtageDemande());	
				requetes.remove(requetes.get(i));
				--i;
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
	
	/**Ajout d'une {@link Requete} specifique a {@link ControleurInterne#requetes}
	 * @see Controleurs.IControleur#addRequete(Requetes.Requete)
	 * @see Requete
	 */
	public void addRequete (Requete requete) {
		requetes.add(requete);
	}
	
	/**Ajout d'une {@link RequeteInterne} specifique a {@link ControleurInterne#requetes}
	 * @param etage l'etage de la {@link RequeteInterne}.
	 * @see Controleurs.IControleur#addRequete(Requetes.Requete)
	 * @see RequeteInterne
	 */
	public void addRequete (int etage) {
		requetes.add(new RequeteInterne(etage));
	}
	
	/**Renvoie le numero d'etage de la prochaine {@link Requete}. Si {@link ControleurInterne#requetes} est vide alors renvoie -1.
	 * @return le numero d'etage de la prochaine {@link Requete}. Si {@link ControleurInterne#requetes} est vide alors renvoie -1.
	 */
	public int prochaineDest () {
		if (requetes.size() > 0) {
			return requetes.get(0).getEtageDemande();
		}
		//S'il n'y a pas de requete retourne -1
		else
			return -1;
	}
	
	/**Renvoie le nombre de {@link Requete} dans {@link ControleurInterne#requetes}.
	 * @return le nombre de {@link Requete} dans {@link ControleurInterne#requetes}.
	 */
	public int getNumberOfRequete () {
		return requetes.size();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ControleurInterne [ascenseur=" + ascenseur + ", requetes="
				+ requetes + "]";
	}
}

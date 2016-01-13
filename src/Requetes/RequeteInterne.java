package Requetes;

import Client.Constantes;

/**Description des requetes internes. Sous-classe de {@link Requete}.<br>
 * Correspond aux {@link Boutons.BoutonInterne} se trouvant a l'interieur de l'{@link Client.Ascenseur}.
 * @author Thomas
 */
public class RequeteInterne extends Requete {
	
	/** Construit une RequeteInterne pour demander a l'{@link Client.Ascenseur} d'aller a un etage.<br>
	 * {@link Requete#libelle} = "Aller a l'etage"
	 * @param nouvelEtage Le numero de l'etage correspondant au {@link Boutons.BoutonDestination}.
	 */
	public RequeteInterne (int nouvelEtage){
		super(nouvelEtage, Constantes.DEPLACEMENT);
	}
	
	//constructeur pour l'arret d'urgence
	/**Construit une RequeteInterne pour demander l'arret de l'ascenseur en appuyant sur un {@link Boutons.BoutonStop}<br>
	 * {@link Requete#libelle} = "Arreter l'ascenseur"
	 */
	public RequeteInterne (){
		super (0, Constantes.STOP);
	}

}

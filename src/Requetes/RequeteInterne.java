package Requetes;

/**Description des requetes internes. Sous-classe de {@link Requete}.<br>
 * Correspond aux {@link Boutons.BoutonInterne} se trouvant a l'interieur de l'{@link Ascenseur}.
 * @author Thomas
 */
public class RequeteInterne extends Requete {
	
	/** Construit une RequeteInterne pour demander a l'ascenseur d'aller a un etage.<br>
	 * {@link Requete#libelle} = "Allez a l'etage"
	 * @param nouvelEtage Le numero de l'etage correspondant au {@link Boutons.BoutonDestination}.
	 */
	public RequeteInterne (int nouvelEtage){
		super(nouvelEtage, "Allez a l'etage");
	}
	
	//constructeur pour l'arr�t d'urgence
	/**Construit une RequeteInterne pour demander l'arret de l'ascenseur en appuyant sur un {@link Boutons.BoutonStop}<br>
	 * {@link Requete#libelle} = "Arreter l'ascenseur"
	 */
	public RequeteInterne (){
		super (0, "Arreter l'ascenseur");
	}

}

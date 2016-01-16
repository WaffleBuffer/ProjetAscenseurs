package Requetes;

import Client.Constantes;

/**Description des RequeteInterne.<br>
 * Cree par un {@link Boutons.BoutonInterne} se trouvant a l'interieur d'un {@link Client.Ascenseur}.
 * @author Thomas
 */
public class RequeteInterne extends Requete {
	
	/** Construit une RequeteInterne correspondant a la demande d'un {@link Client.Ascenseur} d'aller a un {@link Boutons.BoutonDestination#etage}.<br>
	 * {@link Requete#libelle} = {@link Constantes#DEPLACEMENT}.
	 * @param nouvelEtage Le numero de l'etage correspondant au {@link Boutons.BoutonDestination#etage}.
	 * @see Boutons.BoutonDestination#appuyer(Controleurs.IControleur)
	 */
	public RequeteInterne (int nouvelEtage){
		super(nouvelEtage, Constantes.DEPLACEMENT);
	}
	
	/**Construit une RequeteInterne correspondant a la demande d'arret d'un {@link Client.Ascenseur} en appuyant sur un {@link Boutons.BoutonStop}<br>
	 * {@link Requete#libelle} = {@link Constantes#STOP}.
	 * @see Boutons.BoutonStop#appuyer(Controleurs.IControleur)
	 */
	public RequeteInterne (){
		super (0, Constantes.STOP);
	}

}

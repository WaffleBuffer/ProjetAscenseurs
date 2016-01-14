package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import Client.Batiment;
import Controleurs.ControleurInterne;
import Requetes.Requete;

/**Vue de la liste des {@link Requete} a traiter par le systeme concernant le {{@link #batiment} de cette FenetreRequetes
 * @author dark1wador
 *
 */
public class FenetreRequetes extends JFrame{

	/**{@link JTextArea} dans laquelle s'ecrie la liste des {@link Requete}
	 */
	private JTextArea requetes = new JTextArea("Liste des requetes:");
	
	/**{@link Batiment} correspondant a cette vue
	 */
	private Batiment batiment;
	
	/**Vient de {@link JFrame}
	 * @see JFrame
	 */
	private static final long serialVersionUID = 4236819230726511089L;

	/**Construit une FenetreRequetes correspondant a un {@link Batiment}
	 * @param batiment le {@link Batiment} correspondant a cette FenetreRequetes
	 */
	public FenetreRequetes (Batiment batiment) {
		this.batiment = batiment;				// le Batiment associe a cette fenetre
		requetes.setText("");					// initialisation du JTextArea
		requetes.setEditable(false);			// on empeche l'utilisateur d'ecrire dans la fenetre de texte
		this.setLayout(new GridLayout(1, 2));	// il est associe a la fenetre principale
		JPanel panelRequete = new JPanel();		// le JPanel contenant le JTextArea
		panelRequete.add(requetes);				// on l'ajoute
		panelRequete.setBackground(Color.WHITE);// on colore le fond en blanc
		// on cree le JPanel principale auquel on affecte un layout
		JPanel panel = new JPanel(new GridLayout(0, 1));			
		// on met une JScrollBar en cas de besoin
		JScrollPane scrollHistorique = new JScrollPane(panelRequete);	
		// On met un cadre avec le titre en haut, au milieu
		panel.setBorder(BorderFactory.createTitledBorder(null, "Liste de requetes", TitledBorder.CENTER, 
				TitledBorder.DEFAULT_POSITION));
		panel.add(scrollHistorique);	// on ajoute la JScrollBar au panel principale
		this.add(panel);				// et on ajoute le panel principale a la fenetre
		
		this.setTitle(batiment.getNom() + " (queries)");		//Titre de la fenetre 
		this.setSize(new Dimension(400, 500));					//taille de la fenetre fixe
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		// Lors d'un changement de taille de la fenetre, on reactualise l'affichage
		this.addComponentListener(new ComponentAdapter ()
		{
			public void componentResized (ComponentEvent event) {
				FenetreRequetes c = (FenetreRequetes)event.getSource();
				c.actualiserText();
			}
		});
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		// la fenetre apparait au milieu, a droite de l'ecran
		this.setLocation(width - this.getWidth(), height/2 - this.getHeight()/2);
		this.setVisible(true);									//la fenetre apparaît
	}
	
	/**Actualise le text dans le {@link #requetes} de cette FenetreRequetes
	 */
	public void actualiserText () {
		// Reinitialisation du JTextArea
		requetes.setText("");
		String tiretsAffichage = "";	// variable d'affichage
		String egalesAffichage = "";	// variable d'affichage
		// configuration des variables d'affichage sela la taille de la JFrame
		for (int i = 0; i < this.getSize().width / 11; ++i) {
			tiretsAffichage += "-";
			if (0 == i % 2) {
				egalesAffichage += "=";
			}
		}
		// Affichage des RequeteExterne du Batiment
		requetes.append("\n" + tiretsAffichage + "External queries" + tiretsAffichage + "\n");
		for (Requete i : batiment.getControleurExt().getRequetes()) {
			requetes.append(i.toString() + "\n");
		}
		
		// Affichage des RequeteInterne
		requetes.append("\n" + tiretsAffichage + "Internal queries" + tiretsAffichage + "\n");
		//Parcour des ControleurInterne du Batiment
		for (ControleurInterne i : batiment.getControleursInterne()) {
				if (0 != i.getNumberOfRequete()) {
					requetes.append("\n====" + egalesAffichage + 
							"[Lift " + i.getAscenceur().getNumAsc() + "]" + egalesAffichage +"====\n");
					for (Requete j : i.getRequetes()) {		
						requetes.append("\n" + j.toString());
					}
	
					requetes.append("\n====" + egalesAffichage  + egalesAffichage + "========\n");
				}
		}
		
	}
}

package IHM;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Client.Batiment;
import Controleurs.ControleurInterne;
import Requetes.Requete;

/**Vue de la liste des {@link Requete} a traiter par le systeme concernant le {{@link #batiment} de cette FenetreRequetes
 * @author dark1wador
 */
public class FenetreRequetes extends JFrame implements Observer{

	/**{@link JTextArea} dans laquelle s'ecrit la liste des {@link Requetes.RequeteInterne}
	 */
	private JTextArea AffichageRequetesInternes = new JTextArea();
	
	/**{@link JTextArea} dans laquelle s'ecrit la liste des {@link Requetes.RequeteExterne}
	 */
	private JTextArea AffichageRequetesExternes = new JTextArea();
	
	/**{@link JTextArea} dans laquelle s'ecrit l'historique de la derniere iteration
	 * @see Batiment#ResultatDerniereIteration
	 */
	private JTextArea AffichageHistorique = new JTextArea();
	
	/**{@link Batiment} correspondant a cette vue
	 */
	private Batiment batiment;
	
	/**Vient de {@link JFrame}
	 * @see JFrame
	 */
	private static final long serialVersionUID = 0L;

	/**Construit une FenetreRequetes correspondant a un {@link Batiment}
	 * @param batiment le {@link Batiment} correspondant a cette FenetreRequetes
	 */
	public FenetreRequetes (Batiment batiment) {
		// le Batiment associe a cette fenetre
		this.batiment = batiment;						
		
		// on empeche l'utilisateur d'ecrire dans les fenetres de texte
		AffichageRequetesInternes.setEditable(false);	
		AffichageRequetesExternes.setEditable(false);
		AffichageHistorique.setEditable(false);
		
		this.setLayout(new GridLayout(3, 1, 0, 10));	// il est associe a la fenetre principale
		
		JPanel panelRequeteInterne = new JPanel();			// le JPanel contenant le JTextArea
		panelRequeteInterne.add(AffichageRequetesInternes);	// on l'ajoute
		JScrollPane scrollRequeteInterne = new JScrollPane(panelRequeteInterne);
		JPanel panelRequeteInternePrincipale = new JPanel(new GridLayout());
		panelRequeteInternePrincipale.add(scrollRequeteInterne);
		panelRequeteInternePrincipale.setBorder(BorderFactory.createTitledBorder("Internal queries"));
		this.add(panelRequeteInternePrincipale);
		
		JPanel panelRequeteExterne = new JPanel();
		panelRequeteExterne.add(AffichageRequetesExternes);
		JScrollPane scrollRequeteExterne = new JScrollPane(panelRequeteExterne);
		JPanel panelRequeteExternePrincipale = new JPanel(new GridLayout());
		panelRequeteExternePrincipale.setBorder(BorderFactory.createTitledBorder("External queries"));
		panelRequeteExternePrincipale.add(scrollRequeteExterne);
		this.add(panelRequeteExternePrincipale);
		
		// on cree le JPanel de l'historique auquel on affecte un layout
		JPanel panelHistorique = new JPanel();	
		panelHistorique.add(AffichageHistorique);
		// on met une JScrollPane en cas de besoin
		JScrollPane scrollHistorique = new JScrollPane(panelHistorique);
		JPanel panelHistoriquePrincipale = new JPanel(new GridLayout());
		panelHistoriquePrincipale.setBorder(BorderFactory.createTitledBorder("Historic"));
		panelHistoriquePrincipale.add(scrollHistorique);
		this.add(panelHistoriquePrincipale);
		
		// Lors d'un changement de taille de la fenetre, on reactualise l'affichage
		this.addComponentListener(new ComponentAdapter ()
		{
			public void componentResized (ComponentEvent event) {
				FenetreRequetes c = (FenetreRequetes)event.getSource();
				c.update(null, null);
			}
		});
		
		this.setTitle(batiment.getNom() + " (queries)");		//Titre de la fenetre 
		this.setSize(new Dimension(400, 500));					//taille de la fenetre fixe
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		// la fenetre apparait au milieu, a droite de l'ecran
		this.setLocation(width - this.getWidth(), height/2 - this.getHeight()/2);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setVisible(true);									//la fenetre apparait
	}

	/**Actualise le text dans le {@link #AffichageRequetesInternes} de cette FenetreRequetes
	 */
	@Override
	public void update(Observable o, Object arg) {
		// Reinitialisation des JTextArea
		AffichageRequetesInternes.setText("");
		AffichageRequetesExternes.setText("");
		
		// Affichage des RequeteExterne du Batiment
		for (Requete i : batiment.getControleurExt().getRequetes()) {
			AffichageRequetesExternes.append(i.toString() + "\n");
		}
	
		String egalesAffichage = "";	// variable d'affichage
		String etoilesAffichage = "";   // variable d'affichage
		// configuration des variables d'affichage selon la taille de la JFrame
		for (int i = 0; i < this.getSize().width / 11; ++i) {
			
			if (0 == i % 2) {
				egalesAffichage += "=";
				etoilesAffichage += "*";
			}
		}
		
		if (o != null && o.getClass() == Batiment.class && 
				null != batiment.getResultatDerniereIteration() && "" != batiment.getResultatDerniereIteration()) {
			AffichageHistorique.append(etoilesAffichage + Calendar.getInstance().getTime() + etoilesAffichage + "\n");
			AffichageHistorique.append(batiment.getResultatDerniereIteration() + "\n");
		}
		
		// Affichage des RequeteInterne du Batiment
		//Parcour des ControleurInterne du Batiment
		for (ControleurInterne i : batiment.getControleursInternes()) {
				if (0 != i.getNumberOfRequete()) {
					AffichageRequetesInternes.append("\n====" + egalesAffichage + 
							"[Lift " + i.getAscenseur().getNumAsc() + "]" + egalesAffichage +"====\n");
					for (Requete j : i.getRequetes()) {		
						AffichageRequetesInternes.append("\n" + j.toString());
					}
	
					AffichageRequetesInternes.append("\n====" + egalesAffichage  + egalesAffichage + "========\n");
				}
		}
	}
}

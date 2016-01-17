package IHM;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Client.Ascenseur;
import Client.Batiment;
import Options.Option;
import Options.OptionMusique;

public class FenetreOption extends JFrame implements Observer{
	
	Batiment batiment;
	private JList<Ascenseur> listeAscenseur;
	private JList<Option> listeOptionsAscenseur;
	
	public FenetreOption (Batiment batiment) {
		this.batiment = batiment;
		
		GridBagLayout gb = new GridBagLayout();
		setLayout(gb);
		
		GridBagConstraints listConstraint = new GridBagConstraints();
		listConstraint.fill = GridBagConstraints.VERTICAL;
		listConstraint.weightx = 0.5;
		listConstraint.weighty = 2;
		listConstraint.gridx = 0;
		listConstraint.gridy = 0;
		listConstraint.gridheight = 2;
		listConstraint.ipadx = 2;
		listConstraint.ipady = 0;
		
		GridBagConstraints buttonConstraint = new GridBagConstraints();
		buttonConstraint.fill = GridBagConstraints.NONE;
		buttonConstraint.weightx = 0;
		buttonConstraint.weighty = 0;
		buttonConstraint.gridx = 2;
		buttonConstraint.gridy = 1;
		buttonConstraint.ipadx = 23;
		buttonConstraint.ipady = 0;
		buttonConstraint.insets = new Insets(0, 0, 80, 0);
		buttonConstraint.anchor = GridBagConstraints.CENTER;
		
		Ascenseur[] ascenseurs = new Ascenseur[batiment.getControleursInternes().size()];
		for (int i = 0; i < batiment.getControleursInternes().size(); ++i) {
			ascenseurs[i] = batiment.getControleursInternes().get(i).getAscenseur();
		}
		
		this.listeAscenseur = new JList<Ascenseur>(ascenseurs);
		listeAscenseur.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listeAscenseur.setLayoutOrientation(JList.VERTICAL);
		listeAscenseur.setVisibleRowCount(-1);
		listeAscenseur.setPreferredSize(new Dimension (100, 50));
		listeAscenseur.setSelectedIndex(0);
		
		JScrollPane ascenseursPanel = new JScrollPane(listeAscenseur); // panneau des ascenseurs a gauche
		ascenseursPanel.setPreferredSize(new Dimension(250, 80));
		ascenseursPanel.setBorder(BorderFactory.createTitledBorder(null, "Lift list", TitledBorder.CENTER, 
				TitledBorder.DEFAULT_POSITION));
		
		Option[] optionsDispo = new Option[1];
		optionsDispo[0] = new OptionMusique("");
		
		final JList<Option> listeOptions = new JList<Option>(optionsDispo);
		listeOptions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listeOptions.setLayoutOrientation(JList.VERTICAL);
		listeOptions.setVisibleRowCount(-1);
		listeOptions.setPreferredSize(new Dimension (100, 50));
		if (optionsDispo.length > 0) {
			listeOptions.setSelectedIndex(0);
		}
		
		JScrollPane optionsPanel = new JScrollPane(listeOptions); // panneau des options vers le centre
		optionsPanel.setPreferredSize(new Dimension(250, 80));
		optionsPanel.setBorder(BorderFactory.createTitledBorder(null, "Options list", TitledBorder.CENTER, 
				TitledBorder.DEFAULT_POSITION));
		
		this.listeOptionsAscenseur = new JList<Option>();
		listeOptionsAscenseur.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listeOptionsAscenseur.setLayoutOrientation(JList.VERTICAL);
		listeOptionsAscenseur.setVisibleRowCount(-1);
		listeOptionsAscenseur.setPreferredSize(new Dimension (100, 50));
		JScrollPane optionsAscenseurPanel = new JScrollPane(listeOptionsAscenseur); // panneau des options vers le centre
		optionsAscenseurPanel.setBorder(BorderFactory.createTitledBorder(null, "Lift's options list", TitledBorder.CENTER, 
				TitledBorder.DEFAULT_POSITION));
		
		listeAscenseur.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				update(null, null);
			}
		});
		
		JButton addButton = new JButton ("add");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listeAscenseur.getSelectedValue().ajouterOption(listeOptions.getSelectedValue());
			}
		});
		
		JButton suppressionButton = new JButton ("remove");
		suppressionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (listeOptionsAscenseur.getSelectedValue() != null) {
					int selectedindex = listeOptionsAscenseur.getSelectedIndex();
					listeAscenseur.getSelectedValue().supprimerOption((listeOptionsAscenseur.getSelectedValue()));
					Option[] optionsDispo = new Option[listeAscenseur.getSelectedValue().getGestionnaireOption().getOptions().size()];
					for (int i = 0; i < optionsDispo.length; ++i) {
						optionsDispo[i] = listeAscenseur.getSelectedValue().getGestionnaireOption().getOption(i);
					}
					if (selectedindex < optionsDispo.length && optionsDispo[selectedindex] != null) {
						listeOptionsAscenseur.setSelectedIndex(selectedindex);
					}		
				}
			}
		});
		
		JButton activationButton = new JButton ("acitvate");
		activationButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (listeOptionsAscenseur.getSelectedValue() != null) {
					listeAscenseur.getSelectedValue().getGestionnaireOption().activerOption(listeOptionsAscenseur.getSelectedIndex());
				}
			}
		});
		
		this.add(ascenseursPanel, listConstraint);
		listConstraint.gridx = 1;
		this.add(optionsPanel, listConstraint);
		this.add(addButton, buttonConstraint);
		buttonConstraint.ipadx = 0;
		buttonConstraint.ipady = 0;
		buttonConstraint.insets = new Insets(0, 0, 0, 0);
		this.add(suppressionButton, buttonConstraint);	
		buttonConstraint.insets = new Insets(80, 0, 0, 0);
		this.add(activationButton, buttonConstraint);
		listConstraint.gridx = 3;
		this.add(optionsAscenseurPanel, listConstraint);
		this.setTitle(batiment.getNom() + " (options)");		//Titre de la fenetre 
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setSize(new Dimension(900, 500));					//taille de la fenetre fixe
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		// la fenetre apparait au milieu, a droite de l'ecran
		this.setLocation(width - this.getWidth(), height - this.getHeight());
		this.setVisible(true);									//la fenetre apparaît
	}

	@Override
	public void update(Observable o, Object arg) {
		Option[] optionsDispo = new Option[listeAscenseur.getSelectedValue().getGestionnaireOption().getOptions().size()];
		for (int i = 0; i < optionsDispo.length; ++i) {
			optionsDispo[i] = listeAscenseur.getSelectedValue().getGestionnaireOption().getOption(i);
		}
		listeOptionsAscenseur.setListData(optionsDispo);	
	}

}

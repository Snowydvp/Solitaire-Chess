package Projet.IHM;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import javax.swing.*;

import Projet.Controleur;
import Projet.Metier.Niveau;

/**
 * Gère l'affichage de la séléction des défis si ils sont accessible ou non.
 * @author BELLANGER Jessy, LINTOT Maxime, PICOT Maxence et SINAEVE Antoine
 *
 */
public class MenuDefi extends JPanel implements ActionListener 
{
	private static final long serialVersionUID = 1L;
	private JRadioButton debutant;
	private JRadioButton intermediaire;
	private JRadioButton avance;
	private JRadioButton expert;
    private JRadioButton edite;

	private JPanel listeDebutant;
	private JPanel listeIntermediaire;
	private JPanel listeAvance;
	private JPanel listeExpert;
    private JPanel listeEdite;

	private JButton retourMenu;
	
    private JMenu menuPrincipal;
	private JMenu menuAutre;
    
	private JMenuItem quitterMenu;
	private JMenuItem retourMenuItem;
	private JMenuItem relges;

	private Controleur ctrl;
	private Fenetre fenetre;
	
	/**
	 * Constructeur par défaut
	 * @param ctrl est le controleur
	 * @param fenetre est la frame principale
	 * 
	 */
	public MenuDefi(Controleur ctrl, Fenetre fenetre)
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		this.fenetre = fenetre;
		this.ctrl = ctrl;

		this.listeDebutant = initialisePanel(this.ctrl.getNiveauDebutant(), "Debutant");
		this.listeIntermediaire = initialisePanel(this.ctrl.getNiveauIntermediaire(), "Intermediaire");
		this.listeAvance = initialisePanel(this.ctrl.getNiveauAvance(), "Avance");
		this.listeExpert = initialisePanel(this.ctrl.getNiveauExpert(), "Expert");
        this.listeEdite = initialisePanel(this.ctrl.getNiveauEdite(), "Edite");


		JPanel difficulte = new JPanel();
		difficulte.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				"Niveaux",0,0,new Font("Dialog", 1, 12),Color.BLACK
		));

		debutant = new JRadioButton("Défis débutant", true);
		debutant.addActionListener(this);
		debutant.setMnemonic(KeyEvent.VK_NUMPAD1);
		difficulte.add(debutant);

		intermediaire = new JRadioButton("Défis intermédiaire");
		intermediaire.addActionListener(this);
		intermediaire.setMnemonic(KeyEvent.VK_NUMPAD2);
		difficulte.add(intermediaire);

		avance = new JRadioButton("Défis avancé");
		avance.addActionListener(this);
		avance.setMnemonic(KeyEvent.VK_NUMPAD3);
		difficulte.add(avance);

        expert = new JRadioButton("Défis expert");
        expert.addActionListener(this);
        expert.setMnemonic(KeyEvent.VK_NUMPAD4);
        difficulte.add(expert);
        
        edite = new JRadioButton("Défis édité");
        edite.addActionListener(this);
        edite.setMnemonic(KeyEvent.VK_NUMPAD5);
        difficulte.add(edite);

		this.add(difficulte, BorderLayout.NORTH);

		this.add(listeDebutant);
        this.listeDebutant.setVisible(true);
		this.add(listeIntermediaire);
		this.add(listeAvance);
		this.add(listeExpert);
        this.add(listeEdite);

		ButtonGroup group = new ButtonGroup();
		group.add(debutant);
		group.add(intermediaire);
		group.add(avance);
		group.add(expert);
		group.add(edite);

        JPanel menu = new JPanel();
		this.retourMenu = new JButton("Retour au Menu");
		this.retourMenu.addActionListener(this);
        menu.add(retourMenu);
        this.add(menu);
	}
	
	/**
	 * Initialise les panels en fonction de la difficulté.
	 * @param alNiveau est l'ArrayList des niveaux de la difficulté.
	 * @param s est le nom de la difficulté.
	 * @return Panel contenant le bouton d'accès aux défis de la difficulté	
	 */
	public JPanel initialisePanel(ArrayList<Niveau> alNiveau, String s) 
	{
		JPanel tmp = new JPanel(new GridLayout(0, 5, 10, 10));
		tmp.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				s,0,0,new Font("Dialog", 1, 12),Color.BLACK
		));
		
		for(int j = 0; j < alNiveau.size(); j++) 
		{
			JButton buttonTmp = new JButton(alNiveau.get(j).getNumNiveau()+"");
            buttonTmp.setSize(55, 26);
            buttonTmp.setName(s);
            buttonTmp.addActionListener(this);
			if(this.ctrl.getPartie().peutJouerNiveau(s,alNiveau.get(j).getNumNiveau()))
				buttonTmp.setBackground(Color.WHITE);
			else 
			{
                buttonTmp.setBackground(Color.GRAY);
                buttonTmp.setEnabled(false);
            }
			
			tmp.add(buttonTmp);
			tmp.setVisible(false);
		}
		return tmp;
	}

	/**
	 * Méthode réecrite de l'interface ActionListener: Gère les actions liés aux composants du panel
	 * @param e est l'evenement.
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		this.listeDebutant.setVisible(false);
		this.listeIntermediaire.setVisible(false);
		this.listeAvance.setVisible(false);
		this.listeExpert.setVisible(false);
        this.listeEdite.setVisible(false);


		if(e.getSource() == this.debutant)
			this.listeDebutant.setVisible(true);
		else if(e.getSource() == this.intermediaire)
			this.listeIntermediaire.setVisible(true);
		else if(e.getSource() == this.avance)
			this.listeAvance.setVisible(true);
		else if(e.getSource() == this.expert)
			this.listeExpert.setVisible(true);
        else if(e.getSource() == this.edite)
            this.listeEdite.setVisible(true);
		else if(e.getSource() == this.retourMenu || e.getSource() == this.retourMenuItem)
			this.fenetre.afficherMenu(this);
		else if(e.getSource() == this.relges)
		{
			File html = new File("html/regles.html");
			URI uri = html.toURI();
			try {Desktop.getDesktop().browse(uri);} 
			catch (IOException e1) {e1.printStackTrace();}
		}
		else if(e.getSource() == this.quitterMenu)
			System.exit(0);
		else if(e.getSource() instanceof JButton)
		{
			JButton tmp = (JButton) e.getSource();
			
			int num = Integer.parseInt(tmp.getText());
			String difficulte = tmp.getName();
			
			if ( this.edite.isSelected()) this.ctrl.setNiveau(new Niveau(num-1));
			else this.ctrl.setNiveau(new Niveau(num, difficulte));
			
        	Jeu j = new Jeu(this.ctrl, this.fenetre);
        	this.fenetre.afficherJeu(this, j);
		}
		
		this.fenetre.pack();
	}
	
	/**
	 * Définit la barre de menu de la fenetre.
	 * 
	 */
    public void setMenuBarre()
    { 
    	JMenuBar menuRacourci = new JMenuBar();
    	
        this.menuPrincipal = new JMenu("Menu principal");
        this.retourMenuItem = new JMenuItem("Retour menu");
        this.retourMenuItem.addActionListener(this);
        this.retourMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        this.menuPrincipal.add(this.retourMenuItem);
        
        this.quitterMenu = new JMenuItem("Quitter");
        this.quitterMenu.addActionListener(this);
        this.quitterMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        this.menuPrincipal.add(this.quitterMenu);
        
        this.menuAutre = new JMenu("Autres");
        this.relges = new JMenuItem("Règles");
        this.relges.addActionListener(this);
        this.relges.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        this.menuAutre.add(this.relges);
        
        menuRacourci.add(this.menuPrincipal);
        menuRacourci.add(this.menuAutre);
        
        this.fenetre.setJMenuBar(menuRacourci);
    }
}

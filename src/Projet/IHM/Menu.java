package Projet.IHM;

import Projet.Controleur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.swing.*;

/**
 * Menu principale, c'est ici que sont définit tout les boutons pour naviguer
 * dans l'application.
 * @author BELLANGER Jessy, LINTOT Maxime, PICOT Maxence et SINAEVE Antoine
 *
 */
public class Menu extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private Fenetre fenetre;
    private Controleur ctrl;

    private JButton continuer, nouvellePartie, choisirNiveau, creerDefi, regle, options, quitter;
    
	private JMenu menuPrincipal;
	private JMenu menuPartie;
	private JMenu menuNiveau;
	private JMenu menuAutre;
	
	private JMenuItem quitterMenu;
	private JMenuItem nouvellePartieMenu;
	private JMenuItem continuerPartie;
	private JMenuItem editeurDefi;
	private JMenuItem selectionDefi;
	private JMenuItem optionsMenu;
	private JMenuItem regles;

	/**
	 * Constructeur par défaut
	 * @param ctrl est le controleur
	 * @param fenetre est la frame principale
	 * 
	 */
    public Menu(Controleur ctrl, Fenetre fenetre)
    {
        this.ctrl    = ctrl;
        this.fenetre = fenetre;
        this.fenetre.setTitle("Solitaire Chess - Menu");
        this.setMenuBarre();

        this.setLayout(new BorderLayout());

        JPanel espaceLogo = new JPanel(new BorderLayout());
        espaceLogo.add(new JLabel(new ImageIcon("Images/Interface/logo.png")));
        espaceLogo.add(new JLabel("Que voulez-vous faire ?", JLabel.CENTER), BorderLayout.SOUTH);
        this.add(espaceLogo);

        JPanel espaceBoutons = new JPanel(new BorderLayout());
        JPanel espaceBoutonsGauche = new JPanel(new GridLayout(3, 1));
        JPanel espaceBoutonsGaucheHaut = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel espaceBoutonsGaucheBas  = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel espaceBoutonsDroite = new JPanel(new GridLayout(3, 1));
        JPanel espaceBoutonsDroiteHaut = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel espaceBoutonsDroiteMilieu = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel espaceBoutonsDroiteBas = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        espaceBoutons.add(espaceBoutonsGauche, BorderLayout.WEST);
        espaceBoutonsGauche.add(espaceBoutonsGaucheHaut);
        espaceBoutonsGauche.add(espaceBoutonsGaucheBas);
        espaceBoutonsGauche.add(new JPanel());
        espaceBoutons.add(espaceBoutonsDroite, BorderLayout.EAST);
        espaceBoutonsDroite.add(espaceBoutonsDroiteHaut);
        espaceBoutonsDroite.add(espaceBoutonsDroiteMilieu);
        espaceBoutonsDroite.add(espaceBoutonsDroiteBas);

        this.add(espaceBoutons, BorderLayout.SOUTH);

        this.nouvellePartie = new JButton("Nouvelle partie");
        this.nouvellePartie.addActionListener(this);
        espaceBoutonsGaucheHaut.add(this.nouvellePartie);

        this.continuer = new JButton("Continuer");
        this.continuer.addActionListener(this);
        espaceBoutonsGaucheHaut.add(this.continuer);

        this.creerDefi = new JButton("Créer un défi");
        this.creerDefi.addActionListener(this);
        espaceBoutonsGaucheBas.add(this.creerDefi);

        this.choisirNiveau = new JButton("Choisir défi");
        this.choisirNiveau.addActionListener(this);
        espaceBoutonsGaucheBas.add(this.choisirNiveau);

        this.options = new JButton("Options");
        this.options.setPreferredSize(new Dimension(80, 26));
        this.options.addActionListener(this);
        espaceBoutonsDroiteHaut.add(this.options);

        this.regle = new JButton("Règles");
        this.regle.setPreferredSize(new Dimension(80, 26));
        this.regle.addActionListener(this);
        espaceBoutonsDroiteMilieu.add(this.regle);

        this.quitter = new JButton("Quitter");
        this.quitter.setPreferredSize(new Dimension(80, 26));
        this.quitter.addActionListener(this);
        espaceBoutonsDroiteBas.add(this.quitter);
    }

	/**
	 * Méthode permettant de définir les différentes actions.
	 * @param e est l'evenement.
	 * 
	 */
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.quitter || e.getSource() == this.quitterMenu)
            System.exit(0);

        if(e.getSource() == this.continuer || e.getSource() == this.continuerPartie) 
        {
            Jeu j = new Jeu(this.ctrl, this.fenetre);
            this.fenetre.afficherJeu(this,j);
        }
        else
        	if(e.getSource() == this.creerDefi || e.getSource() == this.editeurDefi) 
        	{
	            Editeur ed = new Editeur(this.ctrl, this.fenetre);
	            this.fenetre.afficherEditeur(this,ed);
        	}
        	else 
        		if(e.getSource() == this.nouvellePartie || e.getSource() == this.nouvellePartieMenu)
        		{
		        	this.ctrl.creerPartie();
		        	Jeu j = new Jeu(this.ctrl, this.fenetre);
		        	this.fenetre.afficherJeu(this,j);
        		}
        		else 
        			if(e.getSource() == this.choisirNiveau || e.getSource() == this.selectionDefi)
        			{
			        	MenuDefi mD = new MenuDefi(this.ctrl, this.fenetre);
			        	this.fenetre.afficherMenuDefi(this,mD);
        			}
        			else
    					if(e.getSource() == this.options || e.getSource() == this.optionsMenu) 
    					{
    						new GestionOptions(ctrl, fenetre);
    					}
    					else
    						if(e.getSource() == this.regle || e.getSource() == this.regles)
    						{
    							File html = new File("html/regles.html");
        						URI uri = html.toURI();
        						try {
									Desktop.getDesktop().browse(uri);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
    						}
    }
    
	/**
	 * Méthode permettant de définir la barre de menu de la fenetre.
	 * 
	 */
    public void setMenuBarre()
    {
    	JMenuBar menuRacourci = new JMenuBar();
    	
        this.menuPrincipal = new JMenu("Menu principal");
        this.quitterMenu = new JMenuItem("Quitter");
        this.quitterMenu.addActionListener(this);
        this.quitterMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        this.menuPrincipal.add(this.quitterMenu);
        
        this.menuPartie = new JMenu("Partie");
        this.continuerPartie = new JMenuItem("Continuer");
        this.continuerPartie.addActionListener(this);
        this.continuerPartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, ActionEvent.CTRL_MASK));
        this.menuPartie.add(this.continuerPartie);
        this.nouvellePartieMenu = new JMenuItem("Nouvelle partie");
        this.nouvellePartieMenu.addActionListener(this);
        this.nouvellePartieMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        this.menuPartie.add(this.nouvellePartieMenu);
        
        this.menuNiveau = new JMenu("Niveau");
        this.selectionDefi = new JMenuItem("Séléction défis");
        this.selectionDefi.addActionListener(this);
        this.selectionDefi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        this.menuNiveau.add(this.selectionDefi);
        this.editeurDefi = new JMenuItem("Editeur défis");
        this.editeurDefi.addActionListener(this);
        this.editeurDefi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        this.menuNiveau.add(this.editeurDefi);
        
        this.menuAutre = new JMenu("Autres");
        this.regles = new JMenuItem("Règles");
        this.regles.addActionListener(this);
        this.regles.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        this.menuAutre.add(this.regles);
        this.optionsMenu = new JMenuItem("Options");
        this.optionsMenu.addActionListener(this);
        this.optionsMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        this.menuAutre.add(this.optionsMenu);
        
        menuRacourci.add(this.menuPrincipal);
        menuRacourci.add(this.menuPartie);
        menuRacourci.add(this.menuNiveau);
        menuRacourci.add(this.menuAutre);
        
        this.fenetre.setJMenuBar(menuRacourci);
    }
}
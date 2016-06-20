package Projet.IHM;

import Projet.Controleur;
import Projet.IHM.Projet.Usine;
import Projet.Metier.Aide;
import Projet.Metier.Plateau;
import Projet.Metier.Pieces.*;

import java.util.ArrayList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 * Gère le mode Jeu avec le système de score.
 * @author BELLANGER Jessy, LINTOT Maxime, PICOT Maxence et SINAEVE Antoine
 *
 */
public class Jeu extends BaseFenetre implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private JButton suivant;
    private JButton precedent;
    private JButton rejouer;
    private JButton menu;
    private JButton aide;
    private JButton annuler;
    
    private JLabel compteur;
    private JLabel compteurTotal;
    private JLabel difficulte;
    private JLabel numNiveau;
    
    private int nbCoupsDefi;
    
    private JMenu menuPrincipal;
	private JMenu menuAutre;
    private JMenu menuCorrection;
    private JMenu menuNiveau;
    
	private JMenuItem quitterMenu;
	private JMenuItem retourMenu;
	private JMenuItem annulerCoup;
	private JMenuItem rejouerDefi;
	private JMenuItem relges;
	private JMenuItem defiSuivant;
	private JMenuItem defiPrecedent;
	private JMenuItem aideJeu;
	
	/**
	 * Constructeur par défaut
	 * @param ctrl est le controleur
	 * @param fenetre est la frame principale
	 * 
	 */
    public Jeu(Controleur ctrl, Fenetre fenetre)
    {
    	super(ctrl,fenetre);
        this.ctrl.setPlateau(new Plateau(this.ctrl.getNiveau().getPiece()));
        this.nbCoupsDefi = 0;

        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        
        JPanel outils = new JPanel();

        this.menu     = Usine.creerBoutonAvecImage("Images/Interface/menu.png"   , "Retour au menu"         , this);
        this.annuler  = Usine.creerBoutonAvecImage("Images/Interface/annuler.png", "Annuler le dernier coup", this);
        this.rejouer  = Usine.creerBoutonAvecImage("Images/Interface/rage.png"   , "Rejouer ce défi"        , this);
        this.aide     = Usine.creerBoutonAvecImage("Images/Interface/aide.png"   , "Active l'aide du jeu" , this);

        outils.add(this.menu);
        outils.add(this.annuler);
        outils.add(this.rejouer);
        outils.add(this.aide);


        JPanel panelLabelHaut = new JPanel(new BorderLayout());
        panelLabelHaut.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        this.difficulte = new JLabel();
        this.numNiveau = new JLabel();

        panelLabelHaut.add(this.difficulte, BorderLayout.WEST);
        panelLabelHaut.add(this.numNiveau, BorderLayout.EAST);


        JPanel centre = new JPanel(new BorderLayout());
        centre.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        this.grille = new JPanel(new GridLayout(4, 4));
        this.grille.addMouseListener(this);

        JPanel panelLabelBas = new JPanel(new BorderLayout());

        this.compteur = new JLabel();
        this.compteurTotal = new JLabel();

        panelLabelBas.add(this.compteur, BorderLayout.WEST);
        panelLabelBas.add(this.compteurTotal, BorderLayout.EAST);

        centre.add(panelLabelHaut, BorderLayout.NORTH);
        centre.add(panelLabelBas, BorderLayout.SOUTH);
        centre.add(this.grille);

        this.refreshFenetre();

        Font font = Usine.creerFontTaille(75);

        this.precedent = Usine.creerBoutonAvecTexte("<", "Niveau précédent", this);
        this.precedent.setFont(font);

        this.suivant = Usine.creerBoutonAvecTexte(">", "Niveau suivant", this);
        this.suivant.setFont(font);


        JPanel panelCaptures = new JPanel(new BorderLayout());
        panelCaptures.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        this.piecesCapturees = new JPanel(new FlowLayout(FlowLayout.CENTER, -10, 10));
        this.piecesCapturees.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        this.piecesCapturees.setPreferredSize(new Dimension(0, 80));
        this.piecesCapturees.setBackground(new Color(200, 200, 200));

        panelCaptures.add(piecesCapturees, BorderLayout.CENTER);


        this.menuCorrection = new JMenu    ("Correction");

        this.add(outils, BorderLayout.NORTH);
        this.add(this.precedent, BorderLayout.WEST);
        this.add(this.suivant, BorderLayout.EAST);
        this.add(panelCaptures, BorderLayout.SOUTH);
        this.add(centre);
        
        this.refreshFenetre();
        this.refreshPiecesCapturees();

    }

	/**
	 * Méthode réecrite de l'interface ActionListener: Définit les différentes actions liés aux composants du panel.
	 * @param e est l'evenement.
	 * 
	 */
     @Override
    public void actionPerformed(ActionEvent e)
    {
        if(this.suivant.isEnabled() && (e.getSource() == suivant || e.getSource() == this.defiSuivant))
        {
            this.ctrl.augmenterNiveau();
            this.nbCoupsDefi = 0;
        }
        else 
        	if(this.precedent.isEnabled() && (e.getSource() == precedent || e.getSource() == this.defiPrecedent))
        	{
        		this.ctrl.diminuerNiveau();
        		this.nbCoupsDefi = 0;
        	}
        	else 
        		if(e.getSource() == rejouer || e.getSource() == this.rejouerDefi)
        		{
		            this.ctrl.rejouer();
		            this.estSelectionne = false;
		            this.pieceSelectionnee = null;
		            this.ctrl.getPartie().setNbCoups(this.ctrl.getPartie().getNbCoups()+2);
		            this.nbCoupsDefi += 2;
        		}
        		else 
        			if(e.getSource() == menu || e.getSource() == this.retourMenu)
        				this.fenetre.afficherMenu(this);
        			else  
        				if(e.getSource() == annuler || e.getSource() == this.annulerCoup)
        				{
				            this.ctrl.coupPrecedent();
				            this.estSelectionne = false;
				            this.pieceSelectionnee = null;
        				}
        				else 
        					if(e.getSource() == this.quitterMenu)
        						System.exit(0);
        					else 
        						if((e.getSource() == this.aide || e.getSource() == this.aideJeu) && this.ctrl.getNiveau().getDifficulte().equals("Debutant"))
        						{
						        	new Aide(this.ctrl.getNiveau().getNumNiveau(), this.ctrl);
						            this.ctrl.getPartie().setNbCoups(this.ctrl.getPartie().getNbCoups()+2);
						            this.nbCoupsDefi += 2;
						            if(this.ctrl.victoireNiveauCourant())
						            	this.nbCoupsDefi = 0;
        						}
        						else 
        							if(e.getSource() == this.relges)
        							{
										File html = new File("html/regles.html");
										URI uri = html.toURI();
										try {Desktop.getDesktop().browse(uri);} 
										catch (IOException e1) {e1.printStackTrace();}
        							}

        this.refreshFenetre();
        this.refreshPiecesCapturees();
    }
    
	/**
	 * Actualise l'affichage des pièces capturées.
	 * 
	 */
    protected void refreshPiecesCapturees() 
    {
        this.piecesCapturees.removeAll();
        this.piecesCapturees.updateUI();

        ArrayList<Piece> listeCapturee = this.ctrl.getPlateau().getPiecesCapturees();

        for (Piece pieceTmp : listeCapturee) {
            this.imgPiece = this.getImage(pieceTmp);

            JLabel labelTmp = new JLabel(new ImageIcon(this.imgPiece.getScaledInstance(64, 64, Image.SCALE_DEFAULT)));
            this.piecesCapturees.add(labelTmp);
        }
        
        this.numNiveau.setText    ("Niveau : "                    + this.ctrl.getNiveau().getNumNiveau() );
        this.difficulte.setText   ("Difficulte : "                + this.ctrl.getNiveau().getDifficulte());
        this.compteur.setText     ("Nb. de coup de ce défi : " + this.nbCoupsDefi                     );
        this.compteurTotal.setText("Nb. de coups total : "     + this.ctrl.getPartie().getNbCoups()   );

        if((!(this.ctrl.getDifficulteCourante().get(this.ctrl.getDifficulteCourante().size()-1).getNumNiveau() == this.ctrl.getNiveau().getNumNiveau())  && !this.ctrl.getPartie().peutJouerNiveau(this.ctrl.getNiveau().getDifficulte(), this.ctrl.getNiveau().getNumNiveau()+1) )
        		|| (this.ctrl.getNiveau().getDifficulte().equals("Expert") && (this.ctrl.getNiveau().getNumNiveau() == this.ctrl.getNiveauExpert().size() ))
        		|| (this.ctrl.getNiveau().getDifficulte().equals("Edite") && (this.ctrl.getNiveau().getNumNiveau() == this.ctrl.getNiveauEdite().size())))
        	this.suivant.setEnabled(false);
        else
        	this.suivant.setEnabled(true);
        
        if((this.ctrl.getNiveau().getDifficulte().equals("Debutant") && this.ctrl.getNiveau().getNumNiveau() == 1)
        		|| (this.ctrl.getNiveau().getDifficulte().equals("Edite") && this.ctrl.getNiveau().getNumNiveau() == 1))
        	this.precedent.setEnabled(false);
        else
        	this.precedent.setEnabled(true);
    }
    
    public void mouseClicked (MouseEvent e){}
    public void mouseEntered (MouseEvent e){}
    public void mouseExited  (MouseEvent e){}
    public void mousePressed (MouseEvent e){}
    
	/**
	 * Méthode réecrite de l'interface MouseListener: Définit les différentes actions liées à souris.
	 * @param e est l'evenement de la souris.
	 */
    @Override
    public void mouseReleased(MouseEvent e) 
    {
        int y = e.getY() / this.TAILLE_CASE;
        int x = e.getX() / this.TAILLE_CASE;

        if(!this.estSelectionne)
        {
            if(this.ctrl.getPlateau().getPlateau()[y][x] != null)
            {
                this.estSelectionne = true;
                this.pieceSelectionnee = this.ctrl.getPlateau().getPlateau()[y][x];
            }
        }
        else
        {
            if(this.ctrl.getPlateau().getPlateau()[y][x] != this.pieceSelectionnee && this.ctrl.getPlateau().deplacer(pieceSelectionnee, x, y) )
            {
                this.ctrl.sauvegardeCoup();
                this.nbCoupsDefi++;
                this.ctrl.getPartie().setNbCoups(this.ctrl.getPartie().getNbCoups()+1);
            }
            
        	this.estSelectionne    = false;
            this.pieceSelectionnee = null;
        }
        
        if(this.ctrl.victoireNiveauCourant())
        	this.nbCoupsDefi = 0;
        
        this.refreshFenetre();
        this.refreshPiecesCapturees();
    }
    
	/**
	 * Définit la barre de menu de la fenetre.
	 * 
	 */
    public void setMenuBarre()
    { 
    	JMenuBar menuRacourci = new JMenuBar();
    	
        this.menuPrincipal = new JMenu("Menu principal");
        this.retourMenu = new JMenuItem("Retour menu");
        this.retourMenu.addActionListener(this);
        this.retourMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        this.menuPrincipal.add(this.retourMenu);
        
        this.quitterMenu = new JMenuItem("Quitter");
        this.quitterMenu.addActionListener(this);
        this.quitterMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        this.menuPrincipal.add(this.quitterMenu);
        
        
        this.menuNiveau = new JMenu("Défis");
        this.defiSuivant = new JMenuItem("Défi suivant");
        this.defiSuivant.addActionListener(this);
        this.defiSuivant.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, ActionEvent.CTRL_MASK));
        this.menuNiveau.add(this.defiSuivant);
        
        this.defiPrecedent = new JMenuItem("Défi précédent");
        this.defiPrecedent.addActionListener(this);
        this.defiPrecedent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, ActionEvent.CTRL_MASK));
        this.menuNiveau.add(this.defiPrecedent);
        
        
        this.menuCorrection = new JMenu("Correction");
        this.annulerCoup = new JMenuItem("Annuler déplacement");
        this.annulerCoup.addActionListener(this);
        this.annulerCoup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        this.menuCorrection.add(this.annulerCoup);
        
        this.rejouerDefi = new JMenuItem("Réjouer défi");
        this.rejouerDefi.addActionListener(this);
        this.rejouerDefi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        this.menuCorrection.add(this.rejouerDefi);
        
        this.aideJeu = new JMenuItem("Aide défi");
        this.aideJeu.addActionListener(this);
        this.aideJeu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        this.menuCorrection.add(this.aideJeu);
        
        this.menuAutre = new JMenu("Autres");
        this.relges = new JMenuItem("Règles");
        this.relges.addActionListener(this);
        this.relges.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        this.menuAutre.add(this.relges);
        
        menuRacourci.add(this.menuPrincipal);
        menuRacourci.add(this.menuNiveau);
        menuRacourci.add(this.menuCorrection);
        menuRacourci.add(this.menuAutre);
        
        this.fenetre.setJMenuBar(menuRacourci);
    }
}

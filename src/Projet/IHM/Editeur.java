package Projet.IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;

import Projet.Controleur;
import Projet.IHM.Projet.Usine;
import Projet.Metier.Niveau;
import Projet.Metier.Plateau;
import Projet.Metier.Pieces.Cavalier;
import Projet.Metier.Pieces.Fou;
import Projet.Metier.Pieces.Piece;
import Projet.Metier.Pieces.Pion;
import Projet.Metier.Pieces.Reine;
import Projet.Metier.Pieces.Roi;
import Projet.Metier.Pieces.Tour;

/**
 * Classe gérant le mode éditeur de l'application
 * @author BELLANGER Jessy, LINTOT Maxime, PICOT Maxence et SINAEVE Antoine
 *
 */
public class Editeur extends BaseFenetre implements ActionListener
{
	private static final long serialVersionUID = 1L;
    private JButton valider;
    private JButton reinitialiserPlateau;
    private JButton menu;
    private JButton supprimer;
    
    private JMenu menuPrincipal;
	private JMenu menuAutre;
    private JMenu menuDefi;
    
	private JMenuItem quitterMenu;
	private JMenuItem retourMenu;
	private JMenuItem supprimerPiece;
	private JMenuItem reinitialiserEditeur;
	private JMenuItem relges;
	private JMenuItem validerDefi;
    
    private ArrayList<Piece> piecesDisponibles;
    
    private Piece pieceDisponibleSelectionne;
    
    private JLabel labelSelectionne;
    
	/**
	 * Constructeur par défaut.
	 * @param ctrl est le Controleur.
	 * @param fenetre est la frame principale.
	 * 
	 */
    public Editeur(Controleur ctrl, Fenetre fenetre)
    {
        super(ctrl,fenetre);

        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.initEditeur();
        
        this.labelSelectionne = null;
        
        JPanel centre = new JPanel(new BorderLayout());

        JPanel outils = new JPanel();
        this.menu = new JButton(new ImageIcon("Images/Interface/menu.png"));
        this.menu.addActionListener(this);
        this.menu.setToolTipText("Retour au menu");
        outils.add(this.menu);


        this.reinitialiserPlateau = Usine.creerBoutonAvecImage("Images/Interface/rage.png", "Reinitialiser le plateau", this);
        outils.add(this.reinitialiserPlateau);

        this.supprimer = Usine.creerBoutonAvecImage("Images/Interface/abandonner.png", "Supprimer une pièce", this);
        outils.add(this.supprimer);

        this.valider = Usine.creerBoutonAvecImage("Images/Interface/valider.png", "Valider le défi", this);
        outils.add(this.valider);

        this.grille = new JPanel(new GridLayout(4, 4));
        this.grille.addMouseListener(this);
        this.refreshFenetre();
        centre.add(this.grille);

        JPanel panelCaptures = new JPanel(new BorderLayout());
        panelCaptures.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        this.piecesCapturees = new JPanel(new FlowLayout(FlowLayout.CENTER, -10, 10));
        this.piecesCapturees.setBackground(new Color(200, 200, 200));
        this.piecesCapturees.setPreferredSize(new Dimension(0, 80));
        this.piecesCapturees.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelCaptures.add(piecesCapturees, BorderLayout.CENTER);

        this.add(outils, BorderLayout.NORTH);
        this.add(panelCaptures, BorderLayout.SOUTH);
        this.add(centre);

        this.refreshFenetre();
        this.refreshPiecesRestantes();
    }

	/**
	 * Méthode permettant de définir les différentes actions.
	 * @param e est l'evenement.
	 * 
	 */
    public void actionPerformed(ActionEvent e)
    {
        if((e.getSource() == this.validerDefi || e.getSource() == valider) && this.piecesDisponibles.size() <= 8) 
        {
        	this.sauvegarderNiveau();
        	this.ctrl.initNiveau();
            this.fenetre.afficherMenu(this);
        }
        else 
        	if(e.getSource() == supprimer || e.getSource() == this.supprimerPiece) 
        	{
	        	if(this.pieceSelectionnee != null && this.pieceDisponibleSelectionne == null)
	        	{
	        		this.ctrl.getPlateau().getPlateau()[this.pieceSelectionnee.getPosY()][this.pieceSelectionnee.getPosX()] = null;
	        		this.piecesDisponibles.add(this.pieceSelectionnee);
	        		this.pieceSelectionnee = null;
	        		this.estSelectionne = false;
	        	}
	        }
	        else 
	        	if(e.getSource() == this.relges) 
	        	{
	    			File html = new File("html/regles.html");
	    			URI uri = html.toURI();
	    			try {
	    				Desktop.getDesktop().browse(uri);
	    			} catch (IOException e1) {
	    				e1.printStackTrace();
	    			}
	        	}
	        	else 
	        		if(e.getSource() == reinitialiserPlateau || e.getSource() == this.reinitialiserEditeur) 
			        	this.initEditeur();
	        		else 
	        			if(e.getSource() == menu || e.getSource() == this.retourMenu) 
	                        this.fenetre.afficherMenu(this);
	        			else
	        				if(e.getSource() == this.quitterMenu)
	        					System.exit(0);
        
        this.refreshFenetre();
        this.refreshPiecesRestantes();
    }
    
	/**
	 * Méthode permettant de rafraichir le contenu des pieces disponibles restantes.
	 * 
	 */
    protected void refreshPiecesRestantes() 
    {
        this.piecesCapturees.removeAll();
        this.piecesCapturees.updateUI();
        
        for(int i = 0; i < this.piecesDisponibles.size(); i++)
        {
            Piece pieceTmp = this.piecesDisponibles.get(i);
            this.imgPiece = this.getImage(pieceTmp);

            JLabel labelTmp = new JLabel();
            labelTmp.setName(pieceTmp.getType() + "");
            ImageIcon imageIcon = new ImageIcon(this.imgPiece.getScaledInstance(64, 64, Image.SCALE_DEFAULT));
            labelTmp.setIcon(imageIcon);
            labelTmp.addMouseListener(this);
            this.piecesCapturees.add(labelTmp);
        }
    }
    
    public void mouseClicked (MouseEvent e){}
    public void mouseEntered (MouseEvent e){}
    public void mouseExited  (MouseEvent e){}
    public void mousePressed (MouseEvent e){}
    
	/**
	 * Méthode permettant de définir les différentes actions de la souris.
	 * @param e est l'evenement de la souris.
	 * 
	 */
    public void mouseReleased(MouseEvent e) 
    {
    	if(e.getSource() instanceof JLabel)
    	{
			if (this.labelSelectionne != null)
				this.labelSelectionne.setBorder(null);
			
			this.estSelectionne = false;
			this.pieceSelectionnee = null;
    		JLabel tmp = (JLabel) e.getSource();
    		this.labelSelectionne = tmp;
    		if(tmp.getName().equals("r"))
    			this.pieceDisponibleSelectionne = new Roi(0,0);
    		else 
    			if(tmp.getName().equals("R"))
    				this.pieceDisponibleSelectionne = new Reine(0,0);
    			else 
    				if(tmp.getName().equals("T"))
    					this.pieceDisponibleSelectionne = new Tour(0,0);
    				else 
    					if(tmp.getName().equals("F"))
    						this.pieceDisponibleSelectionne = new Fou(0,0);
    					else 
    						if(tmp.getName().equals("P"))
    							this.pieceDisponibleSelectionne = new Pion(0,0);
    						else 
    							if(tmp.getName().equals("C"))
    								this.pieceDisponibleSelectionne = new Cavalier(0,0);
    		
    		tmp.setBorder(BorderFactory.createLineBorder(Color.black));
    	}
    	else
    	{
	        int y = e.getY() / this.TAILLE_CASE;
	        int x = e.getX() / this.TAILLE_CASE;
	        
	        if(this.pieceDisponibleSelectionne != null) 
	        {
	            if(this.ctrl.getPlateau().getPlateau()[y][x] == null)
	            {
	            	this.ctrl.getPlateau().getPlateau()[y][x] = this.pieceDisponibleSelectionne;
	            	this.pieceDisponibleSelectionne.setPosX(x);
	            	this.pieceDisponibleSelectionne.setPosY(y);
	            	for(int i = 0; i < this.piecesDisponibles.size(); i++)
	            	{
	            		if(this.pieceDisponibleSelectionne.getType() == this.piecesDisponibles.get(i).getType())
	            		{
	            			this.piecesDisponibles.remove(i);
	            			break;
	            		}
	            	}
	            	
	                this.pieceDisponibleSelectionne = null;
	                this.refreshPiecesRestantes();
	            }
	        }	
	        else 
	        {
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
		            if(this.ctrl.getPlateau().getPlateau()[y][x] == null )
		            	this.ctrl.getPlateau().deplacerEditeur(pieceSelectionnee, x, y);
		            
		            this.estSelectionne = false;
		            this.pieceSelectionnee = null;
		        }
	        }
    	}
        this.refreshFenetre();
    }
    
	/**
	 * Méthode permettant de sauvegarder le niveau creé dans un fichier .txt
	 * 
	 */
    public void sauvegarderNiveau()
    {
    	PrintWriter fich = null;

    	try{fich = new PrintWriter(new BufferedWriter(new FileWriter("Niveaux/NiveauEditeur/NiveauEdites.txt", true)));
		}catch(IOException e) {e.printStackTrace();}

    	Piece[][] piece = this.ctrl.getPlateau().getPlateau();
    	
    	fich.println();
    	for(int i = 0; i < 4; i++) 
    	{
    		String line = "";
    		for(int j = 0; j < 4; j++) 
    		{
    			char tmp;
    			
    			if(piece[i][j] == null)tmp = '.';
    			else tmp = piece[i][j].getType();
    			
    			line += tmp+" ";
    		}
    		fich.println(line);
    	}
    	fich.close();
    }
    
	/**
	 * Méthode permettant de définir la barre de menu de la fenetre.
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
        
        
        this.menuDefi = new JMenu("Editeur défi");
        this.supprimerPiece = new JMenuItem("Supprimer pièce");
        this.supprimerPiece.addActionListener(this);
        this.supprimerPiece.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, ActionEvent.CTRL_MASK));
        this.menuDefi.add(this.supprimerPiece);
        
        this.reinitialiserEditeur = new JMenuItem("Réinitialiser plateau");
        this.reinitialiserEditeur.addActionListener(this);
        this.reinitialiserEditeur.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        this.menuDefi.add(this.reinitialiserEditeur);
        
        this.validerDefi = new JMenuItem("Valider défi");
        this.validerDefi.addActionListener(this);
        this.validerDefi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        this.menuDefi.add(this.validerDefi);
        
        this.menuAutre = new JMenu("Autres");
        this.relges = new JMenuItem("Règles");
        this.relges.addActionListener(this);
        this.relges.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        this.menuAutre.add(this.relges);
        
        menuRacourci.add(this.menuPrincipal);
        menuRacourci.add(this.menuDefi);
        menuRacourci.add(this.menuAutre);
        
        this.fenetre.setJMenuBar(menuRacourci);
    }
    
	/**
	 * Méthode permettant de reinitialiser le plateau et les pieces disponibles.
	 * 
	 */
    private void initEditeur()
    {
        Piece[][] pl = new Piece[4][4];
        this.ctrl.setNiveau(new Niveau(pl));
        this.ctrl.setPlateau(new Plateau(this.ctrl.getNiveau().getPiece()));

        this.pieceDisponibleSelectionne = null;
        this.piecesDisponibles = new ArrayList<Piece>();

        this.piecesDisponibles.add(new Roi(0, 0));
        this.piecesDisponibles.add(new Reine(0, 0));
        this.piecesDisponibles.add(new Fou(0, 0));
        this.piecesDisponibles.add(new Fou(0, 0));
        this.piecesDisponibles.add(new Tour(0, 0));
        this.piecesDisponibles.add(new Tour(0, 0));
        this.piecesDisponibles.add(new Cavalier(0, 0));
        this.piecesDisponibles.add(new Cavalier(0, 0));
        this.piecesDisponibles.add(new Pion(0, 0));
        this.piecesDisponibles.add(new Pion(0, 0));
    }
}

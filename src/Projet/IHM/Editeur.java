package Projet.IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;

import Projet.Controleur;
import Projet.Metier.Niveau;
import Projet.Metier.Plateau;
import Projet.Metier.Pieces.Cavalier;
import Projet.Metier.Pieces.Fou;
import Projet.Metier.Pieces.Piece;
import Projet.Metier.Pieces.Pion;
import Projet.Metier.Pieces.Reine;
import Projet.Metier.Pieces.Roi;
import Projet.Metier.Pieces.Tour;

public class Editeur extends BaseFenetre implements ActionListener
{
    private JButton valider;
    private JButton reinitialiserPlateau;
    private JButton test;
    private JButton menu;
    private JButton aide;
    private JButton suivant;
    private JButton precedent;
    private JButton supprimer;
    
    private JToolBar barreActions;
    
    private ArrayList<Piece> piecesDisponibles;
    
    private Piece pieceDisponibleSelectionne;
    
    private int nombreCoups;
    
    private JLabel difficulte;
    private JLabel compteur;
    private JLabel numNiveau;
    

    public Editeur(Controleur ctrl, Fenetre fenetre)
    {
    	super(ctrl,fenetre);
    	
    	this.estEditeur = true;
    	this.ctrl.setNiveau(new Niveau(0,"Debutant"));
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
    	
        JPanel centre = new JPanel(new BorderLayout());

        JPanel outils = new JPanel();
        this.menu = new JButton(new ImageIcon("Images/menu.png"));
        this.menu.addActionListener(this);
        this.menu.setToolTipText("Retour au menu");
        outils.add(this.menu);

        this.test = new JButton(new ImageIcon("Images/eraser.png"));
        this.test.addActionListener(this);
        this.test.setToolTipText("Tester le défi");
        outils.add(this.test);
        
        this.supprimer = new JButton(new ImageIcon("Images/annuler.gif"));
        this.supprimer.addActionListener(this);
        this.supprimer.setToolTipText("Supprimer une pièce");
        outils.add(this.supprimer);
        
        this.valider = new JButton(new ImageIcon("Images/valider.gif"));
        this.valider.addActionListener(this);
        this.valider.setToolTipText("Valider le défi");
        outils.add(this.valider);

        this.reinitialiserPlateau = new JButton(new ImageIcon("Images/fire.png"));
        this.reinitialiserPlateau.addActionListener(this);
        this.reinitialiserPlateau.setToolTipText("Reinitialiser le plateau");
        outils.add(this.reinitialiserPlateau);

        this.aide = new JButton(new ImageIcon("Images/question-circular-button.png"));
        this.aide.addActionListener(this);
        this.aide.setToolTipText("À l'aiiiiiiiiide je suis coincé en 720 !!!!!");
        outils.add(this.aide);
        
        this.difficulte = new JLabel("Difficulté : ", JLabel.LEFT);
        this.numNiveau = new JLabel("Niveau : ", JLabel.RIGHT);
        
        JPanel labelCentre = new JPanel(new BorderLayout());
        labelCentre.add(this.difficulte, BorderLayout.WEST);
        labelCentre.add(this.numNiveau, BorderLayout.EAST);
        labelCentre.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        centre.add(labelCentre, BorderLayout.NORTH);

        this.compteur = new JLabel("Score : "+this.nombreCoups);
        compteur.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        centre.add(compteur, BorderLayout.SOUTH);
        centre.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        this.grille = new JPanel(new GridLayout(4, 4));
        this.grille.addMouseListener(this);
        this.refreshFenetre();
        centre.add(this.grille);

        Font font = new Font("Helvetica", Font.PLAIN, 75);
        this.precedent = new JButton("<");
        this.precedent.setToolTipText("Niveau précédent");
        this.precedent.setFont(font);
        this.precedent.addActionListener(this);
        this.suivant = new JButton(">");
        this.suivant.setToolTipText("Niveau suivant");
        this.suivant.setFont(font);
        this.suivant.addActionListener(this);


        JPanel panelCaptures = new JPanel(new BorderLayout());
        panelCaptures.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        this.piecesCapturees = new JPanel(new FlowLayout(FlowLayout.CENTER, -10, 10));
        this.piecesCapturees.setBackground(new Color(200, 200, 200));
        this.piecesCapturees.setPreferredSize(new Dimension(0, 80));
        this.piecesCapturees.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelCaptures.add(piecesCapturees, BorderLayout.CENTER);

        this.add(outils, BorderLayout.NORTH);
        this.add(this.precedent, BorderLayout.WEST);
        this.add(this.suivant, BorderLayout.EAST);
        this.add(panelCaptures, BorderLayout.SOUTH);
        this.add(centre);
        
        this.refreshFenetre();
        this.refreshPiecesRestantes();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == valider) {
        	this.sauvegarderNiveau();
            this.setVisible(false);
            this.fenetre.add(this.fenetre.getMenu(), BorderLayout.CENTER);
            this.fenetre.getMenu().setVisible(true);
            this.fenetre.setTitle("Solitaire Chess - Menu");
            this.fenetre.pack();
        }
        else if( e.getSource() == supprimer) {
        	if ( this.pieceSelectionnee != null && this.pieceDisponibleSelectionne == null )
        	{
        		this.ctrl.getPlateau().getPlateau()[this.pieceSelectionnee.getPosY()][this.pieceSelectionnee.getPosX()] = null;
        		this.piecesDisponibles.add(this.pieceSelectionnee);
        		this.pieceSelectionnee = null;
        		this.estSelectionne = false;
        	}
        }
        else if(e.getSource() == aide) {
        	
        }
        else if(e.getSource() == reinitialiserPlateau) {
        	this.ctrl.setNiveau(new Niveau(0,"Debutant"));
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
        else if(e.getSource() == menu) {
            this.setVisible(false);
            this.fenetre.add(this.fenetre.getMenu(), BorderLayout.CENTER);
            this.fenetre.getMenu().setVisible(true);
            this.fenetre.setTitle("Solitaire Chess - Menu");
            this.fenetre.pack();
        }
        else if(e.getSource() == test) {
        	
        }
        
        this.refreshFenetre();
        this.refreshPiecesRestantes();
    }
    
    protected void refreshPiecesRestantes() {
        this.piecesCapturees.removeAll();
        this.piecesCapturees.updateUI();
        for ( int i = 0; i < this.piecesDisponibles.size(); i++)
        {
            Piece pieceTmp = this.piecesDisponibles.get(i);
            this.imgPiece = this.getImage(pieceTmp);

            JLabel labelTmp = new JLabel();
            labelTmp.setName(pieceTmp.getType()+"");
            ImageIcon imageIcon = new ImageIcon(this.imgPiece.getScaledInstance(64, 64, Image.SCALE_DEFAULT));
            labelTmp.setIcon(imageIcon);
            labelTmp.addMouseListener(this);
            this.piecesCapturees.add(labelTmp);
        }
    }
    
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {

    	if ( e.getSource() instanceof JLabel )
    	{
    		if ( this.pieceDisponibleSelectionne == null && !this.estSelectionne ) {
	    		JLabel tmp = (JLabel) e.getSource();
	    		if ( tmp.getName().equals("r"))
	    			this.pieceDisponibleSelectionne = new Roi(0,0);
	    		else if ( tmp.getName().equals("R"))
	    			this.pieceDisponibleSelectionne = new Reine(0,0);
	    		else if ( tmp.getName().equals("T"))
	    			this.pieceDisponibleSelectionne = new Tour(0,0);
	    		else if ( tmp.getName().equals("F"))
	    			this.pieceDisponibleSelectionne = new Fou(0,0);
	    		else if ( tmp.getName().equals("P"))
	    			this.pieceDisponibleSelectionne = new Pion(0,0);
	    		else if ( tmp.getName().equals("C"))
	    			this.pieceDisponibleSelectionne = new Cavalier(0,0);
	    		tmp.setBorder(BorderFactory.createLineBorder(Color.black));
    		}
    	}
    	else
    	{
	        int y = e.getY() / this.TAILLE_CASE;
	        int x = e.getX() / this.TAILLE_CASE;
	        if ( this.pieceDisponibleSelectionne != null ) {
	            if(this.ctrl.getPlateau().getPlateau()[y][x] == null)
	            {
	            	this.ctrl.getPlateau().getPlateau()[y][x] = this.pieceDisponibleSelectionne;
	            	this.pieceDisponibleSelectionne.setPosX(x);
	            	this.pieceDisponibleSelectionne.setPosY(y);
	            	for (int i = 0; i < this.piecesDisponibles.size(); i++) {
	            		if ( this.pieceDisponibleSelectionne.getType() == this.piecesDisponibles.get(i).getType())
	            		{
	            			this.piecesDisponibles.remove(i);
	            			break;
	            		}
	            	}
	            	
	                this.pieceDisponibleSelectionne = null;
	                this.refreshPiecesRestantes();
	            }
	        }	
	        else {
		        if(!this.estSelectionne)
		        {
		            if(this.ctrl.getPlateau().getPlateau()[y][x] != null)
		            {
		                this.estSelectionne = true;
		                this.pieceSelectionnee = this.ctrl.getPlateau().getPlateau()[y][x];
		                this.pX = x;
		                this.pY = y;
		            }
		        }
		        else
		        {
		            if(this.ctrl.getPlateau().getPlateau()[y][x] == null && this.ctrl.getPlateau().deplacerEditeur(pieceSelectionnee, x, y) )
		            {
		                this.estSelectionne = false;
		                this.pieceSelectionnee = null;
		            }
		            else
		            {
			            this.estSelectionne = false;
			            this.pieceSelectionnee = null;
		            }
		        }
		        
	        }
    	}

        this.refreshFenetre();
    }
    
    public void sauvegarderNiveau() {
    	
    	PrintWriter fich = null;

    	try {
			fich = new PrintWriter(new BufferedWriter(new FileWriter("Niveaux/NiveauEditeur/NiveauEdites.txt", true)));
		} catch (IOException e) {
			e.printStackTrace();
		}

    	Piece[][] piece = this.ctrl.getPlateau().getPlateau();
    	for ( int i = 0; i < 4; i++) {
    		String line = "";
    		for ( int j = 0; j < 4; j++) {
    			char tmp;
    			if ( piece[i][j] == null)
    				tmp = '.';
    			else
    				tmp = piece[i][j].getType();
    			line += tmp+" ";
    		}
    		fich.println(line);
    	}
    	fich.println();
    	fich.close();
    }
}

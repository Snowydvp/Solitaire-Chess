package Projet.IHM;

import Projet.Controleur;
import Projet.IHM.Fenetre;
import Projet.Metier.Niveau;
import Projet.Metier.Plateau;
import Projet.Metier.Pieces.*;

import java.util.ArrayList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.plaf.IconUIResource;

/**
 * Créé par BELLANGER Jessy, LINTOT Maxime, PICOT Maxence et SINAEVE Antoine le 14/06/2016.
 */
public class Jeu extends BaseFenetre implements ActionListener
{
    private JButton suivant;
    private JButton precedent;
    private JButton rejouer;
    private JButton menu;
    private JButton aide;
    private JButton annuler;
    
    private JToolBar barreActions;
    
    private JLabel compteur;
    private JLabel difficulte;
    private JLabel numNiveau;
    
    private int nombreCoups;

    public Jeu(Controleur ctrl, Fenetre fenetre)
    {
    	super(ctrl,fenetre);

    	this.estEditeur = false;
    	this.nombreCoups = 0;
    	
    	this.ctrl.setNiveau(new Niveau(1,"Debutant"));
    	this.ctrl.setPlateau(new Plateau(this.ctrl.getNiveau().getPiece()));
    	
        JPanel centre = new JPanel(new BorderLayout());

        JPanel outils = new JPanel();
        this.menu = new JButton(new ImageIcon("Images/menu.png"));
        this.menu.addActionListener(this);
        this.menu.setToolTipText("Retour au menu");
        outils.add(this.menu);

        this.annuler = new JButton(new ImageIcon("Images/eraser.png"));
        this.annuler.addActionListener(this);
        this.annuler.setToolTipText("Annuler le dernier coup");
        outils.add(this.annuler);

        this.rejouer = new JButton(new ImageIcon("Images/fire.png"));
        this.rejouer.addActionListener(this);
        this.rejouer.setToolTipText("Rejouer ce d�fi");
        outils.add(this.rejouer);

        this.aide = new JButton(new ImageIcon("Images/question-circular-button.png"));
        this.aide.addActionListener(this);
        this.aide.setToolTipText("� l'aiiiiiiiiide je suis coinc� en 720 !!!!!");
        outils.add(this.aide);


        this.difficulte = new JLabel("Difficult� : ", JLabel.LEFT);
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
        this.precedent.setToolTipText("Niveau pr�c�dent");
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
        this.refreshPiecesCapturees();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == suivant) {
            this.ctrl.augmenterNiveau();
            this.nombreCoups = 0;
        }
        else if(e.getSource() == precedent) {
            this.ctrl.diminuerNiveau();
            this.nombreCoups = 0;
        }
        else if(e.getSource() == rejouer) {
            this.ctrl.rejouer();
            this.estSelectionne = false;
            this.pieceSelectionnee = null;
            this.pX = this.pY = -1;
            this.nombreCoups = 0;
        }
        else if(e.getSource() == menu) {
            this.setVisible(false);
            this.fenetre.add(this.fenetre.getMenu(), BorderLayout.CENTER);
            this.fenetre.getMenu().setVisible(true);
            this.fenetre.setTitle("Solitaire Chess - Menu");
            this.fenetre.pack();
        }
        else if(e.getSource() == annuler) {
            this.ctrl.coupPrecedent();
            this.estSelectionne = false;
            this.pieceSelectionnee = null;
            this.pX = this.pY = -1;
        }
        
        this.refreshFenetre();
        this.refreshPiecesCapturees();
    }
    
    protected void refreshPiecesCapturees() {
        this.piecesCapturees.removeAll();
        this.piecesCapturees.updateUI();
        ArrayList<Piece> listeCapturee = this.ctrl.getPlateau().getPiecesCapturees();
        for ( int i = 0; i < listeCapturee.size(); i++)
        {
            Piece pieceTmp = listeCapturee.get(i);
            this.imgPiece = this.getImage(pieceTmp);

            JLabel labelTmp = new JLabel();
            ImageIcon imageIcon = new ImageIcon(this.imgPiece.getScaledInstance(64, 64, Image.SCALE_DEFAULT));
            labelTmp.setIcon(imageIcon);
            this.piecesCapturees.add(labelTmp);
        }
        
        this.numNiveau.setText("Niveau : "+this.ctrl.getNiveau().getNumNiveau());
        this.difficulte.setText("Difficulte : "+this.ctrl.getNiveau().getDifficulte());
        this.compteur.setText("Score : "+this.nombreCoups);
        
        
        
    }
    
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {

        int y = e.getY() / this.TAILLE_CASE;
        int x = e.getX() / this.TAILLE_CASE;

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
            if(this.ctrl.getPlateau().getPlateau()[y][x] != this.pieceSelectionnee && this.ctrl.getPlateau().deplacer(pieceSelectionnee, x, y) )
            {
                this.estSelectionne = false;
                this.pieceSelectionnee = null;
                this.ctrl.sauvegardeCoup();
                this.nombreCoups++;
            }
            else
            {
                this.estSelectionne = false;
                this.pieceSelectionnee = null;
            }
        }
        
        if(this.ctrl.victoireNiveauCourant())
        	this.nombreCoups = 0;
        
        this.refreshFenetre();
        this.refreshPiecesCapturees();
    }
}

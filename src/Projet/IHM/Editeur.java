package Projet.IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import Projet.Controleur;
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
    
    private JToolBar barreActions;

    public Editeur(Controleur ctrl, Fenetre fenetre)
    {
    	super(ctrl,fenetre);

        this.barreActions = new JToolBar(JToolBar.HORIZONTAL);
        this.barreActions.setFloatable(false);

        this.menu = new JButton();
        this.menu.setIcon(new ImageIcon("Images/iconMenu.gif"));
        this.menu.addActionListener(this);
        this.menu.setToolTipText("Retour au menu");
        this.barreActions.add(menu);

        this.barreActions.addSeparator();

        reinitialiserPlateau = new JButton();
        this.reinitialiserPlateau.setIcon(new ImageIcon("Images/rejouer.gif"));
        this.reinitialiserPlateau.addActionListener(this);
        this.reinitialiserPlateau.setToolTipText("Réinitialiser le plateau");
        this.barreActions.add(reinitialiserPlateau);

        this.barreActions.addSeparator();

        test = new JButton();
        this.test.setIcon(new ImageIcon("Images/precedent.gif"));
        this.test.addActionListener(this);
        this.test.setToolTipText("Tester le défi");
        this.barreActions.add(test);

        valider = new JButton();
        this.valider.setIcon(new ImageIcon("Images/suivant.gif"));
        this.valider.addActionListener(this);
        this.valider.setToolTipText("Valider le défi");
        this.barreActions.add(valider);

        this.barreActions.addSeparator();

        this.aide = new JButton();
        this.aide.setIcon(new ImageIcon("Images/aide.gif"));
        this.aide.addActionListener(this);
        this.aide.setToolTipText("Aide");
        this.barreActions.add(aide);
        
        this.add(this.barreActions, BorderLayout.NORTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == valider) {
        	
        }
        else if(e.getSource() == aide) {
        	
        }
        else if(e.getSource() == reinitialiserPlateau) {
        	
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
            }
            else
            {
                if(this.ctrl.getPlateau().getPlateau()[y][x] == this.pieceSelectionnee)
                {
                    this.estSelectionne = false;
                    this.pieceSelectionnee = null;
                }
            }
        }
        this.ctrl.victoireNiveauCourant();
        this.refreshFenetre();
        this.refreshPiecesCapturees();
    }
}

package Projet.IHM;

import Projet.Controleur;
import Projet.IHM.Fenetre;
import Projet.Metier.Pieces.*;

import java.util.ArrayList;

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

import javax.swing.*;

/**
 * Créé par BELLANGER Jessy, LINTOT Maxime, PICOT Maxence et SINAEVE Antoine le 14/06/2016.
 */
public class Jeu extends JPanel implements MouseListener, ActionListener
{
    private Controleur ctrl;

    private JPanel grille;
    private JPanel piecesCapturees;
    private JToolBar barreActions;

    private Image imgPiece;
    private boolean estSelectionne;
    private Piece pieceSelectionnee;
    private int pX,pY;

    private final int TAILLE_CASE;

    private JButton suivant;
    private JButton precedent;
    private JButton rejouer;
    private JButton menu;
    private JButton aide;
    private JButton annuler;

    private Fenetre fenetre;

    public Jeu(Controleur ctrl, Fenetre fenetre)
    {
        this.setLayout(new BorderLayout());
        this.ctrl = ctrl;
        this.fenetre = fenetre;
        this.estSelectionne = false;
        this.pieceSelectionnee = null;
        this.pX = this.pY = -1;

        this.TAILLE_CASE = new ImageIcon("Images/pion.gif").getIconWidth();

        this.piecesCapturees = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.piecesCapturees.setPreferredSize(new Dimension(0, 64));

        this.barreActions = new JToolBar(JToolBar.HORIZONTAL);
        this.barreActions.setFloatable(false);

        this.menu = new JButton();
        this.menu.setIcon(new ImageIcon("Images/iconMenu.gif"));
        this.menu.addActionListener(this);
        this.menu.setToolTipText("Retour au menu");
        this.barreActions.add(menu);

        this.barreActions.addSeparator();

        rejouer = new JButton();
        this.rejouer.setIcon(new ImageIcon("Images/rejouer.gif"));
        this.rejouer.addActionListener(this);
        this.rejouer.setToolTipText("Recommencer le défi");
        this.barreActions.add(rejouer);

        this.barreActions.addSeparator();

        this.annuler = new JButton();
        this.annuler.setIcon(new ImageIcon("Images/annuler.gif"));
        this.annuler.addActionListener(this);
        this.annuler.setToolTipText("Annuler le dernier coup");
        this.barreActions.add(annuler);

        this.barreActions.addSeparator();

        precedent = new JButton();
        this.precedent.setIcon(new ImageIcon("Images/precedent.gif"));
        this.precedent.addActionListener(this);
        this.precedent.setToolTipText("Défi précédent");
        this.barreActions.add(precedent);

        suivant = new JButton();
        this.suivant.setIcon(new ImageIcon("Images/suivant.gif"));
        this.suivant.addActionListener(this);
        this.suivant.setToolTipText("Défi suivant");
        this.barreActions.add(suivant);

        this.barreActions.addSeparator();

        this.aide = new JButton();
        this.aide.setIcon(new ImageIcon("Images/aide.gif"));
        this.aide.addActionListener(this);
        this.aide.setToolTipText("Aide");
        this.barreActions.add(aide);

        this.grille = new JPanel(new GridLayout(4, 4));
        this.grille.addMouseListener(this);
        this.grille.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.refreshFenetre();
        this.add(this.barreActions, BorderLayout.NORTH);
        this.add(this.piecesCapturees, BorderLayout.SOUTH);
        this.add(this.grille);

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

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

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    public void refreshFenetre()
    {
        this.grille.removeAll();
        this.grille.updateUI();
        for(int y = 0; y < 4; y++)
        {
            for(int x = 0; x < 4; x++)
            {
                ImagePanel panelTmp;
                Piece pieceTmp = this.ctrl.getPlateau().getPlateau()[y][x];

                boolean b = false;
                if ( pieceTmp != null && pieceTmp == this.pieceSelectionnee )
                    b = true;

                if ( pieceTmp != null ) this.imgPiece = this.getImage(pieceTmp);
                else this.imgPiece = new ImageIcon("Images/vide52.gif").getImage();

                if ( (y + x) % 2 == 0) {
                    String difficulte = this.ctrl.getNiveau().getDifficulte();
                    if (difficulte.equals("Debutant"))
                        panelTmp = new ImagePanel("Images/pair1.gif", this.imgPiece, b);
                    else if (difficulte.equals("Intermediaire"))
                        panelTmp = new ImagePanel("Images/pair2.gif", this.imgPiece, b);
                    else if (difficulte.equals("Avance"))
                        panelTmp = new ImagePanel("Images/pair3.gif", this.imgPiece, b);
                    else
                        panelTmp = new ImagePanel("Images/pair4.gif", this.imgPiece, b);
                }
                else
                    panelTmp = new ImagePanel("Images/impair.gif", this.imgPiece, b);
                
                this.grille.add(panelTmp);

            }
        }
    }

    public void refreshPiecesCapturees() {
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

    private Image getImage(Piece p ) {
        if ( p instanceof Cavalier )
            return new ImageIcon("Images/cavalier.gif").getImage();
        else if ( p instanceof Fou )
            return new ImageIcon("Images/fou.gif").getImage();
        else if ( p instanceof Pion )
            return new ImageIcon("Images/pion.gif").getImage();
        else if ( p instanceof Reine)
            return new ImageIcon("Images/reine.gif").getImage();
        else if ( p instanceof Roi )
            return new ImageIcon("Images/roi.gif").getImage();
        else if ( p instanceof Tour)
            return new ImageIcon("Images/tour.gif").getImage();
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == suivant) {
            this.ctrl.augmenterNiveau();
            this.refreshFenetre();
            this.refreshPiecesCapturees();
        }
        else if(e.getSource() == precedent) {
            this.ctrl.diminuerNiveau();
            this.refreshFenetre();
            this.refreshPiecesCapturees();
        }
        else if(e.getSource() == rejouer) {
            this.ctrl.rejouer();
            this.estSelectionne = false;
            this.pieceSelectionnee = null;
            this.pX = this.pY = -1;
            this.refreshFenetre();
            this.refreshPiecesCapturees();
        }
        else if(e.getSource() == menu) {
            this.setVisible(false);
            this.fenetre.add(this.fenetre.getMenu(), BorderLayout.CENTER);
            this.fenetre.getMenu().setVisible(true);
            this.fenetre.setTitle("Solitaire Chess - Menu");
            this.fenetre.pack();
        }
    }
}

class ImagePanel extends JPanel
{
    private Image img;
    private Image imgPiece;
    private boolean estSelectionne;

    public ImagePanel(String img, Image imgPiece, boolean estSelectionnee)
    {
        this.estSelectionne = estSelectionnee;
        this.img = new ImageIcon(img).getImage();
        this.imgPiece = imgPiece;
        Dimension size = new Dimension(this.imgPiece.getWidth(null), this.imgPiece.getHeight(null));
        setPreferredSize(size);
    }

    public void paint(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
        if ( this.estSelectionne ) {
            g.setColor(new Color(127, 127, 127, 50));
            g.fillRect( 0,  0, this.getWidth(), this.getHeight());

        }
        g.drawImage(imgPiece, 0, 0, null);
    }
}

class ImageBackground extends JPanel
{
    private Image background;

    public ImageBackground(Image img)
    {
        this.background = img;
    }

    public void paintComponent(Graphics g)
    {
        g.drawImage(this.background, 0, 0, getWidth(), getHeight(), null);
    }
}

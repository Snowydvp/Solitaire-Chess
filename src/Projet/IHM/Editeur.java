package Projet.IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Projet.Controleur;
import Projet.Metier.Pieces.Cavalier;
import Projet.Metier.Pieces.Fou;
import Projet.Metier.Pieces.Piece;
import Projet.Metier.Pieces.Pion;
import Projet.Metier.Pieces.Reine;
import Projet.Metier.Pieces.Roi;
import Projet.Metier.Pieces.Tour;

public class Editeur extends JPanel implements MouseListener, ActionListener
{
	private Controleur ctrl;
	
	private JPanel grille;
	private JPanel pieceCapturee;
	private JPanel menuWest;
	private JPanel menuWestAll;
	
	private Image imgPiece;
	private boolean estSelectionne;
	private Piece pieceSelectionnee;
	private int pX,pY;
	
	private JButton suivant;
	private JButton precedent;
	private JButton rejouer;
	private JButton menu;
	private JButton aide;
	private JButton annuler;
	
	private Fenetre fenetre;
	
	public Editeur(Controleur ctrl, Fenetre fenetre) 
	{
		this.setLayout(new BorderLayout());
		this.ctrl = ctrl;
		this.fenetre = fenetre;
		this.estSelectionne = false;
		this.pieceSelectionnee = null;
		this.pX = this.pY = -1;

		this.pieceCapturee = new ImageBackground2(new ImageIcon("images/bgpiececapturee.gif").getImage());
		this.pieceCapturee.setPreferredSize(new Dimension(0, 50));
		
		this.menuWestAll = new JPanel(new BorderLayout());
		this.menuWestAll.add(new ImageBackground2(new ImageIcon("images/bgpiececapturee.gif").getImage()),BorderLayout.NORTH);
		this.menuWestAll.add(new ImageBackground2(new ImageIcon("images/bgpiececapturee.gif").getImage()),BorderLayout.SOUTH);
		this.menuWestAll.add(new ImageBackground2(new ImageIcon("images/bgpiececapturee.gif").getImage()),BorderLayout.WEST);
		this.menuWestAll.add(new ImageBackground2(new ImageIcon("images/bgpiececapturee.gif").getImage()),BorderLayout.EAST);
		
		this.menuWest = new ImageBackground2(new ImageIcon("images/bgpiececapturee.gif").getImage());
		this.menuWest.setPreferredSize(new Dimension(33, 0));
		this.menuWest.setLayout(new GridLayout(6,1,0,10));
		
		this.menu = new JButton();
		this.menu.setIcon(new ImageIcon("images/iconMenu.gif"));
		this.menu.addActionListener(this);
		this.menuWest.add(menu);
		
		this.annuler = new JButton();
		this.annuler.setIcon(new ImageIcon("images/annuler.gif"));
		this.annuler.addActionListener(this);
		this.menuWest.add(annuler);
		
		rejouer = new JButton();
		this.rejouer.setIcon(new ImageIcon("images/rejouer.gif"));
		this.rejouer.addActionListener(this);
		this.menuWest.add(rejouer);
		
		suivant = new JButton();
		this.suivant.setIcon(new ImageIcon("images/suivant.gif"));
		this.suivant.addActionListener(this);
		this.menuWest.add(suivant);
		
		precedent = new JButton();
		this.precedent.setIcon(new ImageIcon("images/precedent.gif"));
		this.precedent.addActionListener(this);
		this.menuWest.add(precedent);
		
		this.aide = new JButton();
		this.aide.setIcon(new ImageIcon("images/aide.gif"));
		this.aide.addActionListener(this);
		this.menuWest.add(aide);
		
		this.grille = new JPanel(new GridLayout(4, 4));
		this.grille.addMouseListener(this);
		
		this.refreshFenetre();
		this.menuWestAll.add(this.menuWest);
		this.add(this.menuWestAll, BorderLayout.WEST);
		this.add(this.pieceCapturee, BorderLayout.SOUTH);
		this.add(this.grille);
		this.setVisible(true);
	}

	public void mouseClicked(MouseEvent e) 
	{
		int y = e.getY() / 64;
		int x = e.getX() / 64;
		
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
				this.ctrl.victoireNiveauCourant();
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
		this.refreshFenetre();
		this.refreshPieceCapturee();
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
				ImagePanel2 panelTmp;
				Piece pieceTmp = null;
				
				boolean b = false;
				if ( pieceTmp != null && pieceTmp == this.pieceSelectionnee )
					b = true;
					
				if ( pieceTmp != null ) this.imgPiece = this.getImage(pieceTmp);
				else this.imgPiece = new ImageIcon("images/vide52.gif").getImage();
				
				if ( (y + x) % 2 == 0) {
					String difficultee = this.ctrl.getNiveau().getDifficulte();
					panelTmp = new ImagePanel2("images/pair4.png", this.imgPiece, b);
					if ( difficultee.equals("Debutant")) panelTmp = new ImagePanel2("images/pair1.png", this.imgPiece, b);
					if ( difficultee.equals("Intermediaire")) panelTmp = new ImagePanel2("images/pair2.png", this.imgPiece, b);
					if ( difficultee.equals("Avance")) panelTmp = new ImagePanel2("images/pair3.png", this.imgPiece, b);
				}
				else panelTmp = new ImagePanel2("images/impair.png", this.imgPiece, b);
				
				this.grille.add(panelTmp);
			}
		}
	}
	
	public void refreshPieceCapturee() {
		this.pieceCapturee.removeAll();
		this.pieceCapturee.updateUI();
		ArrayList<Piece> listeCapturee = this.ctrl.getPlateau().getPiecesCapturees();
		for ( int i = 0; i < listeCapturee.size(); i++)
		{
			Piece pieceTmp = listeCapturee.get(i);
			this.imgPiece = this.getImage(pieceTmp);
			
			JLabel labelTmp = new JLabel();
			ImageIcon imageIcon = new ImageIcon(this.imgPiece.getScaledInstance(40, 40, Image.SCALE_DEFAULT));
			labelTmp.setIcon(imageIcon);
			this.pieceCapturee.add(labelTmp);
		}
	}
	
	private Image getImage(Piece p ) {
		if ( p instanceof Cavalier )
			return new ImageIcon("images/cavalier.gif").getImage();
		else if ( p instanceof Fou )
			return new ImageIcon("images/fou.gif").getImage();
		else if ( p instanceof Pion )
			return new ImageIcon("images/pion.gif").getImage();
		else if ( p instanceof Reine)
			return new ImageIcon("images/reine.gif").getImage();
		else if ( p instanceof Roi )
			return new ImageIcon("images/roi.gif").getImage();
		else if ( p instanceof Tour)
			return new ImageIcon("images/tour.gif").getImage();
		return null;
	}

	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == suivant) {
			this.ctrl.augmenterNiveau();
			this.estSelectionne = false;
			this.pieceSelectionnee = null;
			this.pX = this.pY = -1;
			this.refreshFenetre();
			this.refreshPieceCapturee();
		}
		else if(e.getSource() == precedent) {
			this.ctrl.diminuerNiveau();
			this.estSelectionne = false;
			this.pieceSelectionnee = null;
			this.pX = this.pY = -1;
			this.refreshFenetre();
			this.refreshPieceCapturee();
		}
		else if(e.getSource() == rejouer) {
			this.ctrl.rejouer();
			this.estSelectionne = false;
			this.pieceSelectionnee = null;
			this.pX = this.pY = -1;
			this.refreshFenetre();
			this.refreshPieceCapturee();
		}
		else if(e.getSource() == menu) {
			this.fenetre.getMenu().setVisible(true);
			this.fenetre.getJeu().setVisible(false);
		}
		
	}
}

class ImagePanel2 extends JPanel 
{
	private Image img;
	private Image imgPiece;
	private boolean estSelectionne;

	public ImagePanel2(String img, Image imgPiece, boolean estSelectionnee) 
	{
		this.estSelectionne = estSelectionnee;
		this.img = new ImageIcon(img).getImage();
		this.imgPiece = imgPiece;
		Dimension size = new Dimension(this.img.getWidth(null), this.img.getHeight(null));
		setPreferredSize(size);
	}
	
	public void paint(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
		g.drawImage(imgPiece, 0, 0, null);
		
		if ( this.estSelectionne ) {
			g.setColor(Color.BLACK);
			g.drawLine(0, 0, 0, 63);
			g.drawLine(0, 0, 63, 0);
			g.drawLine(0, 63, 63, 63);
			g.drawLine(63, 0, 63, 63);
		}
	}
}

class ImageBackground2 extends JPanel 
{
	private Image background;
	
	public ImageBackground2(Image img)
	{
		this.background = img;
	}
	
    public void paintComponent(Graphics g)
    {
    	g.drawImage(this.background, 0, 0, getWidth(), getHeight(), null);
    }
}

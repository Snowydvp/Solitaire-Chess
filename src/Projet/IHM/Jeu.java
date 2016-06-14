package Projet.IHM;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Projet.Controleur;
import Projet.Metier.Pieces.*;


public class Jeu extends JPanel implements MouseListener, ActionListener
{
	private Controleur ctrl;
	
	private JPanel grille;
	private JPanel pieceCapturee;
	private JPanel menuWest;
	
	private Image imgPiece;
	private boolean estSelectionne;
	private Piece pieceSelectionnee;
	private int pX,pY;
	
	private JButton suivant;
	private JButton precedent;
	
	public Jeu(Controleur ctrl) 
	{
		this.setLayout(new BorderLayout());
		this.ctrl = ctrl;
		this.estSelectionne = false;
		this.pieceSelectionnee = null;
		this.pX = this.pY = -1;

		this.pieceCapturee = new ImageBackground(new ImageIcon("images/bgpiececapturee.gif").getImage());
		this.pieceCapturee.setPreferredSize(new Dimension(0, 50));
		
		this.menuWest = new ImageBackground(new ImageIcon("images/bgpiececapturee.gif").getImage());
		this.menuWest.setPreferredSize(new Dimension(100, 0));
		this.menuWest.setLayout(new FlowLayout());
		JButton b1 = new JButton("Menu");
		b1.setPreferredSize(new Dimension(30, 30));
		this.menuWest.add(new JButton("Menu"));
		this.menuWest.add(new JButton("Rejouer"));
		this.menuWest.add(new JButton("Annuler"));
		this.menuWest.add(new JButton("Aide"));
		
		suivant = new JButton("Suivant");
		this.suivant.addActionListener(this);
		this.menuWest.add(suivant);
		
		precedent = new JButton("Précédent");
		this.precedent.addActionListener(this);
		this.menuWest.add(precedent);
		
		this.grille = new JPanel(new GridLayout(4, 4));
		this.grille.addMouseListener(this);
		
		this.refreshFenetre();
		
		this.add(this.menuWest, BorderLayout.WEST);
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
				ImagePanel panelTmp;
				Piece pieceTmp = this.ctrl.getPlateau().getPlateau()[y][x];
				
				boolean b = false;
				if ( pieceTmp != null && pieceTmp == this.pieceSelectionnee )
					b = true;
					
				if ( pieceTmp != null ) this.imgPiece = this.getImage(pieceTmp);
				else this.imgPiece = new ImageIcon("images/vide52.gif").getImage();
				
				if ( (y + x) % 2 == 0) panelTmp = new ImagePanel("images/pair1.png", this.imgPiece, b);
				else panelTmp = new ImagePanel("images/impair.png", this.imgPiece, b);
				
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
			this.refreshFenetre();
			this.refreshPieceCapturee();
		}
		else if(e.getSource() == precedent) {
			this.ctrl.diminuerNiveau();
			this.refreshFenetre();
			this.refreshPieceCapturee();
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

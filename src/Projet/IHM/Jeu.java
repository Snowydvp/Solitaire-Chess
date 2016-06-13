package Projet.IHM;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
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

public class Jeu extends JFrame 
{
	private Controleur ctrl;
	private JPanel grille;
	private Image imgPiece;
	
	public Jeu(Controleur ctrl) 
	{
		this.setTitle("Fenetre de jeu");
		this.setResizable(false);
		
		this.ctrl = ctrl;
		this.grille = new JPanel(new GridLayout(4, 4));
		
		for(int y = 0; y < 4; y++)
		{
			for(int x = 0; x < 4; x++)
			{
				ImagePanel panelTmp;
				Piece pieceTmp = this.ctrl.getPlateau().getTabPiece()[y][x];
				if ( pieceTmp != null ) {
					if ( pieceTmp instanceof Cavalier )
						this.imgPiece = new ImageIcon("images/cavalier.gif").getImage();
					else if ( pieceTmp instanceof Fou )
						this.imgPiece = new ImageIcon("images/fou.gif").getImage();
					else if ( pieceTmp instanceof Pion )
						this.imgPiece = new ImageIcon("images/pion.gif").getImage();
					else if ( pieceTmp instanceof Reine)
						this.imgPiece = new ImageIcon("images/reine.gif").getImage();
					else if ( pieceTmp instanceof Roi )
						this.imgPiece = new ImageIcon("images/roi.gif").getImage();
					else if ( pieceTmp instanceof Tour)
						this.imgPiece = new ImageIcon("images/tour.gif").getImage();
				}
				else
					this.imgPiece = new ImageIcon("images/vide52.gif").getImage();
				
				if ( (y+x)%2 == 0)
					panelTmp = new ImagePanel("images/pair.gif", this.imgPiece);
				else
					panelTmp = new ImagePanel("images/impair.gif", this.imgPiece);
				
				this.grille.add(panelTmp);
			}
		}
		
		
		this.add(this.grille);
		this.pack();
		this.setVisible(true);
	}
}

class ImagePanel extends JPanel 
{
	  private Image img;
	  private Image imgPiece;
	
	  public ImagePanel(String img, Image imgPiece) {
	    this.img = new ImageIcon(img).getImage();
	    this.imgPiece = imgPiece;
	    Dimension size = new Dimension(this.img.getWidth(null), this.img.getHeight(null));
	    setPreferredSize(size);
	  }
	
	  public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0, null);
	    g.drawImage(imgPiece, 0, 0, null);
	  }
}

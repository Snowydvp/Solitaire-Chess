package Projet.IHM;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Projet.Controleur;
import Projet.Metier.Pieces.*;


public class Jeu extends JFrame implements MouseListener
{
	private Controleur ctrl;
	private JPanel grille;
	private Image imgPiece;
	private boolean estSelectionne;
	private Piece pieceSelectionnee;
	private int pX,pY;
	
	public Jeu(Controleur ctrl) 
	{
		this.setTitle("Fenetre de jeu");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		this.ctrl = ctrl;
		this.estSelectionne = false;
		this.pieceSelectionnee = null;
		this.pX = this.pY = -1;

		this.grille = new JPanel(new GridLayout(4, 4));
		this.grille.addMouseListener(this);
		
		this.refreshFenetre();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.add(this.grille);
		this.pack();
		this.setVisible(true);
	}

	public void mouseClicked(MouseEvent e) 
	{
		int y = e.getY() / 64;
		int x = e.getX() / 64;
		
		if(!this.estSelectionne)
		{
			if(this.ctrl.getPlateau().getTabPiece()[y][x] != null)
			{
				this.estSelectionne = true;
				System.out.println("Piece selectionné");
				this.pieceSelectionnee = this.ctrl.getPlateau().getTabPiece()[y][x];
				this.pX = x;
				this.pY = y;
			}
		}
		else
		{
			if(this.ctrl.getPlateau().getTabPiece()[y][x] != null && this.ctrl.getPlateau().getTabPiece()[y][x] != this.pieceSelectionnee)
			{
				System.out.println("Piece posée");
				this.ctrl.getPlateau().getTabPiece()[y][x] = this.pieceSelectionnee;
				this.ctrl.getPlateau().getTabPiece()[this.pY][this.pX] = null;
				this.estSelectionne = false;
				this.pieceSelectionnee = null;
			}
			else
			{
				if(this.ctrl.getPlateau().getTabPiece()[y][x] == this.pieceSelectionnee)
				{
					System.out.println("Piece deselectionnée");
					this.estSelectionne = false;
					this.pieceSelectionnee = null;
				}
			}
		}
		this.refreshFenetre();
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
				
				if ( (y + x) % 2 == 0)
					panelTmp = new ImagePanel("images/pair.gif", this.imgPiece);
				else
					panelTmp = new ImagePanel("images/impair.gif", this.imgPiece);
				
				this.grille.add(panelTmp);
			}
		}
	}
}

class ImagePanel extends JPanel 
{
	private Image img;
	private Image imgPiece;

	public ImagePanel(String img, Image imgPiece) 
	{
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
	}
}

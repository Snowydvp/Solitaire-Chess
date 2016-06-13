package Projet.IHM;

import java.awt.BorderLayout;
import java.awt.GridLayout;

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
	
	public Jeu(Controleur ctrl) 
	{
		this.setTitle("Fenetre de jeu");
		this.setSize(300, 300);
		this.setResizable(false);
		
		this.ctrl = ctrl;
		this.grille = new JPanel(new GridLayout(4, 4));
		
		for(int y = 0; y < 4; y++)
		{
			for(int x = 0; x < 4; x++)
			{
				JPanel panelTmp = new JPanel(new BorderLayout());
				JLabel imagePiece = new JLabel();
				JLabel imageGrille = new JLabel();
				Piece pieceTmp = this.ctrl.getPlateau().getTabPiece()[y][x];
				if ( pieceTmp != null ) {
					if ( pieceTmp instanceof Cavalier )
						imagePiece.setIcon(new ImageIcon("images/cavalier.gif"));
					else if ( pieceTmp instanceof Fou )
						imagePiece.setIcon(new ImageIcon("images/fou.gif"));
					else if ( pieceTmp instanceof Pion )
						imagePiece.setIcon(new ImageIcon("images/pion.gif"));
					else if ( pieceTmp instanceof Reine)
						imagePiece.setIcon(new ImageIcon("images/reine.gif"));
					else if ( pieceTmp instanceof Roi )
						imagePiece.setIcon(new ImageIcon("images/roi.gif"));
					else if ( pieceTmp instanceof Tour)
						imagePiece.setIcon(new ImageIcon("images/tour.gif"));
				}
				else
					imagePiece.setIcon(new ImageIcon("images/vide52.gif"));
				
				if ( (y+x)%2 == 0)
					imageGrille.setIcon(new ImageIcon("images/pair.gif"));
				else
					imageGrille.setIcon(new ImageIcon("images/impair.gif"));
				
				panelTmp.add(imageGrille);
				panelTmp.add(imagePiece);
				this.grille.add(panelTmp);
			}
		}
		
		
		this.add(this.grille);
		
		this.setVisible(true);
	}
}

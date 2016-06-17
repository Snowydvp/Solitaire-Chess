package Projet.IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Image   img;
    private Image   imgPiece;
    private boolean estSelectionne;
    
    private boolean deplacementPossible;

    public ImagePanel(String img, Image imgPiece, boolean estSelectionnee, boolean deplacementPossible)
    {
    	this.deplacementPossible = deplacementPossible;
        this.estSelectionne      = estSelectionnee;
        this.img                 = new ImageIcon(img).getImage();
        this.imgPiece            = imgPiece;
        Dimension size           = new Dimension(this.imgPiece.getWidth(null), this.imgPiece.getHeight(null));
        setPreferredSize(size);
    }

    public void paint(Graphics g)
    {
        super.paintComponent(g);
        //g.drawImage(img, 0, 0, null);
        
        if ( this.estSelectionne )
        {
            g.setColor(new Color(127, 127, 127, 50));
            g.fillRect( 0,  0, this.getWidth(), this.getHeight());
        }
        else
        	if ( this.deplacementPossible )
        	{
	        	g.setColor(new Color(0, 255, 0, 100));
	        	g.fillRect( 0,  0, this.getWidth(), this.getHeight());
        	}
        	else
        		g.drawImage(img, 0, 0, null);
        
        g.drawImage(imgPiece, 0, 0, null);
    }
}

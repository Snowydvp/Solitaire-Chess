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
    
    private int deplacementPossible;

    public ImagePanel(String img, Image imgPiece, boolean estSelectionnee, int deplacementPossible)
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
        g.drawImage(img, 0, 0, null);
        
        if ( this.estSelectionne )
        {
            g.setColor(Color.BLACK);
        	g.drawRect( 1,  1, this.getWidth() - 2, this.getHeight() - 2);
        	g.drawRect( 2,  2, this.getWidth() - 4, this.getHeight() - 4);
        	g.setColor(new Color(0, 0, 0, 25));
        	g.fillRect(1,  1,  this.getWidth() - 1, this.getHeight() - 1);
        }
        else
        	if ( this.deplacementPossible == 1 )
        	{
	        	g.setColor(new Color(0, 0, 255));
	        	g.drawRect( 1,  1, this.getWidth() - 2, this.getHeight() - 2);
	        	g.drawRect( 2,  2, this.getWidth() - 4, this.getHeight() - 4);
	        	g.setColor(new Color(0, 0, 255, 25));
	        	g.fillRect(1,  1,  this.getWidth() - 1, this.getHeight() - 1);
        	}
        	else if ( this.deplacementPossible == 0 )
        	{
	        	g.setColor(new Color(255, 0, 0));
	        	g.drawRect( 1,  1, this.getWidth() - 2, this.getHeight() - 2);
	        	g.drawRect( 2,  2, this.getWidth() - 4, this.getHeight() - 4);
	        	g.setColor(new Color(255, 0, 0, 25));
	        	g.fillRect(1,  1,  this.getWidth() - 1, this.getHeight() - 1);
        	}
        
        g.drawImage(imgPiece, 0, 0, null);
    }
}

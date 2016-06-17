package Projet.IHM;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Projet.Controleur;
import Projet.Metier.Pieces.Cavalier;
import Projet.Metier.Pieces.Fou;
import Projet.Metier.Pieces.Piece;
import Projet.Metier.Pieces.Pion;
import Projet.Metier.Pieces.Reine;
import Projet.Metier.Pieces.Roi;
import Projet.Metier.Pieces.Tour;

public abstract class BaseFenetre extends JPanel implements MouseListener
{
	private static final long serialVersionUID = 1L;

	protected Controleur ctrl;
	
    protected JPanel grille;
    protected JPanel piecesCapturees;
    
    protected Image imgPiece;
    protected boolean estSelectionne;
    protected Piece pieceSelectionnee;
    protected int pX,pY;
    protected boolean estEditeur;

    protected final int TAILLE_CASE;
    
    protected Fenetre fenetre;
	
	public BaseFenetre(Controleur ctrl, Fenetre fenetre)
	{
        this.setLayout(new BorderLayout());
        this.ctrl              = ctrl;
        this.fenetre           = fenetre;
        this.estSelectionne    = false;
        this.pieceSelectionnee = null;
        this.pX = this.pY      = -1;
        
        this.TAILLE_CASE       = new ImageIcon("Themes/"+this.ctrl.getTheme()+"/pion.gif").getIconWidth();
	}
	
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
                boolean deplacementPossible = false;
                
                if ( this.pieceSelectionnee != null && !this.estEditeur )
                	deplacementPossible = this.ctrl.getPlateau().simuleDeplacement(this.pieceSelectionnee, x, y);
                
                if ( pieceTmp != null && pieceTmp == this.pieceSelectionnee ) 
                {
                    b = true;
                    deplacementPossible = false;
                }

                if ( pieceTmp != null ) 
                	this.imgPiece = this.getImage(pieceTmp);
                else 
                	this.imgPiece = new ImageIcon("Themes/"+this.ctrl.getTheme()+"/vide52.gif").getImage();

                if ( (y + x) % 2 == 0) 
                {
                    String difficulte = this.ctrl.getNiveau().getDifficulte();
                    if (difficulte.equals("Debutant") || difficulte.equals("Edite"))
                        panelTmp = new ImagePanel("Themes/"+this.ctrl.getTheme()+"/pair1.gif", this.imgPiece, b,deplacementPossible);
                    else 
                    	if (difficulte.equals("Intermediaire"))
                    		panelTmp = new ImagePanel("Themes/"+this.ctrl.getTheme()+"/pair2.gif", this.imgPiece, b,deplacementPossible);
                    	else 
                    		if (difficulte.equals("Avance"))
                    			panelTmp = new ImagePanel("Themes/"+this.ctrl.getTheme()+"/pair3.gif", this.imgPiece, b,deplacementPossible);
                    		else
                    			panelTmp = new ImagePanel("Themes/"+this.ctrl.getTheme()+"/pair4.gif", this.imgPiece, b,deplacementPossible);
                }
                else
                    panelTmp = new ImagePanel("Themes/"+this.ctrl.getTheme()+"/impair.gif", this.imgPiece, b,deplacementPossible);
                
                this.grille.add(panelTmp);

            }
        }
    }

    protected Image getImage(Piece p ) 
    {
        if ( p instanceof Cavalier )
            return new ImageIcon("Themes/Default/cavalier.gif").getImage();
        else
        	if ( p instanceof Fou )
        		return new ImageIcon("Themes/Default/fou.gif").getImage();
        	else 
        		if ( p instanceof Pion )
        			return new ImageIcon("Themes/Default/pion.gif").getImage();
        		else
        			if ( p instanceof Reine)
        				return new ImageIcon("Themes/Default/reine.gif").getImage();
        			else 
        				if ( p instanceof Roi )
        					return new ImageIcon("Themes/Default/roi.gif").getImage();
        				else
        					if ( p instanceof Tour)
        						return new ImageIcon("Themes/Default/tour.gif").getImage();
        return null;
    }
}

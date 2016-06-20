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

/**
 * Gère la base de la fenetre pour le mode jeu et le mode éditeur.
 * @author BELLANGER Jessy, LINTOT Maxime, PICOT Maxence et SINAEVE Antoine
 *
 */
public abstract class BaseFenetre extends JPanel implements MouseListener
{
	private static final long serialVersionUID = 1L;

	protected Controleur ctrl;
	
    protected JPanel grille;
    protected JPanel piecesCapturees;
    
    protected Image imgPiece;
    protected boolean estSelectionne;
    protected Piece pieceSelectionnee;
    protected final int TAILLE_CASE;
	
    protected Fenetre fenetre;
	
	/**
	 * Constructeur par défaut
	 * @param ctrl est le controleur
	 * @param fenetre est la frame principale
	 * 
	 */
	public BaseFenetre(Controleur ctrl, Fenetre fenetre)
	{
        this.setLayout(new BorderLayout());
        this.ctrl              = ctrl;
        this.fenetre           = fenetre;
        this.estSelectionne    = false;
        this.pieceSelectionnee = null;
        
        this.TAILLE_CASE       = new ImageIcon("Images/Themes/"+this.ctrl.getTheme()+"/pion.gif").getIconWidth();
	}
	
	/**
	 * Actualise tout les composant de la frame principale.
	 * 
	 */
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
                int deplacementPossible = -1;
                
                if(this.pieceSelectionnee != null) 
                	deplacementPossible = this.ctrl.getPlateau().simuleDeplacement(this.pieceSelectionnee, x, y);
                
                if(pieceTmp != null)
                {
                	if(pieceTmp != null && pieceTmp == this.pieceSelectionnee) 
                		b = true;
                	this.imgPiece = this.getImage(pieceTmp);
                }
                else 
                	this.imgPiece = new ImageIcon("Images/Themes/"+this.ctrl.getTheme()+"/vide52.gif").getImage();

                if((y + x) % 2 == 0) 
                {
                    String difficulte = this.ctrl.getNiveau().getDifficulte();
                    if (difficulte.equals("Debutant") || difficulte.equals("Edite"))
                        panelTmp = new ImagePanel("Images/Themes/"+this.ctrl.getTheme()+"/pair1.gif", this.imgPiece, b,deplacementPossible);
                    else 
                    	if (difficulte.equals("Intermediaire"))
                    		panelTmp = new ImagePanel("Images/Themes/"+this.ctrl.getTheme()+"/pair2.gif", this.imgPiece, b,deplacementPossible);
                    	else 
                    		if (difficulte.equals("Avance"))
                    			panelTmp = new ImagePanel("Images/Themes/"+this.ctrl.getTheme()+"/pair3.gif", this.imgPiece, b,deplacementPossible);
                    		else
                    			panelTmp = new ImagePanel("Images/Themes/"+this.ctrl.getTheme()+"/pair4.gif", this.imgPiece, b,deplacementPossible);
                }
                else
                    panelTmp = new ImagePanel("Images/Themes/"+this.ctrl.getTheme()+"/impair.gif", this.imgPiece, b,deplacementPossible);
                
                this.grille.add(panelTmp);

            }
        }
    }

    
	/**
	 * ????
	 * @param p est la piece dont on veux récuperer l'image.
	 * @return l'image correspondant a la piece passé en paramètre.
	 * 
	 */
    protected Image getImage(Piece p) 
    {
        if(p instanceof Cavalier)
            return new ImageIcon("Images/Themes/"+this.ctrl.getTheme()+"/cavalier.gif").getImage();
        else
        	if(p instanceof Fou)
        		return new ImageIcon("Images/Themes/"+this.ctrl.getTheme()+"/fou.gif").getImage();
        	else 
        		if(p instanceof Pion)
        			return new ImageIcon("Images/Themes/"+this.ctrl.getTheme()+"/pion.gif").getImage();
        		else
        			if(p instanceof Reine)
        				return new ImageIcon("Images/Themes/"+this.ctrl.getTheme()+"/reine.gif").getImage();
        			else 
        				if(p instanceof Roi)
        					return new ImageIcon("Images/Themes/"+this.ctrl.getTheme()+"/roi.gif").getImage();
        				else
        					if(p instanceof Tour)
        						return new ImageIcon("Images/Themes/"+this.ctrl.getTheme()+"/tour.gif").getImage();
        return null;
    }
}

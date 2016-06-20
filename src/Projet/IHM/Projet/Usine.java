package Projet.IHM.Projet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Cette classe constitue une API pour manipuler l'IHM facilement.
 * @author BELLANGER Jessy, LINTOT Maxime, PICOT Maxence, SINAEVE Antoine.
 */
public abstract class Usine
{
    public static final Image ICONE_APPLICATION = new ImageIcon("Images/Interface/crown.png").getImage();

    public static final File   DOSSIER_THEME        = new File("Images/Themes/");
    public static final String REPERTOIRE_INTERFACE = "Images/Interface/";

    /**
     * 
     * @param texte: dans le bouton. 
     * @param tooltip: Info-bulle du bouton.
     * @param ecoute: Listener qui écoute les évènements liés au bouton. 
     */
    public static JButton creerBoutonAvecTexte(String texte, String tooltip, ActionListener ecoute)
    {
        JButton bouton = new JButton(texte);
        bouton.setToolTipText(tooltip);
        bouton.addActionListener(ecoute);

        return bouton;
    }

    /**
     * 
     * @param lienImage: chemin de l'image associé au bouton
     * @param tooltip: Info-bulle du bouton
     * @param ecoute: Listener qui écoute les évènements liés au bouton.
     */
    public static JButton creerBoutonAvecImage(String lienImage, String tooltip, ActionListener ecoute)
    {
        JButton bouton = new JButton(new ImageIcon(lienImage));
        bouton.setToolTipText(tooltip);
        bouton.addActionListener(ecoute);

        return bouton;
    }

    public static Font creerFontTaille(int taille)
    {
        return new Font("Helvetica", Font.PLAIN, taille);
    }
}

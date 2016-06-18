package Projet.IHM.Projet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Créé par BELLANGER Jessy, LINTOT Maxime, PICOT Maxence et SINAEVE Antoine le 17/06/2016.
 */
public abstract class Usine
{
    public static final Image ICONE_APPLICATION = new ImageIcon("Images/Interface/crown.png").getImage();

    public static final File   DOSSIER_THEME        = new File("Images/Themes/");
    public static final String REPERTOIRE_INTERFACE = "Images/Interface/";


    public static JButton creerBoutonAvecTexte(String texte, String tooltip, ActionListener ecoute)
    {
        JButton bouton = new JButton(texte);
        bouton.setToolTipText(tooltip);
        bouton.addActionListener(ecoute);

        return bouton;
    }

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

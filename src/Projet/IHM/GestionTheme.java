package Projet.IHM;

import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.JDialog;

import Projet.Controleur;

public class GestionTheme extends JDialog implements ItemListener {
	
	private Controleur ctrl;
	private Fenetre fenetre;
	private List listeTheme;
	
	public GestionTheme(Controleur ctrl, Fenetre fenetre) 
	{
		super(fenetre, "Selectionner un thème", true);
		this.ctrl = ctrl;
		this.fenetre = fenetre;
		
		this.listeTheme = new List();
		this.listeTheme.addItemListener(this);
		File dossier = new File("Images/Themes");
        File[] files = dossier.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory())
                    this.listeTheme.add(files[i].getName());
            }
        }
        
        this.add(this.listeTheme);
        
        this.pack();
        this.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - this.getWidth() / 2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - this.getHeight() / 2));
        this.setVisible(true);
	}

	public void itemStateChanged(ItemEvent e) {
		String theme = this.listeTheme.getSelectedItem();
		this.ctrl.setTheme(theme);
		this.dispose();
	}

}

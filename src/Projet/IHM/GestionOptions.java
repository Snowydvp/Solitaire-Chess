package Projet.IHM;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import Projet.Controleur;
import Projet.IHM.Projet.Usine;

/**
 * Classe gérant les options de l'application.
 * @author BELLANGER Jessy, LINTOT Maxime, PICOT Maxence et SINAEVE Antoine
 *
 */
public class GestionOptions extends JDialog implements ActionListener 
{
	private static final long serialVersionUID = 1L;
	private Controleur ctrl;
	
	private List listeTheme;
	
	private JCheckBox cheatActive;
	
	private JPanel panelCenter;
	private JPanel panelSouth;
	private JPanel panelTheme;
	
	private JLabel labelTheme;
	
	private JButton valider;
	private JButton annuler;
	
	/**
	 * Constructeur par défaut
	 * @param ctrl est le controleur
	 * @param fenetre est la frame principale
	 * 
	 */
	public GestionOptions(Controleur ctrl, Fenetre fenetre) 
	{
		super(fenetre, "Gérer les options", true);
		this.ctrl = ctrl;
		this.setIconImage(Usine.ICONE_APPLICATION);
		this.setResizable(false);
		
		this.setLayout(new BorderLayout());
		
		this.panelCenter = new JPanel(new GridLayout(2, 1));
		this.panelCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.panelSouth = new JPanel();
		
		this.panelTheme = new JPanel(new BorderLayout());
		
		this.labelTheme = new JLabel("Thèmes disponibles", Label.LEFT);
		this.listeTheme = new List(4);
		this.listeTheme.setName("Liste des thèmes");
        File[] files = Usine.DOSSIER_THEME.listFiles();

        if(files != null) 
        {
            for(File f : files)
                if(f.isDirectory())
                    this.listeTheme.add(f.getName());
        }
        
        this.panelTheme.add(this.labelTheme, BorderLayout.NORTH);
        this.panelTheme.add(this.listeTheme);
        
        this.cheatActive = new JCheckBox("Débloquer tout les niveaux");
        if(this.ctrl.getCheatActive())
        	this.cheatActive.setSelected(true);
        
        this.panelCenter.add(this.cheatActive);
        this.panelCenter.add(this.panelTheme);
        
        this.valider = new JButton("Valider");
        this.valider.addActionListener(this);
        
        this.annuler = new JButton("Annuler");
        this.annuler.addActionListener(this);
        
        
        this.panelSouth.add(this.valider);
        this.panelSouth.add(this.annuler);
        
        this.add(this.panelCenter);
        this.add(this.panelSouth, BorderLayout.SOUTH);
        
        
        
        this.pack();
        this.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - this.getWidth() / 2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - this.getHeight() / 2));
        this.setVisible(true);
	}

	/**
	 * Méthode permettant de définir les différentes actions.
	 * @param e est l'evenement.
	 * 
	 */
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == this.valider)
		{
			if(this.listeTheme.getSelectedItem() != null)
				this.ctrl.setTheme(this.listeTheme.getSelectedItem());
			this.ctrl.setCheatActive(this.cheatActive.isSelected());
		}
		this.dispose();
	}
}

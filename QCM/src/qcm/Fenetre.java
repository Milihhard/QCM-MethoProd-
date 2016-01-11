/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Axel
 */
public class Fenetre extends JFrame{
 
    JButton connexion, inscrption;
    
    public Fenetre() {

        this.setTitle("Promotion");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 500));
        //this.setContentPane(new JDesktopPane());
        initialisation();
        this.pack();
        //Ajouter des ecouteurs

    }

    private void initialisation() {
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints contrainte = new GridBagConstraints();

        contrainte.fill = GridBagConstraints.BOTH;

        //On y met les composants dans l'application
        contrainte.gridx = 0;
        contrainte.gridy = 0;
        contrainte.gridwidth=2;
        //this.add(form, contrainte);
    }
}

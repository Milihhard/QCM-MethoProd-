/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import user.Utilisateur;

/**
 *
 * @author Axel
 */
public class PopupMauvaiseConnexion extends JDialog implements ActionListener {

    private JLabel signal;
    private JButton connexion;
    private JPanel pano;
    Utilisateur user;

    public PopupMauvaiseConnexion(JDialog owner) {
        super(owner, true);
        this.setTitle("Mauvaise connexion");
        pano = new JPanel();

        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connexion = new JButton("Ok");
        signal = new JLabel("Mauvaise connexion!");

        GridBagConstraints contraintes = new GridBagConstraints();
        init();
        this.pack();
        this.setVisible(true);
    }

    private void init() {

        this.setLayout(new GridBagLayout());
        GridBagConstraints contrainte = new GridBagConstraints();

        contrainte.fill = GridBagConstraints.BOTH;
        contrainte.gridx = 0;
        contrainte.gridy = 0;
        this.add(signal, contrainte);
        contrainte.gridy++;
        this.add(connexion, contrainte);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == connexion) {
            System.out.println("niah");
            this.setVisible(false);
        }
    }
}

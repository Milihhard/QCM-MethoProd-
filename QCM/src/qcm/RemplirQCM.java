/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author p1406269
 */
public class RemplirQCM extends JFrame{
  private JPanel container = new JPanel();

  private JCheckBox rep = new JCheckBox();

  private JLabel label = new JLabel("Une ComboBox");


  public RemplirQCM(int idQCM){

    this.setTitle("Animation");

    this.setSize(300, 300);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLocationRelativeTo(null);

    container.setBackground(Color.white);

    container.setLayout(new BorderLayout());

    rep.setPreferredSize(new Dimension(100, 20));


    JPanel top = new JPanel();

    top.add(label);

    top.add(rep);

    container.add(top, BorderLayout.NORTH);

    this.setContentPane(container);

    this.setVisible(true);            

  }
}

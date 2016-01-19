/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
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
   public SQL SQL;
  private JPanel container = new JPanel();

  private JCheckBox rep = new JCheckBox();

  
  ArrayList<Question> listQuest;
  ArrayList<Reponse> listRep;
  

  public RemplirQCM(int idQCM){
     listQuest=SQL.getListQuest(idQCM);
     
    this.setTitle("QCM");

    this.setSize(600, 600);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLocationRelativeTo(null);

    container.setBackground(Color.white);

    this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();

       
    int y=0;
    for(int i=0;i<SQL.getListQuest(idQCM).size();i++){
        
        JLabel label = new JLabel(listQuest.get(i).toString());
        cont.gridx=0;
        cont.gridy=y;
        container.add(label,cont);
        y++;
        listRep=SQL.getListRep(listQuest.get(i).id);
        
        for(int j=0;j<listRep.size();j++ ){
            
            JLabel label2 = new JLabel(listRep.get(j).toString());
            cont.gridx=1;
            cont.gridy=y;
            container.add(label2,cont);
            
            cont.gridx=2;
            cont.gridy=y;
            container.add(rep,cont);
            y++;
        }
    }
      this.setContentPane(container);

    this.setVisible(true);  
    }


    

  }
  
  
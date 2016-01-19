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

  

  
  ArrayList<Question> listQuest;
  ArrayList<Reponse> listRep;
  
  ArrayList<JLabel> Questions= new ArrayList();
  


  public RemplirQCM(int idQCM){
     listQuest=SQL.getListQuest(idQCM);
     
    this.setTitle("QCM");

    this.setSize(600, 600);


    this.setLocationRelativeTo(null);

    container.setBackground(Color.white);

    container.setLayout(new GridBagLayout());
    GridBagConstraints cont = new GridBagConstraints();

       
   
    for(int i=0;i<SQL.getListQuest(idQCM).size();i++){
        
        
        Questions.add(new JLabel(listQuest.get(i).toString()));
        cont.gridx=0;
        cont.gridy++;
        container.add(Questions.get(i),cont);
        
        listRep=SQL.getListRep(listQuest.get(i).id);
        ArrayList<JLabel> Reponses = new ArrayList();
        ArrayList<JCheckBox> Rep = new ArrayList();
        for(int j=0;j<listRep.size();j++ ){
            
            
            Reponses.add(new JLabel(listRep.get(j).toString()));
            cont.gridx=1;
            cont.gridy++;
            container.add(Reponses.get(j),cont);
            
           
            Rep.add(new JCheckBox());
            cont.gridx=2;
            container.add(Rep.get(j),cont);
           
        }
    }
      this.setContentPane(container);

    this.setVisible(true);  
    }

  

  }
  
  
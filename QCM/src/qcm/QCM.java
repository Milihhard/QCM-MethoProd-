/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcm;

/**
 *
 * @author Emilien
 */
public class QCM {
    private String title;
    private int ID;
    private int idEns;
    
    public QCM(String title , int ID ){
        this.title=title;
        this.ID=ID;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public int getID(){
        return this.ID;
    }
    
    public void setTitle(String title ){
        this.title=title;
    }
    
    public void setID(int ID ){
        this.ID=ID;
    }
    
    public int getIdEns(){
        return this.idEns;
    }
    
    public void setIdEns(int idEns){
        this.idEns=idEns;
    }
}

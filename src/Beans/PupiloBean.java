/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import core.Pupilo;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.image.Image;
import vision.LoginController;
import vision.PupiloFXMLController;

/**
 *
 * @author José Carlos Emídio Pereira
 */
public class PupiloBean extends Observable implements Observer{
    private Pupilo pup;

    public PupiloBean(Pupilo pup) {
        this.pup = pup;
    }

    public Pupilo getPupilo() {
        return pup;
    }

    public void setPupilo(Pupilo pup) {
        this.pup = pup;
    }

    @Override
    public void update(Observable theObservable, Object arg) {        
        
        // O pupilo acaba de se logar, daí suas informações 
        // precisam ser atualizadas na tela
        if (theObservable instanceof LoginController) {
            if (arg instanceof Pupilo) {
                this.pup = ((Pupilo) arg);
                this.mudaSensei();
                this.mudaFixa();
                this.mudaImagem();
            }
        } 
        
        // A imagem ou o sensei foi mudado, precisa ser atualizado
        // tanto no bean quanto na tela
        else if (theObservable instanceof PupiloFXMLController) {
            if (arg instanceof Image) {
                this.pup.setImage((Image) arg);
            } else if (arg instanceof String && arg != "telaLogin") {
                this.pup.setSenseiName((String) arg);
            }
        }
    }
    
    public void mudaSensei() {
        super.setChanged();
        super.notifyObservers(this.pup.getSenseiName());
        
        super.setChanged();
        super.notifyObservers(this.pup);
    }
    
    public void mudaFixa() {
        super.setChanged();
        super.notifyObservers(this.pup.getFaixa());
    }
    
    public void mudaImagem() {
        super.setChanged();
        super.notifyObservers(this.pup.getImage());
    }
}

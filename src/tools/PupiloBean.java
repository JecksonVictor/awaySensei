/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import core.Pupilo;
import java.util.Observable;
import java.util.Observer;
import vision.EscolherSenseiFXMLController;
import vision.LoginController;

/**
 *
 * @author JC
 */
public class PupiloBean extends Observable implements Observer{
    private Pupilo pup;

    public PupiloBean(Pupilo pup) {
        this.pup = pup;
    }

    @Override
    public void update(Observable theObservable, Object arg) {        
        if (theObservable instanceof EscolherSenseiFXMLController) {
            this.pup.setSenseiName((String) arg);
            
            this.mudaSensei();
        } else if (theObservable instanceof LoginController) {
            this.pup = ((Pupilo) arg);
        }
    }
    
    public void mudaSensei() {
        super.setChanged();
        super.notifyObservers(this.pup.getSenseiName());
    }
}

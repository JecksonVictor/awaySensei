/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import core.Pupilo;
import core.Sensei;
import core.Treino;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.image.Image;
import vision.LoginFXMLController;
import vision.SenseiFXMLController;

/**
 *
 * @author JC
 */
public class SenseiBean extends Observable implements Observer{

    private Sensei sen;
    private ArrayList<Pupilo> pup;

    public SenseiBean(Sensei sen) {
        this.sen = sen;
        this.pup = new ArrayList<>();
    }

    public Sensei getSensei() {
        return sen;
    }

    public void setSensei(Sensei sen) {
        this.sen = sen;
    }
    
    public void addPupilo(Pupilo pup_) {
        this.pup.add(pup_);
    }
    
    @Override
    public void update(Observable theObservable, Object arg) {
        // Informação do sensei foi modificada, é preciso atualizar
        if(theObservable instanceof SenseiFXMLController) {
            // Um novo treino foi criado
            if (arg instanceof Treino) {
                this.sen.criarTreino(((Treino) arg).getVideo(), ((Treino) arg).getDescricao());
            } 
            // A imagem foi trocada
            else if (arg instanceof Image) {
                this.sen.setImage((Image) arg);
            }
        } 
        
        // O sensei acaba de se logar, é preciso atualizar as informações
        // na tela
        else if (theObservable instanceof LoginFXMLController) {
            if (arg instanceof Sensei) {
                this.sen = (Sensei) arg;
                
                super.setChanged();
                super.notifyObservers(this.sen.getImage());
                super.setChanged();
                super.notifyObservers("");
                
                for (Treino tr : this.sen.getTreinosSalvos()) {
                    super.setChanged();
                    super.notifyObservers(tr);
                }
            }
        }
    }
    
    public void cadastrarTreino (Treino treino_) {
        super.setChanged();
        super.notifyObservers(treino_);
    }
    
    public void adicionaTreino (Treino treino_) {
        super.setChanged();
        super.notifyObservers(treino_);
    }
}

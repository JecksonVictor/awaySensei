/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import core.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author proae
 */
public class SaveUser {

    public SaveUser() {
    }
    
    // Salva lista de objetos do tipo Usuario em users.dat
    public void save(ArrayList<Usuario> user){
        File file = null;
        ObjectOutputStream output = null;
        try {
            file = new File("users.dat");
            output = new ObjectOutputStream(new FileOutputStream(file));
            
            output.writeObject(user);
            output.flush();
            output.close();
        } catch(FileNotFoundException e){            
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    
    // Recupera lista de objetos do tipo Usuario de users.dat
    public ArrayList getList(){
        File file = null;
        ObjectInputStream input = null;
        try {
            file = new File("users.dat");
            input = new ObjectInputStream(new FileInputStream(file));
            
            ArrayList<Usuario> users = (ArrayList<Usuario>) input.readObject();
                return users;
        } catch(FileNotFoundException e){            
            System.out.println(e.toString());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.toString());
        }
        
        return null;
    }
}

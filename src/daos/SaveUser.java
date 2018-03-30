/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import core.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author proae
 */
public class SaveUser {

    public SaveUser() {
    }
    
    // Salva lista de objetos do tipo Usuario em users.dat
    public void save(ArrayList<Usuario> user){
        try {
            File file = new File("users.dat");
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
            output.writeObject(user);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
    // Recupera lista de objetos do tipo Usuario de users.dat
    public ArrayList getList(){
        try {
            File file = new File("users.dat");
                ObjectInputStream input = new ObjectInputStream(new
                FileInputStream(file));
                ArrayList<Usuario> users = (ArrayList<Usuario>) input.readObject();
                return users;
        }catch(Exception e){
            System.out.println(e.toString());
        }
        
        return null;
    }
}

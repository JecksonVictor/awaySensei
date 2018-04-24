/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import core.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author proae
 */
public class UsersFileDao implements UsersDao{

    public UsersFileDao() {
    }

    @Override
    public ArrayList<Usuario> getUsers() {
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

    @Override
    public void addUser(Usuario user) {
        try {
            File file = new File("users.dat");
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
            output.writeObject(user);
        }catch(IOException e){
            System.out.println(e.toString());
        }
    }

    @Override
    public void updateUser(Usuario user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

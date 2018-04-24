/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import core.Usuario;
import java.util.ArrayList;

/**
 *
 * @author JC
 */
public interface UsersDao {
    public void addUser(Usuario user);
    
    public void updateUser(Usuario user);
    
    public ArrayList<Usuario> getUsers();
}

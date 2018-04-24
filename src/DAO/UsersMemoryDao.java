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
public class UsersMemoryDao implements UsersDao{
    
    ArrayList<Usuario> users;

    public UsersMemoryDao() {
        this.users = new ArrayList<Usuario>();
    }
    
    @Override
    public void addUser(Usuario user) {
        this.users.add(user);
    }

    @Override
    public ArrayList<Usuario> getUsers() {
        return this.users;
    }

    @Override
    public void updateUser(Usuario user) {
        for (Usuario user1 : users) {
            if(user1.getNomeDeUsuario().equals(user.getNomeDeUsuario())) {
                if(user1.getSenha().equals(user.getSenha())) {
                    user1 = user;
                    return;
                }
                return;
            }
        }
    }
    
}

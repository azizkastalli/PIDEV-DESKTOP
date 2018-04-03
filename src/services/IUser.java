/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.User;

/**
 *
 * @author USER
 */
public interface IUser {
    
     public boolean VerifyUser(User usr);
    public boolean VerifyIfAdmin(User usr);
    public int GetUserId(User usr);
    
}

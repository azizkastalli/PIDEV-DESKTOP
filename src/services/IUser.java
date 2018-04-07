/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author USER
 * @param <T>
 */
public interface IUser <T>{
    
     public boolean VerifyUser(User usr);
    public boolean VerifyIfAdmin(User usr);
    public int GetUserId(User usr);
    public String GetUserRole(User usr);
    void Create(T obj) throws SQLException;
    void Update(T obj) throws SQLException;
    T Select(T obj) throws SQLException;
    List<T> SelectAll() throws SQLException;
    void Delete(T obj) throws SQLException;
    
}

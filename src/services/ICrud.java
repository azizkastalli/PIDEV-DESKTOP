/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author azizkastalli
 * @param <T>
 */
public interface ICrud <T>
{ 
    void Create(T obj) throws SQLException;
    void Update(T obj) throws SQLException;
    T Select(T obj) throws SQLException;
    List<T> SelectAll(T obj) throws SQLException;
    void Delete(T obj) throws SQLException;
    
}
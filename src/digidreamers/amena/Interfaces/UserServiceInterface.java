/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.Interfaces;

import digidreamers.amena.Models.User;
import java.sql.SQLException;

/**
 *
 * @author aymen
 */
public interface UserServiceInterface {
    public void addUser(User user) throws SQLException;
    
}
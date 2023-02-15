/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.Interfaces;

import digidreamers.amena.Models.User;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author aymen public List<User> afficherUser()
 */
public interface UserServiceInterface {

    public void addUser(User user) throws SQLException;

    public void UpdateUser(User u, int id) throws SQLException, NoSuchAlgorithmException;

    public void deleteUser(int id);

    public List<User> afficherUser();

    public User getUserByName(String name) throws SQLException;

    public User getUserByCIN(String cin) throws SQLException;

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.Interfaces;

import digidreamers.amena.Models.Role;
import java.sql.SQLException;

/**
 *
 * @author aymen
 */
public interface RoleServiceInterface {
   public void addRole (Role role) throws SQLException;
   public void updateRole (Role role,int id)throws SQLException;
}

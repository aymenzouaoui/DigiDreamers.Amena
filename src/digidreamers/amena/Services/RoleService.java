/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.Services;

import digidreamers.amena.Models.Role;
import digidreamers.amena.Utils.MyConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author aymen
 */
public class RoleService {
Connection cnx = MyConnection.getInstance().getConnection();
    Statement stm;
    public RoleService() throws SQLException {
                stm = cnx.createStatement();

    }
    
    public void addRole (Role role) throws SQLException {
        String querry="INSERT INTO `role`( `role`) VALUES ('"+role.getRole()+"')";
        stm.executeUpdate(querry);
        
}
    public void updateRole (Role role,int id)throws SQLException {
        String querry ="UPDATE `role` SET `role`='"+role+"' WHERE `id`="+id;
        stm.executeUpdate(querry);
    }
    
}
